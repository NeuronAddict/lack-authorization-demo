package org.neuronaddict;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/profile")
public class ProfileResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance profile(Profile profile);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.profile(new Profile(
            "John Doe", 
            "john.doe@example.com", 
            "123 Digital Ave, Cyber City", 
            "+1 234 567 890", 
            true, 
            "Administrator"
        ));
    }

}
