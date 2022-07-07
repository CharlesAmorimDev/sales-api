package org.example.sales.service;

import org.example.sales.exception.BusinessRuleException;
import org.example.sales.model.Customer;
import org.example.sales.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    CustomerRepository repository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = repository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado"));

        return User
                .builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .roles(customer.getRole())
                .build();
    }

    public Customer register(Customer customer) {
        if(repository.findByUsername(customer.getUsername()).isPresent()) {
            throw new BusinessRuleException("Usuário existente na base de dados");
        }
        customer.setPassword(encoder.encode(customer.getPassword()));
        return repository.save(customer);
    }

    public void update(Long id, Customer customer) {
        repository.findById(id)
                .map(customerUpdate -> {
                    customer.setId(customerUpdate.getId());
                    customer.setPassword(encoder.encode(customerUpdate.getPassword()));
                    repository.save(customer);
                    return customerUpdate;
                }).orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}

