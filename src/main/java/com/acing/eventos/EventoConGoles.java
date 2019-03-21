package com.acing.eventos;

import java.util.Collection;

public interface EventoConGoles extends GestorSucesos, LocalContraVisitante{
	
	static String getFormatoGoles() {
		return Suceso.getFormatoSuceso();
	}
	
	default Collection<Gol> getGoles(){
		return getSucesosGestionados(Gol.class);
	}

	default int getGolesParticipante(Participante equipo) {
		return (int) getGoles().stream().filter(g -> g.getEquipoAnotador().equals(equipo)).count();
	}
	
	default void addGoles(int numGolesLocal, int numGolesVisitante) {
		addSucesos(Gol.class, numGolesLocal, getLocal());
		addSucesos(Gol.class, numGolesVisitante, getVisitante());
	}
	
	default String getResultado() {
		return String.format(getFormatoGoles(), getGolesParticipante(getLocal()), getGolesParticipante(getVisitante()));
	}

}