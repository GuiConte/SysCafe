/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Guilherme
 */
public class CadastroVenda {

    private JFrame janela;
    private JPanel painelPrincipal, painelProduto, painelFinanceiro, painelFormaPagamento;
    private JTable tblProduto;
    private JScrollPane scrollTable;
    private DefaultTableModel modelo;
    private JLabel lblCodPedido, lblCodCliente, lblCliente, lblProduto, lblQuantidade, lblPreco, lblPesoTotal, lblTotal, lblComissao, lblValorTotal;
    private JLabel lblCGC, lblInsEstadual, lblDataVenda, lblPorcentagem, lblPrazo, lblDataVencimento, lblEntrada;
    private JTextField txtCodPedido, txtCodCliente, txtPesoTotal, txtQuantidade, txtPreco, txtTotal, txtEntrada;
    private JTextField txtValorTotal, txtCGC, txtInsEstadual, txtComissao, txtPorcentagem, txtPrazo, txtPeso;
    private JComboBox cbCliente, cbProduto;
    private JButton btnGravarProd, btnAlterarProd, btnExcluirProd, btnCancelarProd, btnFinalizar, btnSair;
    private JFormattedTextField txtDataVenda, txtDataVencimento;
    private JRadioButton rdAVista, rdPrazo;
    private ButtonGroup groupPagamento;
    private JLabel lblPeso;

    ArrayList<ClienteModel> clientes;
    ArrayList<ProdutoModel> produtos;
    ArrayList<Venda_ProdutoModel> produtosVendidos, produtosAdicionados, produtosAlterados, produtosExcluidos;

    private float peso_total, total_produto, comissao, total_venda;
    private int forma_pagamento;

