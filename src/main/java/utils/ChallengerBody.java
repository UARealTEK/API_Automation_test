package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChallengerBody {
    private boolean PUT_RESTORABLE_CHALLENGER_PROGRESS_STATUS;
    private boolean GET_TODOS;
    private boolean PUT_NEW_RESTORED_CHALLENGER_PROGRESS_STATUS;
    private boolean POST_TODOS;
    private boolean OVERRIDE_PATCH_HEARTBEAT_500;
    private boolean POST_TODOS_TOO_LONG_DESCRIPTION_LENGTH;
    private boolean GET_RESTORABLE_CHALLENGER_PROGRESS_STATUS;
    private boolean POST_SECRET_NOTE_401;
    private boolean PUT_TODOS_PARTIAL_200;
    private boolean GET_TODOS_FILTERED;
    private boolean GET_TODO_404;
    private boolean PUT_TODOS_400_NO_AMEND_ID;
    private boolean GET_HEARTBEAT_204;
    private boolean POST_TODOS_INVALID_EXTRA_FIELD;
    private boolean POST_SECRET_NOTE_BEARER_200;
    private boolean POST_CREATE_XML_ACCEPT_JSON;
    private boolean GET_ACCEPT_XML_PREFERRED;
    private boolean POST_SECRET_NOTE_200;
    private boolean CREATE_NEW_CHALLENGER;
    private boolean POST_UPDATE_TODO;
    private boolean GET_CHALLENGES;
    private boolean GET_HEAD_TODOS;
    private boolean POST_SECRET_NOTE_403;
    private boolean GET_RESTORABLE_TODOS;
    private boolean GET_ACCEPT_XML;
    private boolean POST_TODOS_415;
    private boolean GET_ACCEPT_JSON;
    private boolean CREATE_SECRET_TOKEN_201;
    private boolean OVERRIDE_DELETE_HEARTBEAT_405;
    private boolean POST_TODOS_BAD_DONE_STATUS;
    private boolean GET_SECRET_NOTE_200;
    private boolean OVERRIDE_TRACE_HEARTBEAT_501;
    private boolean POST_TODOS_404;
    private boolean POST_CREATE_JSON_ACCEPT_XML;
    private boolean GET_SECRET_NOTE_BEARER_200;
    private boolean GET_TODO;
    private boolean PUT_TODOS_FULL_200;
    private boolean GET_ACCEPT_ANY_DEFAULT_JSON;
    private boolean GET_SECRET_NOTE_401;
    private boolean POST_MAX_OUT_TITLE_DESCRIPTION_LENGTH;
    private boolean POST_CREATE_JSON;
    private boolean PATCH_HEARTBEAT_500;
    private boolean DELETE_A_TODO;
    private boolean DELETE_ALL_TODOS;
    private boolean POST_TODOS_TOO_LONG_PAYLOAD_SIZE;
    private boolean TRACE_HEARTBEAT_501;
    private boolean DELETE_HEARTBEAT_405;
    private boolean POST_ALL_TODOS;
    private boolean GET_SECRET_NOTE_403;
    private boolean PUT_TODOS_MISSING_TITLE_400;
    private boolean OPTIONS_TODOS;
    private boolean GET_JSON_BY_DEFAULT_NO_ACCEPT;
    private boolean POST_TODOS_TOO_LONG_TITLE_LENGTH;
    private boolean PUT_RESTORABLE_TODOS;
    private boolean GET_TODOS_NOT_PLURAL_404;
    private boolean POST_CREATE_XML;
    private boolean CREATE_SECRET_TOKEN_401;
    private boolean PUT_TODOS_400;
    private boolean GET_UNSUPPORTED_ACCEPT_406;

    public ChallengerBody() {}

    public String createFullChallengerJSONBody() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
