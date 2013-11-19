import java.io.*;

class If extends Special {
 

    private Node data;

	public If(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printIf(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        BooleanLit b = (BooleanLit) data.getCdr().getCar().eval(e);
        if(b.getBoolean() == false){
            return data.getCdr().getCdr().getCdr().getCar().eval(e);
          }
        else
            return data.getCdr().getCdr().getCar().eval(e);
    }
}
