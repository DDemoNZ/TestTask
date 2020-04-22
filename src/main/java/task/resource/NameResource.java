package task.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import task.api.dto.NamesResponseDto;
import task.api.dto.UserDto;
import task.db.UserDao;

@Path("/name")
public class NameResource {

    private UserDao userDao;

    public NameResource(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
    Validation by annotations. But i don't know how to throw own response instead of validation
     errors response.

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(@Valid User user) {
        userDao.saveUserName(user.getName());
        return Response.status(200).entity(String.format("Hello %s. Next year you will be %d.",
                user.getName(), user.getAge() + 1)).build();
    }
    */

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(UserDto userDto) {
        if (!(userDto.getAge() > 0) && userDto.getName() == null || userDto.getName() != null
                && !(userDto.getName().length() > 0) && userDto.getPassword() == null
                || userDto.getPassword() != null && !(userDto.getPassword().length() > 0)) {
            return Response.status(400).entity("Badly formatted.").build();
        }
        userDao.saveUserName(userDto.getName());
        return Response.status(200).entity(String.format("Hello %s. Next year you will be %d.",
                userDto.getName(), userDto.getAge() + 1)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNames() {
        return Response.status(200).entity(new NamesResponseDto(userDao.getNames())).build();
    }
}
