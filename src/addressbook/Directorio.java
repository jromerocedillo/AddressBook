package addressbook;

	//public class Directorio {
	
	import java.io.*;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Scanner;

	public class Directorio {
	    private Map<String, String> contactos;

	    public Directorio() {
	        contactos = new HashMap<>();
	    }

	    public void load() {
	        try (BufferedReader br = new BufferedReader(new FileReader("contactos.txt"))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] partes = linea.split(",");
	                if (partes.length == 2) {
	                    contactos.put(partes[0], partes[1]);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("No se pudo cargar el archivo de contactos.");
	        }
	    }

	    public void save() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contactos.txt"))) {
	            for (Map.Entry<String, String> entry : contactos.entrySet()) {
	                bw.write(entry.getKey() + "," + entry.getValue());
	                bw.newLine();
	            }
	            System.out.println("Se guardaron los cambios en el archivo.");
	        } catch (IOException e) {
	            System.out.println("No se pudo guardar el archivo de contactos.");
	        }
	    }

	    public void list() {
	        System.out.println("Contactos:");
	        for (Map.Entry<String, String> entry : contactos.entrySet()) {
	            System.out.println(entry.getKey() + " : " + entry.getValue());
	        }
	    }

	    public void create(String numero, String nombre) {
	        contactos.put(numero, nombre);
	        System.out.println("Se creó el contacto.");
	    }

	    public void delete(String numero) {
	        if (contactos.containsKey(numero)) {
	            contactos.remove(numero);
	            System.out.println("Se eliminó el contacto.");
	        } else {
	            System.out.println("No se encontró el número en la agenda.");
	        }
	    }

	    public static void main(String[] args) {
	        Directorio agenda = new Directorio();
	        agenda.load();

	        Scanner scanner = new Scanner(System.in);
	        int opcion;

	        do {
	            System.out.println("\nMenú de opciones:");
	            System.out.println("1. Listar contactos");
	            System.out.println("2. Crear contacto");
	            System.out.println("3. Eliminar contacto");
	            System.out.println("4. Guardar cambios");
	            System.out.println("5. Salir");
	            System.out.print("Ingrese su opción: ");
	            opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                    agenda.list();
	                    break;
	                case 2:
	                    System.out.print("Ingrese el número de teléfono: ");
	                    String numero = scanner.next();
	                    System.out.print("Ingrese el nombre: ");
	                    String nombre = scanner.next();
	                    agenda.create(numero, nombre);
	                    break;
	                case 3:
	                    System.out.print("Ingrese el número de teléfono a eliminar: ");
	                    String numeroEliminar = scanner.next();
	                    agenda.delete(numeroEliminar);
	                    break;
	                case 4:
	                    agenda.save();
	                    break;
	                case 5:
	                    System.out.println("Saliendo...");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtelo de nuevo.");
	            }
	        } while (opcion != 5);
	    }
	}


