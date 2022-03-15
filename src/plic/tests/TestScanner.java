package plic.tests;

import java.util.Objects;
import java.io.File;
import plic.analyse.*;
import plic.exception.ErreurSyntaxique;

public class TestScanner {

	public static File source = new File("D:\\Documents\\DEVELOPMENT\\plic\\compilateur\\src\\plic\\sources\\prog1.plic");

	public static void main(String[] args) throws ErreurSyntaxique {
		AnalyseurLexical analyseur = new AnalyseurLexical(source);
		while (!Objects.equals(analyseur.next(), "EOF")) {
			analyseur.next();
		}
	}
}