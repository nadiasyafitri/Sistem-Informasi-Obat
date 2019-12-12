package apap.tugas1.sibat.service;


import apap.tugas1.sibat.model.JenisModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

public interface JenisService {
    List<JenisModel> getJenisList();
    Optional<JenisModel> getJenisById(Long id);
}
