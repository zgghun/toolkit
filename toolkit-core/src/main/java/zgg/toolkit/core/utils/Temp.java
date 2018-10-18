package zgg.toolkit.core.utils;

/**
 * Created by zgg on 2018/10/18
 */
public class Temp {
    private static Temp ourInstance = new Temp();

    public static Temp getInstance() {
        return ourInstance;
    }

    private Temp() {
    }
}
