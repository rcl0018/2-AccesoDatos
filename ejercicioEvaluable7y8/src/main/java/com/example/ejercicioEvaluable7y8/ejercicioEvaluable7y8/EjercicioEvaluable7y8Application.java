package com.example.ejercicioEvaluable7y8.ejercicioEvaluable7y8;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

public class EjercicioEvaluable7y8Application {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Scanner sc = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			System.out.println("\n--- MENÚ AUTORES ---");
			System.out.println("1. Crear autor");
			System.out.println("2. Listar autores");
			System.out.println("3. Buscar autor por ID");
			System.out.println("4. Actualizar autor");
			System.out.println("5. Eliminar autor");
			System.out.println("6. Salir");
			System.out.print("Elige una opción: ");
			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {

				// Crear autor
				case 1: {
					System.out.print("Nombre: ");
					String nombre = sc.nextLine();
					System.out.print("Nacionalidad: ");
					String nacionalidad = sc.nextLine();
					System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
					String fecha = sc.nextLine();

					Autor autor = new Autor();
					autor.setNombre(nombre);
					autor.setNacionalidad(nacionalidad);
					autor.setFechaNacimiento(Date.valueOf(fecha));

					Session session = factory.openSession();
					session.beginTransaction();
					session.persist(autor);
					session.getTransaction().commit();
					session.close();
					System.out.println("Autor creado con éxito.");
					break;
				}

				// crear Libro
				case 2: {
					// Pedir al usuario el título del libro
					System.out.print("Título del libro: ");
					String titulo = sc.nextLine();

					// Pedir al usuario el año de publicación
					System.out.print("Año de publicación: ");
					int anio = sc.nextInt();
					sc.nextLine(); // Consumir el salto de línea pendiente

					// Abrir una sesión de Hibernate
					Session session = factory.openSession();

					// Obtener la lista de todos los autores existentes en la base de datos
					List<Autor> autores = session.createQuery("from Autor", Autor.class).list();

					// Mostrar la lista de autores para que el usuario seleccione uno
					System.out.println("Selecciona el ID del autor:");
					for (Autor a : autores) {
						System.out.println(a.getIdAutor() + " - " + a.getNombre());
					}

					// Leer el ID del autor seleccionado por el usuario
					int idAutor = sc.nextInt();
					sc.nextLine(); //  salto de línea 

					// Obtener el objeto Autor correspondiente al ID seleccionado
					Autor autor = session.get(Autor.class, idAutor);

					// Crear un nuevo objeto Libro y asignarle los valores 
					Libro libro = new Libro();
					libro.setTitulo(titulo);
					libro.setAnioPublicacion(anio);
					libro.setAutor(autor); 
					libro.setGeneros(new HashSet<>()); //  colección de géneros 

					// Iniciar la transacción, guardar el libro y confirmar la transacción
					session.beginTransaction();
					session.persist(libro);
					session.getTransaction().commit();

					// Cerrar la sesión de Hibernate
					session.close();

					// Informar al usuario que el libro se creó correctamente
					System.out.println("Libro creado con éxito.");
					

				}
					break;
					// crear genero y asignarselo a un Libro 

				case 3: {
					System.out.print("Nombre del género: ");
					String nombreGenero = sc.nextLine();

					Session session = factory.openSession();

					// Crear el género
					Genero genero = new Genero();
					genero.setNombre(nombreGenero);
					genero.setLibros(new HashSet<>());

					session.beginTransaction();
					session.persist(genero);
					session.getTransaction().commit();

					// Listar libros disponibles para asignar
					List<Libro> libros = session.createQuery("from Libro", Libro.class).list();
					if (libros.isEmpty()) {
						System.out.println("No hay libros registrados. Crea un libro primero.");
						session.close();
						break;
					}

					System.out.println("Selecciona el ID del libro al que asignar el género:");
					for (Libro l : libros) {
						System.out.println(l.getIdLibro() + " - " + l.getTitulo());
					}
					int idLibro = sc.nextInt();
					sc.nextLine();
					Libro libro = session.get(Libro.class, idLibro);

					// Asignar relación muchos-a-muchos
					genero.getLibros().add(libro);
					libro.getGeneros().add(genero);

					// Guardar la relación
					session.beginTransaction();
					session.update(genero);
					session.update(libro);
					session.getTransaction().commit();
					session.close();

					System.out.println("Género creado y asignado al libro con éxito.");
					break;
				}

				// Listar todos los autores
				case 4: {
					Session session = factory.openSession();
					List<Autor> autores = session.createQuery("from Autor", Autor.class).list();
					session.close();
					System.out.println("\nID | Nombre | Nacionalidad | Fecha de nacimiento");
					for (Autor a : autores) {
						System.out.println(a.getIdAutor() + " | " + a.getNombre() + " | " +
								a.getNacionalidad() + " | " + a.getFechaNacimiento());
					}
					break;
				}

				// Buscar autor por ID
				case 5: {
					System.out.print("ID del autor: ");
					int id = sc.nextInt();
					sc.nextLine();
					Session session = factory.openSession();
					Autor autor = session.get(Autor.class, id);
					session.close();
					if (autor != null) {
						System.out.println(autor.getIdAutor() + " | " + autor.getNombre() + " | " +
								autor.getNacionalidad() + " | " + autor.getFechaNacimiento());
					} else {
						System.out.println("Autor no encontrado.");
					}
					break;
				}

				// Actualizar autor
				case 6: {
					System.out.print("ID del autor a actualizar: ");
					int id = sc.nextInt();
					sc.nextLine();
					Session session = factory.openSession();
					Autor autor = session.get(Autor.class, id);
					if (autor != null) {
						System.out.print("Nuevo nombre: ");
						autor.setNombre(sc.nextLine());
						System.out.print("Nueva nacionalidad: ");
						autor.setNacionalidad(sc.nextLine());
						System.out.print("Nueva fecha de nacimiento (yyyy-mm-dd): ");
						autor.setFechaNacimiento(Date.valueOf(sc.nextLine()));

						session.beginTransaction();
						session.persist(autor);
						session.getTransaction().commit();

						System.out.println("Autor actualizado con éxito.");
					} else {
						System.out.println("Autor no encontrado.");
					}
					session.close();
					break;
				}

				// Eliminar autor
				case 7: {
					System.out.print("ID del autor a eliminar: ");
					int id = sc.nextInt();
					sc.nextLine();
					Session session = factory.openSession();
					Autor autor = session.get(Autor.class, id);
					if (autor != null) {
						session.beginTransaction();
						session.delete(autor);
						session.getTransaction().commit();
						System.out.println("Autor eliminado con éxito.");
					} else {
						System.out.println("Autor no encontrado.");
					}
					session.close();
					break;
				}

				case 8: {
					// consulta HQL
					System.out.print("ID del autor para HQL: ");
					int idAutor = sc.nextInt();
					sc.nextLine();
					Session session = factory.openSession();
					List<Libro> libros = session.createQuery(
							"FROM Libro l WHERE l.autor.idAutor = :idAutor", Libro.class)
							.setParameter("idAutor", idAutor)
							.list();
					session.close();
					System.out.println("\nLibros encontrados (HQL):");
					for (Libro l : libros) {
						System.out.println(l.getTitulo() + " | " + l.getAnioPublicacion());
					}
				}

					break;

				case 9:
					// consulta SQL
					Session session = factory.openSession();

					List<Object[]> resultados = session.createNativeQuery(
							"SELECT l.titulo, g.nombre " +
									"FROM Libro l " +
									"JOIN Libro_Genero lg ON l.id_libro = lg.id_libro " +
									"JOIN Genero g ON lg.id_genero = g.id_genero")
							.list();

					for (Object[] fila : resultados) {
						System.out.println("Libro: " + fila[0] + " | Género: " + fila[1]);
					}

					session.close();

					break;

				case 10:
					salir = true;
					break;

				default:
					System.out.println("Opción inválida.");
			}
		}

		sc.close();
		factory.close();
		System.out.println("Programa finalizado.");
	}

}
