package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.List;

class Item<T> implements Comparable<Item<T>> {
    T element;
    int priority;

    public Item(T element, int priority) {
        this.element = element;
        this.priority = priority;
    }

    @Override
    public int compareTo(Item<T> o) {
        return Integer.compare(this.priority, o.priority);
    }
}

class PriorityQueue<T> {
    List<Item<T>> items;

    PriorityQueue () {
        items = new ArrayList<>();
    }

    void add (T element, int priority) {
        Item<T> newItem = new Item<>(element, priority);
        int i;
        for (i=0; i< items.size(); i++) {
            if (newItem.compareTo(items.get(i))<=0) {
                break;
            }
        }
        items.add(i, newItem);
    }

    T remove () {
        if (isEmpty())
            return null;
        else {
            Item<T> deletedItem = items.remove(items.size()-1);
            return deletedItem.element;
        }
//            return items.remove(items.size()-1).element;
    }

    boolean isEmpty () {
        return items.size()==0;
    }


}

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("najvisok",100);
        priorityQueue.add("sreden", 50);
        priorityQueue.add("nenajvisok", 90);
        priorityQueue.add("najnizok",10);
        priorityQueue.add("najnajvisok", 110);

        while (!priorityQueue.isEmpty()) {
            String el = priorityQueue.remove();
            System.out.println(el);
        }
    }
}
