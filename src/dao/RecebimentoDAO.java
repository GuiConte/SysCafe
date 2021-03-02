/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.RecebimentoModel;

/**
 *
 * @author Guilherme
 */
public class RecebimentoDAO {

    public void cadastrarRecebimento(int id, float valor_pago, float valor_restante, String data_recebimento) throws SQLException {
        String SQL = "insert into recebimento(cod_venda,valor_pago,valor_restante,data_recebimento,valor_entrada)"
                + " values (?,?,?,?,?)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, id);
        pst.setFloat(2, valor_pago);
        pst.setFloat(3, valor_restante);
        pst.setString(4, data_recebimento);
        pst.setFloat(5, valor_pago);
        pst.execute();
        pst.close();
        con.close();
    }

    public void cadastrarRecebimentoAVista(int id, String data, float valor) throws SQLException {
        String SQL = "insert into recebimento(cod_venda,data_recebimento,valor_pago,recebido) values (?,?,?,1)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, id);
        pst.setString(2, data);
        pst.setFloat(3, valor);
        pst.execute();
        pst.close();
        con.close();
    }

    public ArrayList<RecebimentoModel> pesquisarTodosRecebimentos() throws SQLException {
        String SQL = "SELECT r.*,v.total_venda,v.data_venda,v.data_vencimento,c.nome FROM recebimento r"
                + " join venda v on r.cod_venda = v.cod_venda"
                + " join cliente c on v.cod_cliente = c.cod_cliente"
                + " order by r.recebido,v.data_vencimento";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<RecebimentoModel> recebimentos = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    recebimentos.add(new RecebimentoModel(rs.getInt("cod_recebimento"),
                            rs.getInt("cod_venda"),
                            rs.getString("nome"),
                            rs.getString("data_venda"),
                            rs.getString("data_vencimento"),
                            rs.getFloat("total_venda"),
                            rs.getString("data_recebimento"),
                            rs.getFloat("valor_pago"),
                            rs.getBoolean("recebido"),
                            rs.getFloat("valor_restante"),
                            rs.getFloat("valor_entrada")
                            
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }
        return recebimentos;
    }

    public ArrayList<RecebimentoModel> pesquisarRecebimentoEspecificoTabela(String query) throws SQLException {
        String SQL = "SELECT r.*,v.total_venda,v.data_vencimento,v.data_venda,c.nome FROM recebimento r"
                + " join venda v on r.cod_venda = v.cod_venda"
                + " join cliente c on v.cod_cliente = c.cod_cliente"
                + " where " + query
                + " order by r.recebido,v.data_vencimento";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<RecebimentoModel> recebimentos = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    recebimentos.add(new RecebimentoModel(rs.getInt("cod_recebimento"),
                            rs.getInt("cod_venda"),
                            rs.getString("nome"),
                            rs.getString("data_venda"),
                            rs.getString("data_vencimento"),
                            rs.getFloat("total_venda"),
                            rs.getString("data_recebimento"),
                            rs.getFloat("valor_pago"),
                            rs.getBoolean("recebido"),
                            rs.getFloat("valor_restante"),
                            rs.getFloat("valor_entrada")
                            
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return recebimentos;
    }

    public void excluirRecebimento(int id, float valor_restante) throws SQLException {
        String SQL = "update recebimento set data_recebimento = null,valor_pago = null, valor_restante = ?, recebido = 0 "
                + "where cod_venda = ?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setFloat(1, valor_restante);
        pst.setInt(2, id);
        pst.execute();
        pst.close();
        con.close();
    }

    public void excluirRecebimento(int id, float valor_pago, float valor_restante, String data_recebimento) throws SQLException {
        String SQL = "update recebimento set data_recebimento = ?,valor_pago = ?, valor_restante = ?, recebido = 0 "
                + "where cod_venda = ?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, data_recebimento);
        pst.setFloat(2, valor_pago);
        pst.setFloat(3, valor_restante);
        pst.setInt(4, id);
        pst.execute();
        pst.close();
        con.close();
    }

    public void atualizarRecebimento(RecebimentoModel recebimento, int recebido) throws SQLException {
        String SQL = "update recebimento set data_recebimento = ?,valor_pago = ?, recebido = ?, valor_restante = ? "
                + "where cod_venda = ? AND cod_recebimento = ?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, recebimento.getData_recebimento());
        pst.setFloat(2, recebimento.getValor_pago());
        pst.setInt(3, recebido);
        pst.setFloat(4, recebimento.getValor_restante());
        pst.setInt(5, recebimento.getCod_venda());
        pst.setInt(6, recebimento.getCod_recebimento());
        pst.execute();
        pst.close();
        con.close();
    }

    public void atualizarRecebimentoEntrada(int cod_venda, float valor_entrada) throws SQLException {
        String SQL = "update recebimento set valor_entrada = ? where cod_venda = ?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setFloat(1, valor_entrada);
        pst.setInt(2, cod_venda);
        pst.execute();
        pst.close();
        con.close();
    }

    public void atualizarRecebimentoAVista(int id, String data, float valor) throws SQLException {
        String SQL = "update recebimento set data_recebimento = ?, valor_pago = ?, valor_restante = 0, recebido = 1, "
                + "valor_entrada = 0 where cod_venda = ?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, data);
        pst.setFloat(2, valor);
        pst.setInt(3, id);
        pst.execute();
        pst.close();
        con.close();
    }

}
