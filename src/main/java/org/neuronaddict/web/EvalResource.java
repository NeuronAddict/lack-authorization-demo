package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.neuronaddict.data.Profile;

@Path("/eval")
@Authenticated
public class EvalResource {

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance eval(ProfileDTO profile);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public io.quarkus.qute.TemplateInstance eval(@PathParam("id") Long id) {
        Profile profile = Profile.findById(id);
        if (profile == null) {
            throw new NotFoundException("Profile not found for id " + id);
        }
        var currentProfileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.eval(currentProfileDTO);
    }
}