    public void desenharTelaCadastro() throws ParseException {

        ClassLoader cl = this.getClass().getClassLoader();

        janela = new JFrame("Vendas");
        janela.setSize(765, 670);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        HashSet conj = new HashSet(janela.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        janela.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        //-------------- CRIANDO PAINEL PRINCIAL --------------------//
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 02, 750, 560);
        painelPrincipal.setBorder(BorderFactory.createTitledBorder(""));
        janela.add(painelPrincipal);

        //-----------------CODIGO--------------//
        lblCodPedido = new JLabel("Código Pedido ");
        lblCodPedido.setBounds(20, 10, 120, 22);
        painelPrincipal.add(lblCodPedido);

        txtCodPedido = new JTextField();
        txtCodPedido.setBounds(20, 30, 80, 22);
        txtCodPedido.setEnabled(false);
        txtCodPedido.setFocusable(false);
        painelPrincipal.add(txtCodPedido);

        //---------------DATA VENDA-----------------//
        lblDataVenda = new JLabel("Data");
        lblDataVenda.setBounds(140, 10, 100, 22);
        painelPrincipal.add(lblDataVenda);

        txtDataVenda = new JFormattedTextField(new MaskFormatter("##/##/20##"));
        txtDataVenda.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtDataVenda.setBounds(140, 30, 80, 22);
        GregorianCalendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDataVenda.setText(dateFormat.format(dataAtual.getTime()));
        painelPrincipal.add(txtDataVenda);

        //-----------------CODIGO--------------//
        lblCodCliente = new JLabel("Código ");
        lblCodCliente.setBounds(20, 60, 120, 22);
        painelPrincipal.add(lblCodCliente);

        txtCodCliente = new JTextField();
        txtCodCliente.setBounds(20, 80, 40, 23);
        txtCodCliente.setEnabled(false);
        txtCodCliente.setFocusable(false);
        painelPrincipal.add(txtCodCliente);

        //-----------------NOME--------------//
        lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(70, 60, 120, 22);
        painelPrincipal.add(lblCliente);

        cbCliente = new JComboBox();
        cbCliente.setBounds(70, 80, 250, 22);
        AutoCompleteDecorator.decorate(cbCliente);
        painelPrincipal.add(cbCliente);

        //-----------------CGC-------------//
        lblCGC = new JLabel("CNPJ");
        lblCGC.setBounds(330, 60, 120, 22);
        painelPrincipal.add(lblCGC);

        txtCGC = new JTextField();
        txtCGC.setBounds(330, 80, 150, 22);
        txtCGC.setEnabled(false);
        txtCGC.setFocusable(false);
        painelPrincipal.add(txtCGC);

        //-----------------INSCRICAO-------------//
        lblInsEstadual = new JLabel("Inscrição Estadual");
        lblInsEstadual.setBounds(490, 60, 120, 22);
        painelPrincipal.add(lblInsEstadual);

        txtInsEstadual = new JTextField();
        txtInsEstadual.setBounds(490, 80, 150, 22);
        txtInsEstadual.setEnabled(false);
        txtInsEstadual.setFocusable(false);
        painelPrincipal.add(txtInsEstadual);

        //-----------------PORCENTAGEM-------------//
        lblPorcentagem = new JLabel("Porcentagem");
        lblPorcentagem.setBounds(650, 60, 120, 22);
        painelPrincipal.add(lblPorcentagem);

        txtPorcentagem = new JTextField();
        txtPorcentagem.setBounds(650, 80, 80, 22);
        txtPorcentagem.setEnabled(false);
        txtPorcentagem.setFocusable(false);
        painelPrincipal.add(txtPorcentagem);

        //-------------- CRIANDO PAINEL --------------------//
        painelProduto = new JPanel();
        painelProduto.setLayout(null);
        painelProduto.setBounds(5, 120, 740, 300);
        painelProduto.setBorder(BorderFactory.createTitledBorder(""));
        painelPrincipal.add(painelProduto);

        //-----------------PRODUTO--------------//
        lblProduto = new JLabel("Produto");
        lblProduto.setBounds(10, 10, 120, 22);
        painelProduto.add(lblProduto);

        cbProduto = new JComboBox();
        cbProduto.setBounds(10, 30, 200, 22);
        painelProduto.add(cbProduto);

        //-----------------QUANTIDADE--------------//
        lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setBounds(220, 10, 120, 22);
        painelProduto.add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(220, 30, 80, 23);
        painelProduto.add(txtQuantidade);

        //-----------------PREÇO--------------//
        lblPreco = new JLabel("Preço Unitário");
        lblPreco.setBounds(310, 10, 120, 22);
        painelProduto.add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(310, 30, 80, 23);
        painelProduto.add(txtPreco);

        //-----------------PESO--------------//
        lblPeso = new JLabel("Peso");
        lblPeso.setBounds(400, 10, 120, 22);
        painelProduto.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(400, 30, 80, 23);
        txtPeso.setEnabled(false);
        txtPeso.setFocusable(false);
        painelProduto.add(txtPeso);

        //-----------------PESO TOTAL--------------//
        lblPesoTotal = new JLabel("Peso Total");
        lblPesoTotal.setBounds(490, 10, 120, 22);
        painelProduto.add(lblPesoTotal);

        txtPesoTotal = new JTextField();
        txtPesoTotal.setBounds(490, 30, 80, 23);
        txtPesoTotal.setEnabled(false);
        txtPesoTotal.setFocusable(false);
        painelProduto.add(txtPesoTotal);

        //-----------------PESO--------------//
        lblTotal = new JLabel("Total");
        lblTotal.setBounds(590, 10, 120, 22);
        lblTotal.setFont(new Font("Arial", 1, 15));
        lblTotal.setForeground(Color.red);
        painelProduto.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(590, 30, 80, 23);
        txtTotal.setEnabled(false);
        txtTotal.setFocusable(false);
        painelProduto.add(txtTotal);

        // ---------------- BOTAO GRAVAR PRODUTO-----//
        btnGravarProd = new JButton("Gravar");
        btnGravarProd.setBounds(20, 70, 90, 30);
        btnGravarProd.setIcon(new ImageIcon(cl.getResource("Imagens/gravar.png")));
        btnGravarProd.setFont(new Font("Arial", 1, 13));
        btnGravarProd.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelProduto.add(btnGravarProd);

        // ---------------- BOTAO CANCELAR PRODUTO-----//
        btnCancelarProd = new JButton("Cancelar");
        btnCancelarProd.setBounds(120, 70, 90, 30);
        btnCancelarProd.setIcon(new ImageIcon(cl.getResource("Imagens/cancelar.png")));
        btnCancelarProd.setFont(new Font("Arial", 1, 13));
        btnCancelarProd.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelProduto.add(btnCancelarProd);

        // ---------------- BOTAO ALTERAR PRODUTO-----//
        btnAlterarProd = new JButton("Alterar");
        btnAlterarProd.setBounds(220, 70, 90, 30);
        btnAlterarProd.setIcon(new ImageIcon(cl.getResource("Imagens/alterar.png")));
        btnAlterarProd.setFont(new Font("Arial", 1, 13));
        btnAlterarProd.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelProduto.add(btnAlterarProd);

        // ---------------- BOTAO EXCLUIR PRODUTO-----//
        btnExcluirProd = new JButton("Excluir");
        btnExcluirProd.setBounds(320, 70, 90, 30);
        btnExcluirProd.setIcon(new ImageIcon(cl.getResource("Imagens/excluir.png")));
        btnExcluirProd.setFont(new Font("Arial", 1, 13));
        btnExcluirProd.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelProduto.add(btnExcluirProd);

        painelFinanceiro = new JPanel();
        painelFinanceiro.setLayout(null);
        painelFinanceiro.setBounds(5, 415, 740, 145);
        painelFinanceiro.setBorder(BorderFactory.createTitledBorder(""));

        painelFormaPagamento = new JPanel();
        painelFormaPagamento.setLayout(null);
        painelFormaPagamento.setBounds(20, 15, 190, 50);
        painelFormaPagamento.setBorder(BorderFactory.createTitledBorder("Forma de Pagamento"));

        rdAVista = new JRadioButton("A Vista");
        rdAVista.setBounds(5, 20, 80, 17);
        rdAVista.setFont(new Font("Arial", 0, 13));
        rdAVista.setSelected(true);
        rdAVista.setFocusable(false);
        painelFormaPagamento.add(rdAVista);

        rdPrazo = new JRadioButton("A Prazo");
        rdPrazo.setBounds(90, 20, 80, 17);
        rdPrazo.setFont(new Font("Arial", 0, 13));
        rdPrazo.setFocusable(false);
        painelFormaPagamento.add(rdPrazo);

        groupPagamento = new ButtonGroup();
        groupPagamento.add(rdAVista);
        groupPagamento.add(rdPrazo);

        painelFinanceiro.add(painelFormaPagamento);
        

        //-----------------PRAZO--------------//
        lblPrazo = new JLabel("Prazo");
        lblPrazo.setBounds(230, 15, 120, 22);
        painelFinanceiro.add(lblPrazo);

        txtPrazo = new JTextField();
        txtPrazo.setBounds(230, 35, 60, 23);
        txtPrazo.setEnabled(false);
        txtPrazo.setFocusable(false);
        painelFinanceiro.add(txtPrazo);

        //---------------DATA VENDA-----------------//
        lblDataVencimento = new JLabel("Vencimento");
        lblDataVencimento.setBounds(310, 15, 140, 22);
        painelFinanceiro.add(lblDataVencimento);

        txtDataVencimento = new JFormattedTextField(new MaskFormatter("##/##/20##"));
        txtDataVencimento.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtDataVencimento.setBounds(310, 35, 80, 22);
        painelFinanceiro.add(txtDataVencimento);

        //-----------------ENTRADA--------------//
        lblEntrada = new JLabel("Entrada");
        lblEntrada.setBounds(410, 15, 120, 22);
        painelFinanceiro.add(lblEntrada);

        txtEntrada = new JTextField();
        txtEntrada.setBounds(410, 35, 100, 22);
        painelFinanceiro.add(txtEntrada);

        lblComissao = new JLabel("Comissão");
        lblComissao.setBounds(470, 75, 120, 22);
        lblComissao.setFont(new Font("Arial", 1, 15));
        lblComissao.setForeground(Color.red);
        painelFinanceiro.add(lblComissao);

        txtComissao = new JTextField();
        txtComissao.setBounds(470, 95, 120, 28);
        txtComissao.setFont(new Font("Arial", 1, 18));
        txtComissao.setEditable(false);
        txtComissao.setFocusable(false);
        painelFinanceiro.add(txtComissao);

        //-----------------TOTAL VENDA--------------//
        lblValorTotal = new JLabel("Total Venda");
        lblValorTotal.setBounds(610, 75, 120, 22);
        lblValorTotal.setFont(new Font("Arial", 1, 15));
        lblValorTotal.setForeground(Color.blue);
        painelFinanceiro.add(lblValorTotal);

        txtValorTotal = new JTextField();
        txtValorTotal.setBounds(610, 95, 120, 28);
        txtValorTotal.setFont(new Font("Arial", 1, 18));
        txtValorTotal.setEditable(false);
        txtValorTotal.setFocusable(false);
        painelFinanceiro.add(txtValorTotal);
        
        painelPrincipal.add(painelFinanceiro);

        // ---------------- BOTAO FINALIZAR-----//
        btnFinalizar = new JButton("Finalizar Venda");
        btnFinalizar.setBounds(350, 580, 150, 35);
        btnFinalizar.setIcon(new ImageIcon(cl.getResource("Imagens/dinheiro.png")));
        btnFinalizar.setFont(new Font("Arial", 1, 13));
        btnFinalizar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnFinalizar.addActionListener((java.awt.event.ActionEvent evt) -> {

        });
        janela.add(btnFinalizar);

        // ---------------- BOTAO FINALIZAR-----//
        btnSair = new JButton("Sair");
        btnSair.setBounds(520, 580, 90, 35);
        btnSair.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSair.setFont(new Font("Arial", 1, 13));
        btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSair.addActionListener((java.awt.event.ActionEvent evt) -> {

        });
        janela.add(btnSair);

        criarTabela();
        janela.add(painelPrincipal);
        janela.setVisible(true);
        txtCodPedido.grabFocus();

    }

