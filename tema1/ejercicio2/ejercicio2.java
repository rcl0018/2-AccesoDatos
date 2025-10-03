package ejercicio2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ejercicio2 {
    public static void main(String[] args) {

        String archivoOrigen = "C:\\Users\\PC205\\Documents\\2º año\\Acceso a datos\\ejercicio2\\archivoOrigen.txt";
        String archivoDestino = "C:\\Users\\PC205\\Documents\\2º año\\Acceso a datos\\ejercicio2\\archivoDestino.txt";

        int bufferSize = 10;

        try {
            BufferedInputStream bI = new BufferedInputStream(new FileInputStream(archivoOrigen), bufferSize);
            BufferedOutputStream bO = new BufferedOutputStream(new FileOutputStream(archivoDestino));

            byte[] buffer = new byte[200];
            int lectura;
            int bloque = 1;

            while ((lectura = bI.read(buffer)) != -1) {
                // Escribe exactamente lo que voy a leer
                bO.write(buffer, 0, lectura);
                System.out.println("Fin del bloque " + bloque);

                String marcaBloque = "Fin de la copia " + bloque + "";
                bO.write(marcaBloque.getBytes());

                bloque++;
            }
            bI.close();
            bO.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
