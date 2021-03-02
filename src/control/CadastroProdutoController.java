/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ProdutoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ProdutoModel;
import view.*;

/**
 *
 * @author Guilherme
 */
public class CadastroProdutoController {

    private CadastroProduto view_cadProduto;
    private ProdutoDAO dao_Produto;

    public CadastroProdutoController() throws ParseException {
        view_cadProduto = new CadastroProduto();
        dao_Produto = new ProdutoDAO();

        view_cadProduto.desenharTelaCadastro();

        view_cadProduto.addGravarListener(new GravarListener());
        view_cadProduto.addCancelarListener(new CancelarListener());
        //view_cadProduto.addSairListener(new SairListener());
    }

    public CadastroProdutoController(ProdutoModel Produto) throws ParseException {
        view_cadProduto = new CadastroProduto();
        dao_Produto = new ProdutoDAO();

        view_cadProduto.desenharTelaCadastro();
        view_cadProduto.preencheTxt(Produto);

        view_cadProduto.addGravarListener(new AlterarListener());
        view_cadProduto.addCancelarListener(new SairListener());
        //view_cadProduto.addSairListener(new SairListener());
    }

    class GravarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                dao_Produto.cadastrarProduto(view_cadProduto.getProduto());
                view_cadProduto.limparTxt();
                JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class AlterarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                dao_Produto.alterarProduto(view_cadProduto.getProduto());
                JOptionPane.showMessageDialog(null, "Produto Alterado Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class CancelarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_cadProduto.limparTxt();
        }

    }

    class SairListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view_cadProduto.fecharTela();
            try {
                ConsultaProdutoController consulta = new ConsultaProdutoController();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
