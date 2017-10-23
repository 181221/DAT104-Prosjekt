package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Rettigheter;

public interface IRettigheterEAO {

	/**
	 * Legger til en Rettigheter
	 * 
	 * @param r
	 *		Rettigheter
	 */
	public void leggTilRettigheter(Rettigheter r);

	/**
	 * Finner en rettighet
	 * 
	 * @param id
	 *		Id til rettigheten
	 */
	public Rettigheter finnRettigheter(Integer id);
	
	/**
	 * Oppdaterer en rettigheter
	 * 
	 * @param r
	 *		Rettigheter
	 */
	public void oppdaterRettigheter(Rettigheter r);
	
	/**
	 * Sletter en rettigheter
	 * 
	 * @param r
	 *		Rettigheter
	 */
	public void slettRettigheter(Rettigheter r);
	
	/**
	 * Returnerer en liste med alle rettigheter
	 *
	 * @return Alle rettigheter
	 */
	public List<Rettigheter> alleRettigheter();
	
	
}
