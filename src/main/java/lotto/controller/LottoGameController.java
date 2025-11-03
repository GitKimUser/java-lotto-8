package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private static final int LOTTO_PRICE = 1000;

    public void start() {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = issueLottos(purchaseAmount);
    }

    private int getPurchaseAmount() {
        while (true) {
            try {
                String input = InputView.readPurchaseAmount();
                Validator.validatePurchaseAmount(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Lotto> issueLottos(int purchaseAmount) {
        int lottoCount = purchaseAmount / LOTTO_PRICE;
        OutputView.printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }

        OutputView.printLottos(lottos);
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}