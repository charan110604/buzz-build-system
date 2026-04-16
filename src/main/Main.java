package main;

import service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MessageService messageService = new MessageService();
        HashtagService hashtagService = new HashtagService();
        AgencyService agencyService = new AgencyService();
        UndoService undoService = new UndoService();

        while (true) {
            printMenu();
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> messageService.acceptMessage(sc, hashtagService, agencyService, undoService);
                case 2 -> agencyService.processNext();
                case 3 -> agencyService.markDone(undoService);
                case 4 -> hashtagService.displayTrending(sc);
                case 5 -> hashtagService.findCount(sc);
                case 6 -> agencyService.displayQueues();
                case 7 -> undoService.undoProcessing(agencyService);
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== BuzzBuildSystem ===");
        System.out.println("1) New message");
        System.out.println("2) Process next (start IN_PROGRESS)");
        System.out.println("3) Mark done (pop when DONE)");
        System.out.println("4) Top trending");
        System.out.println("5) Find hashtag count");
        System.out.println("6) View agency queues");
        System.out.println("7) Undo last action");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }
}