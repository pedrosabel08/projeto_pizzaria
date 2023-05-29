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
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.ClienteBD;
import modelo.Cliente;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JFormattedTextField txtTelefone;
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
		scrollPane.setBounds(434, 143, 1055, 668);
		contentPane.add(scrollPane);

		tabelaClientes = new JTable();
		tabelaClientes.setBorder(null);
		tabelaClientes.setBackground(Color.DARK_GRAY);
		tabelaClientes.setForeground(Color.WHITE);
		tabelaClientes.setFont(new Font("Tahoma", Font.BOLD, 12));
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

		tabelaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	int row = tabelaClientes.getSelectedRow();
                    txtIdCliente.setText(tabelaClientes.getValueAt(row, 0).toString());
                    txtNome.setText(tabelaClientes.getValueAt(row, 1).toString());
                    txtTelefone.setText(tabelaClientes.getValueAt(row, 2).toString());
                    txtRua.setText(tabelaClientes.getValueAt(row, 3).toString());
                    txtBairro.setText(tabelaClientes.getValueAt(row, 4).toString());
                    txtNumero.setText(tabelaClientes.getValueAt(row, 5).toString());
                    txtReferencia.setText(tabelaClientes.getValueAt(row, 6).toString());
                }
            }
        });
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(521, 839, 60, 26);
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
		txtNome.setBounds(521, 872, 145, 31);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(696, 839, 82, 26);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setBounds(0, 0, 82, 26);
		panel.add(lblTelefone);
		lblTelefone.setBackground(Color.DARK_GRAY);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefone.setForeground(Color.WHITE);

		txtTelefone = new JFormattedTextField();
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTelefone.setBackground(Color.DARK_GRAY);
		txtTelefone.setBounds(696, 872, 145, 31);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		MaskFormatter maskTelefone;
		try {
			maskTelefone = new MaskFormatter("(##)#####-####");
			maskTelefone.install(txtTelefone);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(872, 839, 46, 26);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.CENTER);
		lblRua.setBounds(0, 0, 46, 26);
		panel_3.add(lblRua);
		lblRua.setBackground(Color.DARK_GRAY);
		lblRua.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRua.setForeground(Color.WHITE);

		txtRua = new JTextField();
		txtRua.setForeground(Color.WHITE);
		txtRua.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtRua.setBackground(Color.DARK_GRAY);
		txtRua.setBounds(872, 872, 127, 31);
		contentPane.add(txtRua);
		txtRua.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(1023, 839, 69, 26);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
		lblBairro.setBounds(0, 0, 69, 26);
		panel_4.add(lblBairro);
		lblBairro.setBackground(Color.DARK_GRAY);
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBairro.setForeground(Color.WHITE);

		txtBairro = new JTextField();
		txtBairro.setForeground(Color.WHITE);
		txtBairro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtBairro.setBackground(Color.DARK_GRAY);
		txtBairro.setBounds(1023, 872, 127, 31);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBounds(1208, 841, 69, 26);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNumero = new JLabel("Número");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setBounds(0, 0, 69, 26);
		panel_5.add(lblNumero);
		lblNumero.setBackground(Color.DARK_GRAY);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumero.setForeground(Color.WHITE);

		txtNumero = new JTextField();
		txtNumero.setForeground(Color.WHITE);
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNumero.setBackground(Color.DARK_GRAY);
		txtNumero.setBounds(1210, 873, 69, 31);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(1344, 839, 90, 26);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblReferencia = new JLabel("Referência");
		lblReferencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblReferencia.setBounds(0, 0, 90, 26);
		panel_6.add(lblReferencia);
		lblReferencia.setBackground(Color.DARK_GRAY);
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReferencia.setForeground(Color.WHITE);

		txtReferencia = new JTextField();
		txtReferencia.setForeground(Color.WHITE);
		txtReferencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtReferencia.setBackground(Color.DARK_GRAY);
		txtReferencia.setBounds(1344, 872, 145, 31);
		contentPane.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 204, 0)));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(434, 839, 46, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIDCliente = new JLabel("ID");
		lblIDCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDCliente.setBounds(0, 0, 46, 26);
		panel_1.add(lblIDCliente);
		lblIDCliente.setBackground(Color.DARK_GRAY);
		lblIDCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDCliente.setForeground(Color.WHITE);

		txtIdCliente = new JTextField();
		txtIdCliente.setForeground(Color.WHITE);
		txtIdCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIdCliente.setBackground(Color.DARK_GRAY);
		txtIdCliente.setEnabled(false);
		txtIdCliente.setBounds(434, 872, 46, 31);
		contentPane.add(txtIdCliente);
		txtIdCliente.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrar.setBackground(Color.DARK_GRAY);
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente();
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
				AlterarCliente();
				LimparCampos();
				listarValores();
			}
		});
		btnAlterar.setBounds(721, 938, 197, 40);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
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
		btnExcluir.setBounds(1022, 938, 181, 40);
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
		btnNewButton.setBounds(1502, 143, 56, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(434, 12, 1057, 132);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaCliente.class.getResource("/imagens/cliente.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(55, 130, 271, 236);
		contentPane.add(lblNewLabel_1);}

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
