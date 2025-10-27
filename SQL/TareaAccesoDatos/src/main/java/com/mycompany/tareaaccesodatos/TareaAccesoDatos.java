package com.mycompany.tareaaccesodatos;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class TareaAccesoDatos {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        String URL = "jdbc:mysql://localhost:3306/tienda_db";
        String usuario = "root";
        String password = "A20prende00)";
        Connection sbConector = DriverManager.getConnection(URL, usuario, password);

        System.out.println("conexion establecida");
        System.out.println("");
        System.out.println("");

        String consulta;

        boolean bucle = true;
        while (bucle) {

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
                    String nombre = sc.next();
                    System.out.println("DIme la ciudad del cliente");
                    String ciudad = sc.next();
                    System.out.println("Dime la edad del cliente");
                    int edadCliente = sc.nextInt();

                    consulta = "INSERT INTO Cliente (nombre, ciudad, edad) VALUES (?, ?, ?)";

                    try {

                        PreparedStatement ps = sbConector.prepareStatement(consulta);
                        System.out.println("st realizado");

                        ps.setString(1, nombre);
                        ps.setString(2, ciudad);
                        ps.setInt(3, edadCliente);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //mostrar actualizada la tabla
                    break;

                case 2:
                    // Solicita al usuario el nombre, precio y stock del producto. Inserta estos datos en la tabla producto.
                    System.out.println("Introduce el nombre del producto");
                    String nombreProducto = sc.next();
                    System.out.println("Introduce el precio del preoducto");
                    float precio = (float) sc.nextInt();
                    System.out.println("Introduce el stock del producto");
                    int stock = sc.nextInt();

                    consulta = "INSERT INTO Producto (nombre, precio, stock) VALUES (?, ?, ?)";

                    try {

                        PreparedStatement ps = sbConector.prepareStatement(consulta);
                        System.out.println("st realizado");

                        ps.setString(1, nombreProducto);
                        ps.setFloat(2, precio);
                        ps.setInt(3, stock);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Dime el ID del cliente");
                    int idCliente = sc.nextInt();

                    //insertaremos un nuevo pedido con fecha actual
                    consulta = "INSERT INTO pedido (fecha,id_cliente) VALUES (?, ?)";

                    //insertar un nuevo pedido
                    try {

                        PreparedStatement ps = sbConector.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
                        System.out.println("st realizado");

                        ps.setDate(1, Date.valueOf(LocalDate.now()));
                        ps.setFloat(2, idCliente);
                        ps.executeUpdate();

                        System.out.println("pedido insertado");
                        //ultimo id_producto introducido
                        ResultSet rs = ps.getGeneratedKeys();
                        int idPedido = -1;
                        if (rs.next()) {
                            idPedido = rs.getInt(1);
                            System.out.println("pedido con ID" + idPedido);

                        } else {
                            System.out.println("Error");
                        }

                        //insertar multiples productos
                        boolean continuar = true;
                        while (continuar) {

                            System.out.println("dime la ID del producto");
                            int IDProductos = sc.nextInt();
                            System.out.println("Dime la canrtidad de productos");
                            int cantidadProductos = sc.nextInt();

                            consulta = "INSERT INTO pedido_producto (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
                            ps = sbConector.prepareStatement(consulta);
                            ps.setInt(1, idPedido);
                            ps.setInt(2, IDProductos);
                            ps.setInt(3, cantidadProductos);
                            ps.executeUpdate();

                            consulta = "update Producto set stock = stock -? where id_producto = ?";
                            ps = sbConector.prepareStatement(consulta);
                            ps.setInt(1, cantidadProductos);
                            ps.setInt(2, IDProductos);
                            ps.executeUpdate();

                            System.out.println("desea anadir otro producto");
                            String respuesta = sc.next();
                            if (respuesta.equalsIgnoreCase("no")) {
                                continuar = false;
                            }
                        }
                        System.out.println("pedido resgistrado");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:
                    //Consultar detalles de un pedido por ID

                    System.out.println("Introduce el ID del pedido");
                    int idPedido = sc.nextInt();

                    consulta = "SELECT c.Nombre, c.ciudad "
                            + "FROM Cliente c JOIN Pedido p ON c.id_cliente = p.id_cliente "
                            + "WHERE p.id_pedido = ?";
                    PreparedStatement ps = sbConector.prepareStatement(consulta);
                    ps.setInt(1, idPedido);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String nombreCliente = rs.getString("Nombre");
                        String ciudadCliente = rs.getString("ciudad");
                        System.out.println("Cliente: " + nombreCliente);
                        System.out.println("Ciudad: " + ciudadCliente);

                        // Consultar la fecha del pedido
                        consulta = "SELECT fecha FROM Pedido WHERE id_pedido = ?";
                        ps = sbConector.prepareStatement(consulta);
                        ps.setInt(1, idPedido);
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            Date fecha = rs.getDate("fecha");
                            System.out.println("Fecha del pedido: " + fecha);
                        } else {
                            System.out.println("No se encontró la fecha para el ID de pedido proporcionado.");
                            return;
                        }

                        //Consultar los productos en el pedido (nombre, cantidad, precio)
                        consulta = "SELECT p.nombre, pp.cantidad, p.precio "
                                + "FROM Producto p JOIN pedido_producto pp ON p.id_producto = pp.id_producto "
                                + "WHERE pp.id_pedido = ?";
                        ps = sbConector.prepareStatement(consulta);
                        ps.setInt(1, idPedido);
                        rs = ps.executeQuery();

                        double totalPedido = 0;
                        System.out.println("Productos en el pedido:");
                        while (rs.next()) {
                            nombreProducto = rs.getString("nombre");
                            int cantidad = rs.getInt("cantidad");
                            double preciO = rs.getDouble("precio");
                            double subtotal = cantidad * preciO;
                            totalPedido += subtotal;
                            System.out.printf("%s (Cantidad: %d, Precio: %.2f) - Subtotal: %.2f\n",
                                    nombreProducto, cantidad, preciO, subtotal);
                        }

                        // Mostrar el total del pedido
                        System.out.printf("Total del pedido: %.2f\n", totalPedido);
                    } else {
                        System.out.println("No se encontró el cliente para el ID de pedido proporcionado.");

                    }

                    break;
                case 5:
                    System.out.println("Dime el ID del pedido");
                    int idPedidoo = sc.nextInt();
                    System.out.println("Dime el ID del producto");
                    int idProdcuto = sc.nextInt();

                    consulta = "DELETE FROM pedido_producto WHERE id_pedido = ? AND id_producto = ?";
                    ps = sbConector.prepareStatement(consulta);
                    ps.setInt(1, idPedidoo);
                    ps.setInt(2, idProdcuto);
                    int filaseliminadas = ps.executeUpdate();

                    if (filaseliminadas > 0) {
                        System.out.println("produco eliminado exitosamente del pedido");
                    } else {

                        System.out.println("erro en la eliminacion de filas");
                    }

                    break;
                case 6:
                    System.out.println("saliendo.......");
                    bucle = !bucle;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

}
