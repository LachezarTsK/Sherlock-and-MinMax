import java.util.Arrays;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numberOfElements = scanner.nextInt();
    int[] input = new int[numberOfElements];

    for (int i = 0; i < numberOfElements; i++) {
      input[i] = scanner.nextInt();
    }
    int lowest_subtractValue = scanner.nextInt();
    int highest_subtractValue = scanner.nextInt();
    scanner.close();

    int result = findMinMax(input, lowest_subtractValue, highest_subtractValue);
    System.out.println(result);
  }

  /**
   * Finds the maximum of all local minimums (MinMax). Each local minimum is the smallest possible
   * absolute difference between each array element and the current subtract value. The subtract
   * value range (inclusive) is from lowest_subtractValue to highest_subtractValue.
   *
   * @return An integer, representing the subtract value for MinMax. If there are multiple MinMax,
   *         then the smallest subtract value is returned.
   */
  private static int findMinMax(int[] input, int lowest_subtractValue, int highest_subtractValue) {

    Arrays.sort(input);
    int minMax = Integer.MIN_VALUE;
    int subtractValue_MinMax = 0;

    // Corner case one: The highest array value is below, or equal to, the lowest_subtractValue.
    if (input[input.length - 1] <= lowest_subtractValue) {
      subtractValue_MinMax = highest_subtractValue;
      return subtractValue_MinMax;
    }

    // Corner case two: The lowest array value is above, or equal to, the highest_subtractValue.
    if (input[0] >= highest_subtractValue) {
      subtractValue_MinMax = lowest_subtractValue;
      return subtractValue_MinMax;
    }

    int startIndex_innerLoop = 0;

    while (lowest_subtractValue <= highest_subtractValue) {
      int localMin = Integer.MAX_VALUE;

      for (int i = startIndex_innerLoop; i < input.length; i++) {
        int current = Math.abs(input[i] - lowest_subtractValue);

        if (current < localMin) {
          startIndex_innerLoop = i;
          localMin = current;
        }
        // If the localMin is found, break innerLoop for current lowest_subtractValue.
        else if (current >= localMin) {
          break;
        }
      }

      if (minMax < localMin) {
        minMax = localMin;
        subtractValue_MinMax = lowest_subtractValue;
      }

      lowest_subtractValue++;
    }

    return subtractValue_MinMax;
  }
}
