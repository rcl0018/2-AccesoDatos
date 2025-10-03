package otros;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class casopractico1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            File casoPractico = new File("casopractico.txt");
            casoPractico.createNewFile();

            if (casoPractico.exists()) {
                casoPractico.delete();
            } else {
                casoPractico.createNewFile();
            }

            FileWriter escritura = new FileWriter(casoPractico);
            escritura.write("a b c d e f g h i j k n m  o p q r s t u v w x y z");
            escritura.close();

            RandomAccessFile raf = new RandomAccessFile("casopractico.txt", "rw");
            System.out.println("que posicion deseas modificar");
            int num = sc.nextInt();
            sc.nextLine();

            System.out.println("que desea escribir ");
            String datosNuevos = sc.nextLine();

            raf.seek(num);
            raf.writeBytes(datosNuevos);

            raf.close();


        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
