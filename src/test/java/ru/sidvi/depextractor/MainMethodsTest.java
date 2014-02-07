package ru.sidvi.depextractor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sidvi on 07.02.14.
 */
public class MainMethodsTest {

    @Test
    public void should() {
        Day d = Weekday.MON;
        Day d1 = WeekendDay.SAT;
//        assertThat(d, equalTo(d1));
    }

    interface Day {
    }
    public enum Weekday implements Day {
        MON, TUE, WED, THU, FRI;
    }
    public enum WeekendDay implements Day {
        SAT, SUN;
    }
}
