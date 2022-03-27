package DAOs;

import DTOs.Fighter;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlFighterDao extends MySqlDao implements FighterDaoInterface
{
    @Override
    public List<Fighter> findAllFighters() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Fighter> fightersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM fighters";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                Fighter f = new Fighter(name,wins,losses);
               fightersList.add(f);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllFighterResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllFighters() " + e.getMessage());
            }
        }
        return fightersList;     // may be empty
    }
}
