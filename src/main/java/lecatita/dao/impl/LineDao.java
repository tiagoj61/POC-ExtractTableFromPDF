package lecatita.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
		sql.replace(":" + Burden.class.getFields()[0], burden.getName());
		sql.replace(":" + Burden.class.getFields()[1], burden.getQuantity_male());
		sql.replace(":" + Burden.class.getFields()[2], burden.getQuantity_female());

		// this.jdbcTemplate.execute("Delete");
	}

}
