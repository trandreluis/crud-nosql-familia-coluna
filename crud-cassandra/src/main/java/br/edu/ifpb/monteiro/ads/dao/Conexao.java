package br.edu.ifpb.monteiro.ads.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Conexao {

	private Cluster cluster;
	private Session session;
	
	public Session connect(){
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("cadastro");
		return session;
	}
	
	public void close(){
		this.cluster.close();
	}
}
