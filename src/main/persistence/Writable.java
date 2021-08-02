package persistence;

import org.json.JSONObject;

public interface Writable {

    // EFFECTS: converts this to a JSONObject
    JSONObject toJson();

}
