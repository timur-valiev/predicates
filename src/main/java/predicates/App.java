package predicates;

import predicates.domain.Func;
import predicates.domain.Predicate;
import predicates.factory.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        BufferedWriter writer =  new BufferedWriter(new FileWriter("RESULTS"));
        writer.write("Class P:");
        writer.newLine();
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_P(4)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                }
            }
        }

        PredicateFactory_E predicateFactory_e = new PredicateFactory_E(4);
        writer.write("Class E:");
        writer.newLine();
        for (Func func: new Func(4,1)){
            Predicate p = predicateFactory_e.getMostExtensivePredicate(func);
            if (p!=null){
                writer.write(func.getValues().getValues().toString() + " " + p.getVectors().toString());
                writer.newLine();
            }
        }

        try {
            Predicate predicate4 = new PredicateFactory_L(4).getPredicate4();
            writer.write("Class L: " + predicate4.getVectors().toString());
            writer.newLine();
            for(Func func:new Func(4,1))
                if (PredicateService.checkSave(predicate4, func)) {
                    writer.write(func.getValues().getValues().toString());
                    writer.newLine();
                }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        writer.write("Class O:");
        writer.newLine();
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_O(4)){
                if (PredicateService.checkSave(predicate, func)) {
                   writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                   writer.newLine();
                }
            }
        }

        writer.write("Class C:");
        writer.newLine();
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_C(4,2)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                }
            }
        }
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_C(4,3)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                }
            }
        }

        writer.write("Class B:");
        writer.newLine();
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_B(3,1,4)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                    break;
                }
            }
        }
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_B(4,1,4)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                    break;
                }
            }
        }


        writer.flush();
        writer.close();
    }
}
