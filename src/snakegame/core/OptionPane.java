/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame.core;

import javax.swing.JOptionPane;

/**
 *
 * @author William Benjamim Menezes Sampaio <a href="https://github.com/WilliamSampaio">| GitHub |</a>
 */
public class OptionPane {

    public static String rankGetName() {
        return JOptionPane.showInputDialog(null, "PLAYER NAME:", null, JOptionPane.PLAIN_MESSAGE);
    }

    public static void rankLongNameErr() {
        JOptionPane.showMessageDialog(null, "PLEASE ENTER A NAME OF UP TO 10 CHARACTERS.", "ERROR. NAME TOO LONG!", JOptionPane.ERROR_MESSAGE);
    }

    public static int playAgain() {
        return JOptionPane.showConfirmDialog(null, "PLAY AGAIN??", null, JOptionPane.YES_NO_OPTION);
    }

    public static int exitConfirmation() {
        return JOptionPane.showConfirmDialog(null, "DO YOU REALLY WANT TO LEAVE?", null, JOptionPane.YES_NO_OPTION);
    }

}
