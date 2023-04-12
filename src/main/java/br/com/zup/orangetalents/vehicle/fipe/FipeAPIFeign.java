package br.com.zup.orangetalents.vehicle.fipe;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "${fipe.feign.name}", url = "${fipe.feign.url}")
public interface FipeAPIFeign {

    @GetMapping("/carros/marcas")
    List<Brand> getBrands();

    @GetMapping("/carros/marcas/{id}/modelos")
    Models getModels(@PathVariable String id);

    @GetMapping("/carros/marcas/{idMarca}/modelos/{idModelo}/anos")
    List<Vehicle> getYears(@PathVariable String idMarca, @PathVariable Integer idModelo);

    @GetMapping("/carros/marcas/{idMarca}/modelos/{idModelo}/anos/{ano}")
    VehicleDetails getVehicleDetails(@PathVariable String idMarca, @PathVariable Integer idModelo,
                                            @PathVariable String ano);
}
