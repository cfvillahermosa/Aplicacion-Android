package com.answers.carlos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bdMySQL.BDConectar;
import bdMySQL.BDUsuarios;


import com.answers.aplicacion.ActivityPreguntas;
import com.answers.aplicacion.MainActivity;
import com.answers.aplicacion.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Categorias extends Activity{
	ArrayList<Integer> listIdsCategorias = new ArrayList<Integer>();
	ArrayList<String> listCategorias = new ArrayList<String>();
	String Nombre;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_category);
		Bundle intent = getIntent().getExtras();
		Nombre = intent.getString("usuario");	
		getCategorias();

		//Declaración de los componentes del View
		ListView list = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.item_lista, listCategorias);
		list.setAdapter(adaptador);
		list.setOnItemClickListener(new OnItemClickListener() {

		    @Override
		    public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
		    	Intent i = new Intent(Categorias.this, ActivityPreguntas.class);
				i.putExtra("usuario", Nombre);
				i.putExtra("nombreCategoria", listCategorias.get(position));
				i.putExtra("idCategoria", listIdsCategorias.get(position));
				startActivity(i);
		    }
		});
		
		
	}

	public void getCategorias(){

		String q;
		ResultSet rs = null;
		q = "SELECT id_categoria, nombre FROM categoria";		
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
			listCategorias.add(rs.getString("nombre")); 
			listIdsCategorias.add(rs.getInt("id_categoria"));
		}
		rs.close();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		} finally {
			baseDeDatos.cerrarConexion();
		}

}

}
