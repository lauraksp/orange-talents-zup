package br.com.zup.orangetalents.vehicle;

import br.com.zup.orangetalents.exception.BrandNotFoundException;
import br.com.zup.orangetalents.exception.ModelNotFoundException;
import br.com.zup.orangetalents.exception.VehicleYearNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity saveVehicle(@RequestBody VehicleEntity vehicle, @PathVariable("id") Integer id) throws BrandNotFoundException,
                ModelNotFoundException, VehicleYearNotFoundException {
        return vehicleService.saveVehicle(vehicle, id);
    }
}
