package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Produto;

public class ProdutoBD {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();

	public void cadastrarProduto(Produto produto) {
		String sql = "insert into produto (nomeProduto, precoProduto, qtdProduto, idFornecedor) values (?,?,?,?)";

		conn = new Conexao().faz_conexao();

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setString(3, produto.getQuantidade());
			stmt.setString(4, produto.getFornecedor());

			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Produto cadastrado!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Preencha os dados corretamente");

		}

	}
	public ArrayList <Produto> pesquisarProduto(){
		String sql = "select idProduto, f.nomeFornecedor, nomeProduto, precoProduto, qtdProduto from Produto p inner join Fornecedor f on p.idFornecedor = f.idFornecedor order by idProduto";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("idProduto"));
				produto.setFornecedor(rs.getString("f.nomeFornecedor"));
				produto.setNome(rs.getString("nomeProduto"));
				produto.setPreco(rs.getDouble("precoProduto"));
				produto.setQuantidade(rs.getString("qtdProduto"));
				
				lista.add(produto);
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados ao listar os produtos");
		}
		return lista;
	}
	public void alterarProduto(Produto produto) {
		String sql = "update Produto set nomeProduto = ?,precoProduto = ?, qtdProduto = ? where idProduto = ?";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setString(3, produto.getQuantidade());
			stmt.setInt(4, produto.getId());
			
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Produto alterado com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao alterar -> " + e);
		}
	}
	
	public void excluirProduto(Produto produto) {
		String sql = "delete from Produto where idProduto = ?";

		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Produto excluido com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao excluir -> " + e);
		}
	}
	
	public Produto diminuirEstoque(Produto produto) {

		try {
			PreparedStatement ps = conn.prepareStatement("update Produto set qtdProduto=? where idProduto = ?");
			ps.setString(1, produto.getQuantidade());
			ps.setInt(2, produto.getId());
			ps.executeUpdate();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return null;
	}
	
	public Produto listarqtdID(Produto produto) {
		Produto quantidadeID = new Produto();
		String sql = "select qtdProduto from Produto where idProduto = ? ";
		
		conn = new Conexao().faz_conexao();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				quantidadeID.setQuantidade(rs.getString("qtdProduto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidadeID;
	}
	
	public Produto listarProdutosID(Produto produto) {
		Produto prod = new Produto();
		String sql = "select * from Produto where idProduto = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				prod.setId(rs.getInt("idProduto"));
				prod.setNome(rs.getString("nomeProduto"));
				prod.setPreco(rs.getDouble("precoProduto"));
				prod.setQuantidade(rs.getString("qtdProduto"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prod;
	}
	
	
	

}
