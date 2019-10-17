package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        obatDb.save(obat);
    }
}
