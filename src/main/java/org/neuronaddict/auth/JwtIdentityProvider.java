package org.neuronaddict.auth;

import io.quarkus.arc.profile.UnlessBuildProfile;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@UnlessBuildProfile("noauth")
@RequestScoped
public class JwtIdentityProvider implements IdentityProvider {

    @Inject
    JsonWebToken accessToken;

    @Override
    public String currentName() {
        return accessToken.getName();
    }

    @Override
    public String role() {
        return accessToken.getGroups().iterator().next();
    }
}
