package com.acing.dao;

import java.util.Collection;
import java.util.Date;

import com.acing.eventos.Evento;
import com.acing.eventos.Partido;

public interface EventoDAO {
	Collection<? extends Evento> getEventos();
    Collection<? extends Evento> getEventos(Date fecha);
    <T extends Evento> int guardarEventos(T... eventos);
    <T extends Evento> T borrarEvento(T evento);
    <T extends Evento> boolean actualizarEvento(T evento);
}
