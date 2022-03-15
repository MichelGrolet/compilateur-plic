package plic.analyse;

import plic.exception.*;
import plic.repint.Entree;
import plic.repint.Symbole;
import plic.repint.TDS;

import java.io.File;

public class AnalyseurSyntaxique {
	private final AnalyseurLexical analex;
	private String uniteCourante;

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

	public void analyse() throws ErreurSyntaxique, DoubleDeclaration {
		this.uniteCourante = this.analex.next();
		this.analyseProg();
		if (!this.uniteCourante.equals("EOF"))
			throw new ErreurSyntaxique("Fin de programme attendue");
	}
	
	private void analyseBloc() throws ErreurSyntaxique, DoubleDeclaration {
		this.analyseTerminal("{");
		// Itérer sur analyseDeclaration tant qu’il y a des déclarations
		while (this.uniteCourante.equals("entier")) {
			System.out.println("declaration " + this.uniteCourante);
			this.analyseDeclaration();
		}
		System.out.println("loop instructions");

		this.analyseInstruction();
		// Itérer sur analyseInstruction tant qu’il y a des instructions
		while (this.estIdf() || this.uniteCourante.equals("ecrire"))
			this.analyseInstruction();
		this.analyseTerminal("}");
	}

	private void analyseDeclaration() throws ErreurSyntaxique, DoubleDeclaration {
		this.uniteCourante = this.analex.next();
		if (!this.estIdf()) throw new ErreurSyntaxique("idf attendu dans une déclaration");
		else {
			TDS tds = TDS.getInstance();
			tds.ajouter(new Entree("entier"), new Symbole(this.uniteCourante));
			this.uniteCourante = this.analex.next();
			if (!this.uniteCourante.equals(";")) throw new ErreurSyntaxique("; attendu");
			this.uniteCourante = this.analex.next();
		}
	}

	private void analyseInstruction() throws ErreurSyntaxique, DoubleDeclaration {
		System.out.println("ANALYSE INSTRUCTION " + this.uniteCourante);
		if (this.uniteCourante.equals("ecrire"))
			this.analyseEcrire();
		else this.analyseAffectation();
	}

	private void analyseEcrire() throws ErreurSyntaxique {
		this.uniteCourante = this.analex.next();
		if (!this.uniteCourante.matches("[a-zA-Z]+"))
			throw new ErreurSyntaxique("idf attendu pour écrire");
		this.uniteCourante = this.analex.next();
		if (!this.uniteCourante.equals(";"))
			throw new ErreurSyntaxique("; attendu");
		this.uniteCourante = this.analex.next();
	}

	private void analyseAffectation() throws ErreurSyntaxique {
		this.uniteCourante = this.analex.next();
		if (!this.uniteCourante.equals(":="))
			throw new ErreurSyntaxique("idf attendu dans une affectation");
		analyseExpression();
	}

	private void analyseExpression() throws ErreurSyntaxique {
		this.uniteCourante = this.analex.next();
		if (!this.uniteCourante.matches("[0-9]+"))
			throw new ErreurSyntaxique("entier attendu");
		this.uniteCourante = this.analex.next();
		if (!this.uniteCourante.equals(";"))
			throw new ErreurSyntaxique("; attendu");
		this.uniteCourante = this.analex.next();
	}
}
