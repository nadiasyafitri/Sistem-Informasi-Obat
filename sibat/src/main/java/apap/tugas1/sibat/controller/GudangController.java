package apap.tugas1.sibat.controller;

import apap.tugas1.sibat.model.GudangModel;
import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.service.GudangService;
import apap.tugas1.sibat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class    GudangController {
    @Qualifier("gudangServiceImpl")
    @Autowired
    private GudangService gudangService;
    private ObatService obatService;

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

    //        model.addAttribute("obatList", obatList);

        return "detail-gudang";
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

    @RequestMapping(value = "gudang/hapus/{idGudang}", method = RequestMethod.GET)
    public String deletebyId(@PathVariable Long idGudang, @ModelAttribute GudangModel gudangModel, Model model){
        GudangModel gudang = gudangService.getGudangbyId(idGudang).get();

//        if (obatKu.size() == 0){
//            gudangService.deleteGudang(idGudang);
//
//            model.addAttribute("gudang", gudang);
//
//            return "gudang-deleted";
//        }
        return "not-delete";
    }

}
