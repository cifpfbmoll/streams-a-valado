package ejercicio1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		boolean mostrarMenu = true;
		try {
			Scanner input = new Scanner(System.in);
			while (mostrarMenu) {
				System.out.println("Escoja una opción: ");
				System.out.println("1) Lectura y escritura del fichero de cartelera byte a byte");
				System.out.println("2) Lectura y escritura de fichero de cartelera carácter a carácter");
				System.out.println("3) Lectura y escritura de fichero línea a línea con buffers");
				System.out.println("4) Salir");
				
				int opcion = input.nextInt();
				switch (opcion) {
				case 1:
					byteReaderWriter();
					break;
				case 2:
					charReaderWriter();
					break;
				case 3:
					lineReaderWriter();
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
		String log_error = "\n"+ e.getMessage() + " " + Thread.currentThread().getStackTrace() + " " + fecha;
		FileOutputStream error = new FileOutputStream("errores.txt", true);
		//El error se registra en errores.txt y el programa finaliza su ejecución.
		error.write(log_error.getBytes());
		error.close();
		} finally {
			
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
			while (destino == "") {
				throw new destinoInvalido();
			}
	        out = new FileOutputStream(destino);
	        do {
	        	i = in.read();
	        	if (i != -1) {
	        		out.write(i);
	            }
	        } while (i != -1);
		} catch (destinoInvalido e) {
			e.getMessage();
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
			
	}

	
	public static void charReaderWriter() throws IOException, rutaInvalida {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Indique la ruta de origen: ");
			String origen = input.nextLine();
			//if (origen != "origen.txt") {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	//throw new rutaInvalida();
			//}
			File archivoEntrada = new File(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
			File archivoSalida = new File(destino);
			FileReader lector = new FileReader(archivoEntrada);
			FileWriter escritor = new FileWriter(archivoSalida);
			for (int i = 0; i<archivoEntrada.length(); i++) {
				int caracter = lector.read();
				escritor.write((char) caracter);
			}
			lector.close();
			escritor.close();
		} finally {
			
		}
	}
	
	public static void lineReaderWriter() throws IOException, rutaInvalida {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Indique la ruta de origen: ");
			String origen = input.nextLine();
			File archivoEntrada = new File(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
			File archivoSalida = new File(destino);
			BufferedReader ultralector = new BufferedReader(new FileReader(archivoEntrada));
			BufferedWriter ultraescritor = new BufferedWriter(new FileWriter(archivoSalida));
			
			boolean eof = false;
			String linea_leida = null;
			
			while (!eof) {
				linea_leida = ultralector.readLine();
				if (linea_leida != null) {
					ultraescritor.write(linea_leida, 0, linea_leida.length());
				}
				else {
					eof = true;
				}
			}
			ultralector.close();
			ultraescritor.close();
		} finally {
			
		}
		
	}
	

}
