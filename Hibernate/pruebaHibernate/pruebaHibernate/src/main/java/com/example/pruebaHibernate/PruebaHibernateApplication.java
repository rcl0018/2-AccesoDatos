package com.example.pruebaHibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class PruebaHibernateApplication {

	public static void main(String[] args) {
		//carga la configuracion de HIbernate desde el archivo  "hibernate.cfg.xml", lee el archivo y carga toda las propiedades: conexion, dfialecto,mapeos,etc

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		//Abrir una nueva sesion
		Session session = sessionFactory.openSession();

		//Iniciar transaccion 
		session.beginTransaction();

		Customer customer1 = new Customer("pedro","manuel");
		Pedido pedido1 = new Pedido();
		Producto producto1 = new Producto("tele", 20d);
		order ordern1 = new order();

		//Almacenar el cliente en la base de datos
		session.persist(customer1);
		session.persist(producto1);
		session.persist(pedido1);
		session.persist(ordern1);
		
		//confirmar y cerra
		session.getTransaction().commit();
		session.close();
		System.out.println("se ha cerrado el ciente");		
	}

}
