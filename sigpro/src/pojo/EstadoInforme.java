package pojo;
// Generated Nov 21, 2017 10:47:44 AM by Hibernate Tools 5.2.3.Final

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
 * EstadoInforme generated by hbm2java
 */
@Entity
@Table(name = "estado_informe", catalog = "sipro")
public class EstadoInforme implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3793415193736957743L;
	private Integer id;
	private String nombre;
	private int tipoInforme;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Integer estado;
	private Set<InformePresupuesto> informePresupuestos = new HashSet<InformePresupuesto>(0);

	public EstadoInforme() {
	}

	public EstadoInforme(String nombre, int tipoInforme) {
		this.nombre = nombre;
		this.tipoInforme = tipoInforme;
	}

	public EstadoInforme(String nombre, int tipoInforme, String usuarioCreo, String usuarioActualizo,
			Date fechaCreacion, Date fechaActualizacion, Integer estado, Set<InformePresupuesto> informePresupuestos) {
		this.nombre = nombre;
		this.tipoInforme = tipoInforme;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.informePresupuestos = informePresupuestos;
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

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "tipo_informe", nullable = false)
	public int getTipoInforme() {
		return this.tipoInforme;
	}

	public void setTipoInforme(int tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	@Column(name = "usuario_creo", length = 30)
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
	@Column(name = "fecha_creacion", length = 19)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoInforme")
	public Set<InformePresupuesto> getInformePresupuestos() {
		return this.informePresupuestos;
	}

	public void setInformePresupuestos(Set<InformePresupuesto> informePresupuestos) {
		this.informePresupuestos = informePresupuestos;
	}

}
