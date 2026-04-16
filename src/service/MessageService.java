package service;

import model.*;
import util.TimestampGenerator;
import java.util.Scanner;

public class MessageService {
    private static int messageId = 1;

    public void acceptMessage(Scanner sc, HashtagService hashtagService, AgencyService agencyService, UndoService undoService) {
        System.out.print("User/source: ");
        String user = sc.nextLine().trim();
        System.out.print("Text: ");
        String text = sc.nextLine().trim();

        if (user.isEmpty() || text.isEmpty()) {
            System.out.println("User and message text cannot be empty.");
            return;
        }

        Message msg = new Message(messageId++, user, text, TimestampGenerator.getTimeStamp());
        hashtagService.extractHashtags(msg.text);
        Agency assigned = agencyService.routeMessage(msg);
        undoService.trackAssignment(msg);

        System.out.println("Assigned message #" + msg.id + " to " + assigned.name);
        System.out.print("Tags: ");
        hashtagService.printTags(msg.text);
    }
}