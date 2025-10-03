import java.io.StreamTokenizer;
import java.io.StringReader;

public class ejemplo1 {
    public static void main(String[] args) {
        // esto sirve para contar palabras, numeros, espacios, etc
        StreamTokenizer st = new StreamTokenizer(new StringReader("hola mi edad es de 45 años \n"));
        try {
            // esto parara cuando llege a final
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                // Mostrar por pantalla pantalla
                if (st.ttype == StreamTokenizer.TT_WORD) {
                    System.out.println("palabra " + st.sval);

                } // mostrar numero por pantalla
                else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("number " + st.nval);

                } // mostra final de linea
                else if (st.ttype == StreamTokenizer.TT_EOL) {
                    System.out.println("fin de la linea" );
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
