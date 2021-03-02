/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ConsultaCliente {

    private JFrame janela;
    private JPanel painelConsultaCliente;
    private JTextField txtPesquisa;
    private JButton btnPesquisar, btnAlterar, btnSair, btnCadastrar, btnExcluir;
    private JTable tblCliente;
    private DefaultTableModel modelo;
    private JScrollPane scrollTable;
    private JComboBox cbCampoPesquisa;

    private ArrayList<ClienteModel> clientes;

    public void desenharTelaConsulta() {
        ClassLoader cl = this.getClass().getClassLoader();
        janela = new JFrame("Consulta Clientes");        
        janela.setSize(700, 500);
        janela.setUndecorated(true);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        painelConsultaCliente = new JPanel();
        painelConsultaCliente.setLayout(null);
        painelConsultaCliente.setSize(700, 500);
        painelConsultaCliente.setLocation(0, 0);

        
        cbCampoPesquisa = new JComboBox();
        cbCampoPesquisa.setBounds(60, 40, 150, 30);
        cbCampoPesquisa.addItem("Codigo");
        cbCampoPesquisa.addItem("Nome");
        cbCampoPesquisa.addItem("Contato");
        cbCampoPesquisa.addItem("Cidade");
        painelConsultaCliente.add(cbCampoPesquisa);

        txtPesquisa = new JTextField(30);
        txtPesquisa.setBounds(230, 40, 270, 30);
        painelConsultaCliente.add(txtPesquisa);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(520, 40, 120, 30);
        btnPesquisar.setIcon(new ImageIcon(cl.getResource("Imagens/pesquisa.png")));
        btnPesquisar.setFont(new Font("Arial", 1, 13));
        btnPesquisar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelConsultaCliente.add(btnPesquisar);

        criarTabela();

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(120, 420, 100, 35);
        btnCadastrar.setIcon(new ImageIcon(cl.getResource("Imagens/incluir.png")));
        btnCadastrar.setFont(new Font("Arial", 1, 13));
        btnCadastrar.setHorizontalTextPosition(SwingConstants.RIGHT); 
        painelConsultaCliente.add(btnCadastrar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(230, 420, 100, 35);
        btnAlterar.setIcon(new ImageIcon(cl.getResource("Imagens/alterar.png")));
        btnAlterar.setFont(new Font("Arial", 1, 13));
        btnAlterar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelConsultaCliente.add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(340, 420, 100, 35);
        btnExcluir.setIcon(new ImageIcon(cl.getResource("Imagens/excluir.png")));
        btnExcluir.setFont(new Font("Arial", 1, 13));
        btnExcluir.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelConsultaCliente.add(btnExcluir);

        btnSair = new JButton("Sair");
        btnSair.setBounds(450, 420, 100, 35);
        btnSair.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSair.setFont(new Font("Arial", 1, 13));
        btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSair.addActionListener((java.awt.event.ActionEvent evt) -> {
            janela.dispose();
        });
        painelConsultaCliente.add(btnSair);

        janela.add(painelConsultaCliente);
        janela.setVisible(true);
    }

    private void criarTabela() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Contato");
        modelo.addColumn("Nome");
        modelo.addColumn("Cidade");

        tblCliente = new JTable();
        tblCliente.setModel(modelo);
        tblCliente.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblCliente.getColumnModel().getColumn(1).setPreferredWidth(220);
        tblCliente.getColumnModel().getColumn(2).setPreferredWidth(220);
        tblCliente.getColumnModel().getColumn(3).setPreferredWidth(220);

        tblCliente.getTableHeader().setResizingAllowed(false);
        tblCliente.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tblCliente.getTableHeader();
        cabecalho.setFont(new Font("Arial", 1, 14));

        DefaultTableCellRenderer centralizaColunas = new DefaultTableCellRenderer();
        centralizaColunas.setHorizontalAlignment(SwingConstants.CENTER);
        tblCliente.getColumnModel().getColumn(0).setCellRenderer(centralizaColunas);
        tblCliente.getColumnModel().getColumn(1).setCellRenderer(centralizaColunas);
        tblCliente.getColumnModel().getColumn(2).setCellRenderer(centralizaColunas);
        tblCliente.getColumnModel().getColumn(3).setCellRenderer(centralizaColunas);

        tblCliente.setFont(new Font("Arial", 0, 14));

        scrollTable = new JScrollPane(tblCliente);
        scrollTable.setBounds(60, 100, 580, 300);

        painelConsultaCliente.add(scrollTable);
    }

    public void limparTabela() {
        modelo = (DefaultTableModel) tblCliente.getModel();
        if (modelo.getRowCount() > 0) {
            int rows = modelo.getRowCount();
            for (int i = rows - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public void preencherTabela(ArrayList<ClienteModel> clientes) {
        this.clientes = clientes; //lista de clientes para alteracao

        limparTabela();
        Iterator<ClienteModel> it = clientes.iterator();
        while (it.hasNext()) {
            ClienteModel cliente = it.next();
            modelo.addRow(new Object[]{cliente.getCod_cliente(), cliente.getNome(),
                cliente.getContato(), cliente.getCidade()});
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

    public String getCbPesquisa() {
        String campo = cbCampoPesquisa.getSelectedItem().toString().toLowerCase();
        if (campo.equals("codigo")) {
            campo = "cod_cliente";
        }
        return campo;
    }

    public void preencherTabela_Pesquisa(ArrayList<ClienteModel> clientes) {
        this.clientes = clientes; //lista de clientes para alteracao

        limparTabela();
        if (!clientes.isEmpty()) {
            Iterator<ClienteModel> it = clientes.iterator();
            while (it.hasNext()) {
                ClienteModel cliente = it.next();
                modelo.addRow(new Object[]{cliente.getCod_cliente(), cliente.getNome(),
                cliente.getContato(), cliente.getCidade()});
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado !", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ClienteModel getClienteAlteracao() {
        return this.clientes.get(tblCliente.getSelectedRow());
    }

    public boolean verificarLinhaSelecionadaTabela() {
        if(tblCliente.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public void addPesquisarListener(ActionListener al) {
        btnPesquisar.addActionListener(al);
    }

    public void addCadastrarListener(ActionListener al) {
        btnCadastrar.addActionListener(al);
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
    
    public int getCodClienteSelecionadoTabela(){
        int id = Integer.parseInt(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0).toString());
        return id;
    }
            
    public void addDoubleClickListener(MouseListener ml){
        tblCliente.addMouseListener(ml);
    }
    
}
