package de.bananabonanza.service;

import de.bananabonanza.entity.Address;
import de.bananabonanza.respository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> updateAddress(Address updatedAddress, Long id) {
        return addressRepository.findById(id)
                .map(address -> {
                    address.setCountry(updatedAddress.getCountry());
                    address.setPostalCode(updatedAddress.getPostalCode());
                    address.setCity(updatedAddress.getCity());
                    address.setStreet(updatedAddress.getStreet());
                    address.setHousenumber(updatedAddress.getHousenumber());
                    addressRepository.save(address);
                    return address;
                });
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
