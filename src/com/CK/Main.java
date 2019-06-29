package com.CK;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{3,0,-2,-1,1,2};
        Solution2SumPointer solution = new Solution2SumPointer();
        System.out.println(solution.threeSum(arr).toString());
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        int threeSum = 0, twoSum = 0;
        boolean zero = false;
        List<List<Integer>> finalResult = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                twoSum = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    threeSum = nums[k] + twoSum;
                    if (threeSum == 0) {
                        if (nums[k] == 0 && nums[j] == 0 && nums[i] == 0) {
                            zero = true;
                        } else if (finalResult.isEmpty() && !(nums[k] == 0 && nums[j] == 0 && nums[i] == 0)) {
                            addSingleResultToFinalResult(finalResult, nums[k], nums[j], nums[i]);
                        } else if (!(nums[k] == 0 && nums[j] == 0 && nums[i] == 0)) {
                            int occurence = 0;
                            for (List<Integer> integers : finalResult) {
                                if (integers.contains(nums[k]) && integers.contains(nums[j]) && integers.contains(nums[i])) {
                                    occurence += 1;
                                }
                            }
                            if (occurence == 0) {
                                addSingleResultToFinalResult(finalResult, nums[k], nums[j], nums[i]);
                            }
                        }
                    }
                }
            }
        }
        if (zero) {
            addSingleResultToFinalResult(finalResult, 0, 0, 0);
        }
        return finalResult;
    }

    private void addSingleResultToFinalResult(List<List<Integer>> finalResult, int number1, int number2, int number3) {
        List<Integer> singleResult = new ArrayList<Integer>();
        singleResult.add(number1);
        singleResult.add(number2);
        singleResult.add(number3);
        finalResult.add(singleResult);
    }
}

class Solution2SumPointer {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i !=0 && nums[i] == nums[i-1])continue;
            int left = i + 1, right = nums.length - 1, sum = 0 - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (right < left && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }else if (nums[left] + nums[right] < sum){
                    left++;
                } else  {
                    right--;
                }
            }
        }
        return res;
    }
}