package lecatita.step.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ReaderLine implements ItemReader<String> {

	private String messages = "asdfasdf 12 12 12 12 ||";

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println(count);
		if (count == 0) {
			count++;
			return messages;
		}
		return null;
	}

}
