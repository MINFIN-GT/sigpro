package pojo;
// Generated Oct 20, 2017 4:18:11 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsuarioPermisoId generated by hbm2java
 */
@Embeddable
public class UsuarioPermisoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4240961492492996626L;
	private String usuariousuario;
	private int permisoid;

	public UsuarioPermisoId() {
	}

	public UsuarioPermisoId(String usuariousuario, int permisoid) {
		this.usuariousuario = usuariousuario;
		this.permisoid = permisoid;
	}

	@Column(name = "usuariousuario", nullable = false, length = 30)
	public String getUsuariousuario() {
		return this.usuariousuario;
	}

	public void setUsuariousuario(String usuariousuario) {
		this.usuariousuario = usuariousuario;
	}

	@Column(name = "permisoid", nullable = false)
	public int getPermisoid() {
		return this.permisoid;
	}

	public void setPermisoid(int permisoid) {
		this.permisoid = permisoid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsuarioPermisoId))
			return false;
		UsuarioPermisoId castOther = (UsuarioPermisoId) other;

		return ((this.getUsuariousuario() == castOther.getUsuariousuario())
				|| (this.getUsuariousuario() != null && castOther.getUsuariousuario() != null
						&& this.getUsuariousuario().equals(castOther.getUsuariousuario())))
				&& (this.getPermisoid() == castOther.getPermisoid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsuariousuario() == null ? 0 : this.getUsuariousuario().hashCode());
		result = 37 * result + this.getPermisoid();
		return result;
	}

}
