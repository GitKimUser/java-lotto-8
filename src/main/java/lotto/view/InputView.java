package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    /**
     * 사용자로부터 로또 구입 금액을 입력받는 메서드
     * @return 사용자가 입력한 문자열
     */
    public static String readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }
}