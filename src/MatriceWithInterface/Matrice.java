package MatriceWithInterface;

import java.util.Arrays;

/**
 * Commentaire :
 * Pour être honnête, je n'ai jamais eu d'exercice aussi difficile. C'était un bon challenge. Merci de nous l'avoir
 * proposé.
 *
 * Il m'a demandé d'aller plus loin dans les interfaces et les génériques. J'ai appris qu'on ne pouvait pas
 * instancier un tableau de type générique (car effacé au moment de la compilation) ou qu'il n'était pas possible
 * de définir une méthode statique dans une interface.
 *
 * Je souhaiterai apprendre les "fabriques" et les "prototypes" qui me permettait probablement
 * d'éviter de recourir à des stratégies
 * tierces comme la définition d'une méthode "ValeurUne", "ValeurNulle" ou de la génération de tels objets à valeur
 * aléatoire dans les classes Fraction et Entier.
 *
 * Pourrions-nous (re)voir les liens possibles entre classes qui implémentent une interface et une classe qui utilise
 * les objets issues de ces classes à l'angle de la généricité ?
 *
 * Par souci de transparence, sachez que je me suis épaulé des sites geek for geeks, Stack OverFlow et GitHub pour
 * trouver l'algorithme des méthodes inverse / sous-matrice / déterminants. Bien que réalisable sur papier, cela a été
 * difficile de trouver le pseudo-code qui permettrait de répondre au besoin technique. Je me suis servi des outils
 * pour commenter mes classes. Le reste est évidemment "Homebrew" :)
 *
 * Classe générique Matrice qui représente une matrice d'objets de type T.
 * T doit implémenter l'interface IOperations<T> pour permettre des opérations arithmétiques.
 *
 * @param <T> le type des éléments de la matrice, qui doit étendre IOperations<T>
 */
public class Matrice<T extends IOperations<T>> {

    // Tableau 2D pour stocker les éléments de la matrice
    private final T[][] tableau;
    // Nombre de lignes de la matrice
    private final int ligne;
    // Nombre de colonnes de la matrice
    private final int colonne;

    /**
     * Constructeur pour créer une matrice carrée avec un nombre de lignes et de colonnes spécifié.
     *
     * @param ligne le nombre de lignes de la matrice
     */
    public Matrice(int ligne) {
        this(ligne, ligne);
    }

    /**
     * Constructeur pour créer une matrice avec un nombre spécifié de lignes et de colonnes.
     *
     * @param colonne le nombre de colonnes de la matrice
     * @param ligne le nombre de lignes de la matrice
     * @throws ArithmeticException si les dimensions de la matrice sont inférieures à 1
     */
    @SuppressWarnings("unchecked")
    public Matrice(int colonne, int ligne)  {

        if(colonne < 1 || ligne < 1){
            throw new ArithmeticException("Ce programme n'admet pas de matrice inférieure au rang 1/1.");
        }

        this.colonne = colonne;
        this.ligne = ligne;
        tableau = (T[][]) new IOperations[ligne][colonne];

    }

    /**
     * Définit un élément à une position donnée dans la matrice.
     *
     * @param ligne la ligne de l'élément à définir
     * @param colonne la colonne de l'élément à définir
     * @param valeur la valeur à affecter à la position spécifiée
     */
    protected void setElement(int ligne, int colonne, T valeur){
        tableau[ligne][colonne] = valeur;
    }

    /**
     * Obtient un élément à une position donnée dans la matrice.
     *
     * @param ligne la ligne de l'élément à obtenir
     * @param colonne la colonne de l'élément à obtenir
     * @return l'élément à la position spécifiée
     */
    public T getElement(int ligne, int colonne){
        return tableau[ligne][colonne];
    }

    /**
     * Affiche la matrice dans la console.
     */
    public void visualize() {
        for (int i = 0; i < ligne; i++) {
            System.out.println(Arrays.toString(tableau[i]));
        }
    }

    /**
     * Additionne deux matrices.
     *
     * @param secondeMatrice la seconde matrice à additionner
     * @return une nouvelle matrice résultant de l'addition
     * @throws MatriceOperationException si les matrices ont des dimensions différentes ou si l'une d'elles est vide
     */
    public Matrice<T> additionMatrice(Matrice<T> secondeMatrice) throws MatriceOperationException{
        if (!isColumnEquals(secondeMatrice) || !isRowEquals(secondeMatrice)){
            throw new MatriceOperationException("Operation Impossible : les matrices sont de tailles différentes");
        }

        if(isEmptyValue() || secondeMatrice.isEmptyValue()){
            throw new MatriceOperationException("Opération Impossible : l'une des deux matrices n'est pas remplie !");
        }

        Matrice<T> addition = new Matrice<>(ligne, colonne);
        for(int i = 0; i < ligne; i++){
            for(int j = 0; j < colonne; j++){
                T otherValeur = secondeMatrice.getElement(i, j);
                T somme = getElement(i, j).additionner(otherValeur);
                addition.setElement(i, j, somme);
            }
        }
        return addition;
    }

