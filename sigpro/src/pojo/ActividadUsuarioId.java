package pojo;
// Generated Dec 19, 2017 2:59:18 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ActividadUsuarioId generated by hbm2java
 */
@Embeddable
public class ActividadUsuarioId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3643486269682541511L;
	private int actividadid;
	private String usuario;

	public ActividadUsuarioId() {
	}

	public ActividadUsuarioId(int actividadid, String usuario) {
		this.actividadid = actividadid;
		this.usuario = usuario;
	}

	@Column(name = "actividadid", nullable = false)
	public int getActividadid() {
		return this.actividadid;
	}

	public void setActividadid(int actividadid) {
		this.actividadid = actividadid;
	}

	@Column(name = "usuario", nullable = false, length = 30)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ActividadUsuarioId))
			return false;
		ActividadUsuarioId castOther = (ActividadUsuarioId) other;

		return (this.getActividadid() == castOther.getActividadid())
				&& ((this.getUsuario() == castOther.getUsuario()) || (this.getUsuario() != null
						&& castOther.getUsuario() != null && this.getUsuario().equals(castOther.getUsuario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getActividadid();
		result = 37 * result + (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		return result;
	}

}
