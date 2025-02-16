package com.doit_well.customerservice.service;

import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.entity.UpdateCustomerRequest;
import com.doit_well.customerservice.entity.Users;
import com.doit_well.customerservice.exception.CustomerException;
import com.doit_well.customerservice.exception.UsersException;
import com.doit_well.customerservice.mapper.CustomerMapper;
import com.doit_well.customerservice.repository.CustomerRepository;
import com.doit_well.customerservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private CustomerMapper customerMapper;


    @Override
    public CustomerDto createCustomer(CreateCustomerRequest request) {

        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        Users user = null;
        if (request.createAccount()){
            user = createUserAccount(customer);
        }
        assert user != null;
        customer.setHasAccount(true);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(UpdateCustomerRequest request) {
        if(Objects.isNull(request)) throw new CustomerException("The update request body is null");

        Customer customer = findCustomerById(request.id());

        if(!Objects.isNull(request.firstname()) && !Objects.equals(request.firstname(),customer.getFirstname())){
            customer.setFirstname(request.firstname());
        }
        if(!Objects.isNull(request.lastname()) && !Objects.equals(request.lastname(), customer.getLastname())){
            customer.setLastname(request.lastname());
        }
        if(!Objects.isNull(request.country()) && !Objects.equals(request.country(), customer.getCountry())){
            customer.setCountry(request.country());
        }
        if(!Objects.isNull(request.city()) && !Objects.equals(request.city(), customer.getCity())){
            customer.setCity(request.city());
        }
        if(!Objects.isNull(request.address()) && !Objects.equals(request.address(), customer.getAddress())){
            customer.setAddress(request.address());
        }
        if(!Objects.isNull(request.tel()) && !Objects.equals(request.tel(), customer.getTel())){
            customer.setTel(request.tel());
        }
        if(!Objects.isNull(request.cniImage()) && !Objects.equals(request.cniImage(), customer.getCniImage())){
            customer.setCniImage(request.cniImage());
        }
        if(!Objects.isNull(request.cniNumber()) && !Objects.equals(request.cniNumber(), customer.getCniNumber())){
            customer.setCniNumber(request.cniNumber());
        }
        if(!Objects.isNull(request.profileImage()) && !Objects.equals(request.profileImage(), customer.getProfileImage())){
            customer.setProfileImage(request.profileImage());
        }
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto getCustomer(Integer id) {
        var customer = findCustomerById(id);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        var customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> customerMapper.toDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteCustomer(Integer customerId) {
        var customer = findCustomerById(customerId);
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public Users  createUserAccount(Customer customer) {
        var user = customerMapper.toUser(customer);
        Optional<Users> optUser = userRepository.findByEmail(customer.getEmail());
        if (optUser.isPresent())
            throw new UsersException(String.format("A user with email %s already exist",customer.getEmail()));
        return userRepository.save(user);
    }

    private Customer findCustomerById(Integer id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerException(String.format("Customer with id %s not found", id)));
    }

}
