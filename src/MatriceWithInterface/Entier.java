package MatriceWithInterface;

import java.util.Random;

/**
 * La classe Entier représente un entier ou une valeur numérique réelle (double) en cas de division.
 * Elle implémente l'interface générique IOperations.
 * Cette classe permet d'effectuer des opérations arithmétiques élémentaires
 * (addition, soustraction, multiplication, etc.).
 */
public class Entier implements IOperations<Entier>{

    private final double valeur;

    /**
     * Constructeur pour initialiser un objet Entier avec une valeur numérique.
     *
     * @param valeur La valeur numérique associée à cet objet Entier.
     */
    public Entier(double valeur){
        this.valeur = valeur;
    }

    /**
     * Méthode pour additionner deux objets Entier.
     *
     * @param terme L'objet Entier à additionner.
     * @return Un nouvel objet Entier représentant la somme.
     */
    @Override
    public Entier additionner(Entier terme) {
        return new Entier(valeur + terme.valeur);
    }

    /**
     * Méthode pour soustraire un objet Entier à celui-ci.
     *
     * @param terme L'objet Entier à soustraire.
     * @return Un nouvel objet Entier représentant la différence.
     */
    @Override
    public Entier soustraire(Entier terme) {
        return new Entier(valeur - terme.valeur);
    }

    /**
     * Méthode pour multiplier cet objet Entier par un autre.
     *
     * @param terme L'objet Entier à multiplier.
     * @return Un nouvel objet Entier représentant le produit.
     */
    @Override
    public Entier multiplier(Entier terme) {
        return new Entier(valeur * terme.valeur);
    }

    /**
     * Méthode pour diviser cet objet Entier par un autre.
     *
     * @param terme L'objet Entier par lequel diviser.
     * @return Un nouvel objet Entier représentant le quotient.
     * @throws ArithmeticException Si une division par zéro est tentée.
     */
    public Entier diviser(Entier terme){
        if(terme.equals(new Entier(0))){
            throw new ArithmeticException("Division par zéro impossible");
        }
        return new Entier(valeur / terme.valeur);
    }

    /**
     * Renvoie un objet Entier représentant la valeur 0 (zéro).
     *
     * @return Un objet Entier ayant une valeur nulle.
     */
    public Entier valeurNulle(){
        return new Entier(0);
    }

    /**
     * Méthode statique pour générer un objet Entier aléatoire.
     * Les valeurs générées sont des entiers entre 0 et 9 inclus.
     *
     * @return Un nouvel objet Entier avec une valeur aléatoire.
     */
    public static Entier generation() {
        return new Entier(new Random().nextInt(10));
    }

    /**
     * Renvoie un objet Entier représentant la valeur 1.
     *
     * @return Un objet Entier ayant une valeur égale à 1.
     */
    public Entier valeurUne(){
        return new Entier(1);
    }

    /**
     * Renvoie un objet Entier représentant la valeur -1.
     *
     * @return Un objet Entier ayant une valeur égale à -1.
     */
    public Entier valeurMoinsUne(){
        return new Entier(-1);
    }

    /**
     * Convertit cet objet Entier en une chaîne de caractères.
     * Si la valeur est un entier (sans partie décimale), la sortie est arrondie.
     * Si elle est réelle, elle inclut sa partie décimale.
     *
     * @return Une chaîne représentant la valeur de cet objet.
     */
    public String toString(){
        if(valeur == Math.floor(valeur)){
            return "" + (int) valeur;
        }
        else{
            return "" + valeur;
        }
    }
}
