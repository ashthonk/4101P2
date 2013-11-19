import java.io.*;

class Set extends Special {
        private Node data;
	public Set(Node t){data = t;}
	
    void print(Node t, int n, boolean p) {
    	Printer.printSet(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        Node e0 = data.getCdr().getCdr().getCar();
        e.assign(data.getCdr().getCar(), e0.eval(e));
        return new Nil();       
    }
}
