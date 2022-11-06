package lecatita.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lecatita.dao.interfc.ITableDao;

@Repository
public class TableDao implements ITableDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;



}
