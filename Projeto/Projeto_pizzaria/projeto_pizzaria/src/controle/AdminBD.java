package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Admin;

public class AdminBD {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	ArrayList<Admin> lista = new ArrayList<>();

	public ArrayList <Admin> pesquisarAdmin(){
		String sql = "select * from admin";

		conn = new Conexao().faz_conexao();

		try {
			stmt =  conn.prepareStatement(sql);
			rs = stmt.executeQuery();


			while(rs.next()) {
				Admin adm = new Admin();
				adm.setId(rs.getInt("idFuncionario"));
				adm.setNome(rs.getString("nome"));
				adm.setSenha(rs.getString("senha"));

				lista.add(adm);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro no Banco de Dados ao pesquisar -> " + e);
		}
		return lista;
	}
}