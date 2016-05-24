package com.valmar.ecommerce.security.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.ecommerce.dao.AbstractDao;
import com.valmar.ecommerce.security.model.Authority;
import com.valmar.ecommerce.security.model.AuthorityName;
import com.valmar.ecommerce.security.model.User;


@Repository("userDao")
@EnableTransactionManagement
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	public int validateUser(String username, String password){
		int userId = 0;
		Query query = getSession().createSQLQuery("SELECT id FROM User WHERE username = :username"
				+ " AND password = :password");
		query.setString("username", username);
		query.setString("password", password);
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		Object obj = results.get(0);
		userId = (int) obj;
		return userId;
	}

	@Override
	public User getUserById(int userId) {
		Query query = getSession().createSQLQuery("SELECT * FROM User WHERE id = :id");
		query.setInteger("id", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		User user = new User();
		for (Object[] row : results) {
			user.setId(Long.parseLong(row[0].toString()));
			user.setUsername(row[1].toString());
			user.setPassword(row[2].toString());
			user.setFirstname(row[3].toString());
			user.setLastname(row[4].toString());
			user.setEnabled(row[5].toString().equals("1"));
			String string = row[6].toString();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = null;
			try {
				date = (Date) format.parse(string);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setLastPasswordResetDate(date);
		}
		return user;
	}
	
	public User findByUsername(String username) {
		/*
		 * Criteria criteria = createEntityCriteria();
		 * criteria.add(Restrictions.like("FIRSTNAME", username)); return (User)
		 * criteria.uniqueResult();
		 */

		Query query = getSession().createSQLQuery("SELECT * FROM User WHERE username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		User user = new User();
		for (Object[] row : results) {
			user.setId(Long.parseLong(row[0].toString()));
			user.setUsername(row[1].toString());
			user.setPassword(row[2].toString());
			user.setFirstname(row[3].toString());
			user.setLastname(row[4].toString());
			user.setEnabled(row[5].toString().equals("1"));
			String string = row[6].toString();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = null;
			try {
				date = (Date) format.parse(string);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setLastPasswordResetDate(date);
			System.out.println(user.getFirstname());
		}

		Query queryAuthority = getSession().createSQLQuery(
				"SELECT a.id, a.name FROM test.user u " + "INNER JOIN user_authority ua " + "ON u.id = ua.user_id "
						+ "INNER JOIN authority a " + "ON ua.authority_id = a.id " + "WHERE u.username = :username ");
		queryAuthority.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Object[]> resultsAuthority = queryAuthority.list();
		List<Authority> authorities = new ArrayList<>();
		for (Object[] row : resultsAuthority) {
			Authority authority = new Authority();
			authority.setId(Long.parseLong(row[0].toString()));
			switch (row[1].toString()) {
				case "ROLE_USER":
					authority.setName(AuthorityName.ROLE_USER);
					break; 
				case "ROLE_ADMIN":
					authority.setName(AuthorityName.ROLE_ADMIN);
					break; 
			}
			authorities.add(authority);
			user.setAuthorities(authorities);
		}
		return user;
	}

}
