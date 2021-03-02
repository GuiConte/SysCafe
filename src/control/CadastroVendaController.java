/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.*;
import dao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.*;

/**
 *
 * @author Guilherme
 */
public class CadastroVendaController {

    private CadastroVenda view_cadvenda;

    private VendaDAO dao_venda;
    private ClienteDAO dao_cliente;
    private ProdutoDAO dao_produto;
    private RecebimentoDAO dao_recebimento;

    private ArrayList<ClienteModel> clientes;
    private ArrayList<ProdutoModel> produtos;
    private ArrayList<Venda_ProdutoModel> produtosVendidos, produtosAdicionados, produtosAlterados, produtosExcluidos;

    private String dataFixa;

    public CadastroVendaController() throws ParseException, SQLException {
        view_cadvenda = new CadastroVenda();
        dao_venda = new VendaDAO();
        dao_cliente = new ClienteDAO();
        dao_produto = new ProdutoDAO();
        dao_recebimento = new RecebimentoDAO();
        dataFixa = "";

        clientes = dao_cliente.pesquisarTodosClientes();
        produtos = dao_produto.pesquisarTodosProdutos();
        view_cadvenda.desenharTelaCadastro();
        view_cadvenda.iniciarArrays();
        view_cadvenda.preencherComboCliente(clientes);
        view_cadvenda.selecionarCliente();
        view_cadvenda.preencherComboProduto(produtos);
        view_cadvenda.selecionarProduto();
        view_cadvenda.selecionarFormaPagamento();

        view_cadvenda.addGravarProdutoListener(new GravarProdutoListener_OnCreate());
        view_cadvenda.addCancelarProdutoListener(new CancelarProdutoListener_OnCreate());
        view_cadvenda.addAlterarProdutoListener(new AlterarProdutoListener_OnCreate());
        view_cadvenda.addExcluirProdutoListener(new ExcluirProdutoListener_OnCreate());
        view_cadvenda.addComboClienteListener(new SelecionarClienteListener());
        view_cadvenda.addComboProdutoListener(new SelecionarProdutoListener());
        view_cadvenda.addTxtFocusListener(new TXTFocoListener());
        view_cadvenda.addTxtDataFocusListener(new TXTFocoDataListener());
        view_cadvenda.addClickTableListener(new ClickTableListener());
        view_cadvenda.addCheckFormaPagamentoListener(new SelecionarFormaPagamentoListener());
        view_cadvenda.addFinalizarVendaListener(new FinalizarVendaListener_OnCreate());
        view_cadvenda.addSairListener(new SairListener());
    }

    public CadastroVendaController(String dataFixaVenda) throws ParseException, SQLException {
        view_cadvenda = new CadastroVenda();
        dao_venda = new VendaDAO();
        dao_cliente = new ClienteDAO();
        dao_produto = new ProdutoDAO();
        dao_recebimento = new RecebimentoDAO();
        dataFixa = dataFixaVenda;

        clientes = dao_cliente.pesquisarTodosClientes();
        produtos = dao_produto.pesquisarTodosProdutos();
        view_cadvenda.desenharTelaCadastro();
        view_cadvenda.iniciarArrays();
        view_cadvenda.preencherComboCliente(clientes);
        view_cadvenda.selecionarCliente();
        view_cadvenda.preencherComboProduto(produtos);
        view_cadvenda.selecionarProduto();
        view_cadvenda.selecionarFormaPagamento();
        view_cadvenda.inserirDataFixaVenda(dataFixaVenda);

        view_cadvenda.addGravarProdutoListener(new GravarProdutoListener_OnCreate());
        view_cadvenda.addCancelarProdutoListener(new CancelarProdutoListener_OnCreate());
        view_cadvenda.addAlterarProdutoListener(new AlterarProdutoListener_OnCreate());
        view_cadvenda.addExcluirProdutoListener(new ExcluirProdutoListener_OnCreate());
        view_cadvenda.addComboClienteListener(new SelecionarClienteListener());
        view_cadvenda.addComboProdutoListener(new SelecionarProdutoListener());
        view_cadvenda.addTxtFocusListener(new TXTFocoListener());
        view_cadvenda.addTxtDataFocusListener(new TXTFocoDataListener());
        view_cadvenda.addClickTableListener(new ClickTableListener());
        view_cadvenda.addCheckFormaPagamentoListener(new SelecionarFormaPagamentoListener());
        view_cadvenda.addFinalizarVendaListener(new FinalizarVendaListener_OnCreate());
        view_cadvenda.addSairListener(new SairListener());
    }

