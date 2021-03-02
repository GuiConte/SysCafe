/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.RecebimentoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.RecebimentoModel;
import view.CadastroRecebimento;
import view.ConsultaRecebimento;

/**
 *
 * @author Guilherme
 */
public class CadastroRecebimentoController {

    private CadastroRecebimento view_cadastro;
    private ConsultaRecebimento view_consulta;
    private RecebimentoDAO dao_recebimento;
    
    private RecebimentoModel recebimento,recebimentoTabela;

    public CadastroRecebimentoController(ConsultaRecebimento consulta,RecebimentoModel recebimento) throws ParseException {
        view_cadastro = new CadastroRecebimento();
        dao_recebimento = new RecebimentoDAO();
        view_consulta = consulta;
        recebimentoTabela = recebimento;

        view_cadastro.desenharTelaCadastro(recebimento);
        
        view_cadastro.addReceberListener(new ReceberListener());
    }
    
    class ReceberListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                recebimento = view_cadastro.getRecebimento();
                float valor_restante = recebimento.getValor_restante() - recebimento.getValor_pago();
                float valor_pago = recebimentoTabela.getValor_pago() + recebimento.getValor_pago();
                int recebido = 0;
                if(valor_restante == 0 && valor_pago == recebimento.getValor_total()){
                    recebido = 1;
                }else{
                    recebido = 0;
                }
                
                recebimento.setValor_restante(valor_restante);
                recebimento.setValor_pago(valor_pago);
                
                dao_recebimento.atualizarRecebimento(recebimento,recebido);
                view_consulta.preencherTabela(dao_recebimento.pesquisarTodosRecebimentos());
                JOptionPane.showMessageDialog(null, "Recebimento Feito Com Sucesso !", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view_cadastro.fecharTela();
            } catch (ParseException ex) {
                Logger.getLogger(CadastroRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
