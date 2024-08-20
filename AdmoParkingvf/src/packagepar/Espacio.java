package packagepar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Espacio {

    private final JButton[] botones;
    private int BotonLibre;
    //private int BotonOcupado;
    private final Registro[] registros;

    public Espacio(JButton[] botones) {
        this.botones = botones;
        this.BotonLibre = 0;
     //   this.BotonOcupado = -1;
        this.registros = new Registro[botones.length];
        agregarActionListeners();
    }

    private void agregarActionListeners() {
        for (int i = 0; i < botones.length; i++) {
            final int index = i;
            botones[i].addActionListener(e -> VerInfo(index));
        }
    }

    private void VerInfo(int index) {
        Registro registro = registros[index];
        if (registro != null) {
            String mensaje = "Placa: " + registro.getPlaca() + "\n"
                    + "Tipo vehículo: " + registro.getTipo() + "\n"
                    + "Cédula: " + registro.getCedula() + "\n"
                    + "Hora de Entrada: " + registro.getHoraEntrada();
            JOptionPane.showMessageDialog(null, mensaje, "Información del Espacio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Este espacio está libre.", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void manejarIngreso(Registro registro) {
        if (BotonLibre < botones.length) {
            JButton boton = botones[BotonLibre];
            registros[BotonLibre] = registro;
            boton.setBackground(Color.red);

            BotonLibre++;
        } else {
            JOptionPane.showMessageDialog(null, "No hay más espacios libres.");
        }
    }
    
    public void manejarSalida() {
 
    String[] opciones = new String[botones.length];
    for (int i = 0; i < botones.length; i++) {
        opciones[i] = botones[i].getText(); 
    }

    // Mensaje sleccionar boton
    String seleccion = (String) JOptionPane.showInputDialog(null,"Seleccione el espacio a liberar:","Liberar Espacio",JOptionPane.PLAIN_MESSAGE,null,opciones,opciones[0]);

    // Buscar boton
    if (seleccion != null) {
        int index = -1;
        for (int i = 0; i < botones.length; i++) {
            if (botones[i].getText().equals(seleccion)) {
                index = i;
                break;
            }
        }
        
        if (index >= 0 && registros[index] != null) {
            Registro registro = registros[index];
            registro.registrarSalida();
            double tarifa = registro.calcularTarifa();
            JOptionPane.showMessageDialog(null, "El valor a cobrar es: $" + tarifa, "Cobro", JOptionPane.INFORMATION_MESSAGE);

            JButton boton = botones[index];
            boton.setBackground(Color.green);
   
            registros[index] = null;
            BotonLibre--;  
        } else {
            JOptionPane.showMessageDialog(null, "Este espacio ya se encuentra libre");
        }
    } 
}
    
    public void corregir(){
    String[] correciones = new String[botones.length];
    for (int i = 0; i < botones.length; i++) {
        correciones[i] = botones[i].getText(); 
    }
        String selec = (String) JOptionPane.showInputDialog(null,"Seleccione el espacio que desea corregir:","Corregir",JOptionPane.PLAIN_MESSAGE,null,correciones,correciones[0]);
        if (selec != null) {
        int index = -1;
        for (int i = 0; i < botones.length; i++) {
            if (botones[i].getText().equals(selec)) {
                index = i;
                break;
            }
        }

        if (index >= 0 && registros[index] != null) {
            Registro registro = registros[index];
            
            String nuevaCedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            String nuevaPlaca = JOptionPane.showInputDialog("Ingrese la placa:");
     
            if (nuevaCedula != null && !nuevaCedula.isEmpty()) {
                registro.setCedula(nuevaCedula);
            }
            if (nuevaPlaca != null && !nuevaPlaca.isEmpty()) {
                registro.setPlaca(nuevaPlaca);
            }

            JOptionPane.showMessageDialog(null, "Información actualizada", "Corrección", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El espacio seleccionado está libre.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
}
