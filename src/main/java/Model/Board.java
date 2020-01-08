package Model;

import Model.Fields.Field;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Board {

    public Field[] fields = new Field[40];
    int pris;
    public Board() {
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResources("Felter.json");
        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray fields = (JSONArray) obj;

            fields.forEach(field -> parseField((JSONObject) field));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseField(JSONObject field) {

        String navn = (String) field.get("navn");
        JSONObject farve = (JSONObject)field.get("farve");
        JSONObject bg = (JSONObject)farve.get("bg");


        int r = Math.toIntExact((long)bg.get("r"));
        int g = Math.toIntExact((long)bg.get("g"));
        int b = Math.toIntExact((long)bg.get("b"));
        String type = (String) field.get("type");
        Color color = new Color(r,g,b);


        if(!field.get("type").equals("chance")&&!field.get("type").equals("start")&&!field.get("type").equals("skat")
                &&!field.get("type").equals("prison")&&!field.get("type").equals("safe")&&!field.get("type").equals("action")&&!field.get("type").equals("toJail")) {
            this.pris = Math.toIntExact((long) field.get("pris"));
        } else {
            this.pris = 0;
        }
        int id = Math.toIntExact((long)field.get("nr"))-1;
        this.fields[id] = new Field(color, pris, navn, type);

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
