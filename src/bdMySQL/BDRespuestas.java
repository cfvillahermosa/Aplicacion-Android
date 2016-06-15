package bdMySQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




public class BDRespuestas extends BDConectar {

	// Devuelve un ArrayList con objetos Familia.
	/*public static List<Familia> getDatosFamilia(){
		
		List<Familia> f = new ArrayList<Familia>();
		String q = "SELECT id, nombre FROM familia ORDER BY orden";
		
        try {
            crearConexion();

            ResultSet rs = st.executeQuery( q );
            while (rs.next()) {
                f.add(new Familia( rs.getInt("id"),
                                   rs.getString("nombre") ) );    
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return f;
		
	}

	// Actualiza datos de la tabla familia en la BD.
	// Recibe como parámetro un objeto Familia.
	public static void updateFamilia( Familia f ){
		
		String q;
		
		if ( f.getId() == 0 ) {
			// Si el Id de la Familia está a cero es una nueva fila.
			q = "INSERT INTO familia ( id, nombre ) VALUES ( " +
				f.getId() + ", " +
				f.getNombre() + ")";			
		} else {
			// Si el id es distinto de cero es una actualización del registro.
			q = "UPDATE familia SET " +
				"nombre = " + f.getNombre() +
				"WHERE id = " + f.getId();			
		}
		
        try {
            crearConexion();

            st.executeUpdate( q );
            //Cuando es una nueva fila, averiguo el ultimo Id adsignado por la BD y lo pongo en el objeto Familia. 
            if ( f.getId() == 0 ){
                ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
                rs.first();
                f.setId( rs.getInt(1) );
                rs.close();            	
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return;
	}

	// Borra datos de la tabla familia en la BD.
	// Recibe como parámetro un objeto Familia.
	public static void deleteFamilia( Familia f ){
		
		String q;
		q = "DELETE FROM familia " +
			"WHERE id = " + f.getId();			
		
        try {
            crearConexion();
            st.executeUpdate( q );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return;
	}*/

}
