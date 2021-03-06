/**
 * 
 */
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
 * Modellrepresentasjon av Livetilbakemeldinger
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "LiveTilbakemelding")
@Table(name = "livetm", schema = "db")
public class LiveTilbakemelding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String stemme;
	private Timestamp tid;
	@ManyToOne
	@JoinColumn(name = "id_event", referencedColumnName = "id")
	private Event idEvent;

	/**
	 * Tom konstruktor for live tilbakemeldinger
	 */
	public LiveTilbakemelding() {
		this("", null, null);
	}

	/**
	 * Konstruktor med parameter live tilbakemeldinger
	 * 
	 * @param stemme
	 *            Stemme
	 * @param tid
	 *            Tiden
	 * @param idEvent
	 *            Id til event
	 */
	public LiveTilbakemelding(String stemme, Timestamp tid, Event idEvent) {
		this.stemme = stemme;
		this.tid = tid;
		this.idEvent = idEvent;
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
