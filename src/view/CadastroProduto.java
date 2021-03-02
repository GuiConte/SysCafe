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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import model.ProdutoModel;

/**
 *
 * @author Guilherme
 */
public class CadastroProduto {

    private JFrame janela;
    private JPanel painelCadastroProduto;
    private JLabel lblCodigo, lblProduto, lblPeso;
    private JTextField txtCodigo, txtProduto, txtPeso;
    private JButton btnGravar, btnCancelar;

    public void desenharTelaCadastro() throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();
        janela = new JFrame("");
        janela.setSize(310, 235);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        HashSet conj = new HashSet(janela.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        janela.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        //-------------- CRIANDO PAINEL PRINCIAL --------------------//
        painelCadastroProduto = new JPanel();
        painelCadastroProduto.setLayout(null);
        painelCadastroProduto.setBounds(0, 02, 300, 200);
        painelCadastroProduto.setBorder(BorderFactory.createCompoundBorder( //   BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                ));

        //-----------------CODIGO--------------//
        lblCodigo = new JLabel("Código: ");
        lblCodigo.setBounds(20, 20, 120, 22);
        painelCadastroProduto.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(70, 20, 80, 22);
        txtCodigo.setEnabled(false);
        painelCadastroProduto.add(txtCodigo);

        //-----------------NOME--------------//
        lblProduto = new JLabel("Produto: ");
        lblProduto.setBounds(15, 60, 120, 22);
        painelCadastroProduto.add(lblProduto);

        txtProduto = new JTextField();
        txtProduto.setBounds(70, 60, 220, 22);
        painelCadastroProduto.add(txtProduto);

        //-----------------PESO-------------//
        lblPeso = new JLabel("Peso: ");
        lblPeso.setBounds(30, 100, 120, 22);
        painelCadastroProduto.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(70, 100, 150, 22);
        painelCadastroProduto.add(txtPeso);

        btnGravar = new JButton("Gravar");
        btnGravar.setBounds(40, 160, 115, 35);
        btnGravar.setIcon(new ImageIcon(cl.getResource("Imagens/gravar.png")));
        btnGravar.setFont(new Font("Arial", 1, 13));
        btnGravar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelCadastroProduto.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(160, 160, 115, 35);
        btnCancelar.setIcon(new ImageIcon(cl.getResource("Imagens/cancelar.png")));
        btnCancelar.setFont(new Font("Arial", 1, 13));
        btnCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelCadastroProduto.add(btnCancelar);

        janela.add(painelCadastroProduto);
        janela.setVisible(true);

    }

    public void limparTxt() {
        txtProduto.setText("");
        txtPeso.setText("");
    }

    public void fecharTela() {
        janela.dispose();
    }

    public ProdutoModel getProduto() {
        int cod;
        if (txtCodigo.getText().equals("")) {
            cod = -1;
        } else {
            cod = Integer.parseInt(txtCodigo.getText());
        }
        ProdutoModel Produto = new ProdutoModel(cod,
                txtProduto.getText(),
                Float.parseFloat(txtPeso.getText())
        );

        return Produto;
    }

    public void preencheTxt(ProdutoModel Produto) {
        txtCodigo.setText("" + Produto.getCod_produto());
        txtProduto.setText(Produto.getProduto());
        txtPeso.setText("" + Produto.getPeso());
    }

    public void addGravarListener(ActionListener al) {
        btnGravar.addActionListener(al);
    }

    public void addCancelarListener(ActionListener al) {
        btnCancelar.addActionListener(al);
    }

    // public void addSairListener(ActionListener al) {
    //     btnSair.addActionListener(al);
    // }
}
