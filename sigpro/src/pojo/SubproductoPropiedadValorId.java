package pojo;
// Generated Dec 20, 2017 3:43:57 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SubproductoPropiedadValorId generated by hbm2java
 */
@Embeddable
public class SubproductoPropiedadValorId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3879657242370279903L;
	private int subproductoid;
	private int subproductoPropiedadid;

	public SubproductoPropiedadValorId() {
	}

	public SubproductoPropiedadValorId(int subproductoid, int subproductoPropiedadid) {
		this.subproductoid = subproductoid;
		this.subproductoPropiedadid = subproductoPropiedadid;
	}

	@Column(name = "subproductoid", nullable = false)
	public int getSubproductoid() {
		return this.subproductoid;
	}

	public void setSubproductoid(int subproductoid) {
		this.subproductoid = subproductoid;
	}

	@Column(name = "subproducto_propiedadid", nullable = false)
	public int getSubproductoPropiedadid() {
		return this.subproductoPropiedadid;
	}

	public void setSubproductoPropiedadid(int subproductoPropiedadid) {
		this.subproductoPropiedadid = subproductoPropiedadid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SubproductoPropiedadValorId))
			return false;
		SubproductoPropiedadValorId castOther = (SubproductoPropiedadValorId) other;

		return (this.getSubproductoid() == castOther.getSubproductoid())
				&& (this.getSubproductoPropiedadid() == castOther.getSubproductoPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSubproductoid();
		result = 37 * result + this.getSubproductoPropiedadid();
		return result;
	}

}
