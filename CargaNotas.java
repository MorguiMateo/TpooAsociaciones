import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    public String getCatedra() {
        return catedra;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void addNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return suma / notas.size();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public long getLegajo() {
        return legajo;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese nombre completo del alumno: ");
            scanner.nextLine(); // Limpiar el buffer
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el legajo del alumno: ");
            long legajo = scanner.nextLong();

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.print("Ingrese la cantidad de notas para el alumno: ");
            int cantidadNotas = scanner.nextInt();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra de la nota: ");
                scanner.nextLine(); // Limpiar el buffer
                String catedra = scanner.nextLine();

                System.out.print("Ingrese la nota del examen: ");
                double notaExamen = scanner.nextDouble();

                alumno.addNota(new Nota(catedra, notaExamen));
            }

            alumnos.add(alumno);
        }

        for (Alumno alumno : alumnos) {
            System.out.println("Alumno: " + alumno.getNombreCompleto() +
                    ", Legajo: " + alumno.getLegajo() +
                    ", Promedio: " + alumno.calcularPromedio());

            for (Nota nota : alumno.getNotas()) {
                System.out.println("  Cátedra: " + nota.getCatedra() +
                        ", Nota: " + nota.getNotaExamen());
            }
        }

        scanner.close();
    }
}
