package Model;

public class Shaker {
    private Die die;
    private int numberOfDices;
    private int[][] overrideSlag;
    private int overrideCounter = 0;
    private boolean override = false;

    public Shaker() {
        this.die = new Die();
        this.numberOfDices = 2;
    }
    public Shaker(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.die = new Die();
    }
    Shaker(int numberOfDices, int numberOfSides) {
        this.numberOfDices = numberOfDices;
        this.die = new Die(numberOfSides);
    }

    /**
     * Giv klassen en liste med slag, som den skal følge
     *
     * @param slag Et 2D-int-array med slag i den rækkefølge de skal slås
     */
    public void override(int[][] slag) {
        this.overrideSlag = slag;
        this.override = true;
    }

    /**
     * Slå med terningerne, hvis der ikke er pre-defineret et slag
     *
     * @return int-array med de slag der blev slået
     */
    public int[] shake() {
        // Opret en variabel, som kan returneres
        int[] rtn = new int[this.numberOfDices];

        // Hvis der er pre-defineret slag og de ikke allesammen er slået, tag det næste
        if (this.override && this.overrideSlag.length > this.overrideCounter) {
            // Kopier værdierne fra liste med pre-definerede slag
            rtn = this.overrideSlag[this.overrideCounter];
            // Tæl op, så det vides hvilket slag der skal slås næste gang, hvis der er flere pre-definerede
            this.overrideCounter++;
        } else {
            // Rul med terningen, så mange gange, som der er terninger i raflebægeret
            for (int i = 0; i < this.numberOfDices; i++) {
                rtn[i] = this.die.roll();
            }
        }

        // Returner liste med hvad terningerne slog
        return rtn;
    }

    /**
     * Slå med terningerne, hvis der ikke er pre-defineret et slag og returnerer summen af slagene
     *
     * @return Summen af alle terningerne i bægeret
     */
    public int shake_and_sum() {
        // Opret en variabel, som kan returneres
        int rtn = 0;

        // Hvis der er pre-defineret slag og de ikke allesammen er slået, tag det næste
        if (this.override && this.overrideSlag.length > this.overrideCounter) {
            // For alle slag, der er pre-defineret i denne runde
            for (int tal : this.overrideSlag[this.overrideCounter]) {
                rtn += tal;
            }
            // Tæl op, så det vides hvilket slag der skal slås næste gang, hvis der er flere pre-definerede
            this.overrideCounter++;
        } else {
            // Rul med terningen, så mange gange, som der er terninger i raflebægeret
            for (int i = 0; i < this.numberOfDices; i++) {
                rtn += this.die.roll();
            }
        }

        // Returner liste med hvad terningerne slog
        return rtn;
    }
}
