/**
 * Class the stores the Huffman encoding of characters in the form of character/frequency/encoding triples 
 *  It is like a node that contains the a certain character, that character's frequency, and that character's encoding 
 * @author  Esther Shin 
 */
public class Triple{
  
  /**
   * the character stored in each triple "node"
   */
  private Character character;
  
  /**
   * the character encoding of a character stored in the same triple "node"
   */
  private String charCode;
  
  /**
   * the frequency of a character stored in the same triple "node"
   */
  private Integer frequency;
  
  /**
   * the constructor
   * @param character  the character to be stored in the triple "node"
   * @param frequency  the frequency of occurrences of the character stored in the same triple "node"
   * @param charCode  the encoding of the character stored in the same triple "node"
   */
  public Triple(Character character, Integer frequency, String charCode){
    this.character = character;
    this.charCode = charCode;
    this.frequency = frequency;
  }
  
  /**
   * Sets the character to be stored in the triple "node" 
   * @param currrentChar  the character that is to be stored in the triple "node"
   */
  public void setCharacter(Character currentChar){
    this.character = character;
  }
  
  /**
   * Method that returns the character stored in a triple "node"
   * @return the character stored in a triple "node" 
   */
  public Character getCharacter(){
    return character;
  }
  
  /**
   * Sets the encoding of a character that is to be stored in a triple "node"
   * @param charCode  the encoding (of a character) to be stored in a triple "node"
   */
  public void setCharCode(String charCode){
    this.charCode = charCode;
  }
  
  /**
   * Method that returns the encoding stored in a triple "node"
   * @return the encoding (of a character) stored in a triple "node"
   */
  public String getCharCode(){
    return charCode;
  }
  
  /**
   * Sets the frequency of occurrences of a character to be stored in a triple "node"
   * @param frequency  the frequency of times a character (in a triple "node") occurs
   */
  public void setFrequency(Integer frequency){
    this.frequency = frequency;
  }
  
  /**
   * Method that returns the frequency of occurrences of a character stored in a triple "node"
   * @return the frequency number of occurrences of a character stored in a triple "node"
   */
  public Integer getFrequency(){
    return frequency;
  }
}