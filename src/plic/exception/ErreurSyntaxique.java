package plic.exception;

public class ErreurSyntaxique extends Exception {

	public ErreurSyntaxique(String err) {
		super("erreurSyntaxique"+err);
	}
}
