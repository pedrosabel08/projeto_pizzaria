package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Venda;
import controle.PedidoBD;
import controle.VendaBD;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Insets;

public class TelaVenda extends JFrame {

	private JPanel contentPane;
	private JTable tabelaVenda;
	private JTextField txtIdPedido;
	private JTextField txtSabor;
	private JTextField txtTamanho;
	private JTextField txtBebida;
	private JTextField txtPreco;
	private JTextField txtIdCliente;
	private JTextField txtNome;

	private Pedido pedidoSelecionado;
	private Cliente clienteSelecionado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVenda frame = new TelaVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setPedidoSelecionado(Pedido pedido) {
		this.pedidoSelecionado = pedido;
		this.txtIdPedido.setText(String.valueOf(pedido.getId()));
		this.txtSabor.setText(pedido.getSabor());
		this.txtTamanho.setText(pedido.getTamanho());
		this.txtBebida.setText(pedido.getBebida());
		this.txtPreco.setText(String.valueOf(pedido.getPreco()));
		
	}
	public void setClienteSelecionado(Cliente cliente) {
		this.clienteSelecionado = cliente;
		this.txtIdCliente.setText(String.valueOf(cliente.getIdCliente()));
		this.txtNome.setText(cliente.getNomeCliente());
	}
	
	
	/**
	 * Create the frame.
	 */
	public TelaVenda() {
		
		TelaVenda tv = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 2000, 1800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(742, 254, 747, 553);
		contentPane.add(scrollPane);

		tabelaVenda = new JTable();
		tabelaVenda.setBorder(null);
		tabelaVenda.setBackground(Color.DARK_GRAY);
		tabelaVenda.setForeground(Color.WHITE);
		tabelaVenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero do Pedido", "Sabor", "Cliente", "Valor (R$)"
			}
		);
		tabelaVenda.setModel(model);
		scrollPane.setViewportView(tabelaVenda);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID:");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(166, 334, 46, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtIdPedido = new JTextField();
		txtIdPedido.setMargin(new Insets(2, 6, 2, 2));
		txtIdPedido.setCaretColor(Color.WHITE);
		txtIdPedido.setForeground(Color.WHITE);
		txtIdPedido.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdPedido.setEditable(false);
		txtIdPedido.setColumns(10);
		txtIdPedido.setBackground(Color.DARK_GRAY);
		txtIdPedido.setBounds(199, 331, 46, 25);
		contentPane.add(txtIdPedido);
		
		JButton btnPesquisarPedido = new JButton("Pesquisar");
		btnPesquisarPedido.setForeground(Color.WHITE);
		btnPesquisarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaPedido TP = new TabelaPedido(tv);
				TP.setVisible(true);
			}
		});
		btnPesquisarPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisarPedido.setBackground(Color.DARK_GRAY);
		btnPesquisarPedido.setBounds(285, 323, 157, 37);
		contentPane.add(btnPesquisarPedido);
		
		JLabel lblNewLabel_2_2 = new JLabel("Sabor:");
		lblNewLabel_2_2.setForeground(Color.BLACK);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(166, 389, 60, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txtSabor = new JTextField();
		txtSabor.setMargin(new Insets(2, 6, 2, 2));
		txtSabor.setCaretColor(Color.WHITE);
		txtSabor.setForeground(Color.WHITE);
		txtSabor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSabor.setEditable(false);
		txtSabor.setColumns(10);
		txtSabor.setBackground(Color.DARK_GRAY);
		txtSabor.setBounds(222, 386, 220, 25);
		contentPane.add(txtSabor);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Tamanho:");
		lblNewLabel_2_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(166, 427, 102, 23);
		contentPane.add(lblNewLabel_2_2_1);
		
		txtTamanho = new JTextField();
		txtTamanho.setMargin(new Insets(2, 6, 2, 2));
		txtTamanho.setCaretColor(Color.WHITE);
		txtTamanho.setForeground(Color.WHITE);
		txtTamanho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTamanho.setEditable(false);
		txtTamanho.setColumns(10);
		txtTamanho.setBackground(Color.DARK_GRAY);
		txtTamanho.setBounds(254, 428, 188, 25);
		contentPane.add(txtTamanho);
		
		txtBebida = new JTextField();
		txtBebida.setMargin(new Insets(2, 6, 2, 2));
		txtBebida.setCaretColor(Color.WHITE);
		txtBebida.setForeground(Color.WHITE);
		txtBebida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBebida.setEditable(false);
		txtBebida.setColumns(10);
		txtBebida.setBackground(Color.DARK_GRAY);
		txtBebida.setBounds(255, 469, 187, 25);
		contentPane.add(txtBebida);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Bebida:");
		lblNewLabel_2_2_1_1.setForeground(Color.BLACK);
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_1.setBounds(167, 468, 102, 23);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		txtPreco = new JTextField();
		txtPreco.setMargin(new Insets(2, 6, 2, 2));
		txtPreco.setCaretColor(Color.WHITE);
		txtPreco.setForeground(Color.WHITE);
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPreco.setEditable(false);
		txtPreco.setColumns(10);
		txtPreco.setBackground(Color.DARK_GRAY);
		txtPreco.setBounds(255, 513, 187, 25);
		contentPane.add(txtPreco);
		
		JLabel lblNewLabel_2_2_1_2 = new JLabel("Preco:");
		lblNewLabel_2_2_1_2.setForeground(Color.BLACK);
		lblNewLabel_2_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_2.setBounds(167, 512, 102, 23);
		contentPane.add(lblNewLabel_2_2_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(166, 691, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setForeground(Color.WHITE);
		txtIdCliente.setCaretColor(Color.WHITE);
		txtIdCliente.setMargin(new Insets(2, 6, 2, 2));
		txtIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdCliente.setEditable(false);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBackground(Color.DARK_GRAY);
		txtIdCliente.setBounds(199, 688, 46, 25);
		contentPane.add(txtIdCliente);
		
		JButton btnPesquisarCliente = new JButton("Pesquisar");
		btnPesquisarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaCliente TC = new TabelaCliente(tv);
				TC.setVisible(true);
			}
		});
		btnPesquisarCliente.setForeground(Color.WHITE);
		btnPesquisarCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisarCliente.setBackground(Color.DARK_GRAY);
		btnPesquisarCliente.setBounds(285, 680, 157, 37);
		contentPane.add(btnPesquisarCliente);
		
		txtNome = new JTextField();
		txtNome.setForeground(Color.WHITE);
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setMargin(new Insets(2, 6, 2, 2));
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		txtNome.setBackground(Color.DARK_GRAY);
		txtNome.setBounds(228, 730, 214, 25);
		contentPane.add(txtNome);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nome:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(166, 733, 79, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setBackground(Color.DARK_GRAY);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (pedidoSelecionado != null) {
				for (int i = 0; i < 1; i++) {
					model.addRow(new Object[] {
							pedidoSelecionado.getId(), pedidoSelecionado.getSabor(), clienteSelecionado.getNomeCliente(), pedidoSelecionado.getPreco()
					});
				
					tabelaVenda.setModel(model);
					
				
				} 
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum pedido selecionado!");
			}
				
			}
		});
		btnAdicionar.setBounds(742, 830, 157, 37);
		contentPane.add(btnAdicionar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.setBackground(Color.DARK_GRAY);
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < model.getRowCount(); i++) {
					String idCliente = txtIdCliente.getText();
					String idPedido = (tabelaVenda.getValueAt(i, 0).toString());
					String sabor = (tabelaVenda.getValueAt(i, 1).toString());
					String tamanho = txtTamanho.getText();
					String nome = (tabelaVenda.getValueAt(i, 2).toString());
					String preco = (tabelaVenda.getValueAt(i, 3).toString());
					String bebida = txtBebida.getText();
					DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String h =(dtf5.format(LocalDateTime.now()));
					
					Venda venda = new Venda();
					
					venda.setCliente(Integer.valueOf(idCliente));
					venda.setPedido(Integer.valueOf(idPedido));
					venda.setSabor(sabor);
					venda.setTamanho(tamanho);
					venda.setNome(nome);
					venda.setValor(Double.valueOf(preco));
					venda.setBebida(bebida);
					venda.setData(h);
					
					VendaBD vendaBD = new VendaBD();
					vendaBD.inserirVenda(venda);
					
					
					
					
				}
				while(tabelaVenda.getModel().getRowCount()>0) {
					((DefaultTableModel) tabelaVenda.getModel()).removeRow(0);
				}
				JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
				
				txtIdCliente.setText("");
				txtIdPedido.setText("");
				txtSabor.setText("");
				txtTamanho.setText("");
				txtBebida.setText("");
				txtPreco.setText("");
				txtNome.setText("");
			}
		});
		btnFinalizar.setBounds(1044, 830, 157, 37);
		contentPane.add(btnFinalizar);
		
		JButton btnHistorico = new JButton("Historico");
		btnHistorico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHistorico.setForeground(Color.WHITE);
		btnHistorico.setBackground(Color.DARK_GRAY);
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoVendas HV = new HistoricoVendas(tv);
				HV.setVisible(true);
			}
		});
		btnHistorico.setBounds(1332, 830, 157, 37);
		contentPane.add(btnHistorico);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(166, 265, 157, 37);
		contentPane.add(panel_3);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setForeground(Color.WHITE);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPedido.setBackground(Color.DARK_GRAY);
		lblPedido.setBounds(0, 0, 157, 37);
		panel_3.add(lblPedido);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_3_1.setBackground(Color.DARK_GRAY);
		panel_3_1.setBounds(166, 622, 157, 37);
		contentPane.add(panel_3_1);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCliente.setBackground(Color.DARK_GRAY);
		lblCliente.setBounds(0, 0, 157, 37);
		panel_3_1.add(lblCliente);
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 11, 56, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Venda");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(742, 149, 747, 78);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(TelaVenda.class.getResource("/imagens/carrinho-de-compras.png")));
		lblNewLabel_1.setBounds(1571, 80, 252, 170);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
}
