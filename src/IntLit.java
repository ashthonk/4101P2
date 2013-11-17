import java.io.*;
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  public void print(int n) {
  	Printer.printIntLit(n, intVal);
  }
  @Override
  public int getInt(){
      return intVal;
  }
  public boolean isNumber() { return true; }

  @Override 
  public Node eval(Environment e){
      System.out.println("IntLit called. Returning itself");
      return this;
  }
}
