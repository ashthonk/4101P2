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
  @Override
  public String getName(){
      return Integer.toString(intVal);
  }
  public boolean isNumber() { return true; }

  @Override 
  public Node eval(Environment e){
      return this;
  }
}
