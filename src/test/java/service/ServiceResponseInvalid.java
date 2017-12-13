package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.news.core.HeadlineService;
import com.news.ext.ServiceCaller;
import org.apache.http.HttpException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ServiceResponseInvalid {

    ServiceCaller mockedCaller;
    HeadlineService service;
    Map<String, String> params = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        mockedCaller = mock(ServiceCaller.class);
        service = new HeadlineService(mockedCaller);
    }

    @Test
    public void invalidJSON() throws HttpException {
        when(mockedCaller.sendRequest(service.getServiceURL())).thenReturn("%%%");
        service = new HeadlineService(mockedCaller);
        Map<String, String> map = service.getNews(params, null, null);
        assertEquals("500", map.get("code"));
    }
}
