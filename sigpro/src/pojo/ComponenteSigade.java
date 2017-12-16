package pojo;
// Generated Dec 13, 2017 9:28:15 AM by Hibernate Tools 5.2.3.Final

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ComponenteSigade generated by hbm2java
 */
@Entity
@Table(name = "componente_sigade", catalog = "sipro")
public class ComponenteSigade implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6854220732232603190L;
	private Integer id;
	private String nombre;
	private String codigoPresupuestario;
	private int numeroComponente;
	private BigDecimal montoComponente;
	private int estado;
	private String usuaraioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Set<Componente> componentes = new HashSet<Componente>(0);

	public ComponenteSigade() {
	}

	public ComponenteSigade(String nombre, String codigoPresupuestario, int numeroComponente,
			BigDecimal montoComponente, int estado, String usuaraioCreo, Date fechaCreacion) {
		this.nombre = nombre;
		this.codigoPresupuestario = codigoPresupuestario;
		this.numeroComponente = numeroComponente;
		this.montoComponente = montoComponente;
		this.estado = estado;
		this.usuaraioCreo = usuaraioCreo;
		this.fechaCreacion = fechaCreacion;
	}

	public ComponenteSigade(String nombre, String codigoPresupuestario, int numeroComponente,
			BigDecimal montoComponente, int estado, String usuaraioCreo, String usuarioActualizo, Date fechaCreacion,
			Date fechaActualizacion, Set<Componente> componentes) {
		this.nombre = nombre;
		this.codigoPresupuestario = codigoPresupuestario;
		this.numeroComponente = numeroComponente;
		this.montoComponente = montoComponente;
		this.estado = estado;
		this.usuaraioCreo = usuaraioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
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

	@Column(name = "nombre", nullable = false, length = 2000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "codigo_presupuestario", nullable = false, length = 45)
	public String getCodigoPresupuestario() {
		return this.codigoPresupuestario;
	}

	public void setCodigoPresupuestario(String codigoPresupuestario) {
		this.codigoPresupuestario = codigoPresupuestario;
	}

	@Column(name = "numero_componente", nullable = false)
	public int getNumeroComponente() {
		return this.numeroComponente;
	}

	public void setNumeroComponente(int numeroComponente) {
		this.numeroComponente = numeroComponente;
	}

	@Column(name = "monto_componente", nullable = false, precision = 15)
	public BigDecimal getMontoComponente() {
		return this.montoComponente;
	}

	public void setMontoComponente(BigDecimal montoComponente) {
		this.montoComponente = montoComponente;
	}

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Column(name = "usuaraio_creo", nullable = false, length = 30)
	public String getUsuaraioCreo() {
		return this.usuaraioCreo;
	}

	public void setUsuaraioCreo(String usuaraioCreo) {
		this.usuaraioCreo = usuaraioCreo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "componenteSigade")
	public Set<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

}
