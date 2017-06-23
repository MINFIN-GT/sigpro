package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.ActividadDAO;
import dao.ComponenteDAO;
import dao.HitoDAO;
import dao.HitoResultadoDAO;
import dao.ProductoDAO;
import dao.SubproductoDAO;
import pojo.Actividad;
import pojo.Componente;
import pojo.Hito;
import pojo.HitoResultado;
import pojo.Producto;
import pojo.Subproducto;
import utilities.CLogger;
import utilities.Utils;

@WebServlet("/SAvanceActividades")
public class SAvanceActividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int OBJETO_ID_PROYECTO = 1;
	private static int OBJETO_ID_COMPONENTE = 2;
	private static int OBJETO_ID_PRODUCTO = 3;
	private static int OBJETO_ID_SUBPRODUCTO = 4;
	private static int OBJETO_ID_SUBACTIVIDAD = 5;
    
	class stActividad{
		int id;
		int porcentajeAvance;
		String fechaInicio;
		String fechaFin;
	}
	
	class stAvance{
		String nombre;
		String sinIniciar;
		String proceso;
		String completadas;
		String retrasadas;
	}
	
    public SAvanceActividades() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		Map<String, String> map = gson.fromJson(sb.toString(), type);
		String accion = map.get("accion")!=null ? map.get("accion") : "";
		String response_text = "";
		Integer idPrestamo = Utils.String2Int(map.get("idPrestamo"),0);
		String fechaCorte = map.get("fechaCorte");
		if (accion.equals("getAvance")){
			double  totalActividades = 0;
			double  totalSinIniciar = 0;
			double  totalEnProceso = 0;
			double  totalCompletadas = 0;
			double  totalRetrasadas = 0;
			Date inicio = new Date();
			Date fin = new Date();
			Date Corte = new Date();
			List<stAvance> listaResult = new ArrayList<stAvance>();
			stAvance temp = new stAvance();
			DecimalFormat df2 = new DecimalFormat("###.##");
			
			try{
				List<stActividad> actividades = getActividadesProyecto(idPrestamo, usuario);
				if (actividades != null){
					totalActividades = actividades.size();
				
					for (stActividad actividad : actividades){
						
						inicio = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
			                    .parse(actividad.fechaInicio);
						
						fin = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
			                    .parse(actividad.fechaFin);
						
						Corte = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
			                    .parse(fechaCorte);
						
						if (actividad.porcentajeAvance == 0){
							totalSinIniciar++;
						}
						
						if(Corte.after(inicio) && Corte.before(fin) && actividad.porcentajeAvance > 0 && actividad.porcentajeAvance < 100){
							totalEnProceso++;
						}
						
						if (Corte.after(fin) && actividad.porcentajeAvance > 0 && actividad.porcentajeAvance < 100 ){
							totalRetrasadas++;
						}
						
						if(actividad.porcentajeAvance == 100){
							totalCompletadas++;
						}
					}
					
					temp.nombre = "Cantidad";
					temp.sinIniciar = new Double(totalSinIniciar).toString();
					temp.proceso = new Double(totalEnProceso).toString();
					temp.completadas = new Double(totalCompletadas).toString();
					temp.retrasadas = new Double(totalRetrasadas).toString();
					
					listaResult.add(temp);

					totalSinIniciar = (totalSinIniciar/totalActividades)*100;
					totalEnProceso = (totalEnProceso/totalActividades)*100;
					totalCompletadas = (totalCompletadas/totalActividades)*100;
					totalRetrasadas = (totalRetrasadas/totalActividades)*100;
					
					if (Double.isNaN(totalSinIniciar)){
						totalSinIniciar = 0;
					}
					
					if (Double.isNaN(totalEnProceso)){
						totalEnProceso = 0;
					}
					
					if (Double.isNaN(totalCompletadas)){
						totalCompletadas = 0;
					}
					
					if (Double.isNaN(totalRetrasadas)){
						totalRetrasadas = 0;
					}
					
					temp = new stAvance();
					temp.nombre = "Porcentaje";
					temp.sinIniciar = df2.format(totalSinIniciar);
					temp.proceso = df2.format(totalEnProceso);
					temp.completadas = df2.format(totalCompletadas);
					temp.retrasadas = df2.format(totalRetrasadas);
					
					listaResult.add(temp);
					
					response_text = new GsonBuilder().serializeNulls().create().toJson(listaResult);
					response_text = String.join("", ",\"actividades\":",response_text);
					response_text = String.join("", ",\"totalActividades\":" + totalActividades,response_text);
				}
				
				List<Hito> hitos = HitoDAO.getHitosPaginaPorProyecto(0, 0, idPrestamo, null, null, null, null, null);
				totalSinIniciar = 0;
				totalRetrasadas = 0;
				totalCompletadas = 0;
				
				if(hitos != null && hitos.size() > 0){
					listaResult = new ArrayList<stAvance>();
					double  totalHitos = hitos.size();
					
					Corte = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
							.parse(fechaCorte);
					
					Date fechaHito = new Date();
					
					for(Hito hito : hitos){
						fechaHito = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
								.parse(Utils.formatDate(hito.getFecha()));
						
						HitoResultado hitoResultado = HitoResultadoDAO.getHitoResultadoActivoPorHito(hito.getId());
						
						if(hitoResultado != null){
							if(Corte.before(fechaHito) && hitoResultado.getValorEntero() == 0){
								totalSinIniciar++;
							}
							
							if(Corte.after(fechaHito) && hitoResultado.getValorEntero() == 0){
								totalRetrasadas++;
							}
							
							if(Corte.after(fechaHito) && hitoResultado.getValorEntero() == 1){
								totalCompletadas++;
							}
						}else{
							if (Corte.before(fechaHito)){
								totalSinIniciar++;
							}
							
							if (Corte.after(fechaHito)){
								totalRetrasadas++;
							}
						}
					}
					
					temp = new stAvance();
					temp.nombre = "Cantidad";
					temp.sinIniciar = new Double(totalSinIniciar).toString();
					temp.completadas = new Double(totalRetrasadas).toString();
					temp.retrasadas = new Double(totalCompletadas).toString();
					
					listaResult.add(temp);
					
					totalSinIniciar = (totalSinIniciar/totalHitos)*100;
					totalCompletadas = (totalCompletadas/totalHitos)*100;
					totalRetrasadas = (totalRetrasadas/totalHitos)*100;
					
					temp = new stAvance();
					temp.nombre = "Porcentaje";
					temp.sinIniciar = df2.format(totalSinIniciar);
					temp.completadas = df2.format(totalCompletadas);
					temp.retrasadas = df2.format(totalRetrasadas);
					
					listaResult.add(temp);
					
					String response_hitos = new GsonBuilder().serializeNulls().create().toJson(listaResult);
					response_text += String.join("", ",\"hitos\":",response_hitos);
					response_text = String.join("", ",\"totalHitos\":" + totalHitos,response_text);
				}
				
				List<Producto> productos = ProductoDAO.getProductosPorProyecto(idPrestamo, usuario);
				if(productos != null  && productos.size() > 0){
					listaResult = new ArrayList<stAvance>();
					int totalProductos = productos.size();
					
					for(Producto producto : productos){
						totalSinIniciar = 0;
						totalEnProceso = 0;
						totalRetrasadas = 0;
						totalCompletadas = 0;
						
						actividades = getActividadesProducto(producto, usuario);
						
						if(actividades != null){
							totalActividades = actividades.size();
							
							for (stActividad actividad : actividades){
								
								inicio = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
										.parse(actividad.fechaInicio);
								
								fin = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
										.parse(actividad.fechaFin);
								
								Corte = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
										.parse(fechaCorte);
								
								if (actividad.porcentajeAvance == 0){
									totalSinIniciar++;
								}
								
								if(Corte.after(inicio) && Corte.before(fin) && actividad.porcentajeAvance > 0 && actividad.porcentajeAvance < 100){
									totalEnProceso++;
								}
								
								if (Corte.after(fin) && actividad.porcentajeAvance > 0 && actividad.porcentajeAvance < 100 ){
									totalRetrasadas++;
								}
								
								if(actividad.porcentajeAvance == 100){
									totalCompletadas++;
								}
							}
							
							
							totalSinIniciar = (totalSinIniciar/totalActividades)*100;
							totalEnProceso = (totalEnProceso/totalActividades)*100;
							totalCompletadas = (totalCompletadas/totalActividades)*100;
							totalRetrasadas = (totalRetrasadas/totalActividades)*100;
							
							if (Double.isNaN(totalSinIniciar)){
								totalSinIniciar = 0;
							}
							
							if (Double.isNaN(totalEnProceso)){
								totalEnProceso = 0;
							}
							
							if (Double.isNaN(totalCompletadas)){
								totalCompletadas = 0;
							}
							
							if (Double.isNaN(totalRetrasadas)){
								totalRetrasadas = 0;
							}
							
							temp = new stAvance();
							temp.nombre = producto.getNombre();
							temp.sinIniciar = df2.format(totalSinIniciar);
							temp.proceso = df2.format(totalEnProceso);
							temp.completadas = df2.format(totalCompletadas);
							temp.retrasadas = df2.format(totalRetrasadas);
							
							listaResult.add(temp);
						}
					}
					
					String response_productos = new GsonBuilder().serializeNulls().create().toJson(listaResult);
					response_text += String.join("", ",\"productos\":",response_productos);
					response_text = String.join("", ",\"totalProductos\":" + totalProductos,response_text);
				}
			}catch (Throwable e) {
				e.printStackTrace();
		    }
			
			response_text = String.join("", "{\"success\":true ", response_text, "}");
		}
		
		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");

        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(response_text.getBytes("UTF-8"));
        gz.close();
        output.close();
		
	}
	
	private List<stActividad> getActividadesProducto(Producto producto, String usuario){
		List<stActividad> result = new ArrayList<stActividad>();
		try{
			List<Subproducto> subproductos = SubproductoDAO.getSubproductosPagina(0, 0, producto.getId(),
					null, null, null, null, null, usuario);
			for (Subproducto subproducto : subproductos){						
				List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, subproducto.getId(), OBJETO_ID_SUBPRODUCTO,
						null, null, null, null, null, usuario);
				for (Actividad actividad : actividades ){
					result = ObtenerActividades(actividad,usuario, result);
				}
			}
			List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, producto.getId(), OBJETO_ID_PRODUCTO,
					null, null, null, null, null, usuario);
			for (Actividad actividad : actividades ){						
				result = ObtenerActividades(actividad,usuario, result);
			}
			return result;
		}catch(Throwable e){
			CLogger.write("1", SReporte.class, e);
			return null;
		}
	}
	
	
	private List<stActividad> getActividadesProyecto(int idPrestamo, String usuario){
		List<stActividad> result = new ArrayList<stActividad>();
		
		try{
			List<Componente> componentes = ComponenteDAO.getComponentesPaginaPorProyecto(0, 0, idPrestamo,
					null, null, null, null, null, usuario);
			for (Componente componente : componentes){
				List<Producto> productos = ProductoDAO.getProductosPagina(0, 0, componente.getId(),
						null, null, null, null, null, usuario);
				for (Producto producto : productos){
					List<Subproducto> subproductos = SubproductoDAO.getSubproductosPagina(0, 0, producto.getId(),
							null, null, null, null, null, usuario);
					for (Subproducto subproducto : subproductos){						
						List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, subproducto.getId(), OBJETO_ID_SUBPRODUCTO,
								null, null, null, null, null, usuario);
						for (Actividad actividad : actividades ){
							result = ObtenerActividades(actividad,usuario, result);
						}
					}
					List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, producto.getId(), OBJETO_ID_PRODUCTO,
							null, null, null, null, null, usuario);
					for (Actividad actividad : actividades ){						
						result = ObtenerActividades(actividad,usuario, result);
					}
		
				}
				List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, componente.getId(), OBJETO_ID_COMPONENTE,
						null, null, null, null, null, usuario);
				for (Actividad actividad : actividades ){
					result = ObtenerActividades(actividad,usuario, result);
				}
			}
			List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, idPrestamo, OBJETO_ID_PROYECTO,
					null, null, null, null, null, usuario);
			for (Actividad actividad : actividades ){				
				result = ObtenerActividades(actividad,usuario, result);
			}
			
			//items = String.join("","{\"items\" : [", items,"]}");
			
			return result;
		}
		catch(Throwable e){
			CLogger.write("1", SReporte.class, e);
			return null;
		}
	}
	
	private List<stActividad> ObtenerActividades(Actividad actividad, String usuario, List<stActividad> items){
		List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, actividad.getId(), OBJETO_ID_SUBACTIVIDAD, 
				null, null,null, null, null, usuario);
		stActividad temp = new stActividad();
		temp.id = actividad.getId();
		temp.porcentajeAvance = actividad.getPorcentajeAvance();
		String[] fechaInicioFin = ActividadDAO.getFechaInicioFin(actividad, usuario).split(";");
		temp.fechaInicio = fechaInicioFin[0];
		temp.fechaFin = fechaInicioFin[1];
		
		items.add(temp);
		
		for(Actividad subActividad : actividades){
			items = ObtenerActividades(subActividad, usuario, items);
		}
		
		return items;
	}
}
