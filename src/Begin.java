import java.io.*;
class Begin extends Special {
 
    // TODO: Add any fields needed.

        private Node data;
    // TODO: Add an appropriate constructor.
	public Begin(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printBegin(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        Node e1 = null;
        while(!data.isNull()){
            e1 = data.getCar().eval(e);
            data = data.getCdr();
        }
        return e1;
    }
}
