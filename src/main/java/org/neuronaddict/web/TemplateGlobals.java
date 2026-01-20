package org.neuronaddict.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.UriInfo;
import org.neuronaddict.auth.IdentityProvider;

@RequestScoped
public class TemplateGlobals {

    @Inject
    IdentityProvider identityProvider;

    @Inject
    UriInfo uriInfo;

    @Produces
    @Named("currentPath")
    public String currentPath() {
        return uriInfo.getPath();
    }

    @Produces
    @Named("currentUser")
    public String currentUser() {
        return identityProvider.currentName();
    }
}
