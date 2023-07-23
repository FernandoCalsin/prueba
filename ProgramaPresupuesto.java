import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProgramaPresupuesto {
    public static void main(String[] args) {
        boolean continuar = true;
        String tablas = "";
        ArrayList<Integer> meses = new ArrayList<Integer>();
        while (continuar) {
            int mes = leerInt("Ingrese el número de mes (1-12): ");
            meses.add(mes);
            double ingresoMensual = leerDouble("Ingrese el ingreso mensual: ");
            ArrayList<Double> gastos = new ArrayList<Double>();
            gastos.add(LeerCategoriaDeGastos("gastos de servicio de agua", "gastos de servicio de luz",
                    "gastos de servicio de gas", "gastos de servicio de internet"));
            gastos.add(LeerGastosOpcionales("gastos de alquiler"));
            gastos.add(LeerGastosOpcionales("gastos en alimentos"));
            gastos.add(LeerGastosOpcionales("gastos en ropa o cosas de lujo"));
            gastos.add(LeerGastosOpcionales("gastos imprevistos"));
            double totalCostos = 0;
            for (double gasto : gastos) {
                totalCostos += gasto;
            }
            double montoRestante = ingresoMensual - totalCostos;
            String tabla = "\nTabla de Gstos del mes " + mes + "\n"; // Agregar el número de mes a la tabla
            tabla += "-----------------------------------------------------\n";
            tabla += String.format("Ingreso Mensual:                                %.2f%n", ingresoMensual);
            tabla += String.format("Gastos por Servicios:                        %.2f%n", gastos.get(0));
            tabla += String.format("Gasto de Alquiler de Vivienda:         %.2f%n", gastos.get(1));
            tabla += String.format("Gastos en Alimentos:                         %.2f%n", gastos.get(2));
            tabla += String.format("Gastos en Ropa o Cosas de Lujo:   %.2f%n", gastos.get(3));
            tabla += String.format("Gastos Imprevistos:                           %.2f%n", gastos.get(4));
            tabla += "------------------------------------------------------\n";
            tabla += String.format("Total de Gastos:                               %.2f%n", totalCostos);
            tabla += String.format("Monto Restante:                                %.2f%n", montoRestante);
            tablas += tabla;
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea ingresar otro mes?", "Programa Presupuesto",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                continuar = false;
            }
        }
        JOptionPane.showMessageDialog(null, tablas, "Programa Presupuesto", JOptionPane.INFORMATION_MESSAGE);
        for (int mes : meses) {
            JOptionPane.showMessageDialog(null, "Mes ingresado: " + mes, "Programa Presupuesto",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static double leerDouble(String message) {
        String input = JOptionPane.showInputDialog(null, message, "Programa Presupuesto", JOptionPane.QUESTION_MESSAGE);
        return Double.parseDouble(input);
    }

    private static int leerInt(String message) {
        String input = JOptionPane.showInputDialog(null, message, "Programa Presupuesto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    private static double LeerCategoriaDeGastos(String... categories) {
        double total = 0;
        for (String category : categories) {
            total += leerDouble("Ingrese el " + category + ": ");
        }
        return total;
    }

    private static double LeerGastosOpcionales(String category) {
        int option = JOptionPane.showConfirmDialog(null, "¿Tiene " + category + "?", "Programa Presupuesto",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return leerDouble("Ingrese el " + category + ": ");
        } else {
            return 0;
        }
    }
}