package pojoSigade;
// Generated Nov 2, 2017 2:45:37 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DtmAvanceFisfinanDti generated by hbm2java
 */
@Entity
@Table(name = "dtm_avance_fisfinan_dti", catalog = "sipro_analytic")
public class DtmAvanceFisfinanDti implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6382928333615553394L;
	private DtmAvanceFisfinanDtiId id;

	public DtmAvanceFisfinanDti() {
	}

	public DtmAvanceFisfinanDti(DtmAvanceFisfinanDtiId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "fechaCorte", column = @Column(name = "fecha_corte", length = 19)),
			@AttributeOverride(name = "noPrestamo", column = @Column(name = "no_prestamo", length = 45)),
			@AttributeOverride(name = "codigoPresupuestario", column = @Column(name = "codigo_presupuestario", length = 45)),
			@AttributeOverride(name = "nombrePrograma", column = @Column(name = "nombre_programa", length = 500)),
			@AttributeOverride(name = "codigoOrganismoFinan", column = @Column(name = "codigo_organismo_finan")),
			@AttributeOverride(name = "siglasOrganismoFinan", column = @Column(name = "siglas_organismo_finan", length = 45)),
			@AttributeOverride(name = "nombreOrganismoFinan", column = @Column(name = "nombre_organismo_finan", length = 200)),
			@AttributeOverride(name = "monedaPrestamo", column = @Column(name = "moneda_prestamo", length = 100)),
			@AttributeOverride(name = "montoContratado", column = @Column(name = "monto_contratado", precision = 15)),
			@AttributeOverride(name = "montoContratadoUsd", column = @Column(name = "monto_contratado_usd", precision = 15)),
			@AttributeOverride(name = "montoContratadoGtq", column = @Column(name = "monto_contratado_gtq", precision = 15)),
			@AttributeOverride(name = "desembolsos", column = @Column(name = "desembolsos", precision = 15)),
			@AttributeOverride(name = "desembolsosUsd", column = @Column(name = "desembolsos_usd", precision = 15)),
			@AttributeOverride(name = "desembolsosGtq", column = @Column(name = "desembolsos_gtq", precision = 15)),
			@AttributeOverride(name = "fechaDecreto", column = @Column(name = "fecha_decreto", length = 19)),
			@AttributeOverride(name = "fechaSuscripcion", column = @Column(name = "fecha_suscripcion", length = 19)),
			@AttributeOverride(name = "fechaVigencia", column = @Column(name = "fecha_vigencia", length = 19)),
			@AttributeOverride(name = "porDesembolsar", column = @Column(name = "por_desembolsar", precision = 15)),
			@AttributeOverride(name = "porDesembolsarUsd", column = @Column(name = "por_desembolsar_usd", precision = 15)),
			@AttributeOverride(name = "porDesembolsarGtq", column = @Column(name = "por_desembolsar_gtq", precision = 15)),
			@AttributeOverride(name = "estadoPrestamo", column = @Column(name = "estado_prestamo", length = 45)),
			@AttributeOverride(name = "objetivo", column = @Column(name = "objetivo", length = 4000)) })
	public DtmAvanceFisfinanDtiId getId() {
		return this.id;
	}

	public void setId(DtmAvanceFisfinanDtiId id) {
		this.id = id;
	}

}