    private void criarTabela() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Preço");
        modelo.addColumn("Peso Total");
        modelo.addColumn("Valor");

        tblProduto = new JTable();
        tblProduto.setFocusable(false);
        tblProduto.setModel(modelo);
        tblProduto.getColumnModel().getColumn(0).setPreferredWidth(298);
        tblProduto.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblProduto.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblProduto.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblProduto.getColumnModel().getColumn(4).setPreferredWidth(110);

        tblProduto.getTableHeader().setResizingAllowed(false);
        tblProduto.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tblProduto.getTableHeader();
        cabecalho.setFont(new Font("Arial", 1, 11));

        DefaultTableCellRenderer centralizaColunas = new DefaultTableCellRenderer();
        centralizaColunas.setHorizontalAlignment(SwingConstants.CENTER);
        tblProduto.getColumnModel().getColumn(0).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(1).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(2).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(3).setCellRenderer(centralizaColunas);
        tblProduto.getColumnModel().getColumn(4).setCellRenderer(centralizaColunas);

        tblProduto.setFont(new Font("Arial", 0, 11));

        scrollTable = new JScrollPane(tblProduto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10, 110, 720, 180);
        tblProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        painelProduto.add(scrollTable);
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

    public void setProdutosVendidos(ArrayList<Venda_ProdutoModel> produtos) {
        this.produtosVendidos = produtos;
        preencherTabela();
    }

