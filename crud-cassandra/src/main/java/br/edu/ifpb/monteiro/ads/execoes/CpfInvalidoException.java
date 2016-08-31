package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception {

	public CpfInvalidoException() {
		super("CPF inválido!");
	}
}

