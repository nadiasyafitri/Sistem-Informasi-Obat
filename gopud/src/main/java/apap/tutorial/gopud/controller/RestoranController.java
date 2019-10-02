package apap.tutorial.gopud.controller;

import java.util.List;
import java.util.Optional;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;


@Controller
public class RestoranController {

    @Qualifier("restoranServiceImpl")
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String home() {return "home";}

    @RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
    public String addRestoranFormPage(Model model){
        RestoranModel newRestoran = new RestoranModel();
        model.addAttribute("restoran", newRestoran);
        return "form-add-restoran";
    }

    @RequestMapping(value= "/restoran/add", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model){
        restoranService.addRestoran(restoran);
        model.addAttribute("namaResto", restoran.getNama());
        return "add-restoran";
    }

    @RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
        public String view(
                @RequestParam(value = "idRestoran") Long idRestoran, Model model){

        Optional<RestoranModel> restoran = restoranService.getRestoranByIdRestoran(idRestoran);
        if(restoran.isPresent()){
            RestoranModel myResto = restoran.get();
            model.addAttribute("resto", restoran);

            List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(myResto.getIdRestoran());
            model.addAttribute("menuList", menuList);

            return "view-restoran";

        }else{
            return "not-found";
        }
    }

    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model){
        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        model.addAttribute("restoran", existingRestoran);
        return "form-change-restoran";
    }

    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
        model.addAttribute("restoran", newRestoranData);

        return "change-restoran";
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

    @RequestMapping(value = "restoran/delete/{idRestoran}", method = RequestMethod.GET)
    public String deletebyId(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoranModel, Model model){
        RestoranModel resto = restoranService.getRestoranByIdRestoran(idRestoran).get();
        List<MenuModel> myMenu = menuService.findAllMenuByIdRestoran(idRestoran);

        if (myMenu.size() == 0){
            restoranService.deleteResto(idRestoran);

            model.addAttribute("restoran", resto);

            return "deleted-view";
        }
        return "not-deleted";
    }

}


