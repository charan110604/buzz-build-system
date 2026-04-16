package service;

import model.*;
import dsa.Queue;

public class AgencyService {
    private Agency[] agencies = {
            new Agency("SportsSphere PR"),
            new Agency("HypeHaus Agency"),
            new Agency("TrendTrackers Ltd")
    };

    private int roundRobinIndex = 0;

    public Agency routeMessage(Message msg) {
        Agency agency = agencies[roundRobinIndex];
        msg.status = "PENDING";
        agency.assignMessage(msg);
        roundRobinIndex = (roundRobinIndex + 1) % agencies.length;
        return agency;
    }

    public void displayQueues() {
        for (Agency agency : agencies) {
            System.out.println("\nAgency: " + agency.name);
            int size = agency.queue.size();
            for (int i = 0; i < size; i++) {
                Message msg = agency.queue.dequeue();
                String displayStatus = formatStatus(msg.status);
                System.out.println("  " + (i + 1) + ". #" + msg.id + " [" + displayStatus + "] " + msg.user + ": " + msg.text);
                agency.queue.enqueue(msg);
            }
        }
    }

    public void processNext() {
        for (Agency agency : agencies) {
            Message msg = agency.queue.peek();
            if (msg != null && msg.status.equals("PENDING")) {
                msg.status = "IN_PROGRESS";
                System.out.println(agency.name + " started message #" + msg.id);
                return;
            }
        }
        System.out.println("No pending messages to process.");
    }

    public void markDone(UndoService undoService) {
        for (Agency agency : agencies) {
            Message msg = agency.queue.peek();
            if (msg != null && msg.status.equals("IN_PROGRESS")) {
                msg.status = "DONE";
                agency.queue.dequeue();
                undoService.trackProcessing(msg);
                System.out.println(agency.name + " completed message #" + msg.id);
                return;
            }
        }
        System.out.println("No in-progress messages to mark as done.");
    }

    private String formatStatus(String status) {
        return switch (status) {
            case "PENDING" -> "Pending";
            case "IN_PROGRESS" -> "In Progress";
            case "DONE" -> "Done";
            default -> status;
        };
    }

    public Agency[] getAgencies() {
        return agencies;
    }
}