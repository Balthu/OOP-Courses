package CollectionsHomework;

import java.util.HashSet;
import java.util.TreeSet;

public class Personne implements Comparable<Personne> {
    private final String nom;
    private final String prenom;
    private final int id;
    private static HashSet<Integer> idList = new HashSet<>();

    public Personne(String nom, String prenom, int id) throws IdAlreadyHereException{
        if(isIdListed(id)){
            throw new IdAlreadyHereException("ID : " + id + " déjà encodée");
        }
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        idList.add(id);
    }

    @Override
    public int compareTo(Personne secondPerson) {
        return Integer.compare(this.id, secondPerson.id);
    }

    @Override
    public String toString() {
        return id + " : " + nom + " " + prenom;
    }

    private boolean isIdListed(int id){
        return idList.contains(id);
    }

    public static void main(String[] args) {
        TreeSet<Personne> listPersonne = new TreeSet<>();
        try {

            listPersonne.add(new Personne("Lucky", "Luciano", 15860));
            listPersonne.add(new Personne("Alphonso", "Capone", 666));
            listPersonne.add(new Personne("Tommy", "Lucchese", 1953));
            listPersonne.add(new Personne("error", "doubleId", 1953));
            listPersonne.add(new Personne("John", "Gotti", 7895));
        } catch (IdAlreadyHereException e) {
            System.out.println(e.getMessage());
        }
        for(Personne e: listPersonne){
            System.out.println(e);
        }
    }
}
