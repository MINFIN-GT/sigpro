package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pojo.ProductoPropiedadValor;
import pojo.ProductoPropiedadValorId;
import utilities.CHibernateSession;
import utilities.CLogger;
import utilities.Utils;

public class ProductoPropiedadValorDAO {

	/// estad C:Cargado, N:Nuevo, E:Eliminado
	static class EstructuraPojo {
		Integer propiedadid;
		Integer productoid;

		Integer valorEntero;
		String valorString;
		BigDecimal valorDecimal;
		Date valorTiempo;

		String estado;
	}

	public static ProductoPropiedadValor getProductoPropiedadValor(Integer propiedadId, Integer productoId) {
		Session session = CHibernateSession.getSessionFactory().openSession();
		ProductoPropiedadValor ret = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<ProductoPropiedadValor> criteria = builder.createQuery(ProductoPropiedadValor.class);
			Root<ProductoPropiedadValor> root = criteria.from(ProductoPropiedadValor.class);
			criteria.select(root);
			criteria.where(builder.equal(root.get("id"),
					new ProductoPropiedadValorId(propiedadId, productoId, "", new Date())));
			ret = session.createQuery(criteria).getSingleResult();
		} catch (Throwable e) {
			CLogger.write("1", ProductoPropiedadValorDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static boolean guardar(Integer propiedadId, Integer productoId, String usuario) {

		ProductoPropiedadValor pojo = getProductoPropiedadValor(propiedadId, propiedadId);
		boolean ret = false;

		if (pojo == null) {
			pojo = new ProductoPropiedadValor();

			pojo.setId(new ProductoPropiedadValorId(propiedadId, productoId, usuario, new Date()));

			Session session = CHibernateSession.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.save(pojo);
				session.getTransaction().commit();
				ret = true;
			} catch (Throwable e) {
				CLogger.write("2", ProductoPropiedadValorDAO.class, e);
			} finally {
				session.close();
			}
		}

		return ret;
	}

	public static boolean actualizar(Integer propiedadId, Integer productoId, String nombre, String descripcion,
			String usuario) {

		ProductoPropiedadValor pojo = getProductoPropiedadValor(propiedadId, productoId);
		boolean ret = false;

		if (pojo != null) {

			Session session = CHibernateSession.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.update(pojo);
				session.getTransaction().commit();
				ret = true;
			} catch (Throwable e) {
				CLogger.write("3", ProductoPropiedadValorDAO.class, e);
			} finally {
				session.close();
			}
		}

		return ret;
	}

	public static boolean eliminar(Integer propiedadId, Integer productoId, String usuario) {

		ProductoPropiedadValor pojo = getProductoPropiedadValor(propiedadId, productoId);
		boolean ret = false;

		if (pojo != null) {

			Session session = CHibernateSession.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.delete(pojo);
				session.getTransaction().commit();
				ret = true;
			} catch (Throwable e) {
				CLogger.write("4", ProductoPropiedadValorDAO.class, e);
			} finally {
				session.close();
			}
		}

		return ret;
	}

	public static List<ProductoPropiedadValor> getPagina(int pagina, int registros, Integer productoId) {
		List<ProductoPropiedadValor> ret = new ArrayList<ProductoPropiedadValor>();
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			Query<ProductoPropiedadValor> criteria = session.createQuery(
					"SELECT e FROM ProductoPropiedadValor e where e.id.productoId = " + productoId,
					ProductoPropiedadValor.class);
			criteria.setFirstResult(((pagina - 1) * (registros)));
			criteria.setMaxResults(registros);
			ret = criteria.getResultList();
		} catch (Throwable e) {
			CLogger.write("5", ProductoPropiedadValorDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static String getJson(int pagina, int registros, Integer productoId) {
		String jsonEntidades = "";

		List<ProductoPropiedadValor> pojos = getPagina(pagina, registros, productoId);

		List<EstructuraPojo> listaEstructuraPojos = new ArrayList<EstructuraPojo>();

		for (ProductoPropiedadValor pojo : pojos) {
			EstructuraPojo estructuraPojo = new EstructuraPojo();

			estructuraPojo.estado = "C";

			listaEstructuraPojos.add(estructuraPojo);
		}

		jsonEntidades = Utils.getJSonString("productoTipos", listaEstructuraPojos);

		return jsonEntidades;
	}

	public static Long getTotal() {
		Long ret = 0L;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			Query<Long> conteo = session.createQuery("SELECT count(e) FROM ProductoPropiedadValor e", Long.class);
			ret = conteo.getSingleResult();
		} catch (Throwable e) {
			CLogger.write("7", ProductoPropiedadValorDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static List<ProductoPropiedadValor> getTipoPropiedades(Integer productoId) {
		List<ProductoPropiedadValor> ret = new ArrayList<ProductoPropiedadValor>();
		Session session = CHibernateSession.getSessionFactory().openSession();
		try {
			Query<ProductoPropiedadValor> criteria = session.createQuery(
					"SELECT e FROM ProductoPropiedadValor e where e.id.productoid = :id", ProductoPropiedadValor.class);
			criteria.setParameter("id", productoId);
			ret = criteria.getResultList();
		} catch (Throwable e) {
			CLogger.write("8", ProductoPropiedadValorDAO.class, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public static String getJson(Integer productoId) {
		String jsonEntidades = "";

		List<ProductoPropiedadValor> pojos = getTipoPropiedades(productoId);

		List<EstructuraPojo> listaEstructuraPojos = new ArrayList<EstructuraPojo>();

		for (ProductoPropiedadValor pojo : pojos) {
			EstructuraPojo estructuraPojo = new EstructuraPojo();
			estructuraPojo.estado = "C";

			listaEstructuraPojos.add(estructuraPojo);
		}

		jsonEntidades = Utils.getJSonString("productoTipos", listaEstructuraPojos);

		return jsonEntidades;
	}

	public static boolean persistirValores(Integer productoId, String propiedades, String usuario) {
		boolean ret = false;

		Gson gson = new Gson();

		List<EstructuraPojo> pojos = gson.fromJson(propiedades, new TypeToken<List<EstructuraPojo>>() {
		}.getType());

		for (EstructuraPojo pojo : pojos) {

			if (pojo.estado.equalsIgnoreCase("N")) {
				ret = guardar(productoId, pojo.propiedadid, usuario);
			} else if (pojo.estado.equalsIgnoreCase("E")) {
				ret = eliminar(pojo.productoid, pojo.propiedadid, usuario);
			}
		}

		return ret;
	}

}
