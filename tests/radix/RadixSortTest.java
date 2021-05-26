package radix;

import Radix.RadixSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RadixSortTest {
    private RadixSort radixSort = new RadixSort();

    private boolean listEquals(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).intValue() != b.get(i).intValue()) {
                return false;
            }
        }
        return true;
    }

    @Test
    void validSort() {
        List<Integer> testList = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();
        testList.add(147);
        testList.add(222);
        testList.add(189);
        testList.add(12);
        testList.add(32);
        testList.add(159);
        sortedList.add(12);
        sortedList.add(32);
        sortedList.add(147);
        sortedList.add(159);
        sortedList.add(189);
        sortedList.add(222);
        testList = radixSort.sort(testList);
        assertTrue(listEquals(sortedList, testList));
        testList.clear();
        testList = radixSort.sort(testList);
        sortedList.clear();
        assertTrue(listEquals(sortedList, testList));
    }

    @Test
    void invalidSort() {
        List<Integer> testList = new ArrayList<>();
        testList.add(147);
        testList.add(222);
        testList.add(189);
        testList.add(-12);
        testList.add(32);
        testList.add(159);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> radixSort.sort(testList)),
                () -> assertThrows(IllegalArgumentException.class, () -> radixSort.sort(null))
        );
    }

    @Test
    void digit() {
        int num = 123456789;
        for (int i = 1; i < 9; i++) {
            assertEquals(10 - i, radixSort.digit(num, i));
        }
        final int secondNum = 8480;
        assertAll(
                () -> assertEquals(0, radixSort.digit(secondNum, 1)),
                () -> assertEquals(8, radixSort.digit(secondNum, 2)),
                () -> assertEquals(4, radixSort.digit(secondNum, 3)),
                () -> assertEquals(8, radixSort.digit(secondNum, 4)),
                () -> assertEquals(0, radixSort.digit(secondNum, 5)),
                () -> assertEquals(0, radixSort.digit(secondNum, 6))
        );
    }

    @Test
    void invalidDigit() {
        int num = 123;
        assertEquals(-1, radixSort.digit(num, 0));
    }

    @Test
    void validFindMaxRank() {
        List<Integer> testList = new ArrayList<>();
        testList.add(123);
        testList.add(0);
        testList.add(88);
        testList.add(9);
        assertEquals(3, radixSort.findMaxRank(testList));
        testList.clear();
        testList.add(555555);
        testList.add(1);
        testList.add(1111);
        assertEquals(6, radixSort.findMaxRank(testList));
    }

    @Test
    void invalidFindMaxRank() {
        List<Integer> testList = new ArrayList<>();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> radixSort.findMaxRank(testList)),
                () -> assertThrows(IllegalArgumentException.class, () -> radixSort.findMaxRank(null))
        );
    }
}