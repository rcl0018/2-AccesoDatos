package otros;
import java.io.File;

public class ejemplo1 {

    public static void main(String[] args) {
        try {

            File fichero = new File(".\\Ejemplo.txt");

            String nombreCarpeta = "backup";
            File carpeta = new File(".\\", nombreCarpeta);
            fichero.createNewFile(); // crear ficehro
            // fichero.delete(); //Borrar fichero

            // carpeta.mkdir(); //Crear carpeta

            File fichero2 = new File(".\\backup\\backupEjercicio1.txt");
            fichero.renameTo(fichero2);

        } catch (Exception e) {
        }
    }
}