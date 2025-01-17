package in.devstream.controller;

import in.devstream.openapi.api.PlatformsApi;
import in.devstream.openapi.model.Platform;
import in.devstream.openapi.model.PlatformSyncRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public class PlatformsController implements PlatformsApi {

    @Override
    public ResponseEntity<Void> connectPlatform(Platform platformId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> disconnectPlatform(Platform platformId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> syncAllExternalPlatforms(@Valid PlatformSyncRequest platformSyncRequest) {
        return null;
    }
}
