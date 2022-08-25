package m3.furama.service;

import m3.furama.model.Customer;
import m3.furama.repository.CustomerRepository;
import m3.furama.util.Page;
import m3.furama.util.Pageable;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
       return customerRepository.findAll();
    }

    @Override
    public void save() {

    }

    @Override
    public List<Customer> find(String q) {
        return null;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}