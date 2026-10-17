package renathyu0015.proyectojavafx.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    // Se crea la primera vez que se necesita (no al iniciar la app)
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure()            // lee hibernate.cfg.xml
                        .buildSessionFactory();
            } catch (Exception ex) {
                System.err.println("Error al crear la SessionFactory.");
                throw new IllegalStateException("No se pudo conectar con la base de datos", ex);
            }
        }
        return sessionFactory;
    }

    // Se invoca al cerrar la aplicación
    public static synchronized void cerrar() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}