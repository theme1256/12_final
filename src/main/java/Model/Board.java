package Model;

import Model.Fields.*;

public class Board {
    private Field[] boardList = new Field[40];

    // start 1
    // street 22
    // Chance Card 6
    // Tax 2
    // Jail 2
    // Ferry 4
    // Helle 1
    // beer 2

    // an array, that contains all fields and attributes
    public Board() {
        boardList[0] = new StartField("Start Field",1);
        boardList[1] = new StreetField("Rødovrevej", "blue",60,2);
        boardList[2] = new ChanceCardsFeild("Prøv lykken",3);
        boardList[3] = new StreetField("Hvidovre", "blue",60,4);
        boardList[4] = new TaxField("Indkomstskat", 200, 5);
        boardList[5] = new FerryField("Øresund", 200,6);
        boardList[6] = new StreetField("Roskildevej", "pink",100,7);
        boardList[7] = new ChanceCardsFeild("Prøv lykken",8);
        boardList[8] = new StreetField("Valby Langgade", "pink",100,9);
        boardList[9] = new StreetField( "Allégade","pink",120,10);
        boardList[10] = new JailField("Fængsel", 11);
        boardList[11] = new StreetField("Frederikberg Alle","green",140,12) ;
        boardList[12] = new BeerFeild("Tuborg",150, 13);
        boardList[13] = new StreetField("Bülowsvej","green",140,14);
        boardList[14] = new StreetField("Gl. Kongevej","green",160,15);
        boardList[15] = new FerryField("D.F.D.S", 200, 16);
        boardList[16] = new StreetField("Bernstorffsvej","grey",180,17);
        boardList[17] = new ChanceCardsFeild("Prøv lykken",18);
        boardList[18] = new StreetField("Hellerupvej","grey",180,19);
        boardList[19] = new StreetField("Strandvej","grey",200,20);
        boardList[20] = new Helle(21);
        boardList[21] = new StreetField("Trianglen","red",220,22);
        boardList[22] = new ChanceCardsFeild("Prøv lykken",23);
        boardList[23] = new StreetField("Østrebrogade","red",220,24);
        boardList[24] = new StreetField("Grønningen","red",240,25);
        boardList[25] = new FerryField("Ø",200,26);
        boardList[26] = new StreetField("Bredgade","white",260,27);
        boardList[27] = new StreetField("Kgs. Nytorv","white",260,28);
        boardList[28] = new BeerFeild("Carlsberg",150,29);
        boardList[29] = new StreetField("Østergade","white",280,30);
        boardList[30] = new JailField("Sættes i fængsel",31);
        boardList[31] = new StreetField("Amagertorv","yellow",300,32);
        boardList[32] = new StreetField("Vimmelskaftet","yellow",300,33);
        boardList[33] = new ChanceCardsFeild("Prøv lykken",34);
        boardList[34] = new StreetField("Nygade","yellow",320,35);
        boardList[35] = new FerryField("D/S Bornholm 1866",200,36);
        boardList[36] = new ChanceCardsFeild("Prøv lykken",37);
        boardList[37] = new StreetField("Frederiksborggade","dark red",350,38);
        boardList[38] = new TaxField("Ekstraordinær Statsskat", 100, 39);
        boardList[39] = new StreetField("Rådhuspladsen","dark red",400,40);


    }
}
