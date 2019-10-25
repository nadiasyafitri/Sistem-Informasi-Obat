package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.ObatModel;

import java.util.List;
import java.util.Optional;

public interface ObatService {

    List<ObatModel> getObatList();
    void addObat(ObatModel obat);
    Optional<ObatModel> getObatbyNomorRegistrasi(String nomorRegistrasi);
    void deleteObat(ObatModel obat);
    void deletebyId(Long idObat);
}
