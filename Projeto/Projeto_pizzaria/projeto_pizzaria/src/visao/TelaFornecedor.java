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
import javax.swing.border.MatteBorder;

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
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(318, 147, 1248, 471);
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

		txtID = new JTextField();
		txtID.setForeground(Color.WHITE);
		txtID.setBackground(Color.DARK_GRAY);
		txtID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtID.setEnabled(false);
		txtID.setBounds(318, 691, 86, 30);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtNome = new JTextField();
		txtNome.setForeground(Color.WHITE);
		txtNome.setBackground(Color.DARK_GRAY);
		txtNome.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtNome.setBounds(530, 691, 230, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCNPJ = new JFormattedTextField();
		txtCNPJ.setBackground(Color.DARK_GRAY);
		txtCNPJ.setForeground(Color.WHITE);
		txtCNPJ.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtCNPJ.setBounds(940, 691, 230, 30);
		contentPane.add(txtCNPJ);

		MaskFormatter maskCNPJ;
		try {
			maskCNPJ = new MaskFormatter("##.###.###/####-##");
			maskCNPJ.install(txtCNPJ);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarFornecedor();
				LimparCampos();
				listarValores();
			}
		});
		btnNewButton.setBounds(318, 767, 176, 30);
		contentPane.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.DARK_GRAY);
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
		btnNewButton_3.setBounds(655, 767, 176, 30);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setForeground(Color.WHITE);
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
		btnNewButton_4.setBounds(1015, 767, 176, 30);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Limpar");
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(Color.DARK_GRAY);
		btnNewButton_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnNewButton_5.setBounds(1390, 767, 176, 30);
		contentPane.add(btnNewButton_5);

		txtTelefone = new JFormattedTextField();
		txtTelefone.setBackground(Color.DARK_GRAY);
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtTelefone.setBounds(1336, 695, 230, 33);
		contentPane.add(txtTelefone);

		MaskFormatter maskTelefone;
		try {
			maskTelefone = new MaskFormatter("(##)#####-####");
			maskTelefone.install(txtTelefone);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
			panel_1.setBackground(Color.DARK_GRAY);
			panel_1.setBounds(318, 650, 46, 26);
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
			panel_2.setBounds(530, 650, 60, 26);
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
			panel_2_1.setBounds(940, 650, 60, 26);
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
			panel_2_2.setBounds(1336, 650, 86, 26);
			contentPane.add(panel_2_2);
			
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefone.setForeground(Color.WHITE);
			lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTelefone.setBackground(Color.DARK_GRAY);
			lblTelefone.setBounds(0, 0, 88, 26);
			panel_2_2.add(lblTelefone);
			
			JLabel lblNewLabel_1 = new JLabel("Fornecedor");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_1.setBounds(318, 35, 1248, 75);
			contentPane.add(lblNewLabel_1);
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
}