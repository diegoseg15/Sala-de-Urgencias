package com.diegoseg15.atencion.domain;

public class PacienteAtendido {

    private String dni;
    private String nombre;
    private String apellido;
    private String genero;
    private String grado;
    private String estado;

    public PacienteAtendido() {}

    public PacienteAtendido(PacienteMensaje paciente) {
        this.dni = paciente.getDni();
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.genero = paciente.getGenero();
        this.grado = paciente.getGrado();
        this.estado = paciente.getEstado();
    }

    // Getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
     

