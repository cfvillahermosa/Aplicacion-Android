package bdMySQL;

import java.sql.*;

public class BDConectar {

	static String host      = "db4free.net"; //"10.0.2.2"; //Poner 10.0.0.2 cuando el serv. MySQL es LocalHost
    static String baseDatos = "carlosanswers";
    static String usuario   = "cfvillahermosa2";
    static String password  = "2311carlos";
    static String cadCon	= "jdbc:mysql://"+host+":3306/"+baseDatos;

    public static Connection con;
    public static Statement st;

    /**
     * Crea la conexion con la BBD
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public static void crearConexion() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
       
    	Class.forName( "com.mysql.jdbc.Driver").newInstance();

    	try{        	
        	con = DriverManager.getConnection( cadCon, usuario, password);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Cierra la conexion con la BBDD
     */
    public static void cerrarConexion() {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }

}
