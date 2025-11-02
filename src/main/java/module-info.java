module mx.edu.utez.registrodeusuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ucp;
    requires javafx.graphics;

    opens mx.edu.utez.registrodeusuarios.utils to javafx.fxml;
    opens mx.edu.utez.registrodeusuarios to javafx.graphics, javafx.fxml;
    opens mx.edu.utez.registrodeusuarios.modelo to javafx.fxml;
   // opens mx.edu.utez.registrodeusuarios.controllers to javafx.fxml;
    
    exports mx.edu.utez.registrodeusuarios.utils;
    exports mx.edu.utez.registrodeusuarios.modelo;
   // exports mx.edu.utez.registrodeusuarios.controllers;
}