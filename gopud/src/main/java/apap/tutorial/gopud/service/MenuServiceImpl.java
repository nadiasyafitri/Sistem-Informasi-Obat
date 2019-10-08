package apap.tutorial.gopud.service;


import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDB menuDb;

    @Override
    public void addMenu(MenuModel menu){
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran){
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public Optional<MenuModel> getMenuByIdMenu(Long id) {
        return menuDb.findById(id);
    }

    @Override
    public MenuModel changeMenu(MenuModel menuModel) {
        MenuModel targetMenu = menuDb.findById(menuModel.getId()).get();

        try{
            targetMenu.setNama(menuModel.getNama());
            targetMenu.setDeskripsi(menuModel.getDeskripsi());
            targetMenu.setHarga(menuModel.getHarga());
            targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
            menuDb.save(targetMenu);
            return targetMenu;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteMenu (MenuModel menu){
        menuDb.delete(menu);
    }

    @Override
    public List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran){
        return menuDb.findByRestoranIdRestoranOrderByHargaAsc(idRestoran);
    }

}
