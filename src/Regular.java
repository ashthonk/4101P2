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
        Node car = data.getCar();
        Node cdr = data.getCdr();

        Node temp  = e.lookup(car);
           if (car.isSymbol() && temp != null && temp.isProcedure()){ 
                   return temp.apply(cdr).eval(e);
             } else if(car.isProcedure()){
               return car.apply(cdr);
                   }
             else{   
               //System.out.println("About to call lambda");
                 Node careval = car.eval(e);
                //System.out.println("Lambda finished: " + careval.getInt());
                 Node cdreval = cdr;
                 //System.out.println("Did I make it here?" + cdreval.getInt());
                 if (careval != null && careval.isNull()) {;return cdreval;}
                 else if (cdreval != null && cdreval.isNull()){
                     return careval;}
                 else{
                    return new Cons(careval, cdreval).eval(e);
                    
      }
        
    }
    
}
}

