package no.hvl.dat104.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * Modellrepresentasjon av Tilbakemelding
 * 
 * @author BMO 2.0
 *
 */
@Entity(name = "Tilbakemelding")
@Table(name = "tilbakemelding", schema = "db")
public class Tilbakemelding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String stemme;
	private Timestamp tid;
	
	@ManyToOne
	@JoinColumn(name = "id_event", referencedColumnName = "id")
	private Event idEvent;

	/**
	 * Tom konstruktor for Tilbakemelding
	 */
	public Tilbakemelding() {
		this("", null, null);
	}

	/**
	 * Hovedkonstruktor for Tilbakemelding
	 * 
	 * @param id
	 *            Id for tilbakemelding
	 * @param stemme
	 *            Stemme for tilbakemelding
	 * @param idEvent
	 *            Id for event paa tilbakemelding
	 * @param tid
	 *            Tiden
	 */
	public Tilbakemelding(String stemme, Event idEvent, Timestamp tid) {
		this.stemme = stemme;
		this.idEvent = idEvent;
		this.tid = tid;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the stemme
	 */
	public String getStemme() {
		return stemme;
	}

	/**
	 * @param stemme
	 *            the stemme to set
	 */
	public void setStemme(String stemme) {
		this.stemme = stemme;
	}

	/**
	 * @return the idEvent
	 */
	public Event getIdEvent() {
		return idEvent;
	}

	/**
	 * @param idEvent
	 *            the idEvent to set
	 */
	public void setIdEvent(Event idEvent) {
		this.idEvent = idEvent;
	}

	/**
	 * @return the tid
	 */
	public Timestamp getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(Timestamp tid) {
		this.tid = tid;
	}

}
