package in.devstream.controller;

import in.devstream.openapi.api.HealthApi;
import in.devstream.openapi.model.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;
@Controller
public class HealthController implements HealthApi {
    public ResponseEntity<HealthResponse> healthCheck() {
        HealthResponse r = new HealthResponse(OffsetDateTime.now(), 100, "Server Running Fine");
        return new ResponseEntity<HealthResponse>(r, HttpStatus.OK);
    }
}
