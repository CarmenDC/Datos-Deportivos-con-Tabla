package com.acing.serial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.acing.dao.EventoDAO;
import com.acing.dao.PartidoDAO;
import com.acing.eventos.Evento;
import com.acing.eventos.Partido;
import com.esotericsoftware.jsonbeans.Json;

import es.lanyu.commons.tiempo.Datable;

public class SerializadorJson implements PartidoDAO {//EventoDAO {
	private final String ruta;
	Json json;
	
	public SerializadorJson(String ruta) {
		this.ruta = ruta;
		json = new Json();
	}
	
	@Override
	public Collection<? extends Partido> getEventos() {
		return getEventosFromJson(ruta);
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
	    return guardarColeccionAJsonPorLineas(ruta, (Partido[]) eventos);
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
	
	
	public static int guardarColeccionAJsonPorLineas(String ruta, Partido... eventos) {
	    int numeroEventosGuardados = 0;
		Json json = new Json();
//		json.setSerializer(Date.class, new SerializadorDate());
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(ruta), "UTF-8"))) {
			for (Datable evento : eventos) {
				writer.write(json.toJson(evento));
				numeroEventosGuardados++;
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return numeroEventosGuardados;
	}
	
	private static Collection<Partido> getEventosFromJson(String rutaArchivo) {
		Collection<Partido> eventosLeidos = new ArrayList<>();
		Json json = new Json();
//		json.setSerializer(Date.class, new SerializadorDate());
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(rutaArchivo),
						"UTF-8"))){
			
			String linea;
			while((linea = reader.readLine()) != null) {
				eventosLeidos.add(json.fromJson(Partido.class, linea));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventosLeidos;
	}
	
	private static void guardarStringEnFichero(String cadena, String ruta) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(ruta), "UTF-8"))) {
			writer.write(cadena);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public Collection<? extends Partido> getPartidos(Date fecha) {
        // TODO Auto-generated method stub
        return null;
    }

}
