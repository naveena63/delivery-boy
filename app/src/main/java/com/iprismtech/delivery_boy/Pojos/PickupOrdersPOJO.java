package com.iprismtech.delivery_boy.Pojos;

import java.util.List;

public class PickupOrdersPOJO {

    /**
     * status : true
     * message : Data Fetch Successfully!
     * response : [{"order_id":46,"invoice_id":824393,"warehouse_items_dropped":"yes"}]
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
         * order_id : 46
         * invoice_id : 824393
         * warehouse_items_dropped : yes
         */

        private int order_id;
        private int invoice_id;
        private String warehouse_items_dropped;

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

        public String getWarehouse_items_dropped() {
            return warehouse_items_dropped;
        }

        public void setWarehouse_items_dropped(String warehouse_items_dropped) {
            this.warehouse_items_dropped = warehouse_items_dropped;
        }
    }
}
