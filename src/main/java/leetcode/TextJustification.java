package leetcode;

import java.util.*;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {

        Queue<String> queue = new LinkedList<>();
        int i = 0;
        int sizeSoFar = 0;

        List<String> res = new ArrayList<>();

        while (i < words.length) {
            String word = words[i];

            // 포함시킬 수 있는 경우;
            if (isPossible(queue, word, sizeSoFar, maxWidth)) {
                queue.add(word);
                sizeSoFar += word.length();
                i++;
            } else {
                int wordCount = Integer.valueOf(queue.size());
                int emptySize = maxWidth - sizeSoFar;
                int emptyPer = (int)Math.ceil((double) emptySize / (wordCount - 1));

                StringBuilder sb = new StringBuilder();

                while (queue.isEmpty() == false) {
                    // 맨 처음 단어가 아니라면 공백을 삽입해준다.
                    if (sb.length() > 0) {
                        if (wordCount > 1) {
                            for (int j = 0; j < emptyPer; j++) {
                                sb.append(" ");
                                emptySize--;
                            }
                        } else {
                            // 마지막 단어의 앞에는 남은 공백을 죄다 붙인다.
                            for (int j = 0; j < emptySize; j++) {
                                sb.append(" ");
                            }
                            emptySize = 0;
                        }

                        emptyPer = (int) Math.ceil((double) emptySize / (wordCount - 1));
                    }

                    sb.append(queue.poll());
                    wordCount--;
                }

                // 다 돌았는데 emptySize 가 남아있다면 현재 라인의 글자가 한글자인 경우이다. 이 경우에는 뒤에 모두 붙여준다.
                for (int j = 0; j < emptySize; j++) {
                   sb.append(" ");
                }

                // 단어를 만들었으면 결과 list 에 추가해준다.
                res.add(sb.toString());
                sizeSoFar = 0;
            }
        }

        int wordCount = Integer.valueOf(queue.size());
        int emptySize = maxWidth - sizeSoFar;

        StringBuilder sb = new StringBuilder();

        while (queue.isEmpty() == false) {
            if (sb.length() > 0) {
                sb.append(" ");
                emptySize--;
            }

            sb.append(queue.poll());

            if (wordCount == 1) {
                for (int j = 0; j < emptySize; j++) {
                    sb.append(" ");
                }
                emptySize = 0;
            }

            wordCount--;
        }

        res.add(sb.toString());

        return res;
    }

    boolean isPossible(Queue<String> queue, String word, int sizeSoFar, int maxWidth) {
        if (queue.isEmpty()) {
            return true;
        }

        int emptySize = queue.size();

        return sizeSoFar + emptySize + word.length() <= maxWidth;
    }

    public static void main(String[] args) {
        String [] words = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        int maxWidth = 16;

        TextJustification textJustification = new TextJustification();
        System.out.println(textJustification.fullJustify(words, maxWidth));
    }
}
