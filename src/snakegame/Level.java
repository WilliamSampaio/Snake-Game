package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.LineUnavailableException;*/
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import java.util.Random;

public final class Level extends JPanel implements ActionListener {

    final int ESTADO_MENU = 4;
    final int ESTADO_EMJOGO = 1;
    final int ESTADO_FASECONCLUIDA = 2;
    final int ESTADO_GAMEOVER = 0;
    final int ESTADO_FINAL = 7;

    private int naveAtual = 1;
    private int vidas = 3;
    private int faseAtual;
    private Image fundo;

    private Snake snake;
    private Food food;

    private Timer timer;
    //private List<Inimigo> inimigos;
    private List<Level> fases;
    private int emJogo;
    private boolean parabens;
    private int fundox = 0;
    private int fundoy = 0;
    private int fundo1x = 416;
    private int fundo1y = 0;
    private int FPS = 50;
    /*private Sequencer player;
    private String musica1 = "./assets/sound/backsound.mid";*/

    private Point screenSize;
    private Point gridSize;
    private int unitSize;

    private int delay = 250;

    public Point getGridSize() {
        return gridSize;
    }

    public void setGridSize(Point gridSize) {
        this.gridSize = gridSize;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public Point getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Point screenSize) {
        this.screenSize = screenSize;
    }

    public Image getFundo() {
        return fundo;
    }

    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /*
    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }*/
    public int getEmJogo() {
        return emJogo;
    }

    public void setEmJogo(int emJogo) {
        this.emJogo = emJogo;
    }

    public boolean isParabens() {
        return parabens;
    }

    public void setParabens(boolean parabens) {
        this.parabens = parabens;
    }

    public int getFundox() {
        return fundox;
    }

    public void setFundox(int fundox) {
        this.fundox = fundox;
    }

    public int getFundoy() {
        return fundoy;
    }

    public void setFundoy(int fundoy) {
        this.fundoy = fundoy;
    }

    public int getFundo1x() {
        return fundo1x;
    }

    public void setFundo1x(int fundo1x) {
        this.fundo1x = fundo1x;
    }

    public int getFundo1y() {
        return fundo1y;
    }

    public void setFundo1y(int fundo1y) {
        this.fundo1y = fundo1y;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int[][] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void moveFundo() {
        fundox -= 1;
        if (fundox <= (-1200)) {
            fundox = 0;
        }
    }

    public void moveFundo1() {
        fundo1x -= 1;
        if (fundo1x == 0) {
            fundo1x = 1200;
        }
    }

    public List<Level> getFases() {
        return fases;
    }

    public void setFases(List<Level> fases) {
        this.fases = fases;
    }

    /*public Sequencer getPlayer() {
        return player;
    }

    public void setPlayer(Sequencer player) {
        this.player = player;
    }

    public String getMusica1() {
        return musica1;
    }

    public void setMusica1(String musica1) {
        this.musica1 = musica1;
    }*/

    private int[][] coordenadas = {
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405}
    };

    /*public void tocarMusica(String nome, int repetir) {
        try {
            player = MidiSystem.getSequencer();
            Sequence musica = MidiSystem.getSequence(new File(nome));
            player.open();
            player.setSequence(musica);
            player.setLoopCount(repetir);
            player.start();
        } catch (IOException | InvalidMidiDataException | MidiUnavailableException e) {
            System.out.println("Erro ao tocar: " + e);
        }
    }*/
    public Level(Point point)/* throws LineUnavailableException, IOException */{

        setFocusable(true);
        setDoubleBuffered(true);

        addKeyListener(new TecladoAdapter());

        //setScreenSize(new Point(800, 600));
        setScreenSize(point);

        setUnitSize(getScreenSize().y / 50);

        setGridSize(new Point(45/* * getUnitSize()*/, 45/* * getUnitSize()*/));

        setFundo(new ImageIcon("./assets/img/background_menu.jpg").getImage());
        //setSnake(new Snake("left"), Color.BLACK, Color.RED);

        this.snake = new Snake("left", Color.YELLOW, Color.LIGHT_GRAY);

        snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(3, 0));
        snake.addSegments(new Point(4, 0));
        snake.addSegments(new Point(5, 0));
        snake.addSegments(new Point(6, 0));
        snake.addSegments(new Point(7, 0));
        snake.addSegments(new Point(8, 0));
        snake.addSegments(new Point(9, 0));
        snake.addSegments(new Point(10, 0));
        snake.addSegments(new Point(11, 0));
        snake.addSegments(new Point(12, 0));
        snake.addSegments(new Point(13, 0));
        snake.addSegments(new Point(14, 0));
        snake.addSegments(new Point(15, 0));
        snake.addSegments(new Point(16, 0));
        snake.addSegments(new Point(17, 0));

        this.food = new Food(new Point(this.gridSize.x / 2, this.gridSize.y / 2));

