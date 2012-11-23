package predicates;

import predicates.domain.FastPredicate;
import predicates.domain.Function;
import predicates.domain.Predicate;
import predicates.factory.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/*
Main class-Launcher
*/
public class App 
{
    public static void main( String[] args ) throws Exception {
        BufferedWriter writer =  new BufferedWriter(new FileWriter("text/prilozh_A3.txt"));
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
        for (Predicate predicate: new PredicateFactory_L(4)){
            all.add(predicate);
            all_L.add(predicate);
        }
        for (Predicate predicate: new PredicateFactory_O(4)){
            if (!all_O.contains(predicate.getMirror())){
                all.add(predicate);
                all_O.add(predicate);
            }
        }
        for (Predicate predicate: new PredicateFactory_P(4)){
            all.add(predicate);
            all_P.add(predicate);
        }
        writer.write("Приложение А\n\nТаблица принадлежности функций предполным классам\n");

        List<Predicate> all_list = new ArrayList<Predicate>();
        List<Predicate> p_list = new ArrayList<Predicate>(all_P);
        List<Predicate> o_list = new ArrayList<Predicate>(all_O);
        List<Predicate> l_list = new ArrayList<Predicate>(all_L);
        List<Predicate> e_list = new ArrayList<Predicate>(all_E);
        List<Predicate> c_list = new ArrayList<Predicate>(all_C1);
        c_list.addAll(all_C2);
        c_list.addAll(all_C3);
        List<Predicate> b_list = new ArrayList<Predicate>(all_B3);
        b_list.addAll(all_B4);

        all_list.addAll(p_list);
        all_list.addAll(o_list);
        all_list.addAll(l_list);
        all_list.addAll(e_list);
        all_list.addAll(c_list);
        all_list.addAll(b_list);
        Long ii = 0l;

        FastPredicate[] fastPredicates = new FastPredicate[all_list.size()];
        for (int i =0;i<all_list.size();i++)
            fastPredicates[i] = new FastPredicate(all_list.get(i));

        Set<String> ans = new TreeSet<String>();

        for (Function function: new Function(4,2)){
            if(ii%(4096)==0)
                System.out.println(ii.toString()+" "+ans.size());
            ii++;

            StringBuilder ss = new StringBuilder();
            for (FastPredicate predicate:fastPredicates){
                if (PredicateService.checkSave(predicate,function))
                    ss.append("1 ");
                else
                    ss.append("0 ");
            }
            ans.add(ss.toString());
        }

        for(String s: ans){
            writer.write(s+"\n");
        }

        /*
        int i=0;
        writer.write("\n\nСписок предикатов, описывающих предполные классы\n");
        writer.write("Класс Р\n");
        for (Predicate predicate:p_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }
        writer.write("Класс O\n");
        for (Predicate predicate:o_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }
        writer.write("Класс L\n");
        for (Predicate predicate:l_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }
        writer.write("Класс E\n");
        for (Predicate predicate:e_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }
        writer.write("Класс C\n");
        for (Predicate predicate:c_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }
        writer.write("Класс B\n");
        for (Predicate predicate:b_list){
            i++;
            writer.write("#"+i+"\n");
            writer.write(predicate.getVectors().toString()+"\n");
        }*/

        writer.flush();
        writer.close();
    }
}
