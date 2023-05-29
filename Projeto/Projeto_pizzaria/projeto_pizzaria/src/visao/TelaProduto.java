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

import modelo.Produto;
import controle.ProdutoBD;
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

public class TelaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtFornecedor;
	private JTextField txtQuantidade;
	private JTextField txtPreco;
	private JTextField txtID;
	private JTable tabelaProduto;
	private JTextField txtFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProduto frame = new TelaProduto();
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
	public TelaProduto() {
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
		scrollPane.setViewportBorder(new MatteBorder(0, 0, 6, 6, (Color) new Color(255, 204, 51)));
		scrollPane.setBounds(434, 98, 1055, 676);
		contentPane.add(scrollPane);

		tabelaProduto = new JTable();
		tabelaProduto.setBorder(null);
		tabelaProduto.setBackground(Color.DARK_GRAY);
		tabelaProduto.setForeground(Color.WHITE);
		tabelaProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabelaProduto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Preco", "Quantidade", "Fornecedor "
			}
		));
		scrollPane.setViewportView(tabelaProduto);

		try {
			ProdutoBD produtoBD = new ProdutoBD();

			DefaultTableModel model = (DefaultTableModel) tabelaProduto.getModel();
			model.setNumRows(0);

			ArrayList<Produto> lista = produtoBD.pesquisarProduto();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getPreco(),
						lista.get(num).getQuantidade(),
						lista.get(num).getFornecedor()		

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

		tabelaProduto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	int row = tabelaProduto.getSelectedRow();
                    txtID.setText(tabelaProduto.getValueAt(row, 0).toString());
                    txtNome.setText(tabelaProduto.getValueAt(row, 1).toString());
                    txtPreco.setText(tabelaProduto.getValueAt(row, 2).toString());
                    txtQuantidade.setText(tabelaProduto.getValueAt(row, 3).toString());
                    txtFornecedor.setText(tabelaProduto.getValueAt(row, 4).toString());
                }
            }
        });
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(546, 833, 60, 26);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(0, 0, 60, 26);
		panel_2.add(lblNome);
		lblNome.setBackground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setForeground(Color.WHITE);

		txtNome = new JTextField();
		txtNome.setForeground(Color.WHITE);
		txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNome.setBackground(Color.DARK_GRAY);
		txtNome.setBounds(546, 866, 145, 31);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(1344, 833, 96, 26);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedor.setBounds(0, 0, 95, 26);
		panel.add(lblFornecedor);
		lblFornecedor.setBackground(Color.DARK_GRAY);
		lblFornecedor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFornecedor.setForeground(Color.WHITE);

		txtFornecedor = new JTextField();
		txtFornecedor.setForeground(Color.WHITE);
		txtFornecedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtFornecedor.setBackground(Color.DARK_GRAY);
		txtFornecedor.setBounds(1344, 866, 145, 31);
		contentPane.add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(1095, 833, 110, 26);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setBounds(0, 0, 108, 26);
		panel_3.add(lblQuantidade);
		lblQuantidade.setBackground(Color.DARK_GRAY);
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantidade.setForeground(Color.WHITE);

		txtQuantidade = new JTextField();
		txtQuantidade.setForeground(Color.WHITE);
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtQuantidade.setBackground(Color.DARK_GRAY);
		txtQuantidade.setBounds(1095, 866, 127, 31);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(822, 833, 69, 26);
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
		txtPreco.setForeground(Color.WHITE);
		txtPreco.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPreco.setBackground(Color.DARK_GRAY);
		txtPreco.setBounds(822, 866, 127, 31);
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
		txtID.setForeground(Color.WHITE);
		txtID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtID.setBackground(Color.DARK_GRAY);
		txtID.setEnabled(false);
		txtID.setBounds(434, 866, 46, 31);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrar.setBackground(Color.DARK_GRAY);
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProduto();
				LimparCampos();
				listarValores();
			}
		});
		btnCadastrar.setBounds(434, 938, 188, 40);
		contentPane.add(btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAlterar.setBackground(Color.DARK_GRAY);
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarProduto();
				LimparCampos();
				listarValores();
			}
		});
		btnAlterar.setBounds(698, 938, 197, 40);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluir.setBackground(Color.DARK_GRAY);
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
				LimparCampos();
				listarValores();
			}
		});
		btnExcluir.setBounds(992, 938, 197, 40);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
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
		
		JLabel lblNewLabel_1 = new JLabel("Produtos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(434, 12, 1055, 75);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(1510, 98, 56, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaProduto.class.getResource("/imagens/queijo.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(59, 105, 259, 227);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaProduto.class.getResource("/imagens/calabresa.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(59, 365, 259, 227);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaProduto.class.getResource("/imagens/refrigerante.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(59, 632, 259, 227);
		contentPane.add(lblNewLabel_3);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(1552, 382, 188, 31);
		contentPane.add(txtFiltro);
		
		JButton btnNewButton_1 = new JButton("Filtrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filtro = txtFiltro.getText();
				filtrarTabela(filtro);
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(1552, 419, 188, 40);
		contentPane.add(btnNewButton_1);}

	private void CadastrarProduto() {
		String nome, fornecedor, quantidade;
		double preco;

		nome = txtNome.getText();
		preco = Double.parseDouble(txtPreco.getText());
		quantidade = txtQuantidade.getText();
		fornecedor = txtFornecedor.getText();

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setFornecedor(fornecedor);

		ProdutoBD produtoBD = new ProdutoBD();
		produtoBD.cadastrarProduto(produto);
	}
	private void LimparCampos() {
		txtID.setText("");
		txtNome.setText("");
		txtFornecedor.setText("");
		txtQuantidade.setText("");
		txtPreco.setText("");

	}
	private void AlterarProduto() {
		String nome, fornecedor, quantidade;
		double preco;
		int id;

		id = Integer.parseInt(txtID.getText());
		nome = txtNome.getText();
		fornecedor = txtFornecedor.getText();
		quantidade = txtQuantidade.getText();
		preco = Double.parseDouble(txtPreco.getText());


		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setPreco(preco);

		ProdutoBD produtoBD = new ProdutoBD();
		produtoBD.alterarProduto(produto);
	}
	private void excluirProduto() {
		int id;

		id = Integer.parseInt(txtID.getText());

		Produto produto = new Produto();
		produto.setId(id);

		ProdutoBD produtoBD = new ProdutoBD();
		produtoBD.excluirProduto(produto);
	}
	private void listarValores() {
		try {
			ProdutoBD produtoBD = new ProdutoBD();

			DefaultTableModel model = (DefaultTableModel) tabelaProduto.getModel();
			model.setNumRows(0);

			ArrayList<Produto> lista = produtoBD.pesquisarProduto();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getPreco(),
						lista.get(num).getQuantidade(),
						lista.get(num).getFornecedor()		

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

	}
	private void filtrarTabela(String filtro) {
	    DefaultTableModel model = (DefaultTableModel) tabelaProduto.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    tabelaProduto.setRowSorter(sorter);
	    sorter.setRowFilter(RowFilter.regexFilter(filtro));
	}
}
