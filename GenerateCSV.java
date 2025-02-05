import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateCSV{
    public static void main(String[] args) {
        int numTuples = 500;
        int maxS = 5;
        int maxD = 5;
        //int D = 40;

        try (FileWriter csvWriter = new FileWriter("V_500_24.csv")) {
            csvWriter.append("s,d,D\n");

            Random random = new Random();
            for (int i = 0; i < numTuples; i++) {
                int s = random.nextInt(maxS) + 1;
                int d = random.nextInt(maxD) + 1;
                int x = random.nextInt(8) +1;
                csvWriter.append(s + "," + d + "," + (20 * x) + "\n");
            }

            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
