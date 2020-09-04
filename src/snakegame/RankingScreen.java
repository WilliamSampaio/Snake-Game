/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import jaco.mp3.a.A;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jplay.GameImage;
import jplay.Window;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import static snakegame.Constants.DIR_DATA;

/**
 *
 * @author william
 */
public class RankingScreen extends Constants {

    private Window gameWindow;
    private GameImage background;
    private Font fontRank;
    private Font fontButton;
    private List<Label> player;
    private Button back;

    public Button getBackButton() {
        return back;
    }

    public RankingScreen(Window gameWindow, String filePath) throws FontFormatException, IOException {

        this.gameWindow = gameWindow;
        background = new GameImage(filePath);

        Dimension aux = new Dimension(background.width, background.height);
        background.width = ((gameWindow.getWidth() / 2) / 4) * 3;
        background.height = (aux.height * (((gameWindow.getWidth() / 2) / 4) * 3)) / aux.width;

        background.x = (gameWindow.getWidth() - background.width) / 2;
        background.y = 0;

        fontRank = Font.createFont(Font.TRUETYPE_FONT, new File(DIR_FONTS + "digitalix.ttf")).deriveFont((float) gameWindow.getHeight() / 40);
        fontButton = Font.createFont(Font.TRUETYPE_FONT, new File(DIR_FONTS + "digitalix.ttf")).deriveFont((float) gameWindow.getHeight() / 16);

        player = new ArrayList<>();
        
        back = new Button("[ BACK ]", fontButton, Color.BLACK, gameWindow);
        
        back.setX(((int) gameWindow.getWidth() / 2) - (back.getWidth() / 2));
        back.setY((int) gameWindow.getHeight() - (back.getHeight() + 8));

    }

    public void load() throws ParserConfigurationException, SAXException, IOException {

        // read the ranking.xml file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document ranking = builder.parse(DIR_DATA + "ranking.xml");

        NodeList names = ranking.getElementsByTagName("name");
        NodeList scores = ranking.getElementsByTagName("score");

        if (names.getLength() > 0) {

            player.removeAll(player);

            int rankCount = 1;
            for (int i = names.getLength() - 1; i >= 0; i--) {

                String rankStr = "(" + rankCount + ") " + scores.item(i).getTextContent() + " - " + names.item(i).getTextContent();
                player.add(new Label(gameWindow, rankStr, fontRank, Color.BLACK));
                rankCount++;

            }

        }

    }

    public void draw() {
        background.draw();

        // loop to draw a background options
        for (int i = 0; i < player.size(); i++) {
            // draw the background indexed option
            player.get(i).draw((gameWindow.getWidth() / 2) - player.get(i).width / 2, ((background.height / 5) * 3) + i * (player.get(i).height + 4));
        }

        if (back.isMouseOn()) {
            back.drawSelected();
        } else {
            back.draw();
        }

    }

}
