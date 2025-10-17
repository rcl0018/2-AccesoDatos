/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Ejercicio1 {
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
        String consulta1 = "select * from Pedido";
        //Crear una declaracion para ejecutar la consulta
        Statement statement = dbConnection.createStatement();
       
        //Ejecutar la consulta y obtener resultados
        ResultSet rs = statement.executeQuery(consulta1);
       
        //Recorrer a través de los resultados
        while(rs.next()){
           int idPedido = rs.getInt("id_pedido");
           String fecha = rs.getString("fecha");
           int idCliente = rs.getInt("id_cliente");
            System.out.println(idPedido + fecha + idCliente);

        }
         
        }catch (ClassNotFoundException e){
            System.out.println("Error" + e.getMessage());
        }catch (SQLException a){
            System.out.println("Error" + a.getMessage());
        }
        
        
        
    }
    
}
