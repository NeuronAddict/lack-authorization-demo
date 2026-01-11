package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.neuronaddict.data.Profile;

@Path("/")
public class HomeResource {

    @Inject
    JsonWebToken accessToken;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance home(ProfileDTO profile, JsonWebToken accessToken);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getEval() {
        if (accessToken.getName() == null) {
            return Templates.home(null, null);
        }
        Profile profile = Profile.findByName(accessToken.getName());
        if (profile == null) {
            throw new NotFoundException("Profile not found for name " + accessToken.getName());
        }
        var profileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.home(profileDTO, accessToken);
    }
}
