package pojo;
// Generated Oct 20, 2017 12:16:45 PM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DatoTipo generated by hbm2java
 */
@Entity
@Table(name = "dato_tipo", catalog = "sipro")
public class DatoTipo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7322308379002747180L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private Set<ComponentePropiedad> componentePropiedads = new HashSet<ComponentePropiedad>(0);
	private Set<RiesgoPropiedad> riesgoPropiedads = new HashSet<RiesgoPropiedad>(0);
	private Set<ProgramaPropiedad> programaPropiedads = new HashSet<ProgramaPropiedad>(0);
	private Set<ProyectoPropiedad> proyectoPropiedads = new HashSet<ProyectoPropiedad>(0);
	private Set<ActividadPropiedad> actividadPropiedads = new HashSet<ActividadPropiedad>(0);
	private Set<FormularioItemTipo> formularioItemTipos = new HashSet<FormularioItemTipo>(0);
	private Set<Meta> metas = new HashSet<Meta>(0);
	private Set<ProductoPropiedad> productoPropiedads = new HashSet<ProductoPropiedad>(0);
	private Set<RecursoPropiedad> recursoPropiedads = new HashSet<RecursoPropiedad>(0);
	private Set<SubcomponentePropiedad> subcomponentePropiedads = new HashSet<SubcomponentePropiedad>(0);
	private Set<HitoTipo> hitoTipos = new HashSet<HitoTipo>(0);
	private Set<SubproductoPropiedad> subproductoPropiedads = new HashSet<SubproductoPropiedad>(0);

	public DatoTipo() {
	}

	public DatoTipo(String nombre) {
		this.nombre = nombre;
	}

	public DatoTipo(String nombre, String descripcion, Set<ComponentePropiedad> componentePropiedads,
			Set<RiesgoPropiedad> riesgoPropiedads, Set<ProgramaPropiedad> programaPropiedads,
			Set<ProyectoPropiedad> proyectoPropiedads, Set<ActividadPropiedad> actividadPropiedads,
			Set<FormularioItemTipo> formularioItemTipos, Set<Meta> metas, Set<ProductoPropiedad> productoPropiedads,
			Set<RecursoPropiedad> recursoPropiedads, Set<SubcomponentePropiedad> subcomponentePropiedads,
			Set<HitoTipo> hitoTipos, Set<SubproductoPropiedad> subproductoPropiedads) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.componentePropiedads = componentePropiedads;
		this.riesgoPropiedads = riesgoPropiedads;
		this.programaPropiedads = programaPropiedads;
		this.proyectoPropiedads = proyectoPropiedads;
		this.actividadPropiedads = actividadPropiedads;
		this.formularioItemTipos = formularioItemTipos;
		this.metas = metas;
		this.productoPropiedads = productoPropiedads;
		this.recursoPropiedads = recursoPropiedads;
		this.subcomponentePropiedads = subcomponentePropiedads;
		this.hitoTipos = hitoTipos;
		this.subproductoPropiedads = subproductoPropiedads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 1000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<ComponentePropiedad> getComponentePropiedads() {
		return this.componentePropiedads;
	}

	public void setComponentePropiedads(Set<ComponentePropiedad> componentePropiedads) {
		this.componentePropiedads = componentePropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<RiesgoPropiedad> getRiesgoPropiedads() {
		return this.riesgoPropiedads;
	}

	public void setRiesgoPropiedads(Set<RiesgoPropiedad> riesgoPropiedads) {
		this.riesgoPropiedads = riesgoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<ProgramaPropiedad> getProgramaPropiedads() {
		return this.programaPropiedads;
	}

	public void setProgramaPropiedads(Set<ProgramaPropiedad> programaPropiedads) {
		this.programaPropiedads = programaPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<ProyectoPropiedad> getProyectoPropiedads() {
		return this.proyectoPropiedads;
	}

	public void setProyectoPropiedads(Set<ProyectoPropiedad> proyectoPropiedads) {
		this.proyectoPropiedads = proyectoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<ActividadPropiedad> getActividadPropiedads() {
		return this.actividadPropiedads;
	}

	public void setActividadPropiedads(Set<ActividadPropiedad> actividadPropiedads) {
		this.actividadPropiedads = actividadPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<FormularioItemTipo> getFormularioItemTipos() {
		return this.formularioItemTipos;
	}

	public void setFormularioItemTipos(Set<FormularioItemTipo> formularioItemTipos) {
		this.formularioItemTipos = formularioItemTipos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<Meta> getMetas() {
		return this.metas;
	}

	public void setMetas(Set<Meta> metas) {
		this.metas = metas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<ProductoPropiedad> getProductoPropiedads() {
		return this.productoPropiedads;
	}

	public void setProductoPropiedads(Set<ProductoPropiedad> productoPropiedads) {
		this.productoPropiedads = productoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<RecursoPropiedad> getRecursoPropiedads() {
		return this.recursoPropiedads;
	}

	public void setRecursoPropiedads(Set<RecursoPropiedad> recursoPropiedads) {
		this.recursoPropiedads = recursoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<SubcomponentePropiedad> getSubcomponentePropiedads() {
		return this.subcomponentePropiedads;
	}

	public void setSubcomponentePropiedads(Set<SubcomponentePropiedad> subcomponentePropiedads) {
		this.subcomponentePropiedads = subcomponentePropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<HitoTipo> getHitoTipos() {
		return this.hitoTipos;
	}

	public void setHitoTipos(Set<HitoTipo> hitoTipos) {
		this.hitoTipos = hitoTipos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoTipo")
	public Set<SubproductoPropiedad> getSubproductoPropiedads() {
		return this.subproductoPropiedads;
	}

	public void setSubproductoPropiedads(Set<SubproductoPropiedad> subproductoPropiedads) {
		this.subproductoPropiedads = subproductoPropiedads;
	}

}
