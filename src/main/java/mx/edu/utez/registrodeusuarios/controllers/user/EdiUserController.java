package mx.edu.utez.registrodeusuarios.controllers.user;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.edu.utez.registrodeusuarios.modelo.User;
import mx.edu.utez.registrodeusuarios.modelo.dao.UserDao;

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

    private String correoOriginal;
    private Runnable onGuardarExitoso;

    @FXML
    private void initialize() {
        btnGuardar.setOnAction(e -> guardarDatos());
        txtCorreo.setEditable(false);
        txtCorreo.setStyle("-fx-background-color: #e0e0e0;");
    }

    public void cargarUsuario(String correo) {
        this.correoOriginal = correo;
        
        UserDao dao = new UserDao();
        User user = dao.getUserByCorreo(correo);
        
        if (user != null) {
            txtNombre.setText(user.getNombre());
            txtApellidos.setText(user.getApellidos());
            txtCorreo.setText(user.getCorreo());
            txtContrasena.clear();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", 
                    "No se pudo cargar el usuario con correo: " + correo);
        }
    }

    public void setOnGuardarExitoso(Runnable callback) {
        this.onGuardarExitoso = callback;
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos",
                    "Por favor completa todos los campos obligatorios (*).");
            return;
        }

        if (!correo.equals(correoOriginal)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error",
                    "El correo electrónico no puede ser modificado.");
            txtCorreo.setText(correoOriginal);
            return;
        }

        User user = new User();
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setCorreo(correo);
        
        if (contrasena.isEmpty()) {
            UserDao daoTemp = new UserDao();
            User userTemp = daoTemp.getUserByCorreo(correo);
            if (userTemp != null) {
                user.setContrasena(userTemp.getContrasena());
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error",
                        "No se pudo obtener la información del usuario.");
                return;
            }
        } else {
            user.setContrasena(contrasena);
        }

        UserDao dao = new UserDao();
        boolean exito = dao.updateUser(user);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Datos guardados",
                    "Los datos del usuario se han actualizado correctamente.");

            if (onGuardarExitoso != null) {
                onGuardarExitoso.run();
            }

            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error al guardar",
                    "No se pudieron actualizar los datos del usuario en la base de datos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
