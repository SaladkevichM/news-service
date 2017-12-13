package service;

import com.news.util.Utility;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PropertyAccessTest {

    @Test
    public void test() {
        boolean opened = true;
        try (InputStream input =
                Utility.class.getClassLoader().getResourceAsStream("api.properties")) {
        } catch (IOException ex) {
            opened = false;
        }
        Assert.assertTrue(opened);
    }

}
