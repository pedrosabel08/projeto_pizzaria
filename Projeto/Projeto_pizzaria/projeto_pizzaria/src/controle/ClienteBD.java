package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Cliente;

public class ClienteBD {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	ArrayList<Cliente> lista = new ArrayList<>();
	
	public ArrayList <Cliente> pesquisarCliente(){
		String sql = "select * from Cliente";

		conn = new Conexao().faz_conexao();

		try {
			stmt =  conn.prepareStatement(sql);
			rs = stmt.executeQuery();


			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
				cliente.setRua(rs.getString("rua"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setNumero(rs.getInt("numero"));
				cliente.setReferencia(rs.getString("referencia"));

				lista.add(cliente);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao pesquisar o Cliente -> " + e);
		}
		return lista;
	}
	
	public void cadastrarCliente(Cliente cliente) {
		String sql = "insert into Cliente (nomeCliente, telefoneCliente, rua, bairro, numero, referencia) values (?,?,?,?,?,?)";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getNomeCliente());
			stmt.setString(2, cliente.getTelefoneCliente());
			stmt.setString(3, cliente.getRua());
			stmt.setString(4, cliente.getBairro());
			stmt.setInt(5, cliente.getNumero());
			stmt.setString(6, cliente.getReferencia());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Preencha os campos para cadastrar!");
		}
		
	}
	
	public void alterarCliente(Cliente cliente) {
		String sql = "update Cliente set nomeCliente = ?, telefoneCliente = ?, rua = ?, bairro = ?, numero = ?, referencia = ? where idCliente = ?";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getNomeCliente());
			stmt.setString(2, cliente.getTelefoneCliente());
			stmt.setString(3, cliente.getRua());
			stmt.setString(4, cliente.getBairro());
			stmt.setInt(5, cliente.getNumero());
			stmt.setString(6, cliente.getReferencia());
			stmt.setInt(7, cliente.getIdCliente());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Cliente alterado com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao alterar -> " + e);
		}
	}
	
	public void excluirCliente(Cliente cliente) {
		String sql = "delete from Cliente where idCliente = ?";

		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdCliente());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Cliente excluido com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao excluir -> " + e);
		}
	}
	
}
