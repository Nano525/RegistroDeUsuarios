package mx.edu.utez.registrodeusuarios.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import mx.edu.utez.registrodeusuarios.modelo.Usuario;

import java.net.URL;
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

    private ObservableList<Usuario> usuarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColumnas();
        configurarColumnaAcciones();
        cargarDatos();
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
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
                // Configurar estilos de los botones
                configurarEstilosBotones();
                
                // Configurar eventos de los botones
                configurarEventosBotones();
            }

            private void configurarEstilosBotones() {
                // Botón Ver
                btnVer.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                               "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                
                // Botón Editar
                btnEditar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                                  "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                
                // Botón Toggle
                btnToggle.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; " +
                                  "-fx-padding: 5px 10px; -fx-border-radius: 3px; -fx-cursor: hand;");
                
                // Estilo del contenedor
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

                // Efectos hover
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
        
        // Agregar datos de ejemplo
        usuarios.add(new Usuario("Ramiro López Delgado", "RamiroLopez@gmail.com", "Inactivo"));
        usuarios.add(new Usuario("Anna Rosalez Benitez", "AnnaRosalez@gmail.com", "Activo"));
        usuarios.add(new Usuario("Carlos Mendoza Ruiz", "CarlosMendoza@gmail.com", "Inactivo"));
        usuarios.add(new Usuario("María González Silva", "MariaGonzalez@gmail.com", "Activo"));
        usuarios.add(new Usuario("José Pérez Torres", "JosePerez@gmail.com", "Inactivo"));
        usuarios.add(new Usuario("Laura Sánchez Díaz", "LauraSanchez@gmail.com", "Activo"));
        usuarios.add(new Usuario("Roberto Vega López", "RobertoVega@gmail.com", "Inactivo"));
        usuarios.add(new Usuario("Ana Martínez Cruz", "AnaMartinez@gmail.com", "Activo"));
        usuarios.add(new Usuario("Diego Herrera Ramos", "DiegoHerrera@gmail.com", "Inactivo"));
        
        tableViewUsuarios.setItems(usuarios);
    }

    private void verUsuario(Usuario usuario) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ver Usuario");
        alert.setHeaderText("Información del Usuario");
        alert.setContentText("Nombre: " + usuario.getNombre() + "\n" +
                           "Correo: " + usuario.getCorreo() + "\n" +
                           "Estado: " + usuario.getEstado());
        alert.showAndWait();
    }

    private void editarUsuario(Usuario usuario) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editar Usuario");
        alert.setHeaderText("Funcionalidad de Edición");
        alert.setContentText("Editando usuario: " + usuario.getNombre() + "\n" +
                           "Esta funcionalidad se implementará próximamente.");
        alert.showAndWait();
    }

    private void toggleEstado(Usuario usuario) {
        usuario.toggleEstado();
        // Refrescar la tabla para mostrar el cambio
        tableViewUsuarios.refresh();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estado Cambiado");
        alert.setHeaderText("Estado actualizado");
        alert.setContentText("El estado de " + usuario.getNombre() + " ha cambiado a: " + usuario.getEstado());
        alert.showAndWait();
    }
}