package ejercicio1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			boolean mostrarMenu = true;
			while (mostrarMenu) {
				System.out.println("Escoja una opción: ");
				System.out.println("1) Lectura y escritura del fichero de cartelera byte a byte");
				System.out.println("2) Lectura y escritura de fichero de cartelera carácter a carácter");
				System.out.println("3) Lectura y escritura de fichero línea a línea con buffers");
				System.out.println("4) Salir");
				Scanner input = new Scanner(System.in);
				int opcion = input.nextInt();
				switch (opcion) {
				case 1:
					byteReaderWriter();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					mostrarMenu = false;
					break;
				default:
					System.out.println("Escoja una de las cuatro opciones, por favor");
				}
			}
		} catch (rutaInvalida e) {
		System.out.println(e.getMessage());
		java.util.Date fecha = new java.util.Date();
		String log_error = e.getMessage() + " " + fecha;
		FileOutputStream error = new FileOutputStream("errores.txt");
		//El error se registra en errores.txt y el programa finaliza su ejecución.
		error.write(log_error.getBytes());
		error.close();
		}
	}
	
	public static void byteReaderWriter() throws IOException, rutaInvalida {
		int i;
		Scanner input = new Scanner(System.in);
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			System.out.println("Indique la ruta de origen:");
			String origen = input.nextLine();
			if (origen != "origen.txt") {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	throw new rutaInvalida();
	        }
			in = new FileInputStream(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
	        out = new FileOutputStream(destino);
	        do {
	        	i = in.read();
	        	if (i != -1) {
	        		out.write(i);
	            }
	        } while (i != -1);
		
		} finally {
			in.close();
			out.close();
		}
			
	}

	
	public static void charReaderWriter() {
		
	}
	
	public static void lineReaderWriter() {
		
	}
	

}
