package sample;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Reponse {
    protected final String id;
    protected Set<Proposition> propositions=new Set<Proposition>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Proposition> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Proposition proposition) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Proposition> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    public Reponse(String id) {
        this.id = id;
    }
    public void AjouterPropos(Proposition proposition)
    {
        propositions.add(proposition);
    }
    public void RetirerReponse(String propositionId)
    {
        for (Proposition p:propositions)
        {
            if ((p.id).equals(propositionId))
            {
                propositions.remove(p);
            }
        }
    }
}
