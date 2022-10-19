import javax.swing.*;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class UserInputAndHandling {

    public String userInput() {
            return JOptionPane.showInputDialog(null, "Vilken medlem vill du se, ange namn eller id: ");
        }

    public Member compareInputToList(String input, List<Member> member) {
        for (Member memb : member) {
            if (input.equalsIgnoreCase(memb.getName()) || (input.equalsIgnoreCase(memb.getIdNumber()))) {
                return memb;
            }
        }throw new NoSuchElementException();
    }
    public void writeOutputToReceptionAndTrainer(PrintWriter print, List<Member> member) {
        while (true) {
            String userInput = userInput();
            try {
                Member memb = compareInputToList(userInput, member);
                if (compareDate(memb.getDate().toString())) {
                    System.out.println(outputIfActive(memb));
                    printToFileIfActive(print, memb);
                    break;
                } else {
                    System.out.println(memb.getName() + " har tyvärr inte betalat årsavgiften, hen betalade senast: " + memb.getDate());
                    break;
                }
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(null, "Skriv in ett giltigt namn eller id-nummer tack!");
            } catch (NullPointerException e) {
                System.exit(0);
            }
        }
    }
    public boolean compareDate (String input) {
        LocalDate date1 = LocalDate.now().minusYears(1);
        LocalDate date2 = LocalDate.parse(input);
        return date1.compareTo(date2) < 0;
    }
    public String getNameFromFile(String input){
        String[] stringArray = input.split(",");
        return stringArray[1].trim();
    }
    public String getIdNumberFromFile(String input){
        String[] stringArray = input.split(",");
        return stringArray[0].trim();
    }
    public LocalDate getDateFromFile(String input){
        return LocalDate.parse(input);
    }
    public String outputIfActive(Member memb){
        return memb.getName() + " är en giltig medlem som betalade sin årsavgift: " + memb.getDate();
    }
    public String printToFileIfActive(PrintWriter print, Member memb){
        print.println(memb.getIdNumber()+", "+memb.getName()+" tränade datum: "+LocalDate.now());
        return memb.getIdNumber()+", "+memb.getName()+" tränade datum: "+LocalDate.now();
    }
}

