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
		Entree e = new Entree(this.nom);
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) throw new RuntimeException("ecrire : l'identificateur " + this.nom + " n'existe pas");
	}

	@Override
	public String toMips() {
		Entree e = new Entree(this.nom);
		Symbole s = TDS.getInstance().identifier(e);
		return "li $v0, " + s.getDepl() + "\n";
	}

	@Override
	public String getType() {
		return "entier";
	}

	@Override
	public int getAdresse() {
		Entree e = new Entree(this.nom);
		Symbole s = TDS.getInstance().identifier(e);
		return s.getDepl();
	}
}
