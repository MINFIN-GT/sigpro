package pojo;
// Generated Nov 9, 2017 9:46:17 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrestamoUsuarioId generated by hbm2java
 */
@Embeddable
public class PrestamoUsuarioId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5978509842922595344L;
	private int prestamoid;
	private String usuario;

	public PrestamoUsuarioId() {
	}

	public PrestamoUsuarioId(int prestamoid, String usuario) {
		this.prestamoid = prestamoid;
		this.usuario = usuario;
	}

	@Column(name = "prestamoid", nullable = false)
	public int getPrestamoid() {
		return this.prestamoid;
	}

	public void setPrestamoid(int prestamoid) {
		this.prestamoid = prestamoid;
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
		if (!(other instanceof PrestamoUsuarioId))
			return false;
		PrestamoUsuarioId castOther = (PrestamoUsuarioId) other;

		return (this.getPrestamoid() == castOther.getPrestamoid())
				&& ((this.getUsuario() == castOther.getUsuario()) || (this.getUsuario() != null
						&& castOther.getUsuario() != null && this.getUsuario().equals(castOther.getUsuario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPrestamoid();
		result = 37 * result + (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		return result;
	}

}
