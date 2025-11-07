package mx.edu.utez.registrodeusuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mx/edu/utez/registrodeusuarios/user/DatosUsuario.fxml"));
            
            if (fxmlLoader.getLocation() == null) {
                return;
            }
            
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Datos de Usuarios");
            stage.setScene(scene);
            stage.setMinWidth(800);
            stage.setMinHeight(600);
            stage.setMaximized(true);
            stage.show();
            stage.toFront();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}