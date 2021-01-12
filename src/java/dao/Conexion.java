package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

//    public static Connection cnx = null;
//
//    public static Connection conectar() throws Exception {
//        try {
//            Class.forName("oracle.jdbc.pool.OracleDataSource");
//            cnx = DriverManager.getConnection("jdbc:oracle:thin:@isoptec_medium?TNS_ADMIN=./Wallet_Isoptec", "ADMIN", "Vallegrande2020");
////            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////            cnx = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName= KALIDA", "sa", "sa");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Error: " + e);
//        }
//        return cnx;
//    }
//
//    public static void cerrarCnx() throws Exception {
//        if (Conexion.cnx != null) {
//            cnx.close();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        conectar();
//        if (cnx != null) {
//            System.out.println("Conectado");
//        } else {
//            System.out.println("Sin Conexión");
//        }
//    }
//
//    public static Connection getCnx() {
//        return cnx;
//    }
//
//    public static void setCnx(Connection aCnx) {
//        cnx = aCnx;
//    }

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {

//        String user="";
//        String pwd = "";
//        String url = "";
//        String drive = "";
        InputStream inputStream = Conexion.class.getClassLoader().getResourceAsStream("properties/db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        try {
            Class.forName(driver);
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage() + e.getStackTrace());
        }
        return cnx;
    }
    
    public void cerrarCnx() throws Exception{
        if(Conexion.cnx != null)
            cnx.close();            
    }
    
    public static void main(String[] args) throws SQLException, Exception {
        Conexion.conectar();
        if(Conexion.cnx != null)
            System.out.println("Conectado"); 
    }
}
