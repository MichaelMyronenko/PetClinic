package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.entities.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final UserRegistrationModelMapper<CommonUser, UserRegistrationModel> modelMapper;

    @Override
    public CommonUser registerUser(UserRegistrationModel userModel) {
        if (userRepository.findCommonUserByUsername(userModel.getUsername()).isPresent()) {
            throw new ConflictException("User with username " + userModel.getUsername() + " already exists!");
        }

        CommonUser user = modelMapper.mapToEntity(userModel);

        return userRepository.save(user);
    }
}
