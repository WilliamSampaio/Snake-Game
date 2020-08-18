/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import jaco.mp3.player.MP3Player;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.BorderFactory;

/**
 *
 * @author William Benjamim Menezes Smapaio
 */
public class MusicPlayer implements Runnable {

    private MP3Player playList;
    private String path;
    private boolean repeat;
    private boolean shuffle;

    public MP3Player getPlayList() {
        return playList;
    }
        
    public MusicPlayer(Point screenSize) {
        
        repeat = true;
        shuffle = false;

        playList = new MP3Player();
        playList.setRepeat(repeat);
        playList.setShuffle(shuffle);
        
        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        path = System.getProperty("user.dir") + "/play-list/";
        File f = new File(path);
        // Populates the array with names of files and directories
        File[] musics = f.listFiles();

        if (musics.length >= 1) {
            for (int i = 0; i < musics.length; i++) {
                playList.addToPlayList(musics[i]);
            }
        }
        
    }

    @Override
    public void run() {
        playList.play();
    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_0:
                if(playList.isStopped()){
                    playList.play();
                }else{
                    playList.stop();
                }
                playList.stop();
                break;
            case KeyEvent.VK_1:
                playList.skipBackward();
                break;
            case KeyEvent.VK_2:
                if (playList.isPaused()) {
                    playList.play();
                } else {
                    playList.pause();
                }
                break;
            case KeyEvent.VK_3:
                playList.skipForward();
                break;
            default:
                break;
        }
    }
}
