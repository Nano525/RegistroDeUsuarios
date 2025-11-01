package mx.edu.utez.registrodeusuarios.modelo.dao;

import mx.edu.utez.registrodeusuarios.modelo.User;
import mx.edu.utez.registrodeusuarios.utils.OracleDatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    // Operación de Create
    public boolean createUser(User u) {
        String query = "INSERT INTO USUARIOS (NOMBRE,APELLIDOS,CORREO_ELECTRONICO,CONTRASEÑA,ESTADO) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = OracleDatabaseConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            System.out.println("=== INSERTANDO USUARIO ===");
            System.out.println("Nombre: " + u.getNombre());
            System.out.println("Apellidos: " + u.getApellidos());
            System.out.println("Correo: " + u.getCorreo());
            System.out.println("Contraseña: " + u.getContrasena());


            ps.setString(1, u.getNombre());      // NOMBRE
            ps.setString(2, u.getApellidos());   // APELLIDOS
            ps.setString(3, u.getCorreo());      // CORREO
            ps.setString(4, u.getContrasena());  // CONTRASEÑA
            ps.setString(5, "ACTIVO");

            int result = ps.executeUpdate();
            System.out.println("Filas afectadas: " + result);

            if (result > 0) {
                System.out.println("El registro del usuario se insertó correctamente");
                conn.close();
                return true;
            } else {
                System.out.println("No se insertó ningún registro");
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario:");
            e.printStackTrace();
        }
        return false;
    }

}
