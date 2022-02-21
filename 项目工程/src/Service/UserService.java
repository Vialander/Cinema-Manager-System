package Service;


import Entity.*;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * 用户服务类接口
 */

public interface UserService {
    public abstract Cinema getCinemaByName(String name);

    //根据名字找电影
    public abstract  Film getFilmByName(String name);

    /**
     * 用户服务之购买
     */
    public abstract void buy(User thisUser, FilmFrame thisFilmFrame,int Row,int Colunm);

    /**
     * 列出所有影院
     */
    public abstract void showCinema();

    /**
     * 得到所有影院
     */
    public abstract List<Cinema> getCinema();

    /**
     * 选择某影院里面包含的所有上架电影
     */
    public abstract void showSelectedFilmsInCinema(Cinema cinema);

    /**
     * 得到某影院里面包含的所有上架电影
     */
    public abstract List<Film> getSelectedFilmsInCinema(Cinema cinema);

    /**
     * 显示所有已经上架的电影
     */
    public abstract void showSelectedFilms();

    /**
     * 得到所有已经上架的电影
     */
    public abstract List<Film> getSelectedFilms();


    /**
     * 选择已经显示的已上架电影(根据已上架电影选择场次)
     *
     */
    public abstract void selectSelectedFilms(Film thisFilm);

    /**
     * 手动选座
     * @param thisUser
     * @param thisFilmFrame
     */
    public abstract void getIntoChoose(User thisUser,FilmFrame thisFilmFrame);

    /**
     * 自动选择算法
     * @param thisUser
     * @param thisFilmFrame
     * @return
     */
    public abstract FilmFrame  autoChoose(User thisUser,FilmFrame thisFilmFrame);

    /**
     * 自动选座
     * @param thisUser
     * @param thisFilmFrame
     */
    public abstract void getAutoChoose(User thisUser,FilmFrame thisFilmFrame);


    /**
     * 登陆
     * @return User
     */
    public abstract  User loginUser(String userName,String password);

    /**
     * 登陆
     * @return User
     */
    public abstract  boolean login(String userName,String password);

    /**
     * 获取重名用户
     * @param userName
     * @return
     */
    public abstract boolean getSameUsername(String userName);

    /**
     * 注册
     */
    public abstract void regist(User user);

    /**
     * 查看订单信息
     */
    public abstract List<Account> getUserAccount(User thisUser);

    /**
     * 根据用户获得订单信息
     */
    public abstract Map<Account,FilmFrame> makeUserAccount(User thisUser);

    /**
     * 根据订单信息列出当前人员的订单
     */
    public abstract void showUserAccount(User thisUser);

    /**
     *
     * @param filmFrame
     */
    public abstract void showSeats(FilmFrame filmFrame);

}
