package lotto.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DELIMITER = ",";

    public static void validatePurchaseAmount(String input) {
        validateIsNumeric(input);
        int amount = Integer.parseInt(input);
        validateIsThousandUnit(amount);
    }

    public static void validateWinningNumbers(String input) {
        String[] numbers = input.split(DELIMITER);
        validateNumberCount(numbers);
        List<Integer> winningNumbers = Arrays.stream(numbers)
                .map(String::trim)
                .peek(Validator::validateIsNumeric)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        validateNumberRange(winningNumbers);
        validateDuplicate(winningNumbers);
    }

    public static void validateBonusNumber(String input, List<Integer> winningNumbers) {
        validateIsNumeric(input);
        int bonusNumber = Integer.parseInt(input);
        validateNumberRange(List.of(bonusNumber));
        validateBonusNumberDuplicate(bonusNumber, winningNumbers);
    }

    private static void validateIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "숫자를 입력해야 합니다.");
        }
    }

    private static void validateIsThousandUnit(int amount) {
        if (amount <= 0 || amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    private static void validateNumberCount(String[] numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 6개여야 합니다.");
        }
    }

    private static void validateNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ERROR_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }


    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateBonusNumberDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}