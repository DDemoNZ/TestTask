package task.db;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDao {

    @SqlQuery("SELECT name FROM names.names WHERE name IS NOT NULL")
    List<String> getNames();

    @SqlUpdate("INSERT INTO names.names (name) VALUES (:name)")
    void saveUserName(@Bind("name") String name);
    //
    //    @SqlQuery("SELECT * FROM names.names WHERE name = :name")
    //    Optional<UserDto> getByName(@Bind("name") String username);
}
