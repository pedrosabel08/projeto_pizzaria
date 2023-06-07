package visao;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import controle.FornecedorBD;
import controle.FuncionarioBD;
import modelo.Fornecedor;
import modelo.Funcionario;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFormattedTextField;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.Insets;
import java.awt.Cursor;

public class TelaFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNome;
	private JTable tabelaFornecedor;
	private JFormattedTextField txtCNPJ;
	private JFormattedTextField txtTelefone;
	private JTextField txtFiltro;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFornecedor frame = new TelaFornecedor();
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
	public TelaFornecedor() {
		setUndecorated(true);
		setTitle("Tela de Fornecedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1900, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 147, 1120, 614);
		contentPane.add(scrollPane);

		tabelaFornecedor = new JTable();
		tabelaFornecedor.setForeground(Color.WHITE);
		tabelaFornecedor.setBackground(Color.DARK_GRAY);
		tabelaFornecedor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		tabelaFornecedor.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Nome", "CNPJ", "Telefone"
				}
				));
		scrollPane.setViewportView(tabelaFornecedor);

		try {
			FornecedorBD fornecedorBD = new FornecedorBD();

			DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
			model.setNumRows(0);

			ArrayList<Fornecedor> lista = fornecedorBD.pesquisarFornecedor();

			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getCnpj(),
						lista.get(num).getTelefone()

				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}
		
		tabelaFornecedor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	int row = tabelaFornecedor.getSelectedRow();
                    txtID.setText(tabelaFornecedor.getValueAt(row, 0).toString());
                    txtNome.setText(tabelaFornecedor.getValueAt(row, 1).toString());
                    txtCNPJ.setText(tabelaFornecedor.getValueAt(row, 2).toString());
                    txtTelefone.setText(tabelaFornecedor.getValueAt(row, 3).toString());
                }
            }
        });

		txtID = new JTextField();
		txtID.setCaretColor(Color.WHITE);
		txtID.setMargin(new Insets(2, 6, 2, 2));
		txtID.setForeground(Color.WHITE);
		txtID.setBackground(Color.DARK_GRAY);
		txtID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtID.setEnabled(false);
		txtID.setBounds(368, 833, 86, 30);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtNome = new JTextField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setMargin(new Insets(2, 6, 2, 2));
		txtNome.setForeground(Color.WHITE);
		txtNome.setBackground(Color.DARK_GRAY);
		txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNome.setBounds(623, 833, 230, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCNPJ = new JFormattedTextField();
		txtCNPJ.setCaretColor(Color.WHITE);
		txtCNPJ.setMargin(new Insets(2, 6, 2, 2));
		txtCNPJ.setBackground(Color.DARK_GRAY);
		txtCNPJ.setForeground(Color.WHITE);
		txtCNPJ.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCNPJ.setBounds(958, 833, 230, 30);
		contentPane.add(txtCNPJ);

		MaskFormatter maskCNPJ;
		try {
			maskCNPJ = new MaskFormatter("##.###.###/####-##");
			maskCNPJ.install(txtCNPJ);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarFornecedor();
				LimparCampos();
				listarValores();
			}
		});
		btnNewButton.setBounds(369, 905, 176, 38);
		contentPane.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaFornecedor.getSelectedRowCount() > 0) {
					AlterarFornecedor();
					LimparCampos();
					listarValores();
				}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela para alterar!");
				}
			}
		});
		btnNewButton_3.setBounds(623, 905, 176, 38);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaFornecedor.getSelectedRowCount() > 0) {
					excluirFornecedor();
					listarValores();
					LimparCampos();	
				}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela para excluir!");
				}
			}
		});
		btnNewButton_4.setBounds(958, 905, 176, 38);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Limpar");
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(Color.DARK_GRAY);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnNewButton_5.setBounds(1312, 905, 176, 38);
		contentPane.add(btnNewButton_5);

		txtTelefone = new JFormattedTextField();
		txtTelefone.setCaretColor(Color.WHITE);
		txtTelefone.setMargin(new Insets(2, 6, 2, 2));
		txtTelefone.setBackground(Color.DARK_GRAY);
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTelefone.setBounds(1258, 832, 230, 30);
		contentPane.add(txtTelefone);

		MaskFormatter maskTelefone;
		try {
			maskTelefone = new MaskFormatter("(##)#####-####");
			maskTelefone.install(txtTelefone);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
			panel_1.setBackground(Color.DARK_GRAY);
			panel_1.setBounds(368, 796, 46, 26);
			contentPane.add(panel_1);
			
			JLabel lblIDCliente = new JLabel("ID");
			lblIDCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblIDCliente.setForeground(Color.WHITE);
			lblIDCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblIDCliente.setBackground(Color.DARK_GRAY);
			lblIDCliente.setBounds(0, 0, 46, 26);
			panel_1.add(lblIDCliente);
			
			JPanel panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
			panel_2.setBackground(Color.DARK_GRAY);
			panel_2.setBounds(623, 792, 60, 26);
			contentPane.add(panel_2);
			
			JLabel lblNome = new JLabel("Nome");
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNome.setBackground(Color.DARK_GRAY);
			lblNome.setBounds(0, 0, 60, 26);
			panel_2.add(lblNome);
			
			JPanel panel_2_1 = new JPanel();
			panel_2_1.setLayout(null);
			panel_2_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
			panel_2_1.setBackground(Color.DARK_GRAY);
			panel_2_1.setBounds(958, 792, 60, 26);
			contentPane.add(panel_2_1);
			
			JLabel lblCnpj = new JLabel("CNPJ");
			lblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
			lblCnpj.setForeground(Color.WHITE);
			lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCnpj.setBackground(Color.DARK_GRAY);
			lblCnpj.setBounds(0, 0, 60, 26);
			panel_2_1.add(lblCnpj);
			
			JPanel panel_2_2 = new JPanel();
			panel_2_2.setLayout(null);
			panel_2_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
			panel_2_2.setBackground(Color.DARK_GRAY);
			panel_2_2.setBounds(1258, 791, 86, 26);
			contentPane.add(panel_2_2);
			
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefone.setForeground(Color.WHITE);
			lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTelefone.setBackground(Color.DARK_GRAY);
			lblTelefone.setBounds(0, 0, 88, 26);
			panel_2_2.add(lblTelefone);
			
			JLabel lblNewLabel_1 = new JLabel("Fornecedores");
			lblNewLabel_1.setBackground(Color.BLACK);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(Color.BLACK);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_1.setBounds(318, 35, 1248, 75);
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
			btnVoltar.setBounds(10, 11, 78, 37);
			contentPane.add(btnVoltar);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/imagens/fornecedor.png")));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(24, 154, 263, 240);
			contentPane.add(lblNewLabel);
			
			txtFiltro = new JTextField();
			txtFiltro.setFont(new Font("Tahoma", Font.BOLD, 12));
			txtFiltro.setCaretColor(Color.WHITE);
			txtFiltro.setMargin(new Insets(2, 6, 2, 2));
			txtFiltro.setForeground(Color.WHITE);
			txtFiltro.setBackground(Color.DARK_GRAY);
			txtFiltro.setColumns(10);
			txtFiltro.setBounds(1566, 411, 188, 31);
			contentPane.add(txtFiltro);
			
			JRadioButton rdbtnID = new JRadioButton("ID");
			rdbtnID.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnID.setForeground(Color.WHITE);
			rdbtnID.setBackground(Color.DARK_GRAY);
			rdbtnID.setBounds(1566, 283, 109, 23);
			contentPane.add(rdbtnID);
			
			JRadioButton rdbtnNome = new JRadioButton("Nome");
			rdbtnNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnNome.setForeground(Color.WHITE);
			rdbtnNome.setBackground(Color.DARK_GRAY);
			rdbtnNome.setBounds(1566, 309, 109, 23);
			contentPane.add(rdbtnNome);
			
			JRadioButton rdbtnCNPJ = new JRadioButton("CNPJ");
			rdbtnCNPJ.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnCNPJ.setForeground(Color.WHITE);
			rdbtnCNPJ.setBackground(Color.DARK_GRAY);
			rdbtnCNPJ.setBounds(1566, 335, 109, 23);
			contentPane.add(rdbtnCNPJ);
			
			JRadioButton rdbtnTelefone = new JRadioButton("Telefone");
			rdbtnTelefone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnTelefone.setForeground(Color.WHITE);
			rdbtnTelefone.setBackground(Color.DARK_GRAY);
			rdbtnTelefone.setBounds(1566, 361, 109, 23);
			contentPane.add(rdbtnTelefone);
			
			JRadioButton rdbtnTodos = new JRadioButton("Todos");
			rdbtnTodos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnTodos.setForeground(Color.WHITE);
			rdbtnTodos.setBackground(Color.DARK_GRAY);
			rdbtnTodos.setBounds(1566, 257, 109, 23);
			contentPane.add(rdbtnTodos);
			
			JButton btnNewButton_1_1 = new JButton("Filtrar");
			btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnID.isSelected()) {
						filtrarTabelaPorColuna(0);
					}
					if(rdbtnNome.isSelected()) {
						filtrarTabelaPorColuna(1);
					}
					if(rdbtnCNPJ.isSelected()) {
						filtrarTabelaPorColuna(2);
					}
					if(rdbtnTelefone.isSelected()) {
						filtrarTabelaPorColuna(3);
					}
					if(rdbtnTodos.isSelected()) {
						filtrarTabela(txtFiltro.getText());
					}
				}
			});
			btnNewButton_1_1.setForeground(Color.WHITE);
			btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnNewButton_1_1.setBackground(Color.DARK_GRAY);
			btnNewButton_1_1.setBounds(1566, 448, 188, 40);
			contentPane.add(btnNewButton_1_1);
				
			
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
	}

	private void CadastrarFornecedor() {
		String nome, cnpj, telefone;

		nome = txtNome.getText();
		cnpj = txtCNPJ.getText();
		telefone = txtTelefone.getText();

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(nome);
		fornecedor.setCnpj(cnpj);
		fornecedor.setTelefone(telefone);

		FornecedorBD fornecedorBD = new FornecedorBD();
		fornecedorBD.cadastrarFornecedor(fornecedor);
	}
	private void LimparCampos() {
		txtID.setText("");
		txtNome.setText("");
		txtCNPJ.setText("");
		txtTelefone.setText("");
		txtNome.requestFocus();
	}
	private void AlterarFornecedor() {
		int id;
		String nome, cnpj, telefone;

		id = Integer.parseInt(txtID.getText());
		nome = txtNome.getText();
		cnpj = txtCNPJ.getText();
		telefone = txtTelefone.getText();

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		fornecedor.setNome(nome);
		fornecedor.setCnpj(cnpj);
		fornecedor.setTelefone(telefone);


		FornecedorBD fornecedorBD = new FornecedorBD();
		fornecedorBD.alterarFornecedor(fornecedor);
	}
	private void excluirFornecedor() {
		int id;

		id = Integer.parseInt(txtID.getText());

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);

		FornecedorBD fornecedorBD = new FornecedorBD();
		fornecedorBD.excluirFornecedor(fornecedor);
	}

	private void listarValores() {
		try {
			FornecedorBD fornecedorBD = new FornecedorBD();

			DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
			model.setNumRows(0);

			ArrayList<Fornecedor> lista = fornecedorBD.pesquisarFornecedor();

			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getCnpj(),
						lista.get(num).getTelefone()

				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}
	}
	
	private void filtrarTabelaPorColuna(int columnIndex) {
		String filtro = txtFiltro.getText();
	    DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    tabelaFornecedor.setRowSorter(sorter);
	    sorter.setRowFilter(RowFilter.regexFilter(filtro, columnIndex));
	}
	
	private void filtrarTabela(String filtro) {
	    DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    tabelaFornecedor.setRowSorter(sorter);
	    sorter.setRowFilter(RowFilter.regexFilter(filtro));
	}
}