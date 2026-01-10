package org.neuronaddict;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/profile")
public class ProfileResource {

    private static ProfileDTO currentProfileDTO = new ProfileDTO(
        "John Doe", 
        "john.doe@example.com", 
        "123 Digital Ave, Cyber City", 
        "+1 234 567 890", 
        true, 
        "Administrator"
    );

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance profile(ProfileDTO profile);
        public static native TemplateInstance edit(ProfileDTO profile);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.profile(currentProfileDTO);
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance edit() {
        return Templates.edit(currentProfileDTO);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response update(@FormParam("name") String name,
                         @FormParam("email") String email,
                         @FormParam("address") String address,
                         @FormParam("phone") String phone,
                         @FormParam("role") String role) {
        
        currentProfileDTO = new ProfileDTO(name, email, address, phone, currentProfileDTO.active(), role);
        
        return Response.seeOther(URI.create("/profile")).build();
    }

}
