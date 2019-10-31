package apap.tugas1.sibat.repository;

import apap.tugas1.sibat.model.GudangModel;
import apap.tugas1.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GudangDb extends JpaRepository<GudangModel, Long> {
    List<GudangModel> findAll();
    Optional<GudangModel> findById(Long id);
//    List<ObatModel> findObatByIdGudang(Long idGudang);
}
