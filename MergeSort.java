import java.util.Arrays;

/**
 * Lab 1: Java Basics, Merge Sort and Maven <br />
 * The {@code MergeSort} class
 * @author Paige Lekach
 */
public class MergeSort {

    // More methods can be added here
    
    /**
     * Helper method used to merge the two subarrays created in sort that are passed in as params
     * This method takes merges the subarrays based on order and compares to elements of the two subarrays
     * @param left @code int[]} The integer subarray to be merged together with right into result
     * @param right @code int[]} The integer subarray to be merged together with left into result
     */
    public static int[] merge(int left[], int right[]){
        // lengths of the subarrays
        int lLength = left.length;
        int rLength = right.length;
        int[] result = new int[lLength + rLength];

        int i = 0;
        // while the arrays are not empty, copy over the subarrays in order depending on which values are the smallest
        while(lLength > 0 && rLength > 0){
            if(left[0] <= right[0]){
                result[i] = left[0];
                left = Arrays.copyOfRange(left, 1, lLength);
                lLength = left.length;
            } else{
                result[i] = right[0];
                right = Arrays.copyOfRange(right, 1, rLength);
                rLength = right.length;
            }
            i++;
        }

        // If there are still elements left in left subarray, then copy those over to the resulting array
        if(lLength > 0){
            for(int j = 0; j < lLength; j++){
                result[i] = left[j];
                i++;
            }
        }
        // If there are still elements left in right subarray, then copy those over to the resulting array
        if(rLength > 0){
            for(int k = 0; k < rLength; k++){
                result[i] = right[k];
                i++;
            }
        }
        return result;

    }

    /**
     * The merge sort procedure, merges the sorted subarrays together to result in the final sorted numbers array
     * @param numbers   {@code int[]} The integer array to be sorted
     */
    public static int[] sort(int[] numbers) {
        // lengths of arrays
        int arrayLength = numbers.length;
        int[] left = new int[arrayLength/2];
        int[] right = new int[arrayLength - arrayLength/2];

        // TODO: Lab 1 -- write mergesort here
        // if array is only one element then return numbers, else find the middle point of the array and copy the two sides into
        // left and right 
        if(arrayLength <= 1){
            return numbers;
        } else{
            int middle = numbers.length/2;
            for(int i = 0; i < middle; i++){
                left[i] = numbers[i];
            }
            for(int j = middle; j < numbers.length; j++){
                //System.out.println("In second loop");
                right[j-middle] = numbers[j];
            }

            // recursively calling sort, until length is 1 and they can be merged back together
            left = sort(left);
            right = sort(right);

            if(left[left.length-1] <= right[0]){
                int[] finalArray = new int[left.length + right.length];
                System.arraycopy(left, 0, finalArray, 0, left.length);
                System.arraycopy(right, 0, finalArray, left.length, right.length);
                return finalArray;
            }
            
            int[] mergedArray = new int[left.length + right.length];
            mergedArray = merge(left, right);
            System.arraycopy(mergedArray, 0, numbers, 0, numbers.length);
        }


	return numbers;
    }

    /**
     * Main entry: test the HeapSort
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 200);
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        numbers = sort(numbers);

        for (int n: numbers)
            System.out.print(n + " ");
        System.out.println();
    }

}
