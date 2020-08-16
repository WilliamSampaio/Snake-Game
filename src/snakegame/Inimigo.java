package snakegame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;

    private static final int LARGURA_TELA = 1180;
    private static final int VELOCIDADE = 1;

    private static int contador = 0;

    public Inimigo(int x, int y) {

        this.x = x;
        this.y = y;

        
        
        if (contador++ % 3 == 0) {
            setImagem(new ImageIcon("./assets/img/inimigo_2.png").getImage());

        } else {
            setImagem(new ImageIcon("./assets/img/inimigo_1.png").getImage());
        }

        this.largura = imagem.getWidth(null) / 2;
        this.altura = imagem.getHeight(null) / 2;

        isVisible = true;
    }

    public void mexer() {

        if (this.x < 0) {
            this.x = LARGURA_TELA;
        } else {
            this.x -= VELOCIDADE;
        }
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Inimigo.contador = contador;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

}
