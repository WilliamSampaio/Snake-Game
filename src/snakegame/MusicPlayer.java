/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author William Benjamim Menezes Smapaio
 */
public class MusicPlayer implements Runnable {

    //private Thread thread;
    private FileInputStream fileInputStream;
    private Player player;

    /*public void setThread(Thread thread) {
        this.thread = thread;
    }*/

    public MusicPlayer() throws JavaLayerException, FileNotFoundException, URISyntaxException {

        //thread = new Thread();
        /*fileInputStream = null;
        player = null;*/
        //FileInputStream fileInputStream = new FileInputStream(getClass().getResource("du-hast.mp3").getFile());
        //fileInputStream = new FileInputStream(/*new File(getClass().getResource(*/"resources/du-hast.mp3"/*).getFile())*/);
        player = new Player(new FileInputStream(new File(getClass().getResource("du-hast.mp3").toURI())));

        //thread.start();
    }

    /*public void play() throws JavaLayerException {
        run();
    }*/

    @Override
    public void run() {
        try {
            player.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
