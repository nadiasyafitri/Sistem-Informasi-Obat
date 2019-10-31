package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.GudangModel;
import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GudangServiceImpl implements GudangService {
    @Autowired
    private GudangDb gudangDb;

    @Override
    public List<GudangModel> getGudangList() {
        return gudangDb.findAll();
    }

    @Override
    public Optional<GudangModel> getGudangbyId(Long idGudang) {
        return gudangDb.findById(idGudang);
    }

    @Override
    public void addGudang(GudangModel gudang){
        gudangDb.save(gudang);
    }

    @Override
    public void deleteGudang(Long idGudang) {
        gudangDb.deleteById(idGudang);
    }



}

