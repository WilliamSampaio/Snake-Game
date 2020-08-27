package snakegame;

import jplay.Mouse;
import jplay.Sprite;

public class Button1 {
    private Sprite image;
    private Mouse mouse;

    public Button1(String img, int _x, int _y, Mouse _mouse) {
        image = new Sprite(img, 2);
        //image.setPosition(_x, _y);
        mouse = _mouse;
    }

    public void draw() {
        if(!mouse.isOverObject(image))
            image.setCurrFrame(0);
        else
            image.setCurrFrame(1);

        image.draw();
    }

    public boolean isMouseOn() {
        return mouse.isOverObject(image);
    }

    public Sprite getSprite() {
        return image;
    }
}
