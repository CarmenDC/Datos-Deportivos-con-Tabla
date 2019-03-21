package com.acing.eventos;

import es.lanyu.commons.tiempo.DatableImpl;

public abstract class Suceso extends DatableImpl {
	protected static String getFormatoSuceso(){ return "%s-%s"; }
	protected Participante participante;
	protected String actor;

	public Participante getParticipante() {
		return participante;
	}
	
	protected void setParticipante(Participante participante) {
		this.participante = participante;
	}

}
