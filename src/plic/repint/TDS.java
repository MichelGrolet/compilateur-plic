package plic.repint;

import plic.exception.DoubleDeclaration;

import java.util.HashMap;

/**
 * TDS : stocke les variables déclarées dans le programme
 * Entrée : nom de la variable
 * Symbole : type de la variable et déplacement
 */
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
		if (instance.containsKey(e)) throw new DoubleDeclaration(e.getIdf());
		else {
			s.setDepl(cptDepl);
			this.cptDepl -= 4;
			instance.put(e, s);
		}
	}

	public Symbole identifier(Entree e) {
		for (Entree key : this.keySet())
			if (key.getIdf().equals(e.getIdf())) return this.get(key);
		return null;
	}

	public int getCptDepl() {
		return cptDepl;
	}
}