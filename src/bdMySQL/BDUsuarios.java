package bdMySQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BDUsuarios {
	BDConectar baseDeDatos = new BDConectar();
	// Devuelve un ArrayList con objetos Familia.
		public  List<String> getDatosFamilia(){
			List<String> f = new ArrayList<String>();
			String q = "SELECT nombre FROM usuarios ORDER BY orden";
			
	        try {
	            baseDeDatos.crearConexion();

				ResultSet rs = baseDeDatos.st.executeQuery( q );
	            while (rs.next()) {
	               f.add(rs.getString("nombre")); 
	            }
	            rs.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	 baseDeDatos.cerrarConexion();	        }
	        return f;
			
		}

		// Actualiza datos de la tabla familia en la BD.
		// Recibe como parámetro un objeto Familia.
		public  void updateUsuario( String f ){
			
			String q;
			
			q = "INSERT INTO usuarios ( nombre ) VALUES ( '" +
					f + "')";		
			
	        try {
	        	 baseDeDatos.crearConexion();

	        	 baseDeDatos.st.executeUpdate( q );
	            //Cuando es una nueva fila, averiguo el ultimo Id adsignado por la BD y lo pongo en el objeto Familia. 
	          
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	baseDeDatos.cerrarConexion();
	        }
	        return;
		}

}
