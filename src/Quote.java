import java.io.*;

class Quote extends Special {
 
    // TODO: Add any fields needed.
    private Node data;
 
    // TODO: Add an appropriate constructor.
	
	public Quote(Node t){data = t;}

    void print(Node t, int n, boolean p) {
    	Printer.printQuote(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        return data;
    }
}
    

