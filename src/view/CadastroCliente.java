/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import model.ClienteModel;

/**
 *
 * @author Guilherme
 */
public class CadastroCliente {

    private JFrame janela;
    private JPanel painelCadastroCliente;
    private JLabel lblTitulo;
    private JLabel lblCodigo, lblEmpresa, lblContato, lblCidade, lblEndereco, lblEstado, lblCEP, lblTelefone, lblPorcentagem, lblPrazo, lblCGC, lblInscricaoEst;
    private JTextField txtCodigo,txtEmpresa, txtContato, txtCidade, txtEndereco, txtEstado, txtTelefone, txtPorcentagem, txtPrazo, txtInscricaoEst;
    private JFormattedTextField txtCGC, txtCEP;
    private JButton btnGravar, btnCancelar, btnSair;

    public void desenharTelaCadastro() throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();
        janela = new JFrame("Cadastro Cliente");
        janela.setSize(600, 600);
        janela.setUndecorated(true);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        HashSet conj = new HashSet(janela.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        janela.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        painelCadastroCliente = new JPanel();
        painelCadastroCliente.setLayout(null);
        painelCadastroCliente.setSize(600, 600);
        painelCadastroCliente.setLocation(0, 0);

        lblTitulo = new JLabel("Cadastro de Cliente");
        lblTitulo.setBounds(110, 20, 400, 50);
        lblTitulo.setFont(new Font("Arial", 1, 40));

        painelCadastroCliente.add(lblTitulo);

        //======================================================================
        lblCodigo = new JLabel("Código do Cliente:");
        lblCodigo.setBounds(120, 110, 150, 40);
        lblCodigo.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblCodigo);

