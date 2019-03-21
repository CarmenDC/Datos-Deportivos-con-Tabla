package com.acing.eventos;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido extends EventoImpl implements GestorSucesos, EventoConGoles {
	private final static SimpleDateFormat sdfToString= new SimpleDateFormat("dd/MM/yy HH:mm");
	private Participante local;
	private Participante visitante;

	public String getResultado() {
	    return EventoConGoles.super.getResultado();
	}

	public Partido() {}
	
	public Partido(Participante local, Participante visitante, Date fecha) {
		super();
		this.local = local;
		this.visitante = visitante;
		setFecha(fecha);
	}

	@Override
	public String toString() {
		return "(" + sdfToString.format(getFecha()) + ") " + local + " vs " + visitante + " => " + getResultado();
	}

    @Override
    public Participante getLocal() {
        return local;
    }

    @Override
    public Participante getVisitante() {
        return visitante;
    }
	
	
}
