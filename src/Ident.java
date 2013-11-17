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
      
      
      Node holder = e.lookup(this);
      System.out.println("Ident called. Looking up ident: "+ this.getName());
      System.out.println(holder);
      System.out.print("Ident was called, and " + holder + " was ");
      if (e.lookup(this) == null) System.out.println("not found.");
      else System.out.println("found!");
      if(e.lookup(this) == null){return null;}
      else { return e.lookup(this);}
  }
}
