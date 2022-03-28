package plic.repint;

public class AccesTableau extends Acces {
	public final String nom;
	public final Nombre taille;
	public AccesTableau(String n, Nombre t) {
		this.nom = n;
		this.taille = t;
	}
}
