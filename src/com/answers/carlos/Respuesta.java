package com.answers.carlos;

public class Respuesta {
	String respuesta;
	String fecha;
	String usuario;
	
	public Respuesta(String _usuario, String _respuesta, String _fecha){
		respuesta = _respuesta;
		fecha = _fecha;
		usuario = _usuario;
	}
	
	public String getPregunta(){
		return this.respuesta;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
}
