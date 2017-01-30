package org.agoncal.other.pdfdocusign;

import com.docusign.esign.api.AuthenticationApi;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.Configuration;
import com.docusign.esign.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Logger;

public class PdfSender {

    private static Logger LOG = Logger.getLogger(PdfSender.class.getName());
    public static final String DOCUSIGN_API = "https://demo.docusign.net/restapi";

    private static String username;
    private static String password;
    private static String integratorKey;
    private static String accountId;

    public static void sendPdf(String pdfFile) throws Exception {

        initializeDocusignAPI();
        EnvelopeDefinition envDef = generateEnvelopeDefinition(pdfFile);

        // Sends the envelope
        EnvelopesApi envelopesApi = new EnvelopesApi();
        EnvelopeSummary summary = envelopesApi.createEnvelope(accountId, envDef);

        LOG.info("PDF has been sent with envelope " + summary.getEnvelopeId() + " at " +summary.getStatusDateTime());
    }

    private static EnvelopeDefinition generateEnvelopeDefinition(String pdfFile) throws IOException {
        // create an envelope that will store the document(s), field(s), and recipient(s)
        EnvelopeDefinition envDef = new EnvelopeDefinition();
        envDef.setEmailSubject("Email to my beloved customer");

        // add a document to the envelope
        envDef.setDocuments(getDocument(pdfFile));
        Signer signer = getSigner();

        // Set tabs
        Tabs tabs = new Tabs();
        tabs.setFullNameTabs(getTabFullName());
        tabs.setDateSignedTabs(getTabDateSigned());
        tabs.setSignHereTabs(getTabSignHere());
        signer.setTabs(tabs);

        // add recipients (in this case a single signer) to the envelope
        envDef.setRecipients(new Recipients());
        envDef.getRecipients().setSigners(new ArrayList<>());
        envDef.getRecipients().getSigners().add(signer);

        // send the envelope by setting |status| to "sent". To save as a draft set to "created"
        envDef.setStatus("sent");
        return envDef;
    }

    // creating tabs somewhere on the document for the signer to sign
    // default unit of measurement is pixels, can be mms, cms, inches also
    private static java.util.List<FullName> getTabFullName() {
        FullName tab = new FullName();
        tab.setDocumentId("1");
        tab.setPageNumber("1");
        tab.setRecipientId("1");
        tab.setXPosition("210");
        tab.setYPosition("636");
        java.util.List<FullName> tabs = new ArrayList<>();
        tabs.add(tab);
        return tabs;
    }

    private static java.util.List<DateSigned> getTabDateSigned() {
        DateSigned tab = new DateSigned();
        tab.setDocumentId("1");
        tab.setPageNumber("1");
        tab.setRecipientId("1");
        tab.setXPosition("210");
        tab.setYPosition("674");
        java.util.List<DateSigned> tabs = new ArrayList<>();
        tabs.add(tab);
        return tabs;
    }

    private static java.util.List<SignHere> getTabSignHere() {
        SignHere tab = new SignHere();
        tab.setDocumentId("1");
        tab.setPageNumber("1");
        tab.setRecipientId("1");
        tab.setXPosition("210");
        tab.setYPosition("689");
        java.util.List<SignHere> tabs = new ArrayList<>();
        tabs.add(tab);
        return tabs;
    }

    private static Signer getSigner() {
        // add a recipient to sign the document, identified by name and email we used above
        Signer signer = new Signer();
        signer.setEmail("agoncalves@allcraft.io");
        signer.setName("Donald Duck");
        signer.setRoleName("CTO");
        signer.setRecipientId("1");
        return signer;
    }

    private static java.util.List<Document> getDocument(String pdfFile) throws IOException {
        Path path = Paths.get(pdfFile);
        byte[] fileBytes = Files.readAllBytes(path);

        // add a document to the envelope
        Document doc = new Document();
        String base64Doc = Base64.getEncoder().encodeToString(fileBytes);
        doc.setDocumentBase64(base64Doc);
        doc.setName("Name of the document in DocuSign");
        doc.setDocumentId("1");

        java.util.List<Document> docs = new ArrayList<>();
        docs.add(doc);
        return docs;
    }

    static {
        Properties prop = new Properties();
        try {
            prop.load(PdfSender.class.getClassLoader().getResourceAsStream("docusign.properties"));
        } catch (Exception e) {
            LOG.severe("You must have a docusign.properties files defining the username/password and integrator key of DocuSign in your classpath");
            throw new IllegalArgumentException(e);
        }

        //get the property value and print it out
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        integratorKey = prop.getProperty("integratorKey");
    }

    private static void initializeDocusignAPI() throws ApiException {

        // initialize client for desired environment and add X-DocuSign-Authentication header
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(DOCUSIGN_API);

        // configure 'X-DocuSign-Authentication' authentication header
        String authHeader = "{\"Username\":\"" + username + "\",\"Password\":\"" + password + "\",\"IntegratorKey\":\"" + integratorKey + "\"}";
        apiClient.addDefaultHeader("X-DocuSign-Authentication", authHeader);
        Configuration.setDefaultApiClient(apiClient);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        // STEP 1: AUTHENTICATE TO RETRIEVE ACCOUNTID & BASEURL
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        AuthenticationApi authApi = new AuthenticationApi();
        LoginInformation loginInfo = authApi.login();

        // parse first account ID (user might belong to multiple accounts) and baseUrl
        accountId = loginInfo.getLoginAccounts().get(0).getAccountId();
        String baseUrl = loginInfo.getLoginAccounts().get(0).getBaseUrl();
        String[] accountDomain = baseUrl.split("/v2");

        // below code required for production, no effect in demo (same domain)
        apiClient.setBasePath(accountDomain[0]);
        Configuration.setDefaultApiClient(apiClient);
    }
}
