package lecatita.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lecatita.dao.interfc.IDownloadDao;

@Repository
public class DownloadDao implements IDownloadDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

}
