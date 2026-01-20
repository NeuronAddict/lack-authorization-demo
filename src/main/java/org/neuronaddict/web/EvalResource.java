package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.neuronaddict.auth.IdentityProvider;
import org.neuronaddict.data.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/eval")
public class EvalResource {

    @Inject
    IdentityProvider identityProvider;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance eval(ProfileDTO profile);
    }

    @GET
    public Response eval() throws URISyntaxException {
        return Response.seeOther(new URI("/eval/" + identityProvider.currentId())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public io.quarkus.qute.TemplateInstance evalById(@PathParam("id") Long id) {
        Profile profile = Profile.findById(id);
        if (profile == null) {
            throw new NotFoundException("Profile not found for id " + id);
        }
        var currentProfileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.eval(currentProfileDTO);
    }
}
