package entities;

import com.csc207.domain.Week;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class WeekTest {
    Week week;
    LocalDate startDate;

    /**
     * test setup in common for all the tests bellow
     */
    @Before
    public void setUp() {
        startDate = LocalDate.of(2021, 11, 20);
        week = new Week(startDate, 1L);
    }

    /**
     * tear down after running each test
     */
    @After
    public void tearDown() {
    }

    /**
     * test whether the days in the week are seven days starting with the provided startDate
     */
    @Test
    public void testGetDays(){
        assertEquals(7, week.getDays().length);
    }

    /**
     * confirm that userId in week matches user id
     */
    @Test
    public void testGetUserId(){
        assertSame(1L, week.getUserId());
    }
}
