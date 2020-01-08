package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.Random;

import Controller.BoardController;
import Model.ChanceCards.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deck {
    private Card[] cards = new Card[32];

    public Deck() {
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResources(BoardController.lang + "/Cards.json");
        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            for (Object card : cards) {
                parseCard((JSONObject) card);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public Card draw() {
        Card trukket = cards[0];
        System.arraycopy(cards, 1, cards, 0, cards.length - 1);
        cards[cards.length - 1] = trukket;
        return trukket;
    }

    public void shuffle() {
        int index;
        Card temp;
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    private void parseCard(JSONObject card) {
        String text = card.get("text").toString();
        int id = toInt((long) card.get("id"))-1;

        int amount;

        switch ((String) card.get("type")) {
            case "move-to":
                boolean start = (boolean) card.get("start");
                int to = toInt((long) card.get("to"))-1;
                cards[id] = new MoveTo(id, text, to, start);
                break;
            case "move":
                amount = toInt((long) card.get("amount"))-1;
                cards[id] = new Move(id, text, amount);
                break;
            case "move-special":
                int[] tos = new int[4];
                JSONArray t = (JSONArray) card.get("to");
                for (int i = 0; i < 4; i++) {
                    tos[i] = toInt((long) t.get(i));
                }
                cards[id] = new MoveSpecial(id, text, tos);
                break;
            case "money":
                amount = toInt((long) card.get("amount"));
                cards[id] = new Amount(id, text, amount);
                break;
            case "money-special":
                if (card.containsKey("hus")) {
                    int[] a = new int[2];
                    a[0] = toInt((long) card.get("hus"));
                    a[1] = toInt((long) card.get("hotel"));
                    cards[id] = new AmountSpecial(id, text, a);
                } else if (card.containsKey("fortune")) {
                    amount = toInt((long) card.get("amount"));
                    int fortune = toInt((long) card.get("fortune"));
                    cards[id] = new AmountSpecial(id, text, amount, fortune);
                } else {
                    amount = toInt((long) card.get("amount"));
                    cards[id] = new AmountSpecial(id, text, amount);
                }
                break;
            case "jail":
                cards[id] = new Jail(id, text);
                break;
        }
    }

    private int toInt(long inp) {
        return Math.toIntExact(inp);
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
