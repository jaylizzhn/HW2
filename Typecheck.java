import syntaxtree.Goal;
import myVisitor.PrettyPrinter;
import visitor.GJDepthFirst;
import myVisitor.myGJDF;

public class Typecheck {
    public static void main(String[] args) throws ParseException {

        MiniJavaParser parser =new MiniJavaParser(System.in);

        Goal tree=parser.Goal();

        myGJDF<String,String> tableBuilder=new myGJDF<String,String>();
        tableBuilder.visit(tree,"main");
       // System.out.println(tableBuilder.globalTable.classHasClass);
      //  System.out.println(tableBuilder.globalTable.classHasMethod);
       // System.out.println(tableBuilder.globalTable.classHasVariable);

      //  System.out.println(tableBuilder.globalTable.classHasMethod.get());




        PrettyPrinter<String,String> printer=new PrettyPrinter<String,String>();
        printer.globalTable=tableBuilder.globalTable;
        printer.visit(tree,"main");


    }
}
