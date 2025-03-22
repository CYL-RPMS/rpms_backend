package kr.co.cyberline.cmm.util;

import java.io.Closeable;
import kr.co.cyberline.cmm.util.lang.CylArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CylResourceCloseHelper {
    private static Logger logger = LoggerFactory.getLogger(CylResourceCloseHelper.class);

    public static void close(Closeable... resources) {
        if (CylArrayUtil.isNotEmpty((Object[])resources))
            for (Closeable resource : resources) {
                if (resource != null)
                    try {
                        resource.close();
                    } catch (Exception ignore) {
                        logger.error("해당 객체 close error! >> " + ignore.getMessage());
                    }
            }
    }
}
