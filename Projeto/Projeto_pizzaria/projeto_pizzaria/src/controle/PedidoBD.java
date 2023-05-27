package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Pedido;

public class PedidoBD {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	ArrayList<Pedido> lista = new ArrayList<>();
	
	public ArrayList <Pedido> pesquisarPedido(){
		String sql = "select * from pedido";

		conn = new Conexao().faz_conexao();

		try {
			stmt =  conn.prepareStatement(sql);
			rs = stmt.executeQuery();


			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("idPedido"));
				pedido.setSabor(rs.getString("saborPizza"));
				pedido.setTamanho(rs.getString("tamanhoPizza"));
				pedido.setBebida(rs.getString("bebida"));
				pedido.setPreco(rs.getDouble("preco"));
				
				lista.add(pedido);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao pesquisar o Pedido -> " + e);
		}
		return lista;
	}
	
	public void cadastrarPedido(Pedido pedido) {
		String sql = "insert into Pedido (saborPizza, tamanhoPizza, bebida, preco) values (?,?,?,?)";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pedido.getSabor());
			stmt.setString(2, pedido.getTamanho());
			stmt.setString(3, pedido.getBebida());
			stmt.setDouble(4, pedido.getPreco());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Pedido inserido com sucesso!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Preencha os campos para cadastrar!");
		}
		
	}
	
	public void alterarPedido(Pedido pedido) {
		String sql = "update pedido set saborPizza = ?, tamanhoPizza = ?, bebida = ?, preco = ? where idPedido = ?";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pedido.getSabor());
			stmt.setString(2, pedido.getTamanho());
			stmt.setString(3, pedido.getBebida());
			stmt.setDouble(4, pedido.getPreco());
			stmt.setInt(5, pedido.getId());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Pedido alterado com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao alterar -> " + e);
		}
	}
	
	public void excluirPedido(Pedido pedido) {
		String sql = "delete from pedido where idPedido = ?";

		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pedido.getId());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null,"Pedido excluido com sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao excluir -> " + e);
		}
	}
	
}
