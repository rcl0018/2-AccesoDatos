package tema3;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;
import java.io.*;

public class extraerDatos {

    public static void main(String[] args) {

        try {
            // Crear una instancia de DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Configurar la factoria para que el fichero que se carga está bien validado e
            // ignora espacios en blanco.
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            // se crea un objeto DocumentBuilder por medio de la factoría creada
            // previamente.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Especificar el archivo XML que deseas analizar.
            File file = new File("C:\\Users\\PC205\\Documents\\2º año\\2-AccesoDatos\\tema3\\fichero.xml");

            // Parsear (analizar) el archivo XML y obtener un objeto Document.
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Acceso al nodo raíz
            Element root = doc.getDocumentElement();
            System.out.println("Elemento raíz: " + root.getNodeName());

            // Crear un objeto Xpath para consultar el documento XML
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Definir la expresion Xpath para obtener todas las bibliotecas
            String expresion = "/libraries/library";

            // Evaluar la expresion y obtener una lista de nodos de bibliotecas
            NodeList nodeList = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);

            // Iterar a traves de la lista de nodos de bibliotecas
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);
                System.out.println("\nElemento actual: " + nNode.getNodeName());
                System.out.println("\nElemento padre: " + nNode.getParentNode());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nNode;
                    System.out.println("Nombre: " + elemento.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Ciudad: " + elemento.getAttribute("location"));

                }

                String expresion2 = "//books";

                NodeList nodeList2 = (NodeList) xPath.compile(expresion2).evaluate(nNode, XPathConstants.NODESET);

                for(int j = 0;j <nodeList2.getLength(); j++){
                    Node node2 = nodeList2.item(j);

                    System.out.println("elemento actual " + node2.getNodeName());

                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento2 = (Element)node2;
                        System.out.println(""+elemento2.getElementsByTagName("title").item(j).getTextContent());
                        System.out.println(""+elemento2.getElementsByTagName("author").item(j).getTextContent());
                        System.out.println(""+elemento2.getElementsByTagName("genre").item(j).getTextContent());
                        System.out.println(""+elemento2.getElementsByTagName("year").item(j).getTextContent());
                        
                    }
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
