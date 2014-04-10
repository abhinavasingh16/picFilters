/* DListNode1.java */

/**
 *  A DListNode1 is a node in a DList1 (doubly-linked list).
 */

public class DListNode1 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int red;
  public int green;
  public int blue;
  public int run;
  public DListNode1 prev;
  public DListNode1 next;

  /**
   *  DListNode1() constructor.
   */
  DListNode1() {
    red = 0;
    green = 0;
    blue = 0;
    run = 0;
    prev = null;
    next = null;
  }

  DListNode1(int red, int green, int blue, int run) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.run = run;
    prev = null;
    next = null;
  }
}
