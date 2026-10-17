## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   git clone https://github.com/Daniel00503/proyecto-javafx.git

2. Encender MySQL en XAMPP y ejecutar el archivo crear_bd.sql en phpMyAdmin (pestaña SQL).

3. Desde la carpeta del proyecto, copiar la configuración de Hibernate:
   cp hibernate.cfg.example.xml src/main/resources/hibernate.cfg.xml
   Si MySQL tiene contraseña, escribirla en ese archivo. Este archivo no se sube al repositorio.

4. Abrir el proyecto en NetBeans y ejecutarlo con Run.

5. Usuario de prueba: admin@correo.com / admin123

## Forma de trabajo

- La rama main está protegida; nadie sube cambios directamente.
- Cada integrante trabaja en su propia rama y abre un Pull Request.
- Cada Pull Request necesita una aprobación antes de unirse a main.

## Equipo

- Daniel: creador y administrador del repositorio
- Néstor: DAO
- Jaime: interfaz
- Harry: controlador
