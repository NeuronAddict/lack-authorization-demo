package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.neuronaddict.auth.IdentityProvider;
import org.neuronaddict.data.Profile;

@Path("/")
public class HomeResource {

    @Inject
    IdentityProvider identityProvider;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance home(ProfileDTO profile);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() {
        if (identityProvider.currentName() == null) {
            return Templates.home(null);
        }
        Profile profile = Profile.findByName(identityProvider.currentName());
        if (profile == null) {
            throw new NotFoundException("Profile not found for name " + identityProvider.currentName());
        }
        var profileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.home(profileDTO);
    }
}
