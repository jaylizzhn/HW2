package myVisitor;

import syntaxtree.*;
import visitor.DepthFirstVisitor;
import visitor.GJDepthFirst;
import visitor.Visitor;

import myVisitor.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class PrettyPrinter<R,A> extends myGJDF<R,A> {







    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclaration() )*
     * f2 -> <EOF>
     */
    @Override
    public R visit(Goal n, A argu) { //Done


        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);

        System.out.println("Program type checked successfully");
        return null;
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
    @Override
    @SuppressWarnings("unchecked")
    public R visit(MethodDeclaration n, A argu) {

        n.f0.accept(this,argu);

        String f1=n.f1.accept(this,argu).toString();

        argu=(A)n.f2.accept(this,argu).toString();

        n.f3.accept(this,argu);
        n.f4.accept(this,null);
        n.f5.accept(this,argu);
        n.f6.accept(this,argu);
        n.f7.accept(this,argu);
        n.f8.accept(this,argu);
        n.f9.accept(this,argu);



        String f10=n.f10.accept(this,argu).toString();
        String typeF10=idToType(f10,argu);

        if(!f1.equals(typeF10)&&!f1.equals(f10)){

            System.out.println("Type error");
            System.exit(1);
        }
        n.f11.accept(this,argu);
        n.f12.accept(this,argu);

        return null;

    }



    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> ")"
     */

    @Override
    @SuppressWarnings("unchecked")
    public R visit(ClassDeclaration n, A argu) {
        R _ret=null;
        n.f0.accept(this, argu);
        String f1=n.f1.accept(this, argu).toString();

        argu=(A)f1;
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        return (R)f1;
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
     * f0 -> Identifier()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> ";"
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(AssignmentStatement n, A argu) {

        String f0=n.f0.accept(this, argu).toString();
        String typeF0=f0;
        if(!f0.equals("int")&&!f0.equals("boolean")&&!f0.equals("int[]"))
            typeF0=idToType(f0,argu);

        String f2=n.f2.accept(this, argu).toString();
        String typeF2=f2;




        if(f2.equals("this")){
            for(A key: globalTable.classHasMethod.keySet() ){
                for(int i=0;i<globalTable.classHasMethod.get(key).size();i++){
                    if(globalTable.classHasMethod.get(key).get(i).name.equals(argu)){
                        f2=key.toString();
                    }
                }
            }
        }
        if(!f2.equals("int")&&!f2.equals("boolean")&&!f2.equals("int[]"))
            typeF2=idToType(f2,argu);


        if(typeF0.equals(typeF2)||typeF0.equals(f2))
            return (R)typeF0;
        System.out.println("Type error");
        System.exit(1);

        return null;
    }





    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> ")"
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(AllocationExpression n, A argu) { //Not DonE!!!!!!!!!!!!!!!!!!!!!!!
        super.visit(n, argu);
        String id=(String)n.f1.accept(this,argu);

        return (R)id;
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "&"
     * f2 -> PrimaryExpression()
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(AndExpression n, A argu) { //Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);
        String f2=(String)n.f2.accept(this,argu);

        String typeF0=idToType(f0,argu);
        String typeF2=idToType(f2,argu);

        boolean flag1=false,flag2=false;
        if("boolean".equals(f0)||"boolean".equals(typeF0))
            flag1=true;
        if("boolean".equals(f2)||"boolean".equals(typeF2))
            flag2=true;
        if(!flag1 || !flag2 ){
            System.out.println("Type error");
            System.exit(1);
        }
        return (R)"boolean";
    }


    /**
     * f0 -> PrimaryExpression()
     * f1 -> "<"
     * f2 -> PrimaryExpression()
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(CompareExpression n, A argu) {//Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);
        String f2=(String)n.f2.accept(this,argu);


        boolean flag1,flag2;
        flag1=f0.equals("int")||idToInt(f0,argu);
        flag2=f2.equals("int")||idToInt(f2,argu);

        if(flag1&&flag2)
            return (R)"boolean";


        System.out.println("Type error");
        System.exit(1);
        return null;

    }


    /**
     * f0 -> PrimaryExpression()
     * f1 -> "+"
     * f2 -> PrimaryExpression()
     */

    public boolean idToInt(String s, A argu){
        String rtn=idToType(s,argu);
        if ("int".equals(rtn))
            return true;
        else return false;
    }

    public boolean idToIntArr(String s, A argu){
        String rtn=idToType(s,argu);
        if ("int[]".equals(rtn))
            return true;
        else return false;
    }

    @SuppressWarnings("unchecked")
    public String idToType(String s,A argu){
        ArrayList<Variable> al=globalTable.classHasVariable.get(argu);

        if(al!=null){
            for (Variable variable : al) {

                if (variable.name.equals(s)) {
                    return variable.type.toString();
                }
            }
        }


        ArrayList<String> allClass=globalTable.classHasClass.get((A)"main");
        if(allClass.contains(s.toString()))
            return s;


        for(A key : globalTable.classHasMethod.keySet()){

            for(int j=0;j<globalTable.classHasMethod.get(key).size();j++){

                if(globalTable.classHasMethod.get(key).get(j).name.equals(argu)){
                    al=globalTable.classHasVariable.get(key);
                    if(al!=null)
                        for (Variable variable : al) {
                            if (variable.name.equals(s)) {
                                    return variable.type.toString();
                            }
                        }
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R visit(PlusExpression n, A argu) {//Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);

        String f2=(String)n.f2.accept(this,argu);

        boolean flag1,flag2;
        flag1=f0.equals("int")||idToInt(f0,argu);
        flag2=f2.equals("int")||idToInt(f2,argu);
        if(flag1&&flag2)
            return (R)"int";

        System.out.println("Type error");
        System.exit(1);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R visit(MinusExpression n, A argu) {//Done
        //System.out.println(argu);
        String f0=(String)n.f0.accept(this,argu);
        String f2=(String)n.f2.accept(this,argu);


        boolean flag1,flag2;
        flag1=f0.equals("int")||idToInt(f0,argu);
        flag2=f2.equals("int")||idToInt(f2,argu);

        if(flag1&&flag2)
            return (R)"int";

       // System.out.println(argu);
       // System.out.println(f0+""+f2);
        //System.out.println(globalTable.classHasMethod.get("BBS").get(2).name);

        System.out.println("Type error");
        System.exit(1);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R visit(TimesExpression n, A argu) { //Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);
        String f2=(String)n.f2.accept(this,argu);

        boolean flag1,flag2;
        flag1=f0.equals("int")||idToInt(f0,argu);
        flag2=f2.equals("int")||idToInt(f2,argu);
        if(flag1&&flag2)
            return (R)"int";

        System.out.println("Type error");
        System.exit(1);
        return null;
    }



    /**
     * f0 -> PrimaryExpression()
     * f1 -> "["
     * f2 -> PrimaryExpression()
     * f3 -> "]"
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(ArrayLookup n, A argu) { //Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);
        String f2=(String)n.f2.accept(this,argu);

        boolean flag1,flag2;
        flag1=f0.equals("int[]")||idToIntArr(f0,argu);
        //System.out.println(1233);
        flag2=f2.equals("int")||idToInt(f2,argu);
        //System.out.println(flag2);
        if(flag1&&flag2)
            return (R)"int";


        System.out.println("Type error");
        System.exit(1);
        return null;


    }



    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> "length"
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(ArrayLength n, A argu) { //Done
        super.visit(n, argu);
        String f0=(String)n.f0.accept(this,argu);

        boolean flag=f0.equals("int[]")||idToIntArr(f0,argu);

        if(!flag ){
            System.out.println("Type error");
            System.exit(1);
        }
        return (R)"int";
    }


    /**
     * f0 -> FormalParameter()
     * f1 -> ( FormalParameterRest() )*/

    @SuppressWarnings("unchecked")
    @Override
    public R visit(FormalParameterList n, A argu) {

        n.f0.accept(this, argu);

        n.f1.accept(this, argu);

        return (R)argu;
    }



    /**
     * f0 -> Type()
     * f1 -> Identifier()*/

    @Override
    @SuppressWarnings("unchecked")
    public R visit(FormalParameter n, A argu) {
        String type=n.f0.accept(this, argu).toString();
        String name=n.f1.accept(this, argu).toString();

        return null;
    }

    /**
     * f0 -> ","
     * f1 -> FormalParameter()*/

    @Override
    public R visit(FormalParameterRest n, A argu) {

        n.f0.accept(this, argu);
        return n.f1.accept(this, argu);

    }




    /**
     * f0 -> Expression()
     * f1 -> ( ExpressionRest() )*
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(ExpressionList n, A argu) {
        ArrayList<String> set=new ArrayList<String>();
        String f0=n.f0.accept(this, argu).toString();
        set.add(f0);
        n.f1.accept(this, (A) set);

        return (R) set;
    }

    /**
     * f0 -> ","
     * f1 -> Expression()
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(ExpressionRest n, A argu) {

        ArrayList<String> set=(ArrayList<String>) argu;

        String f1=n.f1.accept(this, null).toString();
        set.add(f1);
        return null;

    }



    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( ExpressionList() )?
     * f5 -> ")"
     */
    @Override
    @SuppressWarnings("unchecked")
    public R visit(MessageSend n, A argu) { //Not Done!!!!!!!!!!!!!!!!!!!!!!!! f4!!!!!!!!!!!!!!!!!!!!!!!!!!



        String f0=n.f0.accept(this,argu).toString();

        if(f0.equals("this")){
            for(A key: globalTable.classHasMethod.keySet() ){
                for(int i=0;i<globalTable.classHasMethod.get(key).size();i++){
                    if(globalTable.classHasMethod.get(key).get(i).name.equals(argu)){
                        f0=key.toString();
                    }
                }

            }
        }

        f0=idToType(f0,argu);

        ArrayList<String> methods=globalTable.classHasClass.get("main");

        //System.out.println(n.f2.accept(this,argu));


        if(!methods.contains(f0)){
            System.out.println("Type error");
            System.exit(1);
        }


        String f2=n.f2.accept(this,argu).toString();

        ArrayList<Method> savedMethod=globalTable.classHasMethod.get(f0);
        for(int i=0;i<savedMethod.size();i++){
            if (savedMethod.get(i).name.equals(f2)){

                ArrayList<String> arg=(ArrayList<String>)n.f4.accept(this,argu);


                ArrayList<String> arguments=new ArrayList<>();
                if(arg!=null) {
                    for(String s:arg){
                        if(s.equals("int")||s.equals("boolean"))
                            arguments.add(s);
                        else {
                            arguments.add(idToType(s, argu));
                        }
                    }
                }

                if(arguments.size()==savedMethod.get(i).al.size()){
                    for(int j=0;j<savedMethod.get(i).al.size();j++){
                        if(!arguments.contains(savedMethod.get(i).al.get(j).type)){
                            System.out.println("Type error");
                            System.exit(1);
                        }
                    }
                }
                return (R) savedMethod.get(i).rtnType;
            }
        }
        return null;

    }







    /**
     * f0 -> "!"
     * f1 -> Expression()
     */

    @Override
    @SuppressWarnings("unchecked")
    public R visit(NotExpression n, A argu) {
        super.visit(n, argu);
        String exp = (String) n.f1.accept(this, argu);
        String typeF1 = idToType(exp, argu);


        if ("boolean".equals(exp) || "boolean".equals(typeF1))
            return (R) "boolean";


        System.out.println("Type error");
        System.exit(1);
        return null;

    }

    /**
     * f0 -> "new"
     * f1 -> "int"
     * f2 -> "["
     * f3 -> Expression()
     * f4 -> "]"
     */

    @Override
    @SuppressWarnings("unchecked")
    public R visit(ArrayAllocationExpression n, A argu) { //Not done !!!!!!!!!!!!!
        super.visit(n, argu);
        String exp=((String)n.f3.accept(this,argu));


        boolean flag1=exp.equals("int")||idToInt(exp,argu);
        if(flag1)
            return (R)"int[]";



        System.out.println("Type error");
        System.exit(1);
        return null;
    }
}
