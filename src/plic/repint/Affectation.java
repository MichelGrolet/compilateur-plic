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
		return idf + " := " + expr;
	}

	/**
	 * Vérifie que l'affectation se fait sur une variable existante
	 */
	@Override
	public void verifier() {
		Entree e = new Entree(this.idf.toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null)
			throw new RuntimeException("affectation : l'identificateur " + this.idf.toString() + " n'existe pas");
	}

	@Override
	public String toMips() {
		String mips = "# Affectation " + this.toString() + "\n";
		// réservation de l'espace mémoire
		mips += "add $sp, $sp, -4\n";
		// assignation de la valeur de l'expression à $v0
		mips += "li $v0, " + this.expr.toString() + "\n";
		// récupération de l'adresse de la variable dans TDS
		Symbole s = TDS.getInstance().identifier(new Entree(this.idf.toString()));
		int empl = s.getDepl()-4;
		mips += "sw $v0, "+ empl +"($s7)\n";
		return mips;
	}
}
