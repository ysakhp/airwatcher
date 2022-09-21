package ust.airwatcher;

import io.swagger.annotations.Authorization;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ust.airwatcher.dto.UserDto;
import ust.airwatcher.entity.Role;
import ust.airwatcher.entity.User;
import ust.airwatcher.entity.UserFavourite;
import ust.airwatcher.repository.RoleRepo;
import ust.airwatcher.repository.UserFavouriteRepo;
import ust.airwatcher.repository.UserRepo;
import ust.airwatcher.service.CustomUserDetailsService;
import ust.airwatcher.service.UserService;
import ust.airwatcher.util.AirPollutionConstants;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepo userRepo;

    @Mock
    RoleRepo roleRepo;

    @Mock
    UserFavouriteRepo userFavouriteRepo;

    @Mock
    CustomUserDetailsService customUserDetailsService;

    @Mock
    private BCryptPasswordEncoder bcryptEncoder;


    @Test
    public void testRegisterUser() {

        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().roleName("DISABLED_USER").id(3).build());

        User user = User.builder().userName("Vimal").password("$2a$10$01oJjUTlCCy54CHyRyBET.hXKW6VTWtELMqeTLHJsvbkWC4L77vcW").roles(roles).build();

        UserDto userReq = UserDto.builder().username("Vimal").password("123").build();

        Mockito.when(userRepo.save(user)).thenReturn(user);

        Mockito.when(userRepo.findOneByUserName("Vimal")).thenReturn(Optional.empty());

        Mockito.when(roleRepo.findByRoleName("DISABLED_USER")).thenReturn(Optional.ofNullable(Role.builder().roleName("DISABLED_USER").id(3).build()));

        Mockito.when(bcryptEncoder.encode("123")).thenReturn("$2a$10$01oJjUTlCCy54CHyRyBET.hXKW6VTWtELMqeTLHJsvbkWC4L77vcW");

        assertEquals(user, userService.registerUser(userReq));

    }

    @Test
    public void testActivateUser(){

        UserDto userReq = UserDto.builder().username("Vimal").password("123").build();

        Set<Role> roles = new HashSet<>();
        Role role = Role.builder().roleName("DISABLED_USER").id(3).build();
        roles.add(role);

        User user = User.builder().userName("Vimal")
                .password("$2a$10$01oJjUTlCCy54CHyRyBET.hXKW6VTWtELMqeTLHJsvbkWC4L77vcW").roles(roles).build();

        Mockito.when(userRepo.findOneByUserName("Vimal")).thenReturn(Optional.of(user));

        Mockito.when(userRepo.saveAndFlush(user)).thenReturn(user);

        Mockito.when(roleRepo.findByRoleName("USER")).thenReturn(Optional.of(role));

        assertEquals(AirPollutionConstants.MSG_USER_ACTIVATED,userService.activateUser("Vimal",true));

    }

    @Test
    public void testAddFavourite(){

        UserFavourite userFavourite = UserFavourite.builder().userId(3).city("Cochin").build();
        Mockito.when(userFavouriteRepo.findOneByCityAndUserId("Cochin",3)).thenReturn(Optional.empty());

        Mockito.when(customUserDetailsService.getLoggedInUserId()).thenReturn(3);

        Mockito.when(userFavouriteRepo.save(userFavourite)).thenReturn(userFavourite);

        assertEquals(AirPollutionConstants.FAVOURITES_ADD_SUCCESS, userService.addFavourite("Cochin"));



    }


   /* @Test
    public void testUserIdExist() {

        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().roleName("DISABLED_USER").id(3).build());

        Mockito.when(userRepo.findOneByUserName("Vimal")).thenReturn(Optional.of(User.builder().userName("Vimal")
                .password("$2a$10$01oJjUTlCCy54CHyRyBET.hXKW6VTWtELMqeTLHJsvbkWC4L77vcW").roles(roles).build()));


    }*/


}
