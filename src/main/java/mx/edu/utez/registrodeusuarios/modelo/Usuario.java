package mx.edu.utez.registrodeusuarios.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty correo;
    private final StringProperty estado;

    public Usuario(String nombre, String apellidos, String correo, String estado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.correo = new SimpleStringProperty(correo);
        this.estado = new SimpleStringProperty(estado);
    }

    public Usuario(String nombreCompleto, String correo, String estado) {
        String[] partes = nombreCompleto.split(" ", 2);
        this.nombre = new SimpleStringProperty(partes.length > 0 ? partes[0] : "");
        this.apellidos = new SimpleStringProperty(partes.length > 1 ? partes[1] : "");
        this.correo = new SimpleStringProperty(correo);
        this.estado = new SimpleStringProperty(estado);
    }
    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getNombreCompleto() {
        return nombre.get() + " " + apellidos.get();
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public void toggleEstado() {
        if ("ACTIVO".equalsIgnoreCase(estado.get()) || "Activo".equals(estado.get())) {
            setEstado("INACTIVO");
        } else {
            setEstado("ACTIVO");
        }
    }
}

