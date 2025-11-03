package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.Lotto;
import lotto.Rank;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private static final int LOTTO_PRICE = 1000;
    private static final String DELIMITER = ",";

    public void start() {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = issueLottos(purchaseAmount);
        Lotto winningLotto = new Lotto(getWinningNumbers());
        int bonusNumber = getBonusNumber(winningLotto.getNumbers());
        Map<Rank, Integer> statistics = calculateStatistics(lottos, winningLotto, bonusNumber);
        OutputView.printStatistics(statistics);
        calculateAndPrintReturn(statistics, purchaseAmount);
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

    private List<Integer> getWinningNumbers() {
        while (true) {
            try {
                String input = InputView.readWinningNumbers();
                Validator.validateWinningNumbers(input);
                return Arrays.stream(input.split(DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                String input = InputView.readBonusNumber();
                Validator.validateBonusNumber(input, winningNumbers);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<Rank, Integer> calculateStatistics(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            int matchCount = countMatches(lotto, winningLotto);
            boolean hasBonus = lotto.contains(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, hasBonus);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
        return statistics;
    }

    private int countMatches(Lotto userLotto, Lotto winningLotto) {
        return (int) userLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
    }

    private void calculateAndPrintReturn(Map<Rank, Integer> statistics, int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : statistics.keySet()) {
            totalPrize += rank.getPrize() * statistics.get(rank);
        }

        double rateOfReturn = (double) totalPrize / purchaseAmount * 100;
        OutputView.printTotalReturn(rateOfReturn);
    }
}