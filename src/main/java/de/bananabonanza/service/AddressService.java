package de.bananabonanza.service;

import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.User;
import de.bananabonanza.respository.AddressRepository;
import de.bananabonanza.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Address> getAllAddressesByUserID(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with the id " + id + " not found."));
        return addressRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public Address updateAddress(Address _address) {
        Address existingAddress = getAddressById(_address.getId()).orElseThrow(() -> new IllegalArgumentException("Address with the id " + _address.getId() + " not found."));

        existingAddress.setCountry(_address.getCountry());
        existingAddress.setPostalCode(_address.getPostalCode());
        existingAddress.setCity(_address.getCity());
        existingAddress.setStreet(_address.getStreet());
        existingAddress.setHousenumber(_address.getHousenumber());

        return addressRepository.save(existingAddress);
    }

    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
