package com.acing.app;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Collection;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.acing.eventos.Partido;
import com.acing.serial.SerializadorCSV;
import com.esotericsoftware.tablelayout.Value;
import com.esotericsoftware.tablelayout.swing.Table;

public class PartidoFrame extends JFrame {
    JLabel lbl_partido;

    public static void main(String[] args) {
        PartidoFrame pruebaFrame = new PartidoFrame();
        pruebaFrame.setVisible(true);
    }
    
    public PartidoFrame() {
        initialize();
    }
    
    private void initialize() {
        setName("Listado Partidos");
        setTitle("Listado Partidos");
        setLocale(new Locale("es", "ES"));
        setMinimumSize(new Dimension(500, 600));
        //Establece el tamaño
        setBounds(100, 100, 1100, 400);
        
        //Para que termine la ejecucion al cerrar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //Componentes
        lbl_partido = new JLabel("Partido");
        lbl_partido.setFont(new Font("Times New Roman", Font.BOLD, 40));
        
        
        //Layout (configuracion general)
        Table tabla = new Table();
        Value padding = new Value.FixedValue(5);
        tabla.top().left().pad(padding);
        tabla.defaults().pad(padding).left();
        
        getContentPane().add(tabla);
        
        //Agregar los componentes
        tabla.addCell(lbl_partido).center().expand();

        Collection<? extends Partido> partidos = new SerializadorCSV("datos/SP1.csv").getEventos();
        
        JScrollPane scPanel = new JScrollPane();
        PruebaLista pruebaLista = new PruebaLista(partidos);
//        pruebaLista.setRowSelectionAllowed(true);
        pruebaLista.getSelectionModel().addListSelectionListener(e -> 
//            System.out.println(pruebaLista.getValueAt(pruebaLista.getSelectedRow(), 0).toString())
            mostrarPartido((Partido) pruebaLista.getValueAt(pruebaLista.getSelectedRow(), 0))
            );
        scPanel.setViewportView(pruebaLista);
        
        tabla.row();
        tabla.addCell(scPanel).expand().fill();
        
//        mostrarPartido((Partido) partidos.toArray()[0]);

    }

    private void mostrarPartido(Partido partido) {
        lbl_partido.setText(partido.toString());
    }
}
