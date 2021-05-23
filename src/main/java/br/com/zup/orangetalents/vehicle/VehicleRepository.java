package br.com.zup.orangetalents.vehicle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository <VehicleEntity, Integer> {
}
