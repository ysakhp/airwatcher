package ust.airwatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ust.airwatcher.entity.Role;
import ust.airwatcher.entity.User;
import ust.airwatcher.dto.UserDto;
import ust.airwatcher.entity.UserFavourite;
import ust.airwatcher.exception.*;
import ust.airwatcher.repository.RoleRepo;
import ust.airwatcher.repository.UserFavouriteRepo;
import ust.airwatcher.repository.UserRepo;
import ust.airwatcher.util.AirPollutionConstants;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserFavouriteRepo userFavouriteRepo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Transactional
    public User registerUser(UserDto userDto) {


        if (userIdExist(userDto)) {
            throw new UsernameAlreadyExistException();
        }

        try {

            return userRepo.save(createUser(userDto));

        } catch (Exception e) {
            e.printStackTrace();
            throw new UserCreationException();
        }

    }

    private boolean userIdExist(UserDto userDto) {

        return userRepo.findOneByUserName(userDto.getUsername()).isPresent();

    }

    private User createUser(UserDto userDto) {

            Role role = Optional.ofNullable(roleRepo.findByRoleName(AirPollutionConstants.DISABLED_USER_ROLE)
                    .orElseThrow(RoleNotFoundException::new)).get();
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);

            return User.builder().userName(userDto.getUsername()).password(bcryptEncoder.encode(userDto.getPassword())).roles(roleSet).build();


    }

    public Object uploadPhoto(String username, MultipartFile file) {

        return username+file;
    }

    public String activateUser(String username, boolean isActive) {


        try {
            User user = userRepo.findOneByUserName(username).orElseThrow(UserNotFoundException::new);

            Role role;
            if (isActive) {
                role = roleRepo.findByRoleName(AirPollutionConstants.USER_ROLE).orElseThrow(Exception::new);
            } else {
                role = roleRepo.findByRoleName(AirPollutionConstants.DISABLED_USER_ROLE).orElseThrow(Exception::new);
            }
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            user.setRoles(roleSet);

            userRepo.saveAndFlush(user);
        }catch (UserNotFoundException e){
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UpdateFailedException();
        }

        return isActive ? AirPollutionConstants.MSG_USER_ACTIVATED:AirPollutionConstants.MSG_USER_DEACTIVATED;

    }

    public String addFavourite(String city) {

        if (!userFavouriteRepo.findOneByCityAndUserId(city, customUserDetailsService.getLoggedInUserId()).isPresent()) {
            try {
                userFavouriteRepo.save(createFavourite(city));
            } catch (Exception e) {
                throw new FavouriteNotAddedException();
            }
        } else
            throw new CityAlreadyExistException();


        return AirPollutionConstants.FAVOURITES_ADD_SUCCESS;

    }

    private UserFavourite createFavourite(String city) {

        return UserFavourite.builder().userId(customUserDetailsService.getLoggedInUserId()).city(city).build();
    }
}
