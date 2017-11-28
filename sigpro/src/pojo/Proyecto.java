package pojo;
// Generated Nov 28, 2017 3:59:14 PM by Hibernate Tools 5.2.3.Final

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name = "proyecto", catalog = "sipro")
public class Proyecto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5359330703385971260L;
	private Integer id;
	private AcumulacionCosto acumulacionCosto;
	private Colaborador colaborador;
	private Etiqueta etiqueta;
	private Prestamo prestamo;
	private ProyectoTipo proyectoTipo;
	private UnidadEjecutora unidadEjecutora;
	private String nombre;
	private String descripcion;
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
	private String latitud;
	private String longitud;
	private String objetivo;
	private String enunciadoAlcance;
	private BigDecimal costo;
	private String objetivoEspecifico;
	private String visionGeneral;
	private Integer renglon;
	private Integer ubicacionGeografica;
	private Date fechaInicio;
	private Date fechaFin;
	private int duracion;
	private String duracionDimension;
	private Integer orden;
	private String treePath;
	private Integer nivel;
	private Integer ejecucionFisicaReal;
	private Integer projectCargado;
	private String observaciones;
	private Integer coordinador;
	private Date fechaElegibilidad;
	private Date fechaCierre;
	private Date fechaInicioReal;
	private Date fechaFinReal;
	private Set<Desembolso> desembolsos = new HashSet<Desembolso>(0);
	private Set<Hito> hitos = new HashSet<Hito>(0);
	private PepDetalle pepDetalle;
	private Set<ProgramaProyecto> programaProyectos = new HashSet<ProgramaProyecto>(0);
	private Set<ProyectoMiembro> proyectoMiembros = new HashSet<ProyectoMiembro>(0);
	private Set<ProyectoImpacto> proyectoImpactos = new HashSet<ProyectoImpacto>(0);
	private Set<ProyectoRolColaborador> proyectoRolColaboradors = new HashSet<ProyectoRolColaborador>(0);
	private Set<ProyectoPropiedadValor> proyectoPropiedadValors = new HashSet<ProyectoPropiedadValor>(0);
	private Set<ProyectoUsuario> proyectoUsuarios = new HashSet<ProyectoUsuario>(0);
	private Set<Componente> componentes = new HashSet<Componente>(0);

	public Proyecto() {
	}

	public Proyecto(Etiqueta etiqueta, ProyectoTipo proyectoTipo, String nombre, String usuarioCreo, Date fechaCreacion,
			int estado, int duracion) {
		this.etiqueta = etiqueta;
		this.proyectoTipo = proyectoTipo;
		this.nombre = nombre;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.duracion = duracion;
	}

	public Proyecto(AcumulacionCosto acumulacionCosto, Colaborador colaborador, Etiqueta etiqueta, Prestamo prestamo,
			ProyectoTipo proyectoTipo, UnidadEjecutora unidadEjecutora, String nombre, String descripcion,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado,
			Long snip, Integer programa, Integer subprograma, Integer proyecto, Integer actividad, Integer obra,
			String latitud, String longitud, String objetivo, String enunciadoAlcance, BigDecimal costo,
			String objetivoEspecifico, String visionGeneral, Integer renglon, Integer ubicacionGeografica,
			Date fechaInicio, Date fechaFin, int duracion, String duracionDimension, Integer orden, String treePath,
			Integer nivel, Integer ejecucionFisicaReal, Integer projectCargado, String observaciones,
			Integer coordinador, Date fechaElegibilidad, Date fechaCierre, Date fechaInicioReal, Date fechaFinReal,
			Set<Desembolso> desembolsos, Set<Hito> hitos, PepDetalle pepDetalle,
			Set<ProgramaProyecto> programaProyectos, Set<ProyectoMiembro> proyectoMiembros,
			Set<ProyectoImpacto> proyectoImpactos, Set<ProyectoRolColaborador> proyectoRolColaboradors,
			Set<ProyectoPropiedadValor> proyectoPropiedadValors, Set<ProyectoUsuario> proyectoUsuarios,
			Set<Componente> componentes) {
		this.acumulacionCosto = acumulacionCosto;
		this.colaborador = colaborador;
		this.etiqueta = etiqueta;
		this.prestamo = prestamo;
		this.proyectoTipo = proyectoTipo;
		this.unidadEjecutora = unidadEjecutora;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
		this.latitud = latitud;
		this.longitud = longitud;
		this.objetivo = objetivo;
		this.enunciadoAlcance = enunciadoAlcance;
		this.costo = costo;
		this.objetivoEspecifico = objetivoEspecifico;
		this.visionGeneral = visionGeneral;
		this.renglon = renglon;
		this.ubicacionGeografica = ubicacionGeografica;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracion = duracion;
		this.duracionDimension = duracionDimension;
		this.orden = orden;
		this.treePath = treePath;
		this.nivel = nivel;
		this.ejecucionFisicaReal = ejecucionFisicaReal;
		this.projectCargado = projectCargado;
		this.observaciones = observaciones;
		this.coordinador = coordinador;
		this.fechaElegibilidad = fechaElegibilidad;
		this.fechaCierre = fechaCierre;
		this.fechaInicioReal = fechaInicioReal;
		this.fechaFinReal = fechaFinReal;
		this.desembolsos = desembolsos;
		this.hitos = hitos;
		this.pepDetalle = pepDetalle;
		this.programaProyectos = programaProyectos;
		this.proyectoMiembros = proyectoMiembros;
		this.proyectoImpactos = proyectoImpactos;
		this.proyectoRolColaboradors = proyectoRolColaboradors;
		this.proyectoPropiedadValors = proyectoPropiedadValors;
		this.proyectoUsuarios = proyectoUsuarios;
		this.componentes = componentes;
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
	@JoinColumn(name = "acumulacion_costoid")
	public AcumulacionCosto getAcumulacionCosto() {
		return this.acumulacionCosto;
	}

	public void setAcumulacionCosto(AcumulacionCosto acumulacionCosto) {
		this.acumulacionCosto = acumulacionCosto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "director_proyecto")
	public Colaborador getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_clase", nullable = false)
	public Etiqueta getEtiqueta() {
		return this.etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestamoid")
	public Prestamo getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_tipoid", nullable = false)
	public ProyectoTipo getProyectoTipo() {
		return this.proyectoTipo;
	}

	public void setProyectoTipo(ProyectoTipo proyectoTipo) {
		this.proyectoTipo = proyectoTipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "unidad_ejecutoraunidad_ejecutora", referencedColumnName = "unidad_ejecutora"),
			@JoinColumn(name = "entidad", referencedColumnName = "entidadentidad"),
			@JoinColumn(name = "ejercicio", referencedColumnName = "ejercicio") })
	public UnidadEjecutora getUnidadEjecutora() {
		return this.unidadEjecutora;
	}

	public void setUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
		this.unidadEjecutora = unidadEjecutora;
	}

	@Column(name = "nombre", nullable = false, length = 2000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 4000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@Column(name = "objetivo", length = 4000)
	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	@Column(name = "enunciado_alcance", length = 4000)
	public String getEnunciadoAlcance() {
		return this.enunciadoAlcance;
	}

	public void setEnunciadoAlcance(String enunciadoAlcance) {
		this.enunciadoAlcance = enunciadoAlcance;
	}

	@Column(name = "costo", precision = 15)
	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	@Column(name = "objetivo_especifico", length = 4000)
	public String getObjetivoEspecifico() {
		return this.objetivoEspecifico;
	}

	public void setObjetivoEspecifico(String objetivoEspecifico) {
		this.objetivoEspecifico = objetivoEspecifico;
	}

	@Column(name = "vision_general", length = 45)
	public String getVisionGeneral() {
		return this.visionGeneral;
	}

	public void setVisionGeneral(String visionGeneral) {
		this.visionGeneral = visionGeneral;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio", length = 19)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_fin", length = 19)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "duracion", nullable = false)
	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Column(name = "duracion_dimension", length = 1)
	public String getDuracionDimension() {
		return this.duracionDimension;
	}

	public void setDuracionDimension(String duracionDimension) {
		this.duracionDimension = duracionDimension;
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

	@Column(name = "ejecucion_fisica_real")
	public Integer getEjecucionFisicaReal() {
		return this.ejecucionFisicaReal;
	}

	public void setEjecucionFisicaReal(Integer ejecucionFisicaReal) {
		this.ejecucionFisicaReal = ejecucionFisicaReal;
	}

	@Column(name = "project_cargado")
	public Integer getProjectCargado() {
		return this.projectCargado;
	}

	public void setProjectCargado(Integer projectCargado) {
		this.projectCargado = projectCargado;
	}

	@Column(name = "observaciones", length = 2000)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "coordinador")
	public Integer getCoordinador() {
		return this.coordinador;
	}

	public void setCoordinador(Integer coordinador) {
		this.coordinador = coordinador;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_elegibilidad", length = 19)
	public Date getFechaElegibilidad() {
		return this.fechaElegibilidad;
	}

	public void setFechaElegibilidad(Date fechaElegibilidad) {
		this.fechaElegibilidad = fechaElegibilidad;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_cierre", length = 19)
	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio_real", length = 19)
	public Date getFechaInicioReal() {
		return this.fechaInicioReal;
	}

	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_fin_real", length = 19)
	public Date getFechaFinReal() {
		return this.fechaFinReal;
	}

	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Desembolso> getDesembolsos() {
		return this.desembolsos;
	}

	public void setDesembolsos(Set<Desembolso> desembolsos) {
		this.desembolsos = desembolsos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Hito> getHitos() {
		return this.hitos;
	}

	public void setHitos(Set<Hito> hitos) {
		this.hitos = hitos;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public PepDetalle getPepDetalle() {
		return this.pepDetalle;
	}

	public void setPepDetalle(PepDetalle pepDetalle) {
		this.pepDetalle = pepDetalle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProgramaProyecto> getProgramaProyectos() {
		return this.programaProyectos;
	}

	public void setProgramaProyectos(Set<ProgramaProyecto> programaProyectos) {
		this.programaProyectos = programaProyectos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoMiembro> getProyectoMiembros() {
		return this.proyectoMiembros;
	}

	public void setProyectoMiembros(Set<ProyectoMiembro> proyectoMiembros) {
		this.proyectoMiembros = proyectoMiembros;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoImpacto> getProyectoImpactos() {
		return this.proyectoImpactos;
	}

	public void setProyectoImpactos(Set<ProyectoImpacto> proyectoImpactos) {
		this.proyectoImpactos = proyectoImpactos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoRolColaborador> getProyectoRolColaboradors() {
		return this.proyectoRolColaboradors;
	}

	public void setProyectoRolColaboradors(Set<ProyectoRolColaborador> proyectoRolColaboradors) {
		this.proyectoRolColaboradors = proyectoRolColaboradors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoPropiedadValor> getProyectoPropiedadValors() {
		return this.proyectoPropiedadValors;
	}

	public void setProyectoPropiedadValors(Set<ProyectoPropiedadValor> proyectoPropiedadValors) {
		this.proyectoPropiedadValors = proyectoPropiedadValors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoUsuario> getProyectoUsuarios() {
		return this.proyectoUsuarios;
	}

	public void setProyectoUsuarios(Set<ProyectoUsuario> proyectoUsuarios) {
		this.proyectoUsuarios = proyectoUsuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

}
