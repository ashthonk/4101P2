import java.io.*;

class Lambda extends Special {

        private Node data;
	public Lambda(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printLambda(t, n, p);
  	}
    @Override
    public Node eval(Environment e){
        //System.out.println("~~~L");
        Closure c = new Closure(data, e);//Might need to be something else
        return c;
    }
}
