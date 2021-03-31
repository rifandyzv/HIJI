import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Arrab {
    static void shuffleArray(int[] ar)
  {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }

  public static void main(String[] args) {
      int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
      shuffleArray(arr);

      for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i] + " ");
      }
  }
}
