package entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class User {

    public final String name;
    public Object getID;
    private String token;

    public final String id;
    public User(String name, String token, String id){ this.name = name; this.token = token; this.id = id;}

    public String getName(){return this.name;}

    public String getToken(){return this.token;}

    public void setToken(String token){this.token = token;}

    public String getId(){return this.id;}

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
