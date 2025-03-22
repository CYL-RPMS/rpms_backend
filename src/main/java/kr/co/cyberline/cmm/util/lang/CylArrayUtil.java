package kr.co.cyberline.cmm.util.lang;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class CylArrayUtil extends ArrayUtils {
    public static <T> T[] copy(T[] arr) {
        if (isNotEmpty((Object[])arr))
            return Arrays.copyOf(arr, arr.length);
        return null;
    }
}
