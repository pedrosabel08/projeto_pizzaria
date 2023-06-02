package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.Pedido;
import controle.PedidoBD;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controle.ClienteBD;
import controle.FuncionarioBD;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class TabelaPedido extends JFrame {

	static Connection conexao;
	private JPanel contentPane;
	private JTable tbPedido;
	private ArrayList<Pedido> listaPedido;
	private Pedido pedidoSelecionado;
	private DefaultTableModel modelo;
	protected static final int posicaoPessoa = 0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TabelaPedido(TelaVenda tv) {
		setUndecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(15, 57, 424, 185);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		tbPedido = new JTable();
		tbPedido.setForeground(Color.WHITE);
		tbPedido.setBackground(Color.DARK_GRAY);
		tbPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbPedido.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Sabor", "Tamanho", "Bebida", "Preco (R$)" }

		));
		scrollPane.setViewportView(tbPedido);
		PedidoBD pedidoBD = new PedidoBD();
		listaPedido = pedidoBD.pesquisarPedido();

		tbPedido.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Sabor", "Tamanho", "Bebida", "Preco (R$)" }));
		scrollPane.setViewportView(tbPedido);

		modelo = (DefaultTableModel) tbPedido.getModel();
		for (int i = 0; i < listaPedido.size(); i++) {
			Pedido p = listaPedido.get(i);
			modelo.addRow(new Object[] { p.getId(), p.getSabor(), p.getTamanho(), p.getBebida(), p.getPreco() });

		}
		tbPedido.setModel(modelo);
		
		JButton btnNewButton_1 = new JButton("Selecionar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha = tbPedido.getSelectedRow();
				int idPedido = (int) tbPedido.getValueAt(linha, 0);

				for (Pedido pedido : listaPedido) {
					if (pedido.getId() == idPedido) {
						pedidoSelecionado = pedido;
					}
				}
				if (linha > -1) {

					tv.setPedidoSelecionado(pedidoSelecionado);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "escolha uma linha na tabela");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(152, 253, 148, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(15, 11, 89, 23);
		contentPane.add(btnNewButton);
	}

}