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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.FornecedorBD;
import controle.FuncionarioBD;
import modelo.Fornecedor;
import modelo.Funcionario;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFormattedTextField;

public class TelaFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNome;
	private JTable tabelaFornecedor;
	private JFormattedTextField txtCNPJ;
	private JFormattedTextField txtTelefone;
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
		setTitle("Tela de Fornecedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1900, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 183, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 147, 1321, 471);
		contentPane.add(scrollPane);
		
		tabelaFornecedor = new JTable();
		tabelaFornecedor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		tabelaFornecedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CNPJ", "Contato"
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
	
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(119, 654, 86, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(380, 650, 230, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CNPJ:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(750, 650, 230, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contato:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(1157, 654, 230, 30);
		contentPane.add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setBackground(Color.WHITE);
		txtID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtID.setEnabled(false);
		txtID.setBounds(141, 691, 86, 30);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBackground(Color.WHITE);
		txtNome.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtNome.setBounds(464, 691, 230, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCNPJ = new JFormattedTextField();
		txtCNPJ.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtCNPJ.setBounds(839, 691, 230, 30);
		contentPane.add(txtCNPJ);
		
		MaskFormatter maskCNPJ;
		try {
			maskCNPJ = new MaskFormatter("##.###.###/####-##");
			maskCNPJ.install(txtCNPJ);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarFornecedor();
				LimparCampos();
				listarValores();
			}
		});
		btnNewButton.setBounds(141, 767, 176, 30);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_2 = new JButton("Selecionar");
		btnNewButton_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelecionarCampos();
			}
		});
		btnNewButton_2.setBounds(356, 767, 176, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
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
		btnNewButton_3.setBounds(587, 767, 176, 30);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
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
		btnNewButton_4.setBounds(818, 767, 176, 30);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Limpar");
		btnNewButton_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnNewButton_5.setBounds(1051, 767, 176, 30);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Voltar");
		btnNewButton_6.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaInicial inicio = new TelaInicial();
				inicio.setLocationRelativeTo(null);
				inicio.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(1286, 767, 176, 30);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel_4 = new JLabel("Tela de Fornecedores");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblNewLabel_4.setBounds(153, 68, 1321, 42);
		contentPane.add(lblNewLabel_4);
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtTelefone.setBounds(1232, 695, 230, 33);
		contentPane.add(txtTelefone);
		
		MaskFormatter maskTelefone;
		try {
			maskTelefone = new MaskFormatter("(##)#####-####");
			maskTelefone.install(txtTelefone);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
	}
	
	private void SelecionarCampos() {
		
		if(tabelaFornecedor.getSelectedRowCount() > 0) {
		int setar = tabelaFornecedor.getSelectedRow();
		
		txtID.setText(tabelaFornecedor.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tabelaFornecedor.getModel().getValueAt(setar, 1).toString());
		txtCNPJ.setText(tabelaFornecedor.getModel().getValueAt(setar, 2).toString());
		txtTelefone.setText(tabelaFornecedor.getModel().getValueAt(setar, 3).toString());
	}
		else {
			JOptionPane.showMessageDialog(null,"Clique em uma linha da tabela para selecionar!");
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

}