/**
 * Class that is like a node and that stores a character and its corresponding encoding  
 * @author  Esther Shin 
 */
public class Tuple{
  
  /**
   * the character stored in each tuple "node"
   */ 
  private Character character;
  
  /**
   * the encoding of a character stored in a tuple "node"
   */
  private String charCode;
  
  /**
   * the constructor
   * @param character  the character to be stored in the tuple "node"
   * @param charCode  the encoding of the character stored in the same tuple "node"
   */
  public Tuple(Character character, String charCode){
    this.character = character;
    this.charCode = charCode;
  }
  
  /**
   * Sets the character to be stored in the tuple "node" 
   * @param currrentChar  the character that is to be stored in the tuple "node"
   */
  public void setCharacter(Character currentChar){
    this.character = character;
  }
  
  /**
   * Method that returns the character stored in a tuple "node"
   * @return the character stored in a tuple "node" 
   */
  public Character getCharacter(){
    return character;
  }
  
  /**
   * Sets the encoding of a character that is to be stored in a tuple "node"
   * @param charCode  the encoding (of a character) to be stored in a tuple "node"
   */
  public void setCharCode(String charCode){
    this.charCode = charCode;
  }
  
  /**
   * Method that returns the encoding stored in a tuple "node"
   * @return the encoding (of a character) stored in a tuple "node"
   */
  public String getCharCode(){
    return charCode;
  }
 
}