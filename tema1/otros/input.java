package otros;
import java.io.FileInputStream;

public class input {
    public static void main(String[] args) {

        String path = "./Ejemplo3.txt";
        String pathEscritura = "./Ejemplo3.txt";

        int leer;
        try {
            FileInputStream entrada = new FileInputStream(path);
            while ((leer = entrada.read()) != -1) {
                System.out.println((char) leer);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
