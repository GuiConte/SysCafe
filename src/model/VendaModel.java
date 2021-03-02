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
public class VendaModel {

    private int cod_pedido;
    private String data_venda;
    private int cod_cliente;
    private int forma_pagamento;
    private String data_vencimento;
    private float comissao;
    private float total_venda;
    private float valor_entrada;

    private String nome_cliente;

    public VendaModel(int cod_pedido, String data_venda, int cod_cliente, int forma_pagamento, String data_vencimento,
            float comissao, float total_venda) {
        this.cod_pedido = cod_pedido;
        this.data_venda = data_venda;
        this.cod_cliente = cod_cliente;
        this.forma_pagamento = forma_pagamento;
        this.data_vencimento = data_vencimento;
        this.comissao = comissao;
        this.total_venda = total_venda;
    }
    
    public VendaModel(int cod_pedido, String data_venda, int cod_cliente, int forma_pagamento, String data_vencimento,
            float comissao, float total_venda, float valor_entrada) {
        this.cod_pedido = cod_pedido;
        this.data_venda = data_venda;
        this.cod_cliente = cod_cliente;
        this.forma_pagamento = forma_pagamento;
        this.data_vencimento = data_vencimento;
        this.comissao = comissao;
        this.total_venda = total_venda;
        this.valor_entrada = valor_entrada;
    }

    public VendaModel(int cod_pedido, String data_venda, int cod_cliente,String nome_cliente, int forma_pagamento, String data_vencimento,
            float comissao, float total_venda,float valor_entrada) {
        this.cod_pedido = cod_pedido;
        this.data_venda = data_venda;
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.forma_pagamento = forma_pagamento;
        this.data_vencimento = data_vencimento;
        this.comissao = comissao;
        this.total_venda = total_venda;
        this.valor_entrada = valor_entrada;
    }

    /**
     * @return the cod_pedido
     */
    public int getCod_pedido() {
        return cod_pedido;
    }

    /**
     * @param cod_pedido the cod_pedido to set
     */
    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    /**
     * @return the data_venda
     */
    public String getData_venda() {
        return data_venda;
    }

    /**
     * @param data_venda the data_venda to set
     */
    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
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
     * @return the forma_pagamento
     */
    public int getForma_pagamento() {
        return forma_pagamento;
    }

    /**
     * @param forma_pagamento the forma_pagamento to set
     */
    public void setForma_pagamento(int forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    /**
     * @return the data_vencimento
     */
    public String getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the comissao
     */
    public float getComissao() {
        return comissao;
    }

    /**
     * @param comissao the comissao to set
     */
    public void setComissao(float comissao) {
        this.comissao = comissao;
    }

    /**
     * @return the total_venda
     */
    public float getTotal_venda() {
        return total_venda;
    }

    /**
     * @param total_venda the total_venda to set
     */
    public void setTotal_venda(float total_venda) {
        this.total_venda = total_venda;
    }

    /**
     * @return the nome_cliente
     */
    public String getNome_cliente() {
        return nome_cliente;
    }

    /**
     * @param nome_cliente the nome_cliente to set
     */
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    /**
     * @return the valor_entrada
     */
    public float getValor_entrada() {
        return valor_entrada;
    }

    /**
     * @param valor_entrada the valor_entrada to set
     */
    public void setValor_entrada(float valor_entrada) {
        this.valor_entrada = valor_entrada;
    }
    
    

}
