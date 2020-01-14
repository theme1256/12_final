package Controller;

import Model.Fields.*;
import gui_fields.*;

import java.awt.*;

public class FieldController {
    private BaseField[] fields = new BaseField[40];

    public FieldController() {
        instantiateFields();
    }

    /**
     * Opretter alle felterne der skal være på brættet
     */
    private void instantiateFields() {
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

    /**
     * Henter listen med alle felter
     *
     * @return Array med felterne
     */
    public BaseField[] getFields() {
        return fields;
    }

    /**
     * Henter et givent felt fra listen
     *
     * @param i Det felt der skal hentes
     * @return Feltet
     */
    public BaseField getField(int i) {
        return fields[i];
    }

    public BaseField getFieldFromName(String find) {
        for (BaseField field : fields) {
            if (field.getName().equals(find))
                return field;
        }
        return null;
    }

    /**
     * Løber felterne igennem og skaber et tilsvarende GUI_Field, så der kan skabes en GUI
     *
     * @return Et array med felter til GUI
     */
    public GUI_Field[] createGUIFromFields() {
        GUI_Field[] gui_fields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] instanceof ChanceCardsField) {
                GUI_Field field = new GUI_Chance("?", "CHANCE", " ", Color.BLACK, Color.WHITE);
                gui_fields[i] = field;
            } else if (fields[i] instanceof TaxField) {
                GUI_Field field = new GUI_Tax();
                field.setTitle(fields[i].getName());
                field.setSubText(" ");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i] instanceof FerryField) {
                GUI_Field field = new GUI_Shipping();
                field.setTitle(fields[i].getName());
                field.setBackGroundColor(fields[i].getColor());
                field.setSubText(fields[i].getPrice() + "kr");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i] instanceof JailField) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(fields[i].getName());
                field.setSubText("Fængsel");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i] instanceof BeerField) {
                GUI_Field field = new GUI_Brewery();
                field.setTitle(fields[i].getName());
                field.setSubText(fields[i].getPrice() + "kr");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i] instanceof Helle) {
                GUI_Field field = new GUI_Refuge();
                field.setTitle(fields[i].getName());
                field.setBackGroundColor(Color.WHITE);
                field.setSubText("Helle");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i] instanceof StartField) {
                GUI_Field field = new GUI_Start();
                field.setTitle(fields[i].getName());
                field.setSubText("");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else {
                GUI_Field field = new GUI_Street();
                field.setTitle(fields[i].getName());
                field.setBackGroundColor(fields[i].getColor());
                field.setDescription(fields[i].getDescription());
                field.setSubText(String.valueOf(fields[i].getPrice() + "kr"));
                gui_fields[i] = field;
            }
        }
        return gui_fields;
    }
}
