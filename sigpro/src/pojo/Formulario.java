package pojo;
// Generated Dec 20, 2017 3:43:57 PM by Hibernate Tools 5.2.3.Final

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
 * Formulario generated by hbm2java
 */
@Entity
@Table(name = "formulario", catalog = "sipro")
public class Formulario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8144521478510852100L;
	private Integer id;
	private FormularioTipo formularioTipo;
	private String codigo;
	private String descripcion;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private Set<FormularioItem> formularioItems = new HashSet<FormularioItem>(0);
	private Set<ObjetoFormulario> objetoFormularios = new HashSet<ObjetoFormulario>(0);

	public Formulario() {
	}

	public Formulario(FormularioTipo formularioTipo, String codigo, String descripcion, String usuarioCreo,
			Date fechaCreacion, int estado) {
		this.formularioTipo = formularioTipo;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public Formulario(FormularioTipo formularioTipo, String codigo, String descripcion, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado,
			Set<FormularioItem> formularioItems, Set<ObjetoFormulario> objetoFormularios) {
		this.formularioTipo = formularioTipo;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.formularioItems = formularioItems;
		this.objetoFormularios = objetoFormularios;
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
	@JoinColumn(name = "formulario_tipoid", nullable = false)
	public FormularioTipo getFormularioTipo() {
		return this.formularioTipo;
	}

	public void setFormularioTipo(FormularioTipo formularioTipo) {
		this.formularioTipo = formularioTipo;
	}

	@Column(name = "codigo", nullable = false, length = 30)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "descripcion", nullable = false, length = 4000)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
	public Set<FormularioItem> getFormularioItems() {
		return this.formularioItems;
	}

	public void setFormularioItems(Set<FormularioItem> formularioItems) {
		this.formularioItems = formularioItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
	public Set<ObjetoFormulario> getObjetoFormularios() {
		return this.objetoFormularios;
	}

	public void setObjetoFormularios(Set<ObjetoFormulario> objetoFormularios) {
		this.objetoFormularios = objetoFormularios;
	}

}
