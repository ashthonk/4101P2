import java.io.*;

class Lambda extends Special {
		
    // TODO: Add any fields needed.
        private Node data;
	
	    // TODO: Add an appropriate constructor.
	public Lambda(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printLambda(t, n, p);
  	}
    @Override
    public Node eval(Environment e){
        return new Closure(data, e);
    }
}
