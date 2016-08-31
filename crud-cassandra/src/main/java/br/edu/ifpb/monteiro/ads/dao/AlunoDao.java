package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;

import br.edu.ifpb.monteiro.ads.excecoes.AlunoInexistenteException;
import br.edu.ifpb.monteiro.ads.excecoes.CpfDuplicadoException;
import br.edu.ifpb.monteiro.ads.excecoes.CpfInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.CursoInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.MatriculaDuplicadaException;
import br.edu.ifpb.monteiro.ads.excecoes.MatriculaInvalidaException;
import br.edu.ifpb.monteiro.ads.excecoes.NomeInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.TelefoneInvalidoException;
import br.edu.ifpb.monteiro.ads.model.Aluno;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class AlunoDao {

	private Conexao conexao;
	private Session session;
	private ManipuladorAluno manipulador = new ManipuladorAluno(this);

	public AlunoDao(Conexao conexao) {
		this.conexao = conexao;
		this.session = conexao.connect();
	}

	public void adicionar(Aluno aluno) throws MatriculaInvalidaException,
			MatriculaDuplicadaException, NomeInvalidoException,
			CursoInvalidoException, TelefoneInvalidoException,
			CpfDuplicadoException, CpfInvalidoException, DadoInvalidoException {
		if ((aluno.getCpf() == null || aluno.getCpf().equals("   .   .   -  "))
				&& (aluno.getNome() == null || aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() == null || aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() == null || aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() == null || aluno.getMatricula()
						.equals("            "))) {
			throw new DadoInvalidoException();
		} else {
			manipulador = new ManipuladorAluno(this);
			manipulador.validar(aluno, null, null);

			session.execute("INSERT INTO cadastro.aluno (matricula, curso, nome, cpf, telefone)"
					+ "VALUES "
					+ "( '"
					+ aluno.getMatricula()
					+ "', '"
					+ aluno.getCurso()
					+ "', '"
					+ aluno.getNome()
					+ "', '"
					+ aluno.getCpf() + "', '" + aluno.getTelefone() + "')");
		}
	}

	public void deletar(Aluno aluno) throws AlunoInexistenteException {
		if (manipulador.buscarMatricula(aluno.getMatricula()) == null) {
			throw new AlunoInexistenteException();
		} else {
			session.execute("DELETE FROM cadastro.aluno WHERE matricula='"
					+ aluno.getMatricula() + "';");
		}
	}

	public void alterar(Aluno aluno, String matricula, String cpf)
			throws MatriculaDuplicadaException, MatriculaInvalidaException,
			NomeInvalidoException, CursoInvalidoException,
			TelefoneInvalidoException, DadoInvalidoException,
			CpfInvalidoException, CpfDuplicadoException {
		if ((aluno.getCpf() == null || aluno.getCpf().equals("   .   .   -  "))
				&& (aluno.getNome() == null || aluno.getNome().trim()
						.equals(""))
				&& (aluno.getCurso() == null || aluno.getCurso().trim()
						.equals(""))
				&& (aluno.getTelefone() == null || aluno.getTelefone().equals(
						"(  )      -    "))
				&& (aluno.getMatricula() == null || aluno.getMatricula()
						.equals("            "))) {
			throw new DadoInvalidoException();
		} else {
			manipulador.validar(aluno, cpf, matricula);
			session.execute("UPDATE cadastro.aluno SET nome='"
					+ aluno.getNome() + "', cpf='" + aluno.getCpf()
					+ "', curso='" + aluno.getCurso() + "', telefone='"
					+ aluno.getTelefone() + "' WHERE matricula='"
					+ aluno.getMatricula() + "';");
		}
	}

	public Aluno consultar(String matricula) throws AlunoInexistenteException {
		if (manipulador.buscarMatricula(matricula) != null && manipulador.buscarMatricula(matricula).getMatricula().equals(matricula)) {
			ResultSet results = session
					.execute("select * from cadastro.aluno where matricula='"
							+ matricula + "';");
			String nome = "";
			String matri = "";
			String curso = "";
			String cpf = "";
			String telefone = "";
			for (Row row : results) {
				nome = row.getString("nome");
				matri = row.getString("matricula");
				curso = row.getString("curso");
				cpf = row.getString("cpf");
				telefone = row.getString("telefone");
			}
			Aluno aluno = new Aluno();
			aluno.setNome(nome);
			aluno.setMatricula(matri);
			aluno.setCurso(curso);
			aluno.setCpf(cpf);
			aluno.setTelefone(telefone);

			return aluno;
		} else{
			throw new AlunoInexistenteException();
		}
	}

	public ArrayList<Aluno> listar() {
		ResultSet results = session.execute("select * from cadastro.aluno;");
		String nome = "";
		String matri = "";
		String curso = "";
		String cpf = "";
		String telefone = "";
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		for (Row row : results) {
			nome = row.getString("nome");
			matri = row.getString("matricula");
			curso = row.getString("curso");
			cpf = row.getString("cpf");
			telefone = row.getString("telefone");
			Aluno aluno = new Aluno();
			aluno.setNome(nome);
			aluno.setMatricula(matri);
			aluno.setCurso(curso);
			aluno.setCpf(cpf);
			aluno.setTelefone(telefone);
			alunos.add(aluno);
		}
		return alunos;
	}

	public void desconectar() {
		conexao.close();
	}
}
