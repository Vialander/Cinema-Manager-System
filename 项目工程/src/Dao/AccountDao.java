package Dao;



import java.util.ArrayList;
import Entity.Account;

public interface AccountDao {
    public abstract int addAccount(Account account);
    public abstract int delAccount(Account account);
    public abstract int updateAccount(Account account);
    public abstract ArrayList<Account> getAllAccount();
    public abstract ArrayList<Account> selectAccount(String sql, Object[] param);
}

