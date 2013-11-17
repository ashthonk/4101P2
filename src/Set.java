import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.

        private Node data;
    // TODO: Add an appropriate constructor.
	public Set(Node t){data = t;}
	
    void print(Node t, int n, boolean p) {
    	Printer.printSet(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        Node e1 = data.getCdr().getCar().eval(e);
        Node e2;
        System.out.println("Set calling lookup");
        e2 = e.lookup(data.getCar());
        if(!e2.isNull()){
            e.assign(e2, e1);
            return e1;
        }
        else
        {System.out.println("Error"); return null;}
        
    }
}
