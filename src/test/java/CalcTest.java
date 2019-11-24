import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;
import packetyk.OperationType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CalcTest {

    @Test
    public void dividingZero() {
        double asDouble = OperationType.DIVISION.getOperator().applyAsDouble(3, 0);
        Double aThrow = Optional.of(asDouble).orElseThrow(IllegalArgumentException::new);
        System.out.println(aThrow);
        Assert.assertEquals("You are not dividing on zero!", "Infinity", aThrow.toString());
    }

    @Test
    public void correctResult(){
        double left = 4;
        double right = 72;
        double multiplication = (4 * 72);
        double asDouble = OperationType.MULTIPLICATION.getOperator().applyAsDouble(left, right);
        Optional<Double> passOpt = Optional.of(asDouble);
        boolean correctMultiplication = passOpt.filter(mult -> mult.equals(multiplication)).isPresent();
        Assert.assertTrue("There is an incorrect multiplication!", correctMultiplication);
    }

    @Test(timeout = 1000)
    public void infinity() {
        while (true);
    }

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    @Rule
    public final Timeout timeout = new Timeout(1000);
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Ignore
    @Test
    public void anotherInfinity() {
        while (true);
    }
    @Test
    public void testFileWriting() throws IOException {
        final File log = folder.newFile("debug.log");
        final FileWriter logWriter = new FileWriter(log);
        logWriter.append("Hello, ");
        logWriter.append("World!!!");
        logWriter.flush();
        logWriter.close();
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "baeldung";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional
                .map(String::length)
                .orElse(0);
        Assert.assertEquals(8, len);
    }
}
