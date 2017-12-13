package service;

import static org.junit.Assert.assertArrayEquals;

import com.news.util.Utility;
import org.apache.http.HttpException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PagingTest {

    @Test
    public void select() throws HttpException {

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> expected = Arrays.asList(4, 5, 6);

        assertArrayEquals(Utility.getPage(data, 2, 3).toArray(), expected.toArray());

    }

    @Test
    public void invalidPageSize() throws HttpException {

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        assertArrayEquals(expected.toArray(), Utility.getPage(data, 1, 50).toArray());

    }

}
