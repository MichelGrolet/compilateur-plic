package plic.repint;

import plic.exception.DoubleDeclaration;

import java.util.HashMap;

public class TDS extends HashMap<Entree, Symbole> {

	private static TDS instance;

	// cptDepl : compteur de déplacement
	private int cptDepl;

	private TDS() {
		cptDepl = 0;
	}

	public static TDS getInstance() {
		if (instance == null)
			instance = new TDS();
		return instance;
	}

	public void ajouter(Entree e, Symbole s) throws DoubleDeclaration {
		System.out.println("Ajout de " + e.getIdf() + " : " + s.getType());
		if (instance.containsKey(e)) throw new DoubleDeclaration(e.getIdf());
		else {
			s.setDepl(cptDepl);
			this.cptDepl -= 4;
			instance.put(e, s);
		}
	}

	public int getCptDepl() {
		return cptDepl;
	}
}