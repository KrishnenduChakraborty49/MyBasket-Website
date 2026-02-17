package com.jpa.concepts.jpa_concepts;

import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaConceptsApplicationTests {

//	@Test
//	void contextLoads() {
//	}
    @Autowired
    private UserRepository userRepository;
    @Test
    public void orphantest(){
        User user=userRepository.findById(0).get();
        System.out.println("user name is"+user.getName());
        user.getAddresses().clear();
        userRepository.save(user);
        System.out.println("ok done");

    }

}
