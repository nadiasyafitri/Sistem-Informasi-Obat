package apap.tugas1.sibat.service;


import apap.tugas1.sibat.model.SupplierModel;
import apap.tugas1.sibat.repository.SupplierDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierDb supplierDb;

    @Override
    public List<SupplierModel> getSupplierList() {
        return supplierDb.findAll();
    }
}
