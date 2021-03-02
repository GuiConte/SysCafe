/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys_cafe;

import dao.*;
import control.ConsultaClienteController;
import control.ConsultaProdutoController;
import control.ConsultaRecebimentoController;
import control.ConsultaVendaController;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import view.RelatorioVenda;
import net.sf.jasperreports.view.JasperViewer;
import view.RelatorioComissao;
import view.RelatorioContasReceber;
import view.RelatorioRecebimento;

/**
 *
 * @author Guilherme
 */
public class Menu {

    private JFrame janela;
    private JPanel painelTitulo, painelFormulario, painelRelatorio;
    private JButton btnProduto, btnCliente, btnVenda, btnRecebimento;
    private JButton btnRelVendas, btnRelContasReceb, btnRelRecebimentos, btnRelMediaMensalVendas, btnRelClientes, btnRelComissao;
    private JLabel lblTitulo;

    public void desenharTela() {
        ClassLoader cl = this.getClass().getClassLoader();
        //JFrame
        janela = new JFrame("Gerenciador");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(940, 600);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        //*********************************    JPanel Cadastro    ***********************************************
        painelTitulo = new JPanel();
        painelTitulo.setLayout(null);
        painelTitulo.setSize(934, 90);
        painelTitulo.setLocation(0, 20);

        lblTitulo = new JLabel("TORREFAÇÃO");
        lblTitulo.setBounds(330, 20, 400, 50);
        lblTitulo.setFont(new Font("Arial", 1, 40));

        painelTitulo.add(lblTitulo);

        janela.add(painelTitulo);

        //*********************************    JPanel Cadastro    ***********************************************
        painelFormulario = new JPanel();
        painelFormulario.setLayout(null);
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Formularios"));
        painelFormulario.setSize(934, 180);
        painelFormulario.setLocation(0, 150);

        //------------------Botao Cliente----------------------------------
        btnCliente = new JButton("Cadastrar Cliente");
        btnCliente.setBounds(50, 40, 250, 35);
        btnCliente.setFont(new Font("Arial", 1, 13));
        btnCliente.setIcon(new ImageIcon(cl.getResource("Imagens/cliente.png")));
        btnCliente.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCliente.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                ConsultaClienteController c = new ConsultaClienteController();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        painelFormulario.add(btnCliente);

        //------------------Botao Produto----------------------------------
        btnProduto = new JButton("Cadastrar Produto");
        btnProduto.setBounds(50, 110, 250, 35);
        btnProduto.setFont(new Font("Arial", 1, 13));
        btnProduto.setIcon(new ImageIcon(cl.getResource("Imagens/cafe.png")));
        btnProduto.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnProduto.addActionListener((java.awt.event.ActionEvent evt) -> {
            ConsultaProdutoController c;
            try {
                c = new ConsultaProdutoController();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelFormulario.add(btnProduto);

        //------------------Botao Venda----------------------------------
        btnVenda = new JButton(" Vendas");
        btnVenda.setBounds(630, 40, 250, 35);
        btnVenda.setFont(new Font("Arial", 1, 13));
        btnVenda.setIcon(new ImageIcon(cl.getResource("Imagens/venda.png")));
        btnVenda.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnVenda.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                ConsultaVendaController c = new ConsultaVendaController();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelFormulario.add(btnVenda);

        //------------------Botao Recebimento----------------------------------
        btnRecebimento = new JButton("Recebimentos");
        btnRecebimento.setBounds(630, 110, 250, 35);
        btnRecebimento.setFont(new Font("Arial", 1, 13));
        btnRecebimento.setIcon(new ImageIcon(cl.getResource("Imagens/dinheiro.png")));
        btnRecebimento.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRecebimento.addActionListener((java.awt.event.ActionEvent evt) -> {
            ConsultaRecebimentoController c;
            try {
                c = new ConsultaRecebimentoController();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelFormulario.add(btnRecebimento);

        //ADD
        janela.add(painelFormulario);

        //*********************************    JPanel Relatorio    ***********************************************
        painelRelatorio = new JPanel();
        painelRelatorio.setLayout(null);
        painelRelatorio.setBorder(BorderFactory.createTitledBorder("Relatorios"));
        painelRelatorio.setSize(934, 220);
        painelRelatorio.setLocation(0, 340);

        //------------------Botao Relatorio de Vendas----------------------------------
        btnRelVendas = new JButton("Rel. de Vendas");
        btnRelVendas.setBounds(50, 50, 250, 35);
        btnRelVendas.setFont(new Font("Arial", 1, 13));
        btnRelVendas.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelVendas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelVendas.addActionListener((java.awt.event.ActionEvent evt) -> {
            RelatorioVenda c = new RelatorioVenda();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        painelRelatorio.add(btnRelVendas);

        //------------------Botao Relatorio Contas a Receber----------------------------------
        btnRelContasReceb = new JButton("Rel. Contas a Receber");
        btnRelContasReceb.setBounds(630, 100, 250, 35);
        btnRelContasReceb.setFont(new Font("Arial", 1, 13));
        btnRelContasReceb.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelContasReceb.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelContasReceb.addActionListener((java.awt.event.ActionEvent evt) -> {

             RelatorioContasReceber c = new RelatorioContasReceber();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelRelatorio.add(btnRelContasReceb);

        //------------------Botao Relatorio de Recebimentos----------------------------------
        btnRelRecebimentos = new JButton("Rel. de Recebimentos");
        btnRelRecebimentos.setBounds(630, 50, 250, 35);
        btnRelRecebimentos.setFont(new Font("Arial", 1, 13));
        btnRelRecebimentos.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelRecebimentos.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelRecebimentos.addActionListener((java.awt.event.ActionEvent evt) -> {
            RelatorioRecebimento c = new RelatorioRecebimento();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

      
        painelRelatorio.add(btnRelRecebimentos);

       

        //------------------Botao Relatorio de Clientes----------------------------------
        btnRelClientes = new JButton("Lista de Clientes");
        btnRelClientes.setBounds(50, 100, 250, 35);
        btnRelClientes.setFont(new Font("Arial", 1, 13));
        btnRelClientes.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelClientes.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelClientes.addActionListener((java.awt.event.ActionEvent evt) -> {

            Connection c = new Conexao().getConnectionMySQL();
            String src = "C:/sys_cafe/Report/Cliente.jasper";
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(src, null, c);
            } catch (Exception ex) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "Erro ao gerar Relatorio/n");
            }

            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setVisible(true);
        });
        painelRelatorio.add(btnRelClientes);

      
        //------------------Botao Relatorio de Comissao----------------------------------
        btnRelComissao = new JButton("Rel. de Comissao");
        btnRelComissao.setBounds(340, 75, 250, 35);
        btnRelComissao.setFont(new Font("Arial", 1, 13));
        btnRelComissao.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelComissao.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelComissao.addActionListener((java.awt.event.ActionEvent evt) -> {

            RelatorioComissao c = new RelatorioComissao();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelRelatorio.add(btnRelComissao);
        
         //------------------Botao Relatorio de Media Mensal de Venda----------------------------------
       /* btnRelMediaMensalVendas = new JButton("Rel. Media Mensal de Venda");
        btnRelMediaMensalVendas.setBounds(50, 100, 250, 35);
        btnRelMediaMensalVendas.setFont(new Font("Arial", 1, 13));
        btnRelMediaMensalVendas.setIcon(new ImageIcon(cl.getResource("Imagens/relatorio.png")));
        btnRelMediaMensalVendas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelMediaMensalVendas.addActionListener((java.awt.event.ActionEvent evt) -> {

        });
        painelRelatorio.add(btnRelMediaMensalVendas);
  //------------------Botao Relatorio de Erros----------------------------------
        /* btnRelErros = new JButton("Rel. de Erros");
        btnRelErros.setBounds(340, 150, 250, 35);
        btnRelErros.setFont(new Font("Arial", 1, 13));
        btnRelErros.setIcon(new ImageIcon("/Workspace/sys_cafe/src/Imagens/relatorio.png"));
        btnRelErros.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelErros.addActionListener((java.awt.event.ActionEvent evt) -> {
            RelatorioVenda c = new RelatorioVenda();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelRelatorio.add(btnRelErros);*/
        //------------------Botao Resumo Mensal de Vendas----------------------------------
        /*btnRelMensalVendas = new JButton("Resumo Mensal de Vendas");
        btnRelMensalVendas.setBounds(630, 50, 250, 35);
        btnRelMensalVendas.setFont(new Font("Arial", 1, 13));
        btnRelMensalVendas.setIcon(new ImageIcon("/Workspace/sys_cafe/src/Imagens/relatorio.png"));
        btnRelMensalVendas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelMensalVendas.addActionListener((java.awt.event.ActionEvent evt) -> {
            
        painelRelatorio.add(btnRelMensalVendas);
         */
        //------------------Botao Resumo Anual de Vendas----------------------------------
        /* btnRelAnualVendas = new JButton("Resumo Anual de Vendas");
        btnRelAnualVendas.setBounds(630, 100, 250, 35);
        btnRelAnualVendas.setFont(new Font("Arial", 1, 13));
        btnRelAnualVendas.setIcon(new ImageIcon("/Workspace/sys_cafe/src/Imagens/relatorio.png"));
        btnRelAnualVendas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRelAnualVendas.addActionListener((java.awt.event.ActionEvent evt) -> {
            RelatorioVenda c = new RelatorioVenda();
            try {
                c.desenharTela();
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        painelRelatorio.add(btnRelAnualVendas);*/
        //ADD
        janela.add(painelRelatorio);

        //*******************************************************************************************************
        janela.setVisible(true);

    }

}
