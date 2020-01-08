package Model;

import Model.Fields.*;

import javax.swing.*;
import java.awt.*;

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
    //private Field[] boardList = new Field[40];

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


    // an array, that contains all fields and attributes
   /* public Board() {
        boardList[0] = new StartField("Start Field","",1);
        Player owner = null;
        boardList[1] = new StreetField("Rødovrevej", "",2,60, null, new Color(18,111,204));
        boardList[2] = new ChanceCardsFeild("Prøv lykken","",3);
        boardList[3] = new StreetField("Hvidovre","",4,60, null,new Color(18,111,204));
        boardList[4] = new TaxField("Indkomstskat", "",200, 5);
        boardList[5] = new FerryField("Øresund","",6,200, null);
        boardList[6] = new StreetField("Roskildevej", "",7,100, null, new Color(221, 66, 195));
        boardList[7] = new ChanceCardsFeild("Prøv lykken","",8);
        boardList[8] = new StreetField("Valby Langgade", "",9,100, null, new Color(211,66,195));
        boardList[9] = new StreetField( "Allégade","",10,120, null,new Color(211,66,195));
        boardList[10] = new JailField("Fængsel", "",11);
        boardList[11] = new StreetField("Frederikberg Alle","",12,140, null, new Color(50,85,65)) ;
        boardList[12] = new BeerFeild("Tuborg","",13,150, null);
        boardList[13] = new StreetField("Bülowsvej","",14,140, null, new Color(50,85,65));
        boardList[14] = new StreetField("Gl. Kongevej","",15,160, null, new Color(50,85,65));
        boardList[15] = new FerryField("D.F.D.S", "", 16,200, null);
        boardList[16] = new StreetField("Bernstorffsvej","",17,180, null, new Color(86,74,72));
        boardList[17] = new ChanceCardsFeild("Prøv lykken","",18);
        boardList[18] = new StreetField("Hellerupvej","",19,180, null, new Color(86,74,72));
        boardList[19] = new StreetField("Strandvej","",20,200, null, new Color(86,74,72));
        boardList[20] = new Helle("Helle","",21);
        boardList[21] = new StreetField("Trianglen","",22,220, null, new Color(193,44,21));
        boardList[22] = new ChanceCardsFeild("Prøv lykken","",23);
        boardList[23] = new StreetField("Østrebrogade","",24,220, null,new Color(193,44,21));
        boardList[24] = new StreetField("Grønningen","",25,240, null, new Color(193,44,21));
        boardList[25] = new FerryField("Ø","",26,200, null);
        boardList[26] = new StreetField("Bredgade","",27,260, null,new Color(186,186,186));
        boardList[27] = new StreetField("Kgs. Nytorv","",28,260, null,new Color(186,186,186));
        boardList[28] = new BeerFeild("Carlsberg","",29,150, null);
        boardList[29] = new StreetField("Østergade","",30,280, null, new Color(186,186,186));
        boardList[30] = new JailField("Sættes i fængsel","",31);
        boardList[31] = new StreetField("Amagertorv","",32,300, null,new Color(224,184,74));
        boardList[32] = new StreetField("Vimmelskaftet","",33,300, null, new Color(224,184,74));
        boardList[33] = new ChanceCardsFeild("Prøv lykken","",34);
        boardList[34] = new StreetField("Nygade","",35,320, null,new Color(224,184,74));
        boardList[35] = new FerryField("D/S Bornholm 1866","",36,200, null);
        boardList[36] = new ChanceCardsFeild("Prøv lykken","",37);
        boardList[37] = new StreetField("Frederiksborggade","",38,350, null,new Color(93,25,24));
        boardList[38] = new TaxField("Ekstraordinær Statsskat", "",100, 39);
        boardList[39] = new StreetField("Rådhuspladsen","",40,400, null,new Color(93,25,24));

    }*\
}
