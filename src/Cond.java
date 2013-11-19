import java.io.*;

class Cond extends Special {
 
    // TODO: Add any fields needed.
    private Node data;
 
    // TODO: Add an appropriate constructor.
	public Cond(Node t){data = t;}
    void print(Node t, int n, boolean p) { 
    	Printer.printCond(t, n, p);
    }
    @Override
    public Node eval(Environment e){ 
        BooleanLit b;
        Node currentclause = data.getCdr();
        while(!currentclause.getCdr().isNull()){
           b = (BooleanLit)currentclause.getCar().getCar().eval(e); 
           //System.out.println(b.getBoolean());
           if(b.getBoolean()){
               return currentclause.getCar().getCdr().getCar().eval(e);
           }
           else {; currentclause = currentclause.getCdr();}
           }

        if(currentclause.getCar().getCar().getName().equals("else")) { return currentclause.getCar().getCdr().getCar().eval(e);}
        else{
            b = (BooleanLit)currentclause.getCar().getCar().eval(e); 
            if(b.getBoolean()) return currentclause.getCar().getCdr().eval(e);
                    }
        return null;}
    }
