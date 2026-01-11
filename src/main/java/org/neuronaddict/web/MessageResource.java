package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.neuronaddict.data.Message;
import org.neuronaddict.data.Profile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Path("/messages")
public class MessageResource {

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance messages(Profile profile, List<Message> messages);
    }

    @GET
    @Path("/{profileId}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@PathParam("profileId") Long profileId) {
        Profile profile = Profile.findById(profileId);
        if (profile == null) {
            throw new NotFoundException();
        }
        List<Message> messages = Message.findByProfile(profile);
        return Templates.messages(profile, messages);
    }

    @POST
    @Path("/{profileId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response post(@PathParam("profileId") Long profileId, @FormParam("content") String content) {
        Profile profile = Profile.findById(profileId);
        if (profile == null) {
            throw new NotFoundException();
        }

        Message message = new Message();
        message.profile = profile;
        message.content = content;
        message.timestamp = LocalDateTime.now();
        message.persist();

        return Response.seeOther(URI.create("/messages/" + profileId)).build();
    }
}
