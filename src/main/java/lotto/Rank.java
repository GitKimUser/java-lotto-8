package lotto;

import java.util.Arrays;

public enum Rank {
    // Enum 상수 정의: (일치 개수, 상금, 메시지)
    FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원) - %d개\n"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n"),
    THIRD(5, 1_500_000, "5개 일치 (1,500,000원) - %d개\n"),
    FOURTH(4, 50_000, "4개 일치 (50,000원) - %d개\n"),
    FIFTH(3, 5_000, "3개 일치 (5,000원) - %d개\n"),
    MISS(0, 0, ""); // 꽝인 경우

    private final int matchCount;
    private final long prize;
    private final String message;

    Rank(int matchCount, long prize, String message) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.message = message;
    }

    /**
     * 일치하는 번호 개수와 보너스 번호 일치 여부로 등수를 반환하는 정적 메서드
     * @param matchCount 일치하는 번호 개수
     * @param hasBonus 보너스 번호 일치 여부
     * @return 계산된 Rank
     */
    public static Rank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        // stream을 사용하여 코드를 간결하게 표현
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}