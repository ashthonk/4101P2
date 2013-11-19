// Main.java -- the main program
import java.io.*;
import java.util.*;
public class Main {
    // Array of token names used for debugging the scanner
    private static final String TokenName[] = {
	"QUOTE",			// '
	"LPAREN",			// (
	"RPAREN",			// )
	"DOT",				// .
	"TRUE",				// #t
	"FALSE",			// #f
	"INT",				// integer constant
	"STRING",			// string constant
	"IDENT"				// identifier
    };
    private static Environment e;
    public static Environment top;
    public static void main (String argv[]) {
        Environment e = new Environment();
//        top.define(e, top);
        
        BuiltIn id[] = {
            new BuiltIn(new Ident("b+")),
            new BuiltIn(new Ident("b-")),
            new BuiltIn(new Ident("b*")),
            new BuiltIn(new Ident("b/")),
            new BuiltIn(new Ident("b=")),
            new BuiltIn(new Ident("b<")),
            new BuiltIn(new Ident("b>")),
            new BuiltIn(new Ident("+")),
            new BuiltIn(new Ident("-")),
            new BuiltIn(new Ident("*")),
            new BuiltIn(new Ident("/")),
            new BuiltIn(new Ident("=")),
            new BuiltIn(new Ident("<")),
            new BuiltIn(new Ident(">")),
            new BuiltIn(new Ident("number?")),
            new BuiltIn(new Ident("symbol?")),
            new BuiltIn(new Ident("pair?")),
            new BuiltIn(new Ident("null?")),
            new BuiltIn(new Ident("procedure?")),
            new BuiltIn(new Ident("car")),
            new BuiltIn(new Ident("cdr")),
            new BuiltIn(new Ident("cons")),
            new BuiltIn(new Ident("set-car!")),
            new BuiltIn(new Ident("set-cdr!")),
            new BuiltIn(new Ident("eq?")),
            new BuiltIn(new Ident("read")),
            new BuiltIn(new Ident("write")),
            new BuiltIn(new Ident("display")),
            new BuiltIn(new Ident("newline")),
            new BuiltIn(new Ident("eval")),
            new BuiltIn(new Ident("apply")),
            new BuiltIn(new Ident("interaction-environment"))
             
        };
        for(int i = 0; i<id.length; i++){
            e.define(id[i].getSymbol(), id[i]);
        }
        top = new Environment(e);
    	System.out.print("Scheme 4101>");
	// create scanner that reads from standard input
	Scanner scanner = new Scanner(System.in);


	if (argv.length > 1) {
	    System.err.println("Usage: java Main [-d]");
	    System.exit(1);
	}
	
	// if commandline option -d is provided, debug the scanner
	if (argv.length == 1 && argv[0].equals("-d")) {
	    // debug scanner
	    Token tok = scanner.getNextToken();
	    while (tok != null) {
		int tt = tok.getType();
		System.out.print(TokenName[tt]);
		if (tt == Token.INT)
		    System.out.println(", intVal = " + tok.getIntVal());
		else if (tt == Token.STRING)
		    System.out.println(", strVal = " + tok.getStrVal());
		else if (tt == Token.IDENT)
		    System.out.println(", name = " + tok.getName());
		else
		    System.out.println();

		tok = scanner.getNextToken();
	    }
	    System.exit(0);
	}
	
	// Create parser
	Parser parser = new Parser(scanner);
	Node root;
  
                
        
        
	//e = new Environment();
        root = parser.parseExp();
	// Parse and pretty-print each input expression
	while (root != null) {
	    //root.print(0);
            Node rootEval = root.eval(top);
//            System.out.println("rootEval is " + rootEval.getClass());
            if(rootEval==null){
                System.out.println("No values returned");
            }
            else if(rootEval.isNull()){
                System.out.println("No values returned");
            }
            else 
                rootEval.print(0, false);
            
            System.out.print("Scheme 4101>");
	    root = parser.parseExp();
	}
	System.exit(0);
    }
    	
}
