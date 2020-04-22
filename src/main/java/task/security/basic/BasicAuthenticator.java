package task.security.basic;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import task.api.dto.UserDto;
import task.security.Secrets;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserDto> {

    @Override
    public Optional<UserDto> authenticate(BasicCredentials credentials) {
        if (isValidCredentials(credentials)) {
            return Optional.of(new UserDto(credentials.getUsername(), credentials.getPassword()));
        }
        throw new WebApplicationException(Response.status(401).entity("Invalid authentication "
                + "credentials.").build());
    }

    private boolean isValidCredentials(BasicCredentials credentials) {
        return Secrets.USERS.containsKey(credentials.getUsername())
                && Secrets.USERS.get(credentials.getUsername()).equals(credentials.getPassword());
    }
}
