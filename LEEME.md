# Plantilla de Login (JavaFX + Hibernate + MySQL)

Proyecto creado desde el arquetipo PlantillaLoginU.
Incluye: login con correo y contraseña, contraseñas en MD5 (HashUtil),
sesión del usuario (SesionUsuario) y pantalla Home.

## Pasos al crear un proyecto nuevo

### 1. Base de datos
- [ ] Abrir `crear_bd.sql`, cambiar el nombre de la base si hace falta
      y ejecutarlo en phpMyAdmin (pestaña SQL).
- [ ] Cambiar la contraseña del usuario de prueba antes de ejecutarlo.

### 2. hibernate.cfg.xml (src/main/resources)
- [ ] URL: cambiar el nombre de la base al final de
      `jdbc:mysql://localhost:3306/NOMBRE_BASE`
- [ ] Usuario de MySQL (en XAMPP normalmente es `root`).
- [ ] Contraseña de MySQL (en XAMPP normalmente va vacía).

### 3. Entidad Usuario (paquete entidades)
- [ ] Si la tabla tiene otro nombre, cambiar `@Table(name = "...")`.
- [ ] Si las columnas tienen otros nombres, cambiar cada `@Column(name = "...")`.

### 4. UsuarioDAO (paquete dao)
- [ ] La consulta HQL usa los ATRIBUTOS de la clase Usuario
      (Correo, Clave, Estado). Si se renombran en la entidad,
      cambiarlos también aquí.
- [ ] Solo entran usuarios con Estado = 'ACTIVO'.

### 5. Ventana
- [ ] Cambiar el título de la ventana en App.java (setTitle).

### 6. Probar
- [ ] Ejecutar con clic derecho sobre el proyecto -> Run.
- [ ] Iniciar sesión con el usuario de prueba.

## Recordatorios
- La base de datos guarda el HASH MD5, nunca la contraseña real.
- Para crear o cambiar una clave desde phpMyAdmin:
  UPDATE tabla SET Clave = MD5('nueva_clave') WHERE id = X;
- Si el login falla, comparar cada condición con:
  SELECT Correo = '...' AS correo_ok, Clave = MD5('...') AS clave_ok,
         Estado = 'ACTIVO' AS estado_ok FROM tabla WHERE id = X;
