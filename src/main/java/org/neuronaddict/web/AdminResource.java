package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import org.neuronaddict.auth.IdentityProvider;
import org.neuronaddict.data.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Path("/admin")
//@RolesAllowed("admin")
public class AdminResource {

    @Inject
    IdentityProvider identityProvider;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance admin(ProfileDTO profile, List<ProfileDTO> profiles);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance admin() {

        if (!"admin".equals(identityProvider.role())) {
            throw new WebApplicationException(403);
        }

        Profile profile = Profile.findByName(identityProvider.currentName());
        ProfileDTO profileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        List<Profile> profiles = Profile.listAll();
        List<ProfileDTO> profileDTOs = profiles.stream()
                .map(ProfileMapper.INSTANCE::profileToProfileDTO)
                .collect(Collectors.toList());

        return Templates.admin(profileDTO, profileDTOs);
    }
}
