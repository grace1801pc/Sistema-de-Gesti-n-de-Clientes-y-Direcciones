package com.example.demo.dto;

public class ClienteDTO {

    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String correo;
    private String celular;
    private DireccionClienteDTO direccionMatriz;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String tipoIdentificacion, String numeroIdentificacion,
                      String nombres, String correo, String celular,
                      DireccionClienteDTO direccionMatriz) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.correo = correo;
        this.celular = celular;
        this.direccionMatriz = direccionMatriz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public DireccionClienteDTO getDireccionMatriz() {
        return direccionMatriz;
    }

    public void setDireccionMatriz(DireccionClienteDTO direccionMatriz) {
        this.direccionMatriz = direccionMatriz;
    }
}
