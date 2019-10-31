package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.*;
import apap.tugas1.sibat.repository.GudangObatDb;
import apap.tugas1.sibat.repository.ObatDb;
import apap.tugas1.sibat.repository.ObatSupplierDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
    @Autowired
    private ObatDb obatDb;

    @Autowired
    private ObatSupplierDb obatSupplierDb;

    @Autowired
    private GudangObatDb gudangObatDb;

    @Override
    public List<ObatModel> getObatList() {
        return obatDb.findAll();
    }

    @Override
    public void addObat(ObatModel obat) {
        generateCode(obat);
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

    @Override
    public List<ObatModel> getObatListbyIdGudang() {
        return obatDb.findAll();
    }

    @Override
    public Optional<ObatModel> getObatbyId(Long id) {
        return obatDb.findById(id);
    }

    @Override
    public void generateCode(ObatModel obatModel) {
        String kode = "";
        if (obatModel.getJenis().getNama().equals("Generik")) {
            kode += "1";
        } else {
            kode += "2";
        }

        if (obatModel.getBentuk().equals("Cairan")) {
            kode += "01";
        } else if (obatModel.getBentuk().equals("Kapsul")) {
            kode += "02";
        } else {
            kode += "03";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String tahunTerbit = format.format(obatModel.getTanggalTerbit());
        Integer tahunKode = Integer.valueOf(tahunTerbit) + 5;
        kode += tahunTerbit + String.valueOf(tahunKode);
        kode += getRandomString(2);
        obatModel.setKode(kode);
    }

    @Override
    public ObatModel changeObat(ObatModel obatModel) {
        ObatModel targetObat = obatDb.findById(obatModel.getId()).get();
        boolean isGenerate = targetObat.getTanggalTerbit() != obatModel.getTanggalTerbit() || targetObat.getBentuk() != obatModel.getBentuk();
        targetObat.setNama(obatModel.getNama());
        targetObat.setHarga(obatModel.getHarga());
        targetObat.setBentuk(obatModel.getBentuk());
        targetObat.setTanggalTerbit(obatModel.getTanggalTerbit());

        if(isGenerate) {
            generateCode(targetObat);
        }
        obatDb.save(targetObat);
        return targetObat;
    }

    @Override
    public List<SupplierModel> getSupplierListbyObat(ObatModel obatModel) {
        List<ObatSupplierModel> supplierObat = obatSupplierDb.getByObat(obatModel);
        List<SupplierModel> supplier = new ArrayList<SupplierModel>();
        for (ObatSupplierModel ob : supplierObat){
            supplier.add(ob.getSupplier());
        }
        return supplier;
    }

    @Override
    public List<GudangModel> getGudang(ObatModel obatModel) {
        List<GudangObatModel> gudangObat = gudangObatDb.getByObat(obatModel);
        List<GudangModel> gudangList = new ArrayList<GudangModel>();
        for (GudangObatModel go : gudangObat){
            gudangList.add(go.getGudang());
        }
        return gudangList;
    }
}

