// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

class BuiltIn extends Node 
{
    private Node symbol;
    public BuiltIn(Node s)		{ symbol = s;}
    private Node args;
    private op operator;
    public Node getSymbol()	
    { return symbol; }
    public Node evalArgsOne = null;
    public Node evalArgsTwo = null;
    public boolean isProcedure()	{return true;}

    public void print(int n) 
    {
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println("#{Built-in Procedure");
	symbol.print(n+3);
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println('}');
    }
    public static enum op{
        BGT, BSTAR, BEQ, BDIV, BLT,ISNUMBER, ISNULL, BPLUS, BMINUS, ISPAIR, ISSYMBOL, ISPROCEDURE, CAR, CDR, CONS, SETCAR, SETCDR, EQ, READ, WRITE, DISPLAY, NEWLINE, EVAL, APPLY, IE
                
    };
    //TODO: this
    public Node eval (Environment e) 
    { 
        evalArgsOne = null;
        try{
        evalArgsOne = this.args.getCar().eval(e);
        
        switch(operator){
            case ISNUMBER:
                     if(evalArgsOne == null) return new BooleanLit(false);
                     if(evalArgsOne.eval(e).isNumber()) return new BooleanLit(true); else return new BooleanLit(false);
            case ISNULL:
                     if(evalArgsOne == null) return new BooleanLit(true);
                     if(evalArgsOne.eval(e).isNull()) return new BooleanLit(true); else return new BooleanLit(false);
            case BPLUS:
                     int a = ((IntLit) evalArgsOne.eval(e)).getInt();
                     int b = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                     return new IntLit(a + b);
            case BMINUS:
                     int c = ((IntLit) evalArgsOne.eval(e)).getInt();
                     int d = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                     return new IntLit(c - d);
            case BSTAR:
                int g = ((IntLit) evalArgsOne.eval(e)).getInt();
                int f = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                return new IntLit(g * f);
            case BDIV:
                int h = ((IntLit) evalArgsOne.eval(e)).getInt();
                int i = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                if (i != 0) return new IntLit(h/i);
                else {System.out.println("Error: Div By Zero"); return new IntLit(-999);}
            case BLT:
                int j = ((IntLit) evalArgsOne.eval(e)).getInt();
                int k = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                return new BooleanLit(j<k);
            case BGT:
                int ja = ((IntLit) evalArgsOne.eval(e)).getInt();
                int jb = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                return new BooleanLit(ja>jb);
            case BEQ:
                int l = ((IntLit) evalArgsOne.eval(e)).getInt();
                int m = ((IntLit) args.getCdr().getCar().eval(e)).getInt();
                return new BooleanLit(l == m);
                
            case ISPAIR:
                     if(evalArgsOne == null) return new BooleanLit(false);
                     if(evalArgsOne.eval(e).isPair()) return new BooleanLit(true); else return new BooleanLit(false);
            case ISSYMBOL:
                     if(args != null && args.isSymbol()) return new BooleanLit(true);
                     else if (args != null && args.getCar()!= null && args.getCar().isSymbol()) return new BooleanLit(true);
                     else return new BooleanLit(false);
            case ISPROCEDURE:
                     if(evalArgsOne == null) return new BooleanLit(false);
                     if(evalArgsOne.isProcedure()) return new BooleanLit(true);
                     else if(evalArgsOne.eval(e).isProcedure()) return new BooleanLit(true);
                     else return new BooleanLit(false);
            case CAR:
                    if (evalArgsOne!=null)
                    return evalArgsOne.getCar();
                    else return null;
            case CDR:
                    if (evalArgsOne!= null)
                    return evalArgsOne.getCdr();
                    else return null;
            case CONS:
                    if (this.args.getCar() != null && this.args.getCdr().getCar() != null){
                    return new Cons(this.args.getCar().eval(e), this.args.getCdr().getCar().eval(e));}
                    else {
                    System.out.println("Missing Args");
                    return null;
                            }
            case EQ:
                  if (args.getCar().getName().equals(args.getCdr().getCar().getName())) return new BooleanLit(true);
                  else return new BooleanLit(false);
            case SETCAR://!
                  Node argsa = args.eval(e);
                  Node val = argsa.getCdr();
                  Cons li = (Cons)argsa.getCar();                 
                  Cons newone = new Cons(val,li.getCdr());
                  Node id = args.getCar();
                  e.assign(id, newone);
                  return new Nil();           
            case SETCDR://!
                  Node argsb = args.eval(e);
                  Node valb = argsb.getCdr();
                  Cons lib = (Cons)argsb.getCar();                 
                  Cons newtwo = new Cons(valb,lib.getCdr());
                  Node idb = args.getCar();
                  e.assign(idb, newtwo);
                  return new Nil();   
            case EVAL:
                if (args.isPair()){
                  Node fun = args.getCar();
                  Node eval = args.getCdr().eval(e);
                  Environment tempenv = (Environment)eval;
                  fun = fun.getCdr().eval(tempenv);
                  return fun;}
            case READ:
                 Scanner reader = new Scanner(System.in);
                 Parser parse = new Parser(reader);
                 return parse.parseExp();
            case WRITE:
                 args.print(0);
                 return new Nil();
            case NEWLINE:
                System.out.println("");
                return new Nil();
            case DISPLAY:
                  evalArgsOne.print(0, false);
                  return new Nil();
                 
            case IE:
                return Main.top;
            default: return null;
    }
    } catch(NullPointerException exc) {System.out.println("Error! You may have missing variable definitions"); return null;}
        
    }
    
