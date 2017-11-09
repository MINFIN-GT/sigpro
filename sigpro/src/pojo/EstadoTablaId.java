package pojo;
// Generated Nov 9, 2017 9:46:17 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EstadoTablaId generated by hbm2java
 */
@Embeddable
public class EstadoTablaId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8686808495004515024L;
	private String usuario;
	private String tabla;

	public EstadoTablaId() {
	}

	public EstadoTablaId(String usuario, String tabla) {
		this.usuario = usuario;
		this.tabla = tabla;
	}

	@Column(name = "usuario", nullable = false, length = 30)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "tabla", nullable = false, length = 50)
	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EstadoTablaId))
			return false;
		EstadoTablaId castOther = (EstadoTablaId) other;

		return ((this.getUsuario() == castOther.getUsuario()) || (this.getUsuario() != null
				&& castOther.getUsuario() != null && this.getUsuario().equals(castOther.getUsuario())))
				&& ((this.getTabla() == castOther.getTabla()) || (this.getTabla() != null
						&& castOther.getTabla() != null && this.getTabla().equals(castOther.getTabla())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		result = 37 * result + (getTabla() == null ? 0 : this.getTabla().hashCode());
		return result;
	}

}
