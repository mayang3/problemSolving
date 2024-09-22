package leetcode;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author neo82
 */
public class _1604 {

    public static void main(String[] args) {
        String[] keyName = {"aa","aa","aa","aa","aa","aa","aa","aa","ba","ba","ba","ba","ba","ba","ba","ba","ca","ca","ca","ca","ca","ca","ca","ca","da","da","da","da","da","da","da","da","ea","ea","ea","ea","ea","ea","ea","ea","fa","fa","fa","fa","fa","fa","fa","fa","ga","ga","ga","ga","ga","ga","ga","ga","ha","ha","ha","ha","ha","ha","ha","ha","ia","ia","ia","ia","ia","ia","ia","ia","ja","ja","ja","ja","ja","ja","ja","ja","ka","ka","ka","ka","ka","ka","ka","ka","la","la","la","la","la","la","la","la","ma","ma","ma","ma","ma","ma","ma","ma","na","na","na","na","na","na","na","na","oa","oa","oa","oa","oa","oa","oa","oa","pa","pa","pa","pa","pa","pa","pa","pa","qa","qa","qa","qa","qa","qa","qa","qa","ra","ra","ra","ra","ra","ra","ra","ra","sa","sa","sa","sa","sa","sa","sa","sa","ta","ta","ta","ta","ta","ta","ta","ta","ua","ua","ua","ua","ua","ua","ua","ua","va","va","va","va","va","va","va","va","wa","wa","wa","wa","wa","wa","wa","wa","xa","xa","xa","xa","xa","xa","xa","xa","ya","ya","ya","ya","ya","ya","ya","ya","za","za","za","za","za","za","za","za","ab","ab","ab","ab","ab","ab","ab","ab","bb","bb","bb","bb","bb","bb","bb","bb","cb","cb","cb","cb","cb","cb","cb","cb","db","db","db","db","db","db","db","db"};
        String[] keyTime = {"17:57","13:10","15:31","17:58","06:04","10:36","12:11","13:28","01:04","15:58","07:03","20:31","09:19","01:30","21:03","15:32","14:12","15:58","23:21","18:00","04:16","23:05","08:11","01:53","05:51","21:23","12:19","12:45","19:54","01:55","20:53","17:09","00:30","03:28","02:55","21:31","10:35","05:34","23:49","23:12","22:19","11:19","05:44","06:23","22:20","01:52","06:19","07:34","01:00","12:45","16:26","06:56","13:32","14:15","10:49","15:19","18:58","16:37","02:17","08:00","23:25","19:36","08:44","01:01","11:08","15:20","01:24","06:26","10:52","03:04","18:17","18:56","17:09","21:08","00:13","12:00","12:38","15:51","00:55","00:12","20:37","16:00","11:13","22:12","10:15","03:07","22:36","14:26","19:47","12:38","22:01","23:16","12:01","16:57","02:49","01:44","19:37","18:34","00:31","05:55","00:26","02:32","06:20","03:19","02:37","14:05","00:18","08:00","00:37","13:23","14:27","18:33","18:12","19:09","14:31","10:42","16:54","21:44","04:34","13:15","18:00","00:21","11:00","15:28","21:43","01:33","12:58","05:29","12:05","07:08","13:50","04:22","16:48","09:14","02:12","09:20","17:36","18:28","11:48","06:23","00:40","03:46","04:29","20:27","21:41","14:29","05:25","05:23","08:20","14:44","00:27","06:23","00:17","23:54","14:11","04:48","01:35","10:56","08:48","02:52","14:18","18:41","18:51","05:49","16:53","02:57","17:21","15:15","02:06","19:11","06:30","12:31","21:39","07:10","12:57","00:17","16:18","19:23","02:34","08:53","18:44","18:11","11:19","15:18","08:21","22:04","01:22","13:09","06:56","10:57","15:06","22:14","09:53","01:07","01:05","14:51","01:40","16:02","09:26","09:43","04:44","15:04","12:24","02:37","17:50","00:17","09:18","06:32","11:28","09:49","17:53","19:29","21:14","00:56","13:27","13:37","21:41","07:14","04:44","20:41","15:09","17:08","20:59","01:39","07:28","04:21","02:46","08:21","16:06","10:01","05:06","06:22","11:55","06:03","04:34","19:47","19:27","16:42","07:09","19:40"};

        _1604 test = new _1604();
        System.out.println(test.alertNames(keyName, keyTime));
    }


    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<LocalTime>> map = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {
            List<LocalTime> localTimes = map.computeIfAbsent(keyName[i], t -> new ArrayList<>());

            localTimes.add(LocalTime.parse(keyTime[i], DateTimeFormatter.ofPattern("HH:mm")));
        }

        List<String> ans = new ArrayList<>();

        for (Map.Entry<String, List<LocalTime>> entry : map.entrySet()) {
            List<LocalTime> localTimes = entry.getValue();

            Collections.sort(localTimes);

            if (localTimes.size() > 2) {
                for (int i = 2; i < localTimes.size(); i++) {
                    LocalTime before = localTimes.get(i).minusHours(1);

                    if (localTimes.get(i).getHour() == 0) {
                        before = LocalTime.of(0,0);
                    }

                    if (localTimes.get(i-2).isAfter(before) || localTimes.get(i-2).equals(before)) {
                        ans.add(entry.getKey());
                        break;
                    }
                }
            }
        }

        Collections.sort(ans);

        return ans;
    }
}
