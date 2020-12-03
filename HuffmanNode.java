// Ranveer Singh
// 6/6/19
// CSE 143 A
// TA: Tyler Mi
// Assignment #8
//
// This class is used to create a single binary search tree node

public class HuffmanNode implements Comparable<HuffmanNode> {
   // stores data of the node
   public int data;
   // stores the ascii value of the character
   public int ascii;
   // stores the node left of current node
   public HuffmanNode left;
   // stores the node right of current node
   public HuffmanNode right;
   
   // constructs a node with ascii value -1
   // and data value 0
   public HuffmanNode() {
      this(0, -1);
   }
   
   // constructs a leaf node with given data
   public HuffmanNode(int data, int ascii) {
      this(data, ascii, null, null);
   }
   
   // constructs a node with data value of left.data + right.data,
   // ascii value equal to -1,
   // with given left and right subtree
   public HuffmanNode(HuffmanNode left, HuffmanNode right) {
      this(left.data + right.data, -1, left, right);
   }
   
   // constructs a branch node with given data, given ascii value,
   // left subtree, and right subtree
   public HuffmanNode(int data, int ascii, HuffmanNode left,
                                          HuffmanNode right) {
      this.ascii = ascii;
      this.data = data;
      this.left = left;
      this.right = right;
   }
   
   // compares the node to another node
   public int compareTo(HuffmanNode other) {
      return data - other.data;
   }
}