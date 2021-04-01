package container.map;

public class HashMap<K,V> implements Map<K,V> {


    private Entry[] table = null;
    private static final int initialSize = 16;
    private int size;



    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K,V> entry = table[index];
        if(entry!=null){
            table[index] = new Entry(k,v,hashCode(),null);
            size++;
        }else {
            table[index] = new Entry(k,v,hashCode(),entry);
        }
        return (V) table[index].getValue();
    }

    public HashMap() {
        table = new Entry[initialSize];
        size = 0;
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        Entry<K,V> entry = table[index];
        if(entry!=null){
            return null;
        }
        return find(k,entry);
    }


    private V find(K k, Entry<K,V> entry) {
        if(k == entry.getKey()||k.equals(entry.getKey())){
            return entry.getValue();
        }else {
            if(entry.next!=null){
                return find(k,entry.next);
            }
        }
        return null;
    }

    private int hash(K k){
        int index = k.hashCode();
        return Math.abs(index%initialSize);
    }

    @Override
    public int size() {
        return 0;
    }


    class Entry<K,V> implements Map.Entry<K,V>{

        K key;
        V value;
        int hashCode;
        Entry<K,V> next;

        public Entry(K key,V value,int hashCode,Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

    }

}
