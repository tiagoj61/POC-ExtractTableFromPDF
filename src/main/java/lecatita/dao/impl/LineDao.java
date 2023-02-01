package lecatita.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lecatita.dao.enumoperations.InsertsEnum;
import lecatita.dao.interfc.ILineDao;
import lecatita.step.writer.state.model.Burden;

@Repository
public class LineDao implements ILineDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Burden burden) {
		String sql = InsertsEnum.BURDEN.getValue();
		sql = sql.replaceAll(":" + Burden.class.getDeclaredFields()[0].getName(), "'" + burden.getName() + "'");
		sql = sql.replaceAll(":" + Burden.class.getDeclaredFields()[1].getName(), "'" + burden.getQuantity_male() + "'");
		sql = sql.replaceAll(":" + Burden.class.getDeclaredFields()[2].getName(),
				"'" + burden.getQuantity_female() + "'");

		this.jdbcTemplate.execute(sql);
	}

	@Override
	public List<Burden> findAll() {
		String sql = "Select burden.name,burden.quantity_male,burden.quantity_female from burden";

		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Burden.class));
	}

	@Override
	public void clearTable() {
		String sql = "delete from burden";

		this.jdbcTemplate.execute(sql);
	}
}
