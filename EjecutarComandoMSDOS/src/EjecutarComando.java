import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//http://chuwiki.chuidiang.org/index.php?title=Backup_de_MySQL_con_Java

public class EjecutarComando {

	public static void main(String[] args) throws IOException, InterruptedException {
		// El código que sigue a continuación usa la linea de comando de windows (CMD).
		Process p = Runtime.getRuntime().exec("mysqldump -u root -padmin supermercado");

		try (InputStream is = p.getInputStream(); FileOutputStream fos = new FileOutputStream("backup_pruebas.sql")) {

			// creo un array de bytes q sera mi buffer
			byte[] buffer = new byte[1000];

			// voy leyendo metiendo en el buffer de 1000 en 1000bytes.
			// Mientras leo el bufer y es mayor de 0 me lo escribe. Vuelvo al llenar el
			// buffer y sigo leyendo.
			int leido;
			while ((leido = is.read(buffer)) > 0) {
				fos.write(buffer, 0, leido);
			}
		}

		// Una vez autenticado hay que capturar errores posibles con try catch y ademas
		// usar el método waitFor() de la clase Process-que es la clase que maneja los
		// procesos que se utilizan con la linea de comando de windows-.

		// Lo que hace waitFor() es esperar que el comando utilizado termine su proceso,
		// haciendo que ningún otro proceso funcione hasta que el comando que esta en
		// proceso termine:
		int processComplete = p.waitFor();

		// si ha terminado el proceso sin errores nos devuelve 0. Asi que comprobamos q
		// este correcto.
		if (processComplete == 0) {
			System.out.println("Todo correcto");
		} else {
			System.out.println("Ha habido algún error");
		}
	}

}
