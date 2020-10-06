import java.util.Scanner;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.LinkedList;

public class LexicalScanner {
    public static int prompt_user(LinkedList tokens) {
        // lineNumbers: holds each individual token's line number
        LinkedList lineNumbers = new LinkedList();

        int lines=1;

        Scanner scan = new java.util.Scanner(System.in);
        System.out.println("Enter Ada Code line-by-line inserting a space between ALL tokens.\nEnter 0 when finished.");
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
            // FOR the length of the current line
            //      ...add each token to grand List 'tokens'
            //      ...update List 'lineNumbers' by adding the current line# * # of tokens on the line
            for (int i=0; i< currentLine.length; i++) {
                tokens.add(currentLine[i]);
                lineNumbers.add((char)(lines));
            }
            // End of current WHILE iteration (user presses [ENTER] )
            // 'lines' (counts # of Ada code lines) is incremented
            ++lines;
        }
        return lines;
    }
    // 'category' is passed each token individually
    // each token is cross-referenced with the provided arrays of different lexeme categories
    //          CONSIDER REWORKING...could drastically slow down execution.
    public String category(String token) {
        String token_lex = "NonLexeme", token_id="VAR_ID";
        LinkedList ada_keywords = new LinkedList(Arrays.asList("abort","abs","abstract","accept","access", "aliased","all","and","array","at",
                 "begin","body","case","constant","declare","delay","delta","digits","do",
                 "else","elsif","end","entry","exception","exit","for","function","generic","goto",
                 "if","in","interface","is","limited","loop","mod","new","not","null","of","or",
                 "others","out","overriding","package","pragma","private","procedure","protected",
                 "raise","range","record","rem","rename","requeue","return","reverse","select",
                 "separate","some","subtype","synchronized","tagged","task","terminate","then","type",
                 "until","use","while","with","xor"));
        ListIterator iterate_keywords = ada_keywords.listIterator();
        while (iterate_keywords.hasNext()) {
            if (iterate_keywords.next().equals(token)) {
                token_lex = "Lexeme";
            }
        }

        LinkedList operators = new LinkedList(Arrays.asList( "+","-","*","/","="));
        ListIterator iterate_ops = operators.listIterator();
        while (iterate_ops.hasNext()) {
            if (iterate_ops.next().equals(token)) {
                switch (token) {
                    case "+":
                        token_id="PLUS";
                    case "-":
                        token_id="MINUS";
                    case "*":
                        token_id="TIMES";
                    case "/":
                        token_id="DIVIDE";
                    case "=":
                        token_id="ASSIGN";
                }
            }
        }
        return "" + token_lex + "..." + token_id;
    }
    public static void main(String[] args) {
        LinkedList tokens = new LinkedList();
        int lines = prompt_user(tokens);
        for (int i=0; i<tokens.size(); i++) {
            System.out.print("Tokens[" + i + "]: ");
            System.out.println(tokens.get(i));
        }
        System.out.println("Number of lines: " + (lines-1) +"\n\n");
    }
}


/*
User enters line.
Split the line, save each word into array
Use array to update main output LinkedList


 */
