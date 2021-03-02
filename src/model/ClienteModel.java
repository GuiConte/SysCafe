/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Guilherme
 */
public class ClienteModel {

    private int cod_cliente;
    private String nome;
    private String contato;
    private String cidade;
    private String endereco;
    private String estado;
    private String cep;
    private String telefone;
    private int porcentagem;
    private int prazo;
    private String cgc;
    private String inscricao_est;

    public ClienteModel(int cod_cliente, String nome, String contato, String cidade, String endereco, String estado,
            String cep, String telefone, int porcentagem, int prazo, String cgc, String inscricao_est) {
        this.cod_cliente = cod_cliente;
        this.nome = nome;
        this.contato = contato;
        this.cidade =cidade;
        this.endereco = endereco;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.porcentagem = porcentagem;
        this.prazo = prazo;
        this.cgc= cgc;
        this.inscricao_est = inscricao_est;
    }

    /**
     * @return the cod_cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * @param cod_cliente the cod_cliente to set
     */
    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the contato
     */
    public String getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(String contato) {
        this.contato = contato;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the porcentagem
     */
    public int getPorcentagem() {
        return porcentagem;
    }

    /**
     * @param porcentagem the porcentagem to set
     */
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    /**
     * @return the prazo
     */
    public int getPrazo() {
        return prazo;
    }

    /**
     * @param prazo the prazo to set
     */
    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    /**
     * @return the cgc
     */
    public String getCgc() {
        return cgc;
    }

    /**
     * @param cgc the cgc to set
     */
    public void setCgc(String cgc) {
        this.cgc = cgc;
    }

    /**
     * @return the inscricao_est
     */
    public String getInscricao_est() {
        return inscricao_est;
    }

    /**
     * @param inscricao_est the inscricao_est to set
     */
    public void setInscricao_est(String inscricao_est) {
        this.inscricao_est = inscricao_est;
    }

}