    public void preencherTabela() {
        DecimalFormat dfPesoTotal, dfValorTotal;
        BigDecimal bdValorTotal;
        Iterator<Venda_ProdutoModel> it = produtosVendidos.iterator();
        dfPesoTotal = new DecimalFormat("0.000 KG");
        dfValorTotal = new DecimalFormat("R$ #.00");

        while (it.hasNext()) {
            Venda_ProdutoModel produto = it.next();
            bdValorTotal = new BigDecimal(produto.getValor()).setScale(2, RoundingMode.HALF_EVEN);
            modelo.addRow(new Object[]{produto.getProduto(), produto.getQuantidade(), produto.getPreco(),
                dfPesoTotal.format(produto.getPeso_total()), dfValorTotal.format(bdValorTotal.floatValue())});
        }
    }

    public void iniciarArrays() {
        clientes = new ArrayList();
        produtos = new ArrayList();
        produtosVendidos = new ArrayList();
        produtosAdicionados = new ArrayList();
        produtosAlterados = new ArrayList();
        produtosExcluidos = new ArrayList();
    }

    public void preencherComboCliente(ArrayList<ClienteModel> clientes) {
        Iterator<ClienteModel> it = clientes.iterator();
        while (it.hasNext()) {
            ClienteModel cliente = it.next();
            this.clientes.add(new ClienteModel(cliente.getCod_cliente(),
                    cliente.getNome(),
                    cliente.getContato(),
                    cliente.getCidade(),
                    cliente.getEndereco(),
                    cliente.getEstado(),
                    cliente.getCep(),
                    cliente.getTelefone(),
                    cliente.getPorcentagem(),
                    cliente.getPrazo(),
                    cliente.getCgc(),
                    cliente.getInscricao_est()
            ));

            cbCliente.addItem(cliente.getNome());
        }
    }

    public void preencherComboProduto(ArrayList<ProdutoModel> produtos) {
        Iterator<ProdutoModel> it = produtos.iterator();
        while (it.hasNext()) {
            ProdutoModel produto = it.next();
            this.produtos.add(new ProdutoModel(produto.getCod_produto(),
                    produto.getProduto(),
                    produto.getPeso()
            ));

            cbProduto.addItem(produto.getProduto());
        }
    }

    public void selecionarCliente() {
        int index = cbCliente.getSelectedIndex();
        txtCodCliente.setText("" + clientes.get(index).getCod_cliente());
        txtCGC.setText(clientes.get(index).getCgc());
        txtInsEstadual.setText(clientes.get(index).getInscricao_est());
        txtPorcentagem.setText("" + clientes.get(index).getPorcentagem() + " %");
        txtPrazo.setText("" + clientes.get(index).getPrazo() + " Dias");

    }

    public void selecionarProduto() {
        int index = cbProduto.getSelectedIndex();
        DecimalFormat df = new DecimalFormat("0.000 KG");
        txtPeso.setText(df.format(produtos.get(index).getPeso()));
    }

    public void selecionarFormaPagamento() throws ParseException {
        DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
        Date dataVenda = conversor.parse(txtDataVenda.getText());
        int index = cbCliente.getSelectedIndex();
        SimpleDateFormat dateFormat;
        GregorianCalendar dataVencimento = new GregorianCalendar();
        dataVencimento.setTime(dataVenda);
        if (rdAVista.isSelected()) {
            txtPrazo.setText("");
            forma_pagamento = 0;
            txtEntrada.setEnabled(false);
            txtEntrada.setText("");
        } else if (rdPrazo.isSelected()) {
            txtPrazo.setText("" + clientes.get(index).getPrazo() + " Dias");
            int prazo = clientes.get(index).getPrazo();
            dataVencimento.add(Calendar.DAY_OF_MONTH, prazo);
            forma_pagamento = 1;
            txtEntrada.setEnabled(true);
            txtEntrada.setText("0.00");
        }
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDataVencimento.setText(dateFormat.format(dataVencimento.getTime()));
    }

    public int retornarIndiceClienteSelecionado() {
        return cbCliente.getSelectedIndex();
    }

    public void addComboClienteListener(ItemListener il) {
        cbCliente.addItemListener(il);
    }

    public void addComboProdutoListener(ItemListener il) {
        cbProduto.addItemListener(il);
    }

    public void addTxtFocusListener(FocusListener fl) {
        txtQuantidade.addFocusListener(fl);
        txtPreco.addFocusListener(fl);
    }

    public void addTxtDataFocusListener(FocusListener fl) {
        txtDataVenda.addFocusListener(fl);
    }

    public void calcularCamposProduto() {
        DecimalFormat df;
        int index = cbProduto.getSelectedIndex();
        if (!txtPreco.getText().equals("") && !txtQuantidade.getText().equals("")) {
            peso_total = produtos.get(index).getPeso() * Float.parseFloat(txtQuantidade.getText());
            df = new DecimalFormat("0.000 KG");
            txtPesoTotal.setText(df.format(peso_total));

            total_produto = peso_total * Float.parseFloat(txtPreco.getText());
            BigDecimal bdTotalProd = new BigDecimal(total_produto).setScale(2, RoundingMode.HALF_EVEN);
            df = new DecimalFormat("R$ #.00");
            txtTotal.setText(df.format(bdTotalProd.floatValue()));
        }
    }

    public void calculaTotal_Comissao() {
        int index = cbCliente.getSelectedIndex();
        float totalVenda = 0;
        float comissao = 0, porcentagem = 0;
        BigDecimal bdTotal, bdComissao;
        DecimalFormat df = new DecimalFormat("R$ 0.00");
        Iterator<Venda_ProdutoModel> it = produtosVendidos.iterator();
        while (it.hasNext()) {
            Venda_ProdutoModel prodVend = it.next();
            totalVenda += prodVend.getValor();
        }

        bdTotal = new BigDecimal(totalVenda).setScale(2, RoundingMode.HALF_EVEN);
        txtValorTotal.setText(df.format(bdTotal.floatValue()));
        total_venda = bdTotal.floatValue();

        porcentagem = clientes.get(index).getPorcentagem();
        porcentagem = porcentagem / 100;
        comissao = totalVenda * porcentagem;
        bdComissao = new BigDecimal(comissao).setScale(2, RoundingMode.HALF_EVEN);
        txtComissao.setText(df.format(bdComissao.floatValue()));
        this.comissao = bdComissao.floatValue();
    }

    private String tratarCamposString(String s) {
        String aux = s.replaceAll(",", ".");
        return aux;
    }

    public void atualizarTxts() {
        txtQuantidade.setText(tratarCamposString(txtQuantidade.getText()));
        txtPreco.setText(tratarCamposString(txtPreco.getText()));
    }

    public void adicionarProduto() {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            produtosVendidos.add(new Venda_ProdutoModel(cbProduto.getSelectedIndex() + 1,
                    cbProduto.getSelectedItem().toString(),
                    Float.parseFloat(txtQuantidade.getText()),
                    Float.parseFloat(txtPreco.getText()),
                    peso_total,
                    total_produto
            ));
            modelo.addRow(new Object[]{cbProduto.getSelectedItem(), txtQuantidade.getText(), txtPreco.getText(),
                txtPesoTotal.getText(), txtTotal.getText()});
        }
    }

    public void alteracaoProdutos_adicionar(Venda_ProdutoModel produto) {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            produtosAdicionados.add(produto);
        }
    }

    public void alteracaoProdutos_alterar(Venda_ProdutoModel produto) {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            produtosAlterados.add(produto);
        }
    }

    public void alteracaoProdutos_excluir(Venda_ProdutoModel produto) {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            produtosExcluidos.add(produto);
        }
    }

    public Venda_ProdutoModel getProduto() {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            return new Venda_ProdutoModel(cbProduto.getSelectedIndex() + 1,
                    cbProduto.getSelectedItem().toString(),
                    Float.parseFloat(txtQuantidade.getText()),
                    Float.parseFloat(txtPreco.getText()),
                    peso_total,
                    total_produto
            );
        }
        return null;
    }

    public Venda_ProdutoModel getProdutoAlterado() {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            return new Venda_ProdutoModel(produtosVendidos.get(tblProduto.getSelectedRow()).getCod_venda_produto(),
                    cbProduto.getSelectedIndex() + 1,
                    cbProduto.getSelectedItem().toString(),
                    Float.parseFloat(txtQuantidade.getText()),
                    Float.parseFloat(txtPreco.getText()),
                    peso_total,
                    total_produto
            );
        }
        return null;
    }

    public Venda_ProdutoModel getProdutoExcluido() {
        if (!txtQuantidade.getText().equals("") && !txtPreco.getText().equals("")) {
            return new Venda_ProdutoModel(produtosVendidos.get(tblProduto.getSelectedRow()).getCod_venda_produto(),
                    cbProduto.getSelectedIndex() + 1,
                    cbProduto.getSelectedItem().toString(),
                    Float.parseFloat(txtQuantidade.getText()),
                    Float.parseFloat(txtPreco.getText()),
                    peso_total,
                    total_produto
            );
        }
        return null;
    }

    public int obterCodVenda() {
        return Integer.parseInt(txtCodPedido.getText());
    }

    public ArrayList<Venda_ProdutoModel> retornarProdutosVendidos() {
        return produtosVendidos;
    }

    public void alterarProduto() {
        if (tblProduto.getSelectedRow() != -1) {
            produtosVendidos.set(tblProduto.getSelectedRow(), new Venda_ProdutoModel(cbProduto.getSelectedIndex() + 1,
                    cbProduto.getSelectedItem().toString(),
                    Float.parseFloat(txtQuantidade.getText()),
                    Float.parseFloat(txtPreco.getText()),
                    peso_total,
                    total_produto
            ));
        }
    }

    public void excluirProduto() {
        if (tblProduto.getSelectedRow() != -1) {
            produtosVendidos.remove(tblProduto.getSelectedRow());
            modelo.removeRow(tblProduto.getSelectedRow());
        }
    }

    public void limpaTxtProduto() {
        txtQuantidade.setText("");
        txtPreco.setText("");
        txtPesoTotal.setText("");
        txtTotal.setText("");
    }

    public void preencheTxtProduto() {
        int indiceLinha = tblProduto.getSelectedRow();

        if (indiceLinha != -1) {
            for (int i = 0; i < produtos.size(); i++) {
                if (tblProduto.getValueAt(indiceLinha, 0).toString().equals(produtos.get(i).getProduto())) {
                    cbProduto.setSelectedIndex(i);
                    break;
                }
            }
            txtQuantidade.setText(tblProduto.getValueAt(indiceLinha, 1).toString());
            txtPreco.setText(tblProduto.getValueAt(indiceLinha, 2).toString());
            txtPesoTotal.setText(tblProduto.getValueAt(indiceLinha, 3).toString());
            txtTotal.setText(tblProduto.getValueAt(indiceLinha, 4).toString());

        }
    }

    public void desabilitarBotaoGravarProd() {
        btnGravarProd.setEnabled(false);
    }

    public void habilitarBotaoGravarProd() {
        btnGravarProd.setEnabled(true);
    }

    public boolean verificaProdutosVendidos() {
        return produtosVendidos.isEmpty();
    }

    public VendaModel getVenda() throws ParseException {
        int id;
        float entrada = 0;
        if (txtCodPedido.getText().equals("")) {
            id = 0;
        } else {
            id = Integer.parseInt(txtCodPedido.getText());
        }

        if (txtEntrada.getText().equals("")) {
            entrada = 0;
        } else {
            entrada = Float.parseFloat(tratarCamposString(txtEntrada.getText()));
        }
        DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
        Date dataVenda = conversor.parse(txtDataVenda.getText());
        Date dataVencimento = conversor.parse(txtDataVencimento.getText());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new VendaModel(id,
                dateFormat.format(dataVenda),
                clientes.get(cbCliente.getSelectedIndex()).getCod_cliente(),
                forma_pagamento,
                dateFormat.format(dataVencimento),
                comissao,
                total_venda,
                entrada
        );
    }

    public ArrayList<Venda_ProdutoModel> getProdutosVendidos() {
        return produtosVendidos;
    }

    public ArrayList<Venda_ProdutoModel> getProdutosAdicionados() {
        return produtosAdicionados;
    }

    public ArrayList<Venda_ProdutoModel> getProdutosAlterados() {
        return produtosAlterados;
    }

    public ArrayList<Venda_ProdutoModel> getProdutosExcluidos() {
        return produtosExcluidos;
    }

    public boolean verificarIndiceSelecionadoTabela() {
        if (tblProduto.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public boolean verificaProdutosAdicionados() {
        if (produtosVendidos.isEmpty()) {
            return false;
        }
        return true;
    }

    public void preencheTxt(VendaModel venda) throws ParseException {
        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVenda = conversor.parse(venda.getData_venda());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        txtCodPedido.setText("" + venda.getCod_pedido());

        txtDataVenda.setText(dateFormat.format(dataVenda));

        for (int i = 0; i < clientes.size(); i++) {
            if (venda.getCod_cliente() == clientes.get(i).getCod_cliente()) {
                cbCliente.setSelectedIndex(i);
                break;
            }
        }

        if (venda.getForma_pagamento() == 0) {
            rdAVista.setSelected(true);
        } else if (venda.getForma_pagamento() == 1) {
            rdPrazo.setSelected(true);
        }

        Date dataVencimento = conversor.parse(venda.getData_vencimento());
        txtDataVencimento.setText(dateFormat.format(dataVencimento));

        BigDecimal bdTotal, bdComissao;
        DecimalFormat df = new DecimalFormat("R$ 0.00");

        bdComissao = new BigDecimal(venda.getComissao()).setScale(2, RoundingMode.HALF_EVEN);
        txtComissao.setText(df.format(bdComissao.floatValue()));
        this.comissao = bdComissao.floatValue();

        bdTotal = new BigDecimal(venda.getTotal_venda()).setScale(2, RoundingMode.HALF_EVEN);
        txtValorTotal.setText(df.format(bdTotal.floatValue()));
        this.total_venda = bdTotal.floatValue();

    }

    public void ajustarCamposFinanceiro(VendaModel venda) throws ParseException {
        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVencimento = conversor.parse(venda.getData_vencimento());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDataVencimento.setText(dateFormat.format(dataVencimento));

        txtEntrada.setText("" + venda.getValor_entrada());
    }

    public void fecharTela() {
        janela.dispose();
    }

    public void inserirDataFixaVenda(String dataFixaVenda) {
        txtDataVenda.setText(dataFixaVenda);
        txtDataVencimento.setText(dataFixaVenda);
    }

    public void addGravarProdutoListener(ActionListener al) {
        btnGravarProd.addActionListener(al);
    }

    public void addCancelarProdutoListener(ActionListener al) {
        btnCancelarProd.addActionListener(al);
    }

    public void addAlterarProdutoListener(ActionListener al) {
        btnAlterarProd.addActionListener(al);
    }

    public void addExcluirProdutoListener(ActionListener al) {
        btnExcluirProd.addActionListener(al);
    }

    public void addClickTableListener(MouseListener ml) {
        tblProduto.addMouseListener(ml);
    }

    public void addCheckFormaPagamentoListener(ActionListener al) {
        rdAVista.addActionListener(al);
        rdPrazo.addActionListener(al);
    }

    public void addFinalizarVendaListener(ActionListener al) {
        btnFinalizar.addActionListener(al);
    }

    public void addSairListener(ActionListener al) {
        btnSair.addActionListener(al);
    }

    public void addKeyPressedListener(KeyListener kl) {
        txtDataVenda.addKeyListener(kl);
        cbCliente.addKeyListener(kl);
        cbProduto.addKeyListener(kl);
        txtQuantidade.addKeyListener(kl);
        txtPreco.addKeyListener(kl);
        txtDataVencimento.addKeyListener(kl);
    }

}
