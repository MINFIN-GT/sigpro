package pojo;
// Generated Nov 9, 2017 9:46:17 AM by Hibernate Tools 5.2.3.Final

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
 * SubcomponenteUsuario generated by hbm2java
 */
@Entity
@Table(name = "subcomponente_usuario", catalog = "sipro")
public class SubcomponenteUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5900012656353307484L;
	private SubcomponenteUsuarioId id;
	private Subcomponente subcomponente;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public SubcomponenteUsuario() {
	}

	public SubcomponenteUsuario(SubcomponenteUsuarioId id, Subcomponente subcomponente) {
		this.id = id;
		this.subcomponente = subcomponente;
	}

	public SubcomponenteUsuario(SubcomponenteUsuarioId id, Subcomponente subcomponente, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion) {
		this.id = id;
		this.subcomponente = subcomponente;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "subcomponenteid", column = @Column(name = "subcomponenteid", nullable = false)),
			@AttributeOverride(name = "usuario", column = @Column(name = "usuario", nullable = false, length = 30)) })
	public SubcomponenteUsuarioId getId() {
		return this.id;
	}

	public void setId(SubcomponenteUsuarioId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcomponenteid", nullable = false, insertable = false, updatable = false)
	public Subcomponente getSubcomponente() {
		return this.subcomponente;
	}

	public void setSubcomponente(Subcomponente subcomponente) {
		this.subcomponente = subcomponente;
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

}
