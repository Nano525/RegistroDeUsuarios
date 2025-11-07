package mx.edu.utez.registrodeusuarios.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mx.edu.utez.registrodeusuarios.modelo.User;
import mx.edu.utez.registrodeusuarios.modelo.Usuario;
import mx.edu.utez.registrodeusuarios.modelo.dao.UserDao;
import mx.edu.utez.registrodeusuarios.utils.WindowUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DatosUsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tableViewUsuarios;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colCorreo;

    @FXML
    private TableColumn<Usuario, String> colEstado;

    @FXML
    private TableColumn<Usuario, Void> colAcciones;

    @FXML
    private Button btnRegistro;

    private ObservableList<Usuario> usuarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColumnas();
        configurarColumnaAcciones();
        cargarDatos();
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(usuario.getNombreCompleto());
        });
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(column -> new TableCell<Usuario, Void>() {
            private final Button btnVer = new Button("👁");
            private final Button btnEditar = new Button("✏️");
            private final Button btnToggle = new Button("🔄");
            private final HBox hbox = new HBox(5, btnVer, btnEditar, btnToggle);

            {
                configurarEstilosBotones();
                configurarEventosBotones();
            }

            private void configurarEstilosBotones() {
                btnVer.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                               "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                btnEditar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                                  "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                btnToggle.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; " +
                                  "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                hbox.setStyle("-fx-alignment: center;");
            }

            private void configurarEventosBotones() {
                btnVer.setOnAction(event -> {
                    Usuario usuario = getTableRow().getItem();
                    verUsuario(usuario);
                });

                btnEditar.setOnAction(event -> {
                    Usuario usuario = getTableRow().getItem();
                    editarUsuario(usuario);
                });

                btnToggle.setOnAction(event -> {
                    Usuario usuario = getTableRow().getItem();
                    toggleEstado(usuario);
                });

                configurarEfectosHover();
            }

            private void configurarEfectosHover() {
                btnVer.setOnMouseEntered(event -> 
                    btnVer.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; " +
                                   "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));
                btnVer.setOnMouseExited(event -> 
                    btnVer.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                                   "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));

                btnEditar.setOnMouseEntered(event -> 
                    btnEditar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; " +
                                      "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));
                btnEditar.setOnMouseExited(event -> 
                    btnEditar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                                      "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));

                btnToggle.setOnMouseEntered(event -> 
                    btnToggle.setStyle("-fx-background-color: #F57C00; -fx-text-fill: white; " +
                                      "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));
                btnToggle.setOnMouseExited(event -> 
                    btnToggle.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; " +
                                      "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;"));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });
    }

    private void cargarDatos() {
        usuarios = FXCollections.observableArrayList();
        
        try {
            UserDao dao = new UserDao();
            List<User> users = dao.getAllUsers();
            
            for (User user : users) {
                String estado = dao.getEstadoByCorreo(user.getCorreo());
                if (estado == null) {
                    estado = "ACTIVO";
                }
                Usuario usuario = new Usuario(
                    user.getNombre(),
                    user.getApellidos(),
                    user.getCorreo(),
                    estado
                );
                usuarios.add(usuario);
            }
            
            tableViewUsuarios.setItems(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar usuarios");
            alert.setContentText("No se pudieron cargar los usuarios desde la base de datos.\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    private void verUsuario(Usuario usuario) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ver Usuario");
        alert.setHeaderText("Información del Usuario");
        alert.setContentText("Nombre: " + usuario.getNombre() + "\n" +
                           "Apellidos: " + usuario.getApellidos() + "\n" +
                           "Correo: " + usuario.getCorreo() + "\n" +
                           "Estado: " + usuario.getEstado());
        alert.showAndWait();
    }

    private void editarUsuario(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/utez/registrodeusuarios/user/EdiUser.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Editar Usuario");
            stage.setScene(scene);
            stage.setMaximized(true);
            
            EdiUserController controller = loader.getController();
            controller.cargarUsuario(usuario.getCorreo());
            controller.setOnGuardarExitoso(() -> {
                cargarDatos();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al abrir ventana de edición");
            alert.setContentText("No se pudo abrir la ventana de edición.\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    private void toggleEstado(Usuario usuario) {
        String nuevoEstado = "ACTIVO".equalsIgnoreCase(usuario.getEstado()) ? "INACTIVO" : "ACTIVO";
        
        UserDao dao = new UserDao();
        boolean exito = dao.updateEstado(usuario.getCorreo(), nuevoEstado);
        
        if (exito) {
            usuario.setEstado(nuevoEstado);
            tableViewUsuarios.refresh();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Estado Cambiado");
            alert.setHeaderText("Estado actualizado");
            alert.setContentText("El estado de " + usuario.getNombreCompleto() + " ha cambiado a: " + nuevoEstado);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al actualizar estado");
            alert.setContentText("No se pudo actualizar el estado del usuario en la base de datos.");
            alert.showAndWait();
        }
    }

    @FXML
    private void navegarARegistro() {
        WindowUtils.openNewWindow(btnRegistro, "/mx/edu/utez/registrodeusuarios/user/Registration.fxml", "Registro de Usuario");
    }
}