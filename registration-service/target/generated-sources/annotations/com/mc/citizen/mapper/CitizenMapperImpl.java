package com.mc.citizen.mapper;

import com.mc.citizen.model.Citizen;
import com.mc.citizen.model.dto.CitizenRequestDto;
import com.mc.citizen.model.dto.CitizenResponseDto;
import com.mc.citizen.model.util.Address;
import com.mc.citizen.model.util.PhoneNumber;
import com.mc.citizen.model.util.Title;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-08T11:26:41+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CitizenMapperImpl implements CitizenMapper {

    @Override
    public CitizenResponseDto toResponseDto(Citizen citizen) {
        if ( citizen == null ) {
            return null;
        }

        CitizenResponseDto.CitizenResponseDtoBuilder citizenResponseDto = CitizenResponseDto.builder();

        citizenResponseDto.id( citizen.getId() );
        citizenResponseDto.createdAt( citizen.getCreatedAt() );
        citizenResponseDto.updatedAt( citizen.getUpdatedAt() );
        Set<Title> set = citizen.getTitles();
        if ( set != null ) {
            citizenResponseDto.titles( new LinkedHashSet<Title>( set ) );
        }
        citizenResponseDto.gender( citizen.getGender() );
        citizenResponseDto.firstName( citizen.getFirstName() );
        citizenResponseDto.lastName( citizen.getLastName() );
        citizenResponseDto.birthDate( citizen.getBirthDate() );
        citizenResponseDto.birthPlace( citizen.getBirthPlace() );
        citizenResponseDto.email( citizen.getEmail() );
        Set<PhoneNumber> set1 = citizen.getPhoneNumbers();
        if ( set1 != null ) {
            citizenResponseDto.phoneNumbers( new LinkedHashSet<PhoneNumber>( set1 ) );
        }
        citizenResponseDto.maritalStatus( citizen.getMaritalStatus() );
        Set<Address> set2 = citizen.getAddresses();
        if ( set2 != null ) {
            citizenResponseDto.addresses( new LinkedHashSet<Address>( set2 ) );
        }

        return citizenResponseDto.build();
    }

    @Override
    public Citizen toEntity(CitizenRequestDto citizenRequestDto) {
        if ( citizenRequestDto == null ) {
            return null;
        }

        Citizen.CitizenBuilder citizen = Citizen.builder();

        Set<Title> set = citizenRequestDto.titles();
        if ( set != null ) {
            citizen.titles( new LinkedHashSet<Title>( set ) );
        }
        citizen.gender( citizenRequestDto.gender() );
        citizen.firstName( citizenRequestDto.firstName() );
        citizen.lastName( citizenRequestDto.lastName() );
        citizen.birthDate( citizenRequestDto.birthDate() );
        citizen.birthPlace( citizenRequestDto.birthPlace() );
        citizen.email( citizenRequestDto.email() );
        Set<PhoneNumber> set1 = citizenRequestDto.phoneNumbers();
        if ( set1 != null ) {
            citizen.phoneNumbers( new LinkedHashSet<PhoneNumber>( set1 ) );
        }
        citizen.maritalStatus( citizenRequestDto.maritalStatus() );
        Set<Address> set2 = citizenRequestDto.addresses();
        if ( set2 != null ) {
            citizen.addresses( new LinkedHashSet<Address>( set2 ) );
        }

        return citizen.build();
    }

    @Override
    public List<CitizenResponseDto> toResponseDtos(List<Citizen> citizens) {
        if ( citizens == null ) {
            return null;
        }

        List<CitizenResponseDto> list = new ArrayList<CitizenResponseDto>( citizens.size() );
        for ( Citizen citizen : citizens ) {
            list.add( toResponseDto( citizen ) );
        }

        return list;
    }
}
