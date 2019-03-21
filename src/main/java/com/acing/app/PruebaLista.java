package com.acing.app;
import java.awt.Component;
import java.util.Collection;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.acing.eventos.Partido;

public class PruebaLista extends JTable {

    public PruebaLista(Collection<? extends Partido> lista) {
        super();
        System.out.println(lista.size());
        
        Vector<String> columnas = new Vector<>();
        columnas.add("local");
        columnas.add("visitante");
        columnas.add("resultado");
        columnas.add("fecha");
        
        Vector<Object> item;
        Vector<Vector<Object>> datos = new Vector<>();
        lista.stream().forEach(p -> { Vector<Object> vector = new Vector();
                vector.add(p);//.getLocal());
                vector.add(p.getVisitante());
                vector.add(p.getResultado());
                vector.add(p.getFecha());
                
                datos.add(vector);
            });
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        setModel(model);
        setRowSelectionAllowed(false);
        
        //PERSONALIZADO DEL RENDER
//        TableCellRenderer uRender = new CustomRenderer<List<PuestoUtil>>(a -> a.get(0).unidad.nombreUnidad);
//        int columnaUCO = model.findColumn("Nombre");
//        getColumnModel().getColumn(columnaUCO).setCellRenderer(uRender);
        TableCellRenderer localRender = new TableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                JLabel componente = new JLabel(((Partido)value).getLocal().toString());
                
                return componente;
            }
        };
        
        //AJUSTANDO ANCHO DE LAS COLUMNAS
        int columnaLocal = model.findColumn("local");
        getColumnModel().getColumn(columnaLocal).setCellRenderer(localRender);
        getColumnModel().getColumn(columnaLocal).setPreferredWidth(200);
        getColumnModel().getColumn(model.findColumn("visitante")).setPreferredWidth(200);
        getColumnModel().getColumn(model.findColumn("resultado")).setPreferredWidth(20);
        getColumnModel().getColumn(model.findColumn("fecha")).setPreferredWidth(100);
        
        
        
//        addMouseListener(e -> );
        
//        Action ver = new AccionBoton(this, columnaCIU){
//            @SuppressWarnings("unchecked")
//            @Override
//            public void realizarCambio(Object[] args){
//                Vector<Object> vector = (Vector<Object>)args[0];
//                List<PuestoUtil> puestos = puestosPorCIU.get(vector.get(getColumnaDatoIndex()));
//                mostrarPuestosEnUnidad(puestos);
//                actualizarActivados(getIndex(), vector);
//            }
//        };
//        
//        @SuppressWarnings("unused")
//        ButtonColumn btVer = new ButtonColumn(this, ver, columnaCIU);
    }
    
}
