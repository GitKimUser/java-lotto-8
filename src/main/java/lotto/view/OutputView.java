package lotto.view;

import java.util.List;
import lotto.Lotto;

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
}