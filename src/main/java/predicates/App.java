package predicates;

import predicates.domain.Func;
import predicates.domain.Predicate;
import predicates.factory.PredicateFactory_P;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for (Func func: new Func(4,1)){
            for (Predicate predicate: new PredicateFactory_P(4)){
                if (PredicateService.checkSave(predicate, func)) {
                    System.out.println(func.getNum().toString() + " " + predicate.getVectors().toString());
                }
            }
        }
    }

}
