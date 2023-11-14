package christmas;

import christmas.controller.EventController;
import christmas.domain.*;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static christmas.domain.DiscountCalculation.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    @DisplayName("배지가 올바르게 반환되는지 테스트")
    void testGiveBadge() {
        assertEquals("산타", Badge.giveBadge(21000));
        assertEquals("트리", Badge.giveBadge(11000));
        assertEquals("별", Badge.giveBadge(5000));
        assertEquals("없음", Badge.giveBadge(1000));
    }

    @Test
    @DisplayName("특정 날짜가 주말인지 확인")
    void testWeekdayCheck() {
        assertTrue(EventController.weekdayCheck(30));
        assertFalse(EventController.weekdayCheck(31));
    }

    @Test
    @DisplayName("특정 날짜가 일요일인지 확인")
    void testIsSunday() {
        assertTrue(EventController.isSunday(24));
        assertFalse(EventController.isSunday(25));
    }


}

