package ejercicio1;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class ejercicio1 {
    public static void main(String[] args) {
        int palabras = 0;
        int numeros = 0;

        try {

            LineNumberReader ln = new LineNumberReader(new FileReader(
                    "C:\\Users\\PC205\\Documents\\2º año\\2-AccesoDatos\\tema2\\ejercicio1\\ejercicio1.txt"));

            String line = ln.readLine();

            while (line != null) {
                StreamTokenizer sT = new StreamTokenizer(new StringReader(
                        line));
                System.out.println("Linea " + ln.getLineNumber() + ": " + line);
                line = ln.readLine();

                while (sT.nextToken() != StreamTokenizer.TT_EOF) {
                    if (sT.ttype == StreamTokenizer.TT_WORD) {
                        palabras++;
                    } else if (sT.ttype == StreamTokenizer.TT_NUMBER) {
                        numeros++;
                    }
                    
                }
                System.out.println("palabras: " + palabras + ", Numeros: " + numeros);
                palabras = 0;
                numeros = 0;

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
