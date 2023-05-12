package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.*;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(90, 88, 103, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(90, 207, 103, 30);
		contentPane.add(lblSenha);
		
		txtNome = new JTextField();
		txtNome.setBounds(90, 129, 143, 44);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(90, 248, 143, 36);
		contentPane.add(txtSenha);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select * from admin where nome = ? and senha = ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtNome.getText());
					stmt.setString(2, new String(txtSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						
						TelaInicial exibir = new TelaInicial();
						exibir.setLocationRelativeTo(null);
						exibir.setVisible(true);
						
						setVisible(false);
						
						JOptionPane.showMessageDialog(null, "Bem-Vindo!");
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Esse usuario nao existe");
					}
					
					stmt.close();
					con.close();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				
			
			}
		});
		btnContinuar.setBounds(347, 184, 89, 23);
		contentPane.add(btnContinuar);
	}
}
