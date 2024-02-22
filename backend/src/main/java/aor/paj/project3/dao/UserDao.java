package aor.paj.project3.dao;

import aor.paj.project3.Entity.UserEntity;
import jakarta.persistence.NoResultException;

public class UserDao extends AbstractDao <UserEntity> {

    private static final long serialVersionUID = 1L;

    public UserDao () {
        super(UserEntity.class);
    }

    public UserEntity findUserByToken(String token) {
        try {
            return (UserEntity) em.createNamedQuery("User.findUserByToken").setParameter("token", token)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }


    public UserEntity findUserByEmail(String email) {
        try {
            return (UserEntity) em.createNamedQuery("User.findUserByEmail").setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }


}
