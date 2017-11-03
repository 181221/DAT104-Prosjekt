package no.hvl.dat104.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.hvl.dat104.model.Tilbakemelding;

public class FormaterTilbakemeldingUtil {

	/**
	 * En liste med formaterte tilbakemeldinger, blir returnert etter konvertering
	 */
	private static List<FormatertTilbakemelding> formaterteTilbakemeldinger = new ArrayList<FormatertTilbakemelding>();

	/**
	 * Tar inn liste med tilbakemeldinger som argument, konverterer til en liste som
	 * er enklere � arbeide med
	 * 
	 * @param liste
	 * @return
	 */
	public static List<FormatertTilbakemelding> formaterTilbakemeldinger(List<Tilbakemelding> liste) {
		if (!liste.isEmpty()) {
			for (Tilbakemelding tilbakemelding : liste) {
				if (!tilbakemeldingMedSammeTidsStempelEksisterer(tilbakemelding.getTid())) {
					System.out.println("Bra");
					FormatertTilbakemelding tilbakemeldingFormatert = new FormatertTilbakemelding();
					konverterStemmeOgInkrementerTilbakemelding(tilbakemeldingFormatert, tilbakemelding.getStemme());
					tilbakemeldingFormatert.setTid(tilbakemelding.getTid());
					formaterteTilbakemeldinger.add(tilbakemeldingFormatert);
				} else {
					finnTilbakemeldingMedSammeTidOgLeggTilStemme(tilbakemelding.getTid(), tilbakemelding.getStemme());
				}
			}
			sorterTilbakemeldingere();
			return formaterteTilbakemeldinger;
		} else {
			System.out.println("Hmm?");
			formaterteTilbakemeldinger.add(new FormatertTilbakemelding(0, 0, 0));
			return formaterteTilbakemeldinger;
		}
	}
	
	private static void sorterTilbakemeldingere() {
		Collections.sort(formaterteTilbakemeldinger, new Comparator<FormatertTilbakemelding>(){
			   @Override
			   public int compare(final FormatertTilbakemelding ftb1, FormatertTilbakemelding ftb2) {
				return ftb1.getTid().compareTo(ftb2.getTid());
			     }
			 });
	}
	
	/**
	 * Hjelpemetode, leter gjennom listen med konverterte tilbakemeldinger og finner
	 * en tilbakemelding med samme tid og �ker stemme antallet
	 * 
	 * @param tid
	 * @param stemme
	 */
	private static void finnTilbakemeldingMedSammeTidOgLeggTilStemme(Timestamp tid, String stemme) {
		for (FormatertTilbakemelding formatertTilbakemelding : formaterteTilbakemeldinger) {
			if (formatertTilbakemelding.getTid().compareTo(tid) == 0) {
				konverterStemmeOgInkrementerTilbakemelding(formatertTilbakemelding, stemme);
			}
		}
	}

	/**
	 * Konverterer stemme fra string format til konvertert tilbakemelding format
	 * 
	 * @param formatertTilbakemelding
	 * @param stemme
	 */
	private static void konverterStemmeOgInkrementerTilbakemelding(FormatertTilbakemelding formatertTilbakemelding,
			String stemme) {
		switch (Integer.valueOf(stemme)) {
		case 0:
			formatertTilbakemelding.incrementFornoyd();
			break;
		case 1:
			formatertTilbakemelding.incrementNoytral();
			break;
		case 2:
			formatertTilbakemelding.incrementMisfornoyd();
			break;
		}
		;
	}

	/**
	 * Sjekker om listen med formaterte tilbakemeldinger har en tilbakemelding med
	 * samme timestamp
	 * 
	 * @param tid
	 * @return
	 */
	private static boolean tilbakemeldingMedSammeTidsStempelEksisterer(Timestamp tid) {
		for (FormatertTilbakemelding ft : formaterteTilbakemeldinger) {
			if (ft.getTid().compareTo(tid) == 0) {
				return true;
			}
		}
		return false;
	}
}