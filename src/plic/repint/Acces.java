package plic.repint;

/**
 * Acces :
 * - soit le nom d'une variable : var
 * - soit le nom d'un tableau suivi d'un emplacement : tab[2]
 */
public class Acces {
	private final Idf idf;
	private final Expression expr;

	public Acces(Idf idf, Expression expr) {
		this.idf = idf;
		this.expr = expr;
	}

	public Acces(Idf idf) {
		this.idf = idf;
		this.expr = null;
	}

	public Expression getExpr() {
		return expr;
	}

	public void verifier() {
		Entree e = new Entree(this.expr.toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null)
			throw new RuntimeException("ecrire : l'identificateur " + this.expr.toString() + " n'existe pas");
	}
}
