package mx.edu.utez.registrodeusuarios.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class OracleDatabaseConnectionManager {
    private static final String WALLET = "src/main/resources/mx/edu/utez/registrodeusuarios/Wallet_Estadias/Wallet_CoffeTrack";
    private static final String DB_NAME = "CoffeTrack_high";
    private static final String DB_URL = "jdbc:oracle:thin:@" + DB_NAME + "?TNS_ADMIN=" + WALLET;
    private static final String DB_USER = "ADMIN";
    private static final String DB_PASSWORD = "Pixel&logic5*";
    private static final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";

    // Singleton: solo una instancia del Pool
    private static PoolDataSource dataSource;

    static {
        try {
            dataSource = PoolDataSourceFactory.getPoolDataSource();
            dataSource.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
            dataSource.setURL(DB_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
            dataSource.setConnectionPoolName("JDBC_UCP_POOL");
            dataSource.setInitialPoolSize(5);
            dataSource.setMinPoolSize(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setTimeoutCheckInterval(5);
            dataSource.setInactiveConnectionTimeout(10);

            Properties connProps = new Properties();
            connProps.setProperty("fixedString", "false");
            connProps.setProperty("remarksReporting", "false");
            connProps.setProperty("restrictGetTables", "false");
            connProps.setProperty("includeSynonyms", "false");
            connProps.setProperty("defaultNChar", "false");
            connProps.setProperty("AccumulateBatchResult", "false");

            dataSource.setConnectionProperties(connProps);

        } catch (Exception e) {
            System.err.println("Error inicializando el pool de conexiones:");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("El pool de conexiones no fue inicializado.");
        }
        return dataSource.getConnection();
    }

    // Método para limpiar el pool de conexiones
    public static void clearConnectionPool() {
        try {
            if (dataSource != null) {
                // Para PoolDataSource, simplemente establecemos la referencia a null
                dataSource = null;
                System.out.println("Pool de conexiones limpiado.");
            }
        } catch (Exception e) {
            System.err.println("Error limpiando el pool de conexiones:");
            e.printStackTrace();
        }
    }

    // Método para reinicializar el pool de conexiones con nombre único
    public static void reinitializeConnectionPool() {
        clearConnectionPool();
        try {
            dataSource = PoolDataSourceFactory.getPoolDataSource();
            dataSource.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
            dataSource.setURL(DB_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
            
            // Usar un nombre único para el pool basado en timestamp
            String uniquePoolName = "JDBC_UCP_POOL_" + System.currentTimeMillis();
            dataSource.setConnectionPoolName(uniquePoolName);
            
            dataSource.setInitialPoolSize(5);
            dataSource.setMinPoolSize(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setTimeoutCheckInterval(5);
            dataSource.setInactiveConnectionTimeout(10);

            Properties connProps = new Properties();
            connProps.setProperty("fixedString", "false");
            connProps.setProperty("remarksReporting", "false");
            connProps.setProperty("restrictGetTables", "false");
            connProps.setProperty("includeSynonyms", "false");
            connProps.setProperty("defaultNChar", "false");
            connProps.setProperty("AccumulateBatchResult", "false");

            dataSource.setConnectionProperties(connProps);
            System.out.println("Pool de conexiones reinicializado con nombre: " + uniquePoolName);
        } catch (Exception e) {
            System.err.println("Error reinicializando el pool de conexiones:");
            e.printStackTrace();
        }
    }

    // Si quieres saber que la conexión esta hecha, puedes ejecutar este método
    // Usa el bóton de play a un lado de la declaración del método
    public static void main(String[] args){
        try (Connection conn = OracleDatabaseConnectionManager.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("La conexión es nula o está cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
