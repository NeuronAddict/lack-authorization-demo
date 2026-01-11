package org.neuronaddict.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.neuronaddict.data.Profile;

import java.net.URI;

@Path("/profile")
@Authenticated
public class ProfileResource {

    @Inject
    JsonWebToken accessToken;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance profile(ProfileDTO profile);
        public static native TemplateInstance edit(ProfileDTO profile);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@PathParam("id") Long id) {
        Profile profile = Profile.findById(id);
        if (profile == null) {
            throw new NotFoundException("Profile not found for id " + id);
        }
        var currentProfileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.profile(currentProfileDTO);
    }

    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance edit(@PathParam("id") Long id) {
        Profile profile = Profile.findById(id);
        var currentProfileDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        return Templates.edit(currentProfileDTO);
    }

    @POST
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response update(@PathParam("id") Long id,
                         @FormParam("name") String name,
                         @FormParam("email") String email,
                         @FormParam("address") String address,
                         @FormParam("phone") String phone) {

        Profile profile = Profile.findById(id);
        profile.name = name;
        profile.email = email;
        profile.address = address;
        profile.phone = phone;
        profile.role = accessToken.getGroups().iterator().next();
        profile.persist();

        return Response.seeOther(URI.create("/profile/" + id)).build();
    }

}
