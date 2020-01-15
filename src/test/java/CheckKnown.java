/**
 *
 *  @author qinxue.pan E-mail: xue@acrcloud.com
 *  @version 1.0.0
 *  @create 2015.10.01
 *  
 **/

import com.acrcloud.utils.ACRCloudRecognizer;
import org.junit.Before;
import java.io.File;
import java.util.*;

public class CheckKnown {

    private final Map<String, Object> config = new HashMap<String, Object>();
    private ACRCloudRecognizer acrCloudRecognizer;
    private final String resourcesPath = new File("").getAbsolutePath() + "/src/test/resources2/";
    private File resourceFolder = null;

    @Before
    public void init(){
        resourceFolder = new File(resourcesPath);
    }

    @org.junit.Test
    public void deleteKnownMusic() throws InterruptedException {

        final boolean DELETE_AFTER_SEARCH = true;
        final String[] fileNames = resourceFolder.list();
        final List<Integer> secondToScan = Arrays.asList(0, 30, 60);
        final List<String> filesToDelete = scanFiles(secondToScan, fileNames);

        System.out.println("############ END PROCESSING FILES ############");
        System.out.println("############ FILES TO DELETE LIST ############");
        displayFilesToDelete(filesToDelete);
        removeFiles(DELETE_AFTER_SEARCH, filesToDelete);
    }

    private List<String> scanFiles(List<Integer> secondList, String[] files) throws InterruptedException {
        final List<String> filesToDelete = new ArrayList<String>();

        for(String file:files){
            System.out.println("Computing : " + file);
            boolean musicFound = false;
            final String filePath = resourcesPath + file;
            int secondListIndex = 0;
            while (!musicFound) {
                System.out.println("Scanning at : " + secondList.get(secondListIndex) + " seconds");
                String result = Utils.acrCloudRecognizer().recognizeByFile(filePath, secondList.get(secondListIndex));
                apiResponse(result, filesToDelete);
                if (result.contains("metadata")) {
                    System.out.println("[ " + file + " ]  -------> MUSIC SPOTTED");
                    filesToDelete.add(filePath);
                    musicFound = true;
                }
                Thread.sleep(500);
                secondListIndex ++;
                if(secondListIndex == secondList.size()){
                    System.out.println(file + " is clean, next music");
                    break;
                }
            }
        }
        return filesToDelete;
    }

    private void apiResponse(String response, List<String> filesToDelete) {
        System.out.println(response);
        if (response.contains("3003")) {
            System.out.println(response);
            System.out.println("FATAL ERROR, REMOVING ALREADY SPOTTED FILES");
            removeFiles(true, filesToDelete);
            throw new RuntimeException("API CALL LIMIT EXCEEDED");
        }
        if (response.contains("2005")) {
            System.out.println(response);
            System.out.println("Can not decode audio data");
            removeFiles(true, filesToDelete);
        }
    }

    void displayFilesToDelete(List<String> file){
        for (String f:file) {
            System.out.println(f);
        }
    }

    void removeFiles(boolean remove, List<String> file){
        if(remove) {
            for (String f : file) {
                System.out.println("REMOVING : " + f);
                new File(f).delete();
            }
        }
    }
}