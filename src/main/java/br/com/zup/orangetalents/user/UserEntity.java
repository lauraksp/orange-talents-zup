package br.com.zup.orangetalents.user;

import br.com.zup.orangetalents.vehicle.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cpf", unique = true, nullable = true)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @OneToMany (mappedBy = "user")
    @JsonManagedReference
    private Set<VehicleEntity> vehicles;


    public UserEntity() {
    }

    public UserEntity(int id, String firstName, String email, String cpf, LocalDateTime birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Set<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }
}
