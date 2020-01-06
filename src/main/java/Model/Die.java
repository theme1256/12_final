package Model;

public class Die
{
    private final int MAX = 6; // maksimale face value
    private int faceValue; // Integer der skal repræsentere faceValuen

    Die()
    {
        faceValue = 1;
    }

    int roll()
    {
        faceValue = (int)(Math.random() * MAX) + 1;
        return faceValue;
    }

    int getFaceValue()
    {
        return faceValue;
    }

    public String toString()
    {
        return Integer.toString(faceValue);
    }
}