package predicates;

import predicates.domain.Func;
import predicates.domain.Predicate;
import predicates.factory.PredicateFactory_E;
import predicates.factory.PredicateFactory_P;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Class P:");
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_P(4)){
                if (PredicateService.checkSave(predicate, func)) {
                    System.out.println(func.getValues().toString() + " " + predicate.getVectors().toString());
                }
            }
        }

        PredicateFactory_E predicateFactory_e = new PredicateFactory_E(4);
        System.out.println("Class E:");
        for (Func func: new Func(4,1)){
            Predicate p = predicateFactory_e.getMostExtensivePredicate(func);
            if (p!=null){
                System.out.println(func.getValues().toString() + " " + p.getVectors().toString());
            }
        }

    }

}
