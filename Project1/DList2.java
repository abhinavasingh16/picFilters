/* DList2.java */

/**
 *  A DList2 is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList2 {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode2 head;
  protected long size;

  /* DList2 invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList2, x.next != null.
   *  3)  For any DListNode2 x in a DList2, x.prev != null.
   *  4)  For any DListNode2 x in a DList2, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList2, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList2() constructor for an empty DList2.
   */
  public DList2() {
    head = new DListNode2();
    head.red = Integer.MIN_VALUE;
    head.green = Integer.MIN_VALUE;
    head.blue = Integer.MIN_VALUE;
    head.run = Integer.MIN_VALUE;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList2() constructor for a one-node DList2.
   */
  public DList2(int red,int green, int blue, int run) {
    head = new DListNode2();
    head.red = Integer.MIN_VALUE;
    head.green = Integer.MIN_VALUE;
    head.blue = Integer.MIN_VALUE;
    head.run = Integer.MIN_VALUE;
    head.next = new DListNode2();
    head.next.red = red;
    head.next.green = green;
    head.next.blue = blue;
    head.next.run = run;
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }

  /**
   *  insertFront() inserts an item at the front of a DList2.
   */
  public void insertFront(int red, int green, int blue, int run) {
    DListNode2 newNode = new DListNode2(red,green,blue,run);
    head.next.prev = newNode;
    head.next.prev.next = head.next;
    head.next.prev.prev = head;
    head.next = newNode;
    size++;
  }

  
  public void insertEnd(int red, int green, int blue, int run) {
    DListNode2 newNode = new DListNode2(red,green,blue,run);
    head.prev.next = newNode;
    head.prev.next.next = head;
    head.prev.next.prev = head.prev;
    head.prev = newNode; 
  }



  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList2.  If the list is empty, do nothing.
   */
  public void removeFront() {
    if (size == 0) {
    } else {
      head.next.next.prev = head;
      head.next = head.next.next;
      size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode2 current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }



}
