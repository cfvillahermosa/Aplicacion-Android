package com.answers.aplicacion;





import com.answers.carlos.Categorias;

import bdMySQL.BDUsuarios;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//Variables de Vista
	Button acceder;
	TextView nombreUsuario;
	ProgressBar pr;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Variables de vista
		pr=(ProgressBar) findViewById(R.id.progressBar1);
		Button acceder = (Button)findViewById(R.id.loginButton);
		final TextView nombreUsuario = (TextView) findViewById(R.id.nombreUsuario);
		acceder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0){
				if(!nombreUsuario.getText().toString().equals("")){
				pr.setVisibility(View.VISIBLE);
				BDUsuarios user= new BDUsuarios();
				user.updateUsuario(nombreUsuario.getText().toString());
				Intent i = new Intent(MainActivity.this, Categorias.class);
				i.putExtra("usuario", nombreUsuario.getText().toString());
				startActivity(i);
				}else{
					Toast t = Toast.makeText(getApplicationContext(), "Debe introducir un nombre para continuar", Toast.LENGTH_LONG);
					t.show();
				}
			}
		});

	}
	
	@Override
	public void onResume(){
		super.onResume();
		pr.setVisibility(View.GONE);
	}

}
