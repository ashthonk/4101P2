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
    public Node eval(Environment e){ //Probably not right
        //Traverse the Cons tree. If we return null, that's false. if we return something, then that's true
        BooleanLit b;
        Node e1;
        Node currentclause;
        while (!data.isNull()){ //Unspecified if this fails
             currentclause = data.getCar();
             if (!data.getCdr().isNull())
                 b = (BooleanLit)currentclause.getCar().eval(e);
               else 
                 if (data.getCar().getCar().getName().equals("else")){ //May have an error. If so, remove one getCar
                    return data.getCar().getCdr().eval(e);                   
                 }
                 else b = (BooleanLit)currentclause.getCar().eval(e);
                                    
             if(b.getBoolean() == false){
                 data = data.getCdr();
                 if(data.isNull())
                     System.out.println("Error! Cond node went all the way down");
             }
             
        }
        return null;
            
    }
}
