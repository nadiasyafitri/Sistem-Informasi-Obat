package apap.tugas1.sibat.service;

import apap.tugas1.sibat.model.ObatSupplierModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ObatSupplierService {
    List<ObatSupplierModel> getObatSupplierList();
}
