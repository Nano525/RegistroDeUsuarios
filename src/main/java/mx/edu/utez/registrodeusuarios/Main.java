package mx.edu.utez.registrodeusuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mx/edu/utez/registrodeusuarios/user/Registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Inicio de sesion");
        newStage.setScene(scene);

        // Hacer la ventana de pantalla completa
        newStage.setMaximized(true);
        // Mostrar la nueva ventana
        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}