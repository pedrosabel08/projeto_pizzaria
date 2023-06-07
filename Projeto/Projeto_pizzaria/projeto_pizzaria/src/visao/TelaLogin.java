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
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Insets;
import java.awt.Cursor;

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
					frame.setLocationRelativeTo(null);
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
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/CasaPizza.png")));
		lblNewLabel.setBounds(323, 123, 260, 244);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 5, 5, (Color) new Color(204, 102, 0)));
		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(72, 69, 185, 298);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(41, 11, 103, 30);
		panel.add(lblNome);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.BLACK);
		
		txtNome = new JTextField();
		txtNome.setMargin(new Insets(2, 6, 2, 2));
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setBackground(Color.BLACK);
		txtNome.setForeground(Color.WHITE);
		txtNome.setBounds(21, 50, 143, 36);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(41, 119, 103, 30);
		panel.add(lblSenha);
		lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setForeground(Color.BLACK);
		
		txtSenha = new JPasswordField();
		txtSenha.setMargin(new Insets(2, 6, 2, 2));
		txtSenha.setCaretColor(Color.WHITE);
		txtSenha.setBackground(Color.BLACK);
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setBounds(21, 160, 143, 36);
		panel.add(txtSenha);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContinuar.setBounds(34, 247, 116, 30);
		panel.add(btnContinuar);
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBackground(Color.DARK_GRAY);
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
	}
}
