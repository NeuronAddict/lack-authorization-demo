package org.neuronaddict.web;


import org.mapstruct.factory.Mappers;
import org.neuronaddict.data.Profile;

public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO profileToProfileDTO(Profile profile);

    Profile profileDTOToProfile(ProfileDTO profileDTO);
}
