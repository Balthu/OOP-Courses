package MatriceWithInterface;

public interface IOperations<T> {
    T diviser(T terme);
    T multiplier(T terme);
    T additionner(T terme);
    T soustraire(T terme);
    T valeurNulle();
    T valeurUne();
    T valeurMoinsUne();

}
