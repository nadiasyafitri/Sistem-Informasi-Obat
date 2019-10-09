package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranInMemoryService implements RestoranService {
    private List<RestoranModel> listRestoran;

    //Constructor
    public RestoranInMemoryService(){
        listRestoran = new ArrayList<>();
    }

    @Override
    public void addRestoran(RestoranModel restoran) {
        listRestoran.add(restoran);
    }

    @Override
    public List<RestoranModel> getRestoranList() {
        return listRestoran;
    }

    @Override
    public List<RestoranModel> getRestoranListOrder() {
        return null;
    }

    @Override
    public Optional<RestoranModel> getRestoranByIdRestoran(Long idRestoran) {
        Optional<RestoranModel> myResto = null;
        for (int i = 0; i < listRestoran.size(); i++ ){
            Long id = listRestoran.get(i).getIdRestoran();
            if (id.equals(idRestoran)){
                myResto = Optional.ofNullable(listRestoran.get(i));
            }
        }
        return myResto;
    }

//    @Override
//    public void editResto(Long idRestoran, Integer nomorTelepon) {
//        for (int i = 0; i < listRestoran.size(); i++ ){
//            Long id = listRestoran.get(i).getIdRestoran();
//            if (id.equals(idRestoran)){
//                listRestoran.get(i).setNomorTelepon(nomorTelepon);
//            }
//        }
//    }

    @Override
    public void deleteResto(Long idRestoran){
    }

    @Override
    public RestoranModel changeRestoran(RestoranModel restoranModel){
       return null;
    }
}

