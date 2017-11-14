package pojo;
// Generated Nov 12, 2017 1:30:38 AM by Hibernate Tools 5.2.3.Final

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
 * PrestamoTipoPrestamo generated by hbm2java
 */
@Entity
@Table(name = "prestamo_tipo_prestamo", catalog = "sipro")
public class PrestamoTipoPrestamo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993055770816132939L;
	private PrestamoTipoPrestamoId id;
	private Prestamo prestamo;
	private PrestamoTipo prestamoTipo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;

	public PrestamoTipoPrestamo() {
	}

	public PrestamoTipoPrestamo(PrestamoTipoPrestamoId id, Prestamo prestamo, PrestamoTipo prestamoTipo,
			String usuarioCreo, Date fechaCreacion, int estado) {
		this.id = id;
		this.prestamo = prestamo;
		this.prestamoTipo = prestamoTipo;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public PrestamoTipoPrestamo(PrestamoTipoPrestamoId id, Prestamo prestamo, PrestamoTipo prestamoTipo,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado) {
		this.id = id;
		this.prestamo = prestamo;
		this.prestamoTipo = prestamoTipo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "prestamoId", column = @Column(name = "prestamoId", nullable = false)),
			@AttributeOverride(name = "tipoPrestamoId", column = @Column(name = "tipoPrestamoId", nullable = false)) })
	public PrestamoTipoPrestamoId getId() {
		return this.id;
	}

	public void setId(PrestamoTipoPrestamoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestamoId", nullable = false, insertable = false, updatable = false)
	public Prestamo getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoPrestamoId", nullable = false, insertable = false, updatable = false)
	public PrestamoTipo getPrestamoTipo() {
		return this.prestamoTipo;
	}

	public void setPrestamoTipo(PrestamoTipo prestamoTipo) {
		this.prestamoTipo = prestamoTipo;
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
