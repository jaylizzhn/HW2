//
// Generated by JTB 1.3.2
//

package myVisitor;
import syntaxtree.*;

import visitor.*;


import java.time.temporal.ValueRange;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class myGJDF<R,A> extends GJDepthFirst<R,A> implements GJVisitor<R,A> {
    //
    // Auto class visitors--probably don't need to be overridden.
    //
    public IDTypeTable globalTable;


    public R visit(NodeList n, A argu) {
        R _ret=null;
        int _count=0;
        for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
        }
        return _ret;
    }

    public R visit(NodeListOptional n, A argu) {
        if ( n.present() ) {
            R _ret=null;
            int _count=0;
            for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
                e.nextElement().accept(this,argu);
                _count++;
            }
            return _ret;
        }
        else
            return null;
    }

    public R visit(NodeOptional n, A argu) {
        if ( n.present() )
            return n.node.accept(this,argu);
        else
            return null;
    }

    public R visit(NodeSequence n, A argu) {
        R _ret=null;
        int _count=0;
        for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
        }
        return _ret;
    }

    public R visit(NodeToken n, A argu) { return null; }

    //
    // User-generated visitor methods below
    //

    public class Variable{
        public String name;
        public String type;
        public Variable(String n,String t){
            name=n;
            type=t;
        }
    }

    public class Method{
        public String name;
        public ArrayList<Variable> al;
        public String rtnType;


    }

    public class IDTypeTable{
        public HashMap<A,ArrayList<String>> classHasClass;
        public HashMap<A,ArrayList<Method>> classHasMethod;
        public HashMap<A,ArrayList<Variable>> classHasVariable;

        void addClass(A a, String n){
            if(globalTable.classHasClass.containsKey(a)){
                globalTable.classHasClass.get(a).add(n);
            }
            else{
                ArrayList<String> temp=new ArrayList<String>();
                temp.add(n);
                globalTable.classHasClass.put(a,temp);
            }
        }
        void addMethod(A a, Method m){
            if(globalTable.classHasMethod.containsKey(a)){
                globalTable.classHasMethod.get(a).add(m);
            }
            else{
                ArrayList<Method> temp=new ArrayList<Method>();
                temp.add(m);
                globalTable.classHasMethod.put(a,temp);
            }
        }
        void addVariable(A a, String n, String t){
            if(globalTable.classHasVariable.containsKey(a)){
                globalTable.classHasVariable.get(a).add(new Variable(n,t));
            }
            else{
                ArrayList<Variable> temp=new ArrayList<Variable>();
                temp.add(new Variable(n,t));
                globalTable.classHasVariable.put(a,temp);
            }
        }
    }

    public IDTypeTable createTable(){
        IDTypeTable table=new IDTypeTable();
        table.classHasClass=new HashMap<A,ArrayList<String>>();
        table.classHasMethod= new HashMap<A,ArrayList<Method>>();
        table.classHasVariable= new HashMap<A,ArrayList<Variable>>();
        return table;
    }



    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclaration() )*
     * f2 -> <EOF>
     */
    public R visit(Goal n, A argu) {
        R _ret=null;
        globalTable=createTable();
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> "public"
     * f4 -> "static"
     * f5 -> "void"
     * f6 -> "main"
     * f7 -> "("
     * f8 -> "String"
     * f9 -> "["
     * f10 -> "]"
     * f11 -> Identifier()
     * f12 -> ")"
     * f13 -> "{"
     * f14 -> ( VarDeclaration() )*
     * f15 -> ( Statement() )*
     * f16 -> "}"
     * f17 -> "}"
     */
    public R visit(MainClass n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        n.f8.accept(this, argu);
        n.f9.accept(this, argu);
        n.f10.accept(this, argu);
        n.f11.accept(this, argu);
        n.f12.accept(this, argu);
        n.f13.accept(this, argu);
        n.f14.accept(this, argu);
        n.f15.accept(this, argu);
        n.f16.accept(this, argu);
        n.f17.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> ClassDeclaration()
     *       | ClassExtendsDeclaration()
     */
    public R visit(TypeDeclaration n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( VarDeclaration() )*
     * f4 -> ( MethodDeclaration() )*
     * f5 -> "}"
     */
    @SuppressWarnings("unchecked")
    public R visit(ClassDeclaration n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        String f1=n.f1.accept(this, argu).toString();

        globalTable.addClass(argu,f1);
        argu=(A)f1;
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "extends"
     * f3 -> Identifier()
     * f4 -> "{"
     * f5 -> ( VarDeclaration() )*
     * f6 -> ( MethodDeclaration() )*
     * f7 -> "}"
     */
    public R visit(ClassExtendsDeclaration n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> ";"
     */
    @SuppressWarnings("unchecked")
    public R visit(VarDeclaration n, A argu) {
        R _ret=null;

        String f0=n.f0.accept(this, argu).toString();

        String f1=n.f1.accept(this, argu).toString();
        globalTable.addVariable(argu,f1,f0);
        n.f2.accept(this, argu);

        Variable v=new Variable(f1,f0);
        return (R)v;
    }

    /**
     * f0 -> "public"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( FormalParameterList() )?
     * f5 -> ")"
     * f6 -> "{"
     * f7 -> ( VarDeclaration() )*
     * f8 -> ( Statement() )*
     * f9 -> "return"
     * f10 -> Expression()
     * f11 -> ";"
     * f12 -> "}"
     */
    @SuppressWarnings("unchecked")
    public R visit(MethodDeclaration n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        String rtn=n.f1.accept(this, argu).toString();
        String f2=n.f2.accept(this, argu).toString();

        Method m=new Method();
        m.rtnType=rtn;
        m.al=new ArrayList<Variable>();
        m.name=f2;

        n.f3.accept(this, argu);
        n.f4.accept(this, (A)m);


        globalTable.addMethod(argu,m);

        argu=(A)f2;

        if(globalTable.classHasVariable.get(argu)==null) {
            globalTable.classHasVariable.put(argu,new ArrayList<Variable>());
        }
        globalTable.classHasVariable.get(argu).addAll(m.al);

        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        n.f8.accept(this, argu);
        n.f9.accept(this, argu);
        n.f10.accept(this, argu);
        n.f11.accept(this, argu);
        n.f12.accept(this, argu);
        return _ret;
    }

    @SuppressWarnings("unchecked")
    public R visit(FormalParameterList n, A argu) {
        //R _ret=null;
        // Method m=(Method)argu;

        //ArrayList<Variable> al=new ArrayList<Variable>();
        n.f0.accept(this, argu);

        n.f1.accept(this, argu);

        return (R)argu;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     */


    @SuppressWarnings("unchecked")
    public R visit(FormalParameter n, A argu) {
        String type=n.f0.accept(this, argu).toString();
        String name=n.f1.accept(this, argu).toString();

        Variable v=null;

        if(type==null || name==null)
            return null;
        v = new Variable(name,type);

        Method m=(Method)argu;

        if(m!=null)
            m.al.add(v);
        return (R)v;
    }

    /**
     * f0 -> ","
     * f1 -> FormalParameter()
     */
    @SuppressWarnings("unchecked")
    public R visit(FormalParameterRest n, A argu) {
        //R _ret=null;
        Method m=(Method) argu;
        n.f0.accept(this, argu);

        if( n.f1.accept(this, argu)!=null){
            Variable v=(Variable)n.f1.accept(this,argu);

            m.al.add(v);
        }

        return null;
        //return _ret;
    }

    /**
     * f0 -> ArrayType()
     *       | BooleanType()
     *       | IntegerType()
     *       | Identifier()
     */
    public R visit(Type n, A argu) {

        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "int"
     * f1 -> "["
     * f2 -> "]"
     */
    @SuppressWarnings("unchecked")
    public R visit(ArrayType n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"int[]";
    }

    /**
     * f0 -> "boolean"
     */
    @SuppressWarnings("unchecked")
    public R visit(BooleanType n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> "int"
     */
    @SuppressWarnings("unchecked")
    public R visit(IntegerType n, A argu) {

        n.f0.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> Block()
     *       | AssignmentStatement()
     *       | ArrayAssignmentStatement()
     *       | IfStatement()
     *       | WhileStatement()
     *       | PrintStatement()
     */
    public R visit(Statement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "{"
     * f1 -> ( Statement() )*
     * f2 -> "}"
     */
    public R visit(Block n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Identifier()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> ";"
     */
    public R visit(AssignmentStatement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Identifier()
     * f1 -> "["
     * f2 -> Expression()
     * f3 -> "]"
     * f4 -> "="
     * f5 -> Expression()
     * f6 -> ";"
     */
    public R visit(ArrayAssignmentStatement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "if"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     * f5 -> "else"
     * f6 -> Statement()
     */
    public R visit(IfStatement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "while"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     */
    public R visit(WhileStatement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "System.out.println"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> ";"
     */
    public R visit(PrintStatement n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> AndExpression()
     *       | CompareExpression()
     *       | PlusExpression()
     *       | MinusExpression()
     *       | TimesExpression()
     *       | ArrayLookup()
     *       | ArrayLength()
     *       | MessageSend()
     *       | PrimaryExpression()
     */
    public R visit(Expression n, A argu) {

        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "&&"
     * f2 -> PrimaryExpression()
     */
    @SuppressWarnings("unchecked")
    public R visit(AndExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "<"
     * f2 -> PrimaryExpression()
     */
    @SuppressWarnings("unchecked")
    public R visit(CompareExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "+"
     * f2 -> PrimaryExpression()
     */
    @SuppressWarnings("unchecked")
    public R visit(PlusExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "-"
     * f2 -> PrimaryExpression()
     */
    @SuppressWarnings("unchecked")
    public R visit(MinusExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "*"
     * f2 -> PrimaryExpression()
     */
    @SuppressWarnings("unchecked")
    public R visit(TimesExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "["
     * f2 -> PrimaryExpression()
     * f3 -> "]"
     */
    @SuppressWarnings("unchecked")
    public R visit(ArrayLookup n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> "length"
     */
    @SuppressWarnings("unchecked")
    public R visit(ArrayLength n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( ExpressionList() )?
     * f5 -> ")"
     */
    public R visit(MessageSend n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);

        n.f4.accept(this, null);

        n.f5.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Expression()
     * f1 -> ( ExpressionRest() )*
     */
    @SuppressWarnings("unchecked")
    public R visit(ExpressionList n, A argu) {

        n.f0.accept(this, argu);

        n.f1.accept(this, argu);

        return null;
    }

    /**
     * f0 -> ","
     * f1 -> Expression()
     */
    @SuppressWarnings("unchecked")
    public R visit(ExpressionRest n, A argu) {
        // R _ret=null;
        n.f0.accept(this,argu);

        n.f1.accept(this, argu);

        return null;

    }

    /**
     * f0 -> IntegerLiteral()
     *       | TrueLiteral()
     *       | FalseLiteral()
     *       | Identifier()
     *       | ThisExpression()
     *       | ArrayAllocationExpression()
     *       | AllocationExpression()
     *       | NotExpression()
     *       | BracketExpression()
     */
    public R visit(PrimaryExpression n, A argu) {

        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    @SuppressWarnings("unchecked")
    public R visit(IntegerLiteral n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return (R)"int";
    }

    /**
     * f0 -> "true"
     */
    @SuppressWarnings("unchecked")
    public R visit(TrueLiteral n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> "false"
     */
    @SuppressWarnings("unchecked")
    public R visit(FalseLiteral n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    @SuppressWarnings("unchecked")
    public R visit(Identifier n, A argu) {

        return (R)n.f0.toString();

    }



    /**
     * f0 -> "this"
     */
    @SuppressWarnings("unchecked")
    public R visit(ThisExpression n, A argu) {

        n.f0.accept(this, argu);
        return (R)"this";
    }

    /**
     * f0 -> "new"
     * f1 -> "int"
     * f2 -> "["
     * f3 -> Expression()
     * f4 -> "]"
     */
    @SuppressWarnings("unchecked")
    public R visit(ArrayAllocationExpression n, A argu) {


        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return (R)"int[]";
    }

    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> ")"
     */
    @SuppressWarnings("unchecked")
    public R visit(AllocationExpression n, A argu) {
        R _ret=null;

        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);

        return null;
    }

    /**
     * f0 -> "!"
     * f1 -> Expression()
     */
    @SuppressWarnings("unchecked")
    public R visit(NotExpression n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return (R)"boolean";
    }

    /**
     * f0 -> "("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public R visit(BracketExpression n, A argu) {

        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return n.f1.accept(this,argu);
    }

}