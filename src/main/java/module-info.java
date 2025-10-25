module mx.edu.utez.registrodeusuarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.edu.utez.registrodeusuarios to javafx.fxml;
    exports mx.edu.utez.registrodeusuarios;
}