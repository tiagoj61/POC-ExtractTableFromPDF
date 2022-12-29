package lecatita.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import lecatita.dao.interfc.ILineDao;
import lecatita.dto.request.BurdenRequest;
import lecatita.service.ILineService;
import lecatita.service.ISendService;
import lecatita.step.writer.state.model.Burden;

@Repository
public class SendService implements ISendService {
	@Autowired
	private ILineDao dao;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void sendLineResult(String empresaId, String ano) {
		sendBurdens(castBurdenRequest(findAllBurdens(), empresaId, ano));
		clearBurdens();
	}

	private List<BurdenRequest> castBurdenRequest(List<Burden> burdens, String empresaId, String ano) {
		return findAllBurdens().stream().map(burden -> new BurdenRequest(empresaId, ano, burden.getName(),
				burden.getQuantity_male(), burden.getQuantity_female())).collect(Collectors.toList());
	}

	private List<Burden> findAllBurdens() {
		List<Burden> burdens = dao.findAll();
		return burdens;
	}

	private void sendBurdens(List<BurdenRequest> burdens) {
		ResponseEntity<JsonNode> response = restTemplate.exchange("http://teste/Send/Burden", HttpMethod.POST,
				new HttpEntity<>(burdens, new HttpHeaders()), JsonNode.class);
	}

	private void clearBurdens() {
		dao.findAll();

	}
}