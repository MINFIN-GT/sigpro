package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.UnidadEjecutora;
import utilities.CHibernateSession;
import utilities.CLogger;
import utilities.Utils;

public class UnidadEjecutoraDAO {
	static class EstructuraPojo {
		Integer unidadEjecutora;
		String nombreUnidadEjecutora;
		Integer entidad;
		String nombreEntidad;
		String usuarioCreo;
		String usuarioActualizo;
		String fechaCreacion;
		String fechaActualizacion;
	}

	public static UnidadEjecutora getUnidadEjecutora(Integer unidadEjecutora) {
		Session session = CHibernateSession.getSessionFactory().openSession();
		UnidadEjecutora ret = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<UnidadEjecutora> criteria = builder.createQuery(UnidadEjecutora.class);
			Root<UnidadEjecutora> root = criteria.from(UnidadEjecutora.class);
			criteria.select(root);
			criteria.where(builder.equal(root.get("unidadEjecutora"), unidadEjecutora));
			ret = session.createQuery(criteria).getSingleResult();
		} catch (Throwable e) {
			CLogger.write("2", UnidadEjecutoraDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static boolean guardar(int codigo, String nombre, int codigoEntidad) {

		UnidadEjecutora pojo = getUnidadEjecutora(codigo);
		boolean ret = false;

		if (pojo == null) {

			pojo = new UnidadEjecutora();
			pojo.setUnidadEjecutora(codigo);
			pojo.setNombre(nombre);

			pojo.setEntidad(EntidadDAO.getEntidad(codigoEntidad));

			pojo.setProyectos(null);
			pojo.setColaboradors(null);

			Session session = CHibernateSession.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.save(pojo);
				session.getTransaction().commit();
				ret = true;
			} catch (Throwable e) {
				CLogger.write("3", UnidadEjecutoraDAO.class, e);
			} finally {
				session.close();
			}
		}

		return ret;
	}

	public static boolean actualizar(int codigo, String nombre, int codigoEntidad) {

		UnidadEjecutora pojo = getUnidadEjecutora(codigo);
		boolean ret = false;

		if (pojo != null) {

			pojo.setNombre(nombre);
			pojo.setEntidad(EntidadDAO.getEntidad(codigoEntidad));

			Session session = CHibernateSession.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.update(pojo);
				session.getTransaction().commit();
				ret = true;
			} catch (Throwable e) {
				CLogger.write("4", UnidadEjecutoraDAO.class, e);
			} finally {
				session.close();
			}
		}

		return ret;
	}

	public static List<UnidadEjecutora> getPagina(int pagina, int registros) {
		List<UnidadEjecutora> ret = new ArrayList<UnidadEjecutora>();
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			Query<UnidadEjecutora> criteria = session.createQuery("SELECT e FROM UnidadEjecutora e",
					UnidadEjecutora.class);
			criteria.setFirstResult(((pagina - 1) * (registros)));
			criteria.setMaxResults(registros);
			ret = criteria.getResultList();
		} catch (Throwable e) {
			CLogger.write("5", UnidadEjecutoraDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static String getJson(int pagina, int registros) {
		String jsonEntidades = "";

		List<UnidadEjecutora> pojos = getPagina(pagina, registros);

		List<EstructuraPojo> listaEstructuraPojos = new ArrayList<EstructuraPojo>();

		for (UnidadEjecutora pojo : pojos) {
			EstructuraPojo estructuraPojo = new EstructuraPojo();

			estructuraPojo.unidadEjecutora = pojo.getUnidadEjecutora();
			estructuraPojo.nombreUnidadEjecutora = pojo.getNombre();
			estructuraPojo.entidad = pojo.getEntidad().getEntidad();
			estructuraPojo.nombreEntidad = pojo.getEntidad().getNombre();
			

			listaEstructuraPojos.add(estructuraPojo);
		}

		jsonEntidades = Utils.getJSonString("unidadesEjecutoras", listaEstructuraPojos);

		return jsonEntidades;
	}

	public static Long getTotal() {
		Long ret = 0L;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			Query<Long> conteo = session.createQuery("SELECT count(e.unidadEjecutora) FROM UnidadEjecutora e",
					Long.class);
			ret = conteo.getSingleResult();
		} catch (Throwable e) {
			CLogger.write("7", UnidadEjecutoraDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static List<UnidadEjecutora> getUnidadEjecutoras() {
		List<UnidadEjecutora> ret = new ArrayList<UnidadEjecutora>();
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<UnidadEjecutora> criteria = builder.createQuery(UnidadEjecutora.class);
			Root<UnidadEjecutora> root = criteria.from(UnidadEjecutora.class);
			criteria.select(root);
			ret = session.createQuery(criteria).getResultList();
		} catch (Throwable e) {
			CLogger.write("8", UnidadEjecutoraDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}
}
