//package com.marketplace.project.dao.custom;
//
//import com.marketplace.project.entities.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//public class UserRepositoryImpl implements UserRepositoryCustom {
//
//    @PersistenceContext()
//    private EntityManager entityManager;
//
//    @Override
//    public User findUserByEmail(String email) {
//        Query query = entityManager.createNativeQuery("SELECT user.* FROM user as u WHERE u.email = ?");
//        query.setParameter(1,email);
//        return (User) query.getSingleResult();
//    }
//
//
////    @Override
////    public List<User> findByCity(String city) {
////        Query query = entityManager.createNamedQuery(User.FIND_USER_BY_CITY, User.class);
////        query.setParameter(1, city);
////        return query.getResultList();
////    }
//
//
////    @Override
////    public void refresh(User user) {
////        entityManager.refresh(user);
////    }
//}
