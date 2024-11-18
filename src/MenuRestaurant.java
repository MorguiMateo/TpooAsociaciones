import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadDeMedida;

    public Ingrediente(String nombre, double cantidad, String unidadDeMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadDeMedida = unidadDeMedida;
    }

    @Override
    public String toString() {
        return nombre + "\t" + cantidad + "\t" + unidadDeMedida;
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    @Override
    public String toString() {
        StringBuilder infoPlato = new StringBuilder();
        infoPlato.append(nombreCompleto).append("\n")
                .append("Precio: $").append(precio).append("\n");
        if (!esBebida) {
            infoPlato.append("Ingredientes:\n")
                    .append("Nombre\tCantidad\tUnidad de Medida\n");
            for (Ingrediente ingrediente : ingredientes) {
                infoPlato.append(ingrediente.toString()).append("\n");
            }
        }
        infoPlato.append("--------------------------------------\n");
        return infoPlato.toString();
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        ArrayList<Plato> platosMenu = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de platos: ");
        int numeroPlatos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numeroPlatos; i++) {
            System.out.print("Ingrese el nombre completo del plato: ");
            String nombrePlato = scanner.nextLine();

            System.out.print("Ingrese el precio del plato: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("¿Es bebida? (true/false): ");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();

            Plato plato = new Plato(nombrePlato, precio, esBebida);

            if (!esBebida) {
                System.out.print("Ingrese el número de ingredientes: ");
                int numeroIngredientes = scanner.nextInt();
                scanner.nextLine();

                for (int j = 0; j < numeroIngredientes; j++) {
                    System.out.print("Ingrese el nombre del ingrediente: ");
                    String nombreIngrediente = scanner.nextLine();

                    System.out.print("Ingrese la cantidad del ingrediente: ");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Ingrese la unidad de medida: ");
                    String unidadMedida = scanner.nextLine();

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidad, unidadMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("\n......................MENÚ......................");
        for (Plato plato : platosMenu) {
            System.out.println(plato);
        }

        scanner.close();
    }
}
