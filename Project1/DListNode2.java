/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public short red;
  public short green;
  public short blue;
  public int run;
  public DListNode2 prev;
  public DListNode2 next;

  /**
   *  DListNode2() constructor.
   */
  DListNode2() {
    red = 0;
    green = 0;
    blue = 0;
    run = 0;
    prev = null;
    next = null;
  }

  DListNode2(short red, short green, short blue, int run) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.run = run;
    prev = null;
    next = null;
  }

}
