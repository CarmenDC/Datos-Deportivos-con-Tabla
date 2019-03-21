package com.acing.eventos;
import java.util.HashMap;
import java.util.Map;

import es.lanyu.commons.identificable.AbstractNombrable;

public class Participante extends AbstractNombrable {// implements Identificable<String>{
	public static Map<String, Participante> mapaParticipantes = new HashMap<>();
//	private String nombre;
//	private String id;
	
//	@Override
//	public String getIdentificador() {
//		return id;
//	}
	
	public Participante() {
	}
	
	public Participante(String nombre) {
//		this.nombre = nombre;
	    setNombre(nombre);
	    setIdentificador(nombre);
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
