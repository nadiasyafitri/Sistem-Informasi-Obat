package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.GudangModel;
import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.model.SupplierModel;

import java.util.List;
import java.util.Optional;

public interface ObatService {

    List<ObatModel> getObatList();
    void addObat(ObatModel obat);
    Optional<ObatModel> getObatbyNomorRegistrasi(String nomorRegistrasi);
    void deleteObat(ObatModel obat);
    void deletebyId(Long idObat);
    List<ObatModel> getObatListbyIdGudang();
    Optional<ObatModel> getObatbyId(Long id);
    ObatModel changeObat(ObatModel obatModel);
    void generateCode(ObatModel obatModel);
    List<SupplierModel> getSupplierListbyObat(ObatModel obatModel);
    List<GudangModel> getGudang(ObatModel obatModel);
}
