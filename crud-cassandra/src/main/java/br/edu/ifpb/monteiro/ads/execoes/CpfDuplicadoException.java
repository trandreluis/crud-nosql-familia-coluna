package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class CpfDuplicadoException extends Exception {

	public CpfDuplicadoException() {
		super("CPF duplicado!");
	}
	
}

