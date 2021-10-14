package t2aestrela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Esta classe implementa a janela que mostra a matriz.
 * @author Adonai Gabriel
 */
public class Matriz extends JPanel {

    private int tamJanela;
    private final Aestrela busca;
    private final int tamNodo;
    private final int tamMapa;
    /**
     * Este método substitui o método paintComponent padrão para desenhar a matriz.
     * @param g - Atributo da classe Graphics para permitir desenhos.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 9));
        g.setColor(Color.BLACK);
        for (int i = 0; i < tamMapa; i++) {
            for (int j = 0; j < tamMapa; j++) {
                if (busca.getMapa()[i][j].isVisitado()) {
                    g.setColor(Color.BLUE);
                    g.fillRect(i * tamNodo, j * tamNodo, tamNodo, tamNodo);
                    if (busca.getMapa()[i][j].equals(busca.getPosInicial())) {
                        g.setColor(Color.GREEN);
                        g.fillRect(i * tamNodo, j * tamNodo, tamNodo, tamNodo);
                    }
                    g.setColor(Color.BLACK);
                }
                if (busca.getMapa()[i][j].isObstaculo()) {
                    g.fillRect(i * tamNodo, j * tamNodo, tamNodo, tamNodo);
                } else if (busca.getMapa()[i][j].isAberto() && !busca.getMapa()[i][j].isVisitado()) {
                    g.setColor(Color.CYAN);
                    g.fillRect(i * tamNodo, j * tamNodo, tamNodo, tamNodo);
                    g.setColor(Color.BLACK);
                }
                g.drawRect(i * tamNodo, j * tamNodo, tamNodo, tamNodo);
                if (tamMapa <= 20 && busca.getMapa()[i][j].isAberto()) {
                    g.drawString("h=" + String.format("%.2f", busca.getMapa()[i][j].getH()), (i * tamNodo + 2), (j * tamNodo + 10));
                    g.drawString("f=" + String.format("%.2f", busca.getMapa()[i][j].getF()), (i * tamNodo + 2), (j * tamNodo + 20));
                }
            }
        }
        if (busca.getPosFinal().equals(busca.getAtual())) {
            Nodo atual = busca.getAtual();
            g.setColor(Color.GREEN);
            while (atual.getPai() != null) {
                g.fillRect(atual.getX() * tamNodo, atual.getY() * tamNodo, tamNodo, tamNodo);
                g.setColor(Color.BLACK);
                g.drawRect(atual.getX() * tamNodo, atual.getY() * tamNodo, tamNodo, tamNodo);
                g.drawString("h=" + String.format("%.2f", atual.getH()), (atual.getX() * tamNodo + 2), (atual.getY() * tamNodo + 10));
                g.drawString("f=" + String.format("%.2f", atual.getF()), (atual.getX() * tamNodo + 2), (atual.getY() * tamNodo + 20));
                g.setColor(Color.GREEN);
                atual = atual.getPai();
            }
        }
        g.setColor(Color.RED);
        g.fillRect(busca.getPosFinal().getX() * tamNodo, busca.getPosFinal().getY() * tamNodo, tamNodo, tamNodo);

    }
    /**
     * Construtor da classe Matriz.
     * @param tamMapa - Valor que representa o número de linhas e colunas da matriz, recebido da interface.
     * @param busca - Objeto do tipo Aestrela que representa a busca A*.
     */
    public Matriz(int tamMapa, Aestrela busca) {
        this.tamJanela = 700;
        this.busca = busca;
        while (this.tamJanela % tamMapa != 0) {
            this.tamJanela--;
        }
        tamNodo = tamJanela / tamMapa;
        this.tamMapa = tamJanela / tamNodo;
    }
}
