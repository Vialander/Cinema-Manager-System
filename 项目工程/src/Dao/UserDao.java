package Dao;

import Entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    public abstract int updateUser(User user);
    public abstract int delUser(User user);
    public abstract int addUser(User user);
    public abstract ArrayList<User> getAllUser();
    public abstract ArrayList<User> selectUser(String sql, Object[] param);
}
