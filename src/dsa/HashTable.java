package dsa;

public class HashTable {
    private class Entry{
        String key;
        int value;
        Entry next;

        Entry(String key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 100;
    private Entry[] table = new Entry[SIZE];

    private int hash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return hash % SIZE;
    }

    public void put(String key) {
        int index = hash(key);
        Entry current = table[index];
        while(current != null){
            if(current.key.equals(key)){
                current.value++;
                return;
            }
           current = current.next;
        }
        Entry newEntry = new Entry(key,1);
        newEntry.next = table[index];
        table[index] = newEntry;
    }

    public int get(String key) {
        int index = hash(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return 0;
    }

    public String[] getAllKeys() {
        String[] keys = new String[SIZE * 2];
        int count = 0;
        for(Entry bucket : table){
            Entry current = bucket;
            while(current !=  null){
                keys[count++] = current.key;
                current = current.next;
            }
        }
        return keys;
    }

}
