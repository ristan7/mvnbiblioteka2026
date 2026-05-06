/**
 * 
 */
package rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.mvnbiblioteka2026.Knjiga;

/**
 * 
 */
public abstract class BibliotekaInterfaceTest {

	BibliotekaInterface b;

	Knjiga k, k2, k3;

	public abstract BibliotekaInterface getInstance();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		b = getInstance();

		k = new Knjiga();
		k.setIsbn(123);

		k2 = new Knjiga();
		k2.setIsbn(345);

		k3 = new Knjiga();
		k3.setIsbn(987);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		b = null;
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#dodajKnjigu(biblioteka.Knjiga)}.
	 */
	@Test
	void testDodajKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> b.dodajKnjigu(null));
	}

	@Test
	void testDodajKnjiguDuplikat() {
		b.dodajKnjigu(k);

		k2.setIsbn(123);

		assertThrows(java.lang.IllegalArgumentException.class, () -> b.dodajKnjigu(k2));
	}

	@Test
	void testDodajKnjigu() {
		b.dodajKnjigu(k);

		b.dodajKnjigu(k2);

		List<Knjiga> knjige = b.vratiSveKnjige();

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k2));
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#obrisiKnjigu(biblioteka.Knjiga)}.
	 */
	@Test
	void testObrisiKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> b.obrisiKnjigu(null));
	}

	@Test
	void testObrisiKnjiguNePostoji() {
		b.dodajKnjigu(k);

		b.dodajKnjigu(k2);

		assertThrows(java.lang.IllegalArgumentException.class, () -> b.obrisiKnjigu(k3));
	}

	@Test
	void testObrisiKnjigu() {
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		b.obrisiKnjigu(k2);

		List<Knjiga> knjige = b.vratiSveKnjige();

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k3));
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#pronadjiKnjigu(biblioteka.Autor, long, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testPronadjiKnjiguSveNull() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.pronadjiKnjigu(null, 0, null, null));
	}

	@Test
	void testPronadjiKnjiguPrazno() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("Knjiga 2");
		k3.setNaslov("Knjiga 3");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(0, knjige.size());

	}

	@Test
	void testPronadjiKnjiguJednaKnjiga() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("1 gospodar prstenova");
		k3.setNaslov("Knjiga 3");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k2));

	}

	@Test
	void testPronadjiKnjiguViseKnjiga() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("1 gospodar prstenova");
		k3.setNaslov("2 gospodar prstenova");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k2));
		assertTrue(knjige.contains(k3));

	}

}
