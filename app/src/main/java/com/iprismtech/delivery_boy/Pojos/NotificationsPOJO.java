package com.iprismtech.delivery_boy.Pojos;

import java.util.List;

public class NotificationsPOJO {


    /**
     * status : true
     * message : Data fetched Successfully!
     * response : [{"id":43,"title":"test agent","description":"test agent","type":"agent","created_at":"2019-10-18 15:04:29","updated_at":null},{"id":42,"title":"test agent","description":"test\r\nagent","type":"agent","created_at":"2019-10-18 14:54:28","updated_at":null},{"id":41,"title":"test agent","description":"test agent","type":"agent","created_at":"2019-10-18 12:15:39","updated_at":null},{"id":40,"title":"fg","description":"fdg","type":"agent","created_at":"2019-10-18 12:15:08","updated_at":null}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 43
         * title : test agent
         * description : test agent
         * type : agent
         * created_at : 2019-10-18 15:04:29
         * updated_at : null
         */

        private int id;
        private String title;
        private String description;
        private String type;
        private String created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }
}
