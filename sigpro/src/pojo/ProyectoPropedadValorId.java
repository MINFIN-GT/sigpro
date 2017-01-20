package pojo;
// Generated Jan 19, 2017 7:44:41 PM by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProyectoPropedadValorId generated by hbm2java
 */
@Embeddable
public class ProyectoPropedadValorId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3521681953748587535L;
	private int proyectoid;
	private int proyectoPropiedadid;

	public ProyectoPropedadValorId() {
	}

	public ProyectoPropedadValorId(int proyectoid, int proyectoPropiedadid) {
		this.proyectoid = proyectoid;
		this.proyectoPropiedadid = proyectoPropiedadid;
	}

	@Column(name = "proyectoid", nullable = false)
	public int getProyectoid() {
		return this.proyectoid;
	}

	public void setProyectoid(int proyectoid) {
		this.proyectoid = proyectoid;
	}

	@Column(name = "proyecto_propiedadid", nullable = false)
	public int getProyectoPropiedadid() {
		return this.proyectoPropiedadid;
	}

	public void setProyectoPropiedadid(int proyectoPropiedadid) {
		this.proyectoPropiedadid = proyectoPropiedadid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProyectoPropedadValorId))
			return false;
		ProyectoPropedadValorId castOther = (ProyectoPropedadValorId) other;

		return (this.getProyectoid() == castOther.getProyectoid())
				&& (this.getProyectoPropiedadid() == castOther.getProyectoPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProyectoid();
		result = 37 * result + this.getProyectoPropiedadid();
		return result;
	}

}
