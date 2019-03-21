package com.acing.serial;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.acing.eventos.Partido;
import com.acing.dao.EventoDAO;
import com.acing.dao.PartidoDAO;
import com.acing.eventos.Evento;
import com.acing.eventos.Gol;
import com.acing.eventos.Participante;

public class SerializadorCSV implements PartidoDAO{

	private final String ruta;
	
//	public void setRutaPorDefecto (String ruta) {
//		this.ruta = ruta;
//	}
	
	public SerializadorCSV(String ruta) {
		this.ruta = ruta;
	}
	
	public static Collection<Partido> getEventos(String rutaArchivo){
		Collection<Partido> eventosLeidos = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(rutaArchivo),
						"UTF-8"))){
			
			String linea = reader.readLine();// Leo la primera linea
			while((linea = reader.readLine()) != null) {
				eventosLeidos.add(deserializarEvento(linea));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventosLeidos;
	}
	
	private static Partido deserializarEvento(String linea) throws ParseException {
		String[] campos = linea.split(",");
		String fechaString = campos[1];
		String localString = campos[2];
		String visitanteString = campos[3];
		String golesLocalString = campos[4];
		String golesVisitanteString = campos[5];
		
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yy");
		
		Date fecha = sdformat.parse(fechaString);
		Participante local = new Participante(localString);
		Participante visitante = new Participante(visitanteString);
		int golesLocal = Integer.parseInt(golesLocalString);
		int golesVisitante = Integer.parseInt(golesVisitanteString);
//		String resultado = golesLocal + "-" + golesVisitante;
		
		// NO SE HACE ASI
//		Evento miEvento = new Evento();
//		miEvento.local = local;
//		miEvento.visitante = visitante;
//		miEvento.fecha = fecha;
//		miEvento.golesLocal = golesLocal;
//		miEvento.golesVisitante = golesVisitante;
		
		Partido miEvento = new Partido(local, visitante, fecha);
//		miEvento.setResultado(resultado);
		miEvento.addSucesos(Gol.class, golesLocal, local);
		miEvento.addSucesos(Gol.class, golesVisitante, visitante);
		
		return miEvento;
	}

	
	@Override
	public Collection<? extends Partido> getEventos() {
		return getEventos(ruta);
	}
	
    @Override
    public Collection<? extends Partido> getPartidos() {
        return getEventos();
    }

	@Override
	public Collection<? extends Partido> getEventos(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public <T extends Evento> int guardarEventos(T... eventos) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T extends Evento> T borrarEvento(T evento) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Evento> boolean actualizarEvento(T evento) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<? extends Partido> getPartidos(Date fecha) {
        // TODO Auto-generated method stub
        return null;
    }
	

}
