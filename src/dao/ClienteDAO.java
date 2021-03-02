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

/**
 *
 * @author Guilherme
 */
public class ClienteDAO {

    public ArrayList<ClienteModel> pesquisarTodosClientes() throws SQLException {
        String SQL = "select * from cliente where excluido = 0 order by nome asc";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<ClienteModel> clientes = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    clientes.add(new ClienteModel(rs.getInt("cod_cliente"),
                            rs.getString("nome"),
                            rs.getString("contato"),
                            rs.getString("cidade"),
                            rs.getString("endereco"),
                            rs.getString("estado"),
                            rs.getString("cep"),
                            rs.getString("telefone"),
                            rs.getInt("porcentagem"),
                            rs.getInt("prazo"),
                            rs.getString("cgc"),
                            rs.getString("inscricao_est")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return clientes;
    }

    public ArrayList<ClienteModel> pesquisarClienteEspecificoTabela(String campo, String valor) throws SQLException {
        String SQL = "select * from cliente where " + campo + "  like '" + valor + "'";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        ResultSet rs = pst.executeQuery();
        ArrayList<ClienteModel> clientes = new ArrayList();

        try {
            if (rs.first()) {
                do {
                    clientes.add(new ClienteModel(rs.getInt("cod_cliente"),
                            rs.getString("nome"),
                            rs.getString("contato"),
                            rs.getString("cidade"),
                            rs.getString("endereco"),
                            rs.getString("estado"),
                            rs.getString("cep"),
                            rs.getString("telefone"),
                            rs.getInt("porcentagem"),
                            rs.getInt("prazo"),
                            rs.getString("cgc"),
                            rs.getString("inscricao_est")
                    ));
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }

        return clientes;
    }

    public void cadastrarCliente(ClienteModel cliente) throws SQLException {
        String SQL = "insert into cliente(nome,contato,cidade,endereco,estado,cep,telefone,"
                + "porcentagem,prazo,cgc,inscricao_est) values (?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getContato());
        pst.setString(3, cliente.getCidade());
        pst.setString(4, cliente.getEndereco());
        pst.setString(5, cliente.getEstado());
        pst.setString(6, cliente.getCep());
        pst.setString(7, cliente.getTelefone());
        pst.setInt(8, cliente.getPorcentagem());
        pst.setInt(9, cliente.getPrazo());
        pst.setString(10, cliente.getCgc());
        pst.setString(11, cliente.getInscricao_est());
        pst.execute();
        pst.close();
        con.close();
    }

    public void alterarCliente(ClienteModel cliente) throws SQLException {
        String SQL = "update cliente set nome=?, contato=?, cidade=?, endereco=?, estado=?,"
                + "cep=?, telefone=?, porcentagem=?, prazo=?, cgc=?, inscricao_est=? where cod_cliente=?";
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getContato());
        pst.setString(3, cliente.getCidade());
        pst.setString(4, cliente.getEndereco());
        pst.setString(5, cliente.getEstado());
        pst.setString(6, cliente.getCep());
        pst.setString(7, cliente.getTelefone());
        pst.setInt(8, cliente.getPorcentagem());
        pst.setInt(9, cliente.getPrazo());
        pst.setString(10, cliente.getCgc());
        pst.setString(11, cliente.getInscricao_est());
        pst.setInt(12, cliente.getCod_cliente());
        pst.execute();
        pst.close();
        con.close();
    }

    public void excluirCliente(int cod_cliente) throws SQLException {
        String SQL = "update cliente set excluido=1 where cod_cliente=" + cod_cliente;
        Connection con = new Conexao().getConnectionMySQL();
        PreparedStatement pst = con.prepareStatement(SQL);
        pst.execute();
        pst.close();
        con.close();
    }
}
