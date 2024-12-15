package CollectionsHomework;

import java.util.ArrayList;
import java.util.HashSet;

public class TheorieEnsemble {

    public static void main(String[] args) {
        ArrayList<String> firstEnsemble = new ArrayList<>();
        ArrayList<String> secondEnsemble = new ArrayList<>();
        firstEnsemble.add("Lionel");
        firstEnsemble.add("Catherine");
        firstEnsemble.add("Loki");
        firstEnsemble.add("Hinata");
        firstEnsemble.add("Rex");
        secondEnsemble.add("Catherine");
        secondEnsemble.add("Loki");
        secondEnsemble.add("John Doe");
        secondEnsemble.add("Jane Doe");
        ArrayList<String> commun = new ArrayList<>(firstEnsemble);
        commun.retainAll(secondEnsemble);
        HashSet<String> externe = new HashSet<>(firstEnsemble);
        externe.addAll(secondEnsemble);
        externe.removeAll(commun);
        System.out.println("Eléments commun : ");
        for(String name : commun){
            System.out.println(name);
        }
        System.out.println("Eléments distincts : ");
        for(String name: externe){
            System.out.println(name);
        }

    }
}
