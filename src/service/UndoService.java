package service;

import dsa.Queue;
import dsa.Stack;
import model.*;

public class UndoService {
    private Stack<Message> assignmentStack = new Stack<>();
    private Stack<Message> processingStack = new Stack<>();

    public void trackAssignment(Message msg) {
        assignmentStack.push(msg);
    }

    public void trackProcessing(Message msg) {
        processingStack.push(msg);
    }

    public void undoAssignment(AgencyService agencyService) {
        Message msg = assignmentStack.pop();
        if (msg == null) {
            System.out.println("No assignment to undo.");
            return;
        }

        for (Agency agency : agencyService.getAgencies()) {
            Queue<Message> queue = agency.queue;
            Queue<Message> tempQueue = new Queue<>();
            boolean found = false;

            while (!queue.isEmpty()) {
                Message current = queue.dequeue();
                if (current.id == msg.id) {
                    found = true;
                } else {
                    tempQueue.enqueue(current);
                }
            }

            while (!tempQueue.isEmpty()) {
                queue.enqueue(tempQueue.dequeue());
            }

            if (found) {
                System.out.println("Undone assignment of message ID " + msg.id + " from " + agency.name);
                return;
            }
        }

        System.out.println("Message not found in any queue.");
    }

    public void undoProcessing(AgencyService agencyService) {
        Message msg = processingStack.pop();
        if (msg != null) {
            msg.status = "IN_PROGRESS";
            agencyService.routeMessage(msg);
            System.out.println("Reverted processing of message #" + msg.id + " to status IN_PROGRESS");
        } else {
            System.out.println("No processed message to undo.");
        }
    }
}