import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Setup
        SwimClub swimClub = initializeSwimClub();
        Scanner scanner = new Scanner(System.in);

        //
        while (true) {
            System.out.println("0. Add Membership");
            System.out.println("1. Get Membership Dues");
            System.out.println("2. Pay Due");
            System.out.println("3. Register Member in Disciplines");
            System.out.println("4. Get Competitive Junior Swimmers");
            System.out.println("5. Get Competitive Senior Swimmers");
            System.out.println("6. Register Contest Result");
            System.out.println("7. Get Five Best Swimmers in Discipline");
            System.out.println("8. Check Payment List");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 0:
                    System.out.print("Enter full name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber1 = scanner.nextLine();
                    System.out.print("Enter birthdate dd-MM-yyyy: ");
                    String birthDate = scanner.nextLine();
                    System.out.print("Enter True if member is active, False otherwise: ");
                    boolean active = scanner.nextBoolean();
                    System.out.print("Enter True if member is competitive, False otherwise: ");
                    boolean competitive = scanner.nextBoolean();
                    System.out.print("Enter True if member has paid, False otherwise: ");
                    boolean paid = scanner.nextBoolean();
                    swimClub.registerMembership(name, phoneNumber1, birthDate, active, competitive, paid);
                    break;
                case 1:
                    swimClub.getMembershipDues();
                    break;
                case 2:
                    System.out.print("Enter phone number: ");
                    String phoneNumber2 = scanner.nextLine();
                    System.out.print("Enter amount to pay: ");
                    double duePaid = scanner.nextDouble();
                    swimClub.payDue(phoneNumber2, duePaid);
                    break;
                case 3:
                    System.out.print("Enter phone number: ");
                    String phoneNumber3 = scanner.nextLine();
                    System.out.print("Enter disciplines (comma separated): ");
                    String[] disciplines = scanner.nextLine().split(",");
                    swimClub.registerMemberInDisciplines(phoneNumber3, disciplines);
                    break;
                case 4:
                    swimClub.getCompetitiveJuniorSwimmers();
                    break;
                case 5:
                    swimClub.getCompetitiveSeniorSwimmers();
                    break;
                case 6:
                    System.out.print("Enter discipline: ");
                    String contestDiscipline = scanner.nextLine();
                    System.out.print("Enter contest name: ");
                    String contestName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber4 = scanner.nextLine();
                    System.out.print("Enter placement: ");
                    int placement = scanner.nextInt();
                    System.out.print("Enter duration in seconds: ");
                    double durationInSeconds = scanner.nextDouble();
                    swimClub.registerContestResult(contestDiscipline, new ContestRecord(contestName, phoneNumber4, placement, durationInSeconds));
                    break;
                case 7:
                    System.out.print("Enter discipline: ");
                    String discipline = scanner.nextLine();
                    swimClub.getFiveBestSwimmersInDiscipline(discipline);
                    break;
                case 8:
                    swimClub.checkPaymentList();
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static SwimClub initializeSwimClub() {

        SwimClub swimClub = new SwimClub();

        swimClub.registerMembership("Arne", "00110011", "01-01-1969", true, true, true);
        swimClub.registerMembership("Lisbeth", "11110011", "01-01-1984", true, true, false);
        swimClub.registerMembership("Brian", "11111111", "01-01-2014", true, true, true);
        swimClub.registerMembership("Lisa", "20551122", "02-08-1995", true, true, false);
        swimClub.registerMembership("Ismail", "45409140", "16-07-2000", false, false, true);
        swimClub.registerMembership("Peter", "52502991", "13-08-1966", false, false, true);
        swimClub.registerMembership("Noor", "91405591", "20-12-1999", true, true, true);
        swimClub.registerMembership("James", "31457191", "14-03-1982", true, true, true);
        swimClub.registerMembership("Ole", "20205031", "20-02-1988", true, false, false);
        swimClub.registerMembership("Mads", "30314469", "20-01-2001", false, false, true);
        swimClub.registerMembership("Lone", "41406343", "30-04-1999", true, true, true);
        swimClub.registerMembership("Noah", "30225188", "29-09-1997", true, false, false);
        swimClub.registerMembership("Per", "91217588", "24-08-1995", false, false, true);
        swimClub.registerMembership("Hans", "31904455", "04-08-1991", true, true, true);
        swimClub.registerMembership("Melissa", "55317654", "13-06-1975", true, false, false);
        swimClub.registerMembership("Benjamin", "11448507", "11-12-1994", true, false, true);
        swimClub.registerMembership("Lars", "33548823", "16-09-1959", false, false, false);
        swimClub.registerMembership("Karl", "23557382", "15-01-2002", true, false, false);
        swimClub.registerMembership("Mona", "55310907", "18-03-1993", false, false, true);
        swimClub.registerMembership("Hans", "33756832", "18-11-1993", true, true, true);
        swimClub.registerMembership("Lasse", "55743189", "22-10-1991", false, false, true);
        swimClub.registerMembership("Signe", "42407822", "14-09-2001", true, false, true);
        swimClub.registerMembership("Bo", "30226791", "23-09-1990", false, false, true);
        swimClub.registerMembership("Tyrone", "28423891", "24-04-2002", true, true, false);

        swimClub.registerContestResult("Crawl", new ContestRecord("EM", "00110011", 1, 29));
        swimClub.registerContestResult("Crawl", new ContestRecord("DM", "11110011", 2, 28));
        swimClub.registerContestResult("Crawl", new ContestRecord("DM", "00110011", 1, 27));
        swimClub.registerContestResult("Butterfly", new ContestRecord("DM", "00110011", 1, 27));
        swimClub.registerContestResult("Butterfly", new ContestRecord("DM", "11111111", 4, 32));
        swimClub.registerContestResult("Butterfly", new ContestRecord("DM", "20551122", 6, 36));
        swimClub.registerContestResult("Breaststroke", new ContestRecord("DM", "20551122", 5, 40));
        swimClub.registerContestResult("Sidestroke", new ContestRecord("DM", "20551122", 6, 50));
        swimClub.registerContestResult("Medley", new ContestRecord("DM", "20551122", 7, 58));
        swimClub.registerContestResult("Medley", new ContestRecord("EM", "20551122", 3, 44));
        swimClub.registerContestResult("Crawl", new ContestRecord("DM", "91405591", 5, 60));
        swimClub.registerContestResult("Butterfly", new ContestRecord("DM", "91405591", 13, 78));
        swimClub.registerContestResult("Butterfly", new ContestRecord("EM", "91405591", 5, 68));
        swimClub.registerContestResult("Freestyle", new ContestRecord("DM", "91405591", 4, 55));
        swimClub.registerContestResult("Freestyle", new ContestRecord("EM", "91405591", 8, 78));
        swimClub.registerContestResult("Breaststroke", new ContestRecord("DM", "31457191", 6, 42));
        swimClub.registerContestResult("Breaststroke", new ContestRecord("EM", "31457191", 9, 45));
        swimClub.registerContestResult("Sidestroke", new ContestRecord("DM", "31457191", 9, 77));
        swimClub.registerContestResult("Sidestroke", new ContestRecord("EM", "31457191", 13, 79));
        swimClub.registerContestResult("Freestyle", new ContestRecord("DM", "31457191", 6, 61));
        swimClub.registerContestResult("Breaststroke", new ContestRecord("DM", "33756832", 9, 48));
        swimClub.registerContestResult("Sidestroke", new ContestRecord("DM", "33756832", 10, 79));
        swimClub.registerContestResult("Sidestroke", new ContestRecord("EM", "33756832", 14, 81));
        swimClub.registerContestResult("Medley", new ContestRecord("DM", "33756832", 9, 62));
        swimClub.registerContestResult("Medley", new ContestRecord("EM", "33756832", 14, 68));
        swimClub.registerContestResult("Crawl", new ContestRecord("DM", "28423891", 4, 32));
        swimClub.registerContestResult("Crawl", new ContestRecord("EM", "28423891", 8, 35));
        swimClub.registerContestResult("Butterfly", new ContestRecord("DM", "28423891", 12, 76));
        swimClub.registerContestResult("Butterfly", new ContestRecord("EM", "28423891", 15, 82));
        swimClub.registerContestResult("Freestyle", new ContestRecord("DM", "28423891", 7, 63));
        swimClub.registerContestResult("Freestyle", new ContestRecord("EM", "28423891", 9, 80));

        return swimClub;
    }
}