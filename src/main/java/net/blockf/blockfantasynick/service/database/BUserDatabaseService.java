package net.blockf.blockfantasynick.service.database;

import com.zaxxer.hikari.HikariDataSource;
import net.blockf.blockfantasynick.database.hikaricp.HikariUtil;
import net.blockf.blockfantasynick.entity.BUser;
import net.blockf.blockfantasynick.service.DatabaseService;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

public class BUserDatabaseService {
    HikariDataSource dataSource = HikariUtil.getInstance().getDataSource();
    public int insertUser(Player player){
        try {
            Connection connection = dataSource.getConnection();

            String sql = "insert into bf_nick_user (uuid,user_name,display_name,display_name_noc,status,update_user,update_user_uuid,update_time) values (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getName());
            preparedStatement.setString(4, player.getName());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, player.getName());
            preparedStatement.setString(7, player.getUniqueId().toString());
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));


            int rs = preparedStatement.executeUpdate();
            connection.close();
            return rs;


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public BUser hasUser(Player player){
        BUser bUser = new BUser();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from bf_nick_user where user_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, player.getName());
            ResultSet rs = preparedStatement.executeQuery();



            if(!rs.next()){
                connection.close();
                return null;
            }



            bUser.setUuid(UUID.fromString(rs.getString("uuid")));
            bUser.setUser_name(rs.getString("user_name"));
            bUser.setDisplay_name(rs.getString("display_name"));
            bUser.setDisplay_name_noc(rs.getString("display_name_noc"));
            bUser.setStatus(rs.getInt("status"));
            bUser.setUpdate_user(rs.getString("update_user"));
            bUser.setUpdate_user_uuid(UUID.fromString(rs.getString("update_user_uuid")));
            bUser.setUpdate_time(rs.getTimestamp("update_time"));


            connection.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return bUser;
    }
    public BUser getUserNick(Player player){
        BUser bUser = new BUser();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from bf_nick_user where uuid = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();



            if(!rs.next()){
                insertUser(player);
                return getUserNick(player);
            }



            bUser.setUuid(UUID.fromString(rs.getString("uuid")));
            bUser.setUser_name(rs.getString("user_name"));
            bUser.setDisplay_name(rs.getString("display_name"));
            bUser.setDisplay_name_noc(rs.getString("display_name_noc"));
            bUser.setStatus(rs.getInt("status"));
            bUser.setUpdate_user(rs.getString("update_user"));
            bUser.setUpdate_user_uuid(UUID.fromString(rs.getString("update_user_uuid")));
            bUser.setUpdate_time(rs.getTimestamp("update_time"));


            connection.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return bUser;
    }

    public BUser getNickByNameNoc(String display_name_noc){
        BUser bUser = new BUser();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from bf_nick_user where display_name_noc = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, display_name_noc);
            ResultSet rs = preparedStatement.executeQuery();


            if(!rs.next()){
                return null;
            }

            bUser.setUuid(UUID.fromString(rs.getString("uuid")));
            bUser.setUser_name(rs.getString("user_name"));
            bUser.setDisplay_name(rs.getString("display_name"));
            bUser.setDisplay_name_noc(rs.getString("display_name_noc"));
            bUser.setStatus(rs.getInt("status"));
            bUser.setUpdate_user(rs.getString("update_user"));
            bUser.setUpdate_user_uuid(UUID.fromString(rs.getString("update_user_uuid")));
            bUser.setUpdate_time(rs.getTimestamp("update_time"));


            connection.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return bUser;
    }
    public int updateUserNick(BUser bUser){
        try {
            Connection connection = dataSource.getConnection();



            String sql = "update bf_nick_user set user_name=?,display_name=?,display_name_noc=?,status=?,update_user=?,update_user_uuid=?,update_time=? where uuid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bUser.getUser_name());
            preparedStatement.setString(2, bUser.getDisplay_name());
            preparedStatement.setString(3, bUser.getDisplay_name_noc());
            preparedStatement.setInt(4,  bUser.getStatus());
            preparedStatement.setString(5, bUser.getUpdate_user());
            preparedStatement.setString(6, bUser.getUpdate_user_uuid().toString());
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(8, bUser.getUuid().toString());


            int rs = preparedStatement.executeUpdate();
            connection.close();
            return rs;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
