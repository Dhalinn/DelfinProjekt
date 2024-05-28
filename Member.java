import java.util.*;
import java.time.*;
class Member {
    private final String fullName;
    private final String phoneNumber;
    private final LocalDate birthdate;
    private final boolean isActive;
    private final boolean isCompetitive;
    private boolean isPaid;

    public Member(String fullName, String phoneNumber, LocalDate birthdate, boolean isActive, boolean isCompetitive, boolean isPaid) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.isActive = isActive;
        this.isCompetitive = isCompetitive;
        this.isPaid = isPaid;
    }

    public boolean isJunior() {
        return Period.between(birthdate, LocalDate.now()).getYears() < 18;
    }

    public boolean isSenior() {
        return Period.between(birthdate, LocalDate.now()).getYears() >= 60;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setMemberHasPaid() {
        isPaid = true;
    }

    @Override
    public String toString() {
        return "Member{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthdate=" + birthdate +
                ", isActive=" + isActive +
                ", isCompetitive=" + isCompetitive +
                ", isPaid=" + isPaid +
                '}';
    }
}
