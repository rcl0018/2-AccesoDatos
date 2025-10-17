
package com.mycompany.prueba1sql;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ejercicio1 {
    
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/tienda_db";
        String usuario = "root";
        String password = "Med@c";
        
        //cargar el driver de la base de datos(puede que sea necesario cargar el .jar)
       //Cargar el driver de la base de datos(puede que sea necesario cargar .jar)
        try{
        Class.forName(driver);
       
        //Establecer conexión con mysql
        Connection dbConnection = DriverManager.getConnection(URL, usuario, password);
        System.out.println("Conexion establecida");
       
        //Consultas
        String consulta1 = "SELECT pedido.*, cliente.nombre, cliente.ciudad FROM pedidos\n" +
                          "INNER JOIN cliente ON cliente.id = producto.id_cliente";
        //Crear una declaracion para ejecutar la consulta
        Statement statement = dbConnection.createStatement();
       
        //Ejecutar la consulta y obtener resultados
        ResultSet rs = statement.executeQuery(consulta1);
       
        //Recorrer a través de los resultados
        while(rs.next()){
           int idPedido = rs.getInt("id_pedidos");
           String fecha = rs.getString("fecha");
           String nombreCliente = rs.getString("nombre");
           String ciudadCliente = rs.getString("ciudad");
            System.out.println("ID pedido = " + idPedido + " Fecha = " + fecha);
        }
         
        }catch (ClassNotFoundException e){
            System.out.println("Error" + e.getMessage());
        }catch (SQLException a){
            System.out.println("Error" + a.getMessage());
        }

        
    }
    
}
