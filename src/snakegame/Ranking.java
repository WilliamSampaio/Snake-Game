/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.FontFormatException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import static snakegame.Constants.DIR_DATA;
import snakegame.core.OptionPane;

/**
 *
 * @author William Benjamim Menezes Sampaio
 * <a href="https://github.com/WilliamSampaio">| GitHub |</a>
 */
public class Ranking {

    private final DocumentBuilderFactory factory;
    private final DocumentBuilder builder;
    private final Document ranking;

    private final Node root;

    private final Transformer transformer;

    static final int UNPROCESSED = 0;
    static final int UNCLASSIFIED = 1;

    private int status;

    public int getStatus() {
        return status;
    }

    public Ranking(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {

        factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        builder = factory.newDocumentBuilder();
        ranking = builder.parse(xmlFilePath);

        root = ranking.getFirstChild();

        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        status = UNPROCESSED;

    }

    public int processScore(int score) throws SAXException, IOException, ParserConfigurationException, FontFormatException, TransformerException {

        if (getRankingList().size() > 0) {
            int lastPositionScore = Integer.parseInt(ranking.getElementsByTagName("score").item(0).getTextContent());
            if (score <= lastPositionScore) {
                if (OptionPane.playAgain() == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
            
            
        }else{
            
            rankScore(score, 0);
            
        }


        /*if (ranking.getElementsByTagName("player").getLength() == 0) {

            String name;
            do {
                name = OptionPane.rankGetName();
                if (name == null) {
                    return 0;
                } else if (name.length() > 10) {
                    OptionPane.rankLongNameErr();
                }
            } while (name.length() > 10);
            name = name.toUpperCase();

            String scoreString = Integer.toString(score);

            Element playerTag = ranking.createElement("player");

            Text nameTagValue = ranking.createTextNode(name);
            Element nameTag = ranking.createElement("name");
            nameTag.appendChild(nameTagValue);

            Text scoreTagValue = ranking.createTextNode(scoreString);
            Element scoreTag = ranking.createElement("score");
            scoreTag.appendChild(scoreTagValue);

            playerTag.appendChild(nameTag);
            playerTag.appendChild(scoreTag);

            root.appendChild(playerTag);

            transformer.transform(new DOMSource(root), new StreamResult(new FileOutputStream(DIR_DATA + "ranking.xml")));

            if (OptionPane.playAgain() == 0) {
                return 1;
            }

            return 2;

        }*/
        return 0;
    }

    public List<RankElement> getRankingList() {
        List<RankElement> rankingList = new ArrayList<>();
        NodeList players = ranking.getElementsByTagName("player");
        if (players.getLength() > 0) {
            NodeList names = ranking.getElementsByTagName("name");
            NodeList scores = ranking.getElementsByTagName("score");
            for (int i = players.getLength() - 1; i >= 0; i--) {
                rankingList.add(new RankElement(names.item(i).getTextContent(), scores.item(i).getTextContent()));
            }
        }
        return rankingList;
    }

    private void rankScore(int score, int indexPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
