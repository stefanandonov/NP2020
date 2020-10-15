package mk.ukim.finki.aud1;

import java.util.stream.IntStream;

public class StringPrefixTest {

    public boolean isPrefix (String str1, String str2) {
        //return str2.startsWith(str1);
        if (str1.length()>str2.length())
            return false;

        if (str1.equals(str2))
            return true;

        for (int i=0;i<str1.length();i++) {
            if (str1.charAt(i)!=str2.charAt(i)) { // StEfan StefanA
                return false;
            }
        }

        /*return IntStream.range(0, str1.length())
                .allMatch(index -> str1.charAt(index)==str2.charAt(index)); */



        return true;
    }
}
