package snakegamejava;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;

    private static final int LARGURA_TELA = 1180;
    private static final int VELOCIDADE = 10;

    public Missel(int x, int y) {

        this.x = x;
        this.y = y;
        setImagem(new ImageIcon("./assets/img/missel.png").getImage());
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisible = true;
    }

    public void mexer() {

        this.x += VELOCIDADE;
        if (this.x > LARGURA_TELA) {
            isVisible = false;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

}
