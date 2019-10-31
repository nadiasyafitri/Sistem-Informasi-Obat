package apap.tugas1.sibat.repository;

import apap.tugas1.sibat.model.GudangObatModel;
import apap.tugas1.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GudangObatDb extends JpaRepository<GudangObatModel, Long> {
    List<GudangObatModel> getByObat(ObatModel obatModel);
}
