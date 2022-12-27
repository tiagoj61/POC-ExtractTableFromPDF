package lecatita.dao.interfc;

import java.util.List;

import lecatita.step.writer.state.model.Burden;

public interface ILineDao {
	public void insert(Burden burden);
	public List<Burden> findAll();
	public void clearTable();
}
