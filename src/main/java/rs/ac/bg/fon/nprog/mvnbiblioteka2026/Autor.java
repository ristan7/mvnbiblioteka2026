package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import java.util.Objects;

/**
 * Predtavlja autora knjige.
 * 
 * Svaki autor ima ime i prezime.
 * 
 * @author Mihajlo Ristanovic
 * @version 1.0
 * 
 */
public class Autor {
	/**
	 * Ime autora kao String.
	 */
	private String ime;
	/**
	 * Prezime autora kao String.
	 */
	private String prezime;
	
	/**
	 * Kreira objekat klase Autor sa imenom i prezimenom koji su null.
	 */
	public Autor() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Kreira objekat klase Autor koji ima uneto ime i prezime.
	 * 
	 * Poziva set metode za oba parametra uz logicku kontrolu.
	 * 
	 * @param ime Ime autora. Ne sme biti null niti prazno.
	 * @param prezime Prezime autora. Ne sme biti null niti prazno.
	 */
	public Autor(String ime, String prezime) {
		super();
		this.setIme(ime);
		this.setPrezime(prezime);
	}
	
	/**
	 * Vraca ime autora.
	 * @return ime autora kao String.
	 */
	public String getIme() {
		return ime;
	}
	
	/**
	 * Postavlja ime autora na unetu vrednost.
	 * @param ime Novo ime autora.
	 * @throws java.lang.NullPointerException Ako je uneto ime null.
	 * @throws java.lang.IllegalArgumentException Ako je uneto ime prazan String.
	 */
	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException("Ime ne sme biti null");
		}
		if (ime.isEmpty()) {
			throw new IllegalArgumentException("Ime ne sme biti prazno");
		}
		this.ime = ime;
	}
	
	/**
	 * Vraca prezime autora.
	 * @return prezime autora kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime autora na unetu vrednost.
	 * @param ime Novo prezime autora.
	 * @throws java.lang.NullPointerException Ako je uneto prezime null.
	 * @throws java.lang.IllegalArgumentException Ako je uneto prezime prazan String.
	 */
	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException("Prezime ne sme biti null");
		}
		if (prezime.isEmpty()) {
			throw new IllegalArgumentException("Prezime ne sme biti prazno");
		}
		this.prezime = prezime;
	}
	
	/**
	 * Vraca String sa svim podacima o autoru.
	 * 
	 * @return Svi podaci o autoru u formatu "Autor [ime=####", prezime=####"]"
	 */
	@Override
	public String toString() {
		return "Autor [ime=" + ime + ", prezime=" + prezime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ime, prezime);
	}

	/**
	 * Poredi dva autora po imenu i prezimenu.
	 * 
	 * @param obj Drugi autor sa kojim se poredi.
	 * @return 
	 * <ul>
	 * <li><b>true</b> ako su oba objekta klasa Autor sa istim imenima i prezimenima ili ako su na istoj adresi.</li>
	 * 
	 * <li><b>false</b> ako je drugi objekat null, ako je druge klase ili ako nisu isti ime i prezime.</li>
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
		Autor other = (Autor) obj;
		return Objects.equals(ime, other.ime) && Objects.equals(prezime, other.prezime);
	}

}
