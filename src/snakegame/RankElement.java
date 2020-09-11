/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

/**
 *
 * @author William Benjamim Menezes Sampaio <a href="https://github.com/WilliamSampaio">| GitHub |</a>
 */
public class RankElement {

    private String playerName;
    private String playerScore;

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerScore() {
        return playerScore;
    }
    
    public RankElement(String playerName, String playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

}
