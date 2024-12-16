package Matrice;

import Fraction.Fraction;
import java.util.Random;


import java.util.Arrays;

public final class Matrice {

    private Fraction[][] matrice;
    private final int row;
    private final int column;

    public Matrice(int row) throws MatriceOperationException {
        this(row, row);
    }

    public Matrice(int row, int column) throws MatriceOperationException {
        if (row < 1 || column <1){
            throw new MatriceOperationException("Le nombre de lignes / colonnes doit être un entier positif");
        }
        this.row = row;
        this.column = column;
        matrice = new Fraction[row][column];
    }

    public void fillRandomMatrice(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                int num = new Random().nextInt(50);
                int denom = new Random().nextInt(50) + 1;
                matrice[i][j] = new Fraction(num, denom);
                }
            }
    }

    public Matrice fillMatrice(Fraction fraction) throws MatriceOperationException{
        if (!isEmptyValue()){
            throw new MatriceOperationException("La matrice est déjà pleine");
        }

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (matrice[i][j] == null){
                    this.matrice[i][j] = fraction;
                    return this;
                }
            }
        }
        return this;
    }

    public Matrice additionMatrice(Matrice secondMatrice) throws MatriceOperationException {

        Matrice somme = new Matrice(row, column);
        if (!isColumnEquals(secondMatrice) || !isRowEquals(secondMatrice)){
            throw new MatriceOperationException("Operation Impossible : les matrices sont de tailles différentes");
        }
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                somme.fillMatrice(matrice[i][j].additionner(secondMatrice.matrice[i][j]));
            }
        }
        return somme;
    }

    public Matrice soustractionMatrice(Matrice secondMatrice) throws MatriceOperationException {

        if (!isColumnEquals(secondMatrice) || !isRowEquals(secondMatrice)){
            throw new MatriceOperationException("Operation Impossible : les matrices sont de tailles différentes");
        }
        Matrice somme = new Matrice(row, column);
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                somme.fillMatrice(matrice[i][j].soustraire(secondMatrice.matrice[i][j]));
            }
        }
        return somme;
    }

    public Matrice multiplicationMatrice(Matrice secondMatrice) throws MatriceOperationException {
        Matrice produit = new Matrice(row, secondMatrice.column);
        for (int i = 0; i < row; i++){
            for (int j = 0; j < secondMatrice.column; j++){
                Fraction somme = new Fraction(0,1);
                for (int k = 0; k < column; k++){
                    somme = somme.additionner((matrice[i][k].multiplier(secondMatrice.matrice[k][j])));
                }
                produit.fillMatrice(somme);
            }
        }
        return produit;
    }

    public Matrice multiplicationScalaire(Fraction scalaire) throws MatriceOperationException {
        Matrice produit = new Matrice(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                produit.fillMatrice(scalaire.multiplier(matrice[i][j]));
            }
        }
        return produit;
    }

    public Matrice transposition() throws MatriceOperationException {
        Matrice transpose = new Matrice(column, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                transpose.fillMatrice(matrice[j][i]);
            }
        }
        return transpose;
    }

    private boolean isRowEquals(Matrice secondMatrice){
        return row == secondMatrice.row;
    }

    private boolean isColumnEquals(Matrice secondMatrice){
        return column == secondMatrice.column;
    }

    private boolean isSquare(){
        return row == column;
    }

    private boolean isEmptyValue(){
        boolean check = false;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (matrice[i][j] == null){
                    check = true;
                    break;
                }
            }
    }
        return check;}

    public void visualize(){
        for (int i = 0; i < row; i++){
            System.out.println(Arrays.toString(matrice[i]));
        }

    }

    public static void main(String[] args) {

        try {
            Matrice test = new Matrice(3);
            Matrice test2 = new Matrice(3);
            test2.fillRandomMatrice();
            test.fillRandomMatrice();
            System.out.println("First");
            test.visualize();
            System.out.println("Second");
            test2.visualize();
            Matrice test3 = test.additionMatrice(test2);
            Matrice test4 = test.multiplicationMatrice(test2);
            System.out.println("Addition");
            test3.visualize();
            System.out.println("Multiplication");
            test4.visualize();
        }
        catch (MatriceOperationException e){
            e.getMessage();
        }


    }


}
