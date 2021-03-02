/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.*;
import dao.*;
import model.ProdutoModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class ConsultaProdutoController {

    private ConsultaProduto view_consproduto;
    private ProdutoDAO dao_Produto;
    private ArrayList<ProdutoModel> Produtos;

    public ConsultaProdutoController() throws SQLException {
        view_consproduto = new ConsultaProduto();
        dao_Produto = new ProdutoDAO();

        Produtos = dao_Produto.pesquisarTodosProdutos();
        view_consproduto.desenharTelaConsulta();
        view_consproduto.preencherTabela(Produtos);

        view_consproduto.addPesquisarListener(new PesquisarListener());
        view_consproduto.addCadastrarListener(new CadastrarListener());
        view_consproduto.addAlterarListener(new AlterarListener());
        view_consproduto.addExcluirListener(new ExcluirListener());
        view_consproduto.addSairListener(new SairListener());
        view_consproduto.addDoubleClickListener(new TableClickListener());

    }

    class PesquisarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (view_consproduto.verificaPesquisaVazia()) {
                try {
                    Produtos = dao_Produto.pesquisarProdutoEspecificoTabela(view_consproduto.getQueryPesquisa());
                    view_consproduto.preencherTabela_Pesquisa(Produtos);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Produtos = dao_Produto.pesquisarTodosProdutos();
                    view_consproduto.preencherTabela(Produtos);
                } catch (SQLException ex) {
                }
            }
        }

    }

    class CadastrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_consproduto.fecharTela();
            try {
                CadastroProdutoController cadastro = new CadastroProdutoController();
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class AlterarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (view_consproduto.verificarLinhaSelecionadaTabela()) {
                view_consproduto.fecharTela();
                try {
                    CadastroProdutoController cadastro = new CadastroProdutoController(view_consproduto.getProdutoAlteracao());
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Produto", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class ExcluirListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int id = view_consproduto.getCodProdutoSelecionadoTabela();
            if (id != -1) {
                if (JOptionPane.showConfirmDialog(null, "Deseja excluir o Produto?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        dao_Produto.excluirProduto(id);
                        JOptionPane.showMessageDialog(null, "Produto Excluido", "Exclusão de Produto", JOptionPane.INFORMATION_MESSAGE);
                        view_consproduto.limparTabela();
                        Produtos = dao_Produto.pesquisarTodosProdutos();
                        view_consproduto.preencherTabela(Produtos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ConsultaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

    }

    class SairListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_consproduto.fecharTela();
        }

    }

    class TableClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2 && view_consproduto.verificarLinhaSelecionadaTabela()) {
                try {
                    CadastroProdutoController cadastro = new CadastroProdutoController(view_consproduto.getProdutoAlteracao());
                    view_consproduto.fecharTela();
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
}
