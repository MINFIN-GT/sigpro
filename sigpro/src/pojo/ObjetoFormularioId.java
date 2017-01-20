package pojo;
// Generated Jan 19, 2017 7:44:41 PM by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ObjetoFormularioId generated by hbm2java
 */
@Embeddable
public class ObjetoFormularioId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5257387370823729339L;
	private int formularioid;
	private int objetoTipoid;

	public ObjetoFormularioId() {
	}

	public ObjetoFormularioId(int formularioid, int objetoTipoid) {
		this.formularioid = formularioid;
		this.objetoTipoid = objetoTipoid;
	}

	@Column(name = "formularioid", nullable = false)
	public int getFormularioid() {
		return this.formularioid;
	}

	public void setFormularioid(int formularioid) {
		this.formularioid = formularioid;
	}

	@Column(name = "objeto_tipoid", nullable = false)
	public int getObjetoTipoid() {
		return this.objetoTipoid;
	}

	public void setObjetoTipoid(int objetoTipoid) {
		this.objetoTipoid = objetoTipoid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ObjetoFormularioId))
			return false;
		ObjetoFormularioId castOther = (ObjetoFormularioId) other;

		return (this.getFormularioid() == castOther.getFormularioid())
				&& (this.getObjetoTipoid() == castOther.getObjetoTipoid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getFormularioid();
		result = 37 * result + this.getObjetoTipoid();
		return result;
	}

}
