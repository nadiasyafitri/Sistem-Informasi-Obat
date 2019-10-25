package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> getObatList() {
        return obatDb.findAll();
    }

    @Override
    public void addObat(ObatModel obat) {
        String kode = "";
        if (obat.getJenis().getNama().equals("Generik")) {
            kode += "1";
        } else {
            kode += "2";
        }

        if (obat.getBentuk().equals("Cairan")) {
            kode += "01";
        } else if (obat.getBentuk().equals("Kapsul")) {
            kode += "02";
        } else {
            kode += "03";
        }
        kode += "20192024";
        kode += getRandomString(2);
        obat.setKode(kode);
        obatDb.save(obat);
    }

    private String getRandomString(int i) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(i);
        for (int j=0;j<i;j++) {
            int index = (int) (alphabet.length() * Math.random());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public Optional<ObatModel> getObatbyNomorRegistrasi(String nomorRegistrasi) {
        return obatDb.findObatModelByNomorRegistrasi(nomorRegistrasi);
    }

    @Override
    public void deleteObat(ObatModel obat) {
        obatDb.delete(obat);
    }

    @Override
    public void deletebyId(Long idObat) {
        obatDb.deleteById(idObat);
    }
}
