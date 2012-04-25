package predicates.factory;

import predicates.domain.Func;
import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 20.04.12
 * Time: 13:34
 */
public class PredicateFactory_L extends PredicateFactory{
    private int[][] table = new int[4][];
    
    public PredicateFactory_L(Integer dim) throws Exception {
        this.dim = dim;
        if (dim==4){
            table[0] = new int[]{0, 1, 2, 3};
            table[1] = new int[]{1, 0, 3, 2};
            table[2] = new int[]{2, 3, 0, 1};
            table[3] = new int[]{3, 2, 1, 0};
        }
        else throw new Exception("Not supported dim");
    }

    public Predicate getPredicate4(){
        Predicate predicate = new Predicate(dim,4);
        for (Tuple tuple: new Tuple(4,4)){
            if(table[tuple.getValue(0)][tuple.getValue(1)]==table[tuple.getValue(2)][tuple.getValue(3)])
                predicate.addVector(tuple.getValues());
        }
        return predicate;
    } 
    

}
