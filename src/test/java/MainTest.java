import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        String suiteFileName = args[0];

        List<String> suites = new ArrayList<>();
        suites.add(suiteFileName);

        testNG.setTestSuites(suites);
        testNG.run();
    }
}
