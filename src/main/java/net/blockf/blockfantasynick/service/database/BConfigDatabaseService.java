package net.blockf.blockfantasynick.service.database;

import com.zaxxer.hikari.HikariDataSource;
import net.blockf.blockfantasynick.database.hikaricp.HikariUtil;
import net.blockf.blockfantasynick.entity.BConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BConfigDatabaseService {
    HikariDataSource dataSource = HikariUtil.getInstance().getDataSource();

    public String getConfig(String config) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from bf_nick_config where conf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, config);
            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return null;
            }
            return rs.getString("value");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
