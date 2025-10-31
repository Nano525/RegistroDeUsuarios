module mx.edu.utez.registrodeusuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static ucp;

    opens mx.edu.utez.registrodeusuarios.utils to javafx.fxml;
    opens mx.edu.utez.registrodeusuarios.controllers to javafx.fxml;
    opens mx.edu.utez.registrodeusuarios.modelo to javafx.fxml;
    
    exports mx.edu.utez.registrodeusuarios.utils;
    exports mx.edu.utez.registrodeusuarios.controllers;
    exports mx.edu.utez.registrodeusuarios.modelo;
}