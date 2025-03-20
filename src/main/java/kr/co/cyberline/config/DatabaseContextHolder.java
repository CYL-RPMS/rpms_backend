package kr.co.cyberline.config;

public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static String defaultDbType;

    public static void setDefaultDbType(String dbType) {
        defaultDbType = dbType;
    }

    public static void setDatabaseType(String databaseType) {
        contextHolder.set(databaseType);
    }

    public static String getDatabaseType() {
        return contextHolder.get() != null ? contextHolder.get() : defaultDbType;
    }

    public static void clear() {
        contextHolder.remove();
    }
}

class DatabaseContextInitializer {
    public DatabaseContextInitializer(String defaultDbType) {
        DatabaseContextHolder.setDefaultDbType(defaultDbType);
    }
}
