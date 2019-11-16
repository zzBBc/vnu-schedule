package json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class InputJson {
	public static Subject[] getDataSubject() throws IOException, JsonParseException, JsonMappingException {
		//		ObjectMapper mapper = new ObjectMapper();
		//		Subject[] dataSubjects = mapper.readValue(new File("./data/dataSubject.json"), Subject[].class);

		Gson gson = new Gson();

		Subject[] object = gson.fromJson(new FileReader("./data/dataSubject.json"), Subject[].class);
		return object;
	}

	public static NameSubject[] getNameSubject() throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		NameSubject[] nameSubjects = mapper.readValue(new File("./data/nameSubject.json"), NameSubject[].class);

		return nameSubjects;
	}
}
