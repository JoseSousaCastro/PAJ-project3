package aor.paj.project3.dao;

import aor.paj.project3.entity.TaskEntity;
import aor.paj.project3.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import java.util.ArrayList;

@Stateless
public class TaskDao extends AbstractDao<ActivityEntity> {

    private static final long serialVersionUID = 1L;

    public ActivityDao() {
        super(ActivityEntity.class);
    }


    public ActivityEntity findActivityById(int id) {
        try {
            return (ActivityEntity) em.createNamedQuery("Activity.findActivityById").setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

    public ArrayList<ActivityEntity> findActivityByUser(UserEntity userEntity) {
        try {
            ArrayList<ActivityEntity> activityEntityEntities = (ArrayList<ActivityEntity>) em.createNamedQuery("Activity.findActivityByUser").setParameter("owner", userEntity).getResultList();
            return activityEntityEntities;
        } catch (Exception e) {
            return null;
        }
    }
}

