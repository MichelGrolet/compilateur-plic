package plic.repint;

public class Affectation extends Instruction {

	/**
	 * idf : nom de la variable à affecter
	 */
	private final Idf idf;

	/**
	 * expr : valeur de l'affectation
	 */
	private final Expression expr;

	public Affectation(Idf idf, Expression expr) {
		this.expr = expr;
		this.idf = idf;
	}

	@Override
	public String toString() {
		return idf + " = " + expr;
	}

	/**
	 * Vérifie que l'affectation se fait sur une variable existante
	 */
	@Override
	public void verifier() {
		Entree e = new Entree(this.idf.toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) {
			throw new RuntimeException("Erreur : l'identificateur " + this.idf + " n'existe pas");
		}
	}
}
