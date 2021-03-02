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
public class RecebimentoModel {

    private int cod_recebimento;
    private int cod_venda;
    private String cliente;
    private String data_vencimento;
    private String data_venda;
    private float valor_total;
    private String data_recebimento;
    private float valor_pago;
    private boolean recebido;
    private float valor_restante;
    private float valor_entrada;

    public RecebimentoModel(int cod_recebimento, int cod_venda, String cliente,String data_venda, String data_vencimento,
            float valor_total, String data_recebimento, float valor_pago, boolean recebido, float valor_restante,
            float valor_entrada) {
        this.cod_recebimento = cod_recebimento;
        this.cod_venda = cod_venda;
        this.cliente = cliente;
        this.data_venda = data_venda;
        this.data_vencimento = data_vencimento;
        this.valor_total = valor_total;
        this.data_recebimento = data_recebimento;
        this.valor_pago = valor_pago;
        this.recebido = recebido;
        this.valor_restante = valor_restante;
        this.valor_entrada = valor_entrada;
        
    }

    public RecebimentoModel(int cod_recebimento, int cod_venda, float valor_total, String data_recebimento,
            float valor_pago, float valor_restante) {
        this.cod_recebimento = cod_recebimento;
        this.cod_venda = cod_venda;
        this.valor_total = valor_total;
        this.data_venda=data_venda;
        this.data_recebimento = data_recebimento;
        this.valor_pago = valor_pago;
        this.valor_restante = valor_restante;
    }

    
    
    /**
     * @return the cod_recebimento
     */
    public int getCod_recebimento() {
        return cod_recebimento;
    }

    /**
     * @param cod_recebimento the cod_recebimento to set
     */
    public void setCod_recebimento(int cod_recebimento) {
        this.cod_recebimento = cod_recebimento;
    }

    /**
     * @return the cod_venda
     */
    public int getCod_venda() {
        return cod_venda;
    }

    /**
     * @param cod_venda the cod_venda to set
     */
    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    /**
     * @return the data_recebimento
     */
    public String getData_recebimento() {
        return data_recebimento;
    }

    /**
     * @param data_recebimento the data_recebimento to set
     */
    public void setData_recebimento(String data_recebimento) {
        this.data_recebimento = data_recebimento;
    }

    /**
     * @return the valor_pago
     */
    public float getValor_pago() {
        return valor_pago;
    }

    /**
     * @param valor_pago the valor_pago to set
     */
    public void setValor_pago(float valor_pago) {
        this.valor_pago = valor_pago;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
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
     * @return the valor_total
     */
    public float getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the recebido
     */
    public boolean isRecebido() {
        return recebido;
    }

    /**
     * @param recebido the recebido to set
     */
    public void setRecebido(boolean recebido) {
        this.recebido = recebido;
    }

    /**
     * @return the valor_restante
     */
    public float getValor_restante() {
        return valor_restante;
    }

    /**
     * @param valor_restante the valor_restante to set
     */
    public void setValor_restante(float valor_restante) {
        this.valor_restante = valor_restante;
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

}
