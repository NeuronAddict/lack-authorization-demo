package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.neuronaddict.data.Profile;

import java.net.URI;

@Path("/eval")
@Authenticated
public class EvalResource {

    @Inject
    JsonWebToken accessToken;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance eval(ProfileDTO profile, JsonWebToken accessToken);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public io.quarkus.qute.TemplateInstance getEval(@PathParam("id") Long id) {
        Profile profile = Profile.findById(id);
        if (profile == null) {
            throw new NotFoundException("Profile not found for id " + id);
        }
        var currentProfileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.eval(currentProfileDTO, accessToken);
    }
}
