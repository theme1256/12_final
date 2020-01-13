package Model;

import Model.Fields.*;

import java.awt.*;

public class BoardsFields {
    private Field[] fields = new Field[40];

    public BoardsFields() {
        fields[0] = new StartField("Start","",1);
        fields[1] = new StreetField("Rødovrevej", "",2,60, new int[]{2, 10, 30, 90, 160, 250}, new int[]{4}, new Color(18,111,204));
        fields[2] = new ChanceCardsField("Prøv lykken","",3);
        fields[3] = new StreetField("Hvidovre","",4,60, new int[]{4, 20, 60, 180, 320, 540}, new int[]{2}, new Color(18,111,204));
        fields[4] = new TaxField("Betal indkomstskat 10% eller 200 kr", "Betal indkomstskat 10% eller 200 kr",5);
        fields[5] = new FerryField("Øresund","",6,200, new Color(255, 255, 255));
        fields[6] = new StreetField("Roskildevej", "",7,100, new int[]{6, 30, 90, 270, 400, 550}, new int[]{9, 10}, new Color(221, 66, 195));
        fields[7] = new ChanceCardsField("Prøv lykken","",8);
        fields[8] = new StreetField("Valby Langgade", "",9,100, new int[]{6, 30, 90, 270, 400, 550}, new int[]{7, 10},  new Color(211,66,195));
        fields[9] = new StreetField( "Allégade","",10,120, new int[]{8, 40, 100, 300, 450, 600}, new int[]{7, 9}, new Color(211,66,195));
        fields[10] = new JailField("Fængsel", "",11);
        fields[11] = new StreetField("Frederikberg Alle","",12,140, new int[]{10, 50, 150, 450, 625, 750}, new int[]{14, 15}, new Color(50,85,65)) ;
        fields[12] = new BeerField("Tuborg","",13,150);
        fields[13] = new StreetField("Bülowsvej","",14,140, new int[]{10, 50, 150, 450, 625, 750}, new int[]{12, 15}, new Color(50,85,65));
        fields[14] = new StreetField("Gl. Kongevej","",15,160, new int[]{12, 60, 180, 500, 700, 900}, new int[]{12, 14}, new Color(50,85,65));
        fields[15] = new FerryField("D.F.D.S", "", 16,200, new Color(255, 255, 255));
        fields[16] = new StreetField("Bernstorffsvej","",17,180, new int[]{14, 70, 200, 550, 750, 950}, new int[]{19, 20}, new Color(86,74,72));
        fields[17] = new ChanceCardsField("Prøv lykken","",18);
        fields[18] = new StreetField("Hellerupvej","",19,180, new int[]{14, 70, 200, 550, 750, 950}, new int[]{17, 20}, new Color(86,74,72));
        fields[19] = new StreetField("Strandvej","",20,200, new int[]{16, 80, 220, 600, 800, 1000}, new int[]{17, 19}, new Color(86,74,72));
        fields[20] = new Helle("Helle","",21);
        fields[21] = new StreetField("Trianglen","",22,220, new int[]{18, 90, 250, 700, 875, 1050}, new int[]{24, 25}, new Color(193,44,21));
        fields[22] = new ChanceCardsField("Prøv lykken","",23);
        fields[23] = new StreetField("Østrebrogade","",24,220, new int[]{18, 90, 250, 700, 875, 1050}, new int[]{22, 25}, new Color(193,44,21));
        fields[24] = new StreetField("Grønningen","",25,240, new int[]{20, 100, 300, 750, 925, 1100}, new int[]{22, 24}, new Color(193,44,21));
        fields[25] = new FerryField("Ø. K.","",26,200, new Color(255, 255, 255));
        fields[26] = new StreetField("Bredgade","",27,260, new int[]{22, 110, 330, 800, 975, 1150}, new int[]{28, 30}, new Color(186,186,186));
        fields[27] = new StreetField("Kgs. Nytorv","",28,260, new int[]{22, 110, 330, 800, 975, 1150}, new int[]{27, 30},new Color(186,186,186));
        fields[28] = new BeerField("Carlsberg","",29,150);
        fields[29] = new StreetField("Østergade","",30,280, new int[]{24, 120, 360, 850, 1025, 1200}, new int[]{27, 28}, new Color(186,186,186));
        fields[30] = new JailField("Sættes i fængsel","",31);
        fields[31] = new StreetField("Amagertorv","",32,300, new int[]{26, 130, 390, 900, 1100, 1275}, new int[]{33, 35}, new Color(224,184,74));
        fields[32] = new StreetField("Vimmelskaftet","",33,300, new int[]{26, 130, 390, 900, 1100, 1275}, new int[]{32, 35}, new Color(224,184,74));
        fields[33] = new ChanceCardsField("Prøv lykken","",34);
        fields[34] = new StreetField("Nygade","",35,320, new int[]{28, 150, 450, 1000, 1200, 1400}, new int[]{32, 33}, new Color(224,184,74));
        fields[35] = new FerryField("D/S Bornholm 1866","",36,200, new Color(255, 255, 255));
        fields[36] = new ChanceCardsField("Prøv lykken","",37);
        fields[37] = new StreetField("Frederiksborggade","",38,350, new int[]{35, 175, 500, 1100, 1300, 1500}, new int[]{40}, new Color(93,25,24));
        fields[38] = new TaxField("Ekstraordinær Statsskat betal 100kr", "Ekstraordinær statsskat betal 100kr",39);
        fields[39] = new StreetField("Rådhuspladsen","",40,400, new int[]{50, 200, 600, 1400, 1700, 2000}, new int[]{38}, new Color(93,25,24));
    }

    public Field[] getFields() {
            return fields;
    }

    private int[] formatRent(int i0, int i1, int i2, int i3, int i4, int i5) {
        int[] rent = new int[6];
        rent[0] = i0;
        rent[1] = i1;
        rent[2] = i2;
        rent[3] = i3;
        rent[4] = i4;
        rent[5] = i5;
        return rent;
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



