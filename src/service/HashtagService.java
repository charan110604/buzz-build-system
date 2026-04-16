package service;

import dsa.HashTable;
import dsa.PriorityQueue;
import model.Hashtag;
import java.util.Scanner;

public class HashtagService {
    private HashTable hashtagTable = new HashTable();

    public void extractHashtags(String text) {
        String[] words = text.split(" ");
        for (String word : words) {
            if (word.startsWith("#")) {
                String cleanTag = word.replaceAll("[^#a-zA-Z0-9]", "").toLowerCase();
                if (!cleanTag.equals("#")) {
                    hashtagTable.put(cleanTag);
                }
            }
        }
    }

    public void displayTrending(Scanner sc) {
        System.out.print("Top N: ");
        int n = sc.nextInt(); sc.nextLine();

        PriorityQueue pq = new PriorityQueue(100);
        String[] tags = hashtagTable.getAllKeys();
        for (String tag : tags) {
            if (tag != null) {
                int count = hashtagTable.get(tag);
                pq.insert(new Hashtag(tag.toLowerCase(), count));
            }
        }

        System.out.println("Trending topics:");
        for (int i = 1; i <= n && !pq.isEmpty(); i++) {
            Hashtag top = pq.extractMax();
            System.out.println(i + ". " + top.tag + " (" + top.count + ")");
        }
    }

    public void findCount(Scanner sc) {
        System.out.print("Enter tag (without #): ");
        String tag = sc.nextLine().toLowerCase();
        int count = hashtagTable.get("#" + tag.toLowerCase());
        System.out.println("Count for #" + tag + ": " + count);
    }

    public void printTags(String text) {
        String[] words = text.split(" ");
        boolean first = true;
        for (String word : words) {
            if (word.startsWith("#")) {
                if (!first) System.out.print(", ");
                System.out.print(word.toLowerCase());
                first = false;
            }
        }
        System.out.println();
    }

    public void showDashboard(Scanner sc){
        displayTrending(sc);
    }
}
