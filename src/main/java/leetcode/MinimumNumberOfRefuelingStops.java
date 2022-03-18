package leetcode;

public class MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] s) {
        long[] dp = new long[s.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < s.length; ++i)
            for (int t = i; t >= 0 && dp[t] >= s[i][0]; --t)
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][1]);
        for (int t = 0; t <= s.length; ++t)
            if (dp[t] >= target) return t;
        return -1;
    }

    public static void main(String[] args) {
        int target = 1000;
        int startFuel = 75;
        int [][] stations = {{41,42},{65,122},{141,176},{190,44},{221,36},{231,123},{281,135},{360,219},{363,161},{394,59},{477,83},{494,209},{523,41},{534,79},{546,81},{602,151},{623,179},{645,39},{647,109},{653,216},{707,165},{788,216},{824,214},{891,132},{987,69}};

        MinimumNumberOfRefuelingStops minimumNumberOfRefuelingStops = new MinimumNumberOfRefuelingStops();
        System.out.println(minimumNumberOfRefuelingStops.minRefuelStops(target, startFuel, stations));
    }
}
