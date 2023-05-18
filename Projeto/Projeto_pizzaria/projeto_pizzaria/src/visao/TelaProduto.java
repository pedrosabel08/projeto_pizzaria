package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ProdutoBD;
import modelo.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;

public class TelaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtMarca;
	private JTable tabelaProduto;
	private JTextField txtTamanho;
	private JTextField txtCor;
	private JTextField txtID;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JTextField txtFornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProduto frame = new TelaProduto();
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
	public TelaProduto() {
		setTitle("Tela de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1900, 1000);
		this.setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 183, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(216, 647, 176, 30);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtNome.setBounds(265, 678, 176, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Marca:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(444, 647, 176, 30);
		contentPane.add(lblNewLabel_1);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMarca.setBounds(488, 678, 176, 30);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnCadastrar.setBackground(UIManager.getColor("Button.shadow"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProduto();
				listarValores();
				LimparCampos();
		}
		});
		btnCadastrar.setBounds(126, 771, 176, 30);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 142, 1321, 471);
		contentPane.add(scrollPane);
		
		
		
		tabelaProduto = new JTable();
		tabelaProduto.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		tabelaProduto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Preco", "Quantidade", "Fornecedor"
			}
		));
		tabelaProduto.setBounds(220, 221, 155, -152);
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
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}
		
		JButton btnNewButton_1 = new JButton("Selecionar");
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_1.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelecionarCampos();
				txtFornecedor.setEditable(false);
			}
		});
		
		btnNewButton_1.setBounds(356, 771, 176, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Limpar");
		btnNewButton_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_2.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnNewButton_2.setBounds(1047, 771, 176, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_3.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaProduto.getSelectedRowCount() > 0) {
				AlterarProduto();
				listarValores();
				LimparCampos();
				txtFornecedor.setEditable(true);
				}
				else {
				JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela para alterar!");
				}
			}
		});
		btnNewButton_3.setBounds(581, 771, 176, 30);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_4.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaProduto.getSelectedRowCount() > 0) {
				excluirProduto();
				listarValores();
				LimparCampos();
				txtFornecedor.setEditable(true);
				}
				else {
				JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela para excluir!");
				}
			}
		});
		btnNewButton_4.setBounds(809, 771, 176, 30);
		contentPane.add(btnNewButton_4);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblTamanho.setBounds(707, 647, 88, 30);
		contentPane.add(lblTamanho);
		
		txtTamanho = new JTextField();
		txtTamanho.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtTamanho.setColumns(10);
		txtTamanho.setBounds(707, 678, 88, 30);
		contentPane.add(txtTamanho);
		
		JLabel lblNewLabel_2 = new JLabel("Cor:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(809, 647, 176, 30);
		contentPane.add(lblNewLabel_2);
		
		txtCor = new JTextField();
		txtCor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtCor.setColumns(10);
		txtCor.setBounds(876, 678, 176, 30);
		contentPane.add(txtCor);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(126, 678, 74, 30);
		contentPane.add(txtID);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblId.setBounds(105, 647, 74, 30);
		contentPane.add(lblId);
		
		JButton btnNewButton_5 = new JButton("Voltar");
		btnNewButton_5.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaInicial inicio = new TelaInicial();
				inicio.setLocationRelativeTo(null);
				inicio.setVisible(true);
				
			}
		});
		btnNewButton_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_5.setBounds(1271, 771, 176, 30);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel_3 = new JLabel("Preco:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(1071, 647, 100, 30);
		contentPane.add(lblNewLabel_3);
		
		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtPreco.setColumns(10);
		txtPreco.setBounds(1094, 678, 100, 30);
		contentPane.add(txtPreco);
		
		JLabel lblNewLabel_4 = new JLabel("Quantidade:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(1234, 645, 162, 30);
		contentPane.add(lblNewLabel_4);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(1271, 678, 88, 30);
		contentPane.add(txtQuantidade);
		
		JLabel lblNewLabel_5 = new JLabel("Tela de Produtos");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblNewLabel_5.setBounds(126, 47, 1321, 46);
		contentPane.add(lblNewLabel_5);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtFornecedor.setColumns(10);
		txtFornecedor.setBounds(1434, 678, 88, 30);
		contentPane.add(txtFornecedor);
		
		JLabel lblNewLabel_4_1 = new JLabel("ID do Fornecedor:");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_4_1.setBounds(1378, 647, 162, 30);
		contentPane.add(lblNewLabel_4_1);
		
	}
	private void SelecionarCampos() {
		
		if(tabelaProduto.getSelectedRowCount() > 0) {
		
		int setar = tabelaProduto.getSelectedRow();
		
		txtID.setText(tabelaProduto.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tabelaProduto.getModel().getValueAt(setar, 1).toString());
		txtMarca.setText(tabelaProduto.getModel().getValueAt(setar, 2).toString());
		txtTamanho.setText(tabelaProduto.getModel().getValueAt(setar, 3).toString());
		txtCor.setText(tabelaProduto.getModel().getValueAt(setar, 4).toString());
		txtPreco.setText(tabelaProduto.getModel().getValueAt(setar, 5).toString());
		txtQuantidade.setText(tabelaProduto.getModel().getValueAt(setar, 6).toString());
		txtFornecedor.setText(tabelaProduto.getModel().getValueAt(setar, 7).toString());
		
	}
		else {
			JOptionPane.showMessageDialog(null,"Clique em uma linha da tabela para selecionar!");
		}
	}
	
	private void CadastrarProduto() {
		String nome, marca, tamanho, cor, preco, fornecedor, quantidade;;
		
		nome = txtNome.getText();
		marca = txtMarca.getText();
		tamanho = txtTamanho.getText();
		cor = txtCor.getText();
		preco = txtPreco.getText();
		quantidade = txtQuantidade.getText();
		fornecedor = txtFornecedor.getText();
		
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(Double.valueOf(preco));
		produto.setQuantidade(quantidade);
		produto.setFornecedor(fornecedor);
		
		ProdutoBD produtoBD = new ProdutoBD();
		produtoBD.cadastrarProduto(produto);
	}
	private void LimparCampos() {
		txtID.setText("");
		txtNome.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
		txtNome.requestFocus();
		txtFornecedor.setText("");
	}
	private void AlterarProduto() {
		int id;
		String nome, marca, tamanho, cor, preco, quantidade;;
		
		id = Integer.parseInt(txtID.getText());
		nome = txtNome.getText();
		marca = txtMarca.getText();
		tamanho = txtTamanho.getText();
		cor = txtCor.getText();
		preco = txtPreco.getText();
		quantidade = txtQuantidade.getText();
		
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setPreco(Double.valueOf(preco));
		produto.setQuantidade(quantidade);
		
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
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}
	}
}
