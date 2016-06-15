package com.answers.aplicacion;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.answers.carlos.Categorias;

import bdMySQL.BDConectar;
import bdMySQL.BDUsuarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityPreguntas extends Activity {
	public int idCategoria;
	public String NombreUsuario; 
	public String NombreCategoria;
	ArrayList<String> listPreguntas = new ArrayList<String>();
	ArrayList<Integer> listIdsPreguntas = new ArrayList<Integer>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_questions);
		Bundle intent = getIntent().getExtras();
		NombreUsuario = intent.getString("usuario");
		NombreCategoria = intent.getString("nombreCategoria");
		idCategoria = intent.getInt("idCategoria");
		getPreguntas();

		//Declaración de los componentes del View
		ListView list = (ListView) findViewById(R.id.questionsList);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.item_preguntas, listPreguntas);
		list.setAdapter(adaptador);
		TextView txtCategoria = (TextView) findViewById(R.id.textCategoria);
		Button btHacerPregunta = (Button) findViewById(R.id.button1);
		txtCategoria.setText(NombreCategoria);
		list.setOnItemClickListener(new OnItemClickListener() {

		    @Override
		    public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
		    	Intent i = new Intent(ActivityPreguntas.this, ActivityRespuestas.class);
				i.putExtra("usuario", NombreUsuario);
				i.putExtra("nombrePregunta", listPreguntas.get(position));
				i.putExtra("idCategoria", idCategoria);
				i.putExtra("idPregunta", listIdsPreguntas.get(position));
				startActivity(i);
		    }
		});
		
		
		btHacerPregunta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0){
				
				Intent i = new Intent(ActivityPreguntas.this, HacerPregunta.class);
				i.putExtra("usuario", NombreUsuario);
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
	
	
	public void getPreguntas(){

		String q;
		ResultSet rs = null;
		q = "SELECT pregunta, id_pregunta FROM questions WHERE id_categoria="+idCategoria+"";		
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
			listPreguntas.add(rs.getString("pregunta"));
			listIdsPreguntas.add(rs.getInt("id_pregunta"));
		}
		rs.close();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		} finally {
			baseDeDatos.cerrarConexion();
		}
}
	
}
