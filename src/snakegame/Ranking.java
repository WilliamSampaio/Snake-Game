/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author William Benjamim Menezes Sampaio <williambenjamimms97@gmail.com>
 */
public class Ranking {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document ranking;

    public Ranking(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException {

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        ranking = builder.parse(xmlFilePath);

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

}
