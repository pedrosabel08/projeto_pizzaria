package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Pedido;
import modelo.Produto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	/**
	 * Create the frame.
	 */
	public TelaVenda() {
		
		TelaVenda tv = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 2000, 1800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(0, 0, 6, 6, (Color) new Color(255, 204, 51)));
		scrollPane.setBounds(742, 254, 747, 553);
		contentPane.add(scrollPane);

		tabelaVenda = new JTable();
		tabelaVenda.setBorder(null);
		tabelaVenda.setBackground(Color.DARK_GRAY);
		tabelaVenda.setForeground(Color.WHITE);
		tabelaVenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabelaVenda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Pedido", "Cliente", "Data", "Valor"
			}
		));
		scrollPane.setViewportView(tabelaVenda);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		lblPedido.setBounds(66, 254, 102, 37);
		contentPane.add(lblPedido);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(66, 334, 46, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtIdPedido = new JTextField();
		txtIdPedido.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdPedido.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtIdPedido.setEditable(false);
		txtIdPedido.setColumns(10);
		txtIdPedido.setBackground(Color.WHITE);
		txtIdPedido.setBounds(99, 331, 46, 25);
		contentPane.add(txtIdPedido);
		
		JButton btnPesquisarPedido = new JButton("Pesquisar");
		btnPesquisarPedido.setForeground(Color.WHITE);
		btnPesquisarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaPedido TP = new TabelaPedido(tv);
				TP.setVisible(true);
			}
		});
		btnPesquisarPedido.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		btnPesquisarPedido.setBackground(Color.DARK_GRAY);
		btnPesquisarPedido.setBounds(185, 323, 157, 37);
		contentPane.add(btnPesquisarPedido);
		
		JLabel lblNewLabel_2_2 = new JLabel("Sabor:");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2_2.setBounds(66, 389, 60, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txtSabor = new JTextField();
		txtSabor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtSabor.setEditable(false);
		txtSabor.setColumns(10);
		txtSabor.setBackground(Color.WHITE);
		txtSabor.setBounds(122, 386, 220, 25);
		contentPane.add(txtSabor);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Tamanho:");
		lblNewLabel_2_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2_2_1.setBounds(66, 427, 102, 23);
		contentPane.add(lblNewLabel_2_2_1);
		
		txtTamanho = new JTextField();
		txtTamanho.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtTamanho.setEditable(false);
		txtTamanho.setColumns(10);
		txtTamanho.setBackground(Color.WHITE);
		txtTamanho.setBounds(154, 428, 188, 25);
		contentPane.add(txtTamanho);
		
		txtBebida = new JTextField();
		txtBebida.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtBebida.setEditable(false);
		txtBebida.setColumns(10);
		txtBebida.setBackground(Color.WHITE);
		txtBebida.setBounds(155, 469, 187, 25);
		contentPane.add(txtBebida);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Bebida:");
		lblNewLabel_2_2_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2_2_1_1.setBounds(67, 468, 102, 23);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtPreco.setEditable(false);
		txtPreco.setColumns(10);
		txtPreco.setBackground(Color.WHITE);
		txtPreco.setBounds(155, 513, 187, 25);
		contentPane.add(txtPreco);
		
		JLabel lblNewLabel_2_2_1_2 = new JLabel("Preco:");
		lblNewLabel_2_2_1_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2_2_1_2.setBounds(67, 512, 102, 23);
		contentPane.add(lblNewLabel_2_2_1_2);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		lblCliente.setBounds(66, 625, 102, 37);
		contentPane.add(lblCliente);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(66, 691, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdCliente.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtIdCliente.setEditable(false);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBackground(Color.WHITE);
		txtIdCliente.setBounds(99, 688, 46, 25);
		contentPane.add(txtIdCliente);
		
		JButton btnPesquisarCliente = new JButton("Pesquisar");
		btnPesquisarCliente.setForeground(Color.WHITE);
		btnPesquisarCliente.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		btnPesquisarCliente.setBackground(Color.DARK_GRAY);
		btnPesquisarCliente.setBounds(185, 680, 157, 37);
		contentPane.add(btnPesquisarCliente);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);
		txtNome.setBounds(128, 730, 214, 25);
		contentPane.add(txtNome);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nome:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(66, 733, 79, 14);
		contentPane.add(lblNewLabel_2_1);
	}
}
