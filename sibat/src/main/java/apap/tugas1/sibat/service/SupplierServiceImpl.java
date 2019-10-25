package apap.tugas1.sibat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierDb supplierDb;
}
