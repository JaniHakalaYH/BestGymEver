import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputAndHandlingTest {

    UserInputAndHandling u = new UserInputAndHandling();
    Path testFil2 = Paths.get("Test/testMembers.txt");

    @org.junit.jupiter.api.Test
    void compareDateTest() {
        LocalDate date1 = LocalDate.now().minusYears(1);
        LocalDate date2 = LocalDate.now();
        Assertions.assertTrue(date1.compareTo(date2) < 0);
        Assertions.assertFalse(date1.compareTo(date2) > 0);
    }

    @org.junit.jupiter.api.Test
    void getNameFromFile() {
        String input = "7608021234, Diamanda Djedi";
        assert(u.getNameFromFile(input).equalsIgnoreCase("Diamanda Djedi"));
    }

    @org.junit.jupiter.api.Test
    void getIdNumberFromFile() {
        String input = "7608021234, Diamanda Djedi";
        assert(u.getIdNumberFromFile(input).equalsIgnoreCase("7608021234"));
    }

    @org.junit.jupiter.api.Test
    void getDateFromFile() {
        String input = "2022-01-30";
        Assertions.assertEquals(u.getDateFromFile(input), LocalDate.parse(input));
    }

    @Test
    void compareInputToList() {
        List<Member> member = new ArrayList<>();
        String input = "Diamanda Djedi";
        member.add(new Member("7608021234","Diamanda Djedi",LocalDate.parse("2022-01-30")));
        member.add(new Member("8512021234", "Chamade Coriola", LocalDate.parse("2018-03-12")));

        Assertions.assertEquals(u.compareInputToList(input, member), member.get(0));
        Assertions.assertNotEquals(u.compareInputToList(input, member), member.get(1));
    }

    @Test
    void outputIfActive() {
        Member member1 = new Member("7608021234","Diamanda Djedi",LocalDate.parse("2022-01-30"));
        String output = member1.getName()+ " är en giltig medlem som betalade sin årsavgift: "+member1.getDate();
        Assertions.assertEquals(u.outputIfActive(member1), output);
    }

    @Test
    void printToFileIfActive() {
        List<Member> test = new ArrayList<>();
        test.add(new Member("7703021234","Alhambra Aromes",LocalDate.parse("2022-07-01")));
        test.add(new Member("8512021234","Chamade Coriola",LocalDate.parse("2018-03-12")));

        try(FileWriter writer = new FileWriter("Test/testfil.txt");
            PrintWriter print = new PrintWriter(writer)){
            String s1 = u.printToFileIfActive(print,test.get(0));
            String s2 = test.get(0).getIdNumber()+", "+test.get(0).getName()+" tränade datum: "+LocalDate.now();
            Assertions.assertEquals(s1, s2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void saveMembersInList()  {
        List<Member> test = new ArrayList<>();
        try (Scanner scanner = new Scanner(testFil2)){
            Assertions.assertEquals(8, u.saveMembersInList(scanner, test).size());
            Assertions.assertNotEquals(9, u.saveMembersInList(scanner, test).size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
