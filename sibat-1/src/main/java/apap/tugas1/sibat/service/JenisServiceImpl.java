package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.JenisModel;
import apap.tugas1.sibat.repository.JenisDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JenisServiceImpl implements JenisService{
    @Autowired
    JenisDb jenisDb;

    public List<JenisModel> getJenisList(){
        return jenisDb.findAll();
    }

    @Override
    public Optional<JenisModel> getJenisById(Long id) {
        return jenisDb.findById(id);
    }
}
