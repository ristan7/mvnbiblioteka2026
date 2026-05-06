package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs.BibliotekaInterface;

/**
 * Implementira BibliotekaInterface preko interne liste sa knjigama.
 * 
 * @author Mihajlo Ristanovic
 */
public class Biblioteka implements BibliotekaInterface {

	private List<Knjiga> knjige = new ArrayList<>();// implementacija liste preko nizova, prvih 16 popuni pa doda jos 16
													// itd

	@Override
	public void dodajKnjigu(Knjiga knjiga) {
		if (knjiga == null) {
			throw new NullPointerException("Knjiga ne sme biti null");
		}
		if (knjige.contains(knjiga)) {
			throw new IllegalArgumentException("Knjiga vec postoji");
		}
		knjige.add(knjiga);

	}

	@Override
	public void obrisiKnjigu(Knjiga knjiga) {
		if (knjiga == null) {
			throw new NullPointerException("Knjiga ne sme biti null");
		}
		if (!knjige.contains(knjiga)) {
			throw new IllegalArgumentException("Knjiga ne postoji");
		}
		knjige.remove(knjiga);

	}

	@Override
	public List<Knjiga> vratiSveKnjige() {
		return knjige;
	}
	
	/**
	 * Pretrazuje biblioteku i vraca sve knjige koje imaju uneti deo naslova.
	 * 
	 * <b>Implementirana je pretraga samo preko naslova a ne preko ostalih kriterijuma.</b>
	 * 
	 * @param autor Jedan od autora knjige.
	 * @param isbn Tacan isbn broj knjige.
	 * @param naslov Deo naslova knjige. Ne mora se unositi ceo naslov. 
	 * @param izdavac Deo naziva izdavaca. Ne mora se unositi ceo naziv.
	 * @return Vraca listu sa knjigama koje odgovaraju kriterijumima ili praznu listu ako ni jedna knjiga ne odgovara kriterijumima.
	 * 
	 * @throws java.lang.IllegalArgumentException ako nije unet nijedan kriterijum pretrage, odnosno ako su svi null odnosno null.
	 */
	@Override
	public List<Knjiga> pronadjiKnjigu(Autor autor, long isbn, String naslov, String izdavac) {

		if (autor == null && isbn <= 0 && naslov == null && izdavac == null) {
			throw new IllegalArgumentException("Morate uneti bar neki kriterijum pretrage");
		}

		List<Knjiga> rezultati = new ArrayList<Knjiga>();

		for (Knjiga k : knjige) {
			if (k.getNaslov().toUpperCase().contains(naslov.toUpperCase())) {
				rezultati.add(k);
			}
		}

		return rezultati;
	}

}
