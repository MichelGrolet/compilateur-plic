package plic.repint;

public class Idf extends Acces {
	private final String nom;

	public Idf(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	public void verifier() {
		Entree e = new Entree(this.expr.toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null)
			throw new RuntimeException("ecrire : l'identificateur " + this.expr.toString() + " n'existe pas");
	}
}
