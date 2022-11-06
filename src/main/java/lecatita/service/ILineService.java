package lecatita.service;

import java.util.List;

import lecatita.step.writer.state.model.Burden;

public interface ILineService {

	void insertBurdens(List<Burden> burdens);
}
