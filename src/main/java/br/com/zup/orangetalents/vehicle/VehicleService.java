package br.com.zup.orangetalents.vehicle;

import br.com.zup.orangetalents.exception.BrandNotFoundException;
import br.com.zup.orangetalents.exception.ModelNotFoundException;
import br.com.zup.orangetalents.exception.VehicleYearNotFoundException;
import br.com.zup.orangetalents.user.UserEntity;
import br.com.zup.orangetalents.utils.Utils;
import br.com.zup.orangetalents.vehicle.fipe.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FipeAPIFeign fipeAPIFeign;


    public VehicleEntity saveVehicle(VehicleEntity vehicle, Integer id) throws BrandNotFoundException, ModelNotFoundException, VehicleYearNotFoundException {
        Brand brandFipe = fipeAPIFeign.getBrands()
                .stream()
                .filter(bf -> bf.getNome().equalsIgnoreCase(vehicle.getBrand()))
                .findFirst()
                .orElseThrow(BrandNotFoundException::new);

        String idMarca = brandFipe.getCodigo();

        Models.Model model = fipeAPIFeign.getModels(idMarca).getModelos()
                .stream()
                .filter(m -> m.getNome().equalsIgnoreCase(vehicle.getModel()))
                .findFirst()
                .orElseThrow(ModelNotFoundException::new);

        Integer idModelo = model.getCodigo();

        Vehicle vehicleYear = fipeAPIFeign.getYears(idMarca, idModelo)
                .stream()
                .filter(v -> v.getCodigo().equals(vehicle.getYear()))
                .findFirst()
                .orElseThrow(VehicleYearNotFoundException::new);

        String ano = vehicleYear.getCodigo();

        VehicleDetails vehicleDetails = fipeAPIFeign.getVehicleDetails(idMarca, idModelo, ano);

        vehicle.setValue(vehicleDetails.getValor());
        setVehicleRotationInfo(vehicle);

        UserEntity user = new UserEntity();
        user.setId(id);

        vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }

    private void setVehicleRotationInfo(VehicleEntity vehicle) {
        String dayWeek = "";
        String lastDigitYear = vehicle.getYear().substring(vehicle.getYear().length() - 1);

        switch (lastDigitYear) {
            case "0" :
            case "1":
                dayWeek = "segunda-feira";
                break;

            case "2" :
            case "3" :
                dayWeek = "terça-feira";
                break;

            case "4" :
            case "5" :
                dayWeek = "quarta-feira";
                break;

            case "6" :
            case "7" :
                dayWeek = "quinta-feira";
                break;

            case "8" :
            case "9" :
                dayWeek = "sexta-feira";
                break;
        }

        vehicle.setDayRotation(dayWeek);

        if(dayWeek.equals(Utils.getDayWeek())){
            vehicle.setActiveRotation(true);
        }
    }
}
