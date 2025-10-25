package com.mc.citizen.mapper;

import com.mc.citizen.model.ApiAddress;
import com.mc.citizen.model.ApiAddressTyp;
import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import com.mc.citizen.model.ApiGender;
import com.mc.citizen.model.ApiMaritalStatus;
import com.mc.citizen.model.ApiPhone;
import com.mc.citizen.model.ApiPhoneType;
import com.mc.citizen.model.ApiTitle;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.model.util.Address;
import com.mc.citizen.model.util.AddressTyp;
import com.mc.citizen.model.util.Gender;
import com.mc.citizen.model.util.MaritalStatus;
import com.mc.citizen.model.util.Phone;
import com.mc.citizen.model.util.PhoneType;
import com.mc.citizen.model.util.Title;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T00:59:47+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CitizenMapperImpl implements CitizenMapper {

    @Override
    public Citizen toCitizen(ApiCitizenRequest apiCitizenRequest) {
        if ( apiCitizenRequest == null ) {
            return null;
        }

        Citizen.CitizenBuilder citizen = Citizen.builder();

        citizen.titles( apiTitleListToTitleSet( apiCitizenRequest.getTitles() ) );
        citizen.gender( apiGenderToGender( apiCitizenRequest.getGender() ) );
        citizen.firstName( apiCitizenRequest.getFirstName() );
        citizen.lastName( apiCitizenRequest.getLastName() );
        citizen.birthDate( apiCitizenRequest.getBirthDate() );
        citizen.birthPlace( apiCitizenRequest.getBirthPlace() );
        citizen.email( apiCitizenRequest.getEmail() );
        citizen.phones( apiPhoneListToPhoneSet( apiCitizenRequest.getPhones() ) );
        citizen.maritalStatus( apiMaritalStatusToMaritalStatus( apiCitizenRequest.getMaritalStatus() ) );
        citizen.addresses( apiAddressListToAddressSet( apiCitizenRequest.getAddresses() ) );

        return citizen.build();
    }

    @Override
    public ApiCitizenResponse toApiResponse(Citizen citizen) {
        if ( citizen == null ) {
            return null;
        }

        ApiCitizenResponse apiCitizenResponse = new ApiCitizenResponse();

        apiCitizenResponse.setId( citizen.getId() );
        apiCitizenResponse.setCreatedAt( map( citizen.getCreatedAt() ) );
        apiCitizenResponse.setUpdatedAt( map( citizen.getUpdatedAt() ) );
        apiCitizenResponse.setTitles( titleSetToApiTitleList( citizen.getTitles() ) );
        apiCitizenResponse.setGender( genderToApiGender( citizen.getGender() ) );
        apiCitizenResponse.setFirstName( citizen.getFirstName() );
        apiCitizenResponse.setLastName( citizen.getLastName() );
        apiCitizenResponse.setBirthDate( citizen.getBirthDate() );
        apiCitizenResponse.setBirthPlace( citizen.getBirthPlace() );
        apiCitizenResponse.setEmail( citizen.getEmail() );
        apiCitizenResponse.setPhones( phoneSetToApiPhoneList( citizen.getPhones() ) );
        apiCitizenResponse.setMaritalStatus( maritalStatusToApiMaritalStatus( citizen.getMaritalStatus() ) );
        apiCitizenResponse.setAddresses( addressSetToApiAddressList( citizen.getAddresses() ) );

        return apiCitizenResponse;
    }

    @Override
    public List<ApiCitizenResponse> toApiResponses(List<Citizen> citizens) {
        if ( citizens == null ) {
            return null;
        }

        List<ApiCitizenResponse> list = new ArrayList<ApiCitizenResponse>( citizens.size() );
        for ( Citizen citizen : citizens ) {
            list.add( toApiResponse( citizen ) );
        }

        return list;
    }

    protected Title apiTitleToTitle(ApiTitle apiTitle) {
        if ( apiTitle == null ) {
            return null;
        }

        Title title;

        switch ( apiTitle ) {
            case PROF: title = Title.PROF;
            break;
            case DR: title = Title.DR;
            break;
            case ING: title = Title.ING;
            break;
            case HAB: title = Title.HAB;
            break;
            case PHD: title = Title.PHD;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + apiTitle );
        }

        return title;
    }

    protected Set<Title> apiTitleListToTitleSet(List<ApiTitle> list) {
        if ( list == null ) {
            return null;
        }

        Set<Title> set = LinkedHashSet.newLinkedHashSet( list.size() );
        for ( ApiTitle apiTitle : list ) {
            set.add( apiTitleToTitle( apiTitle ) );
        }

        return set;
    }

    protected Gender apiGenderToGender(ApiGender apiGender) {
        if ( apiGender == null ) {
            return null;
        }

        Gender gender;

        switch ( apiGender ) {
            case MALE: gender = Gender.MALE;
            break;
            case FEMALE: gender = Gender.FEMALE;
            break;
            case UNKNOWN: gender = Gender.UNKNOWN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + apiGender );
        }

        return gender;
    }

    protected PhoneType apiPhoneTypeToPhoneType(ApiPhoneType apiPhoneType) {
        if ( apiPhoneType == null ) {
            return null;
        }

        PhoneType phoneType;

        switch ( apiPhoneType ) {
            case MOBILE: phoneType = PhoneType.MOBILE;
            break;
            case PRIVATE: phoneType = PhoneType.PRIVATE;
            break;
            case WORK: phoneType = PhoneType.WORK;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + apiPhoneType );
        }

        return phoneType;
    }

    protected Phone apiPhoneToPhone(ApiPhone apiPhone) {
        if ( apiPhone == null ) {
            return null;
        }

        Phone.PhoneBuilder phone = Phone.builder();

        phone.number( apiPhone.getNumber() );
        phone.phoneType( apiPhoneTypeToPhoneType( apiPhone.getPhoneType() ) );

        return phone.build();
    }

    protected Set<Phone> apiPhoneListToPhoneSet(List<ApiPhone> list) {
        if ( list == null ) {
            return null;
        }

        Set<Phone> set = LinkedHashSet.newLinkedHashSet( list.size() );
        for ( ApiPhone apiPhone : list ) {
            set.add( apiPhoneToPhone( apiPhone ) );
        }

        return set;
    }

    protected MaritalStatus apiMaritalStatusToMaritalStatus(ApiMaritalStatus apiMaritalStatus) {
        if ( apiMaritalStatus == null ) {
            return null;
        }

        MaritalStatus maritalStatus;

        switch ( apiMaritalStatus ) {
            case SINGLE: maritalStatus = MaritalStatus.SINGLE;
            break;
            case MARRIED: maritalStatus = MaritalStatus.MARRIED;
            break;
            case DIVORCED: maritalStatus = MaritalStatus.DIVORCED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + apiMaritalStatus );
        }

        return maritalStatus;
    }

    protected AddressTyp apiAddressTypToAddressTyp(ApiAddressTyp apiAddressTyp) {
        if ( apiAddressTyp == null ) {
            return null;
        }

        AddressTyp addressTyp;

        switch ( apiAddressTyp ) {
            case MAIN: addressTyp = AddressTyp.MAIN;
            break;
            case SECONDARY: addressTyp = AddressTyp.SECONDARY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + apiAddressTyp );
        }

        return addressTyp;
    }

    protected Address apiAddressToAddress(ApiAddress apiAddress) {
        if ( apiAddress == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.type( apiAddressTypToAddressTyp( apiAddress.getType() ) );
        address.street( apiAddress.getStreet() );
        address.zip( apiAddress.getZip() );
        address.city( apiAddress.getCity() );

        return address.build();
    }

    protected Set<Address> apiAddressListToAddressSet(List<ApiAddress> list) {
        if ( list == null ) {
            return null;
        }

        Set<Address> set = LinkedHashSet.newLinkedHashSet( list.size() );
        for ( ApiAddress apiAddress : list ) {
            set.add( apiAddressToAddress( apiAddress ) );
        }

        return set;
    }

    protected ApiTitle titleToApiTitle(Title title) {
        if ( title == null ) {
            return null;
        }

        ApiTitle apiTitle;

        switch ( title ) {
            case PROF: apiTitle = ApiTitle.PROF;
            break;
            case DR: apiTitle = ApiTitle.DR;
            break;
            case ING: apiTitle = ApiTitle.ING;
            break;
            case HAB: apiTitle = ApiTitle.HAB;
            break;
            case PHD: apiTitle = ApiTitle.PHD;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + title );
        }

        return apiTitle;
    }

    protected List<ApiTitle> titleSetToApiTitleList(Set<Title> set) {
        if ( set == null ) {
            return null;
        }

        List<ApiTitle> list = new ArrayList<ApiTitle>( set.size() );
        for ( Title title : set ) {
            list.add( titleToApiTitle( title ) );
        }

        return list;
    }

    protected ApiGender genderToApiGender(Gender gender) {
        if ( gender == null ) {
            return null;
        }

        ApiGender apiGender;

        switch ( gender ) {
            case MALE: apiGender = ApiGender.MALE;
            break;
            case FEMALE: apiGender = ApiGender.FEMALE;
            break;
            case UNKNOWN: apiGender = ApiGender.UNKNOWN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + gender );
        }

        return apiGender;
    }

    protected ApiPhoneType phoneTypeToApiPhoneType(PhoneType phoneType) {
        if ( phoneType == null ) {
            return null;
        }

        ApiPhoneType apiPhoneType;

        switch ( phoneType ) {
            case MOBILE: apiPhoneType = ApiPhoneType.MOBILE;
            break;
            case PRIVATE: apiPhoneType = ApiPhoneType.PRIVATE;
            break;
            case WORK: apiPhoneType = ApiPhoneType.WORK;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + phoneType );
        }

        return apiPhoneType;
    }

    protected ApiPhone phoneToApiPhone(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        ApiPhone apiPhone = new ApiPhone();

        apiPhone.setNumber( phone.getNumber() );
        apiPhone.setPhoneType( phoneTypeToApiPhoneType( phone.getPhoneType() ) );

        return apiPhone;
    }

    protected List<ApiPhone> phoneSetToApiPhoneList(Set<Phone> set) {
        if ( set == null ) {
            return null;
        }

        List<ApiPhone> list = new ArrayList<ApiPhone>( set.size() );
        for ( Phone phone : set ) {
            list.add( phoneToApiPhone( phone ) );
        }

        return list;
    }

    protected ApiMaritalStatus maritalStatusToApiMaritalStatus(MaritalStatus maritalStatus) {
        if ( maritalStatus == null ) {
            return null;
        }

        ApiMaritalStatus apiMaritalStatus;

        switch ( maritalStatus ) {
            case SINGLE: apiMaritalStatus = ApiMaritalStatus.SINGLE;
            break;
            case MARRIED: apiMaritalStatus = ApiMaritalStatus.MARRIED;
            break;
            case DIVORCED: apiMaritalStatus = ApiMaritalStatus.DIVORCED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + maritalStatus );
        }

        return apiMaritalStatus;
    }

    protected ApiAddressTyp addressTypToApiAddressTyp(AddressTyp addressTyp) {
        if ( addressTyp == null ) {
            return null;
        }

        ApiAddressTyp apiAddressTyp;

        switch ( addressTyp ) {
            case MAIN: apiAddressTyp = ApiAddressTyp.MAIN;
            break;
            case SECONDARY: apiAddressTyp = ApiAddressTyp.SECONDARY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + addressTyp );
        }

        return apiAddressTyp;
    }

    protected ApiAddress addressToApiAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        ApiAddress apiAddress = new ApiAddress();

        apiAddress.setType( addressTypToApiAddressTyp( address.getType() ) );
        apiAddress.setStreet( address.getStreet() );
        apiAddress.setZip( address.getZip() );
        apiAddress.setCity( address.getCity() );

        return apiAddress;
    }

    protected List<ApiAddress> addressSetToApiAddressList(Set<Address> set) {
        if ( set == null ) {
            return null;
        }

        List<ApiAddress> list = new ArrayList<ApiAddress>( set.size() );
        for ( Address address : set ) {
            list.add( addressToApiAddress( address ) );
        }

        return list;
    }
}
