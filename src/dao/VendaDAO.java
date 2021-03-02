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
import model.ClienteModel;
import model.VendaModel;
import model.Venda_ProdutoModel;

/**
 *
 * @author Guilherme
 */
public class VendaDAO {

    public void cadastrarVenda(VendaModel venda) throws SQLException {
        String SQL = "insert into venda(data_venda,cod_cliente,forma_pagamento,"
                + "data_vencimento,comissao,total_venda) values (?,?,?,?,?,?)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, venda.getData_venda());
        pst.setInt(2, venda.getCod_cliente());
        pst.setInt(3, venda.getForma_pagamento());
        pst.setString(4, venda.getData_vencimento());
        pst.setFloat(5, venda.getComissao());
        pst.setFloat(6, venda.getTotal_venda());
        pst.execute();
        pst.close();
        con.close();
    }

    public void alterarVenda(VendaModel venda) throws SQLException {
        String SQL = "update venda set data_venda=?,cod_cliente=?,forma_pagamento=?,"
                + "data_vencimento=?,comissao=?,total_venda=? where cod_venda=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, venda.getData_venda());
        pst.setInt(2, venda.getCod_cliente());
        pst.setInt(3, venda.getForma_pagamento());
        pst.setString(4, venda.getData_vencimento());
        pst.setFloat(5, venda.getComissao());
        pst.setFloat(6, venda.getTotal_venda());
        pst.setInt(7, venda.getCod_pedido());
        pst.execute();
        pst.close();
        con.close();
    }

    public int consultarID() throws SQLException {
        String SQL = "SELECT AUTO_INCREMENT FROM information_schema.tables "
                + "WHERE table_name = 'venda' AND table_schema = 'sys_cafe' ;";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        int id = -1;
        if (rs.first()) {
            id = rs.getInt("AUTO_INCREMENT");
        }
        return id;
    }

    public void gravarProdutos(int id, Venda_ProdutoModel produto) throws SQLException {
        String SQL = "insert into venda_produto(cod_venda,cod_produto,quantidade,"
                + "preco,peso_total,valor) values (?,?,?,?,?,?)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, id);
        pst.setInt(2, produto.getCod_produto());
        pst.setFloat(3, produto.getQuantidade());
        pst.setFloat(4, produto.getPreco());
        pst.setFloat(5, produto.getPeso_total());
        pst.setFloat(6, produto.getValor());
        pst.execute();
        pst.close();
        con.close();
    }

    public void alterarProdutos(int id, Venda_ProdutoModel produto) throws SQLException {
        String SQL = "update venda_produto set cod_produto=?,quantidade=?,preco=?,"
                + "peso_total=?,valor=? where cod_venda_produto=? AND cod_venda=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, produto.getCod_produto());
        pst.setFloat(2, produto.getQuantidade());
        pst.setFloat(3, produto.getPreco());
        pst.setFloat(4, produto.getPeso_total());
        pst.setFloat(5, produto.getValor());
        pst.setInt(6, produto.getCod_venda_produto());
        pst.setInt(7, id);
        pst.execute();
        pst.close();
        con.close();
    }

    public void excluirProdutos(int id, Venda_ProdutoModel produto) throws SQLException {
        String SQL = "delete from venda_produto where cod_venda_produto=? AND cod_venda=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, produto.getCod_venda_produto());
        pst.setInt(2, id);
        pst.execute();
        pst.close();
        con.close();
    }

    public ArrayList<VendaModel> pesquisarTodasVendas() throws SQLException {
        String SQL = "select c.nome,v.*,r.valor_entrada from venda v "
                + " join cliente c on v.cod_cliente = c.cod_cliente"
                + " join recebimento r on v.cod_venda = r.cod_venda";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<VendaModel> vendas = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    vendas.add(new VendaModel(rs.getInt("v.cod_venda"),
                            rs.getString("v.data_venda"),
                            rs.getInt("v.cod_cliente"),
                            rs.getString("c.nome"),
                            rs.getInt("v.forma_pagamento"),
                            rs.getString("v.data_vencimento"),
                            rs.getFloat("v.comissao"),
                            rs.getFloat("v.total_venda"),
                            rs.getFloat("r.valor_entrada")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }
        return vendas;
    }

    public ArrayList<VendaModel> pesquisarVendaEspecificaTabela(String query) throws SQLException {
        String SQL = "select c.nome,v.*,r.valor_entrada from venda v "
                + " join cliente c on v.cod_cliente = c.cod_cliente"
                + " join recebimento r on v.cod_venda = r.cod_venda"
                + " where " + query;
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<VendaModel> vendas = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    vendas.add(new VendaModel(rs.getInt("v.cod_venda"),
                            rs.getString("v.data_venda"),
                            rs.getInt("v.cod_cliente"),
                            rs.getString("c.nome"),
                            rs.getInt("v.forma_pagamento"),
                            rs.getString("v.data_vencimento"),
                            rs.getFloat("v.comissao"),
                            rs.getFloat("v.total_venda"),
                            rs.getFloat("r.valor_entrada")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return vendas;
    }

    public ArrayList<Venda_ProdutoModel> pesquisarProdutosVenda(int id) throws SQLException {
        String SQL = "select vp.*,p.produto from venda_produto vp "
                + " join produto p on vp.cod_produto = p.cod_produto"
                + " where cod_venda = " + id;
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<Venda_ProdutoModel> produtosVendidos = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    produtosVendidos.add(new Venda_ProdutoModel(rs.getInt("cod_venda_produto"),
                            rs.getInt("vp.cod_produto"),
                            rs.getString("p.produto"),
                            rs.getFloat("vp.quantidade"),
                            rs.getFloat("vp.preco"),
                            rs.getFloat("vp.peso_total"),
                            rs.getFloat("vp.valor")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return produtosVendidos;
    }

    public void excluirVenda(int id) throws SQLException {
        String SQL = "delete from venda where cod_venda=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setInt(1, id);
        pst.execute();
        pst.close();
        con.close();
    }

}
