package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import java.util.List;
import java.util.Objects;

/**
 * Predstavlja knjigu.
 * 
 * Svaka knjiga ima naslov, ISBN, listu autora, izdavaca i izdanje.
 * 
 * @author Mihajlo Ristanovic
 * @version 1.0
 */
public class Knjiga {

	/**
	 * Naslov knjige kao String.
	 */
	private String naslov;

	/**
	 * ISBN broj knjige.
	 */
	private long isbn;

	/**
	 * Lista autora knjige.
	 */
	private List<Autor> autori;

	/**
	 * Izdavac knjige kao String.
	 */
	private String izdavac;

	/**
	 * Izdanje knjige.
	 */
	private int izdanje;

	/**
	 * Vraca naslov knjige.
	 * 
	 * @return naslov knjige kao String.
	 */
	public String getNaslov() {
		return naslov;
	}

	/**
	 * Postavlja naslov knjige na unetu vrednost.
	 * 
	 * @param naslov Novi naslov knjige.
	 * @throws java.lang.NullPointerException Ako je naslov null.
	 * @throws java.lang.IllegalArgumentException Ako je naslov prazan String.
	 */
	public void setNaslov(String naslov) {
		if (naslov == null) {
			throw new NullPointerException("Naslov ne sme biti null");
		}
		if (naslov.isEmpty()) {
			throw new IllegalArgumentException("Naslov ne sme biti prazan");
		}
		this.naslov = naslov;
	}

	/**
	 * Vraca ISBN knjige.
	 * 
	 * @return ISBN knjige.
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * Postavlja ISBN knjige na unetu vrednost.
	 * 
	 * @param isbn Novi ISBN knjige.
	 * @throws java.lang.IllegalArgumentException Ako je ISBN manji ili jednak nuli.
	 */
	public void setIsbn(long isbn) {
		if (isbn <= 0) {
			throw new IllegalArgumentException("ISBN mora biti veci od nule");
		}
		this.isbn = isbn;
	}

	/**
	 * Vraca listu autora knjige.
	 * 
	 * @return lista autora knjige.
	 */
	public List<Autor> getAutori() {
		return autori;
	}

	/**
	 * Postavlja listu autora knjige.
	 * 
	 * @param autori Lista autora.
	 */
	public void setAutori(List<Autor> autori) {
		this.autori = autori;
	}

	/**
	 * Vraca izdavaca knjige.
	 * 
	 * @return izdavac knjige kao String.
	 */
	public String getIzdavac() {
		return izdavac;
	}

	/**
	 * Postavlja izdavaca knjige na unetu vrednost.
	 * 
	 * @param izdavac Novi izdavac knjige.
	 * @throws java.lang.NullPointerException Ako je izdavac null.
	 * @throws java.lang.IllegalArgumentException Ako je izdavac prazan String.
	 */
	public void setIzdavac(String izdavac) {
		if (izdavac == null) {
			throw new NullPointerException("Izdavac ne sme biti null");
		}
		if (izdavac.isEmpty()) {
			throw new IllegalArgumentException("Izdavac ne sme biti prazan");
		}
		this.izdavac = izdavac;
	}

	/**
	 * Vraca izdanje knjige.
	 * 
	 * @return izdanje knjige.
	 */
	public int getIzdanje() {
		return izdanje;
	}

	/**
	 * Postavlja izdanje knjige na unetu vrednost.
	 * 
	 * @param izdanje Novo izdanje knjige.
	 * @throws java.lang.IllegalArgumentException Ako je izdanje manje ili jednako nuli.
	 */
	public void setIzdanje(int izdanje) {
		if (izdanje <= 0) {
			throw new IllegalArgumentException("Izdanje mora biti vece od nule");
		}
		this.izdanje = izdanje;
	}

	/**
	 * Vraca String sa svim podacima o knjizi.
	 * 
	 * @return Svi podaci o knjizi u formatu 
	 * "Knjiga [naslov=####, isbn=####, autori=####, izdavac=####, izdanje=####]"
	 */
	@Override
	public String toString() {
		return "Knjiga [naslov=" + naslov + ", isbn=" + isbn + ", autori=" + autori + ", izdavac=" + izdavac
				+ ", izdanje=" + izdanje + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	/**
	 * Poredi dve knjige po ISBN broju.
	 * 
	 * @param obj Druga knjiga sa kojom se poredi.
	 * @return 
	 * <ul>
	 * <li><b>true</b> ako su oba objekta klase Knjiga sa istim ISBN brojem ili ako su na istoj adresi.</li>
	 * 
	 * <li><b>false</b> ako je drugi objekat null, druge klase ili imaju razlicit ISBN.</li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knjiga other = (Knjiga) obj;
		return isbn == other.isbn;
	}
}