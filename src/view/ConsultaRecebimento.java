package view;

import java.awt.Color;
import java.awt.Component;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.RecebimentoModel;

public class ConsultaRecebimento {

    private JFrame janela;
    private JPanel PainelPesquisa, verde, vermelho, branco;
    private JTable tblRecebimento;
    private JScrollPane scrollTable;
    private DefaultTableModel modelo;
    private JFormattedTextField txtPesquisa;
    private JButton btnReceber, btnExcluir, btnAbrir, btnSairP, btnPesquisar;
    private JComboBox cbCampoPesquisa;
    private JLabel lblverde, lblvermelho, lblbranco;

    ArrayList<RecebimentoModel> recebimentos;

    public void desenharTelaConsulta() throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();
        janela = new JFrame("Consulta Recebimentos");
        janela.setSize(985, 610);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        //-------------- CRIANDO PAINEL PESQUISA --------------------//
        PainelPesquisa = new JPanel();
        PainelPesquisa.setLayout(null);
        PainelPesquisa.setBounds(0, 02, 985, 570);
        PainelPesquisa.setBorder(BorderFactory.createTitledBorder(""));

        //-----------------NOME--------------//
        cbCampoPesquisa = new JComboBox();
        cbCampoPesquisa.setBounds(60, 40, 150, 30);       
        cbCampoPesquisa.addItem("Cliente");
        cbCampoPesquisa.addItem("Cod. Venda");
        cbCampoPesquisa.addItem("Data Vencimento");
        cbCampoPesquisa.addItem("Valor Venda");
        cbCampoPesquisa.addItem("Valor Recebido");
        cbCampoPesquisa.addItem("Data Recebimento");
        PainelPesquisa.add(cbCampoPesquisa);

        txtPesquisa = new JFormattedTextField();
        txtPesquisa.setBounds(230, 40, 270, 30);
        PainelPesquisa.add(txtPesquisa);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(520, 40, 120, 30);
        btnPesquisar.setIcon(new ImageIcon(cl.getResource("Imagens/pesquisa.png")));
        btnPesquisar.setFont(new Font("Arial", 1, 13));
        btnPesquisar.setHorizontalTextPosition(SwingConstants.RIGHT);
        PainelPesquisa.add(btnPesquisar);

        // ---------------- BOTAO NOVO-----//
        btnReceber = new JButton("Receber");
        btnReceber.setBounds(300, 520, 140, 40);
        btnReceber.setIcon(new ImageIcon(cl.getResource("Imagens/dinheiro.png")));
        btnReceber.setFont(new Font("Arial", 1, 13));
        btnReceber.setHorizontalTextPosition(SwingConstants.RIGHT);
        PainelPesquisa.add(btnReceber);

        // ---------------- BOTAO ALTERAR-----//
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(450, 520, 140, 40);
        btnExcluir.setIcon(new ImageIcon(cl.getResource("Imagens/excluir.png")));
        btnExcluir.setFont(new Font("Arial", 1, 13));
        btnExcluir.setHorizontalTextPosition(SwingConstants.RIGHT);

        PainelPesquisa.add(btnExcluir);
        // ---------------- BOTAO GRAVAR PRODUTO-----//
        btnSairP = new JButton("Sair");
        btnSairP.setBounds(600, 520, 140, 40);
        btnSairP.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSairP.setFont(new Font("Arial", 1, 13));
        btnSairP.setHorizontalTextPosition(SwingConstants.RIGHT);

        PainelPesquisa.add(btnSairP);

        verde = new JPanel();
        verde.setLayout(null);
        verde.setBounds(30, 470, 100, 17);
        verde.setBackground(Color.GREEN);
        verde.setBorder(BorderFactory.createTitledBorder(""));
        janela.add(verde);

        lblverde = new JLabel("Contas Pagas");
        lblverde.setBounds(3, -2, 100, 22);
        verde.add(lblverde);

        vermelho = new JPanel();
        vermelho.setLayout(null);
        vermelho.setBounds(150, 470, 100, 17);
        vermelho.setBackground(Color.red);
        vermelho.setBorder(BorderFactory.createTitledBorder(""));
        janela.add(vermelho);

        lblvermelho = new JLabel("Vencidas");
        lblvermelho.setBounds(20, -2, 100, 22);
        vermelho.add(lblvermelho);

        branco = new JPanel();
        branco.setLayout(null);
        branco.setBounds(270, 470, 100, 17);
        branco.setBackground(Color.white);
        branco.setBorder(BorderFactory.createTitledBorder(""));
        janela.add(branco);

        lblbranco = new JLabel("A Vencer");
        lblbranco.setBounds(20, -2, 100, 22);
        branco.add(lblbranco);

