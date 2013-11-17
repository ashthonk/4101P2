import java.io.*;

class Regular extends Special {
 
    // TODO: Add any fields needed.
    private Node data;
    
    // TODO: Add an appropriate constructor.

    public Regular(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printRegular(t, n, p);
    }
    
    @Override
    public Node eval(Environment e){
        System.out.println("In Regular, checking some stuff");
        if(data.getCar().isSymbol()){
            System.out.println("We're about to get a BuiltIn");
            System.out.println("Bout to get " + data.getCar().getName() + " of type " + data.getCar().getClass() + " and it is a procedure? " + data.getCar().isProcedure());
            return null;
        }
        //Node etest = e.lookup(data.getCar());
      /*  if(data.getCar().getClass().equals(new Ident("test").getClass())  && e.lookup(data.getCar()).isProcedure()){
            System.out.println("Going down");
	    Node procWithArgs = data.getCar().apply(data.getCdr().eval(e));
	    return procWithArgs.eval(e);
        }*/
        else{
        System.out.println(data.getCar());
    System.out.println("In Regular~ Call wasn't a cons. Evaluating Car and Cdr..this will have to be fixed!");    
    //return data.getCar().eval(e);
    return new Cons(data.getCar().eval(e),data.getCdr().eval(e));
        }
    }
}
