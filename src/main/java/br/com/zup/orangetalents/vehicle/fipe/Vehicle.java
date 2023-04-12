package br.com.zup.orangetalents.vehicle.fipe;

public class Vehicle {

    private String nome;
    private String codigo;

    public Vehicle() {
    }

    public Vehicle(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
