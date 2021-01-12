import java.util.ArrayList;
/**
 * Class that contains methods associated with creating and operating a min-on-top heap arrayList 
 * @author Esther Shin
 */
public class Project2Sort{
  
  /**
   * an arrayList of huffman nodes that represent the min-on-top heap array to be used to create the Huffman tree 
   * in the HuffmanCompressor class
   */
  private ArrayList<HuffmanNode> heapArray = new ArrayList<HuffmanNode>();

  /**
   * Method that returns the number of elements in the heapArray arrayList
   * @return the number of elements (AKA the size) in the heapArray
   */
  public int getSize(){
    return heapArray.size();
  }
  
  /**
   * Method that returns the Huffman node stored at the specified index in the heapArray arrayList
   * @param index  the position (in the heapArray) of the Huffman node that you want returned 
   * @return the Huffman node stored at the specified index position in the heapArray
   */
  public HuffmanNode getNode(int index){
    return heapArray.get(index);
  }
  
  /**
   * Method that returns the left child of the Huffman node stored at the specified index in the heapArray arrayList
   * @param index  the position (in the heapArray) of the Huffman node whose left child you want returned
   * @return the left child of the Huffman node stored at the specified index position in the heapArray 
   */
  public HuffmanNode getLeft(int index){
    return heapArray.get((2*index) + 1);
  }
  
  /**
   * Method that returns the right child of the Huffman node stored at the specified index in the heapArray arrayList
   * @param index  the position (in the heapArray) of the Huffman node whose right child you want returned
   * @return the right child of the Huffman node stored at the specified index position in the heapArray 
   */
  public HuffmanNode getRight(int index){
    return heapArray.get((2*index) + 2);
  }
  
  /**
   * Method that returns the character stored in Huffman node stored at the specified index in the heapArray arrayList
   * @param index  the position (in the heapArray) of the Huffman node whose character you want returned
   * @return the character stored in Huffman node stored at the specified index in the heapArray
   */
  public Character getChar(int index){
    return heapArray.get(index).getInChar();
  }
  
  /**
   * Method that returns the frequency of occurrences (of a character) stored in Huffman node stored at the specified index in the heapArray arrayList
   * @param index  the position (in the heapArray) of the Huffman node whose frequency you want returned
   * @return the frequency stored in Huffman node stored at the specified index in the heapArray
   */
  public Integer getFreq(int index){
    return heapArray.get(index).getFrequency();
  }
  
  /**
   * Helper that swaps a Huffman node at index1 position with a Huffman node in the index2 position
   * @param index1  the position (in the arrayList) of the first Huffman node to be swapped 
   * @param index2  the position (in the arrayList) of the second Huffman node to be swapped
   * @param a  the arrayList (of Huffman nodes) in which the swapping will take place
   */
  public void swap(int index1, int index2, ArrayList<HuffmanNode> a){
    /**
     * temp: a temporary Huffman node used to store/remember the Huffman node at index1 so that it can be swapped and 
     *  stored in the index2 position later on
     */
    HuffmanNode temp = a.get(index1);
    a.set(index1, a.get(index2));
    a.set(index2, temp); 
  }
  
  /**
   * Method that inserts a Huffman node into the heapArray, and then sifts up the node if necessary in order 
   *  to make the heapArray a true min-on-top heap array 
   * @param item  the Huffman node to be inserted into the heapArray
   */
  public void insert(HuffmanNode item){
    heapArray.add(heapArray.size(), item);
    siftUp(heapArray.size() - 1);
  }
  
  /**
   * Helper method that sifts up a Huffman node in the heapArray after its insertion in order to make sure that heapArray is a 
   *  min-on-top heap 
   * @param i  the position in the arrayList of the Huffman node that is to be sifted up 
   */
  public void siftUp(int i){
    /**
     * leftChild: stores the position of the left child of the Huffman node stored at index/position i 
     */
    int leftChild = (2*i) + 1;
    
    /**
     * rightChild: stores the position of the left child of the Huffman node stored at index/position i 
     */
    int rightChild = (2*i) + 2;
    
    /**
     * parent: stores the position of the parent of the Huffman node stored at index/position i 
     */
    int parent = (i-1)/2;
    
    /**
     * starting at the position of input i, goes through the enire heapArray and swaps a Huffman node with its parent 
     *  if the parent's frequency is greater than the frequency of the Huffman node at position i. Then updates i to equal 
     *  the parent in order to move up the heapArray
     */
    while(i > 0 && (((Integer)heapArray.get(parent).getFrequency()).compareTo((Integer)heapArray.get(i).getFrequency()) > 0)){ 
      swap(parent, i, heapArray);
      i = parent;
      parent = (i-1)/2;
    }
  }
  
  /**
   * Method that removes the root Huffman node at the top of the heapArray, and then sifts down the new Huffman node at the root
   *  in order to make the heapArray a true min-on-top heap array
   */
  public HuffmanNode removeMin(){
    /**
     * toRemove: variable that stores the original root Huffman node so that it can be returned at the end after it is removed
     */
    HuffmanNode toRemove = heapArray.get(0);
    swap(0, heapArray.size() - 1, heapArray);
    heapArray.remove(heapArray.size() - 1);
    siftDown(0);
    return toRemove;
  }
  
  /**
   * Helper method that sifts down the root Huffman node in the heapArray after removal of the minimum node at the top of the heap
   *  in order to make sure that heapArray is a min-on-top heap 
   * @param i  the position in the arrayList of the Huffman node that is to be sifted down 
   */
  public void siftDown(int i){
    /**
     * cursor: stores the position in the arrayList of the Huffman node that is to be sifted down 
     */
    int cursor = i;
    
    /**
     * child: stores the position of the left child of the Huffman node stored at index/position i
     */
    int child = (2*i) + 1;
    
    if(child < heapArray.size()){
      /**
       * Goes through the entire heapArray
       */
      while(child < heapArray.size()){
        /**
         * If there is a right child, and the right child's frequency is less than the left child, then use the right child
         */
        if(child + 1 < heapArray.size() && (((Integer)heapArray.get(child + 1).getFrequency()).compareTo((Integer)heapArray.get(child).getFrequency()) < 0)){
          child = child + 1;
        }
        /**
         * If the frequency of node at position i is greater than the frequency of its child (right child if above if statement is completed, left if it is not), 
         *  then swap them and update the value of the cursor and child in order to move down the heap and continue sifting
         */
        if((((Integer)heapArray.get(i).getFrequency()).compareTo((Integer)heapArray.get(child).getFrequency()) > 0)){
          swap(cursor, child, heapArray);
          cursor = child;
          child = (2*cursor) + 1;
        }
        else{
          break;
        }
      }
    }
  }
}