    /**
     * Soustrait une seconde matrice de la matrice actuelle.
     *
     * @param secondeMatrice la seconde matrice à soustraire
     * @return une nouvelle matrice résultant de la soustraction
     * @throws MatriceOperationException si les matrices ont des dimensions différentes ou si l'une d'elles est vide
     */
    public Matrice<T> soustraireMatrice(Matrice<T> secondeMatrice) throws MatriceOperationException{

        if (!isColumnEquals(secondeMatrice) || !isRowEquals(secondeMatrice)){
            throw new MatriceOperationException("Operation Impossible : les matrices sont de tailles différentes");
        }

        if(isEmptyValue() || secondeMatrice.isEmptyValue()){
            throw new MatriceOperationException("Opération Impossible : l'une des deux matrices n'est pas remplie !");
        }

        Matrice<T> addition = new Matrice<>(ligne, colonne);
        for(int i = 0; i < ligne; i++){
            for(int j = 0; j < colonne; j++){
                T otherValeur = secondeMatrice.getElement(i, j);
                T difference = getElement(i, j).soustraire(otherValeur);
                addition.setElement(i, j, difference);
            }
        }
        return addition;
    }

    /**
     * Multiplie la matrice actuelle par une seconde matrice.
     *
     * @param secondMatrice la seconde matrice à multiplier
     * @return une nouvelle matrice résultant de la multiplication
     * @throws MatriceOperationException si le nombre de colonnes de la première matrice ne correspond pas au nombre de lignes de la seconde matrice
     *                                    ou si l'une des deux matrices est vide
     */
    public Matrice<T> multiplicationMatrice(Matrice<T> secondMatrice) throws MatriceOperationException {

        if(colonne != secondMatrice.ligne){
            throw new MatriceOperationException("Opération Impossible : le nombre de colonne de la matrice A" +
                    " diffère du nombre de ligne de la matrice B !");
        }
        if(isEmptyValue() || secondMatrice.isEmptyValue()){
            throw new MatriceOperationException("Opération Impossible : l'une des deux matrices n'est pas remplie !");
        }

        Matrice<T> produit = new Matrice<>(ligne, secondMatrice.colonne);
        for (int i = 0; i < ligne; i++){
            for (int j = 0; j < secondMatrice.colonne; j++){

                T somme = secondMatrice.getElement(0,0).valeurNulle();
                for (int k = 0; k < colonne; k++){
                        somme = somme.additionner((getElement(i, k).multiplier(secondMatrice.getElement(k, j))));
                    }
                    produit.setElement(i, j, somme);
                }
            }

        return produit;
    }

