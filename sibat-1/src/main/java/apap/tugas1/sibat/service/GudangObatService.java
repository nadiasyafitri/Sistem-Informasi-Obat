package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;

import java.util.List;

public interface GudangObatService{
    void addGudangObat(GudangObatModel gudangObatModel);
    List<GudangObatModel> getAllList();
    List<GudangObatModel> sortbyDate(List<GudangObatModel> listGudangobat);
}
