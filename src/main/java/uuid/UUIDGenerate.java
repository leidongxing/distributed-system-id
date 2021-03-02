package uuid;

import java.util.UUID;

/**
 * @author LeiDongxing
 * created on 2021/3/2
 */
public class UUIDGenerate {

    public static String getId() {
        return UUID.randomUUID().toString();
    }
}