    /**
     * Multiplie chaque élément de la matrice par un scalaire.
     *
     * @param scalaire le scalaire par lequel multiplier la matrice
     * @return une nouvelle matrice résultant de la multiplication scalaire
     * @throws MatriceOperationException si la matrice n'est pas remplie
     */
    public Matrice<T> multiplicationScalaire(T scalaire) throws MatriceOperationException{

        if(isEmptyValue()){
            throw new MatriceOperationException("Opération Impossible : la matrice n'est pas remplie !");
        }

        Matrice<T> produit = new Matrice<>(ligne, colonne);
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                produit.setElement(i, j, scalaire.multiplier(getElement(i, j)));
            }
        }
        return produit;
    }

    /**
     * Transpose la matrice actuelle.
     *
     * @return une nouvelle matrice qui est la transposée de la matrice actuelle
     */
    public Matrice<T> transposition() {

        Matrice<T> transpose = new Matrice<>(colonne, ligne);
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                transpose.setElement(i, j, getElement(j, i));
            }
        }
        return transpose;
    }

    /**
     * Calcule le déterminant d'une matrice donnée.
     *
     * @param matriceIntermediaire la matrice pour laquelle calculer le déterminant
     * @return le déterminant de la matrice
     * @throws MatriceOperationException si la matrice n'est pas carrée
     */
    public T determinant(Matrice<T> matriceIntermediaire) throws MatriceOperationException {

        if (!matriceIntermediaire.isSquare()){
            throw new MatriceOperationException("Opération Impossible : la matrice doit être carrée");
        }

        if(matriceIntermediaire.ligne == 1){
            return matriceIntermediaire.getElement(0,0);
        }

        if(matriceIntermediaire.ligne == 2){
            T first = matriceIntermediaire.getElement(0,0).multiplier(matriceIntermediaire.getElement(1,1));
            T second = matriceIntermediaire.getElement(0,1).multiplier(matriceIntermediaire.getElement(1,0));
            return first.soustraire(second);
        }

        T determinant = getElement(0,0).valeurNulle();
        T signe = getElement(0,0).valeurUne();
        for(int column = 0; column < matriceIntermediaire.ligne; column++){
            Matrice<T> sousMatrice = sousMatrice(matriceIntermediaire, 0, column);
            if (column % 2 == 0){
                signe = getElement(0,0).valeurUne();
            }
            else{
                signe = getElement(0,0).valeurMoinsUne();
            }
            T subEtape = signe.multiplier(matriceIntermediaire.getElement(0, column)).multiplier(determinant(sousMatrice));
            determinant = determinant.additionner(subEtape);
        }

        return determinant;


    }

    /**
     * Calcule l'inverse de la matrice actuelle.
     *
     * @return une nouvelle matrice qui est l'inverse de la matrice actuelle
     * @throws MatriceOperationException si le déterminant est nul ou si la matrice n'est pas carrée
     */
    public Matrice<T> inverse(){

        T det = null;
        Matrice<T> cofacteurs = new Matrice<>(ligne, colonne);
        try {
            det = determinant(this);
            for (int i = 0; i < ligne; i++) {
                for (int j = 0; j < colonne; j++) {
                    Matrice<T> sousMatrice = sousMatrice(this, i, j);
                    T cofacteur = determinant(sousMatrice);
                    if ((i + j) % 2 != 0) {
                        cofacteur = cofacteur.multiplier(getElement(0, 0).valeurMoinsUne());
                    }
                    cofacteurs.setElement(i, j, cofacteur);
                }
            }
        }

        catch (MatriceOperationException e){
        e.getMessage();
    }


        Matrice<T> adjointe = cofacteurs.transposition();

        Matrice<T> inverse = new Matrice<>(ligne, colonne);
        for(int i = 0; i < ligne; i++){
            for(int j = 0; j < colonne; j++){
                T valeurInversee = adjointe.getElement(i,j).diviser(det);
                inverse.setElement(i, j, valeurInversee);
            }
        }

        return inverse;
    }

    /**
     * Crée une sous-matrice en excluant une ligne et une colonne spécifiées.
     *
     * @param matriceToreduce la matrice d'origine à réduire
     * @param ligneExclue la ligne à exclure
     * @param colonneExclue la colonne à exclure
     * @return la sous-matrice résultante
     */
    private Matrice<T> sousMatrice(Matrice<T> matriceToreduce, int ligneExclue, int colonneExclue) {
        Matrice<T> sousMatrice = new Matrice<>(matriceToreduce.ligne - 1, matriceToreduce.colonne - 1);
        int indiceLigne = 0;
        for(int i = 0; i < matriceToreduce.ligne; i++){
            if(i == ligneExclue){
                continue;
            }
            int indiceColonne = 0;
            for (int j = 0; j < matriceToreduce.colonne; j++){
                if(j == colonneExclue){
                    continue;
                }
                sousMatrice.setElement(indiceLigne, indiceColonne, matriceToreduce.getElement(i, j));
                indiceColonne++;
            }
            indiceLigne++;
        }
        return sousMatrice;
    }

    /**
     * Vérifie si le nombre de lignes de la matrice actuelle est égal à celui d'une seconde matrice.
     *
     * @param secondMatrice la seconde matrice à comparer
     * @return true si le nombre de lignes est égal, false sinon
     */
    private boolean isRowEquals(Matrice<T> secondMatrice){
        return ligne == secondMatrice.ligne;
    }

    /**
     * Vérifie si le nombre de colonnes de la matrice actuelle est égal à celui d'une seconde matrice.
     *
     * @param secondMatrice la seconde matrice à comparer
     * @return true si le nombre de colonnes est égal, false sinon
     */
    private boolean isColumnEquals(Matrice<T> secondMatrice){
        return colonne == secondMatrice.colonne;
    }

    /**
     * Vérifie si la matrice actuelle est carrée.
     *
     * @return true si la matrice est carrée, false sinon
     */
    private boolean isSquare(){
        return ligne == colonne;
    }

    /**
     * Vérifie si la matrice contient des valeurs vides.
     *
     * @return true si la matrice contient des valeurs nulles, false sinon
     */
    private boolean isEmptyValue(){
        boolean check = false;
        for (int i = 0; i < ligne; i++){
            for (int j = 0; j < colonne; j++){
                if (getElement(i, j) == null){
                    return true;
                }
            }
        }
        return check;}

}
