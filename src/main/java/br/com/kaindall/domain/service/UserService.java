package br.com.kaindall.domain.service;

import br.com.kaindall.domain.entity.UserEntity;
import br.com.kaindall.domain.repository.UserRepository;
import br.com.kaindall.domain.exception.UserException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.h2.engine.User;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;

    public UserService (UserRepository userRepository) {this.userRepository = userRepository;}
    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return userRepository.findByIdOptional(userId)
                .orElseThrow(UserException::notFound);
    }
    @Transactional
    public UserEntity createUser(UserEntity user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public UserEntity updateUser(UUID userId, UserEntity user) {
        var updatedUser = findById(userId);
        updatedUser.setUsername(user.getUsername());
        userRepository.persist(updatedUser);
        return updatedUser;
    }

    @Transactional
    public void deleteUser(UUID userId) {
        findById(userId);
        userRepository.deleteById(userId);
    }


}
