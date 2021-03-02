/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.RecebimentoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.RecebimentoModel;
import view.ConsultaRecebimento;

/**
 *
 * @author Guilherme
 */
public class ConsultaRecebimentoController {

    private ConsultaRecebimento view_consrecebimento;
    private RecebimentoDAO dao_recebimento;
    private ArrayList<RecebimentoModel> recebimentos;

    public ConsultaRecebimentoController() throws ParseException, SQLException {
        view_consrecebimento = new ConsultaRecebimento();
        dao_recebimento = new RecebimentoDAO();

        recebimentos = dao_recebimento.pesquisarTodosRecebimentos();
        view_consrecebimento.desenharTelaConsulta();
        view_consrecebimento.preencherTabela(recebimentos);

        view_consrecebimento.addReceberListener(new ReceberListener());
        view_consrecebimento.addExcluirListener(new ExcluirListener());
        view_consrecebimento.addSairListener(new SairListener());
        view_consrecebimento.addPesquisarListener(new PesquisarListener());
        view_consrecebimento.addSelecionarFiltroListener(new SelecionarFiltroListener());
        view_consrecebimento.addDoubleClickListener(new TableClickListener());
    }

    class ReceberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_consrecebimento.verificarLinhaSelecionadaTabela()) {
                try {
                    if (!view_consrecebimento.obterRecebimento().isRecebido()) {
                        CadastroRecebimentoController cadastro = new CadastroRecebimentoController(
                                view_consrecebimento, view_consrecebimento.obterRecebimento());
                    } else {
                        JOptionPane.showMessageDialog(null, "Esta conta ja foi recebida !", "Conta Recebida", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Recebimento", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    class ExcluirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_consrecebimento.verificarLinhaSelecionadaTabela()) {
                //if (view_consrecebimento.obterRecebimento().isRecebido()) {
                if (JOptionPane.showConfirmDialog(null, "Deseja excluir o recebimento?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        RecebimentoModel recebimento = view_consrecebimento.obterRecebimento();
                        if (recebimento.getValor_entrada() == 0) {
                            dao_recebimento.excluirRecebimento(recebimento.getCod_venda(),recebimento.getValor_total());
                        } else {
                            float valor_restante = recebimento.getValor_total() - recebimento.getValor_entrada();
                            dao_recebimento.excluirRecebimento(recebimento.getCod_venda(),recebimento.getValor_entrada(),
                                    valor_restante,recebimento.getData_venda());
                        }
                        view_consrecebimento.preencherTabela(dao_recebimento.pesquisarTodosRecebimentos());
                        JOptionPane.showMessageDialog(null, "Recebimento excluido Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // } else {
                //     JOptionPane.showMessageDialog(null, "Esta conta ainda não foi recebida !", "Conta não Recebida", JOptionPane.INFORMATION_MESSAGE);
                //  }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Recebimento", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    class SairListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_consrecebimento.fecharTela();
        }

    }

    class PesquisarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_consrecebimento.verificaPesquisaVazia()) {
                try {
                    recebimentos = dao_recebimento.pesquisarRecebimentoEspecificoTabela(view_consrecebimento.getQueryPesquisa());
                    view_consrecebimento.preencherTabela_Pesquisa(recebimentos);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    recebimentos = dao_recebimento.pesquisarTodosRecebimentos();
                    view_consrecebimento.preencherTabela(recebimentos);
                } catch (SQLException ex) {
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    class SelecionarFiltroListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    view_consrecebimento.selecionarFiltro();
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    class TableClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2 && view_consrecebimento.verificarLinhaSelecionadaTabela()) {
                try {
                    if (!view_consrecebimento.obterRecebimento().isRecebido()) {
                        CadastroRecebimentoController cadastro = new CadastroRecebimentoController(
                                view_consrecebimento, view_consrecebimento.obterRecebimento());
                    } else {
                        JOptionPane.showMessageDialog(null, "Esta conta ja foi recebida !", "Conta Recebida", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
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
