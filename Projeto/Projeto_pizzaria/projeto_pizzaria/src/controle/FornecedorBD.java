package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Fornecedor;

public class FornecedorBD {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	ArrayList<Fornecedor> lista = new ArrayList<>();
	
	public void cadastrarFornecedor(Fornecedor fornecedor) {
		String sql = "insert into Fornecedor (nomeFornecedor,cnpjFornecedor,telefoneFornecedor) values (?,?,?)";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getTelefone());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Fornecedor inserido!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Preencha os campos para cadastrar!");
		}
		
	}
	public ArrayList <Fornecedor> pesquisarFornecedor(){
		String sql = "select * from Fornecedor";
		
		conn = new Conexao().faz_conexao();
		
		try {
			stmt =  conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nomeFornecedor"));
				fornecedor.setCnpj(rs.getString("cnpjFornecedor"));
				fornecedor.setTelefone(rs.getString("telefoneFornecedor"));
				
				lista.add(fornecedor);
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao pesquisar -> " + e);
		}
		return lista;
	}
	
	public void alterarFornecedor(Fornecedor fornecedor) {
		String sql = "update Fornecedor set nomeFornecedor = ?, cnpjFornecedor = ?, telefoneFornecedor = ? where idFornecedor = ?";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getTelefone());
			stmt.setInt(4, fornecedor.getId());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Fornecedor alterado com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao alterar -> " + e);
		}
	}
	public void excluirFornecedor(Fornecedor fornecedor) {
		String sql = "delete from Fornecedor where idFornecedor = ?";

		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fornecedor.getId());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"fornecedor excluido com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao excluir -> " + e);
		}
	}

}