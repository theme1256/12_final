package Model;

import Controller.BoardController;
import Model.Fields.*;
import gui_fields.GUI_Field;
import gui_main.GUI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;

public class Board {
    private Field[] fields = new Field[40];

    public Board() {
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResources(BoardController.lang + "/Fields.json");
        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray fields = (JSONArray) obj;

            for (Object field : fields) {
                parseField((JSONObject) field);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public GUI_Field[] getGUIFields() {
        GUI_Field[] rtn = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                rtn[i] = fields[i].getGUIVersion();
            } catch(NullPointerException e) {
                System.out.println("Fejl i field-nr: " + i);
                e.printStackTrace();
            }
        }
        return rtn;
    }

    private void parseField(JSONObject field) {
        JSONObject farve = (JSONObject) field.get("farve");
        Color bg = toColor((JSONObject) farve.get("bg"));
        Color fg = toColor((JSONObject) farve.get("text"));
        int id = toInt((long) field.get("nr"))-1;
        String text = (String) field.get("navn");

        switch ((String) field.get("type")) {
            case "start":
                int payout = -1 * toInt((long) field.get("pris"));
                fields[id] = new Start(id, text, payout);
                break;
            case "ownable":
                int pris = toInt((long) field.get("pris"));
                switch ((String) field.get("gruppe")) {
                    case "skib":
                        fields[id] = new Shipping(id, text, bg, fg, pris);
                        break;
                    case "bryggeri":
                        fields[id] = new Brewery(id, text, pris);
                        break;
                    default:
                        String[] rent = parseRent((JSONArray) field.get("leje"));
                        fields[id] = new Street(id, text, bg, fg, pris, rent);
                        break;
                }
                break;
            case "chance":
                fields[id] = new Chance(id, text);
                break;
            case "tax":
                fields[id] = new Tax(id, text);
                break;
            case "prison":
                fields[id] = new Prison(id, text);
                break;
            case "safe":
                fields[id] = new Safe(id, text, bg, fg);
                break;
        }
    }

    private int toInt(long inp) {
        return Math.toIntExact(inp);
    }

    private Color toColor(JSONObject color) {
        int r = toInt((long) color.get("r"));
        int g = toInt((long) color.get("g"));
        int b = toInt((long) color.get("b"));
        if (color.containsKey("a")) {
            int a = toInt((long) color.get("a"));
            return new Color(r, g, b, a);
        }

        return new Color(r, g, b);
    }

    private String[] parseRent(JSONArray tal) {
        String[] out = new String[tal.size()];
        for (int i = 0; i < tal.size(); i++) {
            out[i] = tal.get(i).toString();
        }
        return out;
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
