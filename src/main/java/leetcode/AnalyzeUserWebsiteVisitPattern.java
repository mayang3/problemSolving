package leetcode;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, TreeMap<Integer, String>> userMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        TreeMap<Integer, TreeSet<String>> resMap = new TreeMap<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < username.length; i++) {
            userMap.computeIfAbsent(username[i], t -> new TreeMap<>()).put(timestamp[i], website[i]);
        }

        for (TreeMap<Integer, String> treeMap : userMap.values()) {
            List<String> webSiteList = new ArrayList<>(treeMap.values());

            int N = webSiteList.size();

            Set<String> visitedBySameUser = new HashSet<>();

            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    for (int k = j+1; k < N; k++) {
                        String key = webSiteList.get(i) + " " + webSiteList.get(j) + " " + webSiteList.get(k);

                        if (visitedBySameUser.contains(key)) {
                            continue;
                        }

                        visitedBySameUser.add(key);

                        if (countMap.containsKey(key)) {
                            int beforeCount = countMap.get(key);
                            resMap.get(beforeCount).remove(key);
                        }

                        countMap.merge(key, 1, Integer::sum);
                        resMap.computeIfAbsent(countMap.get(key), t -> new TreeSet<>()).add(key);
                    }
                }
            }
        }

        String [] resSplit = resMap.firstEntry().getValue().iterator().next().split(" ");

        List<String> resList = new ArrayList<>();

        for (String resStr : resSplit) {
            resList.add(resStr);
        }

        return resList;
    }


    /**
     *
     * ["h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"]
     * [527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930]
     * ["hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"]
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        String [] username = {"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"};
        int [] timestamp = {527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930};
        String [] website = {"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"};

        AnalyzeUserWebsiteVisitPattern analyzeUserWebsiteVisitPattern = new AnalyzeUserWebsiteVisitPattern();
        System.out.println(analyzeUserWebsiteVisitPattern.mostVisitedPattern(username, timestamp, website));
    }
}
