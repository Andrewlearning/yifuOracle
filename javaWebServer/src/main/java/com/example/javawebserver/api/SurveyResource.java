package com.example.javawebserver.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.loggingingestion.LoggingClient;
import com.oracle.bmc.loggingingestion.model.LogEntry;
import com.oracle.bmc.loggingingestion.model.PutLogsDetails;
import com.oracle.bmc.loggingingestion.model.LogEntryBatch;
import com.oracle.bmc.loggingingestion.requests.PutLogsRequest;
import com.oracle.bmc.loggingingestion.responses.PutLogsResponse;

import java.util.Date;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import com.example.javawebserver.model.SurveyDataModel;

@Path("/surveys")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SurveyResource {

    private static final String CONFIG_PATH = "/root/.oci/config";
    private static final String LOG_OCID = "ocid1.log.oc1.ap-singapore-1.amaaaaaaybq4p6yaq65dmqohulmmau6eusn77bgh72yiezlz5sshfpfps3uq";
    private static final Set<String> VALID_GENDERS = new HashSet<String>() {{
        add("male");
        add("female");
        add("other");
    }};

    private boolean isValidSurveyData(SurveyDataModel data) {
        if (data.score < 1 || data.score > 5) {
            return false;
        }
        if (data.age < 0 || data.age > 150) {
            return false;
        }
        if (data.gender == null || data.gender.isEmpty() || !VALID_GENDERS.contains(data.gender)) {
            return false;
        }
        return true;
    }

    @POST
    public Response receiveSurvey(SurveyDataModel data) {
        if (!isValidSurveyData(data)) {
            System.out.println("Received survey: " + data);
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid survey input").build();
        }
        System.out.println("Received survey: " + data);
        
        try {
            ConfigFileAuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(CONFIG_PATH, "DEFAULT");
            LoggingClient loggingClient = new LoggingClient(provider);


            LogEntry logEntry = LogEntry.builder()
                    .data(data.toJsonString())
                    .id(UUID.randomUUID().toString())
                    .time(new Date())
                    .build();

            LogEntryBatch entryBatch = LogEntryBatch.builder()
                    .entries(Collections.singletonList(logEntry))
                    .source("survey-service")
                    .type("survey.received")
                    .subject("survey-log")
                    .defaultlogentrytime(new Date())
                    .build();

            PutLogsDetails logsDetails = PutLogsDetails.builder()
                    .specversion("1.0")
                    .logEntryBatches(Collections.singletonList(entryBatch))
                    .build();

            PutLogsRequest putLogsRequest = PutLogsRequest.builder()
                    .logId(LOG_OCID)
                    .putLogsDetails(logsDetails)
                    .build();

            PutLogsResponse response = loggingClient.putLogs(putLogsRequest);
            loggingClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error logging to OCI").build();
        }

        return Response.ok("{\"status\": \"received\"}").build();
    }
}