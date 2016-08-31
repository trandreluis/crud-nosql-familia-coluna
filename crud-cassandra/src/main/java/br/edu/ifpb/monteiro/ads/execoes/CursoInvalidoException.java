package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class CursoInvalidoException extends Exception{
	
	public CursoInvalidoException() {
		super("Curso inválido!");
	}
}