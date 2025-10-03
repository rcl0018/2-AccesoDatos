package tema3;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ejemplo1Checked {
    public static void main(String[] args) {
        
        try {
            FileReader file = new FileReader("archivo");


            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
