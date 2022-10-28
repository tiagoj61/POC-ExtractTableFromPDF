package lecatita.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lecatita.dao.interfc.ILineDao;

@Repository
public class TableDao implements ILineDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert() {
		this.jdbcTemplate.execute("Delete");
	}

}