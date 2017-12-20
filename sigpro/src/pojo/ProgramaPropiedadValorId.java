package pojo;
// Generated Dec 19, 2017 2:59:18 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProgramaPropiedadValorId generated by hbm2java
 */
@Embeddable
public class ProgramaPropiedadValorId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4182214498126800430L;
	private int programaPropiedadid;
	private int programaid;

	public ProgramaPropiedadValorId() {
	}

	public ProgramaPropiedadValorId(int programaPropiedadid, int programaid) {
		this.programaPropiedadid = programaPropiedadid;
		this.programaid = programaid;
	}

	@Column(name = "programa_propiedadid", nullable = false)
	public int getProgramaPropiedadid() {
		return this.programaPropiedadid;
	}

	public void setProgramaPropiedadid(int programaPropiedadid) {
		this.programaPropiedadid = programaPropiedadid;
	}

	@Column(name = "programaid", nullable = false)
	public int getProgramaid() {
		return this.programaid;
	}

	public void setProgramaid(int programaid) {
		this.programaid = programaid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProgramaPropiedadValorId))
			return false;
		ProgramaPropiedadValorId castOther = (ProgramaPropiedadValorId) other;

		return (this.getProgramaPropiedadid() == castOther.getProgramaPropiedadid())
				&& (this.getProgramaid() == castOther.getProgramaid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProgramaPropiedadid();
		result = 37 * result + this.getProgramaid();
		return result;
	}

}
