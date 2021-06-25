package leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    static Map<String, Integer> mappings = new HashMap<>();

    static {
        mappings.put("I", 1);
        mappings.put("V", 5);
        mappings.put("X", 10);
        mappings.put("L", 50);
        mappings.put("C", 100);
        mappings.put("D", 500);
        mappings.put("M", 1000);

        mappings.put("IV", 4);
        mappings.put("IX", 9);
        mappings.put("XL", 40);
        mappings.put("XC", 90);
        mappings.put("CD", 400);
        mappings.put("CM", 900);
    }

    public int romanToInt(String s) {

        int n = s.length();
        int res = 0;
        int i = 0;

        while (i < n) {
            if (i < n - 1) {
                String sub = s.substring(i, i+2);

                if (mappings.containsKey(sub)) {
                    res += mappings.get(sub);
                    i+=2;
                    continue;
                }
            }

            res += mappings.getOrDefault(s.substring(i, i+1), 0);
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        System.out.println(romanToInteger.romanToInt("MCMXCIV"));
    }
}
