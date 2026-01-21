package org.neuronaddict.auth;

import org.neuronaddict.data.Profile;

public interface IdentityProvider {

    default int currentId() {
        return Profile.findByName(currentName()).id.intValue();
    }

    String currentName();

    String role();
}
