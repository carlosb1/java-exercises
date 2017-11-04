import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void should_delimiter_correctly_with_whitespaces () throws IOException {

        InputStream in = IOUtils.toInputStream("AB1 AD2 BC2", "UTF-8");
        List<TrainRoadPath> values = Utils.Parse(in);
        assertEquals(3,values.size());
        assertEquals("A",values.get(0).getSource());
        assertEquals("B",values.get(0).getTarget());
        assertEquals(1,values.get(0).getWeight());

    }
}
