package leetcode;

import javax.imageio.ImageTranscoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MinMaxHard {

    public static void main(String[] args) {
        String [] wordList = {"acckzz","ccbazz","eiowzz","abcczz","qqqqqq"};

        MinMaxHard minMaxHard = new MinMaxHard();
        minMaxHard.findSecretWord(wordList, new Master() {
        });
    }

    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            String guess = "";
            int min0 = 100; // 단어끼리 겹치는 수가 제일 많은 녀석을 뽑는다.
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < min0) {
                    guess = w;
                    min0 = count.getOrDefault(w, 0);
                }
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    static public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }

    interface Master {

        String secret = "acckzz";

        default int guess(String guess) {
            int res = match(secret, guess);

            if (res == 6) {
                System.out.println(guess);
            }

            return res;
        };
    }
}
