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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name = "producto", catalog = "sipro")
public class Producto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623254536495238633L;
	private Integer id;
	private AcumulacionCosto acumulacionCosto;
	private Componente componente;
	private ProductoTipo productoTipo;
	private UnidadEjecutora unidadEjecutora;
	private String nombre;
	private String descripcion;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Integer estado;
	private Long snip;
	private Integer programa;
	private Integer subprograma;
	private Integer proyecto;
	private Integer actividad;
	private Integer obra;
	private String latitud;
	private String longitud;
	private Integer peso;
	private BigDecimal costo;
	private Integer renglon;
	private Integer ubicacionGeografica;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer duracion;
	private String duracionDimension;
	private Integer orden;
	private String treePath;
	private Integer nivel;
	private Set<ProductoUsuario> productoUsuarios = new HashSet<ProductoUsuario>(0);
	private Set<Subproducto> subproductos = new HashSet<Subproducto>(0);
	private Set<ProductoPropiedadValor> productoPropiedadValors = new HashSet<ProductoPropiedadValor>(0);

	public Producto() {
	}

	public Producto(Componente componente, ProductoTipo productoTipo, String nombre, String usuarioCreo,
			Date fechaCreacion) {
		this.componente = componente;
		this.productoTipo = productoTipo;
		this.nombre = nombre;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
	}

	public Producto(AcumulacionCosto acumulacionCosto, Componente componente, ProductoTipo productoTipo,
			UnidadEjecutora unidadEjecutora, String nombre, String descripcion, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, Integer estado, Long snip,
			Integer programa, Integer subprograma, Integer proyecto, Integer actividad, Integer obra, String latitud,
			String longitud, Integer peso, BigDecimal costo, Integer renglon, Integer ubicacionGeografica,
			Date fechaInicio, Date fechaFin, Integer duracion, String duracionDimension, Integer orden, String treePath,
			Integer nivel, Set<ProductoUsuario> productoUsuarios, Set<Subproducto> subproductos,
			Set<ProductoPropiedadValor> productoPropiedadValors) {
		this.acumulacionCosto = acumulacionCosto;
		this.componente = componente;
		this.productoTipo = productoTipo;
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
		this.peso = peso;
		this.costo = costo;
		this.renglon = renglon;
		this.ubicacionGeografica = ubicacionGeografica;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracion = duracion;
		this.duracionDimension = duracionDimension;
		this.orden = orden;
		this.treePath = treePath;
		this.nivel = nivel;
		this.productoUsuarios = productoUsuarios;
		this.subproductos = subproductos;
		this.productoPropiedadValors = productoPropiedadValors;
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
	@JoinColumn(name = "componenteid", nullable = false)
	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_tipoid", nullable = false)
	public ProductoTipo getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
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

	@Column(name = "nombre", nullable = false, length = 1000)
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

	@Column(name = "estado")
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
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

	@Column(name = "peso")
	public Integer getPeso() {
		return this.peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
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

	@Column(name = "duracion")
	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<ProductoUsuario> getProductoUsuarios() {
		return this.productoUsuarios;
	}

	public void setProductoUsuarios(Set<ProductoUsuario> productoUsuarios) {
		this.productoUsuarios = productoUsuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<Subproducto> getSubproductos() {
		return this.subproductos;
	}

	public void setSubproductos(Set<Subproducto> subproductos) {
		this.subproductos = subproductos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<ProductoPropiedadValor> getProductoPropiedadValors() {
		return this.productoPropiedadValors;
	}

	public void setProductoPropiedadValors(Set<ProductoPropiedadValor> productoPropiedadValors) {
		this.productoPropiedadValors = productoPropiedadValors;
	}

}
