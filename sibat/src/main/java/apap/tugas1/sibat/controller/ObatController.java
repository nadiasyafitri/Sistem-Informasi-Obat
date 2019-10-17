package apap.tugas1.sibat.controller;

import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @RequestMapping("/")
    public String home(Model model) {
        List<ObatModel> obatList = obatService.getObatList();

        model.addAttribute("obatList", obatList);

        return "beranda";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
    public String addObatFormPage(Model model){
        ObatModel newObat = new ObatModel();

        model.addAttribute("obat", newObat);

        return "form-add-obat";
    }

    @RequestMapping(value = "/obat/", method = RequestMethod.POST)
    public String addObatPageSubmit(@ModelAttribute ObatModel obat, Model model){
        obatService.addObat(obat);

        model.addAttribute("namaObat", obat.getNama());

        model.addAttribute("kodeObat", obat.getKode());

        return "add-obat-done";
    }

    @RequestMapping(value = "/obat/view", method = RequestMethod.GET)
    public String viewObatbyRegisCode(@RequestParam(value = "nomorRegistrasi", required = true) String nomorRegistrasi, Model model){
        ObatModel obat = obatService.getObatbyNomorRegistrasi(nomorRegistrasi).get();

        model.addAttribute("obatList", obat);


        return "detail-obat";
    }


}
