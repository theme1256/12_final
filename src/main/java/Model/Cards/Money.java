package Model.Cards;

import Model.Player;
import gui_main.GUI;

public class Money extends ChanceCards {
    public Money(int cardNumber) {
        super(cardNumber);
    }

    @Override
    public boolean action(Player player, GUI gui) {
        switch (this.cardNumber) {
            case 2:
                player.updateBalance(+50);
                break;
            case 3:
                player.updateBalance(-125);
                break;
            case 4:
                player.updateBalance(-15);
                break;
            case 5:
                player.updateBalance(+5);
                break;
            case 6:
                player.updateBalance(-120);
                break;
            case 7:
                player.updateBalance(+200);
                break;
            case 8:
                player.updateBalance(+20);
                break;
            case 9:
                player.updateBalance(+200);
                break;
            case 10:
                player.updateBalance(-50);
                break;
            case 11:
                player.updateBalance(+100);
                break;
            case 12:
                player.updateBalance(-150);
                break;
            case 13:
                player.updateBalance(+50);
                break;
            case 14:
                player.updateBalance(-60);
                break;
            case 15:
                player.updateBalance(+100);
                break;
            case 16:
                player.updateBalance(+100);
                break;
            default:
                break;
        }

        gui.displayChanceCard(toString());
        return false;
    }

    public String toString(){
        cardDescription = "";
        action = "";
        value = 0;
        switch (cardNumber){
            case 2:
                cardDescription = "Modtag udbytte af Deres aktier\nkr. 50,00.";
                break;
            case 3:
                cardDescription = "Betal kr. 125,00 for at modtagne 2 kasser øl.";
                break;
            case 4:
                cardDescription = "Betal for vognvask og smøring\nkr. 15,00.";
                break;
            case 5:
                cardDescription = "De har rettidigt afleveret Deres abonnementskort.\nDepositum kr. 5,00 udbetales Dem af banken.";
                break;
            case 6:
                cardDescription = "De har anskaffet et nyt dæk til Deres vogn.\nIndbetal kr. 120,00.";
                break;
            case 7:
                cardDescription = "Værdien af egen avl fra nyttehaven udgør kr. 200,00, som De modtager af banken";
                break;
            case 8:
                cardDescription = "De har solgt Deres gamle klude.\nModtag kr. 20,00.";
                break;
            case 9:
                cardDescription = "Kommunen har eftergivet et kvartals skat, hæv i banken til en glad aften kr. 200,00.";
                break;
            case 10:
                cardDescription = "De har været en tur i udlandet og haft for mange cigaretter med hjem.\nBetal told kr. 50,00.";
                break;
            case 11:
                cardDescription = "Efter auktionen på Assistenthuset, hvor De havde pantsat Deres tøj, modtager De ekstra kr. 108,00.";
                break;
            case 12:
                cardDescription = "De har kørt frem for >Fuld Stop<. Betal kr. 150,00. i bøde.";
                break;
            case 13:
                cardDescription = "Grundet på dyrtiden har De fået gageforhøjelse.\nModtag kr. 50,00.";
                break;
            case 14:
                cardDescription = "De har måttet vedtage en parkeringsbøde.\nBetal kr. 60,00 til banken.";
                break;
            case 15:
                cardDescription = "Manufakturvarerne er blevet billigere og bedre, herved sparer De kr. 100,00, som De modtager af banken.";
                break;
            case 16:
                cardDescription = "Deres præmieobligation er kommet ud. De modtager kr. 100,00 af banken.";
                break;
            default:
                cardDescription = "Ukendt Penge-kort... nummer: " + cardNumber;
                System.out.println(cardDescription);
                break;
        }
        return cardDescription;
    }
}
