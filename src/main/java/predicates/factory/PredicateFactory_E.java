package predicates.factory;

import predicates.domain.Func;
import predicates.domain.Predicate;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.04.12
 * Time: 23:48
 */
public class PredicateFactory_E extends PredicateFactory{
    public PredicateFactory_E(Integer dim) {
        this.dim = dim;
    }

    public Predicate getMostExtensivePredicate(Func func) {
        Map<Integer,Set<Integer>> classes = new HashMap<Integer, Set<Integer>>();
        for(int i=0;i<dim;i++){
            classes.put(i,new HashSet<Integer>());
            classes.get(i).add(i);
        }
        for(int i = 0; i < dim; i++){
            int f = func.getValue(i);
            classes.get(i).addAll(classes.get(f));
            classes.put(f,classes.get(i));
        }
        if(classes.get(dim-1).size()==dim)
            return null;
        Predicate predicate = new Predicate(dim,2);
        for (int i = 0; i < dim;i++){
            for (int j:classes.get(i)){
                ArrayList<Integer> vector = new ArrayList<Integer>();
                vector.add(i);
                vector.add(j);
                predicate.addVector(vector);
            }
        }
        return predicate;
    }

}
