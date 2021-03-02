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
public class Venda_ProdutoModel {

    private int cod_venda_produto;
    private int cod_produto;
    private String produto;
    private float quantidade;
    private float preco;
    private float peso_total;
    private float valor;

    public Venda_ProdutoModel(int cod_venda_produto, int cod_produto, String produto, float quantidade, float preco,
            float peso_total, float valor) {
        this.cod_venda_produto = cod_venda_produto;
        this.cod_produto = cod_produto;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.peso_total = peso_total;
        this.valor = valor;
    }

    public Venda_ProdutoModel(int cod_produto, String produto, float quantidade, float preco, float peso_total, float valor) {
        this.cod_produto = cod_produto;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.peso_total = peso_total;
        this.valor = valor;
    }

    public Venda_ProdutoModel(int cod_produto, float quantidade, float preco, float peso_total, float valor) {
        this.cod_produto = cod_produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.peso_total = peso_total;
        this.valor = valor;
    }

    /**
     * @return the cod_venda_produto
     */
    public int getCod_venda_produto() {
        return cod_venda_produto;
    }

    /**
     * @param cod_venda_produto the cod_venda_produto to set
     */
    public void setCod_venda_produto(int cod_venda_produto) {
        this.cod_venda_produto = cod_venda_produto;
    }

    /**
     * @return the cod_produto
     */
    public int getCod_produto() {
        return cod_produto;
    }

    /**
     * @param cod_produto the cod_produto to set
     */
    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    /**
     * @return the quantidade
     */
    public float getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the peso_total
     */
    public float getPeso_total() {
        return peso_total;
    }

    /**
     * @param peso_total the peso_total to set
     */
    public void setPeso_total(float peso_total) {
        this.peso_total = peso_total;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

}
