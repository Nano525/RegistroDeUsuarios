package mx.edu.utez.registrodeusuarios.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowUtils {
    public static void switchWindow(Node referenceNode, String fxmlPath, String title) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WindowUtils.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) referenceNode.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.setMaximized(true);
            newStage.show();
            currentStage.close();
        }catch(IOException e){
            System.err.println("Error al cargar FXML: "+e.getMessage());
            e.printStackTrace();
        }catch(Exception e){
            System.err.println("Error inesperado: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
