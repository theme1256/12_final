package Model.Cards;

import Controller.GameController;
import Model.Player;
import gui_main.GUI;

public class Money_special extends ChanceCards {


    public Money_special(int cardNumber) {
        super(cardNumber);
    }

    @Override
    public void action(Player player, GUI gui) {
        switch (this.cardNumber) {
            case 17:

                player.updateBalance((GameController.playerController.numberOfPlayers-1)*25);
                break;
            case 18:
                if(player.getNetWorth() <= 750){
                    player.updateBalance(+2000);
                } else
                    gui.showMessage("Du lever desværre ikke op til kravene til dette legat, da din totale værdi overstiger græsen med" + (player.getNetWorth() - 750)+ "kr");
                break;

            case 19:

                player.updateBalance(((-25) * player.getHouses()) + ((-125)*player.getHotels()));
                break;
            case 20:
                player.updateBalance(((-50) * player.getHouses()) + ((-125)*player.getHotels()));
            break;
            default:
                break;
        }

        gui.displayChanceCard(toString());
    }


    public String toString(){
        switch (cardNumber){
            case 17:
                cardDescription ="De har lagt penge ud til sammenskudsgilde. Mærkværdigvis betaler alle straks.\n" +
                        " Modtag fra hver medspiller kr. 25,00.";
                break;
            case 18:
                cardDescription ="De modtager >Matador-legatet for værdig trængende<, stort 2000kr\n" +
                        "Ved værdig trængende forstås at Deres formue, dvs. Deres kontante penge + skøder + bygninger ikke overstiger kr. 750,00.";
                break;
            case 19:
                cardDescription = "Kul- og kokspriserne er steget, og De skal betale:\n" +
                        "kr. 25,00 pr. hus og\nkr. 125,00 pr. hotel.";
                break;
            case 20:
                cardDescription = "Ejendomsskatterne er steget, ekstraudgifterne er:\n" +
                        "kr. 50,00 pr. hus\nkr. 125,00 pr. hotel.";
                break;
            default:
                cardDescription += cardDescription +0;
                break;
        }
        return cardDescription;
    }
}
