package br.edu.ifpb.monteiro.ads.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.monteiro.ads.dao.AlunoDao;
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
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteCancelar;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteFocus;

@SuppressWarnings("serial")
public class PainelAddAluno extends JPanel {

	private JTextField areaCpf, areaNome, areaTelefone, areaMatricula,
			areaCurso;
	private String cpfAntigo, matriculaAntiga;
	private Container c;
	private JFrame janela;
	private JPanel painel;
	private OuvinteFocus ouvinteFocus;
	private AlunoDao dao;
	private String funcao;
	private Aluno aluno;
	private JButton btAdd;
	private JLabel titulo;

	public PainelAddAluno(JFrame janela, AlunoDao dao, Aluno aluno) {

		this.janela = janela;

		this.aluno = aluno;

		this.dao = dao;

		this.janela.setSize(800, 600);

		this.janela.setLocationRelativeTo(null);

		this.janela.setResizable(false);

		this.janela.setLayout(new BorderLayout());

		this.painel = new JPanel();

		janela.setTitle("Adicionar Aluno");

		janela.setIconImage(new ImageIcon(getClass().getResource(
				"/images/add.png")).getImage());

		painel.setSize(800, 600);

		c = janela.getContentPane();

		painel.setLayout(null);

		adicionarJLabels();

		adicionarJButtons();

		adicionarJTextFields();

		c.add(painel);

		setarCampos();

		setVisible(true);
	}

