package ml.bigbrains.zakaznoeclient;


import lombok.extern.slf4j.Slf4j;
import ml.bigbrains.zakaznoeclient.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@Slf4j
public class ZakaznoeApiApplicationTests {

	private ZakaznoeApiClient client = new ZakaznoeApiClient();
	private String clientId = "id";
	private String clientToken = "token";
	@Test
	public void testGetToken()
	{
		assertEquals("DEADBEAF==",client.getToken(clientId,clientToken));
	}

	@Test
	public void testGetInboxSummary()
	{
		log.debug("GetInboxSummary");
		InboxSummaryRequest request = new InboxSummaryRequest(LocalDateTime.now().minusDays(30),LocalDateTime.now());
		InboxSummaryResponse response = client.getInboxSummary(request,clientId,clientToken);
		log.debug("RESPONSE: {}",response);
	}

	@Test
	public void testGetLetters()
	{
		log.debug("GetLetters");
		LettersRequest request = new LettersRequest(Category.READ, null, null, LocalDateTime.now().minusDays(30),LocalDateTime.now(),null,null);
		LettersResponse response = client.getLetters(request, clientId,clientToken);
		log.debug("RESPONSE: {}",response);
		log.debug("data size: {}",response.getData().size());
	}

	@Test
	public void testGetLetterContent() throws IOException {
		log.debug("GetLetterContent");
		LetterContentRequest request = new LetterContentRequest("L___1000800000000___20210719");
		LetterContentResponse response = client.getLetterContent(request, clientId,clientToken);
		log.debug("RESPONSE: {}",response);
		Files.write(Paths.get("/tmp/file.zip"), response.getContent());
		log.debug("data size: {}",response.getContent().length);
	}

	@Test
	public void testGetLetterMetaInfo() throws IOException {
		log.debug("GetLetterContent");
		LetterMetaInfoRequest request = new LetterMetaInfoRequest("L___1000000100000___20210719");
		Letter response = client.getLetterMetaInfo(request, clientId,clientToken);
		log.debug("RESPONSE: {}",response);
	}

	@Test
	public void testGetSenders() throws IOException {
		log.debug("GetSenders");
		SendersRequest request = new SendersRequest();
		List<Sender> response = client.getSenders(request, clientId,clientToken);
		log.debug("RESPONSE: {}",response);
	}

	@Test
	public void testGetSenderById() throws IOException {
		log.debug("GetSenders");
		SenderRequest request = new SenderRequest(1L);
		Sender response = client.getSenderById(request, clientId,clientToken);
		log.debug("RESPONSE: {}",response);
	}
}
