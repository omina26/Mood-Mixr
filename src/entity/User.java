package entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User {

    public final String name;
    private String token;

    public User(String name, String token){ this.name = name; this.token = token;}

    public String getName(){return this.name;}

    public String getToken(){return this.token;}

    public void setToken(String token){this.token = token;}

    public Set<String> getTopTracks(){return new Set<String>() {
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
        public Iterator<String> iterator() {
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
        public boolean add(String s) {
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
        public boolean addAll(Collection<? extends String> c) {
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
    };
}
