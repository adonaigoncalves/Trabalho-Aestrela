package t2aestrela;

import java.util.Random;

/**
 * Esta classe implementa o algoritmo de busca A*.
 * @author Adonai Gabriel
 */
public class Aestrela {

    private int tamMapa;
    private Nodo posInicial;
    private Nodo posFinal;
    private Nodo[][] mapa;
    private Nodo atual;
    private int nObstaculos;
    /**
     * Construtor da classe Aestrela.
     * @param posInicial - Nodo que representa o ponto inicial 
     * @param posFinal - 
     * @param tamMapa - 
     * @param percentObstaculo - 
     */
    public Aestrela(Nodo posInicial, Nodo posFinal, int tamMapa, int percentObstaculo) {
        this.tamMapa = tamMapa;
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        mapa = new Nodo[tamMapa][tamMapa];
        nObstaculos = (int) (((tamMapa * tamMapa) * percentObstaculo) / 100);
        geraMapa();
        atual = mapa[posInicial.getX()][posInicial.getY()];
        atual.setAberto(true);
        atual.setVisitado(true);
        calculaHmapa();
        geraObstaculos();
    }
    /**
     * Método que gera o mapa/matriz.
     */
    private void geraMapa() {
        for (int i = 0; i < tamMapa; i++) {
            for (int j = 0; j < tamMapa; j++) {
                mapa[i][j] = new Nodo(i, j);
            }
        }
    }
    /**
     * Método que calcula a função heurística de todos os nodos do mapa.
     */
    private void calculaHmapa() {
        for (int i = 0; i < tamMapa; i++) {
            for (int j = 0; j < tamMapa; j++) {
                mapa[i][j].calculaH(posFinal);
            }
        }
    }
    /**
     * Método que gera os obstáculos aleatoriamente da matriz.
     */
    private void geraObstaculos() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < nObstaculos; i++) {
            int x = random.nextInt(tamMapa);
            int y = random.nextInt(tamMapa);
            if ((posInicial.getX() == x && posInicial.getY() == y) || (posFinal.getX() == x && posFinal.getY() == y) || mapa[x][y].isObstaculo()) {
                i--;
            } else {
                mapa[x][y].setObstaculo(true);
            }
        }
    }
    /**
     * Método que executa o algoritmo A*.
     * @return Nodo - Nodo que representa o nodo solução ou um nodo nulo caso não exista solução.
     */
    public Nodo executa() {
        double f = -1;
        int cont = 1;
        Nodo nulo = null;
        while (cont != 0) {
            if (atual.equals(posFinal)) {
                return atual;
            }
            testaAdjacentes();
            cont = 0;
            for (int i = 0; i < tamMapa; i++) {
                for (int j = 0; j < tamMapa; j++) {
                    if (mapa[i][j].isAberto() && !mapa[i][j].isVisitado()) {
                        if (f < 0 || mapa[i][j].getF() < f) {
                            f = mapa[i][j].getF();
                            atual = mapa[i][j];
                            cont++;
                        }
                    }
                }
            }
            f = -1;
            mapa[atual.getX()][atual.getY()].setVisitado(true);
        }
        return nulo;
    }
    /**
     * Método auxiliar que testa os nodos adjacentes de cada nodo visitado da matriz, os estabelecendo
     * como nodos abertos, como filhos do nodo visitado e calculando sua função g.
     */
    public void testaAdjacentes() {
        if (atual.getX() - 1 >= 0 && !mapa[atual.getX() - 1][atual.getY()].isObstaculo() && !mapa[atual.getX() - 1][atual.getY()].isAberto()) {
            mapa[atual.getX() - 1][atual.getY()].setAberto(true);
            mapa[atual.getX() - 1][atual.getY()].setPai(atual);
            mapa[atual.getX() - 1][atual.getY()].calculaG(posInicial);
        }
        if (atual.getY() - 1 >= 0 && !mapa[atual.getX()][atual.getY() - 1].isObstaculo() && !mapa[atual.getX()][atual.getY() - 1].isAberto()) {
            mapa[atual.getX()][atual.getY() - 1].setAberto(true);
            mapa[atual.getX()][atual.getY() - 1].setPai(atual);
            mapa[atual.getX()][atual.getY() - 1].calculaG(posInicial);
        }
        if (atual.getX() + 1 < tamMapa && !mapa[atual.getX() + 1][atual.getY()].isObstaculo() && !mapa[atual.getX() + 1][atual.getY()].isAberto()) {
            mapa[atual.getX() + 1][atual.getY()].setAberto(true);
            mapa[atual.getX() + 1][atual.getY()].setPai(atual);
            mapa[atual.getX() + 1][atual.getY()].calculaG(posInicial);
        }
        if (atual.getY() + 1 < tamMapa && !mapa[atual.getX()][atual.getY() + 1].isObstaculo() && !mapa[atual.getX()][atual.getY() + 1].isAberto()) {
            mapa[atual.getX()][atual.getY() + 1].setAberto(true);
            mapa[atual.getX()][atual.getY() + 1].setPai(atual);
            mapa[atual.getX()][atual.getY() + 1].calculaG(posInicial);
        }
    }

    public Nodo[][] getMapa() {
        return mapa;
    }

    public void setMapa(Nodo[][] mapa) {
        this.mapa = mapa;
    }

    public Nodo getAtual() {
        return atual;
    }

    public void setAtual(Nodo atual) {
        this.atual = atual;
    }

    public Nodo getPosInicial() {
        return posInicial;
    }

    public void setPosInicial(Nodo posInicial) {
        this.posInicial = posInicial;
    }

    public Nodo getPosFinal() {
        return posFinal;
    }

    public void setPosFinal(Nodo posFinal) {
        this.posFinal = posFinal;
    }

    public int getnObstaculos() {
        return nObstaculos;
    }

    public void setnObstaculos(int nObstaculos) {
        this.nObstaculos = nObstaculos;
    }

    public int getTamMapa() {
        return tamMapa;
    }

    public void setTamMapa(int tamMapa) {
        this.tamMapa = tamMapa;
    }
}