        lblEmpresa = new JLabel("Nome da Empresa:");
        lblEmpresa.setBounds(120, 140, 150, 40);
        lblEmpresa.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblEmpresa);

        lblContato = new JLabel("Nome do Contato:");
        lblContato.setBounds(120, 170, 150, 40);
        lblContato.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblContato);

        lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(120, 200, 150, 40);
        lblCidade.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblCidade);

        lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(120, 230, 150, 40);
        lblEndereco.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblEndereco);

        lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(120, 260, 150, 40);
        lblEstado.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblEstado);

        lblCEP = new JLabel("CEP:");
        lblCEP.setBounds(120, 290, 150, 40);
        lblCEP.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblCEP);

        lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(120, 320, 150, 40);
        lblTelefone.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblTelefone);

        lblPorcentagem = new JLabel("Porcentagem:");
        lblPorcentagem.setBounds(120, 350, 150, 40);
        lblPorcentagem.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblPorcentagem);

        lblPrazo = new JLabel("Prazo:");
        lblPrazo.setBounds(120, 380, 150, 40);
        lblPrazo.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblPrazo);

        lblCGC = new JLabel("CNPJ:");
        lblCGC.setBounds(120, 410, 150, 40);
        lblCGC.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblCGC);

        lblInscricaoEst = new JLabel("Ins Estadual:");
        lblInscricaoEst.setBounds(120, 440, 150, 40);
        lblInscricaoEst.setFont(new Font("Arial", 1, 13));
        painelCadastroCliente.add(lblInscricaoEst);

        //======================================================================
        txtCodigo = new JTextField();
        txtCodigo.setBounds(300, 117, 180, 25);
        txtCodigo.setEditable(false);
        painelCadastroCliente.add(txtCodigo);

        txtEmpresa = new JTextField();
        txtEmpresa.setBounds(300, 177, 180, 25);
        painelCadastroCliente.add(txtEmpresa);

        txtContato = new JTextField();
        txtContato.setBounds(300, 147, 180, 25);
        painelCadastroCliente.add(txtContato);

        txtCidade = new JTextField();
        txtCidade.setBounds(300, 207, 180, 25);
        painelCadastroCliente.add(txtCidade);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(300, 237, 180, 25);
        painelCadastroCliente.add(txtEndereco);

        txtEstado = new JTextField();
        txtEstado.setBounds(300, 267, 180, 25);
        painelCadastroCliente.add(txtEstado);

        txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
        txtCEP.setBounds(300, 297, 180, 25);
        painelCadastroCliente.add(txtCEP);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(300, 327, 180, 25);
        painelCadastroCliente.add(txtTelefone);

        txtPorcentagem = new JTextField();
        txtPorcentagem.setBounds(300, 357, 180, 25);
        txtPorcentagem.setText("2");
        painelCadastroCliente.add(txtPorcentagem);

        txtPrazo = new JTextField();
        txtPrazo.setBounds(300, 387, 180, 25);
        txtPrazo.setText("28");
        painelCadastroCliente.add(txtPrazo);

        txtCGC = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        txtCGC.setBounds(300, 417, 180, 25);
        painelCadastroCliente.add(txtCGC);

        txtInscricaoEst = new JTextField();
        txtInscricaoEst.setBounds(300, 447, 180, 25);
        painelCadastroCliente.add(txtInscricaoEst);

        //======================================================================
        btnGravar = new JButton("Gravar");
        btnGravar.setBounds(70, 500, 120, 50);
        btnGravar.setIcon(new ImageIcon(cl.getResource("Imagens/gravar.png")));
        btnGravar.setFont(new Font("Arial", 1, 13));
        btnGravar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelCadastroCliente.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(240, 500, 120, 50);
        btnCancelar.setIcon(new ImageIcon(cl.getResource("Imagens/cancelar.png")));
        btnCancelar.setFont(new Font("Arial", 1, 13));
        btnCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelCadastroCliente.add(btnCancelar);

        btnSair = new JButton("Sair");
        btnSair.setBounds(410, 500, 120, 50);
        btnSair.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSair.setFont(new Font("Arial", 1, 13));
        btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelCadastroCliente.add(btnSair);

        janela.add(painelCadastroCliente);
        janela.setVisible(true);
    }

    public void limparTxt() {
        txtEmpresa.setText("");
        txtContato.setText("");
        txtCidade.setText("");
        txtEndereco.setText("");
        txtEstado.setText("");
        txtTelefone.setText("");
        txtPorcentagem.setText("2");
        txtPrazo.setText("28");
        txtCGC.setText("");
        txtInscricaoEst.setText("");
        txtCEP.setText("");
    }

    public void fecharTela() {
        janela.dispose();
    }

    public ClienteModel getCliente() {
        int cod;
        if (txtCodigo.getText().equals("")) {
            cod = -1;
        } else {
            cod = Integer.parseInt(txtCodigo.getText());
        }
        ClienteModel cliente = new ClienteModel(cod,
                txtEmpresa.getText(),
                txtContato.getText(),
                txtCidade.getText(),
                txtEndereco.getText(),
                txtEstado.getText(),
                txtCEP.getText(),
                txtTelefone.getText(),
                Integer.parseInt(txtPorcentagem.getText()),
                Integer.parseInt(txtPrazo.getText()),
                txtCGC.getText(),
                txtInscricaoEst.getText()
        );

        return cliente;
    }

    public void preencheTxt(ClienteModel cliente) {
        txtCodigo.setText("" + cliente.getCod_cliente());
        txtEmpresa.setText(cliente.getNome());
        txtContato.setText(cliente.getContato());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtEstado.setText(cliente.getEstado());
        txtTelefone.setText(cliente.getTelefone());
        txtPorcentagem.setText("" + cliente.getPorcentagem());
        txtPrazo.setText("" + cliente.getPrazo());
        txtCGC.setText(cliente.getCgc());
        txtInscricaoEst.setText(cliente.getInscricao_est());
    }

    public boolean validadorCamposCliente() {
        if (txtEmpresa.getText().equals("") || txtPorcentagem.getText().equals("") || txtPrazo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios !\nNome Empresa, Prazo, Porcentagem", "Preencha os campos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void addGravarListener(ActionListener al) {
        btnGravar.addActionListener(al);
    }

    public void addCancelarListener(ActionListener al) {
        btnCancelar.addActionListener(al);
    }

    public void addSairListener(ActionListener al) {
        btnSair.addActionListener(al);
    }
}