    public CadastroVendaController(VendaModel venda) throws ParseException, SQLException {
        view_cadvenda = new CadastroVenda();
        dao_venda = new VendaDAO();
        dao_cliente = new ClienteDAO();
        dao_produto = new ProdutoDAO();
        dao_recebimento = new RecebimentoDAO();
        dataFixa = "";

        clientes = dao_cliente.pesquisarTodosClientes();
        produtos = dao_produto.pesquisarTodosProdutos();
        view_cadvenda.desenharTelaCadastro();
        view_cadvenda.iniciarArrays();
        view_cadvenda.preencherComboCliente(clientes);
        view_cadvenda.preencheTxt(venda);
        view_cadvenda.setProdutosVendidos(dao_venda.pesquisarProdutosVenda(venda.getCod_pedido()));
        view_cadvenda.selecionarCliente();
        view_cadvenda.preencherComboProduto(produtos);
        view_cadvenda.selecionarProduto();
        view_cadvenda.selecionarFormaPagamento();
        view_cadvenda.ajustarCamposFinanceiro(venda);

        view_cadvenda.addGravarProdutoListener(new GravarProdutoListener_OnUpdate());
        view_cadvenda.addCancelarProdutoListener(new CancelarProdutoListener_OnUpdate());
        view_cadvenda.addAlterarProdutoListener(new AlterarProdutoListener_OnUpdate());
        view_cadvenda.addExcluirProdutoListener(new ExcluirProdutoListener_OnUpdate());
        view_cadvenda.addComboClienteListener(new SelecionarClienteListener());
        view_cadvenda.addComboProdutoListener(new SelecionarProdutoListener());
        view_cadvenda.addTxtFocusListener(new TXTFocoListener());
        view_cadvenda.addTxtDataFocusListener(new TXTFocoDataListener());
        view_cadvenda.addClickTableListener(new ClickTableListener());
        view_cadvenda.addCheckFormaPagamentoListener(new SelecionarFormaPagamentoListener());
        view_cadvenda.addFinalizarVendaListener(new FinalizarVendaListener_OnUpdate());
        view_cadvenda.addSairListener(new SairListener());
    }

    class GravarProdutoListener_OnCreate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.adicionarProduto();
            view_cadvenda.limpaTxtProduto();

