import java.io.FileReader;
import java.io.LineNumberReader;

public class ejemplo2 {
    public static void main(String[] args) {
        
        try {
            LineNumberReader ln = new LineNumberReader(new FileReader("C:\\Users\\PC205\\Documents\\2º año\\2-AccesoDatos\\tema2\\ejemplo2.txt"));
            String line = ln.readLine();
            while (line != null) {
                System.out.println("contenido de la linea numero " + ln.getLineNumber() ); //te dice el numero de la linea que es
                System.out.println(line);
                line = ln.readLine();
                
            }

            ln.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
