package predicates;

import predicates.domain.ExtendedFunction;
import predicates.domain.FastPredicate;
import predicates.domain.MyArray;

public class PredicateService {
    //Метод, проверяющий факт сохранения функцией предиката
    public static boolean checkSave(FastPredicate predicate, ExtendedFunction function){
        int res;
        if (function.getCapacity()==1){
            for (MyArray cur:  predicate.getVectors()) {
                res = 0;
                int ii=0;
                for (Integer i: cur.array){
                    res=res*4+(function.values[i]);
                }
                if (!predicate.codes[res])
                    return false;
            }
            return true;
        }
        else
        if(function.getCapacity()==2){
            boolean ans = false;
            for (byte b:predicate.existValues){
                ans=ans || (b<=function.existValues && ((function.existValues & b)==b));
            }
            if (!ans)
                return false;

            for (int pairCode:predicate.pairCodes) {
                try{
                    if (!predicate.codes[function.getValueForVector(predicate.capacity,pairCode)])
                        return false;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }
}
