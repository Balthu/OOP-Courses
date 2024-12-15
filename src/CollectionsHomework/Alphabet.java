package CollectionsHomework;
import java.util.ArrayList;

public class Alphabet {

    public static void afficheAlphabet(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Character> lettresAlphabet = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++){
            lettresAlphabet.add(alphabet.charAt(i));
        }
        for(char e: lettresAlphabet.reversed()){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        afficheAlphabet();
    }

    }

