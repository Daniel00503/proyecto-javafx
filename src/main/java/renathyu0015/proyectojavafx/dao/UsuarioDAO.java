package renathyu0015.proyectojavafx.dao;

import renathyu0015.proyectojavafx.entidades.Usuario;
import renathyu0015.proyectojavafx.util.HibernateUtil;
import org.hibernate.Session;

public class UsuarioDAO {

    /**
     * Busca un usuario ACTIVO con ese correo y clave.
     * @return el Usuario encontrado o null si las credenciales no coinciden
     */
    public Usuario autenticar(String correo, String clave) {
        String hql = "FROM Usuario u "
                + "WHERE u.Correo = :correo AND u.Clave = :clave AND u.Estado = 'ACTIVO'";

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("clave", clave)
                    .uniqueResultOptional()
                    .orElse(null);
        }
    }
}