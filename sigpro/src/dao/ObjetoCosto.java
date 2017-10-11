package dao;

import java.math.BigDecimal;
import org.joda.time.DateTime;

public class ObjetoCosto {
	String nombre;
	Integer objeto_id;
	int objeto_tipo;
	Integer nivel;
	DateTime fecha_inicial;
	DateTime fecha_final;
	stanio[] anios; 
	Integer acumulacion_costoid;
	BigDecimal costo;
	Integer programa;
	Integer subprograma; 
	Integer proyecto;
	Integer actividad;
	Integer obra;
	String treePath;
	
	public ObjetoCosto(String nombre, Integer objeto_id, int objeto_tipo, Integer nivel, DateTime fecha_inicial,
			DateTime fecha_final, stanio[] anios, Integer acumulacion_costoid, BigDecimal costo, Integer programa,
			Integer subprograma, Integer proyecto, Integer actividad, Integer obra, String treePath) {
		super();
		this.nombre = nombre;
		this.objeto_id = objeto_id;
		this.objeto_tipo = objeto_tipo;
		this.nivel = nivel;
		this.fecha_inicial = fecha_inicial;
		this.fecha_final = fecha_final;
		this.anios = anios;
		this.acumulacion_costoid = acumulacion_costoid;
		this.costo = costo;
		this.programa = programa;
		this.subprograma = subprograma;
		this.proyecto = proyecto;
		this.actividad = actividad;
		this.obra = obra;
		this.treePath = treePath;
	}

	
	public void inicializarStanio (Integer anioInicial, Integer anioFinal){		
		int longitudArrelgo = anioFinal - anioInicial+1;
		
		anios = new stanio[longitudArrelgo];
		
		for (int i = 0;i <longitudArrelgo; i++){
			stanio temp = new stanio();
			for(int m=0; m<12; m++){
				temp.mes[m]= new stpresupuesto();
				temp.mes[m].planificado = new BigDecimal(0);
				temp.mes[m].real =  new BigDecimal(0);
			}
			temp.anio = anioInicial+i;
			anios[i] = temp;
		}
	}
	
	public class stpresupuesto{
		public BigDecimal planificado;
		public BigDecimal real;
	}
	
	public class stanio{
		public stpresupuesto[] mes = new stpresupuesto[12];
		public Integer anio;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getObjeto_id() {
		return objeto_id;
	}

	public void setObjeto_id(Integer objeto_id) {
		this.objeto_id = objeto_id;
	}

	public int getObjeto_tipo() {
		return objeto_tipo;
	}

	public void setObjeto_tipo(int objeto_tipo) {
		this.objeto_tipo = objeto_tipo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public DateTime getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(DateTime fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public DateTime getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(DateTime fecha_final) {
		this.fecha_final = fecha_final;
	}

	public stanio[] getAnios() {
		return anios;
	}

	public void setAnios(stanio[] anios) {
		this.anios = anios;
	}

	public Integer getAcumulacion_costoid() {
		return acumulacion_costoid;
	}

	public void setAcumulacion_costoid(Integer acumulacion_costoid) {
		this.acumulacion_costoid = acumulacion_costoid;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Integer getPrograma() {
		return programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	public Integer getSubprograma() {
		return subprograma;
	}

	public void setSubprograma(Integer subprograma) {
		this.subprograma = subprograma;
	}

	public Integer getProyecto() {
		return proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getActividad() {
		return actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	public Integer getObra() {
		return obra;
	}

	public void setObra(Integer obra) {
		this.obra = obra;
	}
	
	public String getTreePath(){
		return treePath;
	}
	
	public void setTreePath(String treePath){
		this.treePath = treePath;
	}
}