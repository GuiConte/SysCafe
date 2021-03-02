/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Guilherme
 */
import model.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

public class ConsultaVenda {

    private JFrame janela;
    private JPanel PainelPesquisa;
    private JTable tblVenda;
    private JScrollPane scrollTable;
    private DefaultTableModel modelo;
    private JFormattedTextField txtPesquisa,txtDataFixaVenda;
    private JButton btnNovo, btnAlterar, btnExcluir, btnSairP, btnPesquisar;
    private JComboBox cbCampoPesquisa;
    private JLabel lblDataFixaVenda;

    ArrayList<VendaModel> vendas;

    public void desenharTelaConsulta() throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();
        janela = new JFrame("Consulta Vendas");
        janela.setSize(740, 610);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        //-------------- CRIANDO PAINEL PESQUISA --------------------//
        PainelPesquisa = new JPanel();
        PainelPesquisa.setLayout(null);
        PainelPesquisa.setBounds(0, 02, 730, 570);
        PainelPesquisa.setBorder(BorderFactory.createTitledBorder(""));

        //-----------------NOME--------------//
        cbCampoPesquisa = new JComboBox();
        cbCampoPesquisa.setBounds(60, 40, 150, 30);
        cbCampoPesquisa.addItem("Cod. Venda");
        cbCampoPesquisa.addItem("Cliente");
        cbCampoPesquisa.addItem("Data Venda");
        cbCampoPesquisa.addItem("Data Vencimento");
        cbCampoPesquisa.addItem("Valor");
        PainelPesquisa.add(cbCampoPesquisa);

        txtPesquisa = new JFormattedTextField();
        txtPesquisa.setBounds(230, 40, 270, 30);
        PainelPesquisa.add(txtPesquisa);
        
        lblDataFixaVenda = new JLabel("Data Venda: ");
        lblDataFixaVenda.setBounds(10, 480, 80, 30);
        PainelPesquisa.add(lblDataFixaVenda);
        txtDataFixaVenda = new JFormattedTextField(new MaskFormatter("##/##/20##"));
        txtDataFixaVenda.setBounds(90,480,100,30);
        PainelPesquisa.add(txtDataFixaVenda);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(520, 40, 120, 30);
        btnPesquisar.setIcon(new ImageIcon(cl.getResource("Imagens/pesquisa.png")));
        btnPesquisar.setFont(new Font("Arial", 1, 13));
        btnPesquisar.setHorizontalTextPosition(SwingConstants.RIGHT);
        PainelPesquisa.add(btnPesquisar);

        // ---------------- BOTAO NOVO-----//
        btnNovo = new JButton("Novo");
        btnNovo.setBounds(150, 530, 90, 35);
        btnNovo.setIcon(new ImageIcon(cl.getResource("Imagens/incluir.png")));
        btnNovo.setFont(new Font("Arial", 1, 13));
        btnNovo.setHorizontalTextPosition(SwingConstants.RIGHT);
        PainelPesquisa.add(btnNovo);

        // ---------------- BOTAO ALTERAR-----//
        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(250, 530, 90, 35);
        btnAlterar.setIcon(new ImageIcon(cl.getResource("Imagens/alterar.png")));
        btnAlterar.setFont(new Font("Arial", 1, 13));
        btnAlterar.setHorizontalTextPosition(SwingConstants.RIGHT);

        PainelPesquisa.add(btnAlterar);

        // ---------------- BOTAO GRAVAR PRODUTO-----//
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(350, 530, 90, 35);
        btnExcluir.setIcon(new ImageIcon(cl.getResource("Imagens/excluir.png")));
        btnExcluir.setFont(new Font("Arial", 1, 13));
        btnExcluir.setHorizontalTextPosition(SwingConstants.RIGHT);

        PainelPesquisa.add(btnExcluir);

        // ---------------- BOTAO GRAVAR PRODUTO-----//
        btnSairP = new JButton("Sair");
        btnSairP.setBounds(450, 530, 90, 35);
        btnSairP.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSairP.setFont(new Font("Arial", 1, 13));
        btnSairP.setHorizontalTextPosition(SwingConstants.RIGHT);

        PainelPesquisa.add(btnSairP);

