package mk.ukim.finki.aud1;

public class StringPrefix {

    //returns true if the first string is a prefix of the second one
    //Java has a method for this functionality -> startsWith()
    public static boolean isPrefix(String first, String second){
        if(first.length() > second.length()) return false;

        for(int i=0; i<first.length(); i++){
            if(first.charAt(i) != second.charAt(i))
                return false;
        }
        return true;
    }
}
