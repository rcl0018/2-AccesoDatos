package com.mycompany.tareaaccesodatos;

import java.util.Scanner;

public class TareaAccesoDatos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("MENU");
        System.out.println("Que opcion quieres elegir");
        System.out.println("Opción 1: Crear nuevo cliente");
        System.out.println("Opción 2: Crear nuevo producto");
        System.out.println("Opción 3: Registrar un nuevo pedido");
        System.out.println("Opción 4: Consultar detalles de un pedido por ID");
        System.out.println("Opción 5: Eliminar un producto de un pedido");
        System.out.println("Opción 6: Salir");

        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
//"Solicita al usuario el nombre, ciudad y edad del cliente. Inserta estos datos en la tabla cliente."
                System.out.println("Dime el nombre del cliente");
                String nombre = sc.nextLine();
                System.out.println("DIme la ciudad del cliente");
                String ciudad = sc.nextLine();
                System.out.println("Dime la edad del cliente");
                int edadCliente = sc.nextInt();
                sc.next();
                
                
                break;
            case 2:
               // Solicita al usuario el nombre, precio y stock del producto. Inserta estos datos en la tabla producto.
System.out.println("Introduce el nombre del cliente");
String nombreProducto = sc.nextLine();
                System.out.println("Introduce el precio del preoducto");
                int precio = sc.nextInt();
                sc.next();
                System.out.println("Introduce el stock del producto");
                int stock = sc.nextInt();
                sc.next();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
                throw new AssertionError();
        }
        
        
        
        
        
    }

}
