package br.edu.ifpb.monteiro.ads.dao;

import br.edu.ifpb.monteiro.ads.execoes.AlunoInexistenteException;
import br.edu.ifpb.monteiro.ads.model.Aluno;

public class Main {
	
	public static void main(String[] args) {
		
		AlunoDao dao = new AlunoDao(new Conexao());
		
//		Aluno aluno = new Aluno();
//		aluno.setNome("Emerson");
//		aluno.setMatricula("201425020100");
//		aluno.setCurso("ADS");
//		aluno.setCpf("113.210.544-75");
//		aluno.setTelefone("(87) 99134-9106");
//		
//		dao.adicionar(aluno);
		try {
			for (Aluno aluno : dao.listar()) {
				System.out.println(aluno.toString());
			}
		} catch (AlunoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.desconectar();
	}

}
