package id.web.alterationstudio.sewainaja.testerapi.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by febrian on 11/22/16.
 */
public class GetApi {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("api_key")
        @Expose
        private String apiKey;
        @SerializedName("expired")
        @Expose
        private String expired;

        /**
         *
         * @return
         * The status
         */
        public Boolean getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(Boolean status) {
            this.status = status;
        }

        /**
         *
         * @return
         * The msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         *
         * @param msg
         * The msg
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         *
         * @return
         * The apiKey
         */
        public String getApiKey() {
            return apiKey;
        }

        /**
         *
         * @param apiKey
         * The api_key
         */
        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         *
         * @return
         * The expired
         */
        public String getExpired() {
            return expired;
        }

        /**
         *
         * @param expired
         * The expired
         */
        public void setExpired(String expired) {
            this.expired = expired;
        }
}
