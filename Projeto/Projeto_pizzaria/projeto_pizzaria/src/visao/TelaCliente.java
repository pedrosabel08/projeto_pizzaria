package visao;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controle.ClienteBD;
import modelo.Cliente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtReferencia;
	private JTextField txtIdCliente;
	private JTable tabelaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1500, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 66, 689, 315);
		contentPane.add(scrollPane);

		tabelaClientes = new JTable();
		tabelaClientes.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Nome", "Telefone", "Rua", "Bairro", "N\u00FAmero", "Refer\u00EAncia"
				}
				));
		scrollPane.setViewportView(tabelaClientes);

		try {
			ClienteBD clienteBD = new ClienteBD();

			DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
			model.setNumRows(0);

			ArrayList<Cliente> lista = clienteBD.pesquisarCliente();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getIdCliente(),
						lista.get(num).getNomeCliente(),
						lista.get(num).getTelefoneCliente(),
						lista.get(num).getRua(),
						lista.get(num).getBairro(),
						lista.get(num).getNumero(),
						lista.get(num).getReferencia()					

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(457, 491, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(457, 516, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(457, 547, 46, 14);
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(457, 572, 86, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(457, 603, 46, 14);
		contentPane.add(lblEndereco);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(457, 628, 46, 14);
		contentPane.add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(457, 653, 86, 20);
		contentPane.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(457, 684, 46, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(457, 715, 86, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(457, 753, 46, 14);
		contentPane.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(457, 777, 86, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblReferencia = new JLabel("Referência");
		lblReferencia.setBounds(457, 808, 62, 14);
		contentPane.add(lblReferencia);

		txtReferencia = new JTextField();
		txtReferencia.setBounds(457, 833, 86, 20);
		contentPane.add(txtReferencia);
		txtReferencia.setColumns(10);

		JLabel lblIDCliente = new JLabel("ID");
		lblIDCliente.setBounds(457, 431, 46, 14);
		contentPane.add(lblIDCliente);

		txtIdCliente = new JTextField();
		txtIdCliente.setEnabled(false);
		txtIdCliente.setBounds(457, 456, 86, 20);
		contentPane.add(txtIdCliente);
		txtIdCliente.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente();
				LimparCampos();
				listarValores();
			}
		});
		btnCadastrar.setBounds(645, 481, 89, 23);
		contentPane.add(btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCliente();
				LimparCampos();
				listarValores();
			}
		});
		btnAlterar.setBounds(645, 571, 89, 23);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
				listarValores();
			}
		});
		btnExcluir.setBounds(645, 636, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnLimpar.setBounds(645, 680, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelecionarCampos();
			}
		});
		btnSelecionar.setBounds(645, 515, 89, 23);
		contentPane.add(btnSelecionar);}


	private void SelecionarCampos() {

		if(tabelaClientes.getSelectedRowCount() > 0) {

			int setar = tabelaClientes.getSelectedRow();

			txtIdCliente.setText(tabelaClientes.getModel().getValueAt(setar, 0).toString());
			txtNome.setText(tabelaClientes.getModel().getValueAt(setar, 1).toString());
			txtTelefone.setText(tabelaClientes.getModel().getValueAt(setar, 2).toString());
			txtRua.setText(tabelaClientes.getModel().getValueAt(setar, 3).toString());
			txtBairro.setText(tabelaClientes.getModel().getValueAt(setar, 4).toString());
			txtNumero.setText(tabelaClientes.getModel().getValueAt(setar, 5).toString());
			txtReferencia.setText(tabelaClientes.getModel().getValueAt(setar, 6).toString());
		}
		else {
			JOptionPane.showMessageDialog(null,"Clique em uma linha da tabela para selecionar!");
		}
	}

	private void CadastrarCliente() {
		String nome, telefone, rua, bairro, referencia;
		int numero;

		nome = txtNome.getText();
		telefone = txtTelefone.getText();
		rua = txtRua.getText();
		bairro = txtBairro.getText();
		numero = Integer.parseInt(txtNumero.getText());
		referencia = txtReferencia.getText();

		Cliente cliente = new Cliente();
		cliente.setNomeCliente(nome);
		cliente.setTelefoneCliente(telefone);
		cliente.setRua(rua);
		cliente.setBairro(bairro);
		cliente.setNumero(numero);
		cliente.setReferencia(referencia);

		ClienteBD clienteBD = new ClienteBD();
		clienteBD.cadastrarCliente(cliente);
	}
	private void LimparCampos() {
		txtIdCliente.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtRua.setText("");
		txtBairro.setText("");
		txtNumero.setText("");
		txtReferencia.setText("");
	}
	private void AlterarCliente() {
		int id, numero;
		String nome, telefone, rua, bairro, referencia;

		id = Integer.parseInt(txtIdCliente.getText());
		nome = txtNome.getText();
		telefone = txtTelefone.getText();
		rua = txtRua.getText();
		bairro = txtBairro.getText();
		numero = Integer.parseInt(txtNumero.getText());
		referencia = txtReferencia.getText();

		Cliente cliente = new Cliente();
		cliente.setIdCliente(id);
		cliente.setNomeCliente(nome);
		cliente.setTelefoneCliente(telefone);
		cliente.setRua(rua);
		cliente.setBairro(bairro);
		cliente.setNumero(numero);
		cliente.setReferencia(referencia);

		ClienteBD clienteBD = new ClienteBD();
		clienteBD.alterarCliente(cliente);
	}
	private void excluirCliente() {
		int id;

		id = Integer.parseInt(txtIdCliente.getText());

		Cliente cliente = new Cliente();
		cliente.setIdCliente(id);

		ClienteBD clienteBD = new ClienteBD();
		clienteBD.excluirCliente(cliente);
	}
	private void listarValores() {
		try {
			ClienteBD clienteBD = new ClienteBD();

			DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
			model.setNumRows(0);

			ArrayList<Cliente> lista = clienteBD.pesquisarCliente();
			for(int num = 0 ; num < lista.size() ; num ++) {
				model.addRow(new Object [] {
						lista.get(num).getIdCliente(),
						lista.get(num).getNomeCliente(),
						lista.get(num).getTelefoneCliente(),
						lista.get(num).getRua(),
						lista.get(num).getBairro(),
						lista.get(num).getNumero(),
						lista.get(num).getReferencia()					

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro no Listar Valores" + e);
		}

	}
}
