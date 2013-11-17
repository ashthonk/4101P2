// Closure.java -- the data structure for function closures

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.

// The method apply() takes the environment out of the closure,
// adds a new frame for the function call, defines bindings for the
// parameters with the argument values in the new frame, and evaluates
// the function body.

class Closure extends Node 
{
    private Node fun;		// a lambda expression
    private Environment env;	// the environment in which the function was defined

    public Closure(Node f, Environment e)	{ fun = f;  env = e; }
    public Node getFun()		{ return fun; }
    public Environment getEnv()		{ return env; }
    public boolean isProcedure()	{ return true; }

    public void print(int n) 
    {
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println("#{Procedure");
	fun.print(n+3);
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println('}');
    }

    @Override
    public Node eval(Environment e){
        //DO This!
        return null;//something;
    }
    //TODO: The method apply() should be overwritten only in classes builtin and closure
    public Node apply (Node args) 
    {
        //1) Takes the environment out of the closure,
        Environment takeout = new Environment(env); //May need to re-cons takeout to env
        Node tempfun = fun.getCar();
        Node tempargs = args;
        Node toreturn = null;
        //2) Adds a new frame for the function call, 
        //3) Defines bindings for the parameters with the argument values in the new frame,
        while(!tempfun.isNull() && !tempargs.isNull()){
            takeout.define(tempfun.getCar(), tempargs.getCar());
            tempfun = tempfun.getCdr();
            tempargs = tempargs.getCdr();
        }
        //4) Evaluates the function body.
        
        while(!tempfun.isNull()){
        toreturn = fun.getCdr().getCar().eval(takeout);
        }
	return toreturn;
    }
}
