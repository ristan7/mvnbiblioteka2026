package rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs;

import java.util.List;

import rs.ac.bg.fon.nprog.mvnbiblioteka2026.Autor;
import rs.ac.bg.fon.nprog.mvnbiblioteka2026.Knjiga;

/**
 * Predstavlja Biblioteku koja moze da se pretrazuje, mogu da se dodaju knjige i brisu.
 * 
 * @author Mihajlo Ristanovic
 * @version 1.0
 */
public interface BibliotekaInterface {
	
	/**
	 * Dodaje knjigu u biblioteku. 
	 * 
	 * Knjiga ne sme biti null i ne sme biti duplikat.
	 * 
	 * @param knjiga Nova knjiga koju treba dodati.
	 * 
	 * @throws java.lang.NullPointerException ako je uneta knjiga null
	 * @throws java.lang.IllegalArgumentException ako uneta knjiga vec postoji u biblioteci.
	 */
	public void dodajKnjigu(Knjiga knjiga);
	
	/**
	 * Brise knjigu iz biblioteke. 
	 * 
	 * Knjiga ne sme biti null i mora vec postojati u biblioteci.
	 * 
	 * @param knjiga Stara knjiga koju treba obrisati.
	 * 
	 * @throws java.lang.NullPointerException ako je uneta knjiga null
	 * @throws java.lang.IllegalArgumentException ako uneta knjiga ne postoji u biblioteci.
	 */
	public void obrisiKnjigu(Knjiga knjiga);
	
	/**
	 * Vraca sve knjige iz biblioteke.
	 * 
	 * @return Lista sa svim knjigama ili prazna lista ako u biblioteci nema ni jedne knjige.
	 */
	public List<Knjiga> vratiSveKnjige();
	
	/**
	 * Pretrazuje biblioteku i vraca sve knjige koje odgovaraju unetim kriterijumima.
	 * 
	 * Mora se uneti bar jedan kriterijum pretrage. Ako se unese vise kriterijuma odjednom, pretraga se vrsi preko svih unetih kriterijuma.
	 * 
	 * @param autor Jedan od autora knjige.
	 * @param isbn Tacan isbn broj knjige.
	 * @param naslov Deo naslova knjige. Ne mora se unositi ceo naslov. 
	 * @param izdavac Deo naziva izdavaca. Ne mora se unositi ceo naziv.
	 * @return Vraca listu sa knjigama koje odgovaraju kriterijumima ili praznu listu ako ni jedna knjiga ne odgovara kriterijumima.
	 * 
	 * @throws java.lang.IllegalArgumentException ako nije unet nijedan kriterijum pretrage, odnosno ako su svi null odnosno null.
	 */
	public List<Knjiga> pronadjiKnjigu(Autor autor, long isbn, String naslov, String izdavac);
}
