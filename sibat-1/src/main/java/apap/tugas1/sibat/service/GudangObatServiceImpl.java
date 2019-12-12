package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.repository.GudangObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GudangObatServiceImpl implements GudangObatService{
    @Autowired
    private GudangObatDb gudangObatDb;

    @Override
    public void addGudangObat(GudangObatModel gudangObatModel) {
        gudangObatDb.save(gudangObatModel);
    }

    @Override
    public List<GudangObatModel> sortbyDate(List<GudangObatModel> listGudangobat) {
        List<GudangObatModel> newExpiredList = new ArrayList<GudangObatModel>();
        LocalDate thisDate = LocalDate.now();
        Integer thisYear = thisDate.getYear();

        for (GudangObatModel obat : listGudangobat) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy");
            Integer medYear = Integer.valueOf(formater.format(obat.getObat().getTanggalTerbit()));
            int selisihTahun = thisYear - medYear;

            if (selisihTahun > 5){
                newExpiredList.add(obat);
            }
        }
        return newExpiredList;
    }

    @Override
    public List<GudangObatModel> getAllList() {
        return gudangObatDb.findAll();
    }
}
