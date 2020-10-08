import java.util.Scanner;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.LinkedList;

public class LexicalScanner {
	public static LinkedList<String> ada_keywords = new LinkedList<String>(Arrays.asList("abort",
			"abs","abstract","accept","access", "aliased","all","and","array","at",
            "begin","body","case","constant","declare","delay","delta","digits","do",
            "else","elsif","end","entry","exception","exit","for","function","generic","goto",
            "if","in","interface","is","limited","loop","mod","new","not","null","of","or",
            "others","out","overriding","package","pragma","private","procedure","protected",
            "raise","range","record","rem","rename","requeue","return","reverse","select",
            "separate","some","subtype","synchronized","tagged","task","terminate","then","type",
            "until","use","while","with","xor"));
	
	public static LinkedList<String> operators = new LinkedList<String>(Arrays.asList("+","-","*","/","=",":","(",")",";"));
	
	public static final String rule = "(?<=;)|(?=;)|(?<=\\+)|(?=\\+)|(?<=\\-)|(?=\\-)|"
			+ "(?<=\\:)|(?=\\:)|(?<=\\*)|(?=\\*)|(?<=\\/)|(?=\\/)|(?<=\\=)|(?=\\=)|"
			+ "(?<=\\()|(?=\\()|(?<=\\))|(?=\\))";
	
	static void dump(String[] arr, LinkedList<String> tokens) {
        for (String s : arr) {
        	String a = String.format("%s", s);
        	tokens.add(a);
        }
    }
	
    public static int prompt_user(LinkedList<String> tokens) {
        // lineNumbers: holds each individual token's line number

        int lines=0;

        Scanner scan = new java.util.Scanner(System.in);
        System.out.println("Enter Ada Code line-by-line.\nInsert a space between ALL tokens.\nEnter 0 when finished.");
        //                                               ^This^ breaks the WHILE loop
        // Each WHILE iteration is 1 line of Ada code entered
        while (true) {
            String line = scan.nextLine();
            if (line.equals("0")) {
                break;
            }
            // currentLine: create new temporary array on each iteration to store the contents
            // of the current line of Ada code. Split by each [SPACE] character.
            String[] currentLine = line.split(" ");
            
            // theLine: create new array on each element of currentLine array
            // split by new rule
            for (int i = 0; i < currentLine.length; i++) {
            	String[] theLine = currentLine[i].split(rule);
            	dump(theLine, tokens);
            }
            
            //}
            // End of current WHILE iteration (user presses [ENTER] )
            // 'lines' (counts # of Ada code lines) is incremented
            ++lines;
        }
        scan.close();
        return lines;
    }
    // 'category' is passed each token individually
    // each token is cross-referenced with the provided arrays of different lexeme categories
    //          CONSIDER REWORKING...could drastically slow down execution.
    public static String category(String token) {
        String token_lex = "NonLexeme", token_id="VAR_ID";

        // Test for Keywords
        
        ListIterator<String> iterate_keywords = ada_keywords.listIterator();
        while (iterate_keywords.hasNext()) {
            if (iterate_keywords.next().equals(token)) {
                token_lex = "Lexeme";
                token_id = "KEYWORD";
            }
        }

        // Test for Operators
        
        ListIterator<String> iterate_ops = operators.listIterator();
        while (iterate_ops.hasNext()) {
            if (iterate_ops.next().equals(token)) {
                switch (token) {
                    case "+":
                        token_id="PLUS";
                        break;
                    case "-":
                        token_id="MINUS";
                        break;
                    case "*":
                        token_id="TIMES";
                        break;
                    case "/":
                        token_id="DIVIDE";
                        break;
                    case "=":
                        token_id="ASSIGN";
                        break;
                    case ":":
                        token_id="COLON";
                        break;
                    case "(":
                    	token_id="PARENTHESIS";
                    	break;
                    case ")":
                    	token_id="PARENTHESIS";
                    	break;
                    case ";":
                    	token_id="SEMICOLON";
                    	break;
                }
            }
        }
        // Test for Numeric Literals
        for (char c : token.toCharArray()) {
            if (Character.isDigit(c)) {
                token_id="NUM_LITERAL";
            }
        }
        return "" + token_lex + "..." + token_id;
    }
    public static void main(String[] args) {
        LinkedList<String> tokens = new LinkedList<String>();
        int lines = prompt_user(tokens);
        for (int i=0; i<tokens.size(); i++) {
            System.out.print("Token[" + i + "]: ");
            System.out.print(tokens.get(i));
            System.out.println("\t" + category((String)tokens.get(i)) + "");
        }
        System.out.println("Number of lines: " + (lines) +"\n\n");
    }
}
