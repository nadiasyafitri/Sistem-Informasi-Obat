package apap.tugas1.sibat.repository;

import apap.tugas1.sibat.model.ObatModel;
import apap.tugas1.sibat.model.ObatSupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObatSupplierDb extends JpaRepository<ObatSupplierModel, Long> {
    List<ObatSupplierModel> getByObat(ObatModel obatModel);
}
