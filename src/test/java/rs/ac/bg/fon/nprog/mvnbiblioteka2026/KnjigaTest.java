package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnjigaTest {

	Knjiga k;

	@BeforeEach
	void setUp() throws Exception {
		k = new Knjiga();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testKnjiga() {
		assertNotNull(k);
	}

	@Test
	void testSetNaslov() {
		k.setNaslov("Na Drini cuprija");

		assertEquals("Na Drini cuprija", k.getNaslov());
	}

	@Test
	void testSetNaslovNull() {
		Exception e = assertThrows(NullPointerException.class, () -> k.setNaslov(null));

		assertEquals("Naslov ne sme biti null", e.getMessage());
	}

	@Test
	void testSetNaslovPrazanString() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setNaslov(""));

		assertEquals("Naslov ne sme biti prazan", e.getMessage());
	}

	@Test
	void testSetIsbn() {
		k.setIsbn(123456789);

		assertEquals(123456789, k.getIsbn());
	}

	@Test
	void testSetIsbnNula() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setIsbn(0));

		assertEquals("ISBN mora biti veci od nule", e.getMessage());
	}

	@Test
	void testSetIsbnNegativan() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setIsbn(-15));

		assertEquals("ISBN mora biti veci od nule", e.getMessage());
	}

	@Test
	void testSetAutori() {
		List<Autor> autori = new ArrayList<>();
		autori.add(new Autor("Ivo", "Andric"));
		autori.add(new Autor("Mesa", "Selimovic"));

		k.setAutori(autori);

		assertEquals(autori, k.getAutori());
		assertEquals(2, k.getAutori().size());
	}

	@Test
	void testSetAutoriNull() {
		k.setAutori(null);

		assertNull(k.getAutori());
	}

	@Test
	void testSetIzdavac() {
		k.setIzdavac("Laguna");

		assertEquals("Laguna", k.getIzdavac());
	}

	@Test
	void testSetIzdavacNull() {
		Exception e = assertThrows(NullPointerException.class, () -> k.setIzdavac(null));

		assertEquals("Izdavac ne sme biti null", e.getMessage());
	}

	@Test
	void testSetIzdavacPrazanString() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setIzdavac(""));

		assertEquals("Izdavac ne sme biti prazan", e.getMessage());
	}

	@Test
	void testSetIzdanje() {
		k.setIzdanje(3);

		assertEquals(3, k.getIzdanje());
	}

	@Test
	void testSetIzdanjeNula() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setIzdanje(0));

		assertEquals("Izdanje mora biti vece od nule", e.getMessage());
	}

	@Test
	void testSetIzdanjeNegativan() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> k.setIzdanje(-2));

		assertEquals("Izdanje mora biti vece od nule", e.getMessage());
	}

	@Test
	void testToString() {
		List<Autor> autori = new ArrayList<>();
		autori.add(new Autor("Ivo", "Andric"));

		k.setNaslov("Prokleta avlija");
		k.setIsbn(111222333);
		k.setAutori(autori);
		k.setIzdavac("Vulkan");
		k.setIzdanje(2);

		String s = k.toString();

		assertTrue(s.contains("Prokleta avlija"));
		assertTrue(s.contains("111222333"));
		assertTrue(s.contains("Vulkan"));
		assertTrue(s.contains("2"));
		assertTrue(s.contains("Ivo"));
		assertTrue(s.contains("Andric"));
	}

	@Test
	void testHashCode() {
		k.setIsbn(12345);

		Knjiga k2 = new Knjiga();
		k2.setIsbn(12345);

		assertEquals(k.hashCode(), k2.hashCode());
	}

	@ParameterizedTest
	@DisplayName("Testovi za equals metodu")
	@CsvSource({
		"12345, 12345, true",
		"12345, 67890, false",
		"99999, 99999, true",
		"1, 2, false"
	})
	void testEqualsObject(long isbn1, long isbn2, boolean jednako) {
		k.setIsbn(isbn1);

		Knjiga k2 = new Knjiga();
		k2.setIsbn(isbn2);

		assertEquals(jednako, k.equals(k2));
	}

	@Test
	void testEqualsNull() {
		assertFalse(k.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(k.equals(new String()));
	}
}