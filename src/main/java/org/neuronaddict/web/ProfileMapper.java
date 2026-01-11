package org.neuronaddict.web;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.neuronaddict.data.Profile;

@Mapper
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO profileToProfileDTO(Profile profile);

    Profile profileDTOToProfile(ProfileDTO profileDTO);
}
