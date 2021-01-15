package fr.training.spring.library.exposition.address;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.exposition.common.AbstractAdapter;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter extends AbstractAdapter<AddressDto, Address> {
    @Override
    public AddressDto mapToDto(Address entity) {
        final AddressDto addressDto = new AddressDto();
        addressDto.setCity(entity.getCity());
        addressDto.setNumber(entity.getNumber());
        addressDto.setStreet(entity.getStreet());
        addressDto.setZipcode(entity.getZipcode());
        return addressDto;
    }

    @Override
    public Address mapToEntity(AddressDto dto) {
        final Address address = new Address();
        address.setCity(dto.getCity());
        address.setNumber(dto.getNumber());
        address.setStreet(dto.getStreet());
        address.setZipcode(dto.getZipcode());
        return address;
    }
}
