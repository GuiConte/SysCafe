/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.VendaDAO;
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
import model.VendaModel;
import view.ConsultaVenda;

/**
 *
 * @author Guilherme
 */
public class ConsultaVendaController {

    private ConsultaVenda view_consvenda;
    private VendaDAO dao_venda;
    private ArrayList<VendaModel> vendas;

    public ConsultaVendaController() throws ParseException, SQLException {
        view_consvenda = new ConsultaVenda();
        dao_venda = new VendaDAO();

        vendas = dao_venda.pesquisarTodasVendas();
        view_consvenda.desenharTelaConsulta();
        view_consvenda.preencherTabela(vendas);

        view_consvenda.addPesquisarListener(new PesquisarListener());
        view_consvenda.addIncluirVendaListener(new IncluirVendaListener());
        view_consvenda.addAlterarVendaListener(new AlterarVendaListener());
        view_consvenda.addExcluirVendaListener(new ExcluirVendaListener());
        view_consvenda.addSelecionarFiltroListener(new SelecionarFiltroListener());
        view_consvenda.addSairListener(new SairListener());
        view_consvenda.addDoubleClickListener(new TableClickListener());
    }

    public ConsultaVendaController(String dataFixa) throws ParseException, SQLException {
        view_consvenda = new ConsultaVenda();
        dao_venda = new VendaDAO();

        vendas = dao_venda.pesquisarTodasVendas();
        view_consvenda.desenharTelaConsulta();
        view_consvenda.preencherTabela(vendas);
        view_consvenda.inserirDataFixaVenda(dataFixa);

        view_consvenda.addPesquisarListener(new PesquisarListener());
        view_consvenda.addIncluirVendaListener(new IncluirVendaListener());
        view_consvenda.addAlterarVendaListener(new AlterarVendaListener());
        view_consvenda.addExcluirVendaListener(new ExcluirVendaListener());
        view_consvenda.addSelecionarFiltroListener(new SelecionarFiltroListener());
        view_consvenda.addSairListener(new SairListener());
        view_consvenda.addDoubleClickListener(new TableClickListener());
    }

    class IncluirVendaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (view_consvenda.getDataFixaVenda().equals("  /  /20  ")) {
                    CadastroVendaController cadastro = new CadastroVendaController();
                } else {
                    CadastroVendaController cadastro = new CadastroVendaController(view_consvenda.getDataFixaVenda());
                }
                view_consvenda.fecharTela();
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class AlterarVendaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_consvenda.verificarLinhaSelecionadaTabela()) {
                try {
                    CadastroVendaController cadastro = new CadastroVendaController(view_consvenda.getVendaAlteracao());
                    view_consvenda.fecharTela();
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a venda", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    class SelecionarFiltroListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    view_consvenda.selecionarFiltro();
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    class PesquisarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (view_consvenda.verificaPesquisaVazia()) {
                try {
                    vendas = dao_venda.pesquisarVendaEspecificaTabela(view_consvenda.getQueryPesquisa());
                    view_consvenda.preencherTabela_Pesquisa(vendas);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    vendas = dao_venda.pesquisarTodasVendas();
                    view_consvenda.preencherTabela(vendas);
                } catch (SQLException ex) {
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    class ExcluirVendaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view_consvenda.verificarLinhaSelecionadaTabela()) {
                if (JOptionPane.showConfirmDialog(null, "Deseja excluir a venda?\nTodos as parcelas geradas serão excluidas", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        dao_venda.excluirVenda(view_consvenda.idLinhaSelecionadaTabela());
                        vendas = dao_venda.pesquisarTodasVendas();
                        view_consvenda.preencherTabela(vendas);
                        JOptionPane.showMessageDialog(null, "Venda Excluida Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a venda", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    class SairListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view_consvenda.fecharTela();
        }

    }

    class TableClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2 && view_consvenda.verificarLinhaSelecionadaTabela()) {
                try {
                    CadastroVendaController cadastro = new CadastroVendaController(view_consvenda.getVendaAlteracao());
                    view_consvenda.fecharTela();
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaVendaController.class.getName()).log(Level.SEVERE, null, ex);
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
