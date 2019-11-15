package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dataSubject.ListSubject;

public class DriverToSubject {

	public static void main(String[] args) throws IOException {

		Subject[] subjects = ListSubject.subjects("./data/AmazonloggedIn.txt");

		ObjectMapper mapper = new ObjectMapper();

		setName(subjects);

		System.out.println("Succeed!");

		mapper.writeValue(new File("./data/dataSubject.json"), subjects);

	}

	public static void setName(Subject[] subjects) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		NameSubject[] nameSubjects = mapper.readValue(new File("./data/nameSubject.json"), NameSubject[].class);

		for(Subject subject: subjects)
			for(NameSubject nameSubject: nameSubjects)
				if(subject.getCode().startsWith(nameSubject.getCode())) {
					subject.setName(nameSubject.getName());

					break;
				}
	}

}