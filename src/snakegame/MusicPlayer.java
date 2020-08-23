/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import jaco.mp3.player.MP3Player;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author William Benjamim Menezes Smapaio
 */
public class MusicPlayer implements Runnable {

    private MP3Player playList;
    private File[] musicsFile;
    private String[] musicsName;
    private boolean repeat;
    private boolean shuffle;
    private boolean emptyFolder;
    private int currentMusicIndex;
    private int playListSize;

    public MusicPlayer(Point screenSize) {

        repeat = false;
        shuffle = false;

        playList = new MP3Player();
        playList.setRepeat(repeat);
        playList.setShuffle(shuffle);

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        String path = System.getProperty("user.dir") + "/play-list/";
        File f = new File(path);

        if (f.list().length >= 1) {
            emptyFolder = false;
            musicsFile = f.listFiles();
            musicsName = f.list();
            playListSize = musicsName.length;
            currentMusicIndex = 0;
        } else {
            emptyFolder = true;
        }

    }

    public void playMusic() {
        if (!emptyFolder) {
            playList.stop();
            playList = new MP3Player(musicsFile[currentMusicIndex]);
            //playList.setRepeat(repeat);
            playList.setShuffle(shuffle);
            playList.play();
            /*if(!playList.isRepeat()){
                skipForward();
            }else{
                playMusic();
            }*/
        }
    }

    public void skipForward() {
        if (currentMusicIndex == playListSize - 1) {
            currentMusicIndex = 0;
            playMusic();
        } else {
            currentMusicIndex += 1;
            playMusic();
        }
    }

    public void skipBackward() {
        if (currentMusicIndex == 0) {
            currentMusicIndex = playListSize - 1;
            playMusic();
        } else {
            currentMusicIndex -= 1;
            playMusic();
        }
    }

    @Override
    public void run() {
        playMusic();
    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_0:
                playList.stop();
                break;
            case KeyEvent.VK_1:
                skipBackward();
                break;
            case KeyEvent.VK_2:
                if (playList.isPaused() || playList.isStopped()) {
                    playList.play();
                } else {
                    playList.pause();
                }
                break;
            case KeyEvent.VK_3:
                skipForward();
                break;
            case KeyEvent.VK_9:
                if (playList.isRepeat()) {
                    playList.setRepeat(false);
                } else {
                    playList.setRepeat(true);
                }
                System.out.println(playList.isRepeat());
            default:
                break;
        }
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, int unitSize) throws FontFormatException, IOException {

        Graphics2D graficos = (Graphics2D) g;

        Font customFont;
        customFont = Font.createFont(
                Font.TRUETYPE_FONT,
                new File(getClass().getClassLoader().getResource("digital-7.ttf").getFile())).deriveFont((float) unitSize * 2);

        graficos.setFont(customFont);

        if (emptyFolder) {
            int width = graficos.getFontMetrics().stringWidth("no music to play :(");
            graficos.drawString("no music to play :(", (screenSize.x - width) / 2, 2 * unitSize);
        } else {
            int width = graficos.getFontMetrics().stringWidth(musicsName[currentMusicIndex]);
            graficos.drawString(musicsName[currentMusicIndex], (screenSize.x - width) / 2, 2 * unitSize);
        }

        return graficos;
    }
}
