/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.*;
import dao.*;
import model.ClienteModel;
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
public class ConsultaClienteController {

    private ConsultaCliente view_conscliente;
    private ClienteDAO dao_cliente;
    private ArrayList<ClienteModel> clientes;

    public ConsultaClienteController() throws SQLException {
        view_conscliente = new ConsultaCliente();
        dao_cliente = new ClienteDAO();

        clientes = dao_cliente.pesquisarTodosClientes();
        view_conscliente.desenharTelaConsulta();
        view_conscliente.preencherTabela(clientes);

        view_conscliente.addPesquisarListener(new PesquisarListener());
        view_conscliente.addCadastrarListener(new CadastrarListener());
        view_conscliente.addAlterarListener(new AlterarListener());
        view_conscliente.addExcluirListener(new ExcluirListener());
        view_conscliente.addSairListener(new SairListener());
        view_conscliente.addDoubleClickListener(new TableClickListener());

    }

    class PesquisarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (view_conscliente.verificaPesquisaVazia()) {
                try {
                    clientes = dao_cliente.pesquisarClienteEspecificoTabela(view_conscliente.getCbPesquisa(),
                            view_conscliente.getTxtPesquisa());
                    view_conscliente.preencherTabela_Pesquisa(clientes);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    clientes = dao_cliente.pesquisarTodosClientes();
                    view_conscliente.preencherTabela(clientes);
                } catch (SQLException ex) {
                }
            }
        }

    }

    class CadastrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_conscliente.fecharTela();
            try {
                CadastroClienteController cadastro = new CadastroClienteController();
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class AlterarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (view_conscliente.verificarLinhaSelecionadaTabela()) {
                view_conscliente.fecharTela();
                try {
                    CadastroClienteController cadastro = new CadastroClienteController(view_conscliente.getClienteAlteracao());
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o cliente", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class ExcluirListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int id = view_conscliente.getCodClienteSelecionadoTabela();
            if (id != -1) {
                if (JOptionPane.showConfirmDialog(null, "Deseja excluir o cliente?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        dao_cliente.excluirCliente(id);
                        JOptionPane.showMessageDialog(null, "Cliente Excluido", "Exclusão de Cliente", JOptionPane.INFORMATION_MESSAGE);
                        view_conscliente.limparTabela();
                        clientes = dao_cliente.pesquisarTodosClientes();
                        view_conscliente.preencherTabela(clientes);
                    } catch (SQLException ex) {
                        Logger.getLogger(ConsultaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

    }

    class SairListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_conscliente.fecharTela();
        }

    }

    class TableClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2 && view_conscliente.verificarLinhaSelecionadaTabela()) {
                try {
                    CadastroClienteController cadastro = new CadastroClienteController(view_conscliente.getClienteAlteracao());
                    view_conscliente.fecharTela();
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
