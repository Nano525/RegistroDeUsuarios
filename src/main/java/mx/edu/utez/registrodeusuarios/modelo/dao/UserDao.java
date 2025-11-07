package mx.edu.utez.registrodeusuarios.modelo.dao;

import mx.edu.utez.registrodeusuarios.modelo.User;
import mx.edu.utez.registrodeusuarios.utils.OracleDatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    
    public boolean createUser(User u) {
        String query = "INSERT INTO USUARIOS (NOMBRE,APELLIDOS,CORREO_ELECTRONICO,CONTRASEÑA,ESTADO) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.setString(5, "ACTIVO");

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> usuarios = new ArrayList<>();
        String query = "SELECT NOMBRE, APELLIDOS, CORREO_ELECTRONICO, CONTRASEÑA, ESTADO FROM USUARIOS ORDER BY NOMBRE";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellidos(rs.getString("APELLIDOS"));
                user.setCorreo(rs.getString("CORREO_ELECTRONICO"));
                user.setContrasena(rs.getString("CONTRASEÑA"));
                usuarios.add(user);
            }
            
            return usuarios;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return usuarios;
        }
    }
    
    public String getEstadoByCorreo(String correo) {
        String query = "SELECT ESTADO FROM USUARIOS WHERE CORREO_ELECTRONICO = ?";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ESTADO");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByCorreo(String correo) {
        String query = "SELECT NOMBRE, APELLIDOS, CORREO_ELECTRONICO, CONTRASEÑA, ESTADO FROM USUARIOS WHERE CORREO_ELECTRONICO = ?";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setNombre(rs.getString("NOMBRE"));
                    user.setApellidos(rs.getString("APELLIDOS"));
                    user.setCorreo(rs.getString("CORREO_ELECTRONICO"));
                    user.setContrasena(rs.getString("CONTRASEÑA"));
                    return user;
                } else {
                    return null;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(User user) {
        String query = "UPDATE USUARIOS SET NOMBRE = ?, APELLIDOS = ?, CONTRASEÑA = ? WHERE CORREO_ELECTRONICO = ?";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellidos());
            ps.setString(3, user.getContrasena());
            ps.setString(4, user.getCorreo());

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEstado(String correo, String estado) {
        String query = "UPDATE USUARIOS SET ESTADO = ? WHERE CORREO_ELECTRONICO = ?";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, estado);
            ps.setString(2, correo);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String correo) {
        String query = "DELETE FROM USUARIOS WHERE CORREO_ELECTRONICO = ?";
        
        try (Connection conn = OracleDatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
