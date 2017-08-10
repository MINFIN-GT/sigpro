package pojo;
// Generated Aug 10, 2017 5:40:56 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MvArbolId generated by hbm2java
 */
@Embeddable
public class MvArbolId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5618148617169726562L;
	private long prestamo;
	private Integer componente;
	private Integer producto;
	private Integer subproducto;
	private long level;
	private Integer actividad;
	private Long treelevel;
	private String treepath;
	private Date fechaInicio;

	public MvArbolId() {
	}

	public MvArbolId(long prestamo, long level) {
		this.prestamo = prestamo;
		this.level = level;
	}

	public MvArbolId(long prestamo, Integer componente, Integer producto, Integer subproducto, long level,
			Integer actividad, Long treelevel, String treepath, Date fechaInicio) {
		this.prestamo = prestamo;
		this.componente = componente;
		this.producto = producto;
		this.subproducto = subproducto;
		this.level = level;
		this.actividad = actividad;
		this.treelevel = treelevel;
		this.treepath = treepath;
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "prestamo", nullable = false)
	public long getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(long prestamo) {
		this.prestamo = prestamo;
	}

	@Column(name = "componente")
	public Integer getComponente() {
		return this.componente;
	}

	public void setComponente(Integer componente) {
		this.componente = componente;
	}

	@Column(name = "producto")
	public Integer getProducto() {
		return this.producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	@Column(name = "subproducto")
	public Integer getSubproducto() {
		return this.subproducto;
	}

	public void setSubproducto(Integer subproducto) {
		this.subproducto = subproducto;
	}

	@Column(name = "level", nullable = false)
	public long getLevel() {
		return this.level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	@Column(name = "actividad")
	public Integer getActividad() {
		return this.actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	@Column(name = "treelevel")
	public Long getTreelevel() {
		return this.treelevel;
	}

	public void setTreelevel(Long treelevel) {
		this.treelevel = treelevel;
	}

	@Column(name = "treepath", length = 511)
	public String getTreepath() {
		return this.treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	@Column(name = "fecha_inicio", length = 19)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MvArbolId))
			return false;
		MvArbolId castOther = (MvArbolId) other;

		return (this.getPrestamo() == castOther.getPrestamo())
				&& ((this.getComponente() == castOther.getComponente()) || (this.getComponente() != null
						&& castOther.getComponente() != null && this.getComponente().equals(castOther.getComponente())))
				&& ((this.getProducto() == castOther.getProducto()) || (this.getProducto() != null
						&& castOther.getProducto() != null && this.getProducto().equals(castOther.getProducto())))
				&& ((this.getSubproducto() == castOther.getSubproducto())
						|| (this.getSubproducto() != null && castOther.getSubproducto() != null
								&& this.getSubproducto().equals(castOther.getSubproducto())))
				&& (this.getLevel() == castOther.getLevel())
				&& ((this.getActividad() == castOther.getActividad()) || (this.getActividad() != null
						&& castOther.getActividad() != null && this.getActividad().equals(castOther.getActividad())))
				&& ((this.getTreelevel() == castOther.getTreelevel()) || (this.getTreelevel() != null
						&& castOther.getTreelevel() != null && this.getTreelevel().equals(castOther.getTreelevel())))
				&& ((this.getTreepath() == castOther.getTreepath()) || (this.getTreepath() != null
						&& castOther.getTreepath() != null && this.getTreepath().equals(castOther.getTreepath())))
				&& ((this.getFechaInicio() == castOther.getFechaInicio())
						|| (this.getFechaInicio() != null && castOther.getFechaInicio() != null
								&& this.getFechaInicio().equals(castOther.getFechaInicio())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPrestamo();
		result = 37 * result + (getComponente() == null ? 0 : this.getComponente().hashCode());
		result = 37 * result + (getProducto() == null ? 0 : this.getProducto().hashCode());
		result = 37 * result + (getSubproducto() == null ? 0 : this.getSubproducto().hashCode());
		result = 37 * result + (int) this.getLevel();
		result = 37 * result + (getActividad() == null ? 0 : this.getActividad().hashCode());
		result = 37 * result + (getTreelevel() == null ? 0 : this.getTreelevel().hashCode());
		result = 37 * result + (getTreepath() == null ? 0 : this.getTreepath().hashCode());
		result = 37 * result + (getFechaInicio() == null ? 0 : this.getFechaInicio().hashCode());
		return result;
	}

}
