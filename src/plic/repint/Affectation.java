package plic.repint;

public class Affectation extends Instruction {

	/**
	 * acces : nom de (la variable / emplacement de tableau) à affecter
	 */
	private final Acces acces;

	/**
	 * expr : valeur de l'affectation
	 */
	private final Expression expr;

	public Affectation(Acces a, Expression expr) {
		this.acces = a;
		this.expr = expr;
	}

	public Affectation(Acces a) {
		this(a, null);
	}

	@Override
	public String toString() {
		return acces.toString() + " := " + expr;
	}

	/**
	 * Vérifie que l'affectation se fait sur une variable existante
	 */
	@Override
	public void verifier() {
		Entree e = new Entree(this.acces.getIdf().toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null)
			throw new RuntimeException("affectation : l'identificateur " + this.acces.getIdf().toString() + " n'existe pas");
	}

	@Override
	public String toMips() {
		String mips = "# Affectation " + this.toString() + "\n";
		// réservation de l'espace mémoire
		mips += "add $sp, $sp, -4\n";
		// assignation de la valeur de l'expression à $v0
		mips += "li $v0, " + this.expr.toString() + "\n";
		// récupération de l'adresse de la variable dans TDS
		Entree e = new Entree(this.acces.getIdf().toString());
		Symbole s = TDS.getInstance().identifier(e);
		int empl = s.getDepl()-4;
		mips += "sw $v0, "+ empl +"($s7)\n";
		return mips;
	}
}
