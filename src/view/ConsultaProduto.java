/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.*;

/**
 *
 * @author Guilherme
 */
public class ConsultaProduto {

    private JFrame janela;
    private JPanel painelPrincipal;
    private JLabel lblFiltrar;
    private JComboBox cbCampoPesquisa;
    private JTextField txtPesquisa;
    private JTable tblProduto;
    private JScrollPane scrollTable;
    private DefaultTableModel modelo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnSair, btnPesquisar;

    private ArrayList<ProdutoModel> Produtos;

    public void desenharTelaConsulta() throws SQLException {
        ClassLoader cl = this.getClass().getClassLoader();

        janela = new JFrame("Cadastro de Produtos");
        janela.setSize(545, 400);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setSize(530, 365);
        painelPrincipal.setLocation(5, 5);

        /*InputMap inputMap = painelPrincipal.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        ActionMap actionMap = painelPrincipal.getActionMap();
        actionMap.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });*/
        cbCampoPesquisa = new JComboBox();
        cbCampoPesquisa.setBounds(10, 15, 100, 22);
        cbCampoPesquisa.addItem("Codigo");
        cbCampoPesquisa.addItem("Produto");
        cbCampoPesquisa.addItem("Peso");
        painelPrincipal.add(cbCampoPesquisa);

        txtPesquisa = new JTextField();
        txtPesquisa.setBounds(120, 15, 250, 22);
        painelPrincipal.add(txtPesquisa);

        // ---------------- BOTAO Filtrar----//
        btnPesquisar = new JButton("Buscar");
        btnPesquisar.setBounds(380, 14, 100, 30);
        btnPesquisar.setIcon(new ImageIcon(cl.getResource("Imagens/pesquisa.png")));
        btnPesquisar.setFont(new Font("Arial", 1, 13));
        btnPesquisar.setHorizontalTextPosition(SwingConstants.RIGHT);

        painelPrincipal.add(btnPesquisar);

        // ---------------- BOTAO NOVO----//
        btnNovo = new JButton("Novo");
        btnNovo.setBounds(420, 60, 100, 30);
        btnNovo.setIcon(new ImageIcon(cl.getResource("Imagens/incluir.png")));
        btnNovo.setFont(new Font("Arial", 1, 13));
        btnNovo.setHorizontalTextPosition(SwingConstants.RIGHT);

        painelPrincipal.add(btnNovo);

        // ---------------- BOTAO ALTERAR-----//
        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(420, 95, 100, 30);
        btnAlterar.setIcon(new ImageIcon(cl.getResource("Imagens/alterar.png")));
        btnAlterar.setFont(new Font("Arial", 1, 13));
        btnAlterar.setHorizontalTextPosition(SwingConstants.RIGHT);

        painelPrincipal.add(btnAlterar);

        // ---------------- BOTAO EXCLUIR-----//
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(420, 130, 100, 30);
        btnExcluir.setIcon(new ImageIcon(cl.getResource("Imagens/excluir.png")));
        btnExcluir.setFont(new Font("Arial", 1, 13));

        painelPrincipal.add(btnExcluir);

        // ---------------- BOTAO SAIR-----//
        btnSair = new JButton("Sair");
        btnSair.setBounds(420, 165, 100, 30);
        btnSair.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSair.setFont(new Font("Arial", 1, 13));
        btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);

        painelPrincipal.add(btnSair);

        criarTabela();

        janela.add(painelPrincipal);
        janela.setVisible(true);

    }

    private void criarTabela() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.addColumn("Código");
        modelo.addColumn("Produto");
        modelo.addColumn("Peso");

        tblProduto = new JTable();
        tblProduto.setModel(modelo);
        tblProduto.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblProduto.getColumnModel().getColumn(1).setPreferredWidth(247);
        tblProduto.getColumnModel().getColumn(2).setPreferredWidth(90);

        tblProduto.getTableHeader().setResizingAllowed(false);
        tblProduto.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tblProduto.getTableHeader();
        cabecalho.setFont(new Font("Arial", 1, 11));

        DefaultTableCellRenderer centralizaColunas = new DefaultTableCellRenderer();
        centralizaColunas.setHorizontalAlignment(SwingConstants.CENTER);
        tblProduto.getColumnModel().getColumn(0).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(1).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(2).setCellRenderer(centralizaColunas);

        tblProduto.setFont(new Font("Arial", 0, 11));

        scrollTable = new JScrollPane(tblProduto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10, 60, 400, 300);
        tblProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        painelPrincipal.add(scrollTable);
    }

    public void limparTabela() {
        modelo = (DefaultTableModel) tblProduto.getModel();
        if (modelo.getRowCount() > 0) {
            int rows = modelo.getRowCount();
            for (int i = rows - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public void preencherTabela(ArrayList<ProdutoModel> Produtos) {
        this.Produtos = Produtos; //lista de Produtos para alteracao

        limparTabela();
        Iterator<ProdutoModel> it = Produtos.iterator();
        while (it.hasNext()) {
            ProdutoModel Produto = it.next();
            modelo.addRow(new Object[]{Produto.getCod_produto(), Produto.getProduto(),
                Produto.getPeso()});
        }
    }

    public boolean verificaPesquisaVazia() {
        if (!txtPesquisa.getText().equals("")) {
            return true;
        }
        return false;
    }

    public String getTxtPesquisa() {
        return txtPesquisa.getText();
    }

    public String getQueryPesquisa() {
        String query = "";
        String pesquisa = txtPesquisa.getText();

        switch (cbCampoPesquisa.getSelectedIndex()) {
            case 0:
                query = "cod_produto = '" + pesquisa + "'";
                break;
            case 1:
                query = "produto like '" + pesquisa + "'";
                break;
            case 2:
                pesquisa = tratarCamposString(pesquisa);
                query = "peso = '" + pesquisa + "'";
                break;
        }

        return query;
    }

    private String tratarCamposString(String s) {
        String aux = s.replaceAll(",", ".");
        return aux;
    }

    public void preencherTabela_Pesquisa(ArrayList<ProdutoModel> Produtos) {
        this.Produtos = Produtos; //lista de Produtos para alteracao

        limparTabela();
        if (!Produtos.isEmpty()) {
            Iterator<ProdutoModel> it = Produtos.iterator();
            while (it.hasNext()) {
                ProdutoModel Produto = it.next();
                modelo.addRow(new Object[]{Produto.getCod_produto(), Produto.getProduto(),
                    Produto.getPeso()});
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado !", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ProdutoModel getProdutoAlteracao() {
        return this.Produtos.get(tblProduto.getSelectedRow());
    }

    public boolean verificarLinhaSelecionadaTabela() {
        if (tblProduto.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public void addPesquisarListener(ActionListener al) {
        btnPesquisar.addActionListener(al);
    }

    public void addCadastrarListener(ActionListener al) {
        btnNovo.addActionListener(al);
    }

    public void addAlterarListener(ActionListener al) {
        btnAlterar.addActionListener(al);
    }

    public void addExcluirListener(ActionListener al) {
        btnExcluir.addActionListener(al);
    }

    public void addSairListener(ActionListener al) {
        btnSair.addActionListener(al);
    }

    public void fecharTela() {
        janela.dispose();
    }

    public int getCodProdutoSelecionadoTabela() {
        int id = Integer.parseInt(tblProduto.getValueAt(tblProduto.getSelectedRow(), 0).toString());
        return id;
    }

    public void addDoubleClickListener(MouseListener ml) {
        tblProduto.addMouseListener(ml);
    }

}
