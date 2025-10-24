drop database if exists tienda_db;
create database tienda_db;
use tienda_db;

create table Cliente(
id_cliente int auto_increment primary key,
Nombre varchar(250),
edad varchar(20),
ciudad varchar(100)
);

create table Pedido(
id_pedido int auto_increment primary key,
fecha date,
id_cliente int,
foreign key(id_cliente) references Cliente(id_cliente)
);

create table Producto(
id_producto int auto_increment primary key,
precio decimal(10,2),
nombre varchar(250),
stock int
);
create table pedido_producto(
id_pedido int,
id_producto int,
cantidad int,
primary key(id_pedido, id_producto),
foreign key (id_pedido) references pedido(id_pedido),
foreign key(id_producto) references producto(id_producto)
);


-- Manipulacion de datos (DML insert, delete,select)
insert into Cliente (nombre,ciudad,edad) values
('Ana Lopez','Madrid',30),
('CarlosRuiz','Sevilla',45),
('Lucia Gomez','Granada',22);


insert into Producto(nombre,precio,stock) values
('Portatil',800.00,2),
('Raton',10,50),
('Teclado',20,50);

insert into Pedido(fecha,id_cliente) values
('2024-01-10',1),
('2024-01-11',2),
('2025-01-12',3),
('2025-05-05',1);

insert into pedido_producto(id_pedido,id_producto,cantidad) values
(1,1,1), -- Ana compa un portatil
(1,2,2),  -- Ana compra 2 ratones
(2,3,2), -- Calos compra 2 teclados
(3,1,1), -- Lucia compra 1 poratatil
(4,3,5); -- Ana compra 5 teclados


-- Cambiar el stock de los portatiles
update Producto set stock = 10 where id_producto=1;

-- Mostrar nombre y ciudad de los clientes mayores de 26 años
select * from Cliente where edad>26;
-- Clientes cuyo nombre empieza por 'L'
select nombre from Cliente where nombre like 'L%';
-- Clientes entre 25 y 45 ordenar alfabeticamente
select * from Cliente where edad between 25 and 45 order by nombre;
-- Productos  con precio menor a 50€ con precio descendiente
select * from Producto where precio <=50 order by precio desc;

-- Mostrar datos de los pedidos con el nombre y ciudad del cliente
select Cliente.nombre,Cliente.ciudad,Pedido.*from Pedido join Cliente on Pedido.id_cliente = Cliente.id_cliente;

-- total gastado por cada cliente
select Cliente.nombre,sum(pedido_producto.cantidad * Producto.precio) as total from Cliente
join Pedido on Cliente.id_cliente = Pedido.id_cliente
join pedido_producto on pedido_producto.id_pedido = Pedido.id_pedido
join Producto on Producto.id_producto = pedido_producto.id_producto
Group by Cliente.nombre having total>100

-- 

