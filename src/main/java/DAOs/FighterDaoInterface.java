package DAOs;

import DTOs.Fighter;
import Exceptions.DaoException;

import java.util.List;

public interface FighterDaoInterface {

    public List<Fighter> findAllFighters() throws DaoException;

}
