package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

public class Menu {

    private Point position;
    private Point dimension;
    private Image menuScreen;
    private int option;
    private int selectedOption;

    public int getOption() {
        return option;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public Menu(Point screenSize, int unitSize) {
        menuScreen = new ImageIcon("src/resources/menu_screen.png").getImage();
        dimension = new Point();
        dimension.x = menuScreen.getWidth(null) * unitSize;
        dimension.y = menuScreen.getHeight(null) * unitSize;
        position = new Point();
        position.x = (screenSize.x - dimension.x) / 2;
        position.y = (screenSize.y - dimension.y) / 2;
        option = 1;
    }

    public void nextOption() {

        switch (option) {
            case 1:
                option = 2;
                break;
            case 2:
                option = 3;
                break;
            default:
                option = 1;
                break;
        }

    }

    public void previousOption() {

        switch (option) {
            case 1:
                option = 3;
                break;
            case 2:
                option = 1;
                break;
            default:
                option = 2;
                break;
        }

    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            previousOption();
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            nextOption();
        }

        if (code == KeyEvent.VK_ENTER) {
            switch (option) {
                case 1:
                    selectedOption = 1;
                    break;
                case 2:
                    selectedOption = 2;
                    break;
                case 3:
                    selectedOption = 3;
                    break;
            }
        }

    }

    public Graphics2D paint(Graphics2D g, Point screenSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(menuScreen, position.x, position.y, dimension.x, dimension.y, null);

        graficos.setColor(Color.BLACK);

        graficos.setStroke(new BasicStroke(10.0f));

        switch (option) {
            case 1:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (8 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
            case 2:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (20 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
            case 3:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (32 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
        }

        return graficos;
    }

}
