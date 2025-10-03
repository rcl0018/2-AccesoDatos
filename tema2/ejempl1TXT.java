import java.io.FileReader;
import java.io.StreamTokenizer;

public class ejempl1TXT {

    public static void main(String[] args) {
        // esto sirve para contar palabras, numeros, espacios, etc

        try {
            StreamTokenizer st = new StreamTokenizer(
                    new FileReader("C:\\Users\\PC205\\Documents\\2º año\\Acceso a datos\\tema2\\ejemplo.txt"));

            st.eolIsSignificant(true); //hay que poner esto para que funcione el final de linea

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
                    System.out.println("fin de la linea");
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
