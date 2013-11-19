import java.io.*;

class Define extends Special {
    
        private Node data;
	public Define(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printDefine(t, n, p);
    	  }
    @Override 
    public Node eval(Environment e){
        try{
          //System.out.println("Ohai");
            Node temp = data.getCdr();
            //System.out.println("Let's see if we can define a lambda");
            //System.out.println(temp.getCar());//X
           // System.out.println(temp.getCdr().getCar());//Lambda Node
            //System.out.println(temp.getCdr().getCar().eval(e).isProcedure());
        if(temp.getCdr().getCar().eval(e).isProcedure()){
            //System.out.println((temp.getCar() == null) + " " + (!temp.getCdr().getCar().isPair()));
           if(temp.getCar() != null && temp.getCdr().getCar().isPair()){
              //System.out.println("Going down!");
                Cons lambda = new Cons(new Ident("lambda"),new Cons(temp.getCdr().getCar(),temp.getCdr().getCdr()));
                Closure c = new Closure(lambda,e);
                //System.out.println("Attempting to define " + temp + " " + temp.getCdr().getCar());
                e.define(temp.getCar(), temp.getCdr());
                //e.print(0);
            }
         }
        else {e.define(temp.getCar(), temp.getCdr().getCar().eval(e));  }
        } catch (NullPointerException q){
            System.out.println("Woah! Null pointer exception in Define!");
        } finally{ return new Nil();}
    }
}
