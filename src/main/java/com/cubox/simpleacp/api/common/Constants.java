package com.cubox.simpleacp.api.common;

public class Constants {

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    private Constants() {
    }

    public class Profile {

        private Profile() {
        }

        public static final String DEVELOPMENT = "dev";
        public static final String PRODUCTION = "prd";

    }

    public class API {

        private API() {
        }

        // HEALTH
        public static final String API_HEALTH = "/health";

        // NO-AUTH
        public static final String API_PREFIX = "/simpleacp/v1";
        public static final String API_AUTH = "/auth";
        public static final String API_CODE = "/code";
        public static final String API_USER = "/user";

        // AUTH REQUIRED
        public static final String API_AUTH_PREFIX = API_PREFIX + "/app";
        public static final String API_PROFILE = "/profile";
        public static final String API_ROLE = "/role";
        public static final String API_ADMIN = "/admin";
        public static final String API_MENU = "/menu";
        public static final String API_SETTING = "/setting";

    }


}
