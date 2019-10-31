package apap.tugas1.sibat.controller;

import apap.tugas1.sibat.model.*;
import apap.tugas1.sibat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;
    @Autowired
    private JenisService jenisService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ObatSupplierService obatSupplierService;
    @Autowired
    private GudangService gudangService;

    @RequestMapping("/")
    public String home(Model model) {
        List<ObatModel> obatList = obatService.getObatList();

        model.addAttribute("obatList", obatList);

        return "home";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
    public String addObatFormPage(Model model){
        ObatModel newObat = new ObatModel();
        JenisModel newJenis = new JenisModel();
        List<ObatSupplierModel> daftarSupplier = new ArrayList<ObatSupplierModel>();
        daftarSupplier.add(new ObatSupplierModel());
        newObat.setListObatSupplier(daftarSupplier);
        newObat.setJenis(newJenis);
        List<JenisModel> listJenis = jenisService.getJenisList();
        List<SupplierModel> listSupplier = supplierService.getListSupplier();
        model.addAttribute("obat", newObat);
        model.addAttribute("jenisObat", newJenis);
        model.addAttribute("jenisList", listJenis);
        model.addAttribute("supplierList", listSupplier);
        model.addAttribute("obat", newObat);

        return "form-add-obat";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.POST, params = "addRow")
    private String addRow (@ModelAttribute ObatModel obatModel, Model model, BindingResult bindingResult){
        ObatSupplierModel obatSupplierModel = new ObatSupplierModel();
        List<SupplierModel> listSupplier = supplierService.getListSupplier();
        List<JenisModel> listJenis = jenisService.getJenisList();

        obatModel.getListObatSupplier().add(obatSupplierModel);

        model.addAttribute("jenisList", listJenis);
        model.addAttribute("supplierList", listSupplier);
        model.addAttribute("obat", obatModel);

        return "form-add-obat";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.POST)
    public String addObatPageSubmit(@ModelAttribute ObatModel obat, Model model){

        System.out.println(obat.getListObatSupplier().isEmpty());

        for (ObatSupplierModel obatSupplier : obat.getListObatSupplier()){
            obatSupplier.setObat(obat);
        }

        obatService.addObat(obat);

        model.addAttribute("namaObat", obat.getNama());

        model.addAttribute("kodeObat", obat.getKode());

        return "add-obat-done";
    }

    @RequestMapping(value = "/obat/view", method = RequestMethod.GET)
    public String viewObatbyRegisCode(@RequestParam(value = "noReg", required = true) String nomorRegistrasi, Model model){
        ObatModel obat = obatService.getObatbyNomorRegistrasi(nomorRegistrasi).get();

        model.addAttribute("obat", obat);

        List<GudangModel> gudang = obatService.getGudang(obat);

        model.addAttribute("gudangObat", gudang);

        List<SupplierModel> supplierList = obatService.getSupplierListbyObat(obat);

        model.addAttribute("listSupplier", supplierList);

        return "detail-obat";
    }

    @RequestMapping(value = "/obat/ubah", method = RequestMethod.GET)
    public String changeObatFormPage(@RequestParam(value = "id", required = true) Long id, Model model){
        ObatModel existingObat = obatService.getObatbyId(id).get();
        System.out.println("0form");
        JenisModel jenisObat = new JenisModel();
        System.out.println("1form");
        List<JenisModel> listJenis = jenisService.getJenisList();
        System.out.println("2form");

        List<SupplierModel> listSupplier = supplierService.getListSupplier();
        System.out.println("3form");
        model.addAttribute("obat", existingObat);
        model.addAttribute("jenisObat", jenisObat);
        model.addAttribute("jenisList", listJenis);
        model.addAttribute("supplierList", listSupplier);
        return "form-update-obat";
    }

    @RequestMapping(value = "/obat/ubah", method = RequestMethod.POST)
    public String changeObatFormSubmit(@RequestParam(value = "id", required = true) Long id, @ModelAttribute ObatModel obat, Model model){
        ObatModel newObatData = obatService.changeObat(obat);

        model.addAttribute("obatNama", newObatData.getNama());

        model.addAttribute("obatKode", newObatData.getKode());

        return "change-obat";
    }

    @RequestMapping(value = "/obat/hapus/{id}", method = RequestMethod.POST)
    public String deleteObat(@PathVariable Long id, @ModelAttribute ObatModel obatModel, Model model){
        ObatModel obat = obatService.getObatbyId(id).get();

        obatService.deleteObat(obat);

        return "home";
    }

    @RequestMapping(value = "obat/filter", method = RequestMethod.GET)
    public String filter(@RequestParam(value = "idGudang", required = false) Long idGudang,
                         @RequestParam(value = "idJenis", required = false) Long idJenis,
                         @RequestParam(value = "idSupplier", required = false) Long idSupplier,
                         Model model){

        List<SupplierModel> supplierList = supplierService.getListSupplier();
        List<ObatModel> listObat = obatService.getObatList();
        List<JenisModel> listJenis = jenisService.getJenisList();
        List<GudangModel> listGudang = gudangService.getGudangList();

        List<ObatModel> obatGudang = null;
        if(idGudang != null){
            List<GudangObatModel> gudangObatModelList = gudangService.getGudangbyId(idGudang).get().getListGudangobat();
            obatGudang = new ArrayList<>();
            for(GudangObatModel go : gudangObatModelList){
                obatGudang.add(go.getObat());
            }
            listObat = listObat.stream().filter(obatGudang::contains).collect(Collectors.toList());
            GudangModel gudang = gudangService.getGudangbyId(idGudang).get();
            String namaGudang = gudang.getNama();
            model.addAttribute("namaGudang", namaGudang);
        }

        List<ObatModel> obatSupplier = null;
        if(idSupplier != null){
            List<ObatSupplierModel> ObatSupplierList = supplierService.getSupplierbyId(idSupplier).get().getListObatSupplier();
            obatSupplier = new ArrayList<>();
            for (ObatSupplierModel os : ObatSupplierList ){
                obatSupplier.add(os.getObat());
            }
            listObat = listObat.stream().filter(obatSupplier::contains).collect(Collectors.toList());
            SupplierModel supplierModel = supplierService.getSupplierbyId(idSupplier).get();
            String namaSupp = supplierModel.getNama();
            model.addAttribute("namaSupplier",namaSupp);
        }

        if(idJenis != null){
            JenisModel jeni = jenisService.getJenisById(idJenis).get();
            List<ObatModel> tmp = new ArrayList<>(listObat);
            listObat = new ArrayList<>();
            for(ObatModel obat:tmp){
                if(jeni == obat.getJenis()){
                    listObat.add(obat);
                }
            }
        }
        model.addAttribute("daftarObat", listObat);
        model.addAttribute("supplierList", supplierList);
        model.addAttribute("jenisList",listJenis);
        model.addAttribute("gudangList",listGudang);

        return "filter-obat";
    }

}
