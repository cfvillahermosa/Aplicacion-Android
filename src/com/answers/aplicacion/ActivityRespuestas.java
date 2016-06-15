package com.answers.aplicacion;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.answers.carlos.Categorias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bdMySQL.BDConectar;
import bdMySQL.BDUsuarios;

public class ActivityRespuestas extends Activity {
	public int idCategoria;
	int idPregunta;
	String NombreUsuario;
	String NombrePregunta;
	ArrayList<String> listRespuestas = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_respuestas);
		Bundle intent = getIntent().getExtras();
		NombreUsuario = intent.getString("usuario");
		NombrePregunta = intent.getString("nombrePregunta");
		idPregunta = intent.getInt("idPregunta");
		idCategoria = intent.getInt("idCategoria");
		
		ArrayList<String> listPreguntas = new ArrayList<String>();
		getPreguntas();

		//Declaración de los componentes del View
		ListView list = (ListView) findViewById(R.id.questionsList);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.item_lista, listRespuestas);
		list.setAdapter(adaptador);
		TextView txtCategoria = (TextView) findViewById(R.id.textCabecera);
		txtCategoria.setText(NombrePregunta);
		Button btResponder = (Button) findViewById(R.id.button1);
		btResponder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0){
				
				Intent i = new Intent(ActivityRespuestas.this, ResponderPregunta.class);
				i.putExtra("usuario", NombreUsuario);
				i.putExtra("idPregunta", idPregunta);
				i.putExtra("nombrePregunta", NombrePregunta);
				startActivity(i);
				onDestroy();
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		finish();
	}
	
	public void getPreguntas(){


		String q;
		ResultSet rs = null;
		q = "SELECT respuesta FROM answers WHERE id_pregunta = "+idPregunta+" ORDER BY fecha";		
		BDConectar baseDeDatos= new BDConectar();
		try {
			baseDeDatos.crearConexion();

			rs = baseDeDatos.st.executeQuery( q );
			//Cuando es una nueva fila, averiguo el ultimo Id adsignado por la BD y lo pongo en el objeto Familia. 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try{
		while (rs.next()) {
			listRespuestas.add(rs.getString("respuesta"));    
		}
		rs.close();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		} finally {
			baseDeDatos.cerrarConexion();
		}

}
	
}

