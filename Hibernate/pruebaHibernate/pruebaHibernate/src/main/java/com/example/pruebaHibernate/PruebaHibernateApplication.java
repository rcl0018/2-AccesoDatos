package com.example.pruebaHibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

public class PruebaHibernateApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// carga la configuracion de HIbernate desde el archivo "hibernate.cfg.xml", lee
		// el archivo y carga toda las propiedades: conexion, dfialecto,mapeos,etc

		// SessionFactory sessionFactory = new
		// Configuration().configure().buildSessionFactory();

		// Abrir una nueva sesion
		// Session session = sessionFactory.openSession();

		// Iniciar transaccion
		// session.beginTransaction();

		// Customer customer1 = new Customer("pedro", "manuel");
		/// Pedido pedido1 = new Pedido();
		// Producto producto1 = new Producto("tele", 20d);
		/// order ordern1 = new order();

		// Almacenar el cliente en la base de datos
		// session.persist(customer1);
		// session.persist(producto1);
		// session.persist(pedido1);
		// persist(ordern1);

		// confirmar y cerra
		// session.getTransaction().commit();
		// session.close();
		// System.out.println("se ha cerrado el ciente");
		boolean activo = true;
		while (activo) {

			System.out.println("1. crear cliente");
			System.out.println("2. Crear un producto");
			System.out.println("3. Crear un pedido");

			System.out.println("4. Listar pedidos de un cliente");
			System.out.println("5. Actualizar  el precio de un producto");
			System.out.println("6. Borrar pedidos");
			System.out.println("7. Salir");

			int respuesta = sc.nextInt();
			switch (respuesta) {

				case 1:
					System.out.println("dime el nombre y apellido del cliente");
					String nombre = sc.next();
					String apellido = sc.next();

					SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
					Session session = sessionFactory.openSession();
					session.beginTransaction();

					Customer customer1 = new Customer();
					customer1.setFirstName(nombre);
					customer1.setLastName(apellido);
					session.persist(customer1);
					session.getTransaction().commit();
					session.close();
					System.out.println("se hacerrado el cliente");

					break;
				case 2:
					System.out.println("Dime el nombre del producto");
					String nombreProducto = sc.next();
					System.out.println("Dime el el precio del producto");
					Double precioProducto = sc.nextDouble();

					sessionFactory = new Configuration().configure().buildSessionFactory();
					session = sessionFactory.openSession();
					session.beginTransaction();

					Producto producto1 = new Producto();
					producto1.setNombre(nombreProducto);
					producto1.setPrecio(precioProducto);
					session.persist(producto1);
					session.getTransaction().commit();
					session.close();
					System.out.println("se hacerrado el producto");

					break;
				case 3:
					sessionFactory = new Configuration().configure().buildSessionFactory();
					session = sessionFactory.openSession();
					session.beginTransaction();
					System.out.print("Introduce la cantidad: ");
					int cantidad = sc.nextInt();

					System.out.print("Introduce el ID del cliente: ");
					int idCliente = sc.nextInt();

					Customer c = session.get(Customer.class, idCliente);

					System.out.print("Introduce el ID del producto: ");
					int idProducto = sc.nextInt();

					Producto p = session.get(Producto.class, idProducto);

					Double importe = p.getPrecio() * cantidad;

					Date fechaActual = new Date(System.currentTimeMillis());
					order pedido1 = new order(fechaActual, cantidad, importe, c, p);
					session.persist(pedido1);

					session.getTransaction().commit();
					session.close();

					break;
				case 4:
					sessionFactory = new Configuration().configure().buildSessionFactory();
					session = sessionFactory.openSession();
					session.beginTransaction();

					System.out.println("dime el id del cliente");
					int idClient = sc.nextInt();
					List<order> pedidos = session.createQuery(
							"FROM Order o WHERE o.customer.id = :id ORDER BY o.fecha DESC",
							order.class)
							.setParameter("id", idClient)
							.getResultList();

					for (order o : pedidos) {
						 // cast porque List no es tipada
						System.out.println(
								"Pedido " + o.getId() +
										" - Producto: " + o.getProduct().getNombre() +
										" - Importe: " + o.getImporte());
					}

					break;
				case 5:
					sessionFactory = new Configuration().configure().buildSessionFactory();
					session = sessionFactory.openSession();
					session.beginTransaction();

					System.out.println("introduce el precio del producto");
					double precioPro = sc.nextDouble();

					System.out.println("introduce el id del producto");
					int idProducti = sc.nextInt();

					Query q = session.createQuery(
							"update Producto set precio=:p where id=:idp");
					q.setParameter("p", precioPro);
					q.setParameter("idp", idProducti);
					q.executeUpdate();
					session.getTransaction().commit();
					session.close();

					break;

				case 6:

					sessionFactory = new Configuration().configure().buildSessionFactory();
					session = sessionFactory.openSession();
					session.beginTransaction();

					System.out.println("Dime el id del pedido que quieres borraer");
					int idPedido = sc.nextInt();

					order pedido2 = session.get(order.class, idPedido);
					session.delete(pedido2);
					session.beginTransaction();
					session.getTransaction().commit();
					session.close();


					break;
				case 7:
					System.out.println("salilr");
					activo = false;
					break;

				default:
					break;
			}

		}

	}

}
