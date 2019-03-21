package com.acing.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.acing.eventos.Evento;
import com.acing.eventos.Gol;
import com.acing.eventos.Partido;
import com.acing.serial.SerializadorCSV;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import es.lanyu.commons.config.Propiedades;

public class ConectorBD implements PartidoDAO {
    
    Database db;
    Map<String, String> idsPorNombre;
    
    public ConectorBD(String ruta) {
        //db = abrirBD(ruta);
        //System.out.println(db);
//        try {
//            db.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        ResultSet rs = ejecutarConsulta("SELECT * FROM Participantes");//pueden salir desordenadas
//        try {
//            int numeroColumnas = rs.getMetaData().getColumnCount();
//            while(rs.next()) {
//                String fila = rs.getObject(1).toString();
//                for(int i = 2; i <= numeroColumnas; i++) {
//                    fila += " | " + rs.getObject(i);
//                }
//                System.out.println(fila);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        
        idsPorNombre = new HashMap<>();
        Propiedades propiedades = new Propiedades("datos/mapaCSV.properties");
        propiedades.forEach((k, v) -> idsPorNombre.put((String)v, (String)k));
        
        Collection<? extends Partido> partidosCSV = new SerializadorCSV("datos/SP1.csv").getPartidos();
        
//        for(Partido p : partidosCSV) {
//            guardarPartido(p);
//            System.out.println(p);
//        }
        
//        try (Database db = DatabaseBuilder.open(new File(ruta))){
//            Table eventos = db.getTable("Eventos");
//            Table partidos = db.getTable("Partidos");
//            Table sucesos = db.getTable("Sucesos");
//            
//            for(Partido p : partidosCSV) {
//                Object[] retorno = eventos.addRow(Column.AUTO_NUMBER, p.getFecha());
//                System.out.println(Arrays.toString(retorno));
//                System.out.println(p);
//                Object idEvento = retorno[0];//Recojo el ID del evento
//                partidos.addRow(
//                        idEvento,
//                        idsPorNombre.get(p.getLocal().getNombre()),
//                        idsPorNombre.get(p.getVisitante().getNombre()));
//                for(Gol g : p.getSucesosGestionados(Gol.class)) {
//                    sucesos.addRow(
//                            Column.AUTO_NUMBER,
//                            idEvento,
//                            idsPorNombre.get(g.getParticipante().getNombre()),
//                            "gol");
//                }
//                
//            }
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
    }
    
    private boolean guardarPartido(Partido partido) {
        boolean guardado = false;
        try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://datos/DatosDeportivos.accdb")){
            PreparedStatement psEventos = conn.prepareStatement(
                    "INSERT INTO Eventos ([fecha]) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);//Necesito leer ids
            PreparedStatement psPartidos = conn.prepareStatement(
                    "INSERT INTO Partidos (idEvento, idLocal, idVisitante) VALUES (?, ?, ?)");
            PreparedStatement psSucesos = conn.prepareStatement(
                    "INSERT INTO Sucesos (idEvento, idParticipante, tipo) VALUES (?, ?, ?)");
            psSucesos.setString(3, "gol");
            
            //Cargo Evento
            //psEventos.setInt(1, 0);//Valor falso
            psEventos.setDate(1, new java.sql.Date(partido.getFecha().getTime()));//Pasar a sql.Date
            int filasAfectadas = psEventos.executeUpdate();
//            ResultSet rsEvento = psEventos.executeQuery("SELECT @@IDENTITY");
            ResultSet rsEvento = psEventos.getGeneratedKeys();
            System.out.println(filasAfectadas + " | " + rsEvento.next());//rsEvento.next();//No estoy asegurando nada, habría que usar asegurar o lanzar excepcion
            int idEvento = rsEvento.getInt(0);//Leer id generado
            
            //Cargo Partido
            psPartidos.setInt(1, idEvento);
            psPartidos.setString(2, idsPorNombre.get(partido.getLocal().getNombre()));
            psPartidos.setString(3, idsPorNombre.get(partido.getVisitante().getNombre()));
            psPartidos.executeQuery();
            
            //Cargo goles
            for(Gol g : partido.getSucesosGestionados(Gol.class)) {
                psSucesos.setInt(1, idEvento);
                psSucesos.setString(2, idsPorNombre.get(g.getParticipante().getNombre()));
                psSucesos.setString(3, "gol");
            }
            
            guardado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guardado;
    }
    
    private ResultSet ejecutarConsulta(String query) {
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://datos/DatosDeportivos.accdb")){
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }
    
    private Database abrirBD(String ruta) {
        Database dbase;
        try (Database db = DatabaseBuilder.open(new File(ruta))) {
            dbase = db;
            System.out.println(db);
            return db;
        } catch (IOException e) {
            e.printStackTrace();
            dbase = null;
        }
        return dbase;
    }

    @Override
    public Collection<? extends Evento> getEventos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<? extends Evento> getEventos(Date fecha) {
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
    public Collection<? extends Partido> getPartidos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<? extends Partido> getPartidos(Date fecha) {
        // TODO Auto-generated method stub
        return null;
    }

}
