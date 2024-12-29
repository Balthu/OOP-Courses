package MatriceWithInterface;

import java.util.Random;

/**
 * author = Balthurion
 * The Fraction class represents a mathematical fraction with a numerator and a denominator.
 * It provides methods for arithmetic operations, simplification, and utility functions.
 * <p>
 * Note: Division by zero is handled by replacing the denominator with 1. Ideally, exceptions
 * should be used for proper error handling.
 */

public class Fraction implements IOperations<Fraction>{
    private final int numerateur;
    private final int denominateur;

    public Fraction(int numerateur){
        this(numerateur, 1);
    }
    /**
     * Constructs a Fraction with the given numerator and denominator.
     * If the denominator is zero, it is replaced by 1, with an error message.
     * If the denominator is negative, the sign is transferred to the numerator.
     *
     * @param numerateur   The numerator of the fraction.
     * @param denominateur The denominator of the fraction. Must not be zero.
     */

    public Fraction(int numerateur, int denominateur){
        if ((denominateur == 0)){
            throw new ArithmeticException("Division par 0 impossible");
        }

        int tempNum = numerateur;
        int tempDenom = denominateur;

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
    // mettre en static car pas besoin d'accéder aux paramètres de l'objet
    public static int pgcd(int firstNb, int secondNb){
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
    // mettre en static car pas besoin d'accéder aux paramètres de l'objet
    public static int ppcm(int firstNb, int secondNb){
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
            throw new ArithmeticException("Impossible de diviser une fraction par une fraction nulle");
        }
        return new Fraction(tempNum, tempDenom).simplifier();
    }

    /**
     * Renvoie un objet Fraction représentant la valeur 0 (zéro).
     *
     * @return Un objet Fraction ayant une valeur nulle.
     */
    @Override
    public Fraction valeurNulle() {
        return new Fraction(0);
    }

    /**
     * Renvoie un objet Fraction représentant la valeur 1.
     *
     * @return Un objet Fraction ayant une valeur égale à 1.
     */
    public Fraction valeurUne(){
        return new Fraction(1);
    }

    /**
     * Renvoie un objet Fraction représentant la valeur -1.
     *
     * @return Un objet Fraction ayant une valeur égale à -1.
     */
    public Fraction valeurMoinsUne(){
        return new Fraction(-1);
    }

    /**
     * Génère une nouvelle fraction avec un numérateur aléatoire compris entre 0 et 9, et un dénominateur aléatoire
     * compris entre 1 et 9 (inclus).
     * Le numérateur est un entier aléatoire entre 0 et 9 (obtient une valeur dans l'intervalle [0, 9]).
     * Le dénominateur est un entier aléatoire entre 1 et 9 (obtient une valeur dans l'intervalle [1, 9]).
     *
     * @return Une nouvelle instance de la classe Fraction contenant un numérateur et un dénominateur aléatoires.
     */
    public static Fraction generation() {
        return new Fraction(new Random().nextInt(10), new Random().nextInt(9) + 1);
    }
}

