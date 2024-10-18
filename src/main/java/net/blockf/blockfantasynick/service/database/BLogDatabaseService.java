package net.blockf.blockfantasynick.service.database;

import com.zaxxer.hikari.HikariDataSource;
import net.blockf.blockfantasynick.database.hikaricp.HikariUtil;
import net.blockf.blockfantasynick.entity.BLog;
import net.blockf.blockfantasynick.entity.BUser;
import net.blockf.blockfantasynick.service.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class BLogDatabaseService {
    HikariDataSource dataSource = HikariUtil.getInstance().getDataSource();


    public int insertLog(BUser bUser){
        try {
            Connection connection = dataSource.getConnection();

            String sql = "insert into bf_nick_log (uuid,user_name,display_name,status,update_user,update_user_uuid) values (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bUser.getUuid().toString());
            preparedStatement.setString(2, bUser.getUser_name());
            preparedStatement.setString(3, bUser.getDisplay_name());
            preparedStatement.setInt(4, bUser.getStatus());
            preparedStatement.setString(5, bUser.getUpdate_user());
            preparedStatement.setString(6, bUser.getUpdate_user_uuid().toString());


            int rs = preparedStatement.executeUpdate();
            connection.close();
            return rs;


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
