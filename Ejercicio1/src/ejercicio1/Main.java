package ejercicio1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {

	private static final String cabecera = "--------------------------------------\n"+ "Cartelera de CineFBMoll\n" + "--------------------------------------\n";
	
	public static void main(String[] args){
		boolean mostrarMenu = true;
		

		try {
			Scanner input = new Scanner(System.in);
			while (mostrarMenu) {
				System.out.println("Escoja una opción: ");
				System.out.println("1) Lectura y escritura del fichero de cartelera byte a byte");
				System.out.println("2) Lectura y escritura de fichero de cartelera carácter a carácter");
				System.out.println("3) Lectura y escritura de fichero línea a línea con buffers");
				System.out.println("4) Ir al menú de objetos");
				System.out.println("5) Salir");
				
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
					menuObjetos();
				case 5:
					mostrarMenu = false;
					break;
				default:
					System.out.println("Escoja una de las cuatro opciones, por favor");
				}
			}
		} catch (origenInvalido e) {
		System.out.println(e.getMessage());
		
		} catch (IOException e) {
			
		}
		finally {
			
		}
	}
	
	public static void byteReaderWriter() throws IOException, origenInvalido{
		int i;
		Scanner input = new Scanner(System.in);
		FileInputStream in = null;
		FileOutputStream out = null;
		String texto = cabecera;
		
		try {
			System.out.println("Indique la ruta de origen:");
			String origen = input.nextLine();
			if (origen.equals("origen.txt") == false) {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	throw new origenInvalido();
	        }
			in = new FileInputStream(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
			while (destino.contentEquals("")) {
				throw new destinoInvalido();
			}
	        out = new FileOutputStream(destino);
	        do {
	        	i = in.read();
	        	texto += (char)i;
	            
	        } while (i != -1);
	        for (int j = 0; j <= 10; j++) {
	        	if (j==0 || j==5) {
	        		texto.replaceFirst("#", "-----\nAño: ");
	        	}
	        	else if (j==1 || j==6) {
	        		texto.replaceFirst("#", "\nDirector: ");	
	        	}
	        	else if (j==2 || j==7) {
	        		texto.replaceFirst("#", "\nDuración: ");	
	        	}
	        	else if (j==3 || j==8) {
	        		texto.replaceFirst("#", "\nSinopsis: ");	
	        	}
	        	else if (j==4 || j==9) {
	        		texto.replaceFirst("#", "\nReparto: ");	
	        	}
	        	if (j==5 || j==9) {
	        		texto.replaceFirst("#", "\nSesión: ");	
	        	}
	        	else {
	        		texto.replace("{", "\n-----");
	        	}
	        }
	        out.write(texto.getBytes());
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

	
	public static void charReaderWriter() throws IOException, origenInvalido {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Indique la ruta de origen: ");
			String origen = input.nextLine();
			if (origen.contentEquals("origen.txt") == false) {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	throw new origenInvalido();
			}
			File archivoEntrada = new File(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
			File archivoSalida = new File(destino);
			FileReader lector = new FileReader(archivoEntrada);
			FileWriter escritor = new FileWriter(archivoSalida);
			escritor.write(cabecera);
			for (int i = 0; i<archivoEntrada.length(); i++) {
				int caracter = lector.read();
			
				escritor.write((char) caracter);
			}
			lector.close();
			escritor.close();
		} finally {
			
		}
	}
	
	public static void lineReaderWriter() throws IOException, origenInvalido {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Indique la ruta de origen: ");
			String origen = input.nextLine();
			if (origen.contentEquals("origen.txt") == false) {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	throw new origenInvalido();
			}
			File archivoEntrada = new File(origen);
			System.out.println("Indique la ruta de destino");
			String destino = input.nextLine();
			File archivoSalida = new File(destino);
			BufferedReader ultralector = new BufferedReader(new FileReader(archivoEntrada));
			BufferedWriter ultraescritor = new BufferedWriter(new FileWriter(archivoSalida));
			
			boolean eof = false;
			do {
				String linea_leida = ultralector.readLine();
				if (linea_leida != null) {
					String[] lineas = linea_leida.split("\\{");
					for (int i = 0; i < lineas.length; i++) {
						String[] myMovie = lineas[i].split("#");
						ultraescritor.write("-----" + myMovie[0] + "-----\n");
						ultraescritor.write("Año: " + myMovie[1] + "\n");
						ultraescritor.write("Director: " + myMovie[2] + "\n");
						ultraescritor.write("Duración: " + myMovie[3] + "\n");
						ultraescritor.write("Sinopsis: " + myMovie[4] + "\n");
						ultraescritor.write("Reparto: " + myMovie[5] + "\n");
						ultraescritor.write("Sesión: " + myMovie[6] + "\n");
					}
				} else {
					eof = true;
				}
			} while (!eof);
			ultraescritor.flush();
			ultralector.close();
			ultraescritor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void menuObjetos() throws origenInvalido, IOException {
		boolean mostrarSubMenu = true;
		
		try {
			Scanner input = new Scanner(System.in);
			while (mostrarSubMenu) {

				System.out.println("---- SUBMENÚ ----");
				System.out.println("1) Lectura línea a línea y escritura con objetos"); 
				System.out.println("2) Lectura de objetos y escritura de objetos");
				System.out.println("3) Lectura de objetos y escritura por consola");
				System.out.println("4) Lectura por consola y escritura de objetos");
				System.out.println("5) Volver al menú principal");
				int opcion = input.nextInt();
				
				switch (opcion) {
				case 1:
					LineReaderObjectWriter();
					break;
				case 2:
					ObjectReaderObjectWriter();
					break;
				case 3:
					ObjectReaderConsoleWriter();
					break;
				case 4:
					break;
				case 5:
					mostrarSubMenu = false;
					break;
				}
			}
		} finally {
			
		}
		
	}
	public static void LineReaderObjectWriter() throws IOException, origenInvalido {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Indique la ruta de origen: ");
			String origen = input.nextLine();
			if (origen.contentEquals("origen.txt") == false) {
	        	//si se selecciona un archivo de origen distinto al de la carpeta, se lanza la siguiente excepción
	        	throw new origenInvalido();
			}
			File archivoEntrada = new File(origen);
			File f = new File("ficheroSalObj.obj");
			FileOutputStream fos = new FileOutputStream(f);
			BufferedReader ultralector = new BufferedReader(new FileReader(archivoEntrada));
			ObjectOutputStream objescritor = new ObjectOutputStream(fos);
			boolean eof = false;
			do {
				String linea_leida = ultralector.readLine();
				if (linea_leida != null) {
					ArrayList <Pelicula> Cartelera = new ArrayList<Pelicula>();
					String[] lineas = linea_leida.split("\\{");
					Pelicula a = new Pelicula();
					Pelicula b = new Pelicula();
					Cartelera.add(a);
					Cartelera.add(b);
					for (int i = 0; i < lineas.length; i++) {
						String[] myMovie = lineas[i].split("#");
						Cartelera.get(i).setTitulo(myMovie[0]);
						Cartelera.get(i).setAño(myMovie[1]);
						Cartelera.get(i).setDirector(myMovie[2]);
						Cartelera.get(i).setDuracion(myMovie[3]);
						Cartelera.get(i).setSinopsis(myMovie[4]);
						Cartelera.get(i).setReparto(myMovie[5]);
						Cartelera.get(i).setSesiones(myMovie[6]);
					}
					
					for (int j =0; j < Cartelera.size(); j++) {
						objescritor.writeObject(Cartelera.get(j));
					}
					
				} else {
					eof = true;
				}
			} while (!eof);
			ultralector.close();
			objescritor.close();
		} catch (EOFException e) {
			System.out.println("Fin de fichero");
		} finally {
			
		}
	}
	
	public static void ObjectReaderObjectWriter() throws origenInvalido, IOException{
		
		try {
			FileInputStream fis = new FileInputStream("ficheroSalObj.obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Pelicula p = (Pelicula) ois.readObject();
			FileOutputStream fos = new FileOutputStream("ficheroSalObj2.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(p);
			
			ois.close();
			oos.close();
		} catch (EOFException e) {
			System.out.println("Fin de fichero");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}

	}
	
	public static void ObjectReaderConsoleWriter() {
			
		try {
			FileInputStream fis = new FileInputStream("ficheroSalObj2.obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Pelicula p = (Pelicula) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
