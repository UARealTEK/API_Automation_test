package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xAuthToken","xChallenger","secretNote","challengeStatus" })
public class ChallengerBody {
    private String xAuthToken = "7b1c8328-fa41-4ec8-a606-11c9e5f6b13c";
    private String xChallenger;
    private String secretNote = "";
    private ChallengeStatus challengeStatus = new ChallengeStatus(); // Nested class with default values
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public String createFullChallengerJSONBody() throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "PUT_RESTORABLE_CHALLENGER_PROGRESS_STATUS",
        "GET_TODOS",
        "PUT_NEW_RESTORED_CHALLENGER_PROGRESS_STATUS",
        "POST_TODOS",
        "OVERRIDE_PATCH_HEARTBEAT_500",
        "POST_TODOS_TOO_LONG_DESCRIPTION_LENGTH",
        "GET_RESTORABLE_CHALLENGER_PROGRESS_STATUS",
        "POST_SECRET_NOTE_401",
        "PUT_TODOS_PARTIAL_200",
        "GET_TODOS_FILTERED",
        "GET_TODO_404",
        "PUT_TODOS_400_NO_AMEND_ID",
        "GET_HEARTBEAT_204",
        "POST_TODOS_INVALID_EXTRA_FIELD",
        "POST_SECRET_NOTE_BEARER_200",
        "POST_CREATE_XML_ACCEPT_JSON",
        "GET_ACCEPT_XML_PREFERRED",
        "POST_SECRET_NOTE_200",
        "CREATE_NEW_CHALLENGER",
        "POST_UPDATE_TODO",
        "GET_CHALLENGES",
        "GET_HEAD_TODOS",
        "POST_SECRET_NOTE_403",
        "GET_RESTORABLE_TODOS",
        "GET_ACCEPT_XML",
        "POST_TODOS_415",
        "GET_ACCEPT_JSON",
        "CREATE_SECRET_TOKEN_201",
        "OVERRIDE_DELETE_HEARTBEAT_405",
        "POST_TODOS_BAD_DONE_STATUS",
        "GET_SECRET_NOTE_200",
        "OVERRIDE_TRACE_HEARTBEAT_501",
        "POST_TODOS_404",
        "POST_CREATE_JSON_ACCEPT_XML",
        "GET_SECRET_NOTE_BEARER_200",
        "GET_TODO",
        "PUT_TODOS_FULL_200",
        "GET_ACCEPT_ANY_DEFAULT_JSON",
        "GET_SECRET_NOTE_401",
        "POST_MAX_OUT_TITLE_DESCRIPTION_LENGTH",
        "POST_CREATE_JSON",
        "PATCH_HEARTBEAT_500",
        "DELETE_A_TODO",
        "DELETE_ALL_TODOS",
        "POST_TODOS_TOO_LONG_PAYLOAD_SIZE",
        "TRACE_HEARTBEAT_501",
        "DELETE_HEARTBEAT_405",
        "POST_ALL_TODOS",
        "GET_SECRET_NOTE_403",
        "PUT_TODOS_MISSING_TITLE_400",
        "OPTIONS_TODOS",
        "GET_JSON_BY_DEFAULT_NO_ACCEPT",
        "POST_TODOS_TOO_LONG_TITLE_LENGTH",
        "PUT_RESTORABLE_TODOS",
        "GET_TODOS_NOT_PLURAL_404",
        "POST_CREATE_XML",
        "CREATE_SECRET_TOKEN_401",
        "PUT_TODOS_400",
        "GET_UNSUPPORTED_ACCEPT_406"
})
class ChallengeStatus {
    private boolean PUT_RESTORABLE_CHALLENGER_PROGRESS_STATUS = false;
    private boolean GET_TODOS = false;
    private boolean PUT_NEW_RESTORED_CHALLENGER_PROGRESS_STATUS = false;
    private boolean POST_TODOS = false;
    private boolean OVERRIDE_PATCH_HEARTBEAT_500 = false;
    private boolean POST_TODOS_TOO_LONG_DESCRIPTION_LENGTH = false;
    private boolean GET_RESTORABLE_CHALLENGER_PROGRESS_STATUS = false;
    private boolean POST_SECRET_NOTE_401 = false;
    private boolean PUT_TODOS_PARTIAL_200 = false;
    private boolean GET_TODOS_FILTERED = false;
    private boolean GET_TODO_404 = false;
    private boolean PUT_TODOS_400_NO_AMEND_ID = false;
    private boolean GET_HEARTBEAT_204 = false;
    private boolean POST_TODOS_INVALID_EXTRA_FIELD = false;
    private boolean POST_SECRET_NOTE_BEARER_200 = false;
    private boolean POST_CREATE_XML_ACCEPT_JSON = false;
    private boolean GET_ACCEPT_XML_PREFERRED = false;
    private boolean POST_SECRET_NOTE_200 = false;
    private boolean CREATE_NEW_CHALLENGER = false;
    private boolean POST_UPDATE_TODO = false;
    private boolean GET_CHALLENGES = false;
    private boolean GET_HEAD_TODOS = false;
    private boolean POST_SECRET_NOTE_403 = false;
    private boolean GET_RESTORABLE_TODOS = false;
    private boolean GET_ACCEPT_XML = false;
    private boolean POST_TODOS_415 = false;
    private boolean GET_ACCEPT_JSON = false;
    private boolean CREATE_SECRET_TOKEN_201 = false;
    private boolean OVERRIDE_DELETE_HEARTBEAT_405 = false;
    private boolean POST_TODOS_BAD_DONE_STATUS = false;
    private boolean GET_SECRET_NOTE_200 = false;
    private boolean OVERRIDE_TRACE_HEARTBEAT_501 = false;
    private boolean POST_TODOS_404 = false;
    private boolean POST_CREATE_JSON_ACCEPT_XML = false;
    private boolean GET_SECRET_NOTE_BEARER_200 = false;
    private boolean GET_TODO = false;
    private boolean PUT_TODOS_FULL_200 = false;
    private boolean GET_ACCEPT_ANY_DEFAULT_JSON = false;
    private boolean GET_SECRET_NOTE_401 = false;
    private boolean POST_MAX_OUT_TITLE_DESCRIPTION_LENGTH = false;
    private boolean POST_CREATE_JSON = false;
    private boolean PATCH_HEARTBEAT_500 = false;
    private boolean DELETE_A_TODO = false;
    private boolean DELETE_ALL_TODOS = false;
    private boolean POST_TODOS_TOO_LONG_PAYLOAD_SIZE = false;
    private boolean TRACE_HEARTBEAT_501 = false;
    private boolean DELETE_HEARTBEAT_405 = false;
    private boolean POST_ALL_TODOS = false;
    private boolean GET_SECRET_NOTE_403 = false;
    private boolean PUT_TODOS_MISSING_TITLE_400 = false;
    private boolean OPTIONS_TODOS = false;
    private boolean GET_JSON_BY_DEFAULT_NO_ACCEPT = false;
    private boolean POST_TODOS_TOO_LONG_TITLE_LENGTH = false;
    private boolean PUT_RESTORABLE_TODOS = false;
    private boolean GET_TODOS_NOT_PLURAL_404 = false;
    private boolean POST_CREATE_XML = false;
    private boolean CREATE_SECRET_TOKEN_401 = false;
    private boolean PUT_TODOS_400 = false;
    private boolean GET_UNSUPPORTED_ACCEPT_406 = false;
}
