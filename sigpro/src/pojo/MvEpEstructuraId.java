package pojo;
// Generated Aug 10, 2017 5:40:56 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MvEpEstructuraId generated by hbm2java
 */
@Embeddable
public class MvEpEstructuraId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8803975496713000142L;
	private Integer ejercicio;
	private Integer fuente;
	private Integer organismo;
	private Integer correlativo;
	private Integer programa;
	private Integer subprograma;
	private Integer proyecto;
	private Integer actividad;
	private Integer obra;
	private BigDecimal enero;
	private BigDecimal febrero;
	private BigDecimal marzo;
	private BigDecimal abril;
	private BigDecimal mayo;
	private BigDecimal junio;
	private BigDecimal julio;
	private BigDecimal agosto;
	private BigDecimal septiembre;
	private BigDecimal octubre;
	private BigDecimal noviembre;
	private BigDecimal diciembre;

	public MvEpEstructuraId() {
	}

	public MvEpEstructuraId(Integer ejercicio, Integer fuente, Integer organismo, Integer correlativo, Integer programa,
			Integer subprograma, Integer proyecto, Integer actividad, Integer obra, BigDecimal enero,
			BigDecimal febrero, BigDecimal marzo, BigDecimal abril, BigDecimal mayo, BigDecimal junio, BigDecimal julio,
			BigDecimal agosto, BigDecimal septiembre, BigDecimal octubre, BigDecimal noviembre, BigDecimal diciembre) {
		this.ejercicio = ejercicio;
		this.fuente = fuente;
		this.organismo = organismo;
		this.correlativo = correlativo;
		this.programa = programa;
		this.subprograma = subprograma;
		this.proyecto = proyecto;
		this.actividad = actividad;
		this.obra = obra;
		this.enero = enero;
		this.febrero = febrero;
		this.marzo = marzo;
		this.abril = abril;
		this.mayo = mayo;
		this.junio = junio;
		this.julio = julio;
		this.agosto = agosto;
		this.septiembre = septiembre;
		this.octubre = octubre;
		this.noviembre = noviembre;
		this.diciembre = diciembre;
	}

	@Column(name = "ejercicio")
	public Integer getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}

	@Column(name = "fuente")
	public Integer getFuente() {
		return this.fuente;
	}

	public void setFuente(Integer fuente) {
		this.fuente = fuente;
	}

	@Column(name = "organismo")
	public Integer getOrganismo() {
		return this.organismo;
	}

	public void setOrganismo(Integer organismo) {
		this.organismo = organismo;
	}

	@Column(name = "correlativo")
	public Integer getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	@Column(name = "programa")
	public Integer getPrograma() {
		return this.programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	@Column(name = "subprograma")
	public Integer getSubprograma() {
		return this.subprograma;
	}

	public void setSubprograma(Integer subprograma) {
		this.subprograma = subprograma;
	}

	@Column(name = "proyecto")
	public Integer getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "actividad")
	public Integer getActividad() {
		return this.actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	@Column(name = "obra")
	public Integer getObra() {
		return this.obra;
	}

	public void setObra(Integer obra) {
		this.obra = obra;
	}

	@Column(name = "enero", precision = 59)
	public BigDecimal getEnero() {
		return this.enero;
	}

	public void setEnero(BigDecimal enero) {
		this.enero = enero;
	}

	@Column(name = "febrero", precision = 59)
	public BigDecimal getFebrero() {
		return this.febrero;
	}

	public void setFebrero(BigDecimal febrero) {
		this.febrero = febrero;
	}

	@Column(name = "marzo", precision = 59)
	public BigDecimal getMarzo() {
		return this.marzo;
	}

	public void setMarzo(BigDecimal marzo) {
		this.marzo = marzo;
	}

	@Column(name = "abril", precision = 59)
	public BigDecimal getAbril() {
		return this.abril;
	}

	public void setAbril(BigDecimal abril) {
		this.abril = abril;
	}

	@Column(name = "mayo", precision = 59)
	public BigDecimal getMayo() {
		return this.mayo;
	}

	public void setMayo(BigDecimal mayo) {
		this.mayo = mayo;
	}

	@Column(name = "junio", precision = 59)
	public BigDecimal getJunio() {
		return this.junio;
	}

	public void setJunio(BigDecimal junio) {
		this.junio = junio;
	}

	@Column(name = "julio", precision = 59)
	public BigDecimal getJulio() {
		return this.julio;
	}

	public void setJulio(BigDecimal julio) {
		this.julio = julio;
	}

	@Column(name = "agosto", precision = 59)
	public BigDecimal getAgosto() {
		return this.agosto;
	}

	public void setAgosto(BigDecimal agosto) {
		this.agosto = agosto;
	}

	@Column(name = "septiembre", precision = 59)
	public BigDecimal getSeptiembre() {
		return this.septiembre;
	}

	public void setSeptiembre(BigDecimal septiembre) {
		this.septiembre = septiembre;
	}

	@Column(name = "octubre", precision = 59)
	public BigDecimal getOctubre() {
		return this.octubre;
	}

	public void setOctubre(BigDecimal octubre) {
		this.octubre = octubre;
	}

	@Column(name = "noviembre", precision = 59)
	public BigDecimal getNoviembre() {
		return this.noviembre;
	}

	public void setNoviembre(BigDecimal noviembre) {
		this.noviembre = noviembre;
	}

	@Column(name = "diciembre", precision = 59)
	public BigDecimal getDiciembre() {
		return this.diciembre;
	}

	public void setDiciembre(BigDecimal diciembre) {
		this.diciembre = diciembre;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MvEpEstructuraId))
			return false;
		MvEpEstructuraId castOther = (MvEpEstructuraId) other;

		return ((this.getEjercicio() == castOther.getEjercicio()) || (this.getEjercicio() != null
				&& castOther.getEjercicio() != null && this.getEjercicio().equals(castOther.getEjercicio())))
				&& ((this.getFuente() == castOther.getFuente()) || (this.getFuente() != null
						&& castOther.getFuente() != null && this.getFuente().equals(castOther.getFuente())))
				&& ((this.getOrganismo() == castOther.getOrganismo()) || (this.getOrganismo() != null
						&& castOther.getOrganismo() != null && this.getOrganismo().equals(castOther.getOrganismo())))
				&& ((this.getCorrelativo() == castOther.getCorrelativo())
						|| (this.getCorrelativo() != null && castOther.getCorrelativo() != null
								&& this.getCorrelativo().equals(castOther.getCorrelativo())))
				&& ((this.getPrograma() == castOther.getPrograma()) || (this.getPrograma() != null
						&& castOther.getPrograma() != null && this.getPrograma().equals(castOther.getPrograma())))
				&& ((this.getSubprograma() == castOther.getSubprograma())
						|| (this.getSubprograma() != null && castOther.getSubprograma() != null
								&& this.getSubprograma().equals(castOther.getSubprograma())))
				&& ((this.getProyecto() == castOther.getProyecto()) || (this.getProyecto() != null
						&& castOther.getProyecto() != null && this.getProyecto().equals(castOther.getProyecto())))
				&& ((this.getActividad() == castOther.getActividad()) || (this.getActividad() != null
						&& castOther.getActividad() != null && this.getActividad().equals(castOther.getActividad())))
				&& ((this.getObra() == castOther.getObra()) || (this.getObra() != null && castOther.getObra() != null
						&& this.getObra().equals(castOther.getObra())))
				&& ((this.getEnero() == castOther.getEnero()) || (this.getEnero() != null
						&& castOther.getEnero() != null && this.getEnero().equals(castOther.getEnero())))
				&& ((this.getFebrero() == castOther.getFebrero()) || (this.getFebrero() != null
						&& castOther.getFebrero() != null && this.getFebrero().equals(castOther.getFebrero())))
				&& ((this.getMarzo() == castOther.getMarzo()) || (this.getMarzo() != null
						&& castOther.getMarzo() != null && this.getMarzo().equals(castOther.getMarzo())))
				&& ((this.getAbril() == castOther.getAbril()) || (this.getAbril() != null
						&& castOther.getAbril() != null && this.getAbril().equals(castOther.getAbril())))
				&& ((this.getMayo() == castOther.getMayo()) || (this.getMayo() != null && castOther.getMayo() != null
						&& this.getMayo().equals(castOther.getMayo())))
				&& ((this.getJunio() == castOther.getJunio()) || (this.getJunio() != null
						&& castOther.getJunio() != null && this.getJunio().equals(castOther.getJunio())))
				&& ((this.getJulio() == castOther.getJulio()) || (this.getJulio() != null
						&& castOther.getJulio() != null && this.getJulio().equals(castOther.getJulio())))
				&& ((this.getAgosto() == castOther.getAgosto()) || (this.getAgosto() != null
						&& castOther.getAgosto() != null && this.getAgosto().equals(castOther.getAgosto())))
				&& ((this.getSeptiembre() == castOther.getSeptiembre()) || (this.getSeptiembre() != null
						&& castOther.getSeptiembre() != null && this.getSeptiembre().equals(castOther.getSeptiembre())))
				&& ((this.getOctubre() == castOther.getOctubre()) || (this.getOctubre() != null
						&& castOther.getOctubre() != null && this.getOctubre().equals(castOther.getOctubre())))
				&& ((this.getNoviembre() == castOther.getNoviembre()) || (this.getNoviembre() != null
						&& castOther.getNoviembre() != null && this.getNoviembre().equals(castOther.getNoviembre())))
				&& ((this.getDiciembre() == castOther.getDiciembre()) || (this.getDiciembre() != null
						&& castOther.getDiciembre() != null && this.getDiciembre().equals(castOther.getDiciembre())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEjercicio() == null ? 0 : this.getEjercicio().hashCode());
		result = 37 * result + (getFuente() == null ? 0 : this.getFuente().hashCode());
		result = 37 * result + (getOrganismo() == null ? 0 : this.getOrganismo().hashCode());
		result = 37 * result + (getCorrelativo() == null ? 0 : this.getCorrelativo().hashCode());
		result = 37 * result + (getPrograma() == null ? 0 : this.getPrograma().hashCode());
		result = 37 * result + (getSubprograma() == null ? 0 : this.getSubprograma().hashCode());
		result = 37 * result + (getProyecto() == null ? 0 : this.getProyecto().hashCode());
		result = 37 * result + (getActividad() == null ? 0 : this.getActividad().hashCode());
		result = 37 * result + (getObra() == null ? 0 : this.getObra().hashCode());
		result = 37 * result + (getEnero() == null ? 0 : this.getEnero().hashCode());
		result = 37 * result + (getFebrero() == null ? 0 : this.getFebrero().hashCode());
		result = 37 * result + (getMarzo() == null ? 0 : this.getMarzo().hashCode());
		result = 37 * result + (getAbril() == null ? 0 : this.getAbril().hashCode());
		result = 37 * result + (getMayo() == null ? 0 : this.getMayo().hashCode());
		result = 37 * result + (getJunio() == null ? 0 : this.getJunio().hashCode());
		result = 37 * result + (getJulio() == null ? 0 : this.getJulio().hashCode());
		result = 37 * result + (getAgosto() == null ? 0 : this.getAgosto().hashCode());
		result = 37 * result + (getSeptiembre() == null ? 0 : this.getSeptiembre().hashCode());
		result = 37 * result + (getOctubre() == null ? 0 : this.getOctubre().hashCode());
		result = 37 * result + (getNoviembre() == null ? 0 : this.getNoviembre().hashCode());
		result = 37 * result + (getDiciembre() == null ? 0 : this.getDiciembre().hashCode());
		return result;
	}

}
