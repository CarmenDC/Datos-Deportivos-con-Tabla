package com.acing.eventos;

import java.util.Collection;
import java.util.stream.Collectors;

public interface GestorSucesos extends Evento {
	
	@SuppressWarnings("unchecked")
	default <T extends Suceso> Collection<T> getSucesosGestionados(Class<T> tipo){
		return getSucesos().stream()
		        .filter(s -> tipo.isAssignableFrom(s.getClass()))
		        .map(s -> (T)s)
		        .collect(Collectors.toList());
	}

	default int getSucesosParticipante(Participante equipo) {
		return (int) getSucesos().stream()
                        .filter(g -> g.getParticipante().equals(equipo))
                        .count();
	}
	
	default void addSuceso(Suceso suceso) {
		getSucesos().add(suceso);
	}
	
	default <T extends Suceso> void addSuceso(Class<T> tipo, Participante participante) throws InstantiationException, IllegalAccessException {
		T suceso = tipo.newInstance();
		suceso.setFecha(null);
//		suceso.setActor("N/A");
		suceso.setParticipante(participante);
		addSuceso(suceso);
	}
	
	default <T extends Suceso> void addSucesos(Class<T> tipo, int numeroSucesos, Participante participante) {
		for(int i = 0; i < numeroSucesos; i++){
			try {
				addSuceso(tipo, participante);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
