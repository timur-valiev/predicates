package predicates.domain;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 11.04.12
 * Time: 23:24
 */
public class Func implements Iterable<Func>, Iterator<Func> {

    @Override
    public boolean hasNext() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Func next() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<Func> iterator() {
        return this;
    }



}
