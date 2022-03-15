package plic.exception;

public class DoubleDeclaration extends Exception {

	public DoubleDeclaration(String err) {
		super("ERREUR: " + err);
	}
}
