import java.io.*;

class Let extends Special {
 
    // TODO: Add any fields needed.
	private Node data;
 
    // TODO: Add an appropriate constructor.
	public Let(Node t){data = t;}
    void print(Node t, int n, boolean p) {
    	  Printer.printLet(t, n, p);
    }
    @Override
    public Node eval(Environment e){
        Environment tempenv = new Environment(e);
        Node travnode = data.getCdr();
        //System.out.println("Travnode: " + travnode);
        Node varassign = travnode.getCar(); //((v1 a1)(v2 a2)...)
        //System.out.println("Varassign: ");
        //Apply Args
        while(!varassign.isNull()){
            //System.out.println("~~~~~~~!~!~");
            //ystem.out.println(varassign.getCar().getCar() + " " +varassign.getCar().getCdr().eval(tempenv) );
            tempenv.define(varassign.getCar().getCar(), varassign.getCar().getCdr().eval(tempenv));
            varassign = varassign.getCdr();
        }
        return travnode.getCdr().eval(tempenv);
    }
}
