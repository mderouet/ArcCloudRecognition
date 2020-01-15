import com.acrcloud.utils.ACRCloudRecognizer;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static ACRCloudRecognizer acrCloudRecognizer = null;
    private static Map<String, Object> config = null;;

    public static synchronized ACRCloudRecognizer acrCloudRecognizer() {
        if(acrCloudRecognizer == null) {
            acrCloudRecognizer = new ACRCloudRecognizer(Utils.getConfig());
        }
        return acrCloudRecognizer;
    }

    private static Map<String, Object> getConfig() {
        if(config == null) {
            config = new HashMap<String, Object>();
            config.put("host", "identify-eu-west-1.acrcloud.com");
            config.put("access_key", "8af3ee689dd830f822a831c9b4201c8c");
            config.put("access_secret", "uG5guWmINGaWb7DFOHKveQlfPDAgtc3iY46oPWYk");
            config.put("rec_type", ACRCloudRecognizer.RecognizerType.AUDIO); // AUDIO, HUMMING, BOTH
            config.put("debug", false);
            config.put("timeout", 10); // seconds
        }
        return config;
    }
}

// martin.derouet0@gmail.com
// key: 50bb27b6cd8b2088ba492e36c6ce2e95
// secret : SPqoNSExNoPsH9CIqSHiEuGepYKEuK7YeQDpgVN2

// milio.keyondre@opka.org password
// identify-eu-west-1.acrcloud.com
//key: 5ec2c9c17555ee8e7e499525e6b08dbd
//secret: KIvvZcEZObvpXolYbLJ9b1PiptGA1ZTy65dquztc

// colonieu.clement@mail.com
//key 8af3ee689dd830f822a831c9b4201c8c
//secret uG5guWmINGaWb7DFOHKveQlfPDAgtc3iY46oPWYk