import java.util.Scanner;
import java.util.Arrays;

public class Solution {

  private static int lowest_subtractValue;
  private static int highest_subtractValue;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numberOfElements = scanner.nextInt();
    int[] input = new int[numberOfElements];

    for (int i = 0; i < numberOfElements; i++) {
      input[i] = scanner.nextInt();
    }
    lowest_subtractValue = scanner.nextInt();
    highest_subtractValue = scanner.nextInt();
    scanner.close();

    int result = findMinMax(input);
    System.out.println(result);
  }

  /**
   * Finds the maximum of all local minimums: minMax. Each local minimum is the smallest possible
   * absolute difference between each array element and the current_subtractValue. The subtract
   * value range (inclusive) is from lowest_subtractValue to highest_subtractValue.
   *
   * The minMax, formed in the above manner, will occur at one of the following points: 
   * 1. current_subtractValue = (input[i]+inpit[i+1])/2. 
   * 2. current_subtractValue = lowest_subtractValue.
   * 3. current_subtractValue = highest_subtractValue.
   *
   * @return An integer, representing the subtract value for minMax. If there are multiple minMax,
   *         then the smallest subtract value is returned.
   */
  private static int findMinMax(int[] input) {

    Arrays.sort(input);

    // Corner case one: The highest array value is below, or equal to, the lowest_subtractValue.
    if (input[input.length - 1] <= lowest_subtractValue) {
      return highest_subtractValue;
    }

    // Corner case two: The lowest array value is above, or equal to, the highest_subtractValue.
    if (input[0] >= highest_subtractValue) {
      return lowest_subtractValue;
    }

    int minMax = Integer.MIN_VALUE;
    int subtractValue_minMax = 0;

    // Corner case three: current_subtractValue = (input[i]+input[i+1])/2 > lowest_subtractValue.
    int min = checkMinimum_currentSubtractValue_isOutsideRangeBoundary(input, lowest_subtractValue);
    if (min > minMax) {
      minMax = min;
      subtractValue_minMax = lowest_subtractValue;
    }

    // Corner case four: current_subtractValue = (input[i]+input[i+1])/2 < highest_subtractValue.
    min = checkMinimum_currentSubtractValue_isOutsideRangeBoundary(input, highest_subtractValue);
    if (min > minMax) {
      minMax = min;
      subtractValue_minMax = highest_subtractValue;
    }

    for (int i = 0; i + 1 < input.length; i++) {
      int current_subtractValue = (input[i] + input[i + 1]) / 2;
      min = checkMinimum_currentSubtractValue_isInRangeBoundary(input[i], current_subtractValue);
      if (min > minMax) {
        minMax = min;
        subtractValue_minMax = current_subtractValue;
      }
    }

    return subtractValue_minMax;
  }

  /** 
  * The method is applied to check for local min at (input[i]+input[i+1])/2. 
  */
  private static int checkMinimum_currentSubtractValue_isInRangeBoundary(
      int array_element, int current_subtractValue) {
    if (current_subtractValue < lowest_subtractValue
        || current_subtractValue > highest_subtractValue) {
      return -1;
    }
    return current_subtractValue - array_element;
  }

  /**
   * The method is applied to check for local min at lowest_subtractValue / highest_subtractValue.
   *
   * To streamline the algorithm, this method has to be distinct from method
   * 'checkMinimum_currentSubtractValue_isInRangeBoundary' becasue the latter method does not
   * require an iteration through the array.
   */
  private static int checkMinimum_currentSubtractValue_isOutsideRangeBoundary(
      int input[], int current_subtractValue) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < input.length; i++) {
      min = Math.min(min, Math.abs(input[i] - current_subtractValue));
    }
    return min;
  }
}
