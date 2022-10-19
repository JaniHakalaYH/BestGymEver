import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program extends UserInputAndHandling{
    protected List<Member> member = new ArrayList<>();
    Program() {

        Path utFil = Paths.get("src/TillTränare.txt");
        Path inFil = Paths.get("src/Members.txt");

        try(FileWriter writer = new FileWriter(utFil.toFile(), true);
            PrintWriter print = new PrintWriter(writer);
            Scanner scan = new Scanner(inFil)) {

            saveMembersInList(scan, member);
            writeOutputToReceptionAndTrainer(print,  member);

        } catch (FileNotFoundException e) {
            System.out.println("Hittade ingen fil!");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något blev fel!");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
