package task.security;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class Secrets {

    public static final Map<String, String> USERS = ImmutableMap.of(
            "user", "pass",
            "admin", "admin"
    );
}
