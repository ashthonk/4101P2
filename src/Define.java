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
        System.out.println("_.-~~~~DEFINE~~~~~-._");
        System.out.println("Define is about to call define and eval");
        if(data.getCdr().getCdr().eval(e).isProcedure())
        {
            System.out.println("Lambda expression tiem!");
            return new Nil();
        }
        else e.define(data.getCdr().getCar(), data.getCdr().getCdr().eval(e));  
        System.out.println("Define has been completed");
        } catch (NullPointerException q){
            System.out.println("Woah! Null pointer exception in Define!");
        } finally{
        return new Nil();}
    }
}
