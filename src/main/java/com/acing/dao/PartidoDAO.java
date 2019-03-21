package com.acing.dao;

import java.util.Collection;
import java.util.Date;

import com.acing.eventos.Evento;
import com.acing.eventos.Partido;

public interface PartidoDAO extends EventoDAO {
	Collection<? extends Partido> getPartidos();
    Collection<? extends Partido> getPartidos(Date fecha);
}
