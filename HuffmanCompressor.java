import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.*;
import java.util.ArrayList.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Class that produces a Huffman encoding of English characters using an arrayList
 * I chose to implement arrayList because it seemed like it would be easier to make the Huffman tree using a min-on-top heap array, and because 
 *  I wanted to be able to easily access different characters and corresponding frequencies (easier access of different indexes comes with arrayLists, not linkedLists)
 * @author Esther Shin 
 */
public class HuffmanCompressor{
  
  /**
   * stores the min-on-top heap array (that stores Huffman nodes) that will be used to create the final Huffman tree
   */
  private static Project2Sort minHeapArray = new Project2Sort();
  
  /**
   * a hashMap storing pairs of a character (that exists in the input file) and its corresponding frequency of occurrences
   */
  private static HashMap<Character, Integer> map = new HashMap<Character, Integer>();
  
  /**
   * an arrayList of tuples in which each tuple contains a character (that exists in the input file) and its corresponding encoding
   */
  private static ArrayList<Tuple> charEncodingArray = new ArrayList<Tuple>(); 
  
  /**
   * Method that reads and compresses an input text file inputFileName, produces a Huffman encoding of the input file, and outputs
   *  the compressed file in outputFileName
   * @param inputFileName  the name of the file that you want to input and a produce a Huffman encoding of 
   * @param outputFileName  the name of the output file that contains the Huffman encoding of the input file
   */
  public static void huffmanCoder(String inputFileName, String outputFileName) throws IOException{
    characterEncoding(huffmanTree(merger(huffmanNodeCreator(inputFileName))));
    outputFileAndSavings(inputFileName, outputFileName);
  }
  
