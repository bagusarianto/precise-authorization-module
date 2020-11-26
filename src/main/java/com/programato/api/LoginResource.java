package com.programato.api;

import com.programato.models.LoginModel;
import com.programato.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.smallrye.jwt.build.Jwt;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Member;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {
    @POST
    @PermitAll
    @Transactional
    public JsonObject authenticate(@Valid LoginModel data) {
        final Argon2 argon2 = Argon2Factory.create();
        final String hashedPassword = argon2.hash(22, 65536, 1, data.getPassword().getBytes());
        final User user = User.findUser(data.getEmail().trim());

        if (user != null) {
            try {
                if (argon2.verify(hashedPassword, data.getPassword().getBytes())) {
                    LocalDateTime currentTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Jakarta"));
                    user.lastLogin = currentTime;

//                    Member member = Member.find("customer_email=?1", data.getEmail()).firstResult();

                    final String token =
                            Jwt.issuer("programato")
                                    .upn(user.email)
                                    .subject(user.name)
                                    .groups(user.roles.stream().map(r -> r.roleName).collect(Collectors.toSet()))
                                    .expiresAt(currentTime.plusDays(3).toInstant(ZoneId.of("Asia/Jakarta").getRules().getOffset(Instant.now())))
                                    .sign();
                    user.persist();
                    return new JsonObject().put("token", token).put("name", user.name);
                } else {
                    return new JsonObject();
                }
            } finally {
                argon2.wipeArray(data.getPassword().getBytes());
            }
        } else {
            return new JsonObject();
        }


    }

//    @POST
//    @Path("/change_password")
//    public String changePassword(String password) {
//        final Argon2 argon2 = Argon2Factory.create();
//        final String hashedPassword = argon2.hash(22, 65536, 1, password.getBytes());
//        return hashedPassword;
//    }
}
