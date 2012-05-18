package predicates;

import com.google.common.collect.ImmutableList;
import predicates.domain.Function;
import predicates.domain.Predicate;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 13.04.12
 * Time: 13:05
 */
public class PredicateService {
    public static boolean checkSave(Predicate predicate, Function function){
        if (function.getCapacity()==1){
            ArrayList<Integer> res = new ArrayList<Integer>();
            for (ImmutableList<Integer> cur:  new ArrayList<ImmutableList<Integer>>(predicate.getVectors())) {
                res.clear();
                for (Integer i: cur)
                    res.add(function.getValue(i));
                if (!predicate.getVectors().contains(ImmutableList.copyOf(res)))
                    return false;
            }
            return true;
        }
        else 
            return false;
    }
}
