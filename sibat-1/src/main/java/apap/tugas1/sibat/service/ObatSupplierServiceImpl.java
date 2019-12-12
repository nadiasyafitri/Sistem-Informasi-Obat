package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.ObatSupplierModel;
import apap.tugas1.sibat.repository.ObatSupplierDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatSupplierServiceImpl implements ObatSupplierService{
    @Autowired
    ObatSupplierDb obatSupplierDb;

    @Override
    public List<ObatSupplierModel> getObatSupplierList() {
        return obatSupplierDb.findAll();
    }
}
