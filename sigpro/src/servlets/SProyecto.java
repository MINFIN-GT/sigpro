package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import dao.ProyectoDAO;
import dao.ProyectoPropiedadDAO;
import dao.ProyectoPropiedadValorDAO;
import pojo.Cooperante;
import pojo.Proyecto;
import pojo.ProyectoPropedadValor;
import pojo.ProyectoPropedadValorId;
import pojo.ProyectoPropiedad;
import pojo.ProyectoTipo;
import pojo.UnidadEjecutora;
import utilities.Utils;

/**
 * Servlet implementation class SProyecto
 */
@WebServlet("/SProyecto")
public class SProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	class datos {
		int id;
		String nombre;
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
	};

	class stdatadinamico {
		String id;
		String tipo;
		String label;
		String valor;
		String valor_f;
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
				datos_.add(dato);
			}

			response_text = new GsonBuilder().serializeNulls().create().toJson(datos_);
			response_text = String.join("", "\"entidades\":", response_text);
			response_text = String.join("", "{\"success\":true,", response_text, "}");


 
		}else if(accion.equals("getProyectoPagina")){
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
				String descripcion = map.get("descripcion");

				Integer programa = map.get("programa")!=null ? Integer.parseInt(map.get("programa")) : null;
				Integer subPrograma = map.get("subprograma")!=null ?  Integer.parseInt(map.get("subprograma")) : null;
				Integer proyecto_ = map.get("proyecto_")!=null ? Integer.parseInt(map.get("proyecto_")) : null;
				Integer actividad = map.get("actividad")!=null ? Integer.parseInt(map.get("actividad")):null;
				Integer obra = map.get("obra")!=null ? Integer.parseInt(map.get("obra")):null;
				Integer fuente = map.get("fuente")!=null ? Integer.parseInt(map.get("fuente")):null;

				ProyectoTipo proyectoTipo = new ProyectoTipo();
				proyectoTipo.setId(map.get("proyectotipoid") !=null ? Integer.parseInt(map.get("proyectotipoid")): null);

				UnidadEjecutora unidadEjecutora = new UnidadEjecutora();
				unidadEjecutora.setUnidadEjecutora(map.get("unidadejecutoraid")!=null ? Integer.parseInt(map.get("unidadejecutoraid")): null);

				Cooperante cooperante = new Cooperante();
				cooperante.setId(map.get("cooperanteid")!=null ? Integer.parseInt(map.get("cooperanteid")): null);

				type = new TypeToken<List<stdatadinamico>>() {
				}.getType();

				List<stdatadinamico> datos = gson.fromJson(map.get("datadinamica"), type);


				if(esnuevo){
					proyecto = new Proyecto(cooperante, proyectoTipo, unidadEjecutora, nombre, descripcion
							, usuario, null, new DateTime().toDate(), null, 1, snip
							,programa , subPrograma, proyecto_,actividad, obra, fuente,
							null, null, null, null, null);

				}else{
					proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
					proyecto.setNombre(nombre);
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
									break;
								case 5:
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									valor.setValorTiempo(data.valor_f.compareTo("")!=0 ? sdf.parse(data.valor_f) : null);
									break;
							}
							result = (result && ProyectoPropiedadValorDAO.guardarProyectoPropiedadValor(valor));
						}
					}
				}
				response_text = String.join("","{ \"success\": ",(result ? "true" : "false"),", "
						+ "\"id\": " + proyecto.getId() +" }");
			}else
				response_text = "{ \"success\": false }";

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
		else if(accion.equals("obtenerProyectoPorId")){
			Integer id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			Proyecto proyecto = ProyectoDAO.getProyectoPorId(id,usuario);
			response_text = String.join("","{ \"success\": ",(proyecto!=null && proyecto.getId()!=null ? "true" : "false"),", "
					+ "\"id\": " + (proyecto!=null ? proyecto.getId():"") +", "
					+ "\"nombre\": \"" + (proyecto!=null ? proyecto.getNombre():"") +"\" }");

		}else{
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

}
