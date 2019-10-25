package apap.tugas1.sibat.repository;

import apap.tugas1.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObatDb extends JpaRepository<ObatModel, Long> {
    List<ObatModel> findAll();

    Optional<ObatModel> findById(Long aLong);

    Optional<ObatModel> findObatModelByNomorRegistrasi(String nomorRegistrasi);





}
