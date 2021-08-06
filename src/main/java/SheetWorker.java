import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

public class SheetWorker {
    private static final String APPLICATION_NAME = "ForzaTelegramBot";
    private static final String SPREADSHEET_ID = "1p9hQrtGU3QuXOo1DP7tDkmcAufLBi51VeW2snU7JOA8";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Создает авторизованный объект Credential.
     * @param HTTP_TRANSPORT Сетевой транспорт HTTP.
     * @return Авторизованный объект учетных данных.
     * @throws IOException Если файл credentials.json не может быть найден.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        // Загрузить секреты клиента.
        InputStream in = SheetWorker.class.getResourceAsStream(CREDENTIALS_FILE_PATH);

        if (in == null) { throw new FileNotFoundException("Ресурс не найден: " + CREDENTIALS_FILE_PATH); }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Построить поток и инициировать запрос авторизации пользователя.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void addPairs(List<List<Object>> pairs) {
        try {
            // Данные.
            ValueRange body = new ValueRange().setValues(pairs);

            // Загружает адрес места вставки в таблицу.
            ValueRange response = getService().spreadsheets().values()
                    .get(SPREADSHEET_ID, "INFO!B2")
                    .execute();

            // Пишет данные в таблицу.
            UpdateValuesResponse request = getService().spreadsheets().values()
                    .update(SPREADSHEET_ID, (String) response.getValues().get(0).get(0), body)
                    .setValueInputOption("RAW")
                    .execute();
        }
        catch (GeneralSecurityException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void setValue(String key, String value) {
        try {
            // Данные.
            ValueRange body1 = new ValueRange().setValues(Arrays.asList(Arrays.asList(key)));

            // Пишет данные в таблицу.
            UpdateValuesResponse request = getService().spreadsheets().values()
                    .update(SPREADSHEET_ID, "SEARCH!A2", body1)
                    .setValueInputOption("RAW")
                    .execute();

            // Загружает адрес места вставки в таблицу.
            ValueRange response = getService().spreadsheets().values()
                    .get(SPREADSHEET_ID, "SEARCH!C2")
                    .execute();

            // Данные.
            ValueRange body2 = new ValueRange().setValues(Arrays.asList(Arrays.asList(key, value)));

            // Пишет данные в таблицу.
            UpdateValuesResponse request2 = getService().spreadsheets().values()
                    .update(SPREADSHEET_ID, (String) response.getValues().get(0).get(0), body2)
                    .setValueInputOption("RAW")
                    .execute();
        }
        catch (GeneralSecurityException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public List<List<Object>> getPairs() {
        List<List<Object>> pairs = null;
        try {
            // Загружает данные из таблицы.
            ValueRange response = getService().spreadsheets().values()
                    .get(SPREADSHEET_ID, "A2:B")
                    .execute();

            // Хранит в себе загруженные значения из таблицы.
            pairs = response.getValues();
        }
        catch (GeneralSecurityException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        return pairs;
    }

    public ArrayList<Object> getKeys() {
        ArrayList<Object> keys = new ArrayList<Object>();
        for (List<Object> list: getPairs()) { keys.add(list.get(0)); }

        return keys;
    }

    public ArrayList<Object> getValues() {
        ArrayList<Object> values = new ArrayList<Object>();
        for (List<Object> list: getPairs()) { values.add(list.get(1)); }

        return values;
    }

    private Sheets getService() throws IOException, GeneralSecurityException {

        // Создайте новую авторизованную клиентскую службу API.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Делает подключение к таблице.
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        return service;
    }
}
