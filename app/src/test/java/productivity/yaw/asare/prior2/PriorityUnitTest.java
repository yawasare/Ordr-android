package productivity.yaw.asare.prior2;

import junit.framework.TestCase;

import java.util.Calendar;

import productivity.yaw.asare.ordr.Priority;

/**
 * Created by yaw on 1/5/16.
 */
public class PriorityUnitTest extends TestCase {

    Priority pLow1;
    Priority pLow2;
    Priority pLow3;
    Priority pLow4;

    Priority pMed1;
    Priority pMed2;
    Priority pMed3;
    Priority pMed4;

    Priority pHigh1;
    Priority pHigh2;
    Priority pHigh3;
    Priority pHigh4;

    Priority pMax1;
    Priority pMax2;
    Priority pMax3;
    Priority pMax4;

    Calendar today;
    Calendar yesterday;
    Calendar aFewDaysAgo;
    Calendar aWeekAgo;
    Calendar aMonthAgo;

    public void setUp() throws Exception {

        int duration1 = 1;
        int duration2 = 2;
        int duration3 = 3;
        int duration4 = 4;

        int deadline1 = 1;
        int deadline2 = 2;
        int deadline3 = 3;
        int deadline4 = 4;
        int deadline5 = 5;

        int lowImportance = 1;
        int medImportance = 2;
        int highImportance = 3;

        today = Calendar.getInstance();
        yesterday = Calendar.getInstance();
        aFewDaysAgo = Calendar.getInstance();
        aWeekAgo = Calendar.getInstance();
        aMonthAgo = Calendar.getInstance();

        yesterday.add(Calendar.DATE, -1);
        aFewDaysAgo.add(Calendar.DATE, -3);
        aWeekAgo.add(Calendar.DATE, -7);
        aMonthAgo.add(Calendar.DATE, -30);

        pLow1 = new Priority("low1", deadline5, duration1, lowImportance);
        pLow2 = new Priority("low2", deadline3, duration2, medImportance);
        pLow3 = new Priority("low3",deadline2,  duration1, lowImportance);
        pLow4 = new Priority("low4",deadline1, duration1, lowImportance);

        pMed1 = new Priority("medium1",deadline5, duration3, medImportance);
        pMed2 = new Priority("medium2",deadline3, duration1, medImportance);
        pMed3 = new Priority("medium3",deadline3, duration2, medImportance);
        pMed4 = new Priority("medium4",deadline2, duration3, lowImportance);

        pHigh1 = new Priority("high", deadline1, duration2, medImportance);
        pHigh2 = new Priority("high", deadline3, duration3, highImportance);
        pHigh3 = new Priority("high", deadline2, duration2, highImportance);
        pHigh4 = new Priority("high", deadline2, duration3, highImportance);


        pMax1 = new Priority("max", deadline1, duration3, highImportance);
        pMax2 = new Priority("max",deadline2, duration4, highImportance);
        pMax3 = new Priority("max", deadline1, duration4, medImportance);
        pMax4 = new Priority("max", deadline1, duration3, highImportance);
    }

    public void testPriorityCalculatePriorityLevel(){

        pLow1.setCreated(today);
        pLow1.calculatePriorityLevel();
        assertEquals(1, pLow1.getPriorityLevel());

        pLow1.setCreated(aFewDaysAgo);
        pLow1.calculatePriorityLevel();
        assertEquals(1, pLow1.getPriorityLevel());

        pLow4.setCreated(today);
        pLow4.calculatePriorityLevel();
        assertEquals(1, pLow4.getPriorityLevel());

        pLow4.setCreated(aFewDaysAgo);
        pLow4.calculatePriorityLevel();
        assertEquals(4, pLow4.getPriorityLevel());

        pMed1.setCreated(today);
        pMed1.calculatePriorityLevel();
        assertEquals(2, pMed1.getPriorityLevel());

        pMed4.setCreated(today);
        pMed4.calculatePriorityLevel();
        assertEquals(2, pMed4.getPriorityLevel());

        pMed4.setCreated(aFewDaysAgo);
        pMed4.calculatePriorityLevel();
        assertEquals(3, pMed4.getPriorityLevel());

        pHigh1.setCreated(today);
        pHigh1.calculatePriorityLevel();
        assertEquals(3, pHigh1.getPriorityLevel());


        pMax4.setCreated(today);
        pMax4.calculatePriorityLevel();
        assertEquals(4, pMax4.getPriorityLevel());

    }

    public void testPriorityCompareTo(){

        pLow1.setCreated(today);
        pMed1.setCreated(today);
        pHigh1.setCreated(today);
        pMax1.setCreated(today);

        assertEquals( 1, pLow1.compareTo(pMed1));
        assertEquals(1, pMed1.compareTo(pHigh1));
        assertEquals(1, pHigh1.compareTo(pMax1));
        assertEquals(1, pLow1.compareTo(pHigh1));
    }
}
