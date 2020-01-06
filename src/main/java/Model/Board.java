package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Board {

    public Board() {
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResources("Felter.json");
        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray fields = (JSONArray) obj;

            fields.forEach(field -> parseField((JSONObject) field));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseField(JSONObject field) {
        //
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
