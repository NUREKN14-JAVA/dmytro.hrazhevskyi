package kn.dmytro_grazhevskiy.usermanagement.db;

import java.util.Collection;

import kn.dmytro_grazhevskiy.usermanagement.User;

public class HsqldbUserDao implements UserDao {

private ConnectionFactory connectionFactory;


public HsqldbUserDao(ConnectionFactory connectionFactory) {
	//super();
	this.connectionFactory = connectionFactory;
}


	public User create(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}


	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void delete(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
