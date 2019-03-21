package com.acing.eventos;

import java.util.ArrayList;
import java.util.Collection;

import es.lanyu.commons.tiempo.DatableImpl;

public abstract class EventoImpl extends DatableImpl implements Evento {
    protected Collection<Suceso> sucesos = new ArrayList<>();
    
    @Override
    public Collection<Suceso> getSucesos() {
        return sucesos;
    }
    
}