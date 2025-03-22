package kr.co.cyberline.cmm.util.lang;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class CylCollectionUtil extends CollectionUtils {
    public static <T> List<T> copy(List<T> list) {
        if (isNotEmpty(list))
            return new ArrayList<>(list);
        return null;
    }
}
