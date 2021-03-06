package no.hvl.dat104.dataaccess.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.model.Tilbakemelding;

@Stateless
public class EventEAO implements IEventEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilEvent(Event e) {
		em.persist(e);

	}

	@Override
	public Event finnEvent(Integer id) {
		try {
			return em.find(Event.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void oppdaterEvent(Event e) {
		em.merge(e);

	}

	@Override
	public void slettEvent(Event e) {
		em.remove(em.find(Event.class, e.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> alleEventer() {
		List<Event> eventer = em.createQuery("SELECT e FROM Event e").getResultList();
		return eventer;
	}

	@Override
	public List<Tilbakemelding> finnAlleTilbakemeldingerTilEvent(Integer id) {
		List<Tilbakemelding> t = new ArrayList<>();
		t.addAll(em.find(Event.class, id).getTilbakemeldinger());
		return t;
	}

	@Override
	public void endreStatusPaaEvent(Integer id, String status) {
		Event e = finnEvent(id);
		e.setStatus(status);
		em.merge(e);
	}

	@Override
	public Event finnEventBasertPaaKodeord(Kodeord k) {
		return (Event) em.createQuery("SELECT e FROM Event e, Kodeord k WHERE e = k.idEvent AND k.kode=" + k.getKode())
				.getSingleResult();
	}

	@Override
	public Aktivitet finnAktivitetTilEvent(Integer id) {
		return (Aktivitet) em.createQuery("SELECT a FROM Aktivitet a, Event e WHERE a = e.idAktivitet AND e.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public void endreParametereTilEvent(Integer id, String navn, String beskrivelse, Timestamp tidFra, Timestamp tidTil,
			String sted) {

		Event e = finnEvent(id);
		e.setNavn(navn);
		e.setBeskrivelse(beskrivelse);
		e.setTidFra(tidFra);
		e.setTidTil(tidTil);
		e.setSted(sted);

		em.merge(e);
	}

	@Override
	public boolean endreEventTilPaagaaende(Integer eventId) {
		try {
			Event e = finnEvent(eventId);
			e.setFaktiskStart(new Timestamp(System.currentTimeMillis()));
			e.setStatus(Status.PAAGANDE);
			em.merge(e);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<LiveTilbakemelding> finnAlleLiveTilbakemeldingerTilEvent(Integer id) {
		List<LiveTilbakemelding> t = new ArrayList<>();
		t.addAll(em.find(Event.class, id).getLiveTilbakemeldinger());
		return t;
	}

	@Override
	public boolean endreEventTilAvsluttet(Integer eventId) {
		try {
			Event e = finnEvent(eventId);
			e.setFaktiskSlutt(new Timestamp(System.currentTimeMillis()));
			e.setStatus(Status.AVSLUTTET);
			em.merge(e);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
