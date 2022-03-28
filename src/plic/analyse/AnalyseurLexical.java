package plic.analyse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyseurLexical {

	private Scanner sc;

	public AnalyseurLexical(File fichier) {
		try {
			this.sc = new Scanner(fichier);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String next() {
		String word = "EOF";
		if (sc.hasNext()) {
			word = sc.next();
			if (word.startsWith("//")) {
				sc.nextLine();
				word = sc.next();
			}
		}
		System.out.println(" | " + word);
		return word;
	}
}
