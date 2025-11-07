package mx.edu.utez.registrodeusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mx.edu.utez.registrodeusuarios.modelo.User;
import mx.edu.utez.registrodeusuarios.modelo.dao.UserDao;
import mx.edu.utez.registrodeusuarios.utils.WindowUtils;

public class RegistrationController {
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TextField txtContrasena1;

    @FXML
    public void onSaveUser(ActionEvent event){
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        String contrasena1 = txtContrasena1.getText();

        if (nombre == null || nombre.trim().isEmpty()) {
            showError("El nombre  es obligatoria");
            return;
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            showError("Los apellidos  es obligatorio");
            return;
        }
        if (correo == null || correo.trim().isEmpty()) {
            showError("El correo electrónico es obligatoria");
            return;
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            showError("La contraseña  es obligatorio");
            return;
        }
        if (contrasena1 == null || contrasena1.trim().isEmpty()) {
            showError("Confirmar la contraseña es obligatorio");
            return;
        }
        if (!contrasena.equals(contrasena1)) {
            showError("Las contraseñas no coinciden");
            return;
        }

        User u = new User(nombre,apellidos,correo,contrasena);
        UserDao dao = new UserDao();

        if (dao.createUser(u)==true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Exitoso");
            alert.setHeaderText("Registro se ha insertado correctamente");
            alert.setContentText("El Usuario se registrado correctamente");
            alert.showAndWait();

            txtNombre.clear();
            txtApellidos.clear();
            txtCorreo.clear();
            txtContrasena.clear();
            txtContrasena1.clear();
        }else  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro Fallido");
            alert.setHeaderText("Verifique todos los campos");
            alert.setContentText("Ingrese todos los datos correctamente");
            alert.showAndWait();
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Requerido");
        alert.setHeaderText("Campo obligatorio vacío");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void regresarADatosUsuario() {
        WindowUtils.switchWindow(btnRegresar, "/mx/edu/utez/registrodeusuarios/user/DatosUsuario.fxml", "Datos de Usuarios");
    }

}
