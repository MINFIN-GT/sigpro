package pojo;
// Generated Oct 2, 2017 1:09:17 AM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Actividad generated by hbm2java
 */
@Entity
@Table(name = "actividad", catalog = "sipro")
public class Actividad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7628925243171847983L;
	private Integer id;
	private ActividadTipo actividadTipo;
	private AcumulacionCosto acumulacionCosto;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private int porcentajeAvance;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private Long snip;
	private Integer programa;
	private Integer subprograma;
	private Integer proyecto;
	private Integer actividad;
	private Integer obra;
	private int objetoId;
	private int objetoTipo;
	private int duracion;
	private String duracionDimension;
	private Integer predObjetoId;
	private Integer predObjetoTipo;
	private String latitud;
	private String longitud;
	private BigDecimal costo;
	private Integer renglon;
	private Integer ubicacionGeografica;
	private Integer orden;
	private String treePath;
	private Integer nivel;
	private Integer proyectoBase;
	private Integer componenteBase;
	private Integer productoBase;
	private Set<ActividadPropiedadValor> actividadPropiedadValors = new HashSet<ActividadPropiedadValor>(0);
	private Set<ActividadUsuario> actividadUsuarios = new HashSet<ActividadUsuario>(0);

	public Actividad() {
	}

	public Actividad(ActividadTipo actividadTipo, String nombre, Date fechaInicio, Date fechaFin, int porcentajeAvance,
			String usuarioCreo, Date fechaCreacion, int estado, int objetoId, int objetoTipo, int duracion,
			String duracionDimension) {
		this.actividadTipo = actividadTipo;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeAvance = porcentajeAvance;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.objetoId = objetoId;
		this.objetoTipo = objetoTipo;
		this.duracion = duracion;
		this.duracionDimension = duracionDimension;
	}

	public Actividad(ActividadTipo actividadTipo, AcumulacionCosto acumulacionCosto, String nombre, String descripcion,
			Date fechaInicio, Date fechaFin, int porcentajeAvance, String usuarioCreo, String usuarioActualizo,
			Date fechaCreacion, Date fechaActualizacion, int estado, Long snip, Integer programa, Integer subprograma,
			Integer proyecto, Integer actividad, Integer obra, int objetoId, int objetoTipo, int duracion,
			String duracionDimension, Integer predObjetoId, Integer predObjetoTipo, String latitud, String longitud,
			BigDecimal costo, Integer renglon, Integer ubicacionGeografica, Integer orden, String treePath,
			Integer nivel, Integer proyectoBase, Integer componenteBase, Integer productoBase,
			Set<ActividadPropiedadValor> actividadPropiedadValors, Set<ActividadUsuario> actividadUsuarios) {
		this.actividadTipo = actividadTipo;
		this.acumulacionCosto = acumulacionCosto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeAvance = porcentajeAvance;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.snip = snip;
		this.programa = programa;
		this.subprograma = subprograma;
		this.proyecto = proyecto;
		this.actividad = actividad;
		this.obra = obra;
		this.objetoId = objetoId;
		this.objetoTipo = objetoTipo;
		this.duracion = duracion;
		this.duracionDimension = duracionDimension;
		this.predObjetoId = predObjetoId;
		this.predObjetoTipo = predObjetoTipo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.costo = costo;
		this.renglon = renglon;
		this.ubicacionGeografica = ubicacionGeografica;
		this.orden = orden;
		this.treePath = treePath;
		this.nivel = nivel;
		this.proyectoBase = proyectoBase;
		this.componenteBase = componenteBase;
		this.productoBase = productoBase;
		this.actividadPropiedadValors = actividadPropiedadValors;
		this.actividadUsuarios = actividadUsuarios;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad_tipoid", nullable = false)
	public ActividadTipo getActividadTipo() {
		return this.actividadTipo;
	}

	public void setActividadTipo(ActividadTipo actividadTipo) {
		this.actividadTipo = actividadTipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acumulacion_costo")
	public AcumulacionCosto getAcumulacionCosto() {
		return this.acumulacionCosto;
	}

	public void setAcumulacionCosto(AcumulacionCosto acumulacionCosto) {
		this.acumulacionCosto = acumulacionCosto;
	}

	@Column(name = "nombre", nullable = false, length = 1000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 1000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio", nullable = false, length = 19)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_fin", nullable = false, length = 19)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "porcentaje_avance", nullable = false)
	public int getPorcentajeAvance() {
		return this.porcentajeAvance;
	}

	public void setPorcentajeAvance(int porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	@Column(name = "usuario_creo", nullable = false, length = 30)
	public String getUsuarioCreo() {
		return this.usuarioCreo;
	}

	public void setUsuarioCreo(String usuarioCreo) {
		this.usuarioCreo = usuarioCreo;
	}

	@Column(name = "usuario_actualizo", length = 30)
	public String getUsuarioActualizo() {
		return this.usuarioActualizo;
	}

	public void setUsuarioActualizo(String usuarioActualizo) {
		this.usuarioActualizo = usuarioActualizo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false, length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion", length = 19)
	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Column(name = "snip")
	public Long getSnip() {
		return this.snip;
	}

	public void setSnip(Long snip) {
		this.snip = snip;
	}

	@Column(name = "programa")
	public Integer getPrograma() {
		return this.programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	@Column(name = "subprograma")
	public Integer getSubprograma() {
		return this.subprograma;
	}

	public void setSubprograma(Integer subprograma) {
		this.subprograma = subprograma;
	}

	@Column(name = "proyecto")
	public Integer getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "actividad")
	public Integer getActividad() {
		return this.actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	@Column(name = "obra")
	public Integer getObra() {
		return this.obra;
	}

	public void setObra(Integer obra) {
		this.obra = obra;
	}

	@Column(name = "objeto_id", nullable = false)
	public int getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(int objetoId) {
		this.objetoId = objetoId;
	}

	@Column(name = "objeto_tipo", nullable = false)
	public int getObjetoTipo() {
		return this.objetoTipo;
	}

	public void setObjetoTipo(int objetoTipo) {
		this.objetoTipo = objetoTipo;
	}

	@Column(name = "duracion", nullable = false)
	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Column(name = "duracion_dimension", nullable = false, length = 1)
	public String getDuracionDimension() {
		return this.duracionDimension;
	}

	public void setDuracionDimension(String duracionDimension) {
		this.duracionDimension = duracionDimension;
	}

	@Column(name = "pred_objeto_id")
	public Integer getPredObjetoId() {
		return this.predObjetoId;
	}

	public void setPredObjetoId(Integer predObjetoId) {
		this.predObjetoId = predObjetoId;
	}

	@Column(name = "pred_objeto_tipo")
	public Integer getPredObjetoTipo() {
		return this.predObjetoTipo;
	}

	public void setPredObjetoTipo(Integer predObjetoTipo) {
		this.predObjetoTipo = predObjetoTipo;
	}

	@Column(name = "latitud", length = 30)
	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	@Column(name = "longitud", length = 30)
	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Column(name = "costo", precision = 15)
	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	@Column(name = "renglon")
	public Integer getRenglon() {
		return this.renglon;
	}

	public void setRenglon(Integer renglon) {
		this.renglon = renglon;
	}

	@Column(name = "ubicacion_geografica")
	public Integer getUbicacionGeografica() {
		return this.ubicacionGeografica;
	}

	public void setUbicacionGeografica(Integer ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}

	@Column(name = "orden")
	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Column(name = "treePath", length = 1000)
	public String getTreePath() {
		return this.treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	@Column(name = "nivel")
	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	@Column(name = "proyecto_base")
	public Integer getProyectoBase() {
		return this.proyectoBase;
	}

	public void setProyectoBase(Integer proyectoBase) {
		this.proyectoBase = proyectoBase;
	}

	@Column(name = "componente_base")
	public Integer getComponenteBase() {
		return this.componenteBase;
	}

	public void setComponenteBase(Integer componenteBase) {
		this.componenteBase = componenteBase;
	}

	@Column(name = "producto_base")
	public Integer getProductoBase() {
		return this.productoBase;
	}

	public void setProductoBase(Integer productoBase) {
		this.productoBase = productoBase;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividad")
	public Set<ActividadPropiedadValor> getActividadPropiedadValors() {
		return this.actividadPropiedadValors;
	}

	public void setActividadPropiedadValors(Set<ActividadPropiedadValor> actividadPropiedadValors) {
		this.actividadPropiedadValors = actividadPropiedadValors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividad")
	public Set<ActividadUsuario> getActividadUsuarios() {
		return this.actividadUsuarios;
	}

	public void setActividadUsuarios(Set<ActividadUsuario> actividadUsuarios) {
		this.actividadUsuarios = actividadUsuarios;
	}

}
