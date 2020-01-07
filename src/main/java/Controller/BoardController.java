package Controller;

import Model.ChanceCards.Card;
import Model.Fields.*;

public class BoardController {
    private Field[] boardList = new Field[40];

    // street 22
    // Chance Card 6
    // Tax 2
    // Jail
    // Ferry 4
    // Parking
    // beer

    // an array, that contains all fields and attributes
    public BoardController() {
        boardList[0] = new StartField();
        boardList[1] = new StreetField(60, 2, "Rødovrevej", "blue");
        //boardList[2] = new Card();
        boardList[3] = new StreetField(60, 4, "Hvidovre", "blue");
        boardList[4] = new TaxField("Indkomstskat", 200);
        boardList[5] = new FerryField("Øresund", 200, 6);
        boardList[6] = new StreetField(100,7,"Roskildevej", "pink");
        //boardList[7] = new Card();
        boardList[8] = new StreetField(100, 9, "Valby Langgade", "pink");
        boardList[9] = new StreetField(120, 10, "Allégade","pink");
        boardList[10] = new ;
        boardList[11] = new StreetField(140, 12,"Frederikberg Alle","green") ;
        boardList[12] = new ;
        boardList[13] = new StreetField(140,14,"Bülowsvej","green");
        boardList[14] = new StreetField(160,15,"Gl. Kongevej","green");
        boardList[15] = new FerryField("D.F.D.S", 200, 16);
        boardList[16] = new StreetField(180,17,"Bernstorffsvej","grey");
        //boardList[17] = new Card();
        boardList[18] = new StreetField(180,19,"Hellerupvej","grey");
        boardList[19] = new StreetField(200,20,"Strandvej","grey");
        boardList[20] = new ;
        boardList[21] = new StreetField(220,22,"Trianglen","red");
        //boardList[22] = new Card();
        boardList[23] = new StreetField(220,24,"Østrebrogade","red");
        boardList[24] = new StreetField(240,25,"Grønningen","red");
        boardList[25] = new FerryField("Ø",200,26);
        boardList[26] = new StreetField(260,27,"Bredgade","white");
        boardList[27] = new StreetField(260,28,"Kgs. Nytorv","white");
        boardList[28] = new ;
        boardList[29] = new StreetField(280,30,"Østergade","white");
        boardList[30] = new ;
        boardList[31] = new StreetField(300,32,"Amagertorv","yellow");
        boardList[32] = new StreetField(300,33,"Vimmelskaftet","yellow");
        //boardList[33] = new Card();
        boardList[34] = new StreetField(320,35,"Nygade","yellow");
        boardList[35] = new FerryField("D/S Bornholm 1866",200,36);
        //boardList[36] = new Card();
        boardList[37] = new StreetField(350,38,"Frederiksborggade","dark red");
        boardList[38] = new TaxField("Ekstraordinær Statsskat", 100);
        boardList[39] = new StreetField(400,40,"Rådhuspladsen","dark red");



    }

}
