// Ranveer Singh
// 6/6/19
// This class compresses text files using Huffman coding

import java.util.*;
import java.io.*;

public class HuffmanTree {
   // stores the overall entry in the tree
   private HuffmanNode overallRoot;
   // stores a list of HuffmanNodes for each character with a 
   // frequency greater than 0
   private Queue<HuffmanNode> q;
     
   // constructs a HuffmanTree
   public HuffmanTree(int[] count) {
      q = new PriorityQueue<HuffmanNode>();
      for (int i = 0; i < count.length; i++) {
         if (count[i]!= 0) {
            q.add(new HuffmanNode(count[i], i));
         }                 
      }
      q.add(new HuffmanNode(1, count.length));
      overallRoot = treeCreator();     
   }
   
   // reconstructs the tree from a file
   public HuffmanTree(Scanner input) {
      overallRoot = new HuffmanNode();
      while (input.hasNextLine()) {
         int n = Integer.parseInt(input.nextLine());
         String binary = input.nextLine();
         overallRoot = treeReconstructor(overallRoot, n, binary);      
      }
   }
   
   // creates our tree
   // returns new overallRoot
   private HuffmanNode treeCreator() {
      if (q.size() == 1) {
         return q.remove();
      } else {
         q.add(new HuffmanNode(q.remove(), q.remove()));
         return treeCreator();
      }
   }
   
   // reconstructs our tree using a root(HuffmanNode root)
   // and ascii value(int ascii), and String(String binary)
   // returns new entries in the tree
   private HuffmanNode treeReconstructor(HuffmanNode root, int ascii, String binary) {
      if (!binary.isEmpty()) {
         if (root == null) {
            root = new HuffmanNode();
            root.left = treeReconstructor(root, ascii, binary);
         } else {
            if (binary.charAt(0) == '0') {
               root.left = treeReconstructor(root.left, ascii, binary.substring(1));
            } else {
               root.right = treeReconstructor(root.right, ascii, binary.substring(1));
            }
         }       
      } else {
         return new HuffmanNode(0, ascii);
      }
      return root;
   }
   
   // prints the tree to the given output(PrinstStream output) 
   public void write(PrintStream output) {
      write(output, overallRoot, "");
   }
   
   // prints the tree to the given output(PrintStream output) 
   // root(HuffmanNode root) and a String(String binary)
   private void write(PrintStream output, HuffmanNode root, String binary) {
      if (root.left == null && root.right == null) {
         output.println(root.ascii);
         output.println(binary);
      } else {
         write(output, root.left, binary + 0);
         write(output, root.right, binary + 1);
      }
   }
   
   // reads individual bits from the input stream(BitInputStream input) and 
   // writes the corresponding characters to the output(PrintStream output)
   // using an end of file number(int eof)
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode root = overallRoot;
      while (root.ascii != eof) {
         if (root.left == null && root.right == null) {
            output.write(root.ascii);
            root = overallRoot;
         } else {
            int n = input.readBit();
            if (n == 0) {
               root = root.left;
            } else {
               root = root.right;
            }
         }
      }
   }
}