        criarTabelaPesquisa();
        janela.add(PainelPesquisa);
        janela.setVisible(true);
    }

    private void criarTabelaPesquisa() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.addColumn("Cod. Venda");
        modelo.addColumn("Cliente");
        modelo.addColumn("Data Venda");
        modelo.addColumn("Data Vencimento");
        modelo.addColumn("Valor Total");

        tblVenda = new JTable();
        tblVenda.setModel(modelo);
        tblVenda.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblVenda.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblVenda.getColumnModel().getColumn(2).setPreferredWidth(125);
        tblVenda.getColumnModel().getColumn(3).setPreferredWidth(125);
        tblVenda.getColumnModel().getColumn(4).setPreferredWidth(125);

        tblVenda.getTableHeader().setResizingAllowed(false);
        tblVenda.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tblVenda.getTableHeader();
        cabecalho.setFont(new Font("Arial", 1, 12));

        DefaultTableCellRenderer centralizaColunas = new DefaultTableCellRenderer();
        centralizaColunas.setHorizontalAlignment(SwingConstants.CENTER);
        tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizaColunas);
        tblVenda.getColumnModel().getColumn(1).setCellRenderer(centralizaColunas);
        tblVenda.getColumnModel().getColumn(2).setCellRenderer(centralizaColunas);
        tblVenda.getColumnModel().getColumn(3).setCellRenderer(centralizaColunas);
        tblVenda.getColumnModel().getColumn(4).setCellRenderer(centralizaColunas);

        tblVenda.setFont(new Font("Arial", 0, 12));

        scrollTable = new JScrollPane(tblVenda, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10, 110, 705, 360);
        tblVenda.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PainelPesquisa.add(scrollTable);
    }

    public void preencherTabela(ArrayList<VendaModel> vendas) throws ParseException {
        this.vendas = vendas; //lista de vendas para alteracao

        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVenda, dataVencimento;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        BigDecimal bdTotalVenda;
        DecimalFormat df = new DecimalFormat("R$ 0.00");

        limparTabela();
        Iterator<VendaModel> it = vendas.iterator();
        while (it.hasNext()) {
            VendaModel venda = it.next();
            dataVenda = conversor.parse(venda.getData_venda());
            dataVencimento = conversor.parse(venda.getData_vencimento());
            bdTotalVenda = new BigDecimal(venda.getTotal_venda()).setScale(2, RoundingMode.HALF_EVEN);
            modelo.addRow(new Object[]{venda.getCod_pedido(), venda.getNome_cliente(),
                dateFormat.format(dataVenda), dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue())});
        }
    }

    public void preencherTabela_Pesquisa(ArrayList<VendaModel> vendas) throws ParseException {
        this.vendas = vendas; //lista de vendas para alteracao

        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVenda, dataVencimento;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        BigDecimal bdTotalVenda;
        DecimalFormat df = new DecimalFormat("R$ 0.00");

        limparTabela();
        if (!vendas.isEmpty()) {
            Iterator<VendaModel> it = vendas.iterator();
            while (it.hasNext()) {
                VendaModel venda = it.next();
                dataVenda = conversor.parse(venda.getData_venda());
                dataVencimento = conversor.parse(venda.getData_vencimento());
                bdTotalVenda = new BigDecimal(venda.getTotal_venda()).setScale(2, RoundingMode.HALF_EVEN);
                modelo.addRow(new Object[]{venda.getCod_pedido(), venda.getNome_cliente(),
                    dateFormat.format(dataVenda), dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue())});
            }
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada !", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void limparTabela() {
        modelo = (DefaultTableModel) tblVenda.getModel();
        if (modelo.getRowCount() > 0) {
            int rows = modelo.getRowCount();
            for (int i = rows - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public void fecharTela() {
        janela.dispose();
    }

    public boolean verificarLinhaSelecionadaTabela() {
        if (tblVenda.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public VendaModel getVendaAlteracao() {
        return this.vendas.get(tblVenda.getSelectedRow());
    }

    public void selecionarFiltro() throws ParseException {
        PainelPesquisa.remove(txtPesquisa);
        PainelPesquisa.revalidate();
        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 3) {
            txtPesquisa = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } else {
            txtPesquisa = new JFormattedTextField();
        }
        txtPesquisa.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtPesquisa.setBounds(230, 40, 270, 30);
        PainelPesquisa.add(txtPesquisa);
        PainelPesquisa.revalidate();
        txtPesquisa.requestFocus();
    }

    public boolean verificaPesquisaVazia() {
        if (txtPesquisa.getText().equals("  /  /    ")) {
            return false;
        } else if (!txtPesquisa.getText().equals("")) {
            return true;
        }
        return false;
    }

    public String getTxtPesquisa() throws ParseException {
        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 3) {
            if (!txtPesquisa.getText().equals("  /  /    ")) {
                DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
                Date data = conversor.parse(txtPesquisa.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(data);
            } else {
                return "";
            }
        } else {
            return txtPesquisa.getText();
        }
    }

    public String getQueryPesquisa() throws ParseException {
        String pesquisa = "";
        String query = "";

        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 3) {
            if (!txtPesquisa.getText().equals("  /  /    ")) {
                DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
                Date data = conversor.parse(txtPesquisa.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                pesquisa = dateFormat.format(data);
            }
        } else {
            pesquisa = txtPesquisa.getText();
        }

        switch (cbCampoPesquisa.getSelectedIndex()) {
            case 0:
                query = "v.cod_venda = '" + pesquisa + "'";
                break;
            case 1:
                query = "c.nome like '" + pesquisa + "'";
                break;
            case 2:
                query = "v.data_venda = '" + pesquisa + "'";
                break;
            case 3:
                query = "v.data_vencimento = '" + pesquisa + "'";
                break;
            case 4:
                pesquisa = tratarCamposString(pesquisa);
                query = "v.total_venda = '" + pesquisa + "'";
                break;
        }

        return query;
    }

    private String tratarCamposString(String s) {
        String aux = s.replaceAll(",", ".");
        return aux;
    }

    public int idLinhaSelecionadaTabela() {
        return Integer.parseInt(tblVenda.getValueAt(tblVenda.getSelectedRow(), 0).toString());
    }
    
    public String getDataFixaVenda (){
        return txtDataFixaVenda.getText();
    }
    
    public void inserirDataFixaVenda(String dataFixaVenda){
        txtDataFixaVenda.setText(dataFixaVenda);
    }

    public void addPesquisarListener(ActionListener al) {
        btnPesquisar.addActionListener(al);
    }

    public void addIncluirVendaListener(ActionListener al) {
        btnNovo.addActionListener(al);
    }

    public void addAlterarVendaListener(ActionListener al) {
        btnAlterar.addActionListener(al);
    }

    public void addExcluirVendaListener(ActionListener al) {
        btnExcluir.addActionListener(al);
    }

    public void addSairListener(ActionListener al) {
        btnSairP.addActionListener(al);
    }

    public void addSelecionarFiltroListener(ItemListener il) {
        cbCampoPesquisa.addItemListener(il);
    }

    public void addDoubleClickListener(MouseListener ml) {
        tblVenda.addMouseListener(ml);
    }
}
