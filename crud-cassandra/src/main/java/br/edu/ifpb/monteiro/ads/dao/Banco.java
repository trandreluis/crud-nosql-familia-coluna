package br.edu.ifpb.monteiro.ads.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Banco {

	public static void main(String[] args) {

		Cluster cluster;
		Session session;

		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("cadastro");

		// session.execute("CREATE KEYSPACE cadastro WITH replication "+"= {'class:'SimpleStrategy', 'replication_factor':3};");
		session.execute("CREATE TABLE cadastro.aluno("
				+ "matricula int, curso text, nome text, cpf text, telefone text, PRIMARY KEY (matricula))");

		cluster.close();
	}
}