    //TODO: this
    public Node apply(Node args) 
    {
        this.args = args;
        if(this.symbol.getName().equals("number?")){
            this.operator = op.ISNUMBER;
        }
         else if(this.symbol.getName().equals("b+") || this.symbol.getName().equals("+")){
                this.operator = op.BPLUS;
        }
                else if(this.symbol.getName().equals("b-")|| this.symbol.getName().equals("-")){
                this.operator = op.BMINUS;
        }
                else if(this.symbol.getName().equals("b*")|| this.symbol.getName().equals("*")){
                this.operator = op.BSTAR;
        }
                else if(this.symbol.getName().equals("b/")|| this.symbol.getName().equals("/")){
                this.operator = op.BDIV;
        }
                else if(this.symbol.getName().equals("b=")|| this.symbol.getName().equals("=")){
                this.operator = op.BEQ;
        }
                else if(this.symbol.getName().equals("b<")|| this.symbol.getName().equals("<")){
                this.operator = op.BLT;
        }
                else if(this.symbol.getName().equals("b>")|| this.symbol.getName().equals(">")){
                this.operator = op.BGT;
                }
                else if(this.symbol.getName().equals("car")){
                    this.operator = op.CAR;
        }
                else if(this.symbol.getName().equals("cdr")){
                    this.operator = op.CDR;
        }
                else if(this.symbol.getName().equals("cons")){
                    this.operator = op.CONS;
        }
                else if(this.symbol.getName().equals("set-car!")){
                    this.operator = op.SETCAR;
        }
                else if(this.symbol.getName().equals("set-cdr!")){
                    this.operator = op.SETCDR;
        }
                else if(this.symbol.getName().equals("symbol?")){
            this.operator = op.ISSYMBOL;
        }
                else if(this.symbol.getName().equals("null?")){
            this.operator = op.ISNULL;
        }
                else if(this.symbol.getName().equals("pair?")){
            this.operator = op.ISPAIR;
        }
                else if(this.symbol.getName().equals("eq?")){
                    this.operator = op.EQ;
        }
                else if(this.symbol.getName().equals("procedure?")){
                 this.operator = op.ISPROCEDURE;   
            
        }
                else if(this.symbol.getName().equals("read")){
            this.operator = op.READ;
        }
                else if(this.symbol.getName().equals("write")){
            this.operator = op.WRITE;
        }
                else if(this.symbol.getName().equals("display")){
            this.operator = op.DISPLAY;
        }
                else if(this.symbol.getName().equals("newline")){
            this.operator = op.NEWLINE;
        }
                else if(this.symbol.getName().equals("eval")){
            this.operator = op.EVAL;
        }
                else if(this.symbol.getName().equals("apply")){
            this.operator = op.APPLY;
        }
                else if(this.symbol.getName().equals("interaction-environment")){
            this.operator = op.IE;
        }
	return this;
    }
    
}