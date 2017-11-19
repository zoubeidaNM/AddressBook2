package com.example.addressbook;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long>{
    List<Address> findAddressesByEmailContainsOrNameContainsOrPhoneContains(String str,String str1,String str2);
    List<Address> findAddressesByEmailContains(String str);
}
