package in.devstream.controller;

import in.devstream.openapi.api.UserApi;
import in.devstream.openapi.model.GetUserSettingsResponse;
import in.devstream.openapi.model.UpdateUserSettingsRequest;
import in.devstream.openapi.model.UserAnalytics;
import in.devstream.openapi.model.UserProfile;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public class UserController implements UserApi {


    @Override
    public ResponseEntity<Void> deleteUserProfile(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<UserAnalytics> getUserAnalytics() {
        return null;
    }

    @Override
    public ResponseEntity<UserProfile> getUserProfileInformation() {
        return null;
    }

    @Override
    public ResponseEntity<GetUserSettingsResponse> getUserSettings() {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUserProfileInformation(@Valid UserProfile userProfile) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUserSettings(@Valid UpdateUserSettingsRequest updateUserSettingsRequest) {
        return null;
    }
}
