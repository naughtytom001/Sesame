package io.github.naughtytom001.sesame.data;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.naughtytom001.sesame.util.FileUtil;
import io.github.naughtytom001.sesame.util.Log;
import io.github.naughtytom001.sesame.util.idMap.UserIdMap;

import java.util.Objects;

/**
 * @author Constanline
 * @since 2023/08/18
 */
public class RuntimeInfo {
    private static final String TAG = RuntimeInfo.class.getSimpleName();

    private static RuntimeInfo instance;

    private final String userId;

    private JSONObject joAll;

    private JSONObject joCurrent;

    public enum RuntimeInfoKey {
        ForestPauseTime
    }

    public static RuntimeInfo getInstance() {
        if (instance == null || !Objects.equals(instance.userId, UserIdMap.getCurrentUid())) {
            instance = new RuntimeInfo();
        }
        return instance;
    }

    private RuntimeInfo() {
        userId = UserIdMap.getCurrentUid();
        String content = FileUtil.readFromFile(FileUtil.runtimeInfoFile(userId));
        try {
            joAll = new JSONObject(content);
        } catch (Exception ignored) {
            joAll = new JSONObject();
        }
        try {
            if (!joAll.has(userId)) {
                joAll.put(userId, new JSONObject());
            }
        } catch (Exception ignored) {
        }
        try {
            joCurrent = joAll.getJSONObject(userId);
        } catch (Exception ignored) {
            joCurrent = new JSONObject();
        }
    }

    public void save() {
        FileUtil.write2File(joAll.toString(), FileUtil.runtimeInfoFile(userId));
    }

    public Object get(RuntimeInfoKey key) throws JSONException {
        return joCurrent.opt(key.name());
    }

    public String getString(String key) {
        return joCurrent.optString(key);
    }

    public Long getLong(String key, long def) {
        return joCurrent.optLong(key, def);
    }

    public boolean getBool(String key, boolean def) {
        return joCurrent.optBoolean(key, def);
    }

    public String getString(RuntimeInfoKey key) {
        return joCurrent.optString(key.name());
    }

    public Long getLong(RuntimeInfoKey key) {
        return joCurrent.optLong(key.name(), 0L);
    }

    public void put(RuntimeInfoKey key, Object value) {
        put(key.name(), value);
    }

    public void put(String key, Object value) {
        try {
            joCurrent.put(key, value);
            joAll.put(userId, joCurrent);
        } catch (JSONException e) {
            Log.i(TAG, "put err:");
            Log.printStackTrace(TAG, e);
        }
        save();
    }
}