            view_cadvenda.calculaTotal_Comissao();
        }
    }

    class CancelarProdutoListener_OnCreate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.limpaTxtProduto();
            view_cadvenda.habilitarBotaoGravarProd();
        }

    }

    class AlterarProdutoListener_OnCreate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.alterarProduto();
            view_cadvenda.limparTabela();
            view_cadvenda.preencherTabela();
            view_cadvenda.limpaTxtProduto();
            view_cadvenda.calculaTotal_Comissao();
            view_cadvenda.habilitarBotaoGravarProd();
        }

    }

    class ExcluirProdutoListener_OnCreate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_cadvenda.verificaProdutosVendidos()) {
                view_cadvenda.excluirProduto();
                view_cadvenda.calculaTotal_Comissao();
                view_cadvenda.habilitarBotaoGravarProd();
            }
        }

    }

    class GravarProdutoListener_OnUpdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.alteracaoProdutos_adicionar(view_cadvenda.getProduto());
            view_cadvenda.adicionarProduto();
            view_cadvenda.limpaTxtProduto();
            view_cadvenda.calculaTotal_Comissao();
        }
    }

    class CancelarProdutoListener_OnUpdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.limpaTxtProduto();
            view_cadvenda.habilitarBotaoGravarProd();
        }

    }

    class AlterarProdutoListener_OnUpdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_cadvenda.alteracaoProdutos_alterar(view_cadvenda.getProdutoAlterado());
            view_cadvenda.alterarProduto();
            view_cadvenda.limparTabela();
            view_cadvenda.preencherTabela();
            view_cadvenda.limpaTxtProduto();
            view_cadvenda.calculaTotal_Comissao();
            view_cadvenda.habilitarBotaoGravarProd();
        }

    }

    class ExcluirProdutoListener_OnUpdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_cadvenda.verificaProdutosVendidos()) {
                view_cadvenda.alteracaoProdutos_excluir(view_cadvenda.getProdutoExcluido());
                view_cadvenda.excluirProduto();
                view_cadvenda.calculaTotal_Comissao();
                view_cadvenda.habilitarBotaoGravarProd();
            }
        }

    }

    class SelecionarClienteListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                view_cadvenda.selecionarCliente();
                if (!view_cadvenda.verificaProdutosVendidos()) {
                    view_cadvenda.calculaTotal_Comissao();
                }
                try {
                    view_cadvenda.selecionarFormaPagamento();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    class SelecionarProdutoListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                view_cadvenda.selecionarProduto();
                view_cadvenda.atualizarTxts();
                view_cadvenda.calcularCamposProduto();
            }
        }

    }

    class TXTFocoListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            view_cadvenda.atualizarTxts();
            view_cadvenda.calcularCamposProduto();
        }

    }

    class TXTFocoDataListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            try {
                view_cadvenda.selecionarFormaPagamento();
            } catch (ParseException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class ClickTableListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            view_cadvenda.preencheTxtProduto();
            view_cadvenda.desabilitarBotaoGravarProd();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    class SelecionarFormaPagamentoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                view_cadvenda.selecionarFormaPagamento();
            } catch (ParseException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class FinalizarVendaListener_OnCreate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (view_cadvenda.verificaProdutosAdicionados()) {
                    produtosVendidos = view_cadvenda.getProdutosVendidos();
                    int id = dao_venda.consultarID();
                    VendaModel venda = view_cadvenda.getVenda();
                    dao_venda.cadastrarVenda(venda);
                    Iterator<Venda_ProdutoModel> it = produtosVendidos.iterator();
                    while (it.hasNext()) {
                        Venda_ProdutoModel produto = it.next();
                        dao_venda.gravarProdutos(id, produto);
                    }
                    if (venda.getForma_pagamento() == 0) {
                        dao_recebimento.cadastrarRecebimentoAVista(id, venda.getData_vencimento(), venda.getTotal_venda());
                    } else if (venda.getForma_pagamento() == 1) {
                        float valor_restante = venda.getTotal_venda();
                        String data_recebimento = null;
                        if (venda.getValor_entrada() != 0) {
                            valor_restante = venda.getTotal_venda() - venda.getValor_entrada();
                            data_recebimento = venda.getData_venda();
                        }
                        dao_recebimento.cadastrarRecebimento(id, venda.getValor_entrada(), valor_restante, data_recebimento);
                    }
                    JOptionPane.showMessageDialog(null, "Venda Realizada Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    if (dataFixa.equals("")) {
                        ConsultaVendaController consulta = new ConsultaVendaController();
                    }else{
                        ConsultaVendaController consulta = new ConsultaVendaController(dataFixa);
                    }
                    view_cadvenda.fecharTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possivel realizar uma venda sem produtos !\n"
                            + "Finalize a gravação dos produtos.", "Venda sem produtos", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class FinalizarVendaListener_OnUpdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = "Se alterar a venda todas as parcelas referentes a ela serão excluidas !";
            if (JOptionPane.showConfirmDialog(null, msg, "Confirmação de Alteração", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                int id = view_cadvenda.obterCodVenda();
                produtosAdicionados = view_cadvenda.getProdutosAdicionados();
                if (!produtosAdicionados.isEmpty()) {
                    Iterator<Venda_ProdutoModel> it = produtosAdicionados.iterator();
                    while (it.hasNext()) {
                        Venda_ProdutoModel produto = it.next();
                        try {
                            dao_venda.gravarProdutos(id, produto);
                        } catch (SQLException ex) {
                            Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                produtosAlterados = view_cadvenda.getProdutosAlterados();
                if (!produtosAlterados.isEmpty()) {
                    Iterator<Venda_ProdutoModel> it = produtosAlterados.iterator();
                    while (it.hasNext()) {
                        Venda_ProdutoModel produto = it.next();
                        try {
                            dao_venda.alterarProdutos(id, produto);
                        } catch (SQLException ex) {
                            Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                produtosExcluidos = view_cadvenda.getProdutosExcluidos();
                if (!produtosExcluidos.isEmpty()) {
                    Iterator<Venda_ProdutoModel> it = produtosExcluidos.iterator();
                    while (it.hasNext()) {
                        Venda_ProdutoModel produto = it.next();
                        try {
                            dao_venda.excluirProdutos(id, produto);
                        } catch (SQLException ex) {
                            Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                try {
                    VendaModel venda = view_cadvenda.getVenda();
                    dao_venda.alterarVenda(venda);
                    //dao_recebimento.excluirRecebimento(id);
                    if (venda.getValor_entrada() == 0) {
                        dao_recebimento.excluirRecebimento(id, venda.getTotal_venda());
                        if (venda.getForma_pagamento() == 0) {
                            dao_recebimento.atualizarRecebimentoAVista(id, venda.getData_venda(), venda.getTotal_venda());
                        }
                    } else {
                        float valor_restante = venda.getTotal_venda() - venda.getValor_entrada();
                        dao_recebimento.excluirRecebimento(id, venda.getValor_entrada(),
                                valor_restante, venda.getData_venda());
                        dao_recebimento.atualizarRecebimentoEntrada(id, venda.getValor_entrada());
                    }
                    JOptionPane.showMessageDialog(null, "Venda Alterada Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    ConsultaVendaController consulta = new ConsultaVendaController();
                    view_cadvenda.fecharTela();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    class SairListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConsultaVendaController consulta = new ConsultaVendaController();
            } catch (ParseException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            view_cadvenda.fecharTela();
        }

    }

}
