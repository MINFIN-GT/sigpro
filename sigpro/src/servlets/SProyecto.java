package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.ComponenteDAO;
import dao.MetaDAO;
import dao.MetaValorDAO;
import dao.PrestamoDAO;
import dao.ProductoDAO;
import dao.ProyectoDAO;
import dao.ProyectoPropiedadDAO;
import dao.ProyectoPropiedadValorDAO;
import pojo.AutorizacionTipo;
import pojo.Componente;
import pojo.Cooperante;
import pojo.EjecucionEstado;
import pojo.InteresTipo;
import pojo.Meta;
import pojo.MetaValor;
import pojo.ObjetoPrestamo;
import pojo.ObjetoPrestamoId;
import pojo.Prestamo;
import pojo.Producto;
import pojo.Proyecto;
import pojo.ProyectoPropedadValor;
import pojo.ProyectoPropedadValorId;
import pojo.ProyectoPropiedad;
import pojo.ProyectoTipo;
import pojo.TipoMoneda;
import pojo.UnidadEjecutora;
import utilities.Utils;

@WebServlet("/SProyecto")
public class SProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	class datos {
		int id;
		String nombre;
		String objetivo;
		String descripcion;
		Long snip;
		int proyectotipoid;
		String proyectotipo;
		String unidadejecutora;
		int unidadejecutoraid;
		String cooperante;
		int cooperanteid;
		String fechaCreacion;
		String usuarioCreo;
		String fechaactualizacion;
		String usuarioactualizo;
		Integer programa;
		Integer subprograma;
		Integer proyecto;
		Integer actividad;
		Integer obra;
		Integer fuente;
		String longitud;
		String latitud;
	};

	class stdatadinamico {
		String id;
		String tipo;
		String label;
		String valor;
		String valor_f;
	}

	private static final int OBJETO_ID_PROYECTO = 1;
	private static final int OBJETO_ID_COMPONENTE = 2;
	private static final int OBJETO_ID_PRODUCTO = 3;
	//private static final int OBJETO_ID_SUBPRODUCTO = 4;
	//private static final int OBJETO_ID_ACTIVIDAD= 5;
	
	private static final int META_ID_REAL = 1;
	private static final int META_ID_PLANIFICADA = 2;
	private static final int META_ID_ANUALREAL = 3;
	private static final int META_ID_ANUALPLANIFICADA = 4;
	private static final int META_ID_LINEABASE= 5;
	private static final int META_ID_FINAL= 6;
	
	private static final int DATOTIPO_TEXTO = 1;
	private static final int DATOTIPO_ENTERO = 2;
	private static final int DATOTIPO_DECIMAL = 3;
	private static final int DATOTIPO_BOOLEANO = 4;
	private static final int DATOTIPO_FECHA = 5;
	
	class stproyectometa {
		Integer id;
		Integer objetoTipo;
		String objetoTipoNombre;
		String nombre;
		Integer objetoPadre;
		String unidadDeMedida;
		Integer datoTipoId;
		String metaPlanificada;
		String metaReal;
		String metaRealId;
		String metaRealFecha;
		String metaAnualPlanificada;
		String metaAnualReal;
		String metaAnualRealId;
		String metaAnualRealFecha;
		String lineaBase;
		String metaFinal;
	}

    public SProyecto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String response_text = "{ \"success\": false }";

		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");

        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(response_text.getBytes("UTF-8"));
        gz.close();
        output.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sesionweb = request.getSession();
		String usuario = sesionweb.getAttribute("usuario")!= null ? sesionweb.getAttribute("usuario").toString() : null;
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		;
		Map<String, String> map = gson.fromJson(sb.toString(), type);
		String accion = map.get("accion")!=null ? map.get("accion") : "";
		String response_text = "";

		if (accion.equals("getProyectos")) {
			List<Proyecto> proyectos = ProyectoDAO.getProyectos(usuario);

			response.setHeader("Content-Encoding", "gzip");
			response.setCharacterEncoding("UTF-8");

			List <datos> datos_ = new ArrayList<datos>();
			for (Proyecto proyecto : proyectos){
				datos dato = new datos();
				dato.id = proyecto.getId();
				dato.nombre = proyecto.getNombre();
				dato.objetivo = proyecto.getObjetivo();
				dato.descripcion = proyecto.getDescripcion();
				dato.snip = proyecto.getSnip();
				dato.proyectotipo = proyecto.getProyectoTipo().getNombre();
				dato.proyectotipoid = proyecto.getProyectoTipo().getId();
				dato.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				dato.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				dato.cooperante = proyecto.getCooperante().getNombre();
				dato.cooperanteid = proyecto.getCooperante().getId();
				dato.fechaCreacion = Utils.formatDateHour( proyecto.getFechaCreacion());
				dato.usuarioCreo = proyecto.getUsuarioCreo();
				dato.fechaactualizacion = Utils.formatDateHour( proyecto.getFechaActualizacion());
				dato.usuarioactualizo = proyecto.getUsuarioActualizo();
				dato.programa = proyecto.getPrograma();
				dato.subprograma = proyecto.getSubprograma();
				dato.proyecto = proyecto.getProyecto();
				dato.obra = proyecto.getObra();
				dato.actividad = proyecto.getActividad();
				dato.fuente = proyecto.getFuente();
				dato.longitud = proyecto.getLongitud();
				dato.latitud = proyecto.getLatitud();
				datos_.add(dato);
			}

			response_text = new GsonBuilder().serializeNulls().create().toJson(datos_);
			response_text = String.join("", "\"entidades\":", response_text);
			response_text = String.join("", "{\"success\":true,", response_text, "}");


 
		} else if (accion.equals("getProyectosPorUnidadEjecutora")){
			Integer unidadEjecutoraId = Utils.String2Int(map.get("unidadEjecutoraId"), 0);
			List<Proyecto> proyectos = ProyectoDAO.getProyectosPorUnidadEjecutora(usuario, unidadEjecutoraId);

			response.setHeader("Content-Encoding", "gzip");
			response.setCharacterEncoding("UTF-8");

			List <datos> datos_ = new ArrayList<datos>();
			for (Proyecto proyecto : proyectos){
				datos dato = new datos();
				dato.id = proyecto.getId();
				dato.nombre = proyecto.getNombre();
				dato.objetivo = proyecto.getObjetivo();
				dato.descripcion = proyecto.getDescripcion();
				dato.snip = proyecto.getSnip();
				dato.proyectotipo = proyecto.getProyectoTipo().getNombre();
				dato.proyectotipoid = proyecto.getProyectoTipo().getId();
				dato.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				dato.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				dato.cooperante = proyecto.getCooperante().getNombre();
				dato.cooperanteid = proyecto.getCooperante().getId();
				dato.fechaCreacion = Utils.formatDateHour( proyecto.getFechaCreacion());
				dato.usuarioCreo = proyecto.getUsuarioCreo();
				dato.fechaactualizacion = Utils.formatDateHour( proyecto.getFechaActualizacion());
				dato.usuarioactualizo = proyecto.getUsuarioActualizo();
				dato.programa = proyecto.getPrograma();
				dato.subprograma = proyecto.getSubprograma();
				dato.proyecto = proyecto.getProyecto();
				dato.obra = proyecto.getObra();
				dato.actividad = proyecto.getActividad();
				dato.fuente = proyecto.getFuente();
				dato.longitud = proyecto.getLongitud();
				dato.latitud = proyecto.getLatitud();
				datos_.add(dato);
			}

			response_text = new GsonBuilder().serializeNulls().create().toJson(datos_);
			response_text = String.join("", "\"entidades\":", response_text);
			response_text = String.join("", "{\"success\":true,", response_text, "}");

		} else if(accion.equals("getProyectoPagina")){
			int pagina = map.get("pagina")!=null  ? Integer.parseInt(map.get("pagina")) : 0;
			int numeroProyecto = map.get("numeroproyecto")!=null  ? Integer.parseInt(map.get("numeroproyecto")) : 0;
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			String columna_ordenada = map.get("columna_ordenada");
			String orden_direccion = map.get("orden_direccion");
			List<Proyecto> proyectos = ProyectoDAO.getProyectosPagina(pagina, numeroProyecto,
					filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion, columna_ordenada, orden_direccion,usuario);
			List<datos> datos_=new ArrayList<datos>();
			for (Proyecto proyecto : proyectos){
				datos dato = new datos();
				dato.id = proyecto.getId();
				dato.nombre = proyecto.getNombre();
				dato.objetivo = proyecto.getObjetivo();
				dato.descripcion = proyecto.getDescripcion();
				dato.snip = proyecto.getSnip();
				dato.proyectotipo = proyecto.getProyectoTipo().getNombre();
				dato.proyectotipoid = proyecto.getProyectoTipo().getId();
				dato.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				dato.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				dato.cooperante = proyecto.getCooperante().getNombre();
				dato.cooperanteid = proyecto.getCooperante().getId();
				dato.fechaCreacion = Utils.formatDateHour( proyecto.getFechaCreacion());
				dato.usuarioCreo = proyecto.getUsuarioCreo();
				dato.fechaactualizacion = Utils.formatDateHour( proyecto.getFechaActualizacion());
				dato.usuarioactualizo = proyecto.getUsuarioActualizo();
				dato.programa = proyecto.getPrograma();
				dato.subprograma = proyecto.getSubprograma();
				dato.proyecto = proyecto.getProyecto();
				dato.obra = proyecto.getObra();
				dato.actividad = proyecto.getActividad();
				dato.fuente = proyecto.getFuente();
				dato.longitud = proyecto.getLongitud();
				dato.latitud = proyecto.getLatitud();
				datos_.add(dato);
			}
			response_text=new GsonBuilder().serializeNulls().create().toJson(datos_);
	        response_text = String.join("", "\"proyectos\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}else if(accion.equals("getProyectoPaginaDisponibles")){
			int pagina = map.get("pagina")!=null  ? Integer.parseInt(map.get("pagina")) : 0;
			int numeroProyecto = map.get("numeroproyecto")!=null  ? Integer.parseInt(map.get("numeroproyecto")) : 0;
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			String columna_ordenada = map.get("columna_ordenada");
			String orden_direccion = map.get("orden_direccion");
			String idsProyectos = map.get("idsproyectos");
			List<Proyecto> proyectos = ProyectoDAO.getProyectosPaginaDisponibles(pagina, numeroProyecto,
					filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion, columna_ordenada, orden_direccion,idsProyectos);
			List<datos> datos_=new ArrayList<datos>();
			for (Proyecto proyecto : proyectos){
				datos dato = new datos();
				dato.id = proyecto.getId();
				dato.nombre = proyecto.getNombre();
				dato.objetivo = proyecto.getObjetivo();
				dato.descripcion = proyecto.getDescripcion();
				dato.snip = proyecto.getSnip();
				dato.proyectotipo = proyecto.getProyectoTipo().getNombre();
				dato.proyectotipoid = proyecto.getProyectoTipo().getId();
				dato.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				dato.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				dato.cooperante = proyecto.getCooperante().getNombre();
				dato.cooperanteid = proyecto.getCooperante().getId();
				dato.fechaCreacion = Utils.formatDateHour( proyecto.getFechaCreacion());
				dato.usuarioCreo = proyecto.getUsuarioCreo();
				dato.fechaactualizacion = Utils.formatDateHour( proyecto.getFechaActualizacion());
				dato.usuarioactualizo = proyecto.getUsuarioActualizo();
				dato.programa = proyecto.getPrograma();
				dato.subprograma = proyecto.getSubprograma();
				dato.proyecto = proyecto.getProyecto();
				dato.obra = proyecto.getObra();
				dato.actividad = proyecto.getActividad();
				dato.fuente = proyecto.getFuente();
				dato.longitud = proyecto.getLongitud();
				dato.latitud = proyecto.getLatitud();
				datos_.add(dato);
			}
			response_text=new GsonBuilder().serializeNulls().create().toJson(datos_);
	        response_text = String.join("", "\"proyectos\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}
		else if (accion.equals("guardar")){
			try{
			boolean result = false;
			boolean esnuevo = map.get("esnuevo").equals("true");
			int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			Proyecto proyecto;
			if (id>0 || esnuevo){
				String nombre = map.get("nombre");
				Long snip = map.get("snip")!=null ? Long.parseLong(map.get("snip")) : null;
				String objetivo = map.get("objetivo");
				String descripcion = map.get("descripcion");

				Integer programa = map.get("programa")!=null ? Integer.parseInt(map.get("programa")) : null;
				Integer subPrograma = map.get("subprograma")!=null ?  Integer.parseInt(map.get("subprograma")) : null;
				Integer proyecto_ = map.get("proyecto_")!=null ? Integer.parseInt(map.get("proyecto_")) : null;
				Integer actividad = map.get("actividad")!=null ? Integer.parseInt(map.get("actividad")):null;
				Integer obra = map.get("obra")!=null ? Integer.parseInt(map.get("obra")):null;
				Integer fuente = map.get("fuente")!=null ? Integer.parseInt(map.get("fuente")):null;
				String longitud = map.get("longitud");
				String latitud = map.get("latitud");

				ProyectoTipo proyectoTipo = new ProyectoTipo();
				proyectoTipo.setId(map.get("proyectotipoid") !=null ? Integer.parseInt(map.get("proyectotipoid")): null);

				UnidadEjecutora unidadEjecutora = new UnidadEjecutora();
				unidadEjecutora.setUnidadEjecutora(map.get("unidadejecutoraid")!=null ? Integer.parseInt(map.get("unidadejecutoraid")): null);

				Cooperante cooperante = new Cooperante();
				cooperante.setId(map.get("cooperanteid")!=null ? Integer.parseInt(map.get("cooperanteid")): null);
				
				// prestamo campos requeridos
				int codigoPresupuestario = Utils.String2Int(map.get("codigoPresupuestario"));
				String numeroPrestamo =  map.get("numeroPrestamo"); 
				String  proyectoPrograma = map.get("proyetoPrograma");
				
				int unidadEjecutoraPrestamo = Utils.String2Int(map.get("unidadEjecutora"), 0);
				UnidadEjecutora unidadEjecutora_ = new UnidadEjecutora();
				unidadEjecutora_.setUnidadEjecutora(unidadEjecutoraPrestamo);
				
				Cooperante cooperanteUe = new Cooperante();
				cooperanteUe.setId(map.get("cooperanteUeId")!=null ? Integer.parseInt(map.get("cooperanteUeId")): null);
				
				Date fechaDecreto = Utils.dateFromString(map.get("fechaDecreto"));
				Date fechaSuscripcion = Utils.dateFromString(map.get("fechaSuscripcion"));
				Date fechaVigencia = Utils.dateFromString(map.get("fechaVigencia"));
				
				int tipoMonedaId = Utils.String2Int(map.get("tipoMonedaId"));
				TipoMoneda tipoMoneda = new TipoMoneda();
				tipoMoneda.setId(tipoMonedaId);
				
				BigDecimal montoContratado = Utils.String2BigDecimal(map.get("montoContratado"), null);
				BigDecimal montoContratadoUsd = Utils.String2BigDecimal(map.get("montoContratadoUsd"), null);
				BigDecimal montoContratadoQtz = Utils.String2BigDecimal(map.get("montoContratadoQtz"), null);
				BigDecimal desembolsoAFechaUsd = Utils.String2BigDecimal(map.get("desembolsoAFechaUsd"), null);
				BigDecimal montoPorDesembolsarUsd = Utils.String2BigDecimal(map.get("montoPorDesembolsarUsd"), null);
				Date fechaElegibilidadUe = Utils.dateFromString(map.get("fechaElegibilidad"));
				Date fechaCierreOrigianlUe = Utils.dateFromString(map.get("fechaCierreOriginal"));
				Date fechaCierreActualUe = Utils.dateFromString(map.get("fechaCierreActual"));
				int mesesProrrogaUe = Utils.String2Int(map.get("mesesProrroga"), null);
				BigDecimal montoAsignadoUe = Utils.String2BigDecimal(map.get("montoAisignadoUe"), null);
				BigDecimal desembolsoAFechaUe = Utils.String2BigDecimal(map.get("desembolsoAFechaUe"), null);
				BigDecimal montoPorDesembolsarUe = Utils.String2BigDecimal(map.get("montoPorDesembolsarUe"), null);
				BigDecimal montoAsignadoUeUsd = Utils.String2BigDecimal(map.get("montoAsignadoUeUsd"), null);
				BigDecimal montoAsignadoUeQtz = Utils.String2BigDecimal(map.get("montoAsignadoUeQtz"), null);
				BigDecimal desembolsoAFechaUeUsd = Utils.String2BigDecimal(map.get("desembolsoAFechaUeUsd"), null);
				BigDecimal montoPorDesembolsarUeUsd = Utils.String2BigDecimal(map.get("montoPorDesembolsarUeUsd"), null);
				
				// prestamo campos adicionales
				
				Date fechaCorte = map.get("fechaCorte") == null ? null : Utils.dateFromString(map.get("fechaCorte"));
				String destino = map.get("destino");
				String sectorEconomico = map.get("sectorEconomico");
				Date fechaFirma = map.get("fechaFimra") == null ? null : Utils.dateFromString(map.get("fechaFimra"));
				Integer tipoAutorizacionId = Utils.String2Int(map.get("tipoAutorizacionId"),null);
				String numeroAutorizacion = map.get("numeroAutorizacion");
				Date fechaAutorizacion = map.get("fechaAutorizacion") == null ? null : Utils.dateFromString(map.get("fechaAutorizacion"));
				Integer aniosPlazo = Utils.String2Int(map.get("aniosPlazo"), null);
				Integer aniosGracia = Utils.String2Int(map.get("aniosGracia"), null);
				Date fechaFinEjecucion = map.get("fechaFinEjecucion") == null ? null : Utils.dateFromString(map.get("fechaFinEjecucion"));
				Integer peridoEjecucion = Utils.String2Int(map.get("periodoEjecucion"), null);
				Integer tipoInteresId = Utils.String2Int(map.get("tipoInteresId"), null);
				BigDecimal porcentajeInteres = Utils.String2BigDecimal(map.get("porcentajeInteres"), null); 
				BigDecimal porcentajeComisionCompra = Utils.String2BigDecimal(map.get("porcentajeComisionCompra"), null);
				BigDecimal amortizado = Utils.String2BigDecimal(map.get("amortizado"), null);
				BigDecimal porAmortizar = Utils.String2BigDecimal(map.get("porAmortizar"), null);
				BigDecimal principalAnio = Utils.String2BigDecimal(map.get("principalAnio"), null);
				BigDecimal interesesAnio = Utils.String2BigDecimal(map.get("interesesAnio"), null);
				BigDecimal comisionCompromisoAnio= Utils.String2BigDecimal(map.get("comisionCompromisoAnio"), null);
				BigDecimal otrosGastos = Utils.String2BigDecimal(map.get("otrosGastos"), null);
				BigDecimal principalAcumulado = Utils.String2BigDecimal(map.get("principalAcumulado"), null);
				BigDecimal interesesAcumulados = Utils.String2BigDecimal(map.get("interesesAcumulados"), null);
				BigDecimal comisionCompromisoAcumulado = Utils.String2BigDecimal(map.get("comisionCompromisoAcumulado"), null);
				BigDecimal otrosCargosAcumulados = Utils.String2BigDecimal(map.get("otrosCargosAcumulados"), null);
				BigDecimal presupuestoAsignadoFuncionamiento = Utils.String2BigDecimal(map.get("presupuestoAsignadoFuncionamiento"), null);
				BigDecimal prespupuestoAsignadoInversion = Utils.String2BigDecimal(map.get("presupuestoAsignadoInversion"), null);
				BigDecimal presupuestoModificadoFun = Utils.String2BigDecimal(map.get("presupuestoModificadoFuncionamiento"), null);
				BigDecimal presupuestoModificadoInv = Utils.String2BigDecimal(map.get("presupuestoModificadoInversion"), null);
				BigDecimal presupuestoVigenteFun = Utils.String2BigDecimal(map.get("presupuestoVigenteFuncionamiento"), null);
				BigDecimal presupuestoVigenteInv = Utils.String2BigDecimal(map.get("presupuestoVigenteInversion"), null);
				BigDecimal prespupuestoDevengadoFun = Utils.String2BigDecimal(map.get("presupuestoDevengadoFunconamiento"), null);
				BigDecimal presupuestoDevengadoInv = Utils.String2BigDecimal(map.get("presupuestoDevengadoInversion"), null);
				BigDecimal presupuestoPagadoFun = Utils.String2BigDecimal(map.get("presupuestoPagadoFuncionamiento"), null);
				BigDecimal presupuestoPagadoInv = Utils.String2BigDecimal(map.get("presupuestoPagadoInversion"), null);
				BigDecimal saldoCuentas = Utils.String2BigDecimal(map.get("saldoCuentas"), null);
				BigDecimal desembolsadoReal = Utils.String2BigDecimal(map.get("desembolsoReal"), null);
				
				int objetoTipo = Utils.String2Int(map.get("objetoTipo"),1);
				AutorizacionTipo autorizacionTipo = null;
				
				if (tipoAutorizacionId != null){
					autorizacionTipo = new AutorizacionTipo();
					autorizacionTipo.setId(tipoAutorizacionId);
				}
				
				Integer ejecucionEstadoId = Utils.String2Int(map.get("ejecucionEstadoId"), null);
				EjecucionEstado ejecucionEstado = null;
				if (ejecucionEstadoId != null){
					ejecucionEstado = new EjecucionEstado();
					ejecucionEstado.setId(ejecucionEstadoId);
				}
				
				InteresTipo interesTipo = null;
				if (tipoInteresId != null){
					interesTipo = new InteresTipo();
					interesTipo.setId(tipoInteresId);
				}
				//Fin prestamo
				
				type = new TypeToken<List<stdatadinamico>>() {
				}.getType();

				List<stdatadinamico> datos = gson.fromJson(map.get("datadinamica"), type);

				if(esnuevo){
					proyecto = new Proyecto(cooperante, proyectoTipo, unidadEjecutora, nombre, descripcion
							, usuario, null, new DateTime().toDate(), null, 1, snip
							,programa , subPrograma, proyecto_,actividad, obra, fuente,latitud,longitud,objetivo
							, null, null, null, null,null,null);

				}else{
					proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
					proyecto.setNombre(nombre);
					proyecto.setObjetivo(objetivo);
					proyecto.setDescripcion(descripcion);
					proyecto.setSnip(snip);
					proyecto.setProyectoTipo(proyectoTipo);
					proyecto.setUnidadEjecutora(unidadEjecutora);
					proyecto.setCooperante(cooperante);
					proyecto.setUsuarioActualizo(usuario);
					proyecto.setFechaActualizacion(new DateTime().toDate());
					proyecto.setPrograma(programa);
					proyecto.setSubprograma(subPrograma);
					proyecto.setProyecto(proyecto_);
					proyecto.setActividad(actividad);
					proyecto.setObra(obra);
					proyecto.setFuente(fuente);
					proyecto.setLongitud(longitud);
					proyecto.setLatitud(latitud);

				   List<ProyectoPropedadValor> valores_temp = ProyectoPropiedadValorDAO.getProyectoPropiedadadesValoresPorProyecto(proyecto.getId());

					proyecto.setProyectoPropedadValors(null);
					if (valores_temp!=null){
						for (ProyectoPropedadValor valor : valores_temp){
							valor.setFechaActualizacion(new DateTime().toDate());
							valor.setUsuarioActualizo("admin");
							ProyectoPropiedadValorDAO.eliminarProyectoPropiedadValor(valor);
						}
					}
				}
				result = ProyectoDAO.guardarProyecto(proyecto);
				if (result){
					for (stdatadinamico data : datos) {
						if (data.valor!=null && data.valor.length()>0 && data.valor.compareTo("null")!=0){
							ProyectoPropiedad proyectoPropiedad = ProyectoPropiedadDAO.getProyectoPropiedadPorId(Integer.parseInt(data.id));
							ProyectoPropedadValorId idValor = new ProyectoPropedadValorId(proyecto.getId(),Integer.parseInt(data.id));
							ProyectoPropedadValor valor = new ProyectoPropedadValor(idValor, proyecto, proyectoPropiedad, usuario, new DateTime().toDate(), 1);
	
							switch (proyectoPropiedad.getDatoTipo().getId()){
								case 1:
									valor.setValorString(data.valor);
									break;
								case 2:
									valor.setValorEntero(Utils.String2Int(data.valor, null));
									break;
								case 3:
									valor.setValorDecimal(Utils.String2BigDecimal(data.valor, null));
									break;
								case 4:
									valor.setValorEntero(Utils.String2Boolean(data.valor, null));
									break;
								case 5:
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									valor.setValorTiempo(data.valor_f.compareTo("")!=0 ? sdf.parse(data.valor_f) : null);
									break;
							}
							result = (result && ProyectoPropiedadValorDAO.guardarProyectoPropiedadValor(valor));
						}
					}
					
					//prestamo
					Prestamo prestamo = PrestamoDAO.getPrestamoPorObjetoYTipo(proyecto.getId(), objetoTipo);
					ObjetoPrestamo objetoPrestamo = null;
					
					if (prestamo==null){
						
						// revisar esta variable plazoEjecucionUe que deberia ir en el penultimo null
						
						prestamo = new Prestamo(autorizacionTipo, cooperanteUe, ejecucionEstado, interesTipo, tipoMoneda, 
								unidadEjecutora_, fechaCorte, codigoPresupuestario, numeroPrestamo, destino, sectorEconomico, 
								fechaFirma, numeroAutorizacion, fechaAutorizacion, aniosPlazo, aniosGracia,
								fechaFinEjecucion, peridoEjecucion, porcentajeInteres, porcentajeComisionCompra, 
								montoContratado, amortizado, porAmortizar, principalAnio, interesesAnio, comisionCompromisoAnio, 
								otrosGastos, principalAcumulado, interesesAcumulados, comisionCompromisoAcumulado, 
								otrosCargosAcumulados, presupuestoAsignadoFuncionamiento, prespupuestoAsignadoInversion,
								presupuestoModificadoFun, presupuestoModificadoInv, presupuestoVigenteFun,
								presupuestoVigenteInv, prespupuestoDevengadoFun, presupuestoDevengadoInv,
								presupuestoPagadoFun, presupuestoPagadoInv, saldoCuentas, desembolsadoReal, 
								usuario, null, new Date(), null, 1, proyectoPrograma, fechaDecreto, 
								fechaSuscripcion, fechaElegibilidadUe, fechaCierreOrigianlUe, fechaCierreActualUe, mesesProrrogaUe,
								null, montoAsignadoUe, desembolsoAFechaUe, montoPorDesembolsarUe, fechaVigencia, 
								montoContratadoUsd, montoContratadoQtz, desembolsoAFechaUsd, montoPorDesembolsarUsd, montoAsignadoUeUsd, 
								montoAsignadoUeQtz, desembolsoAFechaUeUsd, montoPorDesembolsarUeUsd, null);
						
						ObjetoPrestamoId objetoPrestamoId = new ObjetoPrestamoId(0, proyecto.getId(), objetoTipo);
						objetoPrestamo = new ObjetoPrestamo(objetoPrestamoId, prestamo);
						Set <ObjetoPrestamo> objetoPrestamos = new HashSet<>();
						objetoPrestamos.add(objetoPrestamo);
						PrestamoDAO.guardarPrestamo(prestamo, objetoPrestamo);
					}else{
						
						prestamo.setAmortizado(amortizado);
						prestamo.setAniosGracia(aniosGracia);
						prestamo.setAniosPlazo(aniosPlazo);
						prestamo.setAutorizacionTipo(autorizacionTipo);
						prestamo.setCodigoPresupuestario(codigoPresupuestario);
						prestamo.setComisionCompromisoAcumulado(comisionCompromisoAcumulado);
						prestamo.setComisionCompromisoAnio(comisionCompromisoAnio);
						prestamo.setDesembolsadoReal(desembolsadoReal);
						prestamo.setDesembolsoAFechaUe(desembolsoAFechaUe);
						prestamo.setDestino(destino);
						prestamo.setEjecucionEstado(ejecucionEstado);
						prestamo.setEstado(1);
						prestamo.setFechaActualizacion(new DateTime().toDate());
						prestamo.setFechaAutorizacion(fechaAutorizacion);
						prestamo.setFechaCierreActualUe(fechaCierreActualUe);
						prestamo.setFechaCierreOrigianlUe(fechaCierreOrigianlUe);
						prestamo.setFechaCorte(fechaCorte);
						prestamo.setFechaDecreto(fechaDecreto);
						prestamo.setFechaElegibilidadUe(fechaElegibilidadUe);
						prestamo.setFechaFinEjecucion(fechaFinEjecucion);
						prestamo.setFechaFirma(fechaFirma);
						prestamo.setFechaSuscripcion(fechaSuscripcion);
						prestamo.setInteresesAcumulados(interesesAcumulados);
						prestamo.setInteresesAnio(interesesAnio);
						prestamo.setInteresTipo(interesTipo);
						prestamo.setMesesProrrogaUe(mesesProrrogaUe);
						prestamo.setMontoAsignadoUe(montoAsignadoUe);
						prestamo.setMontoContratado(montoContratado);
						prestamo.setMontoPorDesembolsarUe(montoPorDesembolsarUe);
						prestamo.setNumeroAutorizacion(numeroAutorizacion);
						prestamo.setNumeroPrestamo(numeroPrestamo);
						prestamo.setOtrosCargosAcumulados(otrosCargosAcumulados);
						prestamo.setOtrosGastos(otrosGastos);
						prestamo.setPeridoEjecucion(peridoEjecucion);
						prestamo.setPorAmortizar(porAmortizar);
						prestamo.setPorcentajeComisionCompra(porcentajeComisionCompra);
						prestamo.setPorcentajeInteres(porcentajeInteres);
						prestamo.setPrespupuestoAsignadoInversion(prespupuestoAsignadoInversion);
						prestamo.setPrespupuestoDevengadoFuncionamiento(prespupuestoDevengadoFun);
						prestamo.setPresupuestoAsignadoFuncionamiento(presupuestoAsignadoFuncionamiento);
						prestamo.setPresupuestoDevengadoInversion(presupuestoDevengadoInv);
						prestamo.setPresupuestoModificadoFuncionamiento(presupuestoModificadoFun);
						prestamo.setPresupuestoModificadoInversion(presupuestoModificadoInv);
						prestamo.setPresupuestoPagadoFuncionamiento(presupuestoPagadoFun);
						prestamo.setPresupuestoPagadoInversion(presupuestoPagadoInv);
						prestamo.setPresupuestoVigenteFuncionamiento(presupuestoVigenteFun);
						prestamo.setPresupuestoVigenteInversion(presupuestoVigenteInv);
						prestamo.setPrincipalAcumulado(principalAcumulado);
						prestamo.setPrincipalAnio(principalAnio);
						prestamo.setProyectoPrograma(proyectoPrograma);
						prestamo.setSaldoCuentas(saldoCuentas);
						prestamo.setSectorEconomico(sectorEconomico);
						prestamo.setTipoMoneda(tipoMoneda);
						prestamo.setUnidadEjecutora(unidadEjecutora_);
						prestamo.setUsuarioActualizo(usuario);
						prestamo.setFechaVigencia(fechaVigencia);
						prestamo.setMontoContratadoUsd(montoContratadoUsd);
						prestamo.setMontoContratadoQtz(montoContratadoQtz);
						prestamo.setDesembolsoAFechaUsd(desembolsoAFechaUsd);
						prestamo.setMontoPorDesembolsarUsd(montoPorDesembolsarUsd);
						prestamo.setMontoAsignadoUeUsd(montoAsignadoUeUsd);
						prestamo.setMontoAsignadoUeQtz(montoAsignadoUeQtz);
						prestamo.setDesembolsoAFechaUeUsd(desembolsoAFechaUeUsd);
						prestamo.setMontoPorDesembolsarUeUsd(montoPorDesembolsarUeUsd);
						prestamo.setCooperante(cooperanteUe);
						PrestamoDAO.guardarPrestamo(prestamo, PrestamoDAO.getObjetoPrestamo(prestamo.getId()));
					}
					
					//fin prestamo
				}
				
				response_text = String.join("","{ \"success\": ",(result ? "true" : "false"),", "
						, "\"id\": " , proyecto.getId().toString() , ","
						, "\"usuarioCreo\": \"" , proyecto.getUsuarioCreo(),"\","
						, "\"fechaCreacion\":\" " , Utils.formatDateHour(proyecto.getFechaCreacion()),"\","
						, "\"usuarioactualizo\": \"" , proyecto.getUsuarioActualizo() != null ? proyecto.getUsuarioActualizo() : "","\","
						, "\"fechaactualizacion\": \"" , Utils.formatDateHour(proyecto.getFechaActualizacion()),"\""
						," }");
			}else
				response_text = "{ \"success\": false }";

			}
			catch (Throwable e){
				response_text = "{ \"success\": false }";
			}

		}else
		
		if (accion.equals("guardarModal")){
			try{
				int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
				Proyecto proyecto;
				
				String nombre = map.get("nombre");
				
				ProyectoTipo proyectoTipo = new ProyectoTipo();
				proyectoTipo.setId(map.get("proyectotipoid") !=null ? Integer.parseInt(map.get("proyectotipoid")): null);
	
				UnidadEjecutora unidadEjecutora = new UnidadEjecutora();
				unidadEjecutora.setUnidadEjecutora(map.get("unidadejecutoraid")!=null ? Integer.parseInt(map.get("unidadejecutoraid")): null);
	
				Cooperante cooperante = new Cooperante();
				cooperante.setId(map.get("cooperanteid")!=null ? Integer.parseInt(map.get("cooperanteid")): null);
			
				
				proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
				proyecto.setNombre(nombre);
				proyecto.setProyectoTipo(proyectoTipo);
				proyecto.setUnidadEjecutora(unidadEjecutora);
				proyecto.setCooperante(cooperante);
				proyecto.setUsuarioActualizo(usuario);
				proyecto.setFechaActualizacion(new DateTime().toDate());
			 
				ProyectoDAO.guardarProyecto(proyecto);
				
				datos temp = new datos();
				temp.id = proyecto.getId();
				temp.nombre = proyecto.getNombre();
				temp.proyectotipoid = proyecto.getProyectoTipo().getId();
				temp.proyectotipo = proyecto.getProyectoTipo().getNombre();
				temp.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				temp.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				temp.cooperante = proyecto.getCooperante().getNombre();
				temp.cooperanteid = proyecto.getCooperante().getId();	
			
			response_text=new GsonBuilder().serializeNulls().create().toJson(temp);
	        response_text = String.join("", "\"proyecto\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");

			
			}
			catch (Throwable e){
				response_text = "{ \"success\": false }";
			}
		}
		else if(accion.equals("borrarProyecto")){
			int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			if(id>0){
				Proyecto proyecto = ProyectoDAO.getProyectoPorId(id,usuario);

				List<ProyectoPropedadValor> valores_temp = ProyectoPropiedadValorDAO.getProyectoPropiedadadesValoresPorProyecto(proyecto.getId());
				if (valores_temp!=null){
					for (ProyectoPropedadValor valor : valores_temp){
						valor.setFechaActualizacion(new DateTime().toDate());
						valor.setUsuarioActualizo(usuario);
						ProyectoPropiedadValorDAO.eliminarProyectoPropiedadValor(valor);
					}
				}
				response_text = String.join("","{ \"success\": ",(ProyectoDAO.eliminarProyecto(proyecto) ? "true" : "false")," }");
			}
			else
				response_text = "{ \"success\": false }";
		}
		else if(accion.equals("numeroProyectos")){
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			response_text = String.join("","{ \"success\": true, \"totalproyectos\":",ProyectoDAO.getTotalProyectos(filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion,usuario).toString()," }");
		}
		else if(accion.equals("numeroProyectosDisponibles")){
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			String idsProyectos = map.get("idsproyectos");
			response_text = String.join("","{ \"success\": true, \"totalproyectos\":",ProyectoDAO.getTotalProyectosDisponibles(filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion,idsProyectos).toString()," }");
		}
		else if(accion.equals("obtenerProyectoPorId")){
			Integer id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			Proyecto proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
			response_text = String.join("","{ \"success\": ",(proyecto!=null && proyecto.getId()!=null ? "true" : "false"),", "
					+ "\"id\": " + (proyecto!=null ? proyecto.getId():"0") +", "
					+ "\"nombre\": \"" + (proyecto!=null ? proyecto.getNombre():"") +"\" }");

		}else if(accion.equals("obtenerProyectosPorPrograma")){
			Integer idPrograma = map.get("idPrograma")!=null ? Integer.parseInt(map.get("idPrograma")) : 0;
			List<Proyecto> proyectos = ProyectoDAO.getProyectosPorPrograma(idPrograma);
			List<datos> datos_=new ArrayList<datos>();
			for (Proyecto proyecto : proyectos){
				datos dato = new datos();
				dato.id = proyecto.getId();
				dato.nombre = proyecto.getNombre();
				dato.objetivo = proyecto.getObjetivo();
				dato.descripcion = proyecto.getDescripcion();
				dato.fechaCreacion = Utils.formatDateHour( proyecto.getFechaCreacion());
				dato.usuarioCreo = proyecto.getUsuarioCreo();
				datos_.add(dato);
			}
			response_text=new GsonBuilder().serializeNulls().create().toJson(datos_);
	        response_text = String.join("", "\"proyectos\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");

		}else if(accion.equals("getProyectoMetas")){
			Integer proyectoId = map.get("proyectoid")!=null ? Integer.parseInt(map.get("proyectoid")) : 0;
			
			List<stproyectometa> lstproyectometas = obtenerListadoProyectoMetas(proyectoId, usuario);
			
			response_text=new GsonBuilder().serializeNulls().create().toJson(lstproyectometas);
	        response_text = String.join("", "\"proyectometas\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
	        
	        response.setHeader("Content-Encoding", "gzip");
			response.setCharacterEncoding("UTF-8");

	        OutputStream output = response.getOutputStream();
			GZIPOutputStream gz = new GZIPOutputStream(output);
	        gz.write(response_text.getBytes("UTF-8"));
	        gz.close();
	        output.close();

		}
		else if(accion.equals("getProyectoPorId")){
			Integer id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			Proyecto proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
			
			datos temp = new datos();
			if (proyecto!=null){
				temp.id = proyecto.getId();
				temp.nombre = proyecto.getNombre();
				temp.proyectotipoid = proyecto.getProyectoTipo().getId();
				temp.proyectotipo = proyecto.getProyectoTipo().getNombre();
				temp.unidadejecutora = proyecto.getUnidadEjecutora().getNombre();
				temp.unidadejecutoraid = proyecto.getUnidadEjecutora().getUnidadEjecutora();
				temp.cooperante = proyecto.getCooperante().getNombre();
				temp.cooperanteid = proyecto.getCooperante().getId();	
			}
			response_text=new GsonBuilder().serializeNulls().create().toJson(temp);
	        response_text = String.join("", "\"proyecto\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");


		}
		else{
			response_text = "{ \"success\": false }";
		}

		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");

        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(response_text.getBytes("UTF-8"));
        gz.close();
        output.close();
	}

	
	private List<stproyectometa> obtenerListadoProyectoMetas(int proyectoId, String usuario){
		Proyecto proyecto = ProyectoDAO.getProyectoPorId(proyectoId, usuario);
		List<stproyectometa> lstproyectometas = new ArrayList<>();
		if (proyecto!=null){
			stproyectometa proyectometa = new stproyectometa();
			proyectometa.id = proyecto.getId();
			proyectometa.objetoTipo = OBJETO_ID_PROYECTO;
			proyectometa.objetoTipoNombre = "Prestamo";
			proyectometa.nombre = proyecto.getNombre();
			proyectometa.objetoPadre = 0;
			proyectometa.unidadDeMedida = "";
			proyectometa.metaPlanificada = "";
			proyectometa.metaReal = "";
			proyectometa.metaAnualPlanificada = "";
			proyectometa.metaAnualReal = "";
			proyectometa.lineaBase = "";
			proyectometa.metaFinal = "";
			lstproyectometas.add(proyectometa);
		}
		List<Componente> componentes = ComponenteDAO.getComponentesPaginaPorProyecto(0, 0, proyectoId,
				null, null, null, null, null, usuario);
		for (Componente componente : componentes){
			stproyectometa proyectometa = new stproyectometa();
			proyectometa.id = componente.getId();
			proyectometa.objetoTipo = OBJETO_ID_COMPONENTE;
			proyectometa.objetoTipoNombre = "Componente";
			proyectometa.nombre = componente.getNombre();
			proyectometa.objetoPadre = proyectoId;
			proyectometa.unidadDeMedida = "";
			proyectometa.metaPlanificada = "";
			proyectometa.metaReal = "";
			proyectometa.metaRealId = "";
			proyectometa.metaRealFecha = "";
			proyectometa.metaAnualPlanificada = "";
			proyectometa.metaAnualReal = "";
			proyectometa.metaAnualRealFecha = "";
			proyectometa.metaAnualRealId = "";
			proyectometa.lineaBase = "";
			proyectometa.metaFinal = "";
			lstproyectometas.add(proyectometa);
			
			List<Producto> productos = ProductoDAO.getProductosPagina(0, 0, componente.getId(),
					null, null, null, null, null, usuario);
			for (Producto producto : productos){
				proyectometa = new stproyectometa();
				proyectometa.id = producto.getId();
				proyectometa.objetoTipo = OBJETO_ID_PRODUCTO;
				proyectometa.objetoTipoNombre = "Producto";
				proyectometa.nombre = producto.getNombre();
				proyectometa.objetoPadre = componente.getId();
				
				//Obteniendo meta real
				List<Meta> metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_REAL, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					proyectometa.metaRealId = Meta.getId().toString();
					proyectometa.metaRealFecha = Utils.formatDateHour(metavalor.getId().getFecha());
					proyectometa.datoTipoId = Meta.getDatoTipo().getId();
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.metaReal = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.metaReal = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.metaReal = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.metaReal = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				//Obteniendo meta planificada
				metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_PLANIFICADA, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.metaPlanificada = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.metaPlanificada = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.metaPlanificada = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.metaPlanificada = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				//Obteniendo meta anual real
				metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_ANUALREAL, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					proyectometa.metaAnualRealId = Meta.getId().toString();
					proyectometa.metaAnualRealFecha = Utils.formatDateHour(metavalor.getId().getFecha());
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.metaAnualReal = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.metaAnualReal = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.metaAnualReal = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.metaAnualReal = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				//Obteniendo meta anual planificada
				metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_ANUALPLANIFICADA, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.metaAnualPlanificada = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.metaAnualPlanificada = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.metaAnualPlanificada = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.metaAnualPlanificada = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				//Obteniendo linea base
				metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_LINEABASE, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.lineaBase = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.lineaBase = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.lineaBase = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.lineaBase = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				//Obteniendo meta final
				metas = MetaDAO.getMetasPagina(-1, -1, producto.getId(), OBJETO_ID_PRODUCTO, null, META_ID_FINAL, null, null, null, null);
				if (metas!= null && !metas.isEmpty()){
					Meta Meta = metas.get(0);
					proyectometa.unidadDeMedida = Meta.getMetaUnidadMedida().getNombre();
					MetaValor metavalor = MetaValorDAO.getMetaValorPorMetaid(Meta.getId());
					switch(metas.get(0).getDatoTipo().getId()){
						case DATOTIPO_TEXTO: 
							proyectometa.metaFinal = metavalor.getValorString();
							break;
						case DATOTIPO_ENTERO: 
							proyectometa.metaFinal = metavalor.getValorEntero().toString();
							break;
						case DATOTIPO_DECIMAL: 
							proyectometa.metaFinal = metavalor.getValorDecimal().toString();
							break;
						case DATOTIPO_BOOLEANO: 

							break;
						case DATOTIPO_FECHA: 
							proyectometa.metaFinal = Utils.formatDateHour(metavalor.getValorTiempo());
							break;

					}
				}
				
				lstproyectometas.add(proyectometa);				
			}
		}
		return lstproyectometas;
	}
}
