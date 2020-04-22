package task.resource;

import io.dropwizard.auth.Auth;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import task.api.dto.UserDto;

@Path("/auth")
public class AuthenticatedResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public final Response doLogin(@Auth UserDto userDto) {
        return Response.status(200).entity("Authorization success.").build();
    }
}
