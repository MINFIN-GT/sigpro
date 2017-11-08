package pojo;
// Generated Nov 6, 2017 8:46:06 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UsuarioPermiso generated by hbm2java
 */
@Entity
@Table(name = "usuario_permiso", catalog = "sipro")
public class UsuarioPermiso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6112832732377192927L;
	private UsuarioPermisoId id;
	private Permiso permiso;
	private Usuario usuario;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;

	public UsuarioPermiso() {
	}

	public UsuarioPermiso(UsuarioPermisoId id, Permiso permiso, Usuario usuario, String usuarioCreo, Date fechaCreacion,
			int estado) {
		this.id = id;
		this.permiso = permiso;
		this.usuario = usuario;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public UsuarioPermiso(UsuarioPermisoId id, Permiso permiso, Usuario usuario, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado) {
		this.id = id;
		this.permiso = permiso;
		this.usuario = usuario;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "usuariousuario", column = @Column(name = "usuariousuario", nullable = false, length = 30)),
			@AttributeOverride(name = "permisoid", column = @Column(name = "permisoid", nullable = false)) })
	public UsuarioPermisoId getId() {
		return this.id;
	}

	public void setId(UsuarioPermisoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permisoid", nullable = false, insertable = false, updatable = false)
	public Permiso getPermiso() {
		return this.permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuariousuario", nullable = false, insertable = false, updatable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

}
