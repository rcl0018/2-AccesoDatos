package tema3;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class parserDOM {
    public static void main(String[] args) {
            try {
            // Crear una instancia de DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Configurar la factoria para que el fichero que se carga está bien validado e ignora espacios en blanco.
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            //se crea un objeto DocumentBuilder por medio de la factoría creada previamente.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Especificar el archivo XML que deseas analizar.
            File file = new File("C:\\Users\\PC205\\Documents\\2º año\\2-AccesoDatos\\tema3\\fichero.xml");

            // Parsear (analizar) el archivo XML y obtener un objeto Document.
            Document doc = builder.parse(file);  
            doc.getDocumentElement().normalize();

            // Acceso al nodo raíz
            Element root = doc.getDocumentElement();
            System.out.println("Elemento raíz: " + root.getNodeName());

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    
}







