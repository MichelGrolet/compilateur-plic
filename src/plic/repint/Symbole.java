package plic.repint;

public class Symbole {
	private String type;
	private int deplacement;

	public Symbole(String type) {
		this.type = type;
	}

	/*public int deplacement() {

	}*/

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDepl(int cptDepl) {
		this.deplacement = cptDepl;
	}

	public int getDepl() {
		return deplacement;
	}

	@Override
	public String toString() {
		return type+" "+deplacement;
	}
}
