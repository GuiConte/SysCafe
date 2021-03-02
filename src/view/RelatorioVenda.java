package view;

import dao.Conexao;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelatorioVenda {

    private JFrame janela;
    private JPanel painelPrincipal, painelCliente;
    private JLabel lblDataInicial, lblDataFinal, lblNomeCliente;
    private JFormattedTextField txtDataInicial, txtDataFinal;
    private JTextField txtCliente;
    private JButton btnVisualizar, btnSair;

    public void desenharTela() throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();

        janela = new JFrame("Relatório");

        janela.setSize(355, 260);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        //-------------- CRIANDO PAINEL PRINCIAL --------------------//
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 02, 340, 170);
        painelPrincipal.setBorder(BorderFactory.createTitledBorder(""));

        lblDataInicial = new JLabel("Data Inicial");
        lblDataInicial.setBounds(80, 20, 300, 22);
        painelPrincipal.add(lblDataInicial);

        txtDataInicial = new JFormattedTextField(new MaskFormatter(" ##/##/20##"));
        txtDataInicial.setBounds(80, 40, 80, 22);
        painelPrincipal.add(txtDataInicial);

        lblDataFinal = new JLabel("Data Final");
        lblDataFinal.setBounds(180, 20, 300, 22);
        painelPrincipal.add(lblDataFinal);

        txtDataFinal = new JFormattedTextField(new MaskFormatter(" ##/##/20##"));
        txtDataFinal.setBounds(180, 40, 80, 22);
        painelPrincipal.add(txtDataFinal);

        painelCliente = new JPanel();
        painelCliente.setLayout(null);
        painelCliente.setSize(320, 70);
        painelCliente.setLocation(12, 80);
        painelCliente.setBorder(BorderFactory.createTitledBorder(""));
        painelPrincipal.add(painelCliente);

       lblNomeCliente = new JLabel("Nome Cliente");
        lblNomeCliente.setFont(new Font("Arial", 1, 13));
        lblNomeCliente.setBounds(10, 10, 100, 22);
        painelCliente.add(lblNomeCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(10, 40, 300, 22);
        painelCliente.add(txtCliente);

        // ---------------- BOTAO VISUALIZAR-----//
        btnVisualizar = new JButton("Visualizar");
        btnVisualizar.setBounds(70, 180, 105, 35);
        btnVisualizar.setIcon(new ImageIcon(cl.getResource("Imagens/imprimir.png")));
        btnVisualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnVisualizar.setFont(new Font("Arial", 1, 13));
        btnVisualizar.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                this.gerarRelatorio();
            } catch (JRException ex) {
                Logger.getLogger(RelatorioVenda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(RelatorioVenda.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        janela.add(btnVisualizar);

        //------------------------------------------------------//
        // ---------------- BOTAO SAIR-----//
        btnSair = new JButton("Sair");
        btnSair.setBounds(180, 180, 105, 35);
        btnSair.setIcon(new ImageIcon(cl.getResource("Imagens/sair.png")));
        btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSair.setFont(new Font("Arial", 1, 13));
        btnSair.addActionListener((java.awt.event.ActionEvent evt) -> {

            janela.dispose();
        });
        janela.add(btnSair);

        janela.add(painelPrincipal);
        janela.setVisible(true);
    }

    private void gerarRelatorio() throws JRException, ParseException {
        
        HashMap filtro = new HashMap();
        DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial = conversor.parse(txtDataInicial.getText());
        Date dataFinal = conversor.parse(txtDataFinal.getText());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (txtCliente.getText().equals("")) {
            filtro.put("nome", '%');
        } else {
            filtro.put("nome", txtCliente.getText());
        }

        filtro.put("data_inicio", dateFormat.format(dataInicial));
        filtro.put("data_fim", dateFormat.format(dataFinal));
        
        try {
            JasperPrint print = JasperFillManager.fillReport("C:/sys_cafe/Report/RelatorioVenda.jasper", filtro, Conexao.getConnectionMySQL());

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception ex) {
            System.out.println("Relatório não encontrado" + ex.getMessage());
        }

    }
}
