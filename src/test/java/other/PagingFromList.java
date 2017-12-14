package other;

import static org.junit.Assert.assertTrue;

import com.news.util.Utility;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PagingFromList {

    @Test
    public void getPage_SomeRange_Equality() throws HttpException {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> expected = Arrays.asList(4, 5, 6);
        assertTrue(CollectionUtils.isEqualCollection(expected, Utility.getPage(data, 2, 3)));
    }

    @Test
    public void getPage_InvalidPageSize_Equality() throws HttpException {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(CollectionUtils.isEqualCollection(expected, Utility.getPage(data, 1, 50)));
    }

}
