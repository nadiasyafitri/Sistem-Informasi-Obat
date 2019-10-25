package apap.tugas1.sibat.repository;

import apap.tugas1.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JenisDb extends JpaRepository<JenisModel, Long> {
    List<JenisModel> findAll();
}
