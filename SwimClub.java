import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

record ContestRecord(String contestName, String phoneNumber, int placement, double durationInSeconds) {}

class SwimClub {

    // List of members in the swim club
    private final List<Member> members = new ArrayList<>();
    // Phone number and outstanding due
    private final Map<String, Double> payments = new HashMap<>();
    // Phone number and disciplines
    private final Map<String, List<String>> membershipDisciplines = new HashMap<>();
    // Phone number and a list of contest placements
    private final Map<String, List<ContestRecord>> contestRecords = new HashMap<>();

    // Register membership and parse date.
    public void registerMembership(String fullName, String phoneNumber, String birthdate, boolean isActive, boolean isCompetitive, boolean isPaid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = null;

        try {
            date = LocalDate.parse(birthdate, formatter);
        } catch(DateTimeParseException exception) {
            System.err.println("Please input a data of pattern dd-MM-yyyy");
        }

        var member = new Member(fullName, phoneNumber, date, isActive, isCompetitive, isPaid);

        if (isPaid) {
            // Calculate fee and put payment
            this.payments.put(phoneNumber, calculateFee(member));
        }

        this.members.add(member);
    }

    // Calculate membership dues based on member age and activity
    public double calculateDues(Member member) {

        if (member.isPaid()) {
            return 0;
        }

        Double paidAmountForMember = payments.getOrDefault(member.getPhoneNumber(), 0d);

        double duesAmount = calculateFee(member);

        if (duesAmount - paidAmountForMember <= 0) {
            member.setMemberHasPaid();
        }

        return duesAmount - paidAmountForMember;
    }

    private double calculateFee(Member member) {
        double duesAmount;
        if (member.isActive()) {
            if(member.isJunior()) {
                duesAmount = 1000;
            } else {
                duesAmount = 1600;
                if (member.isSenior()) {
                    // Apply senior discount
                    duesAmount *= 0.75;
                }
            }
        } else {
            // Passive membership
            duesAmount = 500;
        }
        return duesAmount;
    }

    // Get dues for all members.
    public void getMembershipDues() {
        for (Member member : members) {
            var due = calculateDues(member);
            if (due > 0) {
                System.out.println(member + " - Due: " + due);
            }
        }
    }

    // Pay membership due for a specific member based on phone number.
    public void payDue(String phoneNumber, double duePaid) {
        if (payments.containsKey(phoneNumber)) {
            Double previousDue = payments.get(phoneNumber);
            payments.replace(phoneNumber, previousDue + duePaid);
        } else {
            payments.put(phoneNumber, duePaid);
        }
    }

    // Register membership in a discipline based on a phone number.
    public void registerMemberInDisciplines(String phoneNumber, String... disciplines) {
        membershipDisciplines.put(phoneNumber, new ArrayList<>(List.of(disciplines)));
    }

    // Print list of competitive junior swimmers.
    public void getCompetitiveJuniorSwimmers() {
        for (Member member : members) {
            if (member.isJunior() && member.isCompetitive()) {
                System.out.print(member);
                if (membershipDisciplines.containsKey(member.getPhoneNumber())) {
                    for (String discipline : membershipDisciplines.get(member.getPhoneNumber())) {
                        System.out.print(" " + discipline);
                    }
                }
                System.out.println();
            }
        }
    }

    // Print list of competitive senior swimmers (anyone who isn't a junior)
    public void getCompetitiveSeniorSwimmers() {
        for (Member member : members) {
            if (!member.isJunior() && member.isCompetitive()) {
                System.out.print(member);
                if (membershipDisciplines.containsKey(member.getPhoneNumber())) {
                    for (String discipline : membershipDisciplines.get(member.getPhoneNumber())) {
                        System.out.print(" " + discipline);
                    }
                }
                System.out.println();
            }
        }
    }

    // Register contest results for a specific discipline.
    public void registerContestResult(String discipline, ContestRecord record) {
        contestRecords.putIfAbsent(discipline, new ArrayList<>());
        contestRecords.get(discipline).add(record);
    }

    public void getFiveBestSwimmersInDiscipline(String discipline) {
        if (contestRecords.containsKey(discipline)) {
            List<ContestRecord> records = new ArrayList<>(contestRecords.get(discipline));
            records.sort(Comparator.comparingDouble(ContestRecord::durationInSeconds));
            records.stream().limit(5).forEach(System.out::println);
        } else {
            System.out.println("No records found for discipline: " + discipline);
        }
    }

    public void checkPaymentList() {
        for (Member member : members) {
            double due = calculateDues(member);
            if (due > 0) {
                System.out.println(member + " has an outstanding payment of: " + due);
            }
        }
    }
}