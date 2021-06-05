package leetcode;

import java.util.*;

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            double val = values[i];

            graph.computeIfAbsent(eq.get(0), t -> new HashMap<>()).put(eq.get(1), val);
            graph.computeIfAbsent(eq.get(1), t -> new HashMap<>()).put(eq.get(0), 1 / val);
        }

        double [] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> list = queries.get(i);

            String from = list.get(0);
            String to = list.get(1);

            res[i] = solve(graph, from, to, new HashSet<>());
        }

        return res;
    }

    private double solve(Map<String, Map<String, Double>> graph, String from, String to, Set<String> visited) {
        // 주어진 query 가 표현 가능한 범위인지를 확인한다.
        if (graph.containsKey(from) == false) {
            return -1;
        }

        if (visited.contains(from)) {
            return -1;
        }

        if (graph.get(from).containsKey(to)) {
            return graph.get(from).get(to);
        }

        visited.add(from);
        Map<String, Double> map = graph.get(from);

        if (map != null) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                double weight = solve(graph, entry.getKey(), to, visited);
                if (weight > -1) {
                    return weight * entry.getValue();
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();

        equations.add(Arrays.asList(new String[] {"a", "e"}));
        equations.add(Arrays.asList(new String[] {"b", "e"}));

        double[] values = {4.0, 3.0};

        List<List<String>> queries = new ArrayList<>();

        queries.add(Arrays.asList(new String[] {"a","b"}));
        queries.add(Arrays.asList(new String[] {"e","e"}));
        queries.add(Arrays.asList(new String[] {"x","x"}));

        EvaluateDivision evaluateDivision = new EvaluateDivision();
        System.out.println(Arrays.toString(evaluateDivision.calcEquation(equations, values, queries)));
    }
}
