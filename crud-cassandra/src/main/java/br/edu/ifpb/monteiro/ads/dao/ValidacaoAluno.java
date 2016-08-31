package br.edu.ifpb.monteiro.ads.dao;

import br.edu.ifpb.monteiro.ads.execoes.AlunoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.CpfDuplicadoException;
import br.edu.ifpb.monteiro.ads.execoes.CpfInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.CursoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.MatriculaDuplicadaException;
import br.edu.ifpb.monteiro.ads.execoes.MatriculaInvalidaException;
import br.edu.ifpb.monteiro.ads.execoes.NomeInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.TelefoneInvalidoException;
import br.edu.ifpb.monteiro.ads.model.Aluno;

public class ValidacaoAluno {
	
	private AlunoDao dao;
	
	public ValidacaoAluno(AlunoDao dao) {
		this.dao = dao;
	}

	public void validar(Aluno aluno, String cpf, String matricula)
			throws MatriculaInvalidaException, NomeInvalidoException,
			CpfInvalidoException, CursoInvalidoException,
			TelefoneInvalidoException, AlunoInexistenteException,
			MatriculaDuplicadaException, CpfDuplicadoException {
		if ((aluno.getCpf() != null || !aluno.getCpf().equals("   .   .   -  "))
				&& (aluno.getNome() != null || !aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() != null || !aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() != null || !aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() == null || aluno.getMatricula()
						.equals("            "))) {
			throw new MatriculaInvalidaException();
		} else if ((aluno.getCpf() != null || !aluno.getCpf().equals(
				"   .   .   -  "))
				&& (aluno.getNome() == null || aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() != null || !aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() != null || !aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() != null || !aluno.getMatricula()
						.equals("            "))) {
			throw new NomeInvalidoException();
		} else if ((aluno.getCpf() == null || aluno.getCpf().equals(
				"   .   .   -  "))
				&& (aluno.getNome() != null || !aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() != null || !aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() != null || !aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() != null || !aluno.getMatricula()
						.equals("            "))) {
			throw new CpfInvalidoException();
		} else if ((aluno.getCpf() != null || !aluno.getCpf().equals(
				"   .   .   -  "))
				&& (aluno.getNome() != null || !aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() == null || aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() != null || !aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() != null || !aluno.getMatricula()
						.equals("            "))) {
			throw new CursoInvalidoException();
		} else if ((aluno.getCpf() != null || !aluno.getCpf().equals(
				"   .   .   -  "))
				&& (aluno.getNome() != null || !aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() != null || !aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() == null || aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() != null || !aluno.getMatricula()
						.equals("            "))) {
			throw new TelefoneInvalidoException();
		}

		else {
			if (buscarMatricula(aluno.getMatricula()) != null && !buscarMatricula(aluno.getMatricula()).getMatricula().equals(matricula)) {
				throw new MatriculaDuplicadaException();
			} else if (buscarCpf(aluno.getCpf()) != null && !buscarCpf(aluno.getCpf()).getCpf().equals(cpf)) {
				throw new CpfDuplicadoException();
			}
		}
	}
	
	public Aluno buscarMatricula(String matricula) throws AlunoInexistenteException{
		for (Aluno aluno1 : dao.listar()) {
			if(aluno1.getMatricula().equals(matricula)){
				return aluno1;
			}
		}
		return null;
	}
	
	public Aluno buscarCpf(String cpf) throws AlunoInexistenteException{
		for (Aluno aluno1 : dao.listar()) {
			if(aluno1.getCpf().equals(cpf)){
				return aluno1;
			}
		}
		return null;
	}
}
