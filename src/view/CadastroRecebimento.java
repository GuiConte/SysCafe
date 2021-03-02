package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import model.RecebimentoModel;

public class CadastroRecebimento {

    private JFrame janela;
    private JPanel painelPrincipal;
    private JLabel lblCodigoVenda, lblVlrTotal, lblPagamento, lblDataRecebimento, lblDataVenda;
    private JFormattedTextField txtDataRecebimento, txtDataVenda;
    private JTextField txtCodigoVenda, txtVlrTotal, txtPagamento;
    private JButton btnReceber;

    private RecebimentoModel recebimento;

    public void desenharTelaCadastro(RecebimentoModel recebimento) throws ParseException {
        ClassLoader cl = this.getClass().getClassLoader();
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.recebimento = recebimento;
        janela = new JFrame("Recebimento");
        janela.setSize(355, 270);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        HashSet conj = new HashSet(janela.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        janela.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        //-------------- CRIANDO PAINEL PRINCIAL --------------------//
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 02, 340, 235);
        painelPrincipal.setBorder(BorderFactory.createTitledBorder(""));
        janela.add(painelPrincipal);

        //-----------------CODIGO--------------//
        lblCodigoVenda = new JLabel("Código Venda ");
        lblCodigoVenda.setBounds(20, 10, 120, 22);
        painelPrincipal.add(lblCodigoVenda);

        txtCodigoVenda = new JTextField();
        txtCodigoVenda.setBounds(20, 30, 80, 22);
        txtCodigoVenda.setEnabled(false);
        txtCodigoVenda.setText("" + recebimento.getCod_venda());
        painelPrincipal.add(txtCodigoVenda);

//---------------DATA VENDA-----------------//
        lblDataVenda = new JLabel("Data Venda");
        lblDataVenda.setBounds(135, 10, 120, 22);
        painelPrincipal.add(lblDataVenda);

        DateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVenda = conversor.parse(recebimento.getData_venda());

        txtDataVenda = new JFormattedTextField(new MaskFormatter("##/##/####"));
        txtDataVenda.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtDataVenda.setBounds(130, 30, 80, 22);
        txtDataVenda.setEnabled(false);
        txtDataVenda.setText(dateFormat.format(dataVenda));
        painelPrincipal.add(txtDataVenda);

        //---------------DATA VENDA-----------------//
        lblDataRecebimento = new JLabel("Data Recebimento");
        lblDataRecebimento.setBounds(230, 10, 120, 22);
        painelPrincipal.add(lblDataRecebimento);

        txtDataRecebimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        txtDataRecebimento.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtDataRecebimento.setBounds(240, 30, 80, 22);
        GregorianCalendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(new Date());
        txtDataRecebimento.setText(dateFormat.format(dataAtual.getTime()));
        txtDataRecebimento.setEnabled(true);
        painelPrincipal.add(txtDataRecebimento);

        //-----------------CODIGO--------------//
        lblVlrTotal = new JLabel("Valor Total da Venda");
        lblVlrTotal.setBounds(110, 60, 160, 22);
        lblVlrTotal.setForeground(Color.ORANGE);
        lblVlrTotal.setFont(new Font("Arial", 1, 15));
        painelPrincipal.add(lblVlrTotal);

        txtVlrTotal = new JTextField();
        txtVlrTotal.setBounds(130, 80, 100, 22);
        txtVlrTotal.setForeground(Color.ORANGE);
        txtVlrTotal.setEnabled(false);
        BigDecimal bdTotalVenda = new BigDecimal(recebimento.getValor_total()).setScale(2, RoundingMode.HALF_EVEN);
        DecimalFormat df = new DecimalFormat("R$ 0.00");
        txtVlrTotal.setText("" + df.format(bdTotalVenda));
        painelPrincipal.add(txtVlrTotal);

        //-----------------CODIGO--------------//
        lblPagamento = new JLabel("Dinheiro");
        lblPagamento.setBounds(150, 110, 140, 22);
        lblPagamento.setForeground(Color.red);
        lblPagamento.setFont(new Font("Arial", 1, 15));
        painelPrincipal.add(lblPagamento);

        txtPagamento = new JTextField();
        txtPagamento.setBounds(130, 130, 100, 22);
        txtPagamento.setEnabled(true);
        BigDecimal bdTotalPagamento = new BigDecimal(recebimento.getValor_restante()).setScale(2, RoundingMode.HALF_EVEN);
        df = new DecimalFormat("0.00");
        txtPagamento.setText("" + df.format(bdTotalPagamento));
        painelPrincipal.add(txtPagamento);

        // ---------------- BOTAO NOVO-----//
        btnReceber = new JButton("Receber");
        btnReceber.setBounds(120, 180, 120, 40);
        btnReceber.setIcon(new ImageIcon(cl.getResource("Imagens/dinheiro.png")));
        btnReceber.setFont(new Font("Arial", 1, 13));
        btnReceber.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelPrincipal.add(btnReceber);

        janela.add(painelPrincipal);
        janela.setVisible(true);

    }

    private String tratarCamposString(String s) {
        String aux = s.replaceAll(",", ".");
        return aux;
    }

    public RecebimentoModel getRecebimento() throws ParseException {
        DateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
        Date dataRecebimento = conversor.parse(txtDataRecebimento.getText());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return new RecebimentoModel(recebimento.getCod_recebimento(),
                recebimento.getCod_venda(),
                recebimento.getValor_total(),
                dateFormat.format(dataRecebimento),
                Float.parseFloat(tratarCamposString(txtPagamento.getText())),
                recebimento.getValor_restante()
        );
    }

    public void addReceberListener(ActionListener al) {
        btnReceber.addActionListener(al);
    }

    public void fecharTela() {
        janela.dispose();
    }
}
