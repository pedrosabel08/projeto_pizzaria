package visao;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controle.VendaBD;
import modelo.Venda;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;


public class HistoricoVendas extends JFrame {

	private JPanel contentPane;
	private JTable tbVendas;
	private DefaultTableModel modelo;
	private ArrayList<Venda> listaVendas;
	private JTextField txtTotal;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public HistoricoVendas(TelaVenda tv) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHistricoDeVendas = new JLabel("Hist\u00F3rico de Vendas");
		lblHistricoDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistricoDeVendas.setFont(new Font("Tahoma", Font.PLAIN, 16));

		contentPane.add(lblHistricoDeVendas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 36, 507, 180);
		contentPane.add(scrollPane);
		
		tbVendas = new JTable();
		tbVendas.setForeground(Color.WHITE);
		tbVendas.setBackground(Color.DARK_GRAY);
		tbVendas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbVendas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID","Pedido","Cliente", "Valor", "Data"
			}
		));
		scrollPane.setViewportView(tbVendas);
		VendaBD vendaBD = new VendaBD();
		listaVendas = vendaBD.buscarVenda();
		
		modelo = (DefaultTableModel) tbVendas.getModel();
		for (int i = 0; i < listaVendas.size(); i++) {
			Venda v = listaVendas.get(i);
			modelo.addRow(new Object[] { v.getId(), v.getPedido(),v.getNome(), v.getValor(), v.getData() });

		}

		tbVendas.setModel(modelo);
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.setForeground(Color.WHITE);
		btnFechar.setBackground(Color.DARK_GRAY);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFechar.setBounds(44, 256, 102, 30);
		contentPane.add(btnFechar);
		
		JLabel lblNewLabel_4 = new JLabel("Valor Total:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(333, 262, 74, 18);
		contentPane.add(lblNewLabel_4);
		
		txtTotal = new JTextField();
		txtTotal.setBorder(null);
		txtTotal.setForeground(Color.WHITE);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txtTotal.setColumns(10);
		txtTotal.setBackground(Color.BLACK);
		txtTotal.setBounds(404, 262, 86, 18);
		contentPane.add(txtTotal);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTotal.setForeground(Color.WHITE);
		btnTotal.setBackground(Color.DARK_GRAY);
		btnTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double somaTotal=0;

				for(int i=0; i<modelo.getRowCount();i++)
					somaTotal += Double.parseDouble(modelo.getValueAt(i, 3).toString());
				txtTotal.setText(String.valueOf(somaTotal));
			}
		});
		btnTotal.setBounds(156, 256, 102, 30);
		contentPane.add(btnTotal);
		
		JLabel lblNewLabel = new JLabel("Historico de Vendas");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 11, 507, 14);
		contentPane.add(lblNewLabel);
	}
}
