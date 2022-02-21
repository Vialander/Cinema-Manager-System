package Dao;

import Entity.Room;

import java.util.ArrayList;
import java.util.List;

public interface RoomDao {
    public abstract int updateRoom(Room room);
    public abstract int delRoom(Room room);
    public abstract int addRoom(Room room);
    public abstract ArrayList<Room> getAllRoom();
    public abstract ArrayList<Room> selectRoom(String sql, Object[] param);
}
