package t2aestrela;

/**
 * Esta classe implementa as funções de cada Nodo da matriz.
 * @author Adonai Gabriel
 */
public class Nodo {

    private double h;
    private int g;
    private int x, y;
    private boolean obs;
    private boolean visitado;
    private boolean aberto;
    private Nodo nodoPai;
    /**
     * Construtor padrão da classe Nodo.
     * @param x - Valor que representa a coluna do nodo na matriz.
     * @param y - Valor que representa a linha do nodo na matriz.
     */
    public Nodo(int x, int y) {
        this.obs = false;
        this.visitado = false;
        this.x = x;
        this.y = y;
    }
    /**
     * Este método calcula a função heurística de cada ponto/nodo.
     * @param posFinal - Nodo que representa o ponto final a ser encontrado na matriz.
     */
    public void calculaH(Nodo posFinal) {
        h = Math.sqrt(Math.pow((posFinal.getX() - x), 2) + Math.pow((posFinal.getY() - y), 2));
    }
    /**
     * Este método calcula a função g de cada ponto/nodo.
     * @param posInicial - Nodo que representa o ponto inicial, onde começa a busca na matriz.
     */
    public void calculaG(Nodo posInicial) {
        g = Math.abs(this.x - posInicial.getX()) + Math.abs(this.y - posInicial.getY());
    }
    /**
     * Este método retorna o valor que representa a coluna do nodo na matriz.
     * @return int - Valor que representa a coluna do nodo na matriz.
     */
    public int getX() {
        return x;
    }
    /**
     * Este método estabelece um valor à coluna de um nodo da matriz.
     * @param x - Valor que representa a coluna do nodo na matriz.
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Este método retorna o valor que representa a linha do nodo na matriz.
     * @return int - Valor que representa a linha do nodo na matriz.
     */
    public int getY() {
        return y;
    }
    /**
     * Este método estabelece um valor à linha de um nodo da matriz.
     * @param y - Valor que representa a linha do nodo na matriz.
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Este método retorna o valor que representa se o nodo é um obstáculo.
     * @return bool - Valor que representa se o nodo é um obstáculo.
     */
    public boolean isObstaculo() {
        return obs;
    }
    /**
     * Este método estabelece (true) caso o nodo seja um obstáculo e (false) caso contrário.
     * @param obstaculo - Valor que representa se um nodo é um obstáculo.
     */
    public void setObstaculo(boolean obstaculo) {
        this.obs = obstaculo;
    }
    /**
     * Este método retorna (true) caso o nodo seja um obstáculo e (false) caso contrário.
     * @return bool - Valor que representa se o nodo foi visitado.
     */
    public boolean isVisitado() {
        return visitado;
    }
    /**
     * Este método estabelece (true) caso o nodo seja um obstáculo e (false) caso contrário.
     * @param visitado - Valor que representa se o nodo foi visitado.
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    /**
     * Este método retorna (true) caso o nodo tenha sido aberto e (false) caso contrário.
     * @return bool - Valor que representa se o nodo foi aberto.
     */
    public boolean isAberto() {
        return aberto;
    }
    /**
     * Este método estabelece (true) caso o nodo tenha sido aberto e (false) caso contrário.
     * @param aberto - Valor que representa se o nodo foi aberto.
     */
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
    /**
     * Este método retorna o valor da função g do nodo.
     * @return int - Valor que representa a função g.
     */
    public double getG() {
        return g;
    }
    /**
     * Este método estabelece o valor da função g.
     * @param g - Valor que representa a função g.
     */
    public void setG(int g) {
        this.g = g;
    }
    /**
     * Este método retorna o valor da função h do nodo.
     * @return double - Valor que representa a função h.
     */
    public double getH() {
        return h;
    }
    /**
     * Este método estabelece o valor da função h.
     * @param h - Valor que representa a função h.
     */
    public void setH(float h) {
        this.h = h;
    }
    /**
     * Este método retorna o valor da função f do nodo.
     * @return double - Valor que representa a função f.
     */
    public double getF() {
        return g + h;
    }
    /**
     * Este método estabelece o pai do nodo atual.
     * @param pai - Valor que representa a função f.
     */
    public void setPai(Nodo pai) {
        this.nodoPai = pai;
    }
    /**
     * Este método retorna o pai do nodo atual.
     * @return Nodo - Valor que representa a função g.
     */
    public Nodo getPai() {
        return nodoPai;
    }
    /**
     * Método que substitui o método toString padrão por um toString que
     * represente um estado da árvore de busca.
     * @return String - String que representa um nodo da árvore de busca.
     */
    @Override
    public String toString() {
        return "(" + x + "; " + y + "; " + String.format("%.2f", h) + "; " + String.format("%.2f", getF()) + ")";
    }
    /**
     * Método que substitui o método equals padrão por um que compara corretamente
     * um nodo com outro.
     * @param objeto - Qualquer tipo de objeto.
     * @return Retorna (True) se for do tipo Nodo e se os atributos do
     * Nodo estão corretos e (False) caso contrário.
     */
    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Nodo)) {
            return false;
        }
        Nodo aux = (Nodo) objeto;
        return (aux.x == x && aux.y == y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }
}
