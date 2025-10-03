package otros;
import java.io.FileReader;
import java.io.FileWriter;

public class Filereader {
    public static void main(String[] args) {

        String path = "./Ejemplo2.txt";  //ruta de fichero
        String pathEscritura = "./Ejemplo2.txt";

        try {
            FileReader lector = new FileReader(path);  //leer la ruta
            int data;

            while ((data = lector.read() )!= -1 ) {   //mientras que el no se lea un -1 se sigue ejecuntando el bucle

                System.out.print((char)data); //para convertirlo en carcater
            };  //lector del primer caracter y refencia en la tabla asci y para cambiarlo utilizamos el char

            System.out.println();

            lector.close();
            System.out.println("Lectura completada");
        } catch (Exception e) {
            // TODO: handle exception
        }


        try {
            FileWriter escritura = new FileWriter(pathEscritura);

            escritura.write("esto es la gran muralla china");
            escritura.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
