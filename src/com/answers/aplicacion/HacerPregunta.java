package com.answers.aplicacion;

import java.util.ArrayList;

import bdMySQL.BDConectar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HacerPregunta extends Activity{
	public int idCategoria;
	int idPregunta;
	String NombreUsuario;
	EditText txtPregunta;
	String NombreCategoria;
	ArrayList<String> listRespuestas = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_question);
		Bundle intent = getIntent().getExtras();
		NombreUsuario = intent.getString("usuario");
		NombreCategoria = intent.getString("nombreCategoria");
		idCategoria = intent.getInt("idCategoria");

		//Declaración de los componentes del View
		txtPregunta = (EditText) findViewById(R.id.editText1);
		Button btEnviar = (Button) findViewById(R.id.button1);
		btEnviar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0){
				setPregunta();
				Intent i = new Intent(HacerPregunta.this, ActivityPreguntas.class);
				i.putExtra("NombreUsuario", NombreUsuario);
				i.putExtra("idCategoria", idCategoria);
				i.putExtra("nombreCategoria", NombreCategoria);
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
	
	public void setPregunta(){

		String q = "INSERT INTO questions (pregunta, usuario, id_categoria) VALUES ('"+txtPregunta.getText().toString()+"', '"+NombreUsuario+"', "+idCategoria+" )";		
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
