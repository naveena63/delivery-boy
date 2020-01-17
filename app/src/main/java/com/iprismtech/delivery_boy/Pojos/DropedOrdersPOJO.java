package com.iprismtech.delivery_boy.Pojos;

import java.util.List;

public class DropedOrdersPOJO {

    /**
     * status : true
     * message : Data Fetch Successfully!
     * response : [{"order_id":47,"invoice_id":521927,"home_items_delivered":"yes"}]
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
         * order_id : 47
         * invoice_id : 521927
         * home_items_delivered : yes
         */

        private int order_id;
        private int invoice_id;
        private String home_items_delivered;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getInvoice_id() {
            return invoice_id;
        }

        public void setInvoice_id(int invoice_id) {
            this.invoice_id = invoice_id;
        }

        public String getHome_items_delivered() {
            return home_items_delivered;
        }

        public void setHome_items_delivered(String home_items_delivered) {
            this.home_items_delivered = home_items_delivered;
        }
    }
}
