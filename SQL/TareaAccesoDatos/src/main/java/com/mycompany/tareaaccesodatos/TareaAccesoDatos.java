package com.mycompany.tareaaccesodatos;

import java.util.Scanner;

public class TareaAccesoDatos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Menu");
        System.out.println("Que opcion quieres elegir");
        System.out.println("Opción 1: Crear nuevo cliente");
        System.out.println("Opción 2: Crear nuevo producto");
        System.out.println("Opción 3: Registrar un nuevo pedido");
        System.out.println("Opción 4: Consultar detalles de un pedido por ID");
        System.out.println("Opción 5: Eliminar un producto de un pedido");
        System.out.println("Opción 6: Salir");

        Int opcion = sc.nextInt();
        switch (opcion) {
            case 1:

                break;
            case 2:

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
