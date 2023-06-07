package visao;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modelo.Pedido;
import controle.PedidoBD;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.Insets;
import java.awt.Cursor;

public class TelaPedido extends JFrame {

	private JPanel contentPane;
	private JTextField txtSabor;
	private JTextField txtTamanho;
	private JTextField txtBebida;
	private JTextField txtPreco;
	private JTextField txtID;
	private JTable tabelaPedido;
	private JTextField txtFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPedido frame = new TelaPedido();
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
	public TelaPedido() {
		setUndecorated(true);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 2000, 1200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(434, 98, 1055, 676);
		contentPane.add(scrollPane);

		tabelaPedido = new JTable();
		tabelaPedido.setBorder(null);
		tabelaPedido.setBackground(Color.DARK_GRAY);
		tabelaPedido.setForeground(Color.WHITE);
		tabelaPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabelaPedido.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Sabor", "Tamanho", "Bebida", "Preco (R$)"
			}
		));
		scrollPane.setViewportView(tabelaPedido);

		try {
			PedidoBD pedidoBD = new PedidoBD();

			DefaultTableModel model = (DefaultTableModel) tabelaPedido.getModel();
			model.setNumRows(0);

			ArrayList<Pedido> lista = pedidoBD.pesquisarPedido();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getSabor(),
						lista.get(num).getTamanho(),
						lista.get(num).getBebida(),
						lista.get(num).getPreco(),			

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

		tabelaPedido.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	int row = tabelaPedido.getSelectedRow();
                    txtID.setText(tabelaPedido.getValueAt(row, 0).toString());
                    txtSabor.setText(tabelaPedido.getValueAt(row, 1).toString());
                    txtTamanho.setText(tabelaPedido.getValueAt(row, 2).toString());
                    txtBebida.setText(tabelaPedido.getValueAt(row, 3).toString());
                    txtPreco.setText(tabelaPedido.getValueAt(row, 4).toString());
                }
            }
        });
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(546, 833, 60, 26);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		JLabel lblSabor = new JLabel("Sabor");
		lblSabor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSabor.setBounds(0, 0, 60, 26);
		panel_2.add(lblSabor);
		lblSabor.setBackground(Color.DARK_GRAY);
		lblSabor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSabor.setForeground(Color.WHITE);

		txtSabor = new JTextField();
		txtSabor.setCaretColor(Color.WHITE);
		txtSabor.setMargin(new Insets(2, 6, 2, 2));
		txtSabor.setForeground(Color.WHITE);
		txtSabor.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSabor.setBackground(Color.DARK_GRAY);
		txtSabor.setBounds(546, 866, 145, 31);
		contentPane.add(txtSabor);
		txtSabor.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(814, 833, 82, 26);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setBounds(0, 0, 82, 26);
		panel.add(lblTamanho);
		lblTamanho.setBackground(Color.DARK_GRAY);
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTamanho.setForeground(Color.WHITE);

		txtTamanho = new JTextField();
		txtTamanho.setCaretColor(Color.WHITE);
		txtTamanho.setMargin(new Insets(2, 6, 2, 2));
		txtTamanho.setForeground(Color.WHITE);
		txtTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTamanho.setBackground(Color.DARK_GRAY);
		txtTamanho.setBounds(814, 866, 145, 31);
		contentPane.add(txtTamanho);
		txtTamanho.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(1095, 833, 69, 26);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblBebida = new JLabel("Bebida");
		lblBebida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBebida.setBounds(0, 0, 66, 26);
		panel_3.add(lblBebida);
		lblBebida.setBackground(Color.DARK_GRAY);
		lblBebida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBebida.setForeground(Color.WHITE);

		txtBebida = new JTextField();
		txtBebida.setCaretColor(Color.WHITE);
		txtBebida.setMargin(new Insets(2, 6, 2, 2));
		txtBebida.setForeground(Color.WHITE);
		txtBebida.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtBebida.setBackground(Color.DARK_GRAY);
		txtBebida.setBounds(1095, 866, 127, 31);
		contentPane.add(txtBebida);
		txtBebida.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(1362, 833, 69, 26);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblPreco = new JLabel("Preco");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setBounds(0, 0, 69, 26);
		panel_4.add(lblPreco);
		lblPreco.setBackground(Color.DARK_GRAY);
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPreco.setForeground(Color.WHITE);

		txtPreco = new JTextField();
		txtPreco.setCaretColor(Color.WHITE);
		txtPreco.setMargin(new Insets(2, 6, 2, 2));
		txtPreco.setForeground(Color.WHITE);
		txtPreco.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPreco.setBackground(Color.DARK_GRAY);
		txtPreco.setBounds(1362, 866, 127, 31);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(434, 833, 46, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIDCliente = new JLabel("ID");
		lblIDCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDCliente.setBounds(0, 0, 46, 26);
		panel_1.add(lblIDCliente);
		lblIDCliente.setBackground(Color.DARK_GRAY);
		lblIDCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDCliente.setForeground(Color.WHITE);

		txtID = new JTextField();
		txtID.setCaretColor(Color.WHITE);
		txtID.setMargin(new Insets(2, 6, 2, 2));
		txtID.setForeground(Color.WHITE);
		txtID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtID.setBackground(Color.DARK_GRAY);
		txtID.setEnabled(false);
		txtID.setBounds(434, 866, 46, 31);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrar.setBackground(Color.DARK_GRAY);
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarPedido();
				LimparCampos();
				listarValores();
			}
		});
		btnCadastrar.setBounds(434, 938, 188, 40);
		contentPane.add(btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAlterar.setBackground(Color.DARK_GRAY);
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCliente();
				LimparCampos();
				listarValores();
			}
		});
		btnAlterar.setBounds(698, 938, 197, 40);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluir.setBackground(Color.DARK_GRAY);
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
				LimparCampos();
				listarValores();
			}
		});
		btnExcluir.setBounds(992, 938, 197, 40);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpar.setBackground(Color.DARK_GRAY);
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnLimpar.setBounds(1292, 938, 197, 40);
		contentPane.add(btnLimpar);
		
		JLabel lblNewLabel_1 = new JLabel("Pedidos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(434, 0, 1055, 75);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltar.setBackground(Color.DARK_GRAY);
		btnVoltar.setBounds(10, 11, 76, 37);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPedido.class.getResource("/imagens/pedido.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 105, 263, 240);
		contentPane.add(lblNewLabel);
		
		txtFiltro = new JTextField();
		txtFiltro.setCaretColor(Color.WHITE);
		txtFiltro.setMargin(new Insets(2, 6, 2, 2));
		txtFiltro.setForeground(Color.WHITE);
		txtFiltro.setBackground(Color.DARK_GRAY);
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(1593, 514, 188, 31);
		contentPane.add(txtFiltro);
		
		JRadioButton rdbtnID = new JRadioButton("ID");
		rdbtnID.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnID.setForeground(Color.WHITE);
		rdbtnID.setBackground(Color.DARK_GRAY);
		rdbtnID.setBounds(1593, 291, 109, 23);
		contentPane.add(rdbtnID);
		
		JRadioButton rdbtnSabor = new JRadioButton("Sabor");
		rdbtnSabor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnSabor.setForeground(Color.WHITE);
		rdbtnSabor.setBackground(Color.DARK_GRAY);
		rdbtnSabor.setBounds(1593, 334, 109, 23);
		contentPane.add(rdbtnSabor);
		
		JRadioButton rdbtnTamanho = new JRadioButton("Tamanho");
		rdbtnTamanho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnTamanho.setForeground(Color.WHITE);
		rdbtnTamanho.setBackground(Color.DARK_GRAY);
		rdbtnTamanho.setBounds(1593, 375, 109, 23);
		contentPane.add(rdbtnTamanho);
		
		JRadioButton rdbtnBebida = new JRadioButton("Bebida");
		rdbtnBebida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnBebida.setForeground(Color.WHITE);
		rdbtnBebida.setBackground(Color.DARK_GRAY);
		rdbtnBebida.setBounds(1593, 421, 109, 23);
		contentPane.add(rdbtnBebida);
		
		JRadioButton rdbtnPreco = new JRadioButton("Preco");
		rdbtnPreco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnPreco.setForeground(Color.WHITE);
		rdbtnPreco.setBackground(Color.DARK_GRAY);
		rdbtnPreco.setBounds(1593, 460, 109, 23);
		contentPane.add(rdbtnPreco);
		
		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setSelected(true);
		rdbtnTodos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnTodos.setForeground(Color.WHITE);
		rdbtnTodos.setBackground(Color.DARK_GRAY);
		rdbtnTodos.setBounds(1593, 253, 109, 23);
		contentPane.add(rdbtnTodos);
		
		JButton btnNewButton_1 = new JButton("Filtrar");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnID.isSelected()) {
					filtrarTabelaPorColuna(0);
				}
				if(rdbtnSabor.isSelected()) {
					filtrarTabelaPorColuna(1);
				}
				if(rdbtnTamanho.isSelected()) {
					filtrarTabelaPorColuna(2);
				}
				if(rdbtnBebida.isSelected()) {
					filtrarTabelaPorColuna(3);
				}
				if(rdbtnPreco.isSelected()) {
					filtrarTabelaPorColuna(4);
				}
				if(rdbtnTodos.isSelected()) {
					filtrarTabela(txtFiltro.getText());
				}
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(1593, 551, 188, 40);
		contentPane.add(btnNewButton_1);}

	private void CadastrarPedido() {
		String sabor, tamanho, bebida;
		double preco;

		sabor = txtSabor.getText();
		tamanho = txtTamanho.getText();
		bebida = txtBebida.getText();
		preco = Double.parseDouble(txtPreco.getText());

		Pedido pedido = new Pedido();
		pedido.setSabor(sabor);
		pedido.setTamanho(tamanho);
		pedido.setBebida(bebida);
		pedido.setPreco(preco);

		PedidoBD pedidoBD = new PedidoBD();
		pedidoBD.cadastrarPedido(pedido);
	}
	private void LimparCampos() {
		txtID.setText("");
		txtSabor.setText("");
		txtTamanho.setText("");
		txtBebida.setText("");
		txtPreco.setText("");

	}
	private void AlterarCliente() {
		String sabor, tamanho, bebida;
		int id;
		double preco;

		id = Integer.parseInt(txtID.getText());
		sabor = txtSabor.getText();
		tamanho = txtTamanho.getText();
		bebida = txtBebida.getText();
		preco = Double.parseDouble(txtPreco.getText());


		Pedido pedido = new Pedido();
		pedido.setId(id);
		pedido.setSabor(sabor);
		pedido.setTamanho(tamanho);
		pedido.setBebida(bebida);
		pedido.setPreco(preco);

		PedidoBD pedidoBD = new PedidoBD();
		pedidoBD.alterarPedido(pedido);
	}
	private void excluirCliente() {
		int id;

		id = Integer.parseInt(txtID.getText());

		Pedido pedido = new Pedido();
		pedido.setId(id);

		PedidoBD pedidoBD = new PedidoBD();
		pedidoBD.excluirPedido(pedido);
	}
	private void listarValores() {
		try {
			PedidoBD pedidoBD = new PedidoBD();

			DefaultTableModel model = (DefaultTableModel) tabelaPedido.getModel();
			model.setNumRows(0);

			ArrayList<Pedido> lista = pedidoBD.pesquisarPedido();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getSabor(),
						lista.get(num).getTamanho(),
						lista.get(num).getBebida(),
						lista.get(num).getPreco(),	
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

	}
	private void filtrarTabelaPorColuna(int columnIndex) {
		String filtro = txtFiltro.getText();
	    DefaultTableModel model = (DefaultTableModel) tabelaPedido.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    tabelaPedido.setRowSorter(sorter);
	    sorter.setRowFilter(RowFilter.regexFilter(filtro, columnIndex));
	}
	
	private void filtrarTabela(String filtro) {
	    DefaultTableModel model = (DefaultTableModel) tabelaPedido.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    tabelaPedido.setRowSorter(sorter);
	    sorter.setRowFilter(RowFilter.regexFilter(filtro));
	}
}
