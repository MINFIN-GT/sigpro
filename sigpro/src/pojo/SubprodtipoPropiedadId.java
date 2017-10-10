package pojo;
// Generated Oct 9, 2017 6:11:54 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SubprodtipoPropiedadId generated by hbm2java
 */
@Embeddable
public class SubprodtipoPropiedadId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5874448577393979832L;
	private int subproductoTipoid;
	private int subproductoPropiedadid;

	public SubprodtipoPropiedadId() {
	}

	public SubprodtipoPropiedadId(int subproductoTipoid, int subproductoPropiedadid) {
		this.subproductoTipoid = subproductoTipoid;
		this.subproductoPropiedadid = subproductoPropiedadid;
	}

	@Column(name = "subproducto_tipoid", nullable = false)
	public int getSubproductoTipoid() {
		return this.subproductoTipoid;
	}

	public void setSubproductoTipoid(int subproductoTipoid) {
		this.subproductoTipoid = subproductoTipoid;
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
		if (!(other instanceof SubprodtipoPropiedadId))
			return false;
		SubprodtipoPropiedadId castOther = (SubprodtipoPropiedadId) other;

		return (this.getSubproductoTipoid() == castOther.getSubproductoTipoid())
				&& (this.getSubproductoPropiedadid() == castOther.getSubproductoPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSubproductoTipoid();
		result = 37 * result + this.getSubproductoPropiedadid();
		return result;
	}

}
