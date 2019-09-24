package apap.tutorial.gopud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;


@Controller
public class RestoranController {

    @Autowired
    private RestoranService restoranService;

    //URL Mapping add
    @RequestMapping("/restoran/add")
    public String add(
            //Request Param untuk di Pass
            @RequestParam(value = "idRestoran", required = true) String idRestoran,
            @RequestParam(value = "nama", required = true) String nama,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
            Model model
    ) {
        //Membuat objek RestoranModel
        RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);

        //Memanggil service addRestoran
        restoranService.addRestoran(restoran);

        // Add variable nama restoran ke "namaResto" untuk dirender
        model.addAttribute("namaResto", nama);

        //Return view template
        return "add-restoran";

    }

    // URL mapping view
    @RequestMapping("restoran/view")
    public String view(
            //Request Parameter untuk dipass
            @RequestParam(value = "idRestoran") String idRestoran, Model model
    ) {
        //Mengambil objek Restoran/Model yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        if (restoran == null){
            return "not-found";
        }

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        //Return view template
        return "view-restoran";
    }

    //URL mapping viewAll
    @RequestMapping("restoran/viewall")
    public String viewall(Model model) {

        // Mengambil semua objek restoran model yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("restoList", listRestoran);

        // Return view template
        return "viewall-restoran";
    }

    @GetMapping(value = "/restoran/view/id-restoran/{idRestoran}")
    public String viewRestoWithPathVariable(
            @PathVariable(value = "idRestoran")
                    String idRestoran,
            Model model) {

        //Mengambil objek Restoran/Model yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        if (restoran == null){
            return "not-found";
        }

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        //Return view template
        return "view-restoran";
    }

    @GetMapping(value = "/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
    public String updateNomorTelepon(
            @PathVariable(value = "idRestoran")
                    String idRestoran,
            @PathVariable(value = "nomorTelepon")
                    Integer nomorTelepon,
            Model model
    ) {
        //Mengambil objek Restoran/Model yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        if (restoran == null){
            return "not-found";
        }

        restoranService.editResto(idRestoran, nomorTelepon);

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        //Return view template
        return "updated-view";
    }

    @GetMapping(value = "/restoran/delete/id/{idRestoran}")
    public String deleteResto(
            @PathVariable(value = "idRestoran")
                    String idRestoran,
            Model model
    ) {
        //Mengambil objek Restoran/Model yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        if (restoran == null){
            return "not-found";
        }

        restoranService.deleteResto(idRestoran);

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        //Return view template
        return "deleted-view";
    }
}


