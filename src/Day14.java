import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day14/testdata.txt");
        Scanner scanner = new Scanner(input);
        ArrayList<String> instructions = new ArrayList<>();

        //pass the first line to a string
        String startTemplate = scanner.nextLine();
        //skip the blank space before instructions
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            //parse data
            String line = scanner.nextLine();
            instructions.add(line);
        }
        scanner.close();

        System.out.println("here");
    }
}
