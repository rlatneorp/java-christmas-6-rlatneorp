package christmas.domain;

import static christmas.domain.Constant.*;

public class Badge {

    public static String giveBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= SANTA_BADGE) {
            return "산타";
        }
        if (totalBenefitAmount >= TREE_BADGE) {
            return "트리";
        }
        if (totalBenefitAmount >= STAR_BADGE) {
            return "별";
        }
        return "없음";
    }
}
