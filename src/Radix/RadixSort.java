package Radix;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {
    private List<Integer> digits = new ArrayList<>();
    public RadixSort() {
        for(int i = 0; i <= 9; i++) {
            digits.add(0);
        }
    }

    public List<Integer> sort(List<Integer> list) throws IllegalArgumentException {
        if(list == null || hasNegative(list)) {
            throw new IllegalArgumentException();
        }
        if(list.size() == 0) {
            return list;
        }
        int maxRank = findMaxRank(list);
        for(int i = 1; i <= maxRank; i++) {
            clearDigits();
            list = iteration(list, i);
        }
        return list;
    }

    private List<Integer> iteration(List<Integer> list, int currentPosition) {
        List<Integer> newList = new ArrayList<>();
        for(int num : list) {
            int digit = this.digit(num, currentPosition);
            if(digit != -1) {
                digits.set(digit, digits.get(digit) + 1);
            }
        }
        for(int i = 1; i <= 9; i++){
            digits.set(i, digits.get(i - 1) + digits.get(i));
        }
        for(int i = 0; i < list.size(); i++) {
            newList.add(0);
        }
        for(int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            int digit = this.digit(num, currentPosition);
            digits.set(digit, digits.get(digit) - 1);
            newList.set(digits.get(digit), num);
        }
        return newList;
    }

    public int digit(int number, int position) {
        int digit = 0;
        if(position <= 0) {
            return -1;
        }
        while(position > 0) {
            digit = number % 10;
            number /= 10;
            position -= 1;
        }
        return digit;
    }

    public int findMaxRank(List<Integer> list) throws IllegalArgumentException {
        int maxNum = Integer.MIN_VALUE;
        int rank = 0;
        if(list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }
        for(int num : list) {
            maxNum = Math.max(num, maxNum);
        }
        while (maxNum > 0) {
            maxNum /= 10;
            rank += 1;
        }
        return rank;
    }

    private boolean hasNegative(List<Integer> list){
        for(int num : list){
            if(num < 0){
                return true;
            }
        }
        return false;
    }

    private void clearDigits(){
        for(int i = 0; i <= 9; i++){
            digits.set(i, 0);
        }
    }
}
