package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
        public static void main(String[] args) {

            // Configuración de conexión
            String url = "jdbc:oracle:thin:@//localhost:1521/xe";
            String usuario = "concesionario";
            String contraseña = "Davante";
            
            // Conexión a la base de datos
            try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
                System.out.println("Conexión exitosa a la base de datos Oracle!");
                System.out.println("Conectando con la base de datos en " + url);

                Scanner scanner = new Scanner(System.in);
                int opcion;

                    do {
                        // Mostrar menú
                        System.out.println("=== Menú de Opciones ===");
                        System.out.println("1. Crear Marca");
                        System.out.println("2. Mostrar Marcas");
                        System.out.println("3. Número de Marcas");
                        System.out.println("4. Crear Coche");
                        System.out.println("5. Mostar Coche");
                        System.out.println("6. Coches por marca");
                        System.out.println("7. Nº Coches por marca");
                        System.out.println("8. Borrar Coche");
                        System.out.println("9. Borrar Marca");
                        System.out.println("10. Catalogo Concesionario");
                        System.out.println("11. Actualizar Nombre Marca");
                        System.out.println("12. Actualizar Nombre Coche");
                        System.out.println("0. Salir");
                        System.out.print("Elige una opción: ");

                        // Leer la opción del usuario
                        opcion = scanner.nextInt();

                        // Procesar la opción seleccionada
                        switch (opcion) {
                            case 1:
                                createMarca(conn,scanner);
                                break;
                            case 2:
                                readMarca(conn);
                                break;
                            case 3:
                                contarMarcas(conn);
                                break;
                            case 4:
                                createCoche(conn,scanner);
                                break;
                            case 5:
                                readCoche(conn);
                                break;
                            case 6:
                                cocheMarca(conn,scanner);
                                break;
                            case 7:
                                numeroCocheMarca(conn,scanner);
                                break;
                            case 8:
                                DeleteCoche(conn,scanner);
                                break;
                            case 9:
                                DeleteMarca(conn,scanner);
                                break;
                            case 10:
                                CatalogoConcesionario(conn);
                                break;
                            case 11:
                                ActualizarNombreMarca(conn,scanner);
                                break;
                            case 12:
                                ActualizarNombreCoche(conn,scanner);
                                break;
                            case 0:
                                System.out.println("Saliendo del programa...");
                                break;
                            default:
                                System.out.println("Opción no válida. Intenta de nuevo.");
                            }
                            System.out.println(); // Línea en blanco para separar iteraciones
                    } while (opcion != 0);
                    scanner.close();
                    conn.close();
            } catch (Exception e) {
                System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void createMarca(Connection conn,Scanner scanner) {
           
            // Variables para almacenar ID y nombre
            int id;
            String nombre;

            // Solicitar el ID (número entero)
            System.out.print("Introduce el ID (número entero): ");
            id = scanner.nextInt();
            scanner.nextLine();

            // Verificar si el ID ya existe
            String checkQuery = "SELECT COUNT(*) FROM Marca WHERE IdMarca = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    System.out.println("Error: Ya existe una marca con el ID " + id + ".");
                    return; // Salir del método si el ID ya existe
                }
            } catch (Exception e) {
                System.err.println("Error al verificar la existencia del ID: " + e.getMessage());
                e.printStackTrace();
                return; // Salir del método en caso de error
            }

            // Solicitar el nombre (cadena de texto)
            System.out.print("Introduce el nombre: ");
            nombre = scanner.nextLine();
           
            String query = "INSERT INTO Marca (IdMarca, Nombre) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    // Insertar primer registro
                    pstmt.setInt(1, id);
                    pstmt.setString(2, nombre );
                    pstmt.executeUpdate();

                    System.out.println("Registros insertados correctamente.");
            } catch (Exception e) {
                System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }        
        }

        public static void readMarca(Connection conn) {
           
            String query = "SELECT * FROM Marca";
            try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
                // Obtener información de las columnas
                int columnCount = rs.getMetaData().getColumnCount();

                // Mostrar resultados
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rs.getMetaData().getColumnName(i);
                        Object value = rs.getObject(i);
                        System.out.print(columnName + ": " + value + " | ");
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }          
        }

        public static void contarMarcas(Connection conn) {
            // Consulta parametrizada para contar empleados en un departamento
            String query = "SELECT COUNT(*) FROM Marca";
            
            try {
                // Preparar la consulta
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {                    
                    // Ejecutar la consulta
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            // Obtener el resultado de COUNT(*)
                            int totalMarcas = rs.getInt(1);
                            System.out.println("Número total de marcas: " + totalMarcas);
                        } else {
                            System.out.println("La consulta no devolvió resultados.");
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void createCoche(Connection conn,Scanner scanner) {
           
            int id;
            String nombre;
            int precio;
            Object marcaRef = null;

            try {
                // Pedir al usuario que introduzca el ID de la marca
                System.out.print("Introduce el ID de la marca: ");
                int idMarca = scanner.nextInt();

                // Obtener la referencia de la marca con el ID proporcionado por el usuario
                String sqlSelectRef = "SELECT REF(m) FROM Marca m WHERE m.IdMarca = ?";
                PreparedStatement selectRefStmt = conn.prepareStatement(sqlSelectRef);
                selectRefStmt.setInt(1, idMarca);
                ResultSet rs = selectRefStmt.executeQuery();

                if (rs.next()) {
                    marcaRef = rs.getObject(1); // Obtener la referencia de la marca
                    String query = "INSERT INTO Coche (IdVehiculo, Nombre, Precio, Marca ) VALUES (?, ?,?,?)";
                    
                    // Solicitar el ID (número entero)
                    System.out.print("Introduce el ID (número entero): ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    // Solicitar el nombre (cadena de texto)
                    System.out.print("Introduce el nombre: ");
                    nombre = scanner.nextLine();

                    // Solicitar el precio 
                    System.out.print("Introduce el precio: ");
                    precio = scanner.nextInt();
                    scanner.nextLine();

                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            // Insertar primer registro
                            pstmt.setInt(1, id);
                            pstmt.setString(2, nombre );
                            pstmt.setInt(3, precio );
                            pstmt.setObject(4, marcaRef );
                            pstmt.executeUpdate();

                            System.out.println("Registros insertados correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
                        e.printStackTrace();
                    } 
                }
                rs.close();
                selectRefStmt.close();

                if (marcaRef == null) {
                    System.out.println("No se encontró la marca con el ID: " + idMarca);
                    return;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }      
        }

        public static void readCoche(Connection conn) {
            // Consulta con JOIN para obtener el nombre de la marca
            String query = "SELECT c.IdVehiculo, c.Nombre, c.Precio, DEREF(c.Marca).Nombre AS NombreMarca " +
                           "FROM Coche c " +
                           "LEFT JOIN Marca m ON DEREF(c.Marca).IdMarca = m.IdMarca";
        
            try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
                // Mostrar resultados
                while (rs.next()) {
                    int idVehiculo = rs.getInt("IdVehiculo");
                    String nombreCoche = rs.getString("Nombre");
                    double precio = rs.getDouble("Precio");
                    String nombreMarca = rs.getString("NombreMarca");
        
                    System.out.println("IDVEHICULO: " + idVehiculo + " | " +
                                       "NOMBRE: " + nombreCoche + " | " +
                                       "PRECIO: " + precio + " | " +
                                       "MARCA: " + nombreMarca);
                }
            } catch (Exception e) {
                System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void cocheMarca(Connection conn, Scanner scanner) {
            Object marcaRef = null;
        
            try {
                // Pedir al usuario que introduzca el ID de la marca
                System.out.print("Introduce el ID de la marca: ");
                int idMarca = scanner.nextInt();
        
                // Obtener la referencia de la marca con el ID proporcionado por el usuario
                String sqlSelectRef = "SELECT REF(m) FROM Marca m WHERE m.IdMarca = ?";
                PreparedStatement selectRefStmt = conn.prepareStatement(sqlSelectRef);
                selectRefStmt.setInt(1, idMarca);
                ResultSet rs = selectRefStmt.executeQuery();
        
                if (rs.next()) {
                    marcaRef = rs.getObject(1); // Obtener la referencia de la marca
                    System.out.println("Ref de la marca: " + marcaRef);
        
                    // Ahora que tenemos la referencia, consulta los coches asociados
                    String query = "SELECT * FROM Coche WHERE Marca = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setObject(1, marcaRef); // Asignar la referencia de la marca en la consulta
        
                        // Ejecutar la consulta para obtener los coches
                        ResultSet cocheRS = pstmt.executeQuery();
        
                        // Obtener información de las columnas
                        int columnCount = cocheRS.getMetaData().getColumnCount();
        
                        // Mostrar resultados
                        while (cocheRS.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                String columnName = cocheRS.getMetaData().getColumnName(i);
                                Object value = cocheRS.getObject(i);
                                System.out.print(columnName + ": " + value + " | ");
                            }
                            System.out.println();
                        }
                        cocheRS.close(); // Cerrar el ResultSet de los coches
                    } catch (SQLException e) {
                        System.err.println("Error al ejecutar la consulta de coches: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No se encontró la marca con el ID: " + idMarca);
                }
                
                rs.close(); // Cerrar el ResultSet de la referencia
                selectRefStmt.close(); // Cerrar el PreparedStatement de la referencia
        
            } catch (SQLException e) {
                System.err.println("Error de base de datos: " + e.getMessage());
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        }

        public static void numeroCocheMarca(Connection conn, Scanner scanner) {
            Object marcaRef = null;
        
            try {
                // Pedir al usuario que introduzca el ID de la marca
                System.out.print("Introduce el ID de la marca: ");
                int idMarca = scanner.nextInt();
        
                // Obtener la referencia de la marca con el ID proporcionado por el usuario
                String sqlSelectRef = "SELECT REF(m) , nombre FROM Marca m WHERE m.IdMarca = ?";
                PreparedStatement selectRefStmt = conn.prepareStatement(sqlSelectRef);
                selectRefStmt.setInt(1, idMarca);
                ResultSet rs = selectRefStmt.executeQuery();
        
                if (rs.next()) {
                    marcaRef    =   rs.getObject(1); // Obtener la referencia de la marca
                    String nombreMarca =   rs.getString(2);
                    System.out.println("Ref de la marca: " + marcaRef);

                    String query = "SELECT Count(*) FROM Coche WHERE Marca = ?";
                    try {
                        // Preparar la consulta para contar coches de la marca
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setObject(1, marcaRef); // Asignar la referencia de la marca como parámetro en la consulta

                            // Ejecutar la consulta
                            try (ResultSet rs1 = pstmt.executeQuery()) {
                                if (rs1.next()) {
                                    // Obtener el resultado de COUNT(*)
                                    int totalcochesMarcas = rs1.getInt(1);
                                    System.out.println("Marca " + nombreMarca  + " tiene: " + totalcochesMarcas + " coches.");
                                } else {
                                    System.out.println("La consulta no devolvió resultados.");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No se encontró la marca con el ID: " + idMarca);
                }
                
                rs.close(); // Cerrar el ResultSet de la referencia
                selectRefStmt.close(); // Cerrar el PreparedStatement de la referencia
        
            } catch (SQLException e) {
                System.err.println("Error de base de datos: " + e.getMessage());
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        }

        public static void DeleteCoche(Connection conn, Scanner scanner) {
            // Solicitar el ID del coche a borrar
            System.out.print("Introduce el ID del coche a borrar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        
            // Consulta para verificar si el coche existe
            String checkQuery = "SELECT COUNT(*) FROM Coche WHERE IdVehiculo = ?";
            // Consulta para borrar el coche
            String deleteQuery = "DELETE FROM Coche WHERE IdVehiculo = ?";
        
            try (
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)
            ) {
                // Verificar si el coche existe
                checkStmt.setInt(1, id);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // El coche existe, proceder con su eliminación
                        deleteStmt.setInt(1, id);
                        int rowsDeleted = deleteStmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Coche con ID " + id + " eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el coche con ID " + id + ".");
                        }
                    } else {
                        System.out.println("El coche con ID " + id + " no existe.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al intentar borrar el coche: " + e.getMessage());
                e.printStackTrace();
            }
        }


        public static void DeleteMarca(Connection conn, Scanner scanner) {
            // Solicitar el ID de la marca a borrar
            System.out.print("Introduce el ID de la marca a borrar: ");
            int idMarca = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        
            // Consulta para verificar si la marca existe
            String checkMarcaQuery = "SELECT COUNT(*) FROM Marca WHERE IdMarca = ?";
            // Consulta para obtener el REF de la marca
            String getMarcaRefQuery = "SELECT REF(m) FROM Marca m WHERE m.IdMarca = ?";
            // Consulta para borrar los coches asociados a la marca
            String deleteCochesQuery = "DELETE FROM Coche WHERE Marca = ?";
            // Consulta para borrar la marca
            String deleteMarcaQuery = "DELETE FROM Marca WHERE IdMarca = ?";
        
            try (
                PreparedStatement checkMarcaStmt = conn.prepareStatement(checkMarcaQuery);
                PreparedStatement getMarcaRefStmt = conn.prepareStatement(getMarcaRefQuery);
                PreparedStatement deleteCochesStmt = conn.prepareStatement(deleteCochesQuery);
                PreparedStatement deleteMarcaStmt = conn.prepareStatement(deleteMarcaQuery)
            ) {
                // Verificar si la marca existe
                checkMarcaStmt.setInt(1, idMarca);
                try (ResultSet rs = checkMarcaStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // La marca existe, proceder con la obtención del REF de la marca
                        getMarcaRefStmt.setInt(1, idMarca);
                        try (ResultSet marcaResult = getMarcaRefStmt.executeQuery()) {
                            if (marcaResult.next()) {
                                // Obtener el REF de la marca
                                java.sql.Ref marcaRef = marcaResult.getRef(1);
        
                                // Eliminar los coches asociados a la marca utilizando el REF
                                deleteCochesStmt.setRef(1, marcaRef);
                                int cochesDeleted = deleteCochesStmt.executeUpdate();
                                System.out.println("Coches asociados eliminados: " + cochesDeleted);
        
                                // Eliminar la marca
                                deleteMarcaStmt.setInt(1, idMarca);
                                int marcaDeleted = deleteMarcaStmt.executeUpdate();
                                if (marcaDeleted > 0) {
                                    System.out.println("Marca con ID " + idMarca + " eliminada correctamente.");
                                } else {
                                    System.out.println("No se pudo eliminar la marca con ID " + idMarca + ".");
                                }
                            } else {
                                System.out.println("No se pudo obtener el REF de la marca.");
                            }
                        }
                    } else {
                        System.out.println("La marca con ID " + idMarca + " no existe.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al intentar borrar la marca y los coches asociados: " + e.getMessage());
                e.printStackTrace();
            }
        }


        public static void CatalogoConcesionario(Connection conn) {
            // Consulta para obtener vehículos por marca
            String query = "SELECT DEREF(c.Marca).Nombre AS NombreMarca, c.IDVEHICULO, c.NOMBRE, c.PRECIO " +
                           "FROM   Coche c " +
                           "LEFT JOIN   Marca m ON DEREF(c.Marca).IdMarca = m.IdMarca " +
                           "ORDER BY m.Nombre, c.IDVEHICULO";
  
            try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
                String currentMarca = null;
                
                // Iterar sobre los resultados
                while (rs.next()) {
                    String marca = rs.getString("NombreMarca");
                    int idVehiculo = rs.getInt("IDVEHICULO");
                    String nombreCoche = rs.getString("NOMBRE");
                    double precio = rs.getDouble("PRECIO");
        
                    // Verificar si la marca ha cambiado
                    if (currentMarca == null || !currentMarca.equals(marca)) {
                        // Si la marca es diferente, imprimir el nombre de la marca
                        System.out.println("Marca " + marca);
                        currentMarca = marca; // Actualizar la marca actual
                    }
        
                    // Mostrar el vehículo
                    System.out.println("IDVEHICULO: " + idVehiculo + " | NOMBRE: " + nombreCoche + " | PRECIO: " + precio);
                }
            } catch (Exception e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }


        public static void ActualizarNombreMarca(Connection conn, Scanner scanner) {
            // Solicitar el ID de la marca que se desea actualizar
            System.out.print("Introduce el ID de la marca a actualizar: ");
            int idMarca = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner
        
            // Consulta para verificar si la marca existe
            String checkMarcaQuery = "SELECT COUNT(*) FROM Marca WHERE IdMarca = ?";
            // Consulta para actualizar el nombre de la marca
            String updateMarcaQuery = "UPDATE Marca SET Nombre = ? WHERE IdMarca = ?";
        
            try (
                PreparedStatement checkMarcaStmt = conn.prepareStatement(checkMarcaQuery);
                PreparedStatement updateMarcaStmt = conn.prepareStatement(updateMarcaQuery)
            ) {
                // Verificar si la marca existe
                checkMarcaStmt.setInt(1, idMarca);
                try (ResultSet rs = checkMarcaStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Si la marca existe, pedir el nuevo nombre
                        System.out.print("Introduce el nuevo nombre de la marca: ");
                        String nuevoNombre = scanner.nextLine();
        
                        // Actualizar el nombre de la marca
                        updateMarcaStmt.setString(1, nuevoNombre);
                        updateMarcaStmt.setInt(2, idMarca);
                        int rowsAffected = updateMarcaStmt.executeUpdate();
        
                        if (rowsAffected > 0) {
                            System.out.println("Marca actualizada correctamente.");
                        } else {
                            System.out.println("No se pudo actualizar la marca.");
                        }
                    } else {
                        System.out.println("La marca con ID " + idMarca + " no existe.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al intentar actualizar el nombre de la marca: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void ActualizarNombreCoche(Connection conn, Scanner scanner) {
            // Solicitar el ID de la marca que se desea actualizar
            System.out.print("Introduce el ID del nombre coche a actualizar: ");
            int idMarca = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner
        
            // Consulta para verificar si la marca existe
            String checkMarcaQuery = "SELECT COUNT(*) FROM Coche WHERE IdVehiculo = ?";
            // Consulta para actualizar el nombre de la marca
            String updateMarcaQuery = "UPDATE Coche SET Nombre = ? WHERE IdVehiculo = ?";
        
            try (
                PreparedStatement checkMarcaStmt = conn.prepareStatement(checkMarcaQuery);
                PreparedStatement updateMarcaStmt = conn.prepareStatement(updateMarcaQuery)
            ) {
                // Verificar si la marca existe
                checkMarcaStmt.setInt(1, idMarca);
                try (ResultSet rs = checkMarcaStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Si la marca existe, pedir el nuevo nombre
                        System.out.print("Introduce el nuevo nombre de la marca: ");
                        String nuevoNombre = scanner.nextLine();
        
                        // Actualizar el nombre de la marca
                        updateMarcaStmt.setString(1, nuevoNombre);
                        updateMarcaStmt.setInt(2, idMarca);
                        int rowsAffected = updateMarcaStmt.executeUpdate();
        
                        if (rowsAffected > 0) {
                            System.out.println("Marca actualizada correctamente.");
                        } else {
                            System.out.println("No se pudo actualizar la marca.");
                        }
                    } else {
                        System.out.println("La marca con ID " + idMarca + " no existe.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al intentar actualizar el nombre de la marca: " + e.getMessage());
                e.printStackTrace();
            }
        }
}