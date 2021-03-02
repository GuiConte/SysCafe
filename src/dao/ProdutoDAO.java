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
import model.ProdutoModel;

/**
 *
 * @author Guilherme
 */
public class ProdutoDAO {

    public ArrayList<ProdutoModel> pesquisarTodosProdutos() throws SQLException {
        String SQL = "select * from produto where excluido = 0";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<ProdutoModel> Produtos = new ArrayList();

        try {
            if (rs.first()) {
                do {
                   Produtos.add(new ProdutoModel(rs.getInt("cod_produto"),
                            rs.getString("produto"),
                            rs.getFloat("peso")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return Produtos;
    }

    public ArrayList<ProdutoModel> pesquisarProdutoEspecificoTabela(String query) throws SQLException {
        String SQL = "select * from produto where " + query;
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<ProdutoModel> Produtos = new ArrayList();

        try {
            if (rs.first()) {
                do {
                   Produtos.add(new ProdutoModel(rs.getInt("cod_produto"),
                            rs.getString("produto"),
                            rs.getFloat("peso")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return Produtos;
    }

    public void cadastrarProduto(ProdutoModel Produto) throws SQLException {
        String SQL = "insert into produto(produto,peso) values (?,?)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, Produto.getProduto());
        pst.setFloat(2, Produto.getPeso());
        pst.execute();
        pst.close();
        con.close();
    }

    public void alterarProduto(ProdutoModel Produto) throws SQLException {
        String SQL = "update produto set produto=?, peso=? where cod_produto=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, Produto.getProduto());
        pst.setFloat(2, Produto.getPeso());
        pst.setInt(3, Produto.getCod_produto());
        pst.execute();
        pst.close();
        con.close();
    }

    public void excluirProduto(int cod_produto) throws SQLException {
        String SQL = "update produto set excluido=1 where cod_Produto=" + cod_produto;
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.execute();
        pst.close();
        con.close();
    }
}
