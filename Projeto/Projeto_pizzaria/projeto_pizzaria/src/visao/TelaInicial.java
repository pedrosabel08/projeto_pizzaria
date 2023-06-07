package visao;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/imagens/pizzaria.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1500, 1200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 5, 5, (Color) new Color(204, 102, 0)));
		panel.setBackground(new Color(255, 204, 102));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(TelaInicial.class.getResource("/imagens/pizza.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 5, 5, (Color) new Color(204, 102, 0)));
		panel_1.setBackground(new Color(255, 204, 102));
		
		JButton btnNewButton = new JButton("Trocar de Conta");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarTrocarConta();
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
					.addGap(220))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1485, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addContainerGap(1391, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(33)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(134)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)))
					.addGap(53))
		);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tela Inicial");
		lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_1.setBounds(797, 25, 402, 54);
		panel_1.add(lblNewLabel_1);
		
		JButton btnFuncionario = new JButton("Funcionario");
		btnFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFuncionario.setForeground(new Color(255, 255, 255));
		btnFuncionario.setBackground(Color.DARK_GRAY);
		btnFuncionario.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFuncionario frame = new TelaFuncionario();
				frame.setVisible(true);
				dispose();
			}
		});
		
		JButton btnFornecedor = new JButton("Fornecedor");
		btnFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFornecedor frame = new TelaFornecedor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnFornecedor.setForeground(Color.WHITE);
		btnFornecedor.setBackground(Color.DARK_GRAY);
		btnFornecedor.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				TelaCliente frame = new TelaCliente();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCliente.setForeground(Color.WHITE);
		btnCliente.setBackground(Color.DARK_GRAY);
		btnCliente.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProduto frame = new TelaProduto();
				frame.setVisible(true);
				dispose();
			}
		});
		btnProduto.setForeground(Color.WHITE);
		btnProduto.setBackground(Color.DARK_GRAY);
		btnProduto.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda frame = new TelaVenda();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVenda.setForeground(Color.WHITE);
		btnVenda.setBackground(Color.DARK_GRAY);
		btnVenda.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedido frame = new TelaPedido();
				frame.setVisible(true);
				dispose();
			}
		});
		btnPedido.setForeground(Color.WHITE);
		btnPedido.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnPedido.setBackground(Color.DARK_GRAY);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarFecharSistema();
			}
			
		});
		btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnFechar.setBackground(Color.DARK_GRAY);
		btnFechar.setForeground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnFechar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnVenda, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnPedido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnProduto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnFornecedor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnFuncionario, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
					.addGap(50))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(btnFuncionario, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(btnFornecedor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(btnCliente, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(btnProduto, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addComponent(btnPedido, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addComponent(btnVenda, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);}
		
	    private void confirmarFecharSistema() {
	        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente fechar o sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
	        if (opcao == JOptionPane.YES_OPTION) {
	            dispose();
	            System.exit(0);
	        }
	    }
	    private void confirmarTrocarConta() {
	        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente trocar de conta?", "Confirmação", JOptionPane.YES_NO_OPTION);
	        if (opcao == JOptionPane.YES_OPTION) {
	        	dispose();
	            TelaLogin frame = new TelaLogin();
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	        }
	    }
	}

