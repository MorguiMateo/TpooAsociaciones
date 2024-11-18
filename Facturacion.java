import java.util.ArrayList;
import java.util.Scanner;

// Clase DetalleFactura
class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    // Constructor
    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularDescuentoYSubtotal();
    }

    // Métodos para calcular el descuento y el subtotal
    public void calcularDescuentoYSubtotal() {
        if (cantidad >= 5) {
            descuentoItem = precioUnitario * 0.1;
        } else {
            descuentoItem = 0;
        }
        subtotal = (precioUnitario * cantidad) - (descuentoItem * cantidad);
    }

    // Getters
    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getDescuentoItem() {
        return descuentoItem;
    }

    public double getSubtotal() {
        return subtotal;
    }
}

// Clase Factura
class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura;

    // Constructor
    public Factura() {
        this.detallesFactura = new ArrayList<>();
    }

    // Métodos para asignar valores y calcular el total
    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        if (numeroFactura > 0) {
            this.numeroFactura = numeroFactura;
        }
    }

    public void setCliente(String cliente) {
        if (!cliente.isEmpty()) {
            this.cliente = cliente;
        }
    }

    public void agregarDetalleFactura(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    // Método para imprimir la factura
    public void imprimirFactura() {
        System.out.println("Fecha: " + fechaFactura);
        System.out.println("Número: " + numeroFactura);
        System.out.println("Cliente: " + cliente);
        System.out.println("Código   Nombre   Cantidad   Precio   Descuento   Subtotal");
        for (DetalleFactura detalle : detallesFactura) {
            System.out.printf("%s    %s    %d    %.2f    %.2f    %.2f\n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem(),
                    detalle.getSubtotal());
        }
        System.out.println("Total: " + totalCalculadoFactura);
    }
}

// Clase Facturación
public class Facturacion {
    static String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabon en Polvo", "60"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        System.out.println("Ingrese la fecha de la factura:");
        factura.setFechaFactura(scanner.nextLine());

        System.out.println("Ingrese el número de la factura:");
        factura.setNumeroFactura(scanner.nextLong());
        scanner.nextLine(); // consume el salto de línea

        System.out.println("Ingrese el nombre del cliente:");
        factura.setCliente(scanner.nextLine());

        while (true) {
            System.out.println("Ingrese el código del artículo (o 'fin' para terminar):");
            String codigo = scanner.nextLine();
            if (codigo.equals("fin")) {
                break;
            }

            int index = buscarArticulo(codigo);
            if (index == -1) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad:");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // consume el salto de línea

            String nombre = articulos[index][1];
            double precio = Double.parseDouble(articulos[index][2]);

            DetalleFactura detalle = new DetalleFactura(codigo, nombre, cantidad, precio);
            factura.agregarDetalleFactura(detalle);
        }

        factura.calcularMontoTotal();
        factura.imprimirFactura();
    }

    public static int buscarArticulo(String codigo) {
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i][0].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
}
