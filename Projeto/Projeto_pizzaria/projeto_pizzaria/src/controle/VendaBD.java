package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Venda;


public class VendaBD {
	Connection conn;
	PreparedStatement stmt;
    ResultSet rs;
	ArrayList<Venda> listarVenda = new ArrayList<>();
	

	
	public ArrayList<Venda> buscarVenda() {

		String sql = "select idVenda, valorVenda, dataVenda, c.nomeCliente, idPedido from venda v inner join cliente c on v.idCliente = c.idCliente";
	    
		conn = new Conexao().faz_conexao();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next() ){
				Venda venda = new Venda();
			    venda.setId(rs.getInt("idVenda"));
			    venda.setValor(rs.getDouble("valorVenda"));
				venda.setData(rs.getString("dataVenda"));
			    venda.setNome(rs.getString("c.nomeCliente"));
			    venda.setPedido(rs.getInt("idPedido"));
		    	
			    listarVenda.add(venda);
	     }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    return listarVenda;
	}
	
	public void inserirVenda(Venda v) {

		String sql = "insert into Venda (valorVenda, dataVenda, idCliente, idPedido) values(?,?,?,?)";
		
		conn = new Conexao().faz_conexao();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, v.getValor());
			stmt.setString(2, v.getData());
			stmt.setInt(3, v.getCliente());
			stmt.setInt(4, v.getPedido());
			
			stmt.executeUpdate();
		
			}catch(SQLException e1) {
				
				e1.printStackTrace();
			}
}
}