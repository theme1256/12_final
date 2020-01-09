package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class Deck {/*
    private Card[] cards = new Card[32];

    public Deck() {
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResources("Kort.json");
        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            cards.forEach(card -> parseCard((JSONObject) card));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void parseCard(JSONObject card) {
        JSONObject text = (JSONObject)card.get("text");
        //System.out.println(text.get("da"));
        int id = Math.toIntExact((long)card.get("id"))-1;
        this.cards[id] = new Card((String)text.get("da"));
        System.out.println(text.get("da"));
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }*/
}
