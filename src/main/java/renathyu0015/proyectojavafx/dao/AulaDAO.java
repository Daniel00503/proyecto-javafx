package renathyu0015.proyectojavafx.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import renathyu0015.proyectojavafx.entidades.Aula;
import renathyu0015.proyectojavafx.util.HibernateUtil;

public class AulaDAO {

    /**
     * Registra una nueva aula en la base de datos.
     *
     * @param nuevaAula El objeto Aula a persistir
     * @return true si se registró con éxito, false si ocurrió un error
     */
    public boolean registrar(Aula nuevaAula) {
        Transaction transaccion = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = sesion.beginTransaction();
            sesion.persist(nuevaAula);
            transaccion.commit();
            return true;
        } catch (Exception e) {
            if (transaccion != null) {
                transaccion.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de un aula existente.
     *
     * @param aula El objeto Aula con los datos modificados
     * @return true si se actualizó con éxito, false si ocurrió un error
     */
    public boolean actualizar(Aula aula) {
        Transaction transaccion = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = sesion.beginTransaction();
            sesion.merge(aula);
            transaccion.commit();
            return true;
        } catch (Exception e) {
            if (transaccion != null) {
                transaccion.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un aula de la base de datos mediante su ID.
     *
     * @param id El identificador único del aula
     * @return true si se eliminó con éxito, false si ocurrió un error
     */
    public boolean eliminar(Integer id) {
        Transaction transaccion = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = sesion.beginTransaction();
            Aula aula = sesion.get(Aula.class, id);
            if (aula != null) {
                sesion.remove(aula);
                transaccion.commit();
                return true;
            }
            return false; // El aula no existía
        } catch (Exception e) {
            if (transaccion != null) {
                transaccion.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un aula específica por su ID.
     *
     * @param id El identificador único del aula
     * @return El objeto Aula encontrado o null si no existe
     */
    public Aula buscarPorId(Integer id) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Aula.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene la lista completa de todas las aulas registradas.
     *
     * @return Una lista de objetos Aula, o null si ocurre un error
     */
    public List<Aula> obtenerTodas() {
        String hql = "FROM Aula";
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Aula.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Verifica si ya existe un aula con el mismo nombre en el mismo edificio.
     * Utiliza la misma lógica que tu validación de correos para evitar romper la restricción unique.
     *
     * @param nombre El nombre del aula a validar
     * @param edificio El nombre del edificio a validar
     * @return true si ya existe la combinación, false de lo contrario
     */
    public boolean existeAulaEnEdificio(String nombre, String edificio) {
        String hql = "SELECT COUNT(a) FROM Aula a WHERE a.nombre = :nombre AND a.edificio = :edificio";
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Long conteo = sesion.createQuery(hql, Long.class)
                    .setParameter("nombre", nombre)
                    .setParameter("edificio", edificio)
                    .uniqueResult();
            return conteo > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}