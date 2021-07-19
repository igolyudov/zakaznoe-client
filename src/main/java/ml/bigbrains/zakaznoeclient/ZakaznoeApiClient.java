package ml.bigbrains.zakaznoeclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ml.bigbrains.zakaznoeclient.model.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class ZakaznoeApiClient {

    private String baseUrl = "https://zakaznoe.pochta.ru/api/";
    private String secretKey;
    private String clientId;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient();

    public ZakaznoeApiClient() {
    }

    public ZakaznoeApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private <T> T getRequest(String url, String token, Map<String, String> params, Class<T> responseClass) {

        HttpUrl.Builder requestUrl = HttpUrl.parse(baseUrl+url).newBuilder();

        for(Map.Entry<String, String> param : params.entrySet()) {
            requestUrl.addQueryParameter(param.getKey(),param.getValue());
        }

        okhttp3.Request request = new Request.Builder()
                .url(requestUrl.build())
                .header("content-type","application/json")
                .header("accept","application/json")
                .header("Authorization","Basic "+token)
                .build();
        log.debug("TOKEN: {}",token);
        log.debug("REQUEST: {}",request);
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
            {
                log.warn("Response is not success. Response code: {}",response.code());
            }
            if(response.body()!=null)
                return mapper.readValue(response.body().string(), responseClass);
            else
            {
                log.error("Empty response body after request to {}",url);
                return null;
            }
        }
        catch (IOException e)
        {
            log.error("Error in getRequest to {}",url,e);
            return null;
        }
    }

    private byte [] getRequest(String url, String token, Map<String, String> params) {

        HttpUrl.Builder requestUrl = HttpUrl.parse(baseUrl+url).newBuilder();

        for(Map.Entry<String, String> param : params.entrySet()) {
            requestUrl.addQueryParameter(param.getKey(),param.getValue());
        }

        okhttp3.Request request = new Request.Builder()
                .url(requestUrl.build())
                .header("accept","application/octet-stream")
                .header("Authorization","Basic "+token)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
            {
                log.warn("Response is not success. Response code: {}",response.code());
            }
            if(response.body()!=null)
                return response.body().bytes();
            else
            {
                log.error("Empty response body after request to {}",url);
                return null;
            }
        }
        catch (IOException e)
        {
            log.error("Error in getRequest to {}",url,e);
            return null;
        }
    }

    public String getToken(String clientId, String clientToken)
    {
        return Base64.getEncoder().encodeToString((clientId+":"+clientToken).getBytes(StandardCharsets.UTF_8));
    }

    public InboxSummaryResponse getInboxSummary(InboxSummaryRequest request, String clientId, String clientToken)
    {
        log.debug("inbox/summary request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        InboxSummaryResponse response = getRequest(request.getRequestUrl(), token, request.getParamMap(), InboxSummaryResponse.class);
        return response;
    }

    public LettersResponse getLetters(LettersRequest request, String clientId, String clientToken)
    {
        log.debug("letters request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        return getRequest(request.getRequestUrl(), token, request.getParamMap(), LettersResponse.class);
    }

    public LetterContentResponse getLetterContent(LetterContentRequest request, String clientId, String clientToken)
    {
        log.debug("letter content request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        byte [] content = getRequest(request.getRequestUrl(), token, request.getParamMap());
        LetterContentResponse response = new LetterContentResponse(content);
        return response;
    }

    public Letter getLetterMetaInfo(LetterMetaInfoRequest request, String clientId, String clientToken)
    {
        log.debug("letter meta-info request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        return getRequest(request.getRequestUrl(), token, request.getParamMap(), Letter.class);
    }

    public List<Sender> getSenders(SendersRequest request, String clientId, String clientToken)
    {
        log.debug("senders request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        Sender[] response = getRequest(request.getRequestUrl(), token, request.getParamMap(), Sender[].class);
        if(response!=null) {
            List<Sender> senders = Arrays.asList(response);
            return senders;
        }
        else
            return new ArrayList<>();
    }

    public Sender getSenderById(SenderRequest request, String clientId, String clientToken)
    {
        log.debug("sender request params: {}",request.getParamMap());
        String token = getToken(clientId, clientToken);
        Sender response = getRequest(request.getRequestUrl(), token, request.getParamMap(), Sender.class);
        return response;
    }
}
