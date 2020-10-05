import java.util.*
import java.util.LinkedList;

public class Scanner {

  // operations to test
  private static enum Type {
    add, sub, mult, div, remain, op
      // CONSIDER: removing REMAIN ... to make it different and easier
  }
  
  // Token Class: holds value/type of token
  private static class Token<T_Val, T_Type> {
    private final T_Val value;
    private final T_Type type;
    
  public Scanner(T_Val value, T_Type type) {
    this.type = type;
    this.value = value;
  }
    
  // toString Override
  @Override
  public String toString() {
    return "Token Value: " + this.value + " ... Token Type: " + this.type;
  }
}

// getOperand: takes in a string and index and returns operand at that index
private static String getOperand(String op, int index) {
  for (int i=index; i<op.length(); {
    if (Character.isLetterOrDigit(op.charAt(i))) {
      i++;
    }
    else {
    return op.substring(index, i);
  }
  return op.substring(index, i)
}
  public static void main(String[] args) {
    // Save the Ada code as a String
    // Use getOperand to parse through this String & print tokens
    String ADA_CODE =
      "procedure Main is" +
      "begin" +
      "a : Integer := 2;" +
      "b : Integer := 3;" +
      "s : String := \"The sum of \";" +
      "Put_Line (s & a & \" and \" & b & \" is \" & (a+b) );" +
      "end";
    getOperand(ADA_CODE, 0);

  }
}
