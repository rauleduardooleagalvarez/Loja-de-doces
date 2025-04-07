import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LojaDeDoces1 {

    public static void main(String[] args) {
        // Criando a janela
        JFrame frame = new JFrame("Loja de Doces");
        frame.setSize(350, 500); // Ajustando o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Preços dos doces
        double precoBrigadeiro = 10.0;
        double precoCookie = 15.0;
        double precoPudim = 50.0;

        // Labels para contadores de quantidade
        JLabel labelQtdBrigadeiro = new JLabel("Qtd: 0");
        labelQtdBrigadeiro.setBounds(150, 160, 100, 30);
        frame.add(labelQtdBrigadeiro);

        JLabel labelQtdCookie = new JLabel("Qtd: 0");
        labelQtdCookie.setBounds(150, 250, 100, 30);
        frame.add(labelQtdCookie);

        JLabel labelQtdPudim = new JLabel("Qtd: 0");
        labelQtdPudim.setBounds(150, 340, 100, 30);
        frame.add(labelQtdPudim);

        // Label para mostrar o total
        JLabel labelTotal = new JLabel("Total: R$0,00");
        labelTotal.setBounds(220, 400, 250, 30);
        frame.add(labelTotal);

        // Botão "Pedir" para calcular o total
        JButton btnPedir = new JButton("Pedir");
        btnPedir.setBounds(50, 430, 250, 30);
        frame.add(btnPedir);

        // Criando instâncias do handler
        Handler handler = new Handler(labelTotal, precoBrigadeiro, precoCookie, precoPudim,
                                       labelQtdBrigadeiro, labelQtdCookie, labelQtdPudim);

        // Botões para doces
        JButton btnBrigadeiro = new JButton("Brigadeiro - R$10,00");
        btnBrigadeiro.setBounds(50, 30, 250, 30);
        frame.add(btnBrigadeiro);
        btnBrigadeiro.addActionListener(handler);

        JButton btnCookie = new JButton("Cookie - R$15,00");
        btnCookie.setBounds(50, 70, 250, 30);
        frame.add(btnCookie);
        btnCookie.addActionListener(handler);

        JButton btnPudim = new JButton("Pudim - R$50,00");
        btnPudim.setBounds(50, 110, 250, 30);
        frame.add(btnPudim);
        btnPudim.addActionListener(handler);

        // Ação do botão "Pedir"
        btnPedir.addActionListener(e -> {
            double total = handler.calcularTotal(); // Calcula o total
            labelTotal.setText(String.format("Total: R$%.2f", total)); // Atualiza a label do total
        });

        // Imagens dos doces
        adicionarImagem("C:\\Users\\Aluno\\Downloads\\9d5590f8a919cae9633142d617dea805.png", frame, 50, 160, 80, 80);
        adicionarImagem("C:\\Users\\Aluno\\Downloads\\OIP.jpg", frame, 50, 250, 80, 80);
        adicionarImagem("C:\\Users\\Aluno\\Downloads\\f53d329f8b6137ec55ea73ca1975cbb3_pudim-iogurte-receitas-nestle.jpg", frame, 50, 340, 80, 80);

        // Tornando a janela visível
        frame.setVisible(true);
    }

    private static void adicionarImagem(String caminho, JFrame frame, int x, int y, int largura, int altura) {
        try {
            ImageIcon icon = new ImageIcon(caminho);
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(img));
            label.setBounds(x, y, largura, altura);
            frame.add(label);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminho);
        }
    }
}

class Handler implements ActionListener {
    private final double precoBrigadeiro;
    private final double precoCookie;
    private final double precoPudim;

    private int quantidadeBrigadeiro = 0;
    private int quantidadeCookie = 0;
    private int quantidadePudim = 0;

    private final JLabel labelQtdBrigadeiro;
    private final JLabel labelQtdCookie;
    private final JLabel labelQtdPudim;

    public Handler(JLabel labelTotal, double precoBrigadeiro, double precoCookie, double precoPudim,
                   JLabel labelQtdBrigadeiro, JLabel labelQtdCookie, JLabel labelQtdPudim) {
        this.precoBrigadeiro = precoBrigadeiro;
        this.precoCookie = precoCookie;
        this.precoPudim = precoPudim;
        this.labelQtdBrigadeiro = labelQtdBrigadeiro;
        this.labelQtdCookie = labelQtdCookie;
        this.labelQtdPudim = labelQtdPudim;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Brigadeiro - R$10,00" -> {
                quantidadeBrigadeiro++;
                labelQtdBrigadeiro.setText("Qtd: " + quantidadeBrigadeiro);
                
            }
            case "Cookie - R$15,00" -> {
                quantidadeCookie++;
                labelQtdCookie.setText("Qtd: " + quantidadeCookie);
                
            }
            case "Pudim - R$50,00" -> {
                quantidadePudim++;
                labelQtdPudim.setText("Qtd: " + quantidadePudim);
                
            }
        }
    }

    public double calcularTotal() {
        return (quantidadeBrigadeiro * precoBrigadeiro) +
               (quantidadeCookie * precoCookie) +
               (quantidadePudim * precoPudim);
    }
}
