package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public RestoranModel getRestoranByIdRestoran(String idRestoran) {
        RestoranModel myResto = null;
        for (int i = 0; i < listRestoran.size(); i++ ){
            String id = listRestoran.get(i).getIdRestoran();
            if (id.equals(idRestoran)){
                myResto = listRestoran.get(i);
            }
        }
        return myResto;
    }

    @Override
    public void editResto(String idRestoran, Integer nomorTelepon) {
        for (int i = 0; i < listRestoran.size(); i++ ){
            String id = listRestoran.get(i).getIdRestoran();
            if (id.equals(idRestoran)){
                listRestoran.get(i).setNomorTelepon(nomorTelepon);
            }
        }
    }

    @Override
    public void deleteResto(String idRestoran){
        for (int i = 0; i < listRestoran.size(); i++ ){
            String id = listRestoran.get(i).getIdRestoran();
            if (id.equals(idRestoran)){
                listRestoran.remove(i);
            }
        }
    }
}
