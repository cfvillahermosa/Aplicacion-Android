package com.answers.carlos;

public class Pregunta {
	String pregunta;
	String fecha;
	String usuario;
	
	public Pregunta(String _usuario, String _pregunta, String _fecha){
		pregunta = _pregunta;
		fecha = _fecha;
		usuario = _usuario;
	}
	
	public String getPregunta(){
		return this.pregunta;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
}
