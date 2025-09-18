package com.example.demo.dto;

public class DireccionClienteDTO {

    private Long id;
    private String provincia;
    private String ciudad;
    private String direccion;
    private boolean matriz;

    public DireccionClienteDTO() {
    }

    public DireccionClienteDTO(Long id, String provincia, String ciudad,
                               String direccion, boolean matriz) {
        this.id = id;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.matriz = matriz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isMatriz() {
        return matriz;
    }

    public void setMatriz(boolean matriz) {
        this.matriz = matriz;
    }
}