	private void adicionarJTextFields() {
		try {
			areaCpf = new JFormattedTextField(new MaskFormatter(
					"###.###.###-##"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Preencha de acordo com o formato ###.###.###-##", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		ouvinteFocus = new OuvinteFocus(areaCpf);
		areaCpf.addFocusListener(ouvinteFocus);
		areaCpf.setBounds(355, 240, 330, 25);
		areaCpf.setFont(new Font("Times New Roman", 0, 20));
		areaCpf.setToolTipText("Exemplo: 111.222.333-44");
		painel.add(areaCpf);

		try {
			areaMatricula = new JFormattedTextField(new MaskFormatter(
					"############"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Preencha de acordo com o formato ############", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		ouvinteFocus = new OuvinteFocus(areaMatricula);
		areaMatricula.addFocusListener(ouvinteFocus);
		areaMatricula.setBounds(400, 160, 285, 25);
		areaMatricula.setFont(new Font("Times New Roman", 0, 20));
		areaMatricula.setToolTipText("Exemplo: 111.222.333-44");
		painel.add(areaMatricula);

		areaNome = new JTextField();
		ouvinteFocus = new OuvinteFocus(areaNome);
		areaNome.addFocusListener(ouvinteFocus);
		areaNome.setBounds(365, 200, 320, 25);
		areaNome.setFont(new Font("Times New Roman", 0, 20));
		areaNome.setToolTipText("Exemplo: Astrogildo");
		painel.add(areaNome);

		areaCurso = new JTextField();
		ouvinteFocus = new OuvinteFocus(areaCurso);
		areaCurso.addFocusListener(ouvinteFocus);
		areaCurso.setBounds(365, 280, 320, 25);
		areaCurso.setFont(new Font("Times New Roman", 0, 20));
		areaCurso.setToolTipText("Exemplo: Astrogildo");
		painel.add(areaCurso);

		try {
			areaTelefone = new JFormattedTextField(new MaskFormatter(
					"(##) #####-####"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Preencha de acordo com o formato (##) #####-####", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		ouvinteFocus = new OuvinteFocus(areaTelefone);
		areaTelefone.addFocusListener(ouvinteFocus);
		areaTelefone.setBounds(390, 320, 295, 25);
		areaTelefone.setFont(new Font("Times New Roman", 0, 20));
		areaTelefone.setToolTipText("Exemplo: (87) 99999-9999");
		painel.add(areaTelefone);
	}

	private void adicionarJButtons() {
		btAdd = new JButton("Adicionar");
		OuvinteAddAluno ouvintePessoa = new OuvinteAddAluno(janela);
		btAdd.addActionListener(ouvintePessoa);
		btAdd.setIcon(new ImageIcon(getClass().getResource("/images/ok.png")));
		btAdd.setBounds(565, 380, 120, 30);
		painel.add(btAdd);
		JButton btCancel = new JButton("Cancelar");
		OuvinteCancelar ouvinteCancelar = new OuvinteCancelar(janela, dao);
		btCancel.addActionListener(ouvinteCancelar);
		btCancel.setIcon(new ImageIcon(getClass().getResource(
				"/images/cancelar.png")));
		btCancel.setBounds(100, 380, 120, 30);
		painel.add(btCancel);
	}

	private void adicionarJLabels() {
		titulo = new JLabel("Adicionar Aluno");
		titulo.setFont(new Font("Times New Roman", 0, 40));
		titulo.setBounds(270, 40, 270, 50);
		painel.add(titulo);

		JLabel matricula = new JLabel("Informe a matricula do aluno:*");
		matricula.setFont(new Font("Times New Roman", 0, 24));
		matricula.setBounds(100, 160, 300, 30);
		painel.add(matricula);

		JLabel nome = new JLabel("Informe o nome do aluno:*");
		nome.setFont(new Font("Times New Roman", 0, 24));
		nome.setBounds(100, 200, 265, 30);
		painel.add(nome);

		JLabel cpf = new JLabel("Informe o CPF do aluno:*");
		cpf.setFont(new Font("Times New Roman", 0, 24));
		cpf.setBounds(100, 240, 255, 30);
		painel.add(cpf);

		JLabel curso = new JLabel("Informe o curso do aluno:*");
		curso.setFont(new Font("Times New Roman", 0, 24));
		curso.setBounds(100, 280, 260, 30);
		painel.add(curso);

		JLabel telefone = new JLabel("Informe o telefone do aluno:*");
		telefone.setFont(new Font("Times New Roman", 0, 24));
		telefone.setBounds(100, 320, 290, 30);
		painel.add(telefone);
	}

	public void setarCampos() {
		if (aluno != null) {
			btAdd.setText("Editar");
			titulo.setText("Editar Aluno");
			titulo.setBounds(290, 40, 270, 50);
			areaCpf.setText(aluno.getCpf());
			areaCurso.setText(aluno.getCurso());
			areaMatricula.setText(aluno.getMatricula());
			areaMatricula.setEditable(false);
			areaNome.setText(aluno.getNome());
			areaTelefone.setText(aluno.getTelefone());
			btAdd.setIcon(new ImageIcon(getClass().getResource(
					"/images/edit.png")));
			janela.setIconImage(new ImageIcon(getClass().getResource(
					"/images/edit.png")).getImage());
			janela.setTitle("Editar Aluno");
			funcao = "editado";
			cpfAntigo = aluno.getCpf();
			matriculaAntiga = aluno.getMatricula();
		} else {
			funcao = "adicionado";
		}
	}

	private class OuvinteAddAluno implements ActionListener {

		JFrame janela;

		public OuvinteAddAluno(JFrame janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {

			try {
				if (aluno != null) {
					try {
						Aluno aluno1 = dao.consultar(aluno.getMatricula());
						aluno1.setNome(areaNome.getText().toString());
						aluno1.setTelefone(areaTelefone.getText().toString());
						aluno1.setCpf(areaCpf.getText().toString());
						aluno1.setCurso(areaCurso.getText().toString());
						dao.alterar(aluno1, matriculaAntiga, cpfAntigo);
						JOptionPane.showMessageDialog(null, "Aluno " + funcao
								+ " com sucesso!");
						new PainelTabelas(janela, dao);
					} catch (AlunoInexistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Aluno aluno = new Aluno();
					aluno.setNome(areaNome.getText().toString());
					aluno.setTelefone(areaTelefone.getText().toString());
					aluno.setCpf(areaCpf.getText().toString());
					aluno.setCurso(areaCurso.getText().toString());
					aluno.setMatricula(areaMatricula.getText().toString());
					dao.adicionar(aluno);
					JOptionPane.showMessageDialog(null, "Aluno " + funcao
							+ " com sucesso!");
					new PainelTabelas(janela, dao);
				}
			} catch (MatriculaInvalidaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaMatricula.setText("");
			} catch (MatriculaDuplicadaException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaMatricula.setText("");
			} catch (NomeInvalidoException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaNome.setText("");
			} catch (CursoInvalidoException e4) {
				JOptionPane.showMessageDialog(null, e4.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaCurso.setText("");
			} catch (TelefoneInvalidoException e5) {
				JOptionPane.showMessageDialog(null, e5.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaTelefone.setText("");
			} catch (CpfInvalidoException e6) {
				JOptionPane.showMessageDialog(null, e6.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaCpf.setText("");
			} catch (CpfDuplicadoException e7) {
				JOptionPane.showMessageDialog(null, e7.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				areaCpf.setText("");
			} catch (DadoInvalidoException e8) {
				JOptionPane.showMessageDialog(null, e8.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (AlunoInexistenteException e8) {
				JOptionPane.showMessageDialog(null, e8.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}