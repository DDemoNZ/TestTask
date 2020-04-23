package task.resource;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import task.api.dto.RequestSmtpDto;
import task.smtp.SmtpService;

@Path("/message")
public class SmtpResources {

    private SmtpService smtpService;

    public SmtpResources(SmtpService smtpService) {
        this.smtpService = smtpService;
    }

    @PUT
    @Path("/smtp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(@Valid RequestSmtpDto requestSmtpDto) {
        try {
            smtpService.send(requestSmtpDto.getEmail(), requestSmtpDto.getMessage());
            return Response.status(200).entity(String.format("Message \"%s\" sent to %s.",
                    requestSmtpDto.getMessage(), requestSmtpDto.getEmail())).build();
        } catch (MessagingException e) {
            return Response.status(400).entity("Bad request").build();
        }
    }
}
