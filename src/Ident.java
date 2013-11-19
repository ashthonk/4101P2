import java.io.*;
class Ident extends Node {
  private String name;

  public Ident(String n) { name = n; }

  public void print(int n) {
  	Printer.printIdent(n, name);
    
  }
  
  public boolean isSymbol() { return true; }
  public String getSymbol(){return name;}
  public String getName(){return name;}
  @Override
  public Node eval(Environment e){
      
      //e.print(0);
      Node holder = e.lookup(this);
      //System.out.println(holder.getCar());
      if(holder == null){return null;}
      else if (holder.isProcedure()){
      //    System.out.println(this);
          return this.eval(e);
      }
      else return holder;
  }
  
}
