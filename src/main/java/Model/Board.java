package Model;

import Model.Fields.*;

import java.awt.*;

public class Board {
    public Field[] fields = new Field[40];

public Board() {
        fields[0] = new StartField("Start","",1);
        fields[1] = new StreetField("Rødovrevej", "",2,60, new Color(18,111,204), null);
        fields[2] = new ChanceCardsField("Prøv lykken","",3);
        fields[3] = new StreetField("Hvidovre","",4,60, new Color(18,111,204), null);
        fields[4] = new TaxField("Betal indkomstskat 10% eller 200 kr", "Betal indkomstskat 10% eller 200 kr",5);
        fields[5] = new FerryField("Øresund","",6,200, null);
        fields[6] = new StreetField("Roskildevej", "",7,100, new Color(221, 66, 195), null);
        fields[7] = new ChanceCardsField("Prøv lykken","",8);
        fields[8] = new StreetField("Valby Langgade", "",9,100,  new Color(211,66,195), null);
        fields[9] = new StreetField( "Allégade","",10,120, new Color(211,66,195), null);
        fields[10] = new JailField("Fængsel", "",11);
        fields[11] = new StreetField("Frederikberg Alle","",12,140, new Color(50,85,65), null) ;
        fields[12] = new BeerField("Tuborg","",13,150, null);
        fields[13] = new StreetField("Bülowsvej","",14,140, new Color(50,85,65),null);
        fields[14] = new StreetField("Gl. Kongevej","",15,160, new Color(50,85,65), null);
        fields[15] = new FerryField("D.F.D.S", "", 16,200, null);
        fields[16] = new StreetField("Bernstorffsvej","",17,180, new Color(86,74,72),null);
        fields[17] = new ChanceCardsField("Prøv lykken","",18);
        fields[18] = new StreetField("Hellerupvej","",19,180, new Color(86,74,72),null);
        fields[19] = new StreetField("Strandvej","",20,200, new Color(86,74,72),null);
        fields[20] = new Helle("Helle","",21);
        fields[21] = new StreetField("Trianglen","",22,220, new Color(193,44,21),null);
        fields[22] = new ChanceCardsField("Prøv lykken","",23);
        fields[23] = new StreetField("Østrebrogade","",24,220, new Color(193,44,21),null);
        fields[24] = new StreetField("Grønningen","",25,240, new Color(193,44,21), null);
        fields[25] = new FerryField("Ø","",26,200, null);
        fields[26] = new StreetField("Bredgade","",27,260, new Color(186,186,186), null);
        fields[27] = new StreetField("Kgs. Nytorv","",28,260,new Color(186,186,186), null);
        fields[28] = new BeerField("Carlsberg","",29,150, null);
        fields[29] = new StreetField("Østergade","",30,280,  new Color(186,186,186), null);
        fields[30] = new JailField("Sættes i fængsel","",31);
        fields[31] = new StreetField("Amagertorv","",32,300, new Color(224,184,74), null);
        fields[32] = new StreetField("Vimmelskaftet","",33,300, new Color(224,184,74), null);
        fields[33] = new ChanceCardsField("Prøv lykken","",34);
        fields[34] = new StreetField("Nygade","",35,320, new Color(224,184,74), null);
        fields[35] = new FerryField("D/S Bornholm 1866","",36,200, null);
        fields[36] = new ChanceCardsField("Prøv lykken","",37);
        fields[37] = new StreetField("Frederiksborggade","",38,350, new Color(93,25,24), null);
        fields[38] = new TaxField("Ekstraordinær Statsskat betal 100kr", "Ekstraordinær statsskat betal 100kr",39);
        fields[39] = new StreetField("Rådhuspladsen","",40,400,new Color(93,25,24), null);
        }

        /*  public Field[] fields = new Field[40];
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
    } */
}



