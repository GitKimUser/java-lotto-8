package lotto.controller;

import lotto.util.Validator;
import lotto.view.InputView;

public class LottoGameController {

    public void start() {
        int purchaseAmount = getPurchaseAmount();
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
}