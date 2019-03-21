package com.acing.app;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.acing.eventos.Partido;
import com.acing.dao.ConectorBD;
import com.acing.dao.EventoDAO;
import com.acing.dao.PartidoDAO;
import com.acing.eventos.Evento;
import com.acing.serial.SerializadorCSV;
import com.acing.serial.SerializadorJson;
import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.OutputType;

public class App {

	public static void main(String[] args) {
		System.out.println("FUNCIONA!!!");
		
		Collection<? extends Partido> eventos;
		Json json = new Json(OutputType.json);
		String rutaCsv = "datos/SP1.csv";
		String rutaJson = "datos/eventos.json";
		PartidoDAO eventoDAO;
		
		ConectorBD conector = new ConectorBD("datos/DatosDeportivos.accdb");
		
//		Participante participanteLeyendose = null;
//		Map<String, Participante> mapaParticipantes = new HashMap<>();
//		mapaParticipantes.containsKey(participanteLeyendose.getIdentificador());
//		mapaParticipantes.containsValue(participanteLeyendose); // Usa equals
		
		// LEER DESDE EL CSV
//		eventos = SerializadorCSV.getEventos(rutaCsv);
		eventoDAO = new SerializadorCSV(rutaCsv);
		eventos = eventoDAO.getPartidos();//.getEventos();
		
		// CONVERTIR eventos a JSON
		eventoDAO = new SerializadorJson(rutaJson);
//		String eventosJson = json.prettyPrint(eventos);
//		System.out.println(eventosJson);
//		SerializadorJson.guardarColeccionAJsonPorLineas(rutaJson, eventos.toArray(new Evento[0]));
		eventoDAO.guardarEventos(eventos.toArray(new Partido[0]));
//		EventoDAO eventoDAOaux = eventoDAO;
//		eventos.stream().forEach(e -> eventoDAOaux.guardarEvento(e));
		
		// Guardar un String a disco
//		guardarStringEnFichero(eventosJson, rutaJson);
		
//		eventos = json.fromJson(ArrayList.class, rutaJson);
		
		EventoDAO eventoDAO2 = eventoDAO;   // No es recomendable. No lo hagais NUNCA!
		eventos = eventoDAO.getPartidos();
		
//		eventos.stream()
////				.filter(e1 -> e1.getResultado().equals("0-0"))
////				.map(e2 -> {String texto = e2.getFecha().toString();
////							texto += " " + e2.getResultado();
////							return texto;
////							})
//				.forEach(System.out::println);//d -> System.out.println(d));
		
		JFrame frame = new PruebaFrame();
//		JPanel panel = new JPanel();
//		JScrollPane scPanel = new JScrollPane();
//		scPanel.setViewportView(new PruebaLista(eventos));
//	    panel.add(scPanel);
//		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
//		JOptionPane.showConfirmDialog(panel, "titulo");
	}

}
