package Model.Cards;

public class Money extends Kort {
    public Money(int cardNumber) {
        super(cardNumber);
    }
    public String toString(){
        switch (cardNumber){
            case 2:
                cardDiscription += "Modtag udbytte af Deres aktier\nkr. 50,00.";
                break;
            case 3:
                cardDiscription += "Betal kr. 125,00 for at modtagne 2 kasser øl.";
                break;
            case 4:
                cardDiscription += "Betal for vognvask og smøring\nkr. 15,00.";
                break;
            case 5:
                cardDiscription += "De har rettidigt afleveret Deres abonnementskort.\nDepositum kr. 5,00 udbetales Dem af banken.";
                break;
            case 6:
                cardDiscription += "De har anskaffet et nyt dæk til Deres vogn.\nIndbetal kr. 120,00.";
                break;
            case 7:
                cardDiscription += "Værdien af egen avl fra nyttehaven udgør kr. 200,00, som De modtager af banken";
                break;
            case 8:
                cardDiscription += "De har solgt Deres gamle klude.\nModtag kr. 20,00.";
                break;
            case 9:
                cardDiscription += "Kommunen har eftergivet et kvartals skat, hæv i banken til en glad aften kr. 200,00.";
                break;
            case 10:
                cardDiscription += "De har været en tur i udlandet og haft for mange cigaretter med hjem.\nBetal told kr. 50,00.";
                break;
            case 11:
                cardDiscription += "Efter auktionen på Assistenthuset, hvor De havde pantsat Deres tøj, modtager De ekstra kr. 108,00.";
                break;
            case 12:
                cardDiscription += "De har kørt frem for >Fuld Stop<. Betal kr. 150,00. i bøde.";
                break;
            case 13:
                cardDiscription += "Grundet på dyrtiden har De fået gageforhøjelse.\nModtag kr. 50,00.";
                break;
            case 14:
                cardDiscription += "De har måttet vedtage en parkeringsbøde.\nBetal kr. 60,00 til banken.";
                break;
            case 15:
                cardDiscription += "Manufakturvarerne er blevet billigere og bedre, herved sparer De kr. 100,00, som De modtager af banken.";
                break;
            case 16:
                cardDiscription += "Deres præmieobligation er kommet ud. De modtager kr. 100,00 af banken.";
                break;
            default:
                cardDiscription += cardNumber+0;
                break;
        }
        return cardDiscription;
    }
}
