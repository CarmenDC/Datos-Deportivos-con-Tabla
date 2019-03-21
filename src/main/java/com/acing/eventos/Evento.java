package com.acing.eventos;

import java.util.Collection;
import java.util.Date;

import es.lanyu.commons.tiempo.Datable;

public interface Evento extends Datable{
    
//    Date getFecha();
    
    Collection<Suceso> getSucesos();

    String getResultado();

}