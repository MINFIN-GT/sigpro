package pojo;
// Generated Jun 2, 2017 12:28:44 PM by Hibernate Tools 5.2.1.Final

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
 * ProgramaTipo generated by hbm2java
 */
@Entity
@Table(name = "programa_tipo", catalog = "sipro")
public class ProgramaTipo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6112759131314697922L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Integer estado;
	private Set<ProgtipoPropiedad> progtipoPropiedads = new HashSet<ProgtipoPropiedad>(0);
	private Set<Programa> programas = new HashSet<Programa>(0);

	public ProgramaTipo() {
	}

	public ProgramaTipo(String nombre, String descripcion, String usuarioCreo, String usuarioActualizo,
			Date fechaCreacion, Date fechaActualizacion, Integer estado, Set<ProgtipoPropiedad> progtipoPropiedads,
			Set<Programa> programas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.progtipoPropiedads = progtipoPropiedads;
		this.programas = programas;
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

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 2000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programaTipo")
	public Set<ProgtipoPropiedad> getProgtipoPropiedads() {
		return this.progtipoPropiedads;
	}

	public void setProgtipoPropiedads(Set<ProgtipoPropiedad> progtipoPropiedads) {
		this.progtipoPropiedads = progtipoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programaTipo")
	public Set<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(Set<Programa> programas) {
		this.programas = programas;
	}

}
