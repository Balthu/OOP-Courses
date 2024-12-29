package MatriceWithInterface;

import java.util.Random;

public class TestMatrice {

    public static void main(String[] args) {

        for (int essai = 1; essai < 11; essai++) {

            System.out.println("***************************** \nEntiers "+ essai + " \n*****************************");
            // Les matrices ont un rang n généré aléatoirement.
            // Elles sont carrée pour éviter de lever des exceptions différents.
            int rang = new Random().nextInt(2) + 3;

            // création des matrices carrées
            Matrice<Entier> matrice = new Matrice<>(rang);
            Matrice<Entier> secondMatrice = new Matrice<>(rang);

            // Boucle pour remplir les matrices
            for (int i = 0; i < rang; i++) {
                for (int j = 0; j < rang; j++) {
                    // génération de valeurs aléatoires
                    Entier entierRandom = Entier.generation();
                    matrice.setElement(i, j, entierRandom);
                    // génération de valeurs aléatoires
                    Entier secondEntierRandom = Entier.generation();
                    secondMatrice.setElement(i, j, secondEntierRandom);
                }
            }

            // Initialisation et affectation d'une valeur aléatoire pour la multiplication scalaire
            Entier scalaire = Entier.generation();

            // Affichage des deux matrices de base
            System.out.println("|_____________ Première matrice _____________|");
            matrice.visualize();
            System.out.println("|_____________ Deuxième matrice _____________|");
            secondMatrice.visualize();

            System.out.println("|_____________ Transposée de la première matrice _____________|");
            Matrice<Entier> transpose = matrice.transposition();
            transpose.visualize();



            try{
                System.out.println("|_____________ Matrice 1 * "+ scalaire + " _____________|");
                Matrice<Entier> multiplicationScalaire = matrice.multiplicationScalaire(scalaire);
                multiplicationScalaire.visualize();

                System.out.println("|_____________ Matrice 1 + Matrice 2 _____________|");
                Matrice<Entier> somme = matrice.additionMatrice(secondMatrice);
                somme.visualize();

                System.out.println("|_____________ Matrice 1 - Matrice 2 _____________|");
                Matrice<Entier> difference = matrice.soustraireMatrice(secondMatrice);
                difference.visualize();

                System.out.println("|_____________ Matrice 1 * Matrice 2 _____________|");
                Matrice<Entier> multiplicationUnVersDeux = matrice.multiplicationMatrice(secondMatrice);
                multiplicationUnVersDeux.visualize();

                System.out.println("|_____________ Matrice 2 * Matrice 1 _____________|");
                Matrice<Entier> multiplicationDeuxVersUn = secondMatrice.multiplicationMatrice(matrice);
                multiplicationDeuxVersUn.visualize();

                System.out.println("|_____________ Déterminant de Matrice 1 _____________|");
                Entier determinant = matrice.determinant(matrice);
                System.out.println(determinant);

                System.out.println("|_____________ Inverse de Matrice 1 _____________|");
                Matrice<Entier> inverse = matrice.inverse();
                inverse.visualize();

            } catch (MatriceOperationException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("| FIN DU TEST N° " + essai + " |\n\n\n\n");


        }


    for(int essai = 1; essai < 11; essai++) {

        System.out.println("***************************** \nFractions "+ essai + " \n*****************************");
        // Les matrices ont un rang n généré aléatoirement.
        // Elles sont carrée pour éviter de lever des exceptions différents.
        int rang = new Random().nextInt(2) + 3;

        // création des matrices carrées
        Matrice<Fraction> matrice = new Matrice<>(rang);
        Matrice<Fraction> secondMatrice = new Matrice<>(rang);

        // Boucle pour remplir les matrices
        for (int i = 0; i < rang; i++) {
            for (int j = 0; j < rang; j++) {
                // génération de valeurs aléatoires
                Fraction entierRandom = Fraction.generation();
                matrice.setElement(i, j, entierRandom);
                // génération de valeurs aléatoires
                Fraction secondEntierRandom = Fraction.generation();
                secondMatrice.setElement(i, j, secondEntierRandom);
            }
        }

        // Initialisation et affectation d'une valeur aléatoire pour la multiplication scalaire
        Fraction scalaire = Fraction.generation();

        // Affichage des deux matrices de base
        System.out.println("|_____________ Première matrice _____________|");
        matrice.visualize();
        System.out.println("|_____________ Deuxième matrice _____________|");
        secondMatrice.visualize();

        System.out.println("|_____________ Transposée de la première matrice _____________|");
        Matrice<Fraction> transpose = matrice.transposition();
        transpose.visualize();



        try{
            System.out.println("|_____________ Matrice 1 * "+ scalaire + " _____________|");
            Matrice<Fraction> multiplicationScalaire = matrice.multiplicationScalaire(scalaire);
            multiplicationScalaire.visualize();

            System.out.println("|_____________ Matrice 1 + Matrice 2 _____________|");
            Matrice<Fraction> somme = matrice.additionMatrice(secondMatrice);
            somme.visualize();

            System.out.println("|_____________ Matrice 1 - Matrice 2 _____________|");
            Matrice<Fraction> difference = matrice.soustraireMatrice(secondMatrice);
            difference.visualize();

            System.out.println("|_____________ Matrice 1 * Matrice 2 _____________|");
            Matrice<Fraction> multiplicationUnVersDeux = matrice.multiplicationMatrice(secondMatrice);
            multiplicationUnVersDeux.visualize();

            System.out.println("|_____________ Matrice 2 * Matrice 1 _____________|");
            Matrice<Fraction> multiplicationDeuxVersUn = secondMatrice.multiplicationMatrice(matrice);
            multiplicationDeuxVersUn.visualize();

            System.out.println("|_____________ Déterminant de Matrice 1 _____________|");
            Fraction determinant = matrice.determinant(matrice);
            System.out.println(determinant);

            System.out.println("|_____________ Inverse de Matrice 1 _____________|");
            Matrice<Fraction> inverse = matrice.inverse();
            inverse.visualize();


        } catch (MatriceOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("| FIN DU TEST N° " + essai + " |\n\n\n\n");


    }
}
}