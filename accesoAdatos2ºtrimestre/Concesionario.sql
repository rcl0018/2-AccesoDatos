drop type vehiculoType force;
drop type MarcaType force;
drop type CocheType force;

drop table Marca cascade constraints;
drop table Vehiculo cascade constraints;
drop table Coche cascade constraints;



-- crear un tipo objeto llamado VehiculoType
CREATE OR REPLACE TYPE vehiculoType AS OBJECT (
        idvehiculo NUMBER,
        nombre     VARCHAR2(30)
) NOT FINAL; -- permite que otros tipos hereden de este 
/

-- crear un tipo objecto llamado MarcaType
CREATE OR REPLACE TYPE marcatype AS OBJECT (
        idmarca NUMBER,
        nombre  VARCHAR(30)
);
/
-- varray de 3 colores (posiciones)
create or replace type ColoresType as VARRAY(3) of varchar2(20);

-- tabla anidada de reparaciones
create or replace type ReparacionesType as Table of varchar2(50);

-- crear un tipo objecto llamado CocheType
create or replace type CocheType under VehiculoType(
precio number,
marca REF MarcaType,
colores ColoresType,
reparaciones ReparacionesType
);
/

create table Marca of MarcaType(
IdMarca Primary key 
);
/

create table Coche of CocheType(
primary key (IdVehiculo),
SCOPE FOR (marca) IS Marca  -- le dice a Oracle que todas las referecias marcan apunten a filas de la tabla marca
)

nested table reparaciones Store as Reparaciones_Store;

/
insert into marca values (MarcaType(1,'Seat'));
insert into marca values (MarcaType(2,'Peugeot'));
insert into marca values (MarcaType(3,'Volvo_Raul'));
insert into marca values (MarcaType(4,'Suzuki_Raul'));

    INSERT INTO coche
        SELECT
            cochetype(1,
                      'Ibiza',
                      15000,
                      ref(m),
                      colorestype('Rojo', 'Blanco'),
                      reparacionestype('Cambio aceite', 'Pastillas freno'))
        FROM
            marca m  where m.idmarca = 1;
/
    INSERT INTO coche
        SELECT
            cochetype(2,
                      '408',
                      3000,
                      ref(m),
                      colorestype('blanco', 'Negro'),
                      reparacionestype('Correa distribucion', 'freno'))
        FROM
            marca m
        WHERE
            m.idmarca = 2;
/

    INSERT INTO coche
        SELECT
            cochetype(3,
                      's60_Raul',
                      50000,
                      ref(m),
                      colorestype('Gris', 'Negro'),
                      reparacionestype('Cambio aceite', 'pastillas de freno'))
        FROM
            marca m
        WHERE
            m.idmarca = 3;
/

INSERT INTO coche
    SELECT
        cochetype(4,
                  'Vitara_Raul',
                  23000,
                  ref(m),
                  colorestype('Rojo', 'Azul'),
                  reparacionestype('Cambio motor', 'Cambio Ruedas'))
    FROM
        marca m
    WHERE
        m.idmarca = 4;
/

-- ver todas las marcas
select * from Coche;

-- ver coches con nombre de la marca (usando DEREF)
select 
c.idvehiculo,
c.nombre,
c.precio,
DEREF(c.marca).idMarca as idMarca,
DEREF(c.marca).Nombre as NombreMarca
from coche c;

-- ahora ordenar por nombreMarca
select 
c.idvehiculo,
c.nombre,
c.precio,
DEREF(c.marca).Nombre as NombreMarca
from coche c
order by NombreMarca;


-- ver colores de cada coche
select 
c.idvehiculo,
c.nombre,
c.precio,
col.COLUMN_VALUE as Color
from coche c, 
TABLE(c.colores) col
order by c.idVehiculo;

-- ver reparaciones de un coche
select 
c.idvehiculo,
c.nombre,
c.precio,
col.COLUMN_VALUE as Reparaciones
from coche c, 
TABLE(c.reparaciones) col
order by c.idVehiculo;

-- numero de coches por marca 
select 
DEREF(c.marca).nombre as NombreMarca,
COUNT(*) as NumCoches
from coche c
group by DEREF(c.marca).nombre;

-- diferentes marcas
select distinct (DEREF(c.marca).nombre) from coche c;

-- coches de una mrca concreta (por nombre)

-- Colores del coche 1

-- Reparaciones del coche 1
