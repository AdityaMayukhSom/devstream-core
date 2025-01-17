package in.devstream.controller;

import in.devstream.openapi.api.ContentApi;
import in.devstream.openapi.model.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ContentController implements ContentApi {

    @Override
    public ResponseEntity<Void> deleteContent(String contentId) {
        return null;
    }

    @Override
    public ResponseEntity<ContentListResponse> getAllContentByUserId(@Valid Optional<String> filter) {
        return null;
    }

    @Override
    public ResponseEntity<ContentAnalytics> getContentAnalytics(String contentId) {
        return null;
    }

    @Override
    public ResponseEntity<ContentDetails> getContentDetails(String contentId) {
        return null;
    }

    @Override
    public ResponseEntity<ContentStatusResponse> getContentStatus(String contentId) {
        return null;
    }

    @Override
    public ResponseEntity<CollaborationActionResponse> getPendingInvitations(String contentId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> sendCollaborationInvitations(String contentId, @Valid CollaborationRequest collaborationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePendingInvitesForContentById(String contentId, @Valid CollaborationActionResponse collaborationActionResponse) {
        return null;
    }
}
