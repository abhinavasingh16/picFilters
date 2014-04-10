public class Quicksort  {
  public short[] numbers;
  public short number;

  public void sort(short[] values) {
    // check for empty or null array
    if (values ==null || values.length==0){
      return;
    }
    this.numbers = values;
    short number = (short)(values.length);
    quicksort((short)0, (short)(number - 1));
  }

  private void quicksort(short low, short high) {
    short i = low, j = high;
    // Get the pivot element from the middle of the list
    short pivot = numbers[low + (high-low)/2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (numbers[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (numbers[j] > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(short i, short j) {
    short temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }
  
  private static String arrayToStringSing(short[] a){
    String aString;     
      aString = "";
      int column;
      int row;

      for (row = 0; row < a.length; row++) {
          aString = aString + " " + a[row];
          }
      return aString;
  }
  
  public static void main(String[] args){
    short[] a = {1,3,2,6,3,6};
    Quicksort b = new Quicksort();
    b.sort(a);
    System.out.println(arrayToStringSing(b.numbers));
  }
}