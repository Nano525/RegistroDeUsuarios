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
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void openNewWindow(Node referenceNode, String fxmlPath, String title) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WindowUtils.class.getResource(fxmlPath));
            
            if (fxmlLoader.getLocation() == null) {
                return;
            }
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.setMinWidth(800);
            newStage.setMinHeight(600);
            newStage.setMaximized(true);
            
            if (referenceNode != null && referenceNode.getScene() != null) {
                Stage parentStage = (Stage) referenceNode.getScene().getWindow();
                newStage.initOwner(parentStage);
            }
            
            newStage.show();
            newStage.toFront();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
