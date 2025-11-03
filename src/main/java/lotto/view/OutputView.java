package lotto.view;

import java.util.List;
import lotto.Lotto;
import lotto.Rank;
import java.util.Map;

public class OutputView {
    public static void printLottoCount(int count) {
        System.out.println(); // 실행 결과 예시의 줄바꿈을 위해 추가
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto); // Lotto 객체를 바로 출력
        }
    }

    public static void printStatistics(Map<Rank, Integer> statistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        // Enum에 정의된 순서(FIRST ~ FIFTH)의 역순으로 출력
        System.out.printf(Rank.FIFTH.getMessage(), statistics.getOrDefault(Rank.FIFTH, 0));
        System.out.printf(Rank.FOURTH.getMessage(), statistics.getOrDefault(Rank.FOURTH, 0));
        System.out.printf(Rank.THIRD.getMessage(), statistics.getOrDefault(Rank.THIRD, 0));
        System.out.printf(Rank.SECOND.getMessage(), statistics.getOrDefault(Rank.SECOND, 0));
        System.out.printf(Rank.FIRST.getMessage(), statistics.getOrDefault(Rank.FIRST, 0));
    }

    public static void printTotalReturn(double rate) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", rate);
    }
}