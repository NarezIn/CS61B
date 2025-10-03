import java.util.*;
public class ClassNameHere {
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> numFreq = new HashMap<>();
        for (int num : nums){
            numFreq.put(num, numFreq.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] freqList = new ArrayList[nums.length];
        for (int num : nums){
            int index = numFreq.get(num) - 1;
            if (freqList[index] == null){
                freqList[index] = new ArrayList<>();
                freqList[index].add(num);
            }
            else{
                freqList[index].add(num);
            }
        }
        int track = 0;
        int ii = nums.length;
        while (track < k){
            ii--;
            if (freqList[ii] != null){
                for (int item : freqList[ii]){
                    result[track++] = item;
                    if (track >= k) break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        topKFrequent(arr, 2);
    }
}
