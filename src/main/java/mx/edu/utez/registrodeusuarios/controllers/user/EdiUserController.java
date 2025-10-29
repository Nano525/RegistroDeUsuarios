package mx.edu.utez.registrodeusuarios.controllers.user;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EdiUserController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Button btnGuardar;

    @FXML
    private void initialize() {
        // Inicialización del controlador
        btnGuardar.setOnAction(event -> guardarDatos());
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos",
                    "Por favor completa todos los campos obligatorios (*).");
            return;
        }

        // Aquí podrías agregar lógica para actualizar los datos en la base de datos
        // Por ejemplo: usuarioService.actualizarUsuario(new Usuario(nombre, apellidos, correo, contrasena));

        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Datos guardados",
                "Los datos del usuario se han actualizado correctamente.");

        // Limpia los campos después de guardar
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtCorreo.clear();
        txtContrasena.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
