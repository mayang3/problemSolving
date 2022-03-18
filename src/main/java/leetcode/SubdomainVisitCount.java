package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {
    public static void main(String[] args) {
        String [] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        SubdomainVisitCount subdomainVisitCount = new SubdomainVisitCount();
        System.out.println(subdomainVisitCount.subdomainVisits(cpdomains));
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> scoreMap = new HashMap<>();

        for (String domain : cpdomains) {
            String[] countAndDomain = domain.split(" ");

            save(scoreMap, Integer.valueOf(countAndDomain[0]), countAndDomain[1]);
        }

        List<String> res = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }

        return res;
    }

    private void save(Map<String, Integer> scoreMap, int count, String doamin) {
        int index = doamin.indexOf(".");

        if (index < 0) {
            scoreMap.merge(doamin, count, Integer::sum);
            return;
        }

        scoreMap.merge(doamin, count, Integer::sum);
        save(scoreMap, count, doamin.substring(index+1));
    }
}
