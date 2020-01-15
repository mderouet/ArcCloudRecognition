/**
 *
 *  @author qinxue.pan E-mail: xue@acrcloud.com
 *  @version 1.0.0
 *  @create 2015.10.01
 *  
 **/

import com.acrcloud.utils.ACRCloudExtrTool;
import com.acrcloud.utils.ACRCloudRecognizer;
import org.junit.Before;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class Recognition {

    private ACRCloudRecognizer acrCloudRecognizer;
    private final String resourcesPath = new File("").getAbsolutePath() + "/src/test/resources/";
    private File resourceFolder = null;

    @Before
    public void init(){
        resourceFolder = new File(resourcesPath);
    }

    @org.junit.Test
    public void recognitionByFilePath() {
        final String music = "despacito.mp3";
        String result = Utils.acrCloudRecognizer().recognizeByFile(resourcesPath + music, 0);
        System.out.println(result);
        int fileDurationMS = ACRCloudExtrTool.getDurationMSByFile(resourcesPath + music);
        System.out.println("duration_ms = " + fileDurationMS);
        assertEquals("Expected musing duration in MS : ", 281520, fileDurationMS);
        assertTrue(result.contains("Despacito"));
    }
}