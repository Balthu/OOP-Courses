package Fractions;

/**
 * author = Balthurion
 * The Fraction class represents a mathematical fraction with a numerator and a denominator.
 * It provides methods for arithmetic operations, simplification, and utility functions.
 * <p>
 * Note: Division by zero is handled by replacing the denominator with 1. Ideally, exceptions
 * should be used for proper error handling.
 */

public final class Fraction {
    private final int numerateur;
    private final int denominateur;

    /**
     * Constructs a Fraction with the given numerator and denominator.
     * If the denominator is zero, it is replaced by 1, with an error message.
     * If the denominator is negative, the sign is transferred to the numerator.
     *
     * @param numerateur   The numerator of the fraction.
     * @param denominateur The denominator of the fraction. Must not be zero.
     */
    public Fraction(int numerateur, int denominateur){
        int tempNum = numerateur;
        int tempDenom = denominateur;
        if ((denominateur == 0)){
            System.out.print("Error - division by zero. The denominator was replaced with 1... " +
                    "until the developer learned about exceptions. :) ");
            tempDenom = 1;
        }

        if (denominateur < 0){
            tempDenom = Math.abs(tempDenom);
            tempNum *= -1;
        }

        this.denominateur = tempDenom;
        this.numerateur = tempNum;
    }

    /**
     * Returns a string representation of the fraction.
     * If the denominator is 1, only the numerator is displayed.
     *
     * @return A string representation of the fraction.
     */
    @Override
    public String toString() {
        if(denominateur != 1){
            return numerateur + "/" + denominateur;
        }
        else{
            return "" + numerateur;
        }
    }

    /**
     * Gets the numerator of the fraction.
     *
     * @return The numerator of the fraction.
     */
    public int getNumerator(){
        return numerateur;
    }

    /**
     * Gets the denominator of the fraction.
     *
     * @return The denominator of the fraction.
     */
    public int getDenominator(){
        return denominateur;
    }

    /**
     * Calculates the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
     *
     * @param firstNb  The first integer.
     * @param secondNb The second integer.
     * @return The GCD of the two integers.
     */
    public int pgcd(int firstNb, int secondNb){
        if(secondNb == 0){
            return firstNb;
        }
        else{
            return pgcd(secondNb, firstNb % secondNb);
        }
    }

    /**
     * Calculates the least common multiple (LCM) of two integers using their GCD.
     *
     * @param firstNb  The first integer.
     * @param secondNb The second integer.
     * @return The LCM of the two integers.
     */
    public int ppcm(int firstNb, int secondNb){
        int euclide = pgcd(firstNb, secondNb);
        return (firstNb * secondNb) / euclide;
    }

    /**
     * Simplifies the fraction by dividing the numerator and denominator by their GCD.
     *
     * @return The simplified fraction.
     */
    public Fraction simplifier(){
        int tempDiviseur = pgcd(numerateur, denominateur);
        return new Fraction(numerateur / tempDiviseur, denominateur / tempDiviseur);

    }

    /**
     * Adds the given fraction to this fraction and returns the result.
     *
     * @param secondFraction The fraction to add.
     * @return A new Fraction representing the sum.
     */
    public Fraction additionner(Fraction secondFraction){
        int tempPpcm = ppcm(denominateur, secondFraction.getDenominator());
        int firstNb = numerateur * (tempPpcm / denominateur);
        int secondNb = secondFraction.getNumerator() * (tempPpcm / secondFraction.getDenominator());
        return new Fraction(firstNb + secondNb, tempPpcm).simplifier();
    }


    /**
     * Subtracts the given fraction from this fraction and returns the result.
     *
     * @param secondFraction The fraction to subtract.
     * @return A new Fraction representing the difference.
     */
    public Fraction soustraire(Fraction secondFraction){
        int tempPpcm = ppcm(denominateur, secondFraction.getDenominator());
        int firstNb = numerateur * (tempPpcm / denominateur);
        int secondNb = secondFraction.getNumerator() * (tempPpcm / secondFraction.getDenominator());
        return new Fraction(firstNb - secondNb, tempPpcm).simplifier();
    }

    /**
     * Multiplies this fraction by the given fraction and returns the result.
     *
     * @param secondFraction The fraction to multiply by.
     * @return A new Fraction representing the product.
     */
    public Fraction multiplier(Fraction secondFraction){
        int tempNum = numerateur * secondFraction.getNumerator();
        int tempDenom = denominateur * secondFraction.getDenominator();
        return new Fraction(tempNum, tempDenom).simplifier();
    }

    /**
     * Divides this fraction by the given fraction and returns the result.
     * If the second fraction's numerator is zero, the denominator is set to 1 with an error message.
     *
     * @param secondFraction The fraction to divide by.
     * @return A new Fraction representing the quotient.
     */
    public Fraction diviser(Fraction secondFraction){
        int tempNum = numerateur * secondFraction.getDenominator();
        int tempDenom;
        if (secondFraction.getNumerator() != 0){
            tempDenom = denominateur * secondFraction.getNumerator();
        }
        else{
            tempDenom = 1;
            System.out.print("Error - division by zero. The denominator was replaced with 1... " +
                    "until the developer learned about exceptions. :)");
        }
        return new Fraction(tempNum, tempDenom).simplifier();
    }
}
