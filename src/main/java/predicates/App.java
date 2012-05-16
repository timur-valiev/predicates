package predicates;

import predicates.domain.Func;
import predicates.domain.Predicate;
import predicates.factory.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        BufferedWriter writer =  new BufferedWriter(new FileWriter("RESULTS"));
        /*writer.write("Class P:");
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
                }
            }
        }
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_B(4,1,4)){
                if (PredicateService.checkSave(predicate, func)) {
                    writer.write(func.getValues().getValues().toString() + " " + predicate.getVectors().toString());
                    writer.newLine();
                }
            }
        }
        */
        Set<Predicate> all = new LinkedHashSet<Predicate>();
        Set<Predicate> all_B3 = new LinkedHashSet<Predicate>();
        Set<Predicate> all_C1 = new LinkedHashSet<Predicate>();
        Set<Predicate> all_B4 = new LinkedHashSet<Predicate>();
        Set<Predicate> all_C2 = new LinkedHashSet<Predicate>();
        Set<Predicate> all_C3 = new LinkedHashSet<Predicate>();
        Set<Predicate> all_E = new LinkedHashSet<Predicate>();
        Set<Predicate> all_L = new LinkedHashSet<Predicate>();
        Set<Predicate> all_O = new LinkedHashSet<Predicate>();
        Set<Predicate> all_P = new LinkedHashSet<Predicate>();

            for (Predicate predicate: new PredicateFactory_B(3,1,4)){
                all.add(predicate);
                all_B3.add(predicate);
            }

        for (Predicate predicate: new PredicateFactory_B(4,1,4)){
            all.add(predicate);
            all_B4.add(predicate);
        }

        for (Predicate predicate: new PredicateFactory_C(4,1)){
            all.add(predicate);
            all_C1.add(predicate);
        }

        for (Predicate predicate: new PredicateFactory_C(4,2)){
            all.add(predicate);
            all_C2.add(predicate);
        }

        for (Predicate predicate: new PredicateFactory_C(4,3)){
            all.add(predicate);
            all_C3.add(predicate);
        }

        for (Predicate predicate: new PredicateFactory_E(4)){
            all.add(predicate);
            all_E.add(predicate);
        }

        Predicate pred = new PredicateFactory_L(4).getPredicate4();
        all.add(pred);
        all_L.add(pred);

        for (Predicate predicate: new PredicateFactory_O(4)){
            all.add(predicate);
            all_O.add(predicate);
        }

        for (Predicate predicate: new PredicateFactory_P(4)){
            all.add(predicate);
            all_P.add(predicate);
        }
        writer.write(all.toString());
        writer.flush();
        writer.close();
    }
}
