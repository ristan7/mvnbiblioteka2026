package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

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
	 * <b>Implementirana je pretraga samo preko naslova a ne preko ostalih
	 * kriterijuma.</b>
	 * 
	 * @param autor   Jedan od autora knjige.
	 * @param isbn    Tacan isbn broj knjige.
	 * @param naslov  Deo naslova knjige. Ne mora se unositi ceo naslov.
	 * @param izdavac Deo naziva izdavaca. Ne mora se unositi ceo naziv.
	 * @return Vraca listu sa knjigama koje odgovaraju kriterijumima ili praznu
	 *         listu ako ni jedna knjiga ne odgovara kriterijumima.
	 * 
	 * @throws java.lang.IllegalArgumentException ako nije unet nijedan kriterijum
	 *                                            pretrage, odnosno ako su svi null
	 *                                            odnosno null.
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

	@Override
	public void sacuvajUFajl(String fajl) {

		if (fajl == null) {
			throw new NullPointerException("Putanja do fajla ne sme biti null");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter fw = new FileWriter(fajl)) {
			gson.toJson(knjige, fw);
		} catch (IOException e) {
			throw new RuntimeException("Greska pri upisu u JSON fajl", e);
		}

	}

	@Override
	public void ucitajIzFajla(String fajl) {
		if (fajl == null) {
			throw new NullPointerException("Putanja do fajla ne sme biti null");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileReader fr = new FileReader(fajl)) {
			List<Knjiga> noveKnjige = Arrays.asList(gson.fromJson(fr, Knjiga[].class));
			if (noveKnjige != null) {
				for (Knjiga novaKnjiga : noveKnjige) {
					if (!knjige.contains(novaKnjiga)) {
						knjige.add(novaKnjiga);
					}
				}
			}

		} catch (IOException ex) {
			throw new RuntimeException("Greska pri ucitavanju iz JSON fajla", ex);
		}

	}

	/**
	 * Pretrazuje biblioteku i upisuje sve knjige koje imaju uneti deo naslova u
	 * JSON fajl.
	 * 
	 * <b>Implementirana je pretraga samo preko naslova a ne preko ostalih
	 * kriterijuma.</b>
	 * 
	 * @param autor   Jedan od autora knjige.
	 * @param isbn    Tacan isbn broj knjige.
	 * @param naslov  Deo naslova knjige. Ne mora se unositi ceo naslov.
	 * @param izdavac Deo naziva izdavaca. Ne mora se unositi ceo naziv.
	 * @param fajl    Naziv fajla u koji se upisuju rezultati pretrage.
	 * @return Upisuje listu sa knjigama koje odgovaraju kriterijumima ili praznu
	 *         listu ako ni jedna knjiga ne odgovara kriterijumima.
	 * 
	 * 
	 * @throws java.lang.NullPointerException Ako je zadati fajl null.
	 * @throws java.lang.RuntimeException     Ako dodje do greske pri upisivanju u
	 *                                        JSON fajl.
	 */
	@Override
	public void pronadjiKnjigu(Autor autor, long isbn, String naslov, String izdavac, String fajl) {
		if (fajl == null) {
			throw new NullPointerException("Putanja do fajla ne sme biti null");
		}

		List<Knjiga> rezultati = pronadjiKnjigu(autor, isbn, naslov, izdavac);

		JsonArray jsonRezultati = new JsonArray();

		for (Knjiga k : rezultati) {
			JsonObject knjigaJson = new JsonObject();

			knjigaJson.addProperty("isbn", k.getIsbn());
			knjigaJson.addProperty("title", k.getNaslov());

			if (k.getAutori() == null || k.getAutori().isEmpty()) {
				knjigaJson.add("authors", JsonNull.INSTANCE);
			} else {
				JsonArray authors = new JsonArray();

				for (Autor a : k.getAutori()) {
					authors.add(a.getIme() + " " + a.getPrezime());
				}

				knjigaJson.add("authors", authors);
			}
			jsonRezultati.add(knjigaJson);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

		try (FileWriter fw = new FileWriter(fajl)) {
			gson.toJson(jsonRezultati, fw);
		} catch (IOException e) {
			throw new RuntimeException("Greska pri upisu u JSON fajl", e);
		}
	}

}