        //inicializaInimigos();
        setEmJogo(ESTADO_MENU);
        //tocarMusica(getMusica1(), 1);
        //setTimer(new Timer(/*250 / getFPS()*/500, this));
        timer = new Timer(delay, this);
        timer.start();
    }

    public void setFase(int fase) {
        switch (fase) {
            case 1:
                //snake.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase1.jpg").getImage());
                this.snake = new Snake("left", Color.BLACK, Color.RED);
                //inicializaInimigos();
                break;
            case 2:
                //snake.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase2.jpg").getImage());
                this.snake = new Snake("left", Color.BLACK, Color.RED);
                //inicializaInimigos();
                break;
            case 3:
                //snake.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase3.jpg").getImage());
                this.snake = new Snake("left", Color.BLACK, Color.RED);
                //inicializaInimigos();
                break;
            case 4:
                //snake.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase4.jpg").getImage());
                this.snake = new Snake("left", Color.BLACK, Color.RED);
                //inicializaInimigos();
                break;
            case 5:
                setFundo(new ImageIcon("./assets/img/background_menu.jpg").getImage());
                //inicializaInimigos();
                this.snake = new Snake("left", Color.BLACK, Color.RED);
                break;

        }
    }

    /*public void inicializaInimigos() {

        Random gerador = new Random();

        inimigos = new ArrayList<Inimigo>();
        for (int i = 0; i < coordenadas.length; i++) {
            inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
            //inimigos.add(new Inimigo(gerador.nextInt(2389), gerador.nextInt(425)));
        }
    }*/
    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;

        /*System.out.println(getGridSize().x);
        System.out.println(getGridSize().y);*/
 /* int posX = (getScreenSize().x - getGridSize().x) / 2;
        int posY = (getScreenSize().y - getGridSize().y) / 2;*/
 /*for (int x = posX; x < getGridSize().x; x += 416) {
            for (int y = posY; y < getGridSize().y; y += 416) {
                //graficos.drawImage(fundo, x, y, this);*/
        graficos.setColor(Color.DARK_GRAY);
        graficos.fillRect(0, 0, getScreenSize().x, getScreenSize().y);
        graficos.setColor(Color.GRAY);
        graficos.fillRect(
                (getScreenSize().x - (getGridSize().x * getUnitSize())) / 2,
                (getScreenSize().y - (getGridSize().y * getUnitSize())) / 2,
                getGridSize().x * getUnitSize(),
                getGridSize().y * getUnitSize());

        snake.paint(graficos, screenSize, gridSize, unitSize);
        food.paint(graficos, screenSize, gridSize, unitSize);

        /*graficos.setColor(Color.YELLOW);
        for (int i = 0; i < snake.getSegments().size(); i++) {
            //int snake
            graficos.fillRect(
                    ((getScreenSize().x - (getGridSize().x * getUnitSize())) / 2) + (snake.getSegments().get(i).x * getUnitSize()),
                    ((getScreenSize().y - (getGridSize().y * getUnitSize())) / 2) + (snake.getSegments().get(i).y * getUnitSize()),
                    getUnitSize() - 1,
                    getUnitSize() - 1);
        }*/
 /* }
        }*/

 /*graficos.drawImage(fundo, fundox, fundoy, null);
        graficos.drawImage(fundo, fundo1x, fundo1y, null);*/
        switch (emJogo) {
            case ESTADO_EMJOGO:
                //graficos.drawImage(snake.getImagem(), snake.getX(), snake.getY(), snake.getAltura() / 2, snake.getLargura() / 2, this);

                //List<Missel> misseis = snake.getMisseis();
                /*for (int i = 0; i < misseis.size(); i++) {

                    Missel m = (Missel) misseis.get(i);
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
                for (int i = 0; i < inimigos.size(); i++) {

                    Inimigo in = inimigos.get(i);
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }*/
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                //graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("VIDAS: " + vidas, 200, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("NAVE: " + naveAtual, 350, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("FASE: " + faseAtual, 500, 20);

                if (faseAtual == 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("EQUIPE:", 300, 100);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LEVY LIRA", 300, 120);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LISVANETE GARCIA", 300, 140);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  GABRIEL BEZERRA", 300, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  WILLIAM SAMPAIO", 300, 180);

                    ImageIcon unip = new ImageIcon("./assets/img/unip.png");
                    graficos.drawImage(unip.getImage(), 600, 75, null);
                }

                break;
            case ESTADO_FASECONCLUIDA:
                if (faseAtual != 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 60));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("FASE " + faseAtual, 100, 110);
                    graficos.setFont(new Font("arial", Font.BOLD, 40));
                    graficos.drawString("CONCLUIDA", 100, 160);
                    graficos.setFont(new Font("arial", Font.BOLD, 80));
                    graficos.drawString(">>>", 550, 160);

                }
                break;
            case ESTADO_GAMEOVER: {
                graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);

                graficos.setColor(Color.WHITE);

                graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("GAME OVER", 100, 160);
                break;
            }
            case ESTADO_MENU: {
                /*graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);*/

 /*graficos.setColor(Color.YELLOW);

                for (int i = 0; i < snake.getSegments().size(); i++) {
                    //int snake
                    graficos.fillRect(
                            ((getScreenSize().x - (getGridSize().x * getUnitSize())) / 2) + (snake.getSegments().get(i).x * getUnitSize()),
                            ((getScreenSize().y - (getGridSize().y * getUnitSize())) / 2) + (snake.getSegments().get(i).y * getUnitSize()),
                            getUnitSize() - 1,
                            getUnitSize() - 1);
                }*/

 /*graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("DS1A34", 200, 160);
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("PRESSIONE ENTER PARA INICIAR", 440, 550);*/
                break;
            }

        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        /*if (inimigos.size() == 0) {
            emJogo = ESTADO_FASECONCLUIDA;
        }*/
 /*List<Missel> misseis = snake.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missel m = (Missel) misseis.get(i);

            if (m.isIsVisible()) {
                m.move();
            } else {
                misseis.remove(i);
            }
        }*/

 /* for (int i = 0; i < inimigos.size(); i++) {

            Inimigo in = inimigos.get(i);

            if (in.isIsVisible()) {
                in.move();
            } else {
                inimigos.remove(i);
            }
        }*/
        if (snake.getSegments().get(0).equals(food.getPosition())) {
            snake.eat(this.gridSize);
            food.newPosition(this.gridSize);
        } else {
            if (snake.isAlive()) {
                snake.move(this.gridSize);
                //snake.checkSelfCollision();
            }/* else {
                //snake.die();
            }*/
        }

        //snake.move(this.gridSize);
        if (faseAtual != 5) {
            //checarColisoes();
        }

        moveFundo();
        moveFundo1();
        repaint();
    }

    /*public void checarColisoes() {

        Rectangle formaNave = snake.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;

        /*for (int i = 0; i < inimigos.size(); i++) {

            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();

            if (formaNave.intersects(formaInimigo)) {

                snake.setIsVisible(false);
                tempInimigo.setIsVisible(false);

                emJogo = ESTADO_GAMEOVER;
            }
        }
        List<Missel> misseis = snake.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();

            /*for (int j = 0; j < inimigos.size(); j++) {

                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();

                if (formaMissel.intersects(formaInimigo)) {
                    tempInimigo.setIsVisible(false);
                    tempMissel.setIsVisible(false);

                }
            }
        }

    }*/
    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            Graphics g = null;

            /*if (e.getKeyCode() == KeyEvent.VK_1) {
                snake.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                naveAtual = 1;
            }

            if (e.getKeyCode() == KeyEvent.VK_2) {
                snake.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                naveAtual = 2;
            }*/

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                
                
                //resetGame();

                /*switch (emJogo) {
                    case ESTADO_GAMEOVER:
                        vidas -= 1;
                        if (vidas == 0) {
                            emJogo = ESTADO_MENU;
                            break;
                        } else {
                            setFase(faseAtual);
                            emJogo = ESTADO_EMJOGO;
                            break;
                        }
                    case ESTADO_FASECONCLUIDA: {
                        faseAtual += 1;
                        setFase(faseAtual);
                        emJogo = ESTADO_EMJOGO;
                        break;
                    }
                    case ESTADO_MENU:
                        vidas = 3;
                        faseAtual = 1;
                        setFase(faseAtual);
                        emJogo = ESTADO_EMJOGO;
                        break;
                }*/
            }
            snake.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                emJogo = ESTADO_MENU;
                
                System.exit(0);
                
            }
        }

        /*@Override
        public void keyReleased(KeyEvent e) {
            snake.keyReleased(e);
        }*/
    }

    private void resetGame() {
        this.snake = new Snake("left", Color.YELLOW, Color.LIGHT_GRAY);

        snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(3, 0));
        snake.addSegments(new Point(4, 0));
        snake.addSegments(new Point(5, 0));
        snake.addSegments(new Point(6, 0));
        snake.addSegments(new Point(7, 0));
        snake.addSegments(new Point(8, 0));
        snake.addSegments(new Point(9, 0));
        snake.addSegments(new Point(10, 0));
        snake.addSegments(new Point(11, 0));

        this.food = new Food(new Point(this.gridSize.x / 2, this.gridSize.y / 2));

        //inicializaInimigos();
        setEmJogo(ESTADO_MENU);
        //tocarMusica(getMusica1(), 1);
        //setTimer(new Timer(/*250 / getFPS()*/500, this));
        timer = new Timer(delay, this);
        timer.start();
    }

}
