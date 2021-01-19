package net.codejava.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> listAll(){
        List<Customer> list = (List<Customer>) repo.findAll();
        //System.out.println(list.get(0).getName());
        return list;
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

    public Customer get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Customer> search(String keyword) {
        return repo.search(keyword);
    }

}
