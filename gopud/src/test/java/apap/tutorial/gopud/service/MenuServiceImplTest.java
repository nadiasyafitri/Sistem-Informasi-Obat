package apap.tutorial.gopud.service;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.repository.RestoranDB;
import apap.tutorial.gopud.service.RestoranService;
import apap.tutorial.gopud.service.RestoranServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.constraints.Null;
import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

    @InjectMocks
    MenuService menuService = new MenuServiceImpl();

    @Mock
    MenuDB menuDB;

    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave() {
        MenuModel newMenu = new MenuModel();
        newMenu.setNama("ayam");
        newMenu.setHarga(BigInteger.valueOf(200));
        newMenu.setDurasiMasak(30);
        newMenu.setDeskripsi("enak");
        menuService.addMenu(newMenu);
        verify(menuDB, times(1)).save(newMenu);
    }

    @Test
    public void whenGetMenuListCalledItShouldReturnAllMenu() {
        List<MenuModel> allMenuInDatabase = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allMenuInDatabase.add(new MenuModel());
        }
        when (menuService.getMenuList()).thenReturn(allMenuInDatabase);
        List<MenuModel> dataFromServiceCall = menuService.getMenuList();
        assertEquals(3, dataFromServiceCall.size());

        verify(menuDB, times(1)).findAll();
    }

    @Test
    public void whenGetMenuByIdCalledByExistingDataItShouldReturnCorrectData() {
        MenuModel returnedData = new MenuModel();
        returnedData.setNama("kaefci");
        returnedData.setHarga(BigInteger.valueOf(200));
        returnedData.setId((long)1);
        returnedData.setDurasiMasak(14022);
        returnedData.setDeskripsi("enak sekali");
        when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(returnedData
        ));
        Optional<MenuModel> dataFromServiceCall =
                menuService.getMenuByIdMenu(1L);
        verify(menuDB, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());
        MenuModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("kaefci", dataFromOptional.getNama());
        assertEquals(BigInteger.valueOf(200), dataFromOptional.getHarga());
        assertEquals(Long.valueOf(1), dataFromOptional.getId());
        assertEquals(Integer.valueOf(14022), dataFromOptional.getDurasiMasak());
        assertEquals("enak sekali", dataFromOptional.getDeskripsi());
    }

    @Test
    public void whenChangeMenuCalledItShouldChangeMenuData() {
        MenuModel updatedData = new MenuModel();
        updatedData.setNama("kaefci");
        updatedData.setHarga(BigInteger.valueOf(700));
        updatedData.setId((long)1);
        updatedData.setDurasiMasak(14022);
        updatedData.setDeskripsi("ayam goreng");
        when(menuDB.findById(1L)).thenReturn(Optional.of(updatedData));
        when(menuService.changeMenu(updatedData)).thenReturn(updatedData);
        MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
        assertEquals("kaefci", dataFromServiceCall.getNama());
        assertEquals(BigInteger.valueOf(700), dataFromServiceCall.getHarga());
        assertEquals(Long.valueOf(1), dataFromServiceCall.getId());
        assertEquals(Integer.valueOf(14022), dataFromServiceCall.getDurasiMasak());
        assertEquals("ayam goreng", dataFromServiceCall.getDeskripsi());
    }

    @Test(expected = NullPointerException.class)
    public void whenNull(){
        MenuModel updateddata = null;
        menuService.changeMenu(updateddata);
    }

    @Test
    public void whenfindAllReturnedAllData(){
        List<MenuModel> allMenuInTheDatabase = new ArrayList<>();
        for (int loop = 3; loop> 0; loop--){
            allMenuInTheDatabase.add(new MenuModel());
        }
        when(menuService.findAllMenuByIdRestoran(1L)).thenReturn(allMenuInTheDatabase);
        List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(1L);
        assertEquals(3, dataFromServiceCall.size());
        verify(menuDB, times(1)).findByRestoranIdRestoran(1L);
    }

    @Test
    public void whenGetListMenuByHargaAscReturnCorrectData(){
        List<MenuModel> allMenuInTheDatabase = new ArrayList<>();
        for (int loop = 3; loop> 0; loop--){
            allMenuInTheDatabase.add(new MenuModel());
        }
        when(menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(allMenuInTheDatabase);
        List<MenuModel> dataFromServiceCall = menuService.getListMenuOrderByHargaAsc(1L);
        assertEquals(3, dataFromServiceCall.size());
        verify(menuDB, times(1)).findByRestoranIdRestoranOrderByHargaAsc(1L);

    }

    @Test
    public void whenDeleteMenuItShouldDeleteMenuData(){
        MenuModel deletedData = new MenuModel();
        deletedData.setId((long)1);
        deletedData.setNama("Pie");
        deletedData.setDurasiMasak(900);
        deletedData.setHarga(BigInteger.valueOf(200));
        deletedData.setDeskripsi("yummy");
        when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(deletedData));
        menuService.deleteMenu(deletedData);
        verify(menuDB, times(1)).delete(deletedData);
    }




}
