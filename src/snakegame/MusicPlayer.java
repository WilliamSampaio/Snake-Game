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

    private FileInputStream fileInputStream;
    private Player player;
    private File[] playList;
    private String path;

    public MusicPlayer() throws JavaLayerException, FileNotFoundException, URISyntaxException {

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        path = System.getProperty("user.dir") + "/play-list/";
        File f = new File(path);

        // Populates the array with names of files and directories
        playList = f.listFiles();

    }

    @Override
    public void run() {
        try {
            if (playList.length >= 1) {
                for (int i = 0; i < playList.length; i++) {
                    fileInputStream = new FileInputStream(playList[i]);
                    player = new Player(fileInputStream);
                    //System.out.println(playList[i].getName());
                    player.play();
                }
            }
        } catch (JavaLayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
