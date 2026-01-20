package org.neuronaddict.auth;

import io.quarkus.arc.profile.IfBuildProfile;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@IfBuildProfile("noauth")
@RequestScoped
public class HeaderIdentityProvider implements IdentityProvider {

    @Inject
    HttpServerRequest request;

    @Override
    public String currentName() {
        return request.getHeader("X-User");
    }
}
