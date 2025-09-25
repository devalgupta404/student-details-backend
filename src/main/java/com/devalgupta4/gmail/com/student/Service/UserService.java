package com.devalgupta4.gmail.com.student.Service;

import com.devalgupta4.gmail.com.student.dto.CreateUserRequestDto;
import com.devalgupta4.gmail.com.student.entity.Domain;
import com.devalgupta4.gmail.com.student.entity.User;
import com.devalgupta4.gmail.com.student.repository.DomainRepository;
import com.devalgupta4.gmail.com.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Transactional
    public User createUser(CreateUserRequestDto request) {
        if (!isValidEmailFormat(request.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        String domainName = extractDomain(normalizedEmail);
        if (domainName != null && !domainRepository.existsByNameIgnoreCase(domainName)) {
            try {
                domainRepository.save(new Domain(domainName.toLowerCase()));
            } catch (DataIntegrityViolationException ignored) {
                // created concurrently, safe to ignore
            }
        }

        User user = new User(request.getName(), normalizedEmail);
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isValidEmailFormat(String email) {
        return email != null && email.contains("@") && email.indexOf('@') < email.length() - 1;
    }

    private String extractDomain(String email) {
        if (email == null || !email.contains("@")) {
            return null;
        }
        return email.substring(email.indexOf("@") + 1).toLowerCase();
    }

    public List<User> getUsersByCollegeDomain(String domain) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCollegeDomain() != null &&
                               user.getCollegeDomain().equalsIgnoreCase(domain))
                .toList();
    }
}
