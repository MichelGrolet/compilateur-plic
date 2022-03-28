package plic.repint;

public class AccesTableau extends Acces {
	public final String nom;
	public final Nombre taille;

	public AccesTableau(String n, Nombre t) {
		this.nom = n;
		this.taille = t;
	}

	@Override
	public void verifier() {
		Entree e = new Entree(this.nom);
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) throw new RuntimeException("ecrire : l'identificateur " + this.nom + " n'existe pas");
	}

	@Override
	public String toMips() {
		return "";
	}

	@Override
	public String getType() {
		return "tableau";
	}

	@Override
	public int getAdresse() {
		Entree e = new Entree(this.nom);
		Symbole s = TDS.getInstance().identifier(e);
		return s.getDepl();
	}
}