        criarTabelaPesquisa();
        janela.add(PainelPesquisa);
        janela.setVisible(true);
    }

    public void criarTabelaPesquisa() {
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
        modelo.addColumn("Valor Pago ");
        modelo.addColumn("Valor Restante ");
        modelo.addColumn("Data Pagamento ");
        modelo.addColumn("Recebido");

        tblRecebimento = new JTable();
        tblRecebimento.setModel(modelo);
        tblRecebimento.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblRecebimento.getColumnModel().getColumn(1).setPreferredWidth(208);
        tblRecebimento.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblRecebimento.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblRecebimento.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblRecebimento.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblRecebimento.getColumnModel().getColumn(6).setPreferredWidth(110);
        tblRecebimento.getColumnModel().getColumn(7).setPreferredWidth(110);

        tblRecebimento.getColumnModel().getColumn(8).setMinWidth(0);
        tblRecebimento.getColumnModel().getColumn(8).setMaxWidth(0);

        tblRecebimento.getTableHeader().setResizingAllowed(false);
        tblRecebimento.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tblRecebimento.getTableHeader();
        cabecalho.setFont(new Font("Arial", 1, 12));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
                Date dataVencimento = new Date();
                try {
                    dataVencimento = conversor.parse(tblRecebimento.getValueAt(row, 3).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaRecebimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                GregorianCalendar dataAtual = new GregorianCalendar();
                dataAtual.setTime(new Date());
                GregorianCalendar vencimento = new GregorianCalendar();
                vencimento.setTime(dataVencimento);
                dataAtual.add(Calendar.DAY_OF_MONTH, -1);
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                //********************************************************
                Color c = Color.WHITE;
                String recebido = tblRecebimento.getValueAt(row, 8).toString();
                if (isSelected) {
                    c = Color.BLUE;
                } else {
                    if (dataAtual.after(vencimento)) {
                        c = Color.RED;
                    }
                    if (recebido.equals("true")) {
                        c = Color.GREEN;
                    }
                }
                label.setBackground(c);
                //*********************************************************
                return label;
            }

        };

        tblRecebimento.setDefaultRenderer(Object.class, renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblRecebimento.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(2).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(3).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(4).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(5).setCellRenderer(renderer);
        tblRecebimento.getColumnModel().getColumn(6).setCellRenderer(renderer);

        tblRecebimento.setFont(new Font("Arial", 1, 12));

        scrollTable = new JScrollPane(tblRecebimento, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10, 110, 955, 350);
        tblRecebimento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PainelPesquisa.add(scrollTable);
    }

    public void preencherTabela(ArrayList<RecebimentoModel> recebimentos) throws ParseException {

        this.recebimentos = recebimentos; //lista de vendas para alteracao

        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataRecebimento, dataVencimento, dataVenda;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        BigDecimal bdTotalVenda, bdTotalPago, bdValorRestante;
        DecimalFormat df = new DecimalFormat("R$ 0.00");

        limparTabela();
        Iterator<RecebimentoModel> it = recebimentos.iterator();
        while (it.hasNext()) {
            RecebimentoModel recebimento = it.next();
            dataVenda = conversor.parse(recebimento.getData_venda());
            dataVencimento = conversor.parse(recebimento.getData_vencimento());
            bdTotalVenda = new BigDecimal(recebimento.getValor_total()).setScale(2, RoundingMode.HALF_EVEN);
            bdValorRestante = new BigDecimal(recebimento.getValor_restante()).setScale(2, RoundingMode.HALF_EVEN);
            if (recebimento.isRecebido()) {
                dataRecebimento = conversor.parse(recebimento.getData_recebimento());
                bdTotalPago = new BigDecimal(recebimento.getValor_pago()).setScale(2, RoundingMode.HALF_EVEN);
                modelo.addRow(new Object[]{recebimento.getCod_venda(), recebimento.getCliente(),
                    dateFormat.format(dataVenda),dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue()),
                    df.format(bdTotalPago.floatValue()), df.format(bdValorRestante.floatValue()),
                    dateFormat.format(dataRecebimento),
                    recebimento.isRecebido()});

            } else {
                String receb = "";
                String totalpago = "";
                if (recebimento.getData_recebimento() != null) {
                    dataRecebimento = conversor.parse(recebimento.getData_recebimento());
                    bdTotalPago = new BigDecimal(recebimento.getValor_pago()).setScale(2, RoundingMode.HALF_EVEN);
                    receb = dateFormat.format(dataRecebimento);
                    totalpago = df.format(bdTotalPago.floatValue());
                }
                modelo.addRow(new Object[]{recebimento.getCod_venda(), recebimento.getCliente(),
                    dateFormat.format(dataVenda),dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue()),
                    totalpago, df.format(bdValorRestante.floatValue()),
                    receb,
                    recebimento.isRecebido()});
            }
        }
    }

    public void preencherTabela_Pesquisa(ArrayList<RecebimentoModel> recebimentos) throws ParseException {

        this.recebimentos = recebimentos; //lista de vendas para alteracao

        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataRecebimento, dataVencimento, dataVenda;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        BigDecimal bdTotalVenda, bdTotalPago, bdValorRestante;
        DecimalFormat df = new DecimalFormat("R$ 0.00");

        limparTabela();
        if (!recebimentos.isEmpty()) {
            Iterator<RecebimentoModel> it = recebimentos.iterator();
            while (it.hasNext()) {
                RecebimentoModel recebimento = it.next();
                dataVenda = conversor.parse(recebimento.getData_venda());
                dataVencimento = conversor.parse(recebimento.getData_vencimento());
                bdTotalVenda = new BigDecimal(recebimento.getValor_total()).setScale(2, RoundingMode.HALF_EVEN);
                bdValorRestante = new BigDecimal(recebimento.getValor_restante()).setScale(2, RoundingMode.HALF_EVEN);
                if (recebimento.isRecebido()) {
                    dataRecebimento = conversor.parse(recebimento.getData_recebimento());
                    bdTotalPago = new BigDecimal(recebimento.getValor_pago()).setScale(2, RoundingMode.HALF_EVEN);
                    modelo.addRow(new Object[]{recebimento.getCod_venda(), recebimento.getCliente(),
                        dateFormat.format(dataVenda),dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue()),
                        df.format(bdTotalPago.floatValue()), df.format(bdValorRestante.floatValue()),
                        dateFormat.format(dataRecebimento),
                        recebimento.isRecebido()});

                } else {
                    String receb = "";
                    String totalpago = "";
                    if (recebimento.getData_recebimento() != null) {
                        dataRecebimento = conversor.parse(recebimento.getData_recebimento());
                        bdTotalPago = new BigDecimal(recebimento.getValor_pago()).setScale(2, RoundingMode.HALF_EVEN);
                        receb = dateFormat.format(dataRecebimento);
                        totalpago = df.format(bdTotalPago.floatValue());
                    }
                    modelo.addRow(new Object[]{recebimento.getCod_venda(), recebimento.getCliente(),
                        dateFormat.format(dataVenda),dateFormat.format(dataVencimento), df.format(bdTotalVenda.floatValue()),
                        totalpago, df.format(bdValorRestante.floatValue()),
                        receb,
                        recebimento.isRecebido()});
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Recebimento não encontrado !", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean verificarLinhaSelecionadaTabela() {
        if (tblRecebimento.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public void limparTabela() {
        modelo = (DefaultTableModel) tblRecebimento.getModel();
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

    public RecebimentoModel obterRecebimento() {
        return recebimentos.get(tblRecebimento.getSelectedRow());
    }

    public boolean verificaPesquisaVazia() {
        if (!txtPesquisa.getText().equals("")) {
            return true;
        }
        return false;
    }

    public String getQueryPesquisa() throws ParseException {
        String query = "";
        String pesquisa = "";

        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 5) {
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
                query = "c.nome like '" + pesquisa + "'";
                break;
                case 1:
                query = "v.cod_venda = '" + pesquisa + "'";
                break;
            case 2:
                query = "v.data_vencimento = '" + pesquisa + "'";
                break;
            case 3:
                pesquisa = tratarCamposString(pesquisa);
                query = "v.total_venda = '" + pesquisa + "'";
                break;
            case 4:
                pesquisa = tratarCamposString(pesquisa);
                query = "r.valor_pago = '" + pesquisa + "'";
                break;
            case 5:
                query = "r.data_recebimento = '" + pesquisa + "'";
                break;
        }

        return query;
    }

    public String getTxtPesquisa() throws ParseException {
        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 5) {
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

    private String tratarCamposString(String s) {
        String aux = s.replaceAll(",", ".");
        return aux;
    }

    public void selecionarFiltro() throws ParseException {
        PainelPesquisa.remove(txtPesquisa);
        PainelPesquisa.revalidate();
        if (cbCampoPesquisa.getSelectedIndex() == 2 || cbCampoPesquisa.getSelectedIndex() == 5) {
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

    public void addReceberListener(ActionListener al) {
        btnReceber.addActionListener(al);
    }

    public void addExcluirListener(ActionListener al) {
        btnExcluir.addActionListener(al);
    }

    public void addSairListener(ActionListener al) {
        btnSairP.addActionListener(al);
    }

    public void addPesquisarListener(ActionListener al) {
        btnPesquisar.addActionListener(al);
    }

    public void addSelecionarFiltroListener(ItemListener il) {
        cbCampoPesquisa.addItemListener(il);
    }

    public void addDoubleClickListener(MouseListener ml) {
        tblRecebimento.addMouseListener(ml);
    }
}
