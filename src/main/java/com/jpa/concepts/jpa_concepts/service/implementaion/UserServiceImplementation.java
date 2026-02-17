package com.jpa.concepts.jpa_concepts.service.implementaion;

import com.jpa.concepts.jpa_concepts.DTO.UserDto;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import com.jpa.concepts.jpa_concepts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userdto) {
        User user=modelMapper.map(userdto,User.class);
        user.setUserId(null);
        var saveduser=userRepository.save(user);

        return modelMapper.map(saveduser,UserDto.class);
    }

    @Override
    public User updateUser(int userId, User user) {
        var oldUser=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user is not found"));
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setUserImageUrl(user.getUserImageUrl());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(int userId) {
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("user is not found"));
    }

    @Override
    public void deleteUser(int userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user is not found"));
        userRepository.delete(user);
    }
}
