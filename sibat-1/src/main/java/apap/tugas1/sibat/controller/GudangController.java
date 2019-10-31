package apap.tugas1.sibat.controller;

import apap.tugas1.sibat.model.GudangModel;
import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.model.ObatSupplierModel;
import apap.tugas1.sibat.service.GudangObatService;
import apap.tugas1.sibat.service.GudangService;
import apap.tugas1.sibat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {
    @Qualifier("gudangServiceImpl")

    @Autowired
    private GudangService gudangService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private GudangObatService gudangObatService;

    @RequestMapping("/gudang")
    public String viewGudang(Model model){
        List<GudangModel> gudangList = gudangService.getGudangList();

        model.addAttribute("gudangList", gudangList);

        return "view-gudang";
    }

    @RequestMapping(value = "/gudang/view", method = RequestMethod.GET)
    public String detailGudang(
            @RequestParam(value = "idGudang", required = true) Long idGudang, Model model){

        GudangModel gudang = gudangService.getGudangbyId(idGudang).get();

        model.addAttribute("gudang", gudang);

        //Get List Obat from Gudang Obat
        List<GudangObatModel> gudangObat = gudang.getListGudangobat();

        Integer size = gudangObat.size();

        model.addAttribute("size", size);

        model.addAttribute("gudangObatList", gudangObat);

        List<ObatModel> obatList = obatService.getObatList();

        model.addAttribute("obatList", obatList);

        return "detail-gudang";
    }

    @RequestMapping(value = "/gudang/tambah-obat", method = RequestMethod.POST)
    public String assignObatatGudang(@ModelAttribute GudangModel gudangModel, Model model, HttpServletRequest request){
        // Get idGudang data
        Long idObat = Long.valueOf(request.getParameter("obat"));
        Long idGudang = Long.valueOf(request.getParameter("id"));

        GudangModel gudang = gudangService.getGudangbyId(idGudang).get();
        ObatModel obat =obatService.getObatbyId(idObat).get();

        List<ObatModel> availableMed = obatService.getObatList();
        List<GudangObatModel> gudangMeds = gudang.getListGudangobat();

        List<ObatModel> gudangMeds2 = new ArrayList<ObatModel>();
        for (GudangObatModel ob : gudangMeds){
            ObatModel obs = ob.getObat();
            gudangMeds2.add(obs);
        }

//        List<ObatModel> notAvailMed = new ArrayList<ObatModel>();


        availableMed.removeAll(gudangMeds2);
//        Integer size = gudangObat.size();
        model.addAttribute("size", gudangMeds.size());

//        List<ObatModel> obatList = obatService.getObatList();
        model.addAttribute("obatList", availableMed);

        if (gudangMeds2.contains(obat)){
            model.addAttribute("gudang",gudang);
            model.addAttribute("gudangObatList", gudangMeds);
            return "detail-gudang";
        }else {
            GudangObatModel obatGudang = new GudangObatModel();
            model.addAttribute("gudang",gudang);
            obatGudang.setObat(obatService.getObatbyId(idObat).get());
            obatGudang.setGudang(gudangService.getGudangbyId(idGudang).get());
            gudangObatService.addGudangObat(obatGudang);
            model.addAttribute("gudangObatList", gudang.getListGudangobat());
            return "redirect:/gudang/view?idGudang=" + idGudang;
        }
    }


    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.GET)
    public String addGudangFormPage(Model model){
        GudangModel newGudang = new GudangModel();
        model.addAttribute("gudang", newGudang);
        return "form-add-gudang";
    }

    @RequestMapping(value= "/gudang/tambah", method = RequestMethod.POST)
    public String addgudangSubmit(@ModelAttribute GudangModel gudang, Model model){
        gudangService.addGudang(gudang);

        model.addAttribute("gudang", gudang.getNama());

        return "gudang-added";
    }

    @RequestMapping(value = "gudang/hapus/{id}", method = RequestMethod.GET)
    public String deletebyId(@PathVariable Long id, @ModelAttribute GudangModel gudangModel, Model model){
        GudangModel gudang = gudangService.getGudangbyId(id).get();

        List<GudangObatModel> gudangObat = gudang.getListGudangobat();

        Integer size = gudangObat.size();

        if (size == 0){
            gudangService.deleteGudang(id);

            model.addAttribute("namaGudang", gudang.getNama());

            return "deleted-view";
        }
        return "not-delete";
    }

    @RequestMapping(value = "/gudang/search", method = RequestMethod.GET)
    public String searchExpiredMed(Model model){
        List<GudangModel> gudangObat = gudangService.getGudangList();
        model.addAttribute("gudangObat",gudangObat);
        return "expired-med";
    }

    @RequestMapping(path = "/gudang/expired-obat", method = RequestMethod.GET)
    public String ExpiredMed(@RequestParam(value = "idGudang") Long idGudang, Model model){
        GudangModel gudang = gudangService.getGudangbyId(idGudang).get();

        List<GudangObatModel> listGudangObat = gudang.getListGudangobat();

        listGudangObat = gudangObatService.sortbyDate(listGudangObat);

        model.addAttribute("gudangObatList", listGudangObat);

        return "tabel-expired";
    }




}
