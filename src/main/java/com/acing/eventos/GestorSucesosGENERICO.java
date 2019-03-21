package com.acing.eventos;

import java.util.Collection;
import java.util.stream.Collectors;

public interface GestorSucesosGENERICO<T extends Suceso> extends Evento {
	
	@SuppressWarnings("unchecked")
	default <S extends T> Collection<S> getSucesosGestionados(Class<S> tipo){
		return getSucesos().stream()
		        .filter(s -> tipo.isAssignableFrom(s.getClass()))
		        .map(s -> (S)s)
		        .collect(Collectors.toList());
	}

	default int getSucesosParticipante(Participante equipo) {
		return (int) getSucesos().stream()
                        .filter(g -> g.getParticipante().equals(equipo))
                        .count();
	}
	
	default void addSuceso(T suceso) {
		getSucesos().add(suceso);
	}
	
	default void addSuceso(Class<? extends T> tipo, Participante participante) throws InstantiationException, IllegalAccessException {
		T suceso = tipo.newInstance();
		suceso.setFecha(null);
//		suceso.setActor("N/A");
		suceso.setParticipante(participante);
		addSuceso(suceso);
	}
	
	default void addSucesos(Class<? extends T> tipo, int numeroSucesos, Participante participante) {
		for(int i = 0; i < numeroSucesos; i++){
			try {
				addSuceso(tipo, participante);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
