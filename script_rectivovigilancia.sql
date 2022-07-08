
CREATE TABLE [Sivicos].[dbo].reactivo_reporte_usuario (
	nombre varchar(255),
	sexo varchar(1),
	edad varchar(3),
	edad_en varchar(1),
	direccion varchar(255),
	telefono varchar(15),
	pais varchar(15),
	departamento varchar(15),
	ciudad varchar(15),
	correo varchar(60),
	nombre_reactivo varchar(255),
	nombre_comercial_reactivo varchar(255),
	registro_reactivo varchar(100),
	lote_reactivo varchar(100),
	referencia_reactivo varchar(100),
	fabricante_reactivo varchar(255),
	importador_reactivo varchar(255),
    fecha_evento datetime2(3),
	fecha_elaboracion datetime2(3),
	deteccion varchar(1),
	descripcion_evento varchar(255),
	id numeric (18,0) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE [Sivicos].[dbo].reactivo_seguimiento (
    fecha_ingreso datetime2(3) NOT NULL,
    usuario varchar(255) NOT NULL,
    observacion text,
    id_reporte varchar(255),
    categoria varchar(255),
	id numeric (18,0) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO [dbo].[reactivo_menus]
           ([mostrar_menu]
           ,[solucion_menu]
           ,[accion_menu]
           ,[descripcion_menu]
           ,[url_menu]
           ,[permitido_menu]
           ,[idopcion])
     VALUES
           ('S'
           ,'E'
           ,''
           ,'Editar Usuarios Internet'
           ,'pages/EditarUsuarioInternet.xhtml'
           , 1
           ,15);
		   
INSERT INTO [dbo].[reactivo_roles_menu]
           ([ID_OPCION_MENU]
           ,[ID_ROL])
     VALUES
           (15
           ,1);
		   
INSERT INTO [dbo].[reactivo_menus]
           ([mostrar_menu]
           ,[solucion_menu]
           ,[accion_menu]
           ,[descripcion_menu]
           ,[url_menu]
           ,[permitido_menu]
           ,[idopcion])
     VALUES
           ('S'
           ,'E'
           ,''
           ,'Editar Usuarios Red Nacional'
           ,'pages/EditarUsuarioRed.xhtml'
           , 1
           ,16);
INSERT INTO [dbo].[reactivo_roles_menu]
           ([ID_OPCION_MENU]
           ,[ID_ROL])
     VALUES
           (16
           ,1);
		   
INSERT INTO [dbo].[reactivo_menus]
           ([mostrar_menu]
           ,[solucion_menu]
           ,[accion_menu]
           ,[descripcion_menu]
           ,[url_menu]
           ,[permitido_menu]
           ,[idopcion])
     VALUES
           ('S'
           ,'E'
           ,''
           ,'Consulta Reporte Usuario'
           ,'consultas/consultaReporteUsuario.xhtml'
           , 1
           ,17);
		   
INSERT INTO [dbo].[reactivo_roles_menu]
           ([ID_OPCION_MENU]
           ,[ID_ROL])
     VALUES
           (17
           ,1);