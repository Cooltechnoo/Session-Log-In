package de.Cooltechno.sessionlogin.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SetSession {
    public static String username = "";
    public static String uuidStr = "";
    public static String accessToken = "";
    public static boolean useOriginal = true;

    public static final Map<String, SessionData> history = new LinkedHashMap<>();

    public static class SessionData {
        public final String name, uuid, token;
        public long lastClickTime = 0;
        public boolean isValid = true; // Default to true until checked

        public SessionData(String name, String uuid, String token) {
            this.name = name;
            this.uuid = uuid;
            this.token = token;
        }
    }

    public static void loginWithToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return;
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            JsonObject json = JsonParser.parseString(payloadJson).getAsJsonObject();

            if (json.has("pfd")) {
                JsonObject profile = json.getAsJsonArray("pfd").get(0).getAsJsonObject();
                String name = profile.get("name").getAsString();
                String rawId = profile.get("id").getAsString();
                // Format raw hex ID into a standard UUID string
                String formattedUuid = rawId.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");

                applySession(name, formattedUuid, token);

                SessionData data = new SessionData(name, formattedUuid, token);
                history.remove(name);
                history.put(name, data);

                // Automatically validate the new session in the background
                validateSession(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateSession(SessionData data) {
        CompletableFuture.runAsync(() -> {
            try {
                URL url = new URL("https://sessionserver.mojang.com/session/minecraft/join");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setConnectTimeout(3000);
                // If it returns 401, it's definitely invalid
                data.isValid = (con.getResponseCode() != 401);
            } catch (Exception e) {
                data.isValid = false;
            }
        });
    }

    public static void applySession(String name, String uuid, String token) {
        username = name;
        uuidStr = uuid;
        accessToken = token;
        useOriginal = false;
    }

    public static UUID getUuid() {
        try { return UUID.fromString(uuidStr); } catch (Exception e) { return UUID.randomUUID(); }
    }
}