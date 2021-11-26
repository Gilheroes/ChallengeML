package com.challenge.ml.bsn;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

import com.challenge.ml.exception.InvalidDataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Users;
import com.challenge.ml.exception.NotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class EnrolamientoBsnImpl implements EnrolamientoBsn {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public String getJWTToken(String userName) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("jwtChallenge")
                .setSubject(userName)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @Override
    public UsersVO saveNewUser(UsersVO newUser) throws InvalidDataException {
        this.validateRequestInformation(newUser);
        Users users = mapper.map(newUser, Users.class);
        return mapper.map(usersRepository.save(users), UsersVO.class);
    }

    @Override
    public boolean validateUser(UsersVO userVO) {
        try {
            Users userEntity = mapper.map(userVO, Users.class);
            List<Users> usersList = usersRepository.findAll();
            for (Users user : usersList) {
                if (user.getUser_name().equals(userEntity.getUser_name()))
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UsersVO findByUserAndPwd(UsersVO usersVO, HttpSession httpSession) throws NotFoundException, InvalidDataException {

        this.validateRequestInformation(usersVO);
        Users users = usersRepository.findByUserAndPwd(usersVO.getUser_name(), usersVO.getPassword());
        if (null == users) {
            throw new NotFoundException("No existe un usuario con los datos proporcionados.");
        }
        usersVO = mapper.map(users, UsersVO.class);
        httpSession.setAttribute("id", users.getIdUsers());
        return usersVO;
    }


    /**
     * Method to validate request information.
     *
     * @param usersVO Object with request information.
     * @throws InvalidDataException If the object don't have the required data.
     */
    private void validateRequestInformation(final UsersVO usersVO) throws InvalidDataException {
        if (null == usersVO.getUser_name() || null == usersVO.getPassword()) {
            throw new InvalidDataException("Los datos de usuario y contraseña son requeridos");
        }
    }

}
