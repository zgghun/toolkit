package zgg.toolkit.system.constant;

/**
 * Created by zgg on 2018/11/15
 * 权限常量,方便引用
 */

public class PerConst {
    private static final String CREATE = ":create";
    private static final String READ = ":read";
    private static final String UPDATE = ":update";
    private static final String DELETE = ":delete";

    public static final String USER_VIEW = "user" + READ;
    public static final String USER_SAVE = "user" + CREATE;
    public static final String USER_ENABLE = "user:enable";
    public static final String USER_SET_ROLE = "user:setRole";

    // role
    public static final String ROLE_VIEW = "role:view";
    public static final String ROLE_SAVE = "role:save";
    public static final String ROLE_ENABLE = "role:enable";
    public static final String ROLE_DEL = "role:del";
    public static final String ROLE_SET_PER = "role:setPer";

    // permission
    public static final String PER_VIEW = "per:view";
    public static final String PER_SAVE = "per:save";
    public static final String PER_DEL = "per:del";
    public static final String PER_MODULE_SAVE = "per:moduleSave";
    public static final String PER_MODULE_ENABLE = "per:moduleEnable";
    public static final String PER_MODULE_DEL = "per:moduleDel";

}
