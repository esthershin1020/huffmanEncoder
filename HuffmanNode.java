/**
 * This class is a huffman node that makes up a huffman tree
 * @author Esther Shin 
 */
public class HuffmanNode{
  
  /**
   * a reference to the left child of a node in the Huffman tree
   */
  private HuffmanNode left;
  
  /**
   * a reference to the right child of a node in the Huffman tree
   */
  private HuffmanNode right;
  
  /**
   * stores the frequency of occurrences of all characters in the subtree rooted at this node. For a leaf node, this
   *  frequency is the frequency of the character in the leaf node; for an interior node, the frequency is the sum of all
   *  frequency values in the leaves of the subtree
   */
  private Integer frequency;
  
  /**
   * stores the character denoted by the node
   */
  private Character inChar;
  
  /**
   * the constructor
   * @param inChar  the character to be denoted by the node
   * @param frequency  the frequency of occurrences of a character stored in the node
   */
  public HuffmanNode(Character inChar, Integer frequency){
    this.frequency = frequency;
    this.inChar = inChar;
  }
  
  /**
   * Sets the character to be denoted by a node 
   * @param inChar  the character that is to be denoted by the node
   */
  public void setInChar(Character inChar){
    this.inChar = inChar;
  }
  
  /**
   * Method that returns the character denoted by a node
   * @return the character denoted by the node 
   */
  public Character getInChar(){
    return inChar;
  }
  
  /**
   * Sets the frequency of occurrences of a character stored in the node
   * @param frequency  the frequency (number of times) of occurrences of the character stored in the node
   */
  public void setFrequency(Integer frequency){
    this.frequency = frequency;
  }
  
  /**
   * Method that returns the frequency of occurrences of a character stored in a node
   * @return the frequency of occurrences of a character stored in a node
   */
  public int getFrequency(){
    return frequency;
  }
  
  /**
   * Method that returns the left child of a node 
   * @return the left child node of a node
   */
  public HuffmanNode getLeft(){
    return left;
  }
  
  /** 
   * Sets the left child node of a node 
   * @param left  the left child node of a node 
   */
  public void setLeft(HuffmanNode left){
    this.left = left;
  }
  
  /**
   * Method that returns the right child of a node 
   * @return the right child node of a node
   */
  public HuffmanNode getRight(){
    return right;
  }
  
  /** 
   * Sets the right child node of a node 
   * @param right  the left child node of a node 
   */
  public void setRight(HuffmanNode right){
    this.right = right;
  }
}