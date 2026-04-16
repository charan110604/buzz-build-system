package dsa;

import model.Hashtag;

public class PriorityQueue {
    private Hashtag[] heap;
    private int size;

    public PriorityQueue(int capacity){
        heap = new Hashtag[capacity];
        size = 0;
    }

    private void swap(int i,int j){
        Hashtag temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyUp(int index){
        while(index > 0 && heap[index].count > heap[(index -1) / 2].count){
            swap(index,(index-1)/2);
            index = (index -1) /2;
        }
    }

    private void heapifyDown(int index){
        int largest = index;
        int left = 2 * index + 1,right = 2 * index + 2;

        if(left < size && heap[left].count > heap[largest].count) largest = left;
        if(right < size && heap[right].count > heap[largest].count) largest = right;

        if(largest != index){
            swap(index,largest);
            heapifyDown(largest);
        }
    }

    public void insert(Hashtag tag){
        if(size >= heap.length) return;
        heap[size] = tag;
        heapifyUp(size);
        size++;
    }

    public Hashtag extractMax(){
        if(size == 0) return null;
        Hashtag max = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return max;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
