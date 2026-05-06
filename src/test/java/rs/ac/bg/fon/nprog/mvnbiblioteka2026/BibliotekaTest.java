package rs.ac.bg.fon.nprog.mvnbiblioteka2026;

import rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs.BibliotekaInterface;
import rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs.BibliotekaInterfaceTest;

class BibliotekaTest extends BibliotekaInterfaceTest {
	
	@Override
	public BibliotekaInterface getInstance() {
		return new Biblioteka();
	}

}
