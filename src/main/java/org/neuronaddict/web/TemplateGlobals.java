package org.neuronaddict.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.UriInfo;

@RequestScoped
public class TemplateGlobals {

    @Inject
    UriInfo uriInfo;

    @Produces
    @Named("currentPath")
    public String currentPath() {
        return uriInfo.getPath();
    }
}
