package com.acing.app;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.acing.serial.SerializadorCSV;
import com.esotericsoftware.tablelayout.BaseTableLayout.Debug;
import com.esotericsoftware.tablelayout.Value;
import com.esotericsoftware.tablelayout.swing.Table;

public class PruebaFrame extends JFrame {
    JComboBox<Object> cb_VariosTipos;
    JCheckBox ckb_ParaAlgo;
    JTextField txt_Input;

    public static void main(String[] args) {
        PruebaFrame pruebaFrame = new PruebaFrame();
        pruebaFrame.setVisible(true);
    }
    
    public PruebaFrame() {
        initialize();
    }
    
    private void initialize() {
        setName("apliCSCE");
        setLocale(new Locale("es", "ES"));
        setMinimumSize(new Dimension(500, 600));
        //Establece el tamaño
        setBounds(100, 100, 1100, 400);
        
        //Para que termine la ejecucion al cerrar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Forma normal con swing
//        JPanel pn_Principal = new JPanel();
//        BoxLayout boxLayout = new BoxLayout(pn_Principal, BoxLayout.Y_AXIS);
//        pn_Principal.setLayout(boxLayout);
//        getContentPane().add(pn_Principal, BorderLayout.NORTH);
//        
//        JPanel pn_AccesoDirecto = new JPanel();
//        ((FlowLayout) pn_AccesoDirecto.getLayout()).setAlignment(FlowLayout.LEFT);
//        modificarValores = new BotonAccion("Añadir/Modificar Valores", "Modificar Valores");
//        pn_AccesoDirecto.add(modificarValores);
        
       
        //Componentes
        JLabel lbl_1 = new JLabel("Label 1");
        JLabel lbl_2 = new JLabel("Pinta toString() por defecto");
        JLabel lbl_3 = new JLabel("Label 3");
        JLabel lbl_4 = new JLabel("Label 4");
        JLabel lbl_Resultado = new JLabel("Resultado de una operación");
        JButton btn_1 = new JButton("Botón 1");
        JButton btn_2 = new JButton("Botón 2");
        JComboBox<String> cb_String = new JComboBox<>(new String[]{ "Primero", "Segundo" });
        Object objetoPersonalizado = new Object() {
            @Override
            public String toString() {
                return "Mi Objeto especial";
            } };
        
        //Acceso desde otro scope
        cb_VariosTipos = new JComboBox<>(new Object[]{ "Primero", 2, true, objetoPersonalizado });
        ckb_ParaAlgo = new JCheckBox("Es Algo (label TextBox)");
        txt_Input = new JTextField();
        txt_Input.setText("Algo escrito para pruebas");
        
        //Listeners
        btn_1.addActionListener(e -> lbl_Resultado.setText("Pulsado " + ((JButton)e.getSource()).getText().toString()));
        btn_2.addActionListener(e -> lbl_Resultado.setText(validarInput()));
        
        //Layout (configuracion general)
        Table tabla = new Table();
        Value padding = new Value.FixedValue(5);
        tabla.top().left().pad(padding);
        tabla.defaults().pad(padding).left();
//      tabla.columnDefaults(1).width(new Value.FixedValue(100));
        tabla.columnDefaults(2).expandX();
        
//        JPanel jPanel = new JPanel();
//        jPanel.add(tabla);
//        jPanel.setBackground(Color.RED);
//        jPanel.setBounds(100, 100, 100, 100);
//        getContentPane().add(jPanel);
        getContentPane().add(tabla);
        
        //Tamanhos
        Value anchoCombos = new Value.FixedValue(200);
        
        //Modo debug (se ven los bordes del layout)
        tabla.debug(Debug.all);
        
        //Agregar los componentes
        tabla.addCell(lbl_1);
        tabla.addCell(cb_String).width(anchoCombos);
        tabla.row();
        tabla.addCell(lbl_3);
        tabla.addCell(cb_VariosTipos).fillX();
        tabla.addCell(lbl_2);//.expandX();
        tabla.row();
        tabla.addCell(new JLabel("Es Algo"));
//        ckb_Jefe.setHorizontalTextPosition(SwingConstants.LEFT);
        tabla.addCell(ckb_ParaAlgo);
        tabla.row();
        tabla.addCell(lbl_4);//lbl_1);
        tabla.addCell(txt_Input).colspan(2).fillX();
        tabla.row();
        tabla.addCell(btn_1).center();
        tabla.addCell(lbl_Resultado).colspan(2);
        tabla.addCell(btn_2);
//        lbl_Ciu.setText("Label 1 cambiada");
        
        JScrollPane scPanel = new JScrollPane();
        scPanel.setViewportView(new PruebaLista(new SerializadorCSV("datos/SP1.csv").getEventos()));
        
        tabla.row();
        tabla.addCell(scPanel).colspan(3).fillX();

    }

    private String validarInput() {
        return ckb_ParaAlgo.isSelected() ? txt_Input.getText() : cb_VariosTipos.getSelectedItem().toString();
    }
}
