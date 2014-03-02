/* DList1.java */

/**
 *  A DList1 is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList1 {

  /**
   *  head references the first node.
   *  tail references the last node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode1 head;
  protected DListNode1 tail;
  protected long size;

  /* DList1 invariants:
   *  1)  head.prev == null.
   *  2)  tail.next == null.
   *  3)  For any DListNode1 x in a DList, if x.next == y and x.next != null,
   *      then y.prev == x.
   *  4)  For any DListNode1 x in a DList, if x.prev == y and x.prev != null,
   *      then y.next == x.
   *  5)  The tail can be accessed from the head by a sequence of "next"
   *      references.
   *  6)  size is the number of DListNode1s that can be accessed from the
   *      head by a sequence of "next" references.
   */

  /**
   *  DList1() constructor for an empty DList1.
   */
  public DList1() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  DList1() constructor for a one-node DList1.
   */
  public DList1(int red,int green, int blue, int run) {
    head = new DListNode1(red,green,blue,run);
    tail = head;
    size = 1;
  }

  /**
   *  DList1() constructor for a two-node DList1.
   */
  public DList1(int red1,int green1, int blue1, int run1, int red2,int green2, int blue2, int run2) {
    head = new DListNode1(red1,green1,blue1,run1);
    tail = new DListNode1(red2,green2,blue2,run2);
    head.next = tail;
    tail.prev = head;
    size = 2;
  }

  public DListNode1 copyDList(DListNode1 node) {
    DListNode1 copyNode = new DListNode1();
    copyNode.red = node.red;
    copyNode.green = node.green;
    copyNode.blue = node.blue;
    copyNode.run = node.run;
    return copyNode;
  }

  /**
   *  insertFront() inserts an item at the front of a DList1.
   */
  public void insertFront(int red,int green, int blue, int run) {
    DListNode1 newNode = new DListNode1(red,green,blue,run);
    if (this.size == 0){
      head = newNode;
      tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  
  public void insertEnd(int red,int green, int blue, int run) {
    DListNode1 newNode = new DListNode1(red,green,blue,run);
    if (this.size == 0) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail.next.prev = tail.next;
      tail = tail.next;
    }
    size++;
  }

  /**
   *  removeFront() removes the first item (and node) from a DList1.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    if (size == 0) {
    } else if (size == 1) {
      head = null;
      tail = null;
      size--;
    } else{
        head = head.next;
        head.prev = null;
        size--;
    }
  }

  public void removeSecond() {
    if (size == 0 || size == 1) {
    } else if (size == 2) {
      head = null;
      tail = null;
      size--;
    } else{
      head.next = head.next.next;
      head.next.prev = head;
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
    DListNode1 current = head;
    while (current != null) {
      result = result + current.red + current.green+current.blue+current.run+ "  ";
      current = current.next;
    }
    return result + "]";
  }

}
