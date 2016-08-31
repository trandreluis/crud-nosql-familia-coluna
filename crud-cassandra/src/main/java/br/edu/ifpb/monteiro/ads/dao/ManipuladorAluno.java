package br.edu.ifpb.monteiro.ads.dao;

import br.edu.ifpb.monteiro.ads.excecoes.CpfDuplicadoException;
import br.edu.ifpb.monteiro.ads.excecoes.CpfInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.CursoInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.MatriculaDuplicadaException;
import br.edu.ifpb.monteiro.ads.excecoes.MatriculaInvalidaException;
import br.edu.ifpb.monteiro.ads.excecoes.NomeInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.TelefoneInvalidoException;
import br.edu.ifpb.monteiro.ads.model.Aluno;

public class ManipuladorAluno {

	private AlunoDao dao;

	public ManipuladorAluno(AlunoDao dao) {
		this.dao = dao;
	}

	public void validar(Aluno aluno, String cpf, String matricula)
			throws MatriculaInvalidaException, NomeInvalidoException,
			CpfInvalidoException, CursoInvalidoException,
			TelefoneInvalidoException, MatriculaDuplicadaException,
			CpfDuplicadoException {
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
			if (buscarMatricula(aluno.getMatricula()) != null
					&& !buscarMatricula(aluno.getMatricula()).getMatricula()
							.equals(matricula)) {
				throw new MatriculaDuplicadaException();
			} else if (buscarCpf(aluno.getCpf()) != null
					&& !buscarCpf(aluno.getCpf()).getCpf().equals(cpf)) {
				throw new CpfDuplicadoException();
			}
		}
	}

	public Aluno buscarMatricula(String matricula) {
		for (Aluno aluno1 : dao.listar()) {
			if (aluno1.getMatricula().equals(matricula)) {
				return aluno1;
			}
		}
		return null;
	}

	public Aluno buscarCpf(String cpf) {
		for (Aluno aluno1 : dao.listar()) {
			if (aluno1.getCpf().equals(cpf)) {
				return aluno1;
			}
		}
		return null;
	}
}
