/**
 *
 *  @author qinxue.pan E-mail: xue@acrcloud.com
 *  @version 1.0.0
 *  @create 2015.10.01
 *  
 **/

import com.acrcloud.utils.ACRCloudExtrTool;
import com.acrcloud.utils.ACRCloudRecognizer;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class Test {

    private final Map<String, Object> config = new HashMap<String, Object>();
    private ACRCloudRecognizer acrCloudRecognizer;

    @Before
    public void init(){
        config.put("host", "identify-eu-west-1.acrcloud.com");
        config.put("access_key", "50bb27b6cd8b2088ba492e36c6ce2e95");
        config.put("access_secret", "SPqoNSExNoPsH9CIqSHiEuGepYKEuK7YeQDpgVN2");
        config.put("rec_type", ACRCloudRecognizer.RecognizerType.AUDIO); // AUDIO, HUMMING, BOTH
        config.put("debug", false);
        config.put("timeout", 10); // seconds

        acrCloudRecognizer = new ACRCloudRecognizer(config);
    }

    @org.junit.Test
    public void recognitionByFilePath() {
        String result = acrCloudRecognizer.recognizeByFile("/Users/warp/Desktop/Musique/1.mp3", 0);
        System.out.println(result);
        int fileDurationMS = ACRCloudExtrTool.getDurationMSByFile("/Users/warp/Desktop/Musique/1.mp3");
        System.out.println("duration_ms = "+fileDurationMS);
        assertEquals("Expected musing duration in MS : ", 281520, fileDurationMS);
        assertTrue(result.contains("Despacito"));
    }

}
