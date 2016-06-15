package com.answers.aplicacion;

import java.sql.ResultSet;
import java.util.ArrayList;

import bdMySQL.BDConectar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ResponderPregunta extends Activity{
	public int idCategoria;
	int idPregunta;
	String NombreUsuario;
	EditText txtRespuesta;
	String NombrePregunta;
	ArrayList<String> listRespuestas = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.responder_question);
		Bundle intent = getIntent().getExtras();
		NombreUsuario = intent.getString("usuario");
		NombrePregunta = intent.getString("nombrePregunta");
		idPregunta = intent.getInt("idPregunta");
		idCategoria = intent.getInt("idCategoria");

		//Declaración de los componentes del View
		TextView txtPregunta = (TextView) findViewById(R.id.textpregunta);
		txtPregunta.setText(NombrePregunta);
		txtRespuesta = (EditText) findViewById(R.id.editText1);
		Button btEnviar = (Button) findViewById(R.id.button1);
		btEnviar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0){
				setRespuesta();
				Intent i = new Intent(ResponderPregunta.this, ActivityRespuestas.class);
				i.putExtra("usuario", NombreUsuario);
				i.putExtra("nombrePregunta", NombrePregunta);
				i.putExtra("idCategoria", idCategoria);
				i.putExtra("idPregunta", idPregunta);
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
	/*Intent i = new Intent(ActivityRespuestas.this, ResponderPregunta.class);
				i.putExtra("usuario", NombreUsuario);
				startActivity(i);*/
	
	public void setRespuesta(){

		String q;

		q = "INSERT INTO answers (usuario, respuesta, id_pregunta) VALUES ('"+NombreUsuario+"', '"+txtRespuesta.getText().toString()+"', "+idPregunta+" )";		
		BDConectar baseDeDatos= new BDConectar();
		try {
			baseDeDatos.crearConexion();

			baseDeDatos.st.executeUpdate( q );
			//Cuando es una nueva fila, averiguo el ultimo Id adsignado por la BD y lo pongo en el objeto Familia. 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			baseDeDatos.cerrarConexion();
		}

}
}
