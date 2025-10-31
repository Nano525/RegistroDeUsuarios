package mx.edu.utez.registrodeusuarios.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final StringProperty nombre;
    private final StringProperty correo;
    private final StringProperty estado;

    public Usuario(String nombre, String correo, String estado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.estado = new SimpleStringProperty(estado);
    }

    // Getters y Setters para las propiedades
    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
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

    // Método para cambiar el estado
    public void toggleEstado() {
        if ("Activo".equals(estado.get())) {
            setEstado("Inactivo");
        } else {
            setEstado("Activo");
        }
    }
}

