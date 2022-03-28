package plic.analyse;

import plic.exception.*;
import plic.repint.*;

import java.io.File;

public class AnalyseurSyntaxique {
	private final AnalyseurLexical analex;
	private String uniteCourante;
	private Bloc bloc;

	public AnalyseurSyntaxique(File file) {
		this.analex = new AnalyseurLexical(file);
	}

	private void analyseProg() throws ErreurSyntaxique, DoubleDeclaration {
		if (!this.uniteCourante.equals("programme"))
			throw new ErreurSyntaxique("programme attendu");
		this.uniteCourante = this.analex.next();
		if (!this.estIdf()) throw new ErreurSyntaxique("idf attendu");
		this.uniteCourante = this.analex.next();
		this.analyseBloc();
	}

	private void analyseTerminal(String terminal) throws ErreurSyntaxique {
		if (!this.uniteCourante.equals(terminal))
			throw new ErreurSyntaxique(terminal + " attendu");
		this.uniteCourante = this.analex.next();
	}

	public boolean estIdf() {
		return this.uniteCourante.matches("[a-zA-Z]+");
	}

	public Bloc analyse() throws ErreurSyntaxique, DoubleDeclaration {
		this.uniteCourante = this.analex.next();
		this.analyseProg();
		if (!this.uniteCourante.equals("EOF"))
			throw new ErreurSyntaxique("Fin de programme attendue");
		return this.bloc;
	}
	
	private void analyseBloc() throws ErreurSyntaxique, DoubleDeclaration {
		this.analyseTerminal("{");
		// Itérer sur analyseDeclaration tant qu’il y a des déclarations
		while (this.uniteCourante.equals("entier") || this.uniteCourante.equals("tableau"))
			this.analyseDeclaration();

		// bloc : liste des instructions
		this.bloc = new Bloc();
		System.out.println("################# Instructions");
		// Itérer sur analyseInstruction au moins une fois puis tant qu’il y a des instructions
		do this.bloc.ajouterInstruction(this.analyseInstruction());
		while (this.estIdf() || this.uniteCourante.equals("ecrire"));
		this.analyseTerminal("}");
	}

	private void analyseDeclaration() throws ErreurSyntaxique, DoubleDeclaration {
		Symbole s;
		if (this.uniteCourante.equals("entier")) {
			s = new SymboleEntier();
			this.uniteCourante = this.analex.next();
		} else if (this.uniteCourante.equals("tableau")) {
			this.uniteCourante = this.analex.next();

			// on attends la taille du tableau entre crochets
			analyseTerminal("[");
			if (!this.estCsteEntiere())
				throw new ErreurSyntaxique("entier attendu");
			int taille = Integer.parseInt(this.uniteCourante);
			s = new SymboleTableau(taille);
			this.uniteCourante = this.analex.next();
			analyseTerminal("]");
		} else throw new ErreurSyntaxique("déclaration d'entier ou de tableau attendue. obtenu : " + this.uniteCourante);

		// on attends maintenant le nom de la variable/du tableau
		if (!this.estIdf())
			throw new ErreurSyntaxique("idf attendu");

		// on ajoute la déclaration
		TDS.getInstance().ajouter(new Entree(this.uniteCourante), s);
		this.uniteCourante = this.analex.next();
		analyseTerminal(";");
	}

	private Instruction analyseInstruction() throws ErreurSyntaxique, DoubleDeclaration {
		Instruction i;
		if (this.uniteCourante.equals("ecrire"))
			i = this.analyseEcrire();
		else if (this.estIdf()) i = this.analyseAffectation();
		else throw new ErreurSyntaxique("instruction attendue");
		return i;
	}

	private Ecrire analyseEcrire() throws ErreurSyntaxique {
		Ecrire e;
		// on vient de lire ecrire, on peut passer à la suite
		this.uniteCourante = this.analex.next();

		if (!this.estIdf()) throw new ErreurSyntaxique("idf attendu");
		else e = new Ecrire(new Idf(this.uniteCourante));

		this.uniteCourante = this.analex.next();
		analyseTerminal(";");

		return e;
	}

	private Affectation analyseAffectation() throws ErreurSyntaxique {
		Acces acces = analyseAcces();
		analyseTerminal(":=");
		Affectation a = new Affectation(acces, this.analyseExpression());
		analyseTerminal(";");
		return a;
	}

	private Acces analyseAcces() throws ErreurSyntaxique {
		String nom = this.uniteCourante;
		Acces a;
		this.uniteCourante = this.analex.next();

		// lecture d'un accès à un tableau
		if (this.uniteCourante.equals("[")) {
			this.uniteCourante = this.analex.next();
			if (!this.estCsteEntiere())
				throw new ErreurSyntaxique("entier attendu");
			int taille = Integer.parseInt(this.uniteCourante);
			a = new AccesTableau(nom, new Nombre(taille));
			analyseTerminal("]");
		}

		return a;
	}

	private Expression analyseExpression() throws ErreurSyntaxique {
		Expression e;
		if (this.estIdf())
			e = new Idf(this.uniteCourante);
		else if (this.estCsteEntiere())
			e = new Nombre(Integer.parseInt(this.uniteCourante));
		else
			throw new ErreurSyntaxique("expression attendue");
		this.uniteCourante = this.analex.next();
		return e;
	}

	private void analyseOperande() {

	}

	private boolean estCsteEntiere() {
		return this.uniteCourante.matches("[0-9]+");
	}
}
