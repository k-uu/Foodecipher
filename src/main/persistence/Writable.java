package persistence;

import org.json.JSONObject;

public interface Writable {

    // Effects: converts this to a JSON object
    JSONObject toJson();

}