  /**
   * Helper method that scans the input text file to generate the initial list of HuffmanNodes, and then makes that list into a min-on-top heap arrayList
   * @param inputFileName  the name of the file you want to input and a produce Huffman encoding of
   * @return  the min-on-top heap arrayList that will be used to create a Huffman tree
   */
  public static Project2Sort huffmanNodeCreator(String inputFileName) throws IOException{
    /**
     * key: stores the character at a certain position in the String of the input file
     */
    Character key;
    
    /**
     * numberOfKeys: keeps track of the number of keys in the map therefore keeping track of the number of existing characters in the input file String 
     */
    int numberOfKeys = 0;
    
    /**
     * book: stores the input file as a String
     */
    String book = Files.lines(Paths.get(inputFileName), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
    
    /**
     * Goes through the entire input file String (book) and puts existing characters and their corresponding frequencies into the hashMap 
     */
    for(int index = 0; index < book.length(); index = index + 1){
      key = book.charAt(index);
      if(map.get(key) != null){
        map.put(key, map.getOrDefault(key, 0) + 1);//replace 
      }
      else{
        map.put(key, 1);
        numberOfKeys = numberOfKeys + 1;
      }
    }
    /**
     * huffmanNodeArray: an arrayList of HuffmanNodes that is the size of the number of characters that exist in the input file, 
     *  and stores each character with its corresponding frequency occurrence number
     */
    ArrayList<HuffmanNode> huffmanNodeArray = new ArrayList<HuffmanNode>(numberOfKeys);
    
    /**
     * mapIterator: an iterator that iterates through the created hashMap
     */
    Iterator mapIterator = map.entrySet().iterator();
    /**
     * Iterates through the hashMap and puts each character and corresponding frequency into each individual HuffmanNode of the arrayList 
     */
    while(mapIterator.hasNext() == true){
      Map.Entry mapStuff = (Map.Entry)mapIterator.next();
      huffmanNodeArray.add(new HuffmanNode((Character)mapStuff.getKey(), (Integer)mapStuff.getValue()));//, null, null));
    }
    
    /**
     * Goes through the entire huffmanNodeArray and inserts each node into the minHeapArray, creating a min-on-top heap arrayList in the process
     */
    for(int b = 0; b < huffmanNodeArray.size(); b = b + 1){
      minHeapArray.insert(huffmanNodeArray.get(b));
    }
    return minHeapArray;
  } 
  
  /**
   * Helper method that merges two HuffmanNodes and return the combined HuffmanNode 
   * @param inputMinHeap  the min-on-top heap arrayList whose HuffmanNodes will be merged
   * @return the arrayList that results after merging the first two lowest-frequency HuffmanNodes 
   */
  public static Project2Sort merger(Project2Sort inputMinHeap){
    /**
     * smallest: stores the HuffmanNode with the smallest frequency value 
     */
    HuffmanNode smallest;
    
    /**
     * smallest2: stores the HuffmanNode with the second smallest frequency value 
     */
    HuffmanNode smallest2;
    
    /**
     * finalFrequency: stores the combined frequency of the smallest frequency node and the second smallest frequency node
     */
    Integer finalFrequency;
    
    /**
     * smallestFrequency: stores the frequency value of the HuffmanNode with the smallest frequency
     */
    Integer smallestFrequency;
    
    /**
     * smallest2Frequency: stores the frequency value of the HuffmanNode with the second smallest frequency
     */
    Integer smallest2Frequency;
    smallest = inputMinHeap.getNode(0);
    smallestFrequency = inputMinHeap.getFreq(0);
    inputMinHeap.removeMin();
    smallest2 = inputMinHeap.getNode(0);
    smallest2Frequency = inputMinHeap.getFreq(0);
    inputMinHeap.removeMin();
    finalFrequency = smallestFrequency + smallest2Frequency;
    
    /**
     * merged: a new HuffmanNode that contains the combined frequency of HuffmanNode smallest and HuffmanNode smallest2 (but no character)
     */
    HuffmanNode merged = new HuffmanNode(null, finalFrequency);
    merged.setLeft(smallest);
    merged.setRight(smallest2);
    inputMinHeap.insert(merged);
    return inputMinHeap;
  }
  
  /**
   * Helper method that runs the Huffman encoding algorithm to produce the Huffman tree 
   * @param inputMinHeap  the min-on-top heap array on which the merger helper method will be run, and will be turned into a Huffman tree
   * @return the HuffmanNode that is the root of the Huffman tree and basically contains the entire tree (since the input heap will end up containing 
   *  only one HuffmanNode, and that HuffmanNode will be the root HuffmanNode of the Huffman tree
   */
  public static HuffmanNode huffmanTree(Project2Sort inputMinHeap){
    /**
     * Applies the merger helper method on the inputMinHeap until only one HuffmanNode remains in the inputMinHeap (at which point the Huffman tree will be finished)
     */
    while(inputMinHeap.getSize() > 1){
      merger(inputMinHeap);
    }
    
    /**
     * root: stores the root of the Huffman tree 
     */
    HuffmanNode root = inputMinHeap.getNode(0);
    inputMinHeap.removeMin();
    return root; 
  }
  
  /**
   * Helper method that traverses the Huffman tree to output the character encoding
   * @param root  The root of the Huffman tree at which you start the tree traversal
   * @return the String encoding of a character 
   */
  public static String characterEncoding(HuffmanNode root){
    /**
     * the base case: if the node you are at is a leaf node 
     */
    if(root.getInChar() != null){
      return root.getInChar().toString(); 
    }
    else{
      /**
      * builder: builds the encoding for when you go left  
      */
      StringBuilder builder = new StringBuilder(); 
      /**
       * builder2: builds the encoding for when you go right
       */
      StringBuilder builder2 = new StringBuilder();  
      /**
       * leftLeg: stores the code being built when going left
       */
      String leftLeg = characterEncoding(root.getLeft());
      builder.append("0");
      if(root.getLeft().getInChar() != null){
        charEncodingArray.add(new Tuple(root.getLeft().getInChar(), builder.toString()));
      }
      else{ 
        /**
         * Goes through the charEncodingArray and if the character you are at in the tree equals a character in the charEncodingArray,
         *  then you add a "0" to that character's encoding if you go left 
         */
        for (int i = 0; i < charEncodingArray.size(); i = i + 1) {
          for (int i2 = 0; i2 < leftLeg.length(); i2 = i2 + 1) { 
            if (charEncodingArray.get(i).getCharacter().equals(leftLeg.charAt(i2))) {
              charEncodingArray.get(i).setCharCode("0" + charEncodingArray.get(i).getCharCode());
            }
          }
        }
      }
      /**
       * rightLeg: stores the code being built when going right 
       */
      String rightLeg = characterEncoding(root.getRight());
      builder2.append("1");
      if(root.getRight().getInChar() != null){
        charEncodingArray.add(new Tuple(root.getRight().getInChar(), builder2.toString()));
      }
      else{
        /**
         * Goes through the charEncodingArray and if the character you are at in the tree equals a character in the charEncodingArray,
         *  then you add a "1" to that character's encoding if you go right 
         */
        for (int i = 0; i < charEncodingArray.size(); i = i + 1) {
          for (int i2 = 0; i2 < rightLeg.length(); i2 = i2 + 1) { 
            if (charEncodingArray.get(i).getCharacter().equals(rightLeg.charAt(i2))) {
              charEncodingArray.get(i).setCharCode("1" + charEncodingArray.get(i).getCharCode());
            }
          }
        }
      }
      return rightLeg + leftLeg;
    }
  }
   /**
    * Helper method that scans the input text file, produces the encoded output file, and computes the savings
    * @param inputFileName  the name of the file that you want to input and a produce a Huffman encoding of 
    * @param outputFileName  the name of the output file that contains the Huffman encoding of the input file
    */
   public static void outputFileAndSavings(String inputFileName, String outputFileName) throws IOException{
     /**
      * book: stores the input file as a String
      */
     String book = Files.lines(Paths.get(inputFileName), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
     
     /**
      * bookFinal: stores the final Huffman encoding of the input file in the form of a String 
      */
     String bookFinal = "";
     
      /**
      * bookLength: stores the length of the input file String
      */
     int bookLength = book.length();
     
     /**
      * encoding: stores the encoding of a certain character 
      */
     String encoding;
     /**
      * Goes through the entire book (input file string), and at each character of the book, searches the entire charEncodingArray for the same character.
      *  If the character in the book matches a character in the charEncoding array, put that character's encoding into the final Huffman encoding of the input file
      */
     for(int index = 0; index < book.length(); index = index + 1){
       for(int index2 = 0; index2 < charEncodingArray.size(); index2 = index2 + 1){
         if(((Character)book.charAt(index)).toString().equals(charEncodingArray.get(index2).getCharacter().toString())){
           encoding = charEncodingArray.get(index2).getCharCode();
           bookFinal = bookFinal + encoding;
         }
       }
     }
     /**
      * writer: writes the bookFinal into an output file of outputFileName 
      */
     BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
     writer.write(bookFinal);
     writer.close();
     /**
      * originalBits: stores the number of bits in the original input file 
      */
     int originalBits = bookLength*8;
     
     /**
      * finalBits: stores the number of bits in the output file 
      */
     int finalBits = bookFinal.length();
     
     /**
     * savings: stores the space savings the encoding has achieved
     */
     int savings;
     savings = originalBits - finalBits;
     
     /**
      * triples: creates a new arrayList of triples that stores all of of the characters in the input file, their corresponding frequency, 
      *  and their corresponding encoding 
      */ 
     ArrayList<Triple> triples = new ArrayList<Triple>();
     /**
      * Goes through the entire charEncodingArray, and for each tuple "node" in the charEncodingArray, iterates through the 
      *  hashMap and puts each character and corresponding frequency with its corresponding encoding (from charEncodingArray) 
      *  into the triples arrayList
      */
     for(int index = 0; index < charEncodingArray.size(); index = index + 1){
       /**
        * * mapIterator: an iterator that iterates through the created hashMap
        */
       Iterator mapIterator = map.entrySet().iterator();
       while(mapIterator.hasNext() == true){
         Map.Entry mapStuff = (Map.Entry)mapIterator.next();
         if(charEncodingArray.get(index).getCharacter().equals(mapStuff.getKey())){
           triples.add(new Triple((Character)mapStuff.getKey(), (Integer)mapStuff.getValue(), charEncodingArray.get(index).getCharCode()));
         }
       }
     }
     /**
      * writer2: writes the computed savings and the Huffman encoding of characters in the form of a table of 
      *  character/frequency/encoding triples into an output file called "totalSavingsAndFinalTripleChart.txt"
      */
     BufferedWriter writer2 = new BufferedWriter(new FileWriter("totalSavingsAndFinalTripleChart.txt"));
     writer2.write("Total Savings: " + savings + "\n");
     /**
      * Goes through the triples arrayList and writes the character, frequency, and encoding of each triple "node" into the totalSavingsAndFinalTripleChart.txt file
      */
     for(int b = 0; b < triples.size(); b = b + 1){ 
       writer2.write(triples.get(b).getCharacter() + ": " + triples.get(b).getFrequency() + ": " + triples.get(b).getCharCode() + "\n");
     }
     writer2.close();
   }
   
   /**
   * main method: starts the program by creating a new HuffmanCompressor and inputting the inputFileName I want to test 
   *  and the outputFileName I want as a result
   */
   public static void main(String[] args) throws IOException{
     HuffmanCompressor p2 = new HuffmanCompressor();
     p2.huffmanCoder("p2InputText.txt", "p2OutputFile.txt");
   }   
}