/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ClienteModel;
import view.*;

/**
 *
 * @author Guilherme
 */
public class CadastroClienteController {

    private CadastroCliente view_cadcliente;
    private ClienteDAO dao_cliente;

    public CadastroClienteController() throws ParseException {
        view_cadcliente = new CadastroCliente();
        dao_cliente = new ClienteDAO();

        view_cadcliente.desenharTelaCadastro();

        view_cadcliente.addGravarListener(new GravarListener());
        view_cadcliente.addCancelarListener(new CancelarListener());
        view_cadcliente.addSairListener(new SairListener());
    }

    public CadastroClienteController(ClienteModel cliente) throws ParseException {
        view_cadcliente = new CadastroCliente();
        dao_cliente = new ClienteDAO();

        view_cadcliente.desenharTelaCadastro();
        view_cadcliente.preencheTxt(cliente);

        view_cadcliente.addGravarListener(new AlterarListener());
        view_cadcliente.addCancelarListener(new CancelarListener());
        view_cadcliente.addSairListener(new SairListener());
    }

    class GravarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                if (view_cadcliente.validadorCamposCliente()) {
                    dao_cliente.cadastrarCliente(view_cadcliente.getCliente());
                    view_cadcliente.limparTxt();
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    view_cadcliente.fecharTela();
                    ConsultaClienteController consulta = new ConsultaClienteController();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class AlterarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                dao_cliente.alterarCliente(view_cadcliente.getCliente());
                JOptionPane.showMessageDialog(null, "Cliente Alterado Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class CancelarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_cadcliente.limparTxt();
        }

    }

    class SairListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_cadcliente.fecharTela();
            try {
                ConsultaClienteController consulta = new ConsultaClienteController();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
