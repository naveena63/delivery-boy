package com.iprismtech.delivery_boy.Pojos;

import java.util.List;

public class PickedItemsPOJO {


    /**
     * status : true
     * message : Data Fetch Successfully!
     * response : [{"store_id":91,"shop_mobile":"1234567820","shop_name":"test","shop_image":"storage/img/5d7f5e0aac523.png","lat":"5","lng":"5","shop_locality":"film nagar","shop_landmark":null,"shop_pincode":"500034","shop_city_name":"5","shop_list":[{"orders_details_id":65,"product_id":6,"product_name":"apple","product_quantity":"cts","price":"10","ordered_quantity":"1","warehouse_items_collected":"yes","merchant_order_out":"no","warehouse_items_picked":"yes","warehouse_items_dropped":"yes","order_id":50,"invoice_id":680032,"created_at":"2019-10-15 15:43:47","store_id":91,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f5e0aac523.png","lat":"5","lng":"5","shop_name":"test"}]}]
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
         * store_id : 91
         * shop_mobile : 1234567820
         * shop_name : test
         * shop_image : storage/img/5d7f5e0aac523.png
         * lat : 5
         * lng : 5
         * shop_locality : film nagar
         * shop_landmark : null
         * shop_pincode : 500034
         * shop_city_name : 5
         * shop_list : [{"orders_details_id":65,"product_id":6,"product_name":"apple","product_quantity":"cts","price":"10","ordered_quantity":"1","warehouse_items_collected":"yes","merchant_order_out":"no","warehouse_items_picked":"yes","warehouse_items_dropped":"yes","order_id":50,"invoice_id":680032,"created_at":"2019-10-15 15:43:47","store_id":91,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f5e0aac523.png","lat":"5","lng":"5","shop_name":"test"}]
         */

        private int store_id;
        private String shop_mobile;
        private String shop_name;
        private String shop_image;
        private String lat;
        private String lng;
        private String shop_locality;
        private Object shop_landmark;
        private String shop_pincode;
        private String shop_city_name;
        private List<ShopListBean> shop_list;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getShop_mobile() {
            return shop_mobile;
        }

        public void setShop_mobile(String shop_mobile) {
            this.shop_mobile = shop_mobile;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_image() {
            return shop_image;
        }

        public void setShop_image(String shop_image) {
            this.shop_image = shop_image;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getShop_locality() {
            return shop_locality;
        }

        public void setShop_locality(String shop_locality) {
            this.shop_locality = shop_locality;
        }

        public Object getShop_landmark() {
            return shop_landmark;
        }

        public void setShop_landmark(Object shop_landmark) {
            this.shop_landmark = shop_landmark;
        }

        public String getShop_pincode() {
            return shop_pincode;
        }

        public void setShop_pincode(String shop_pincode) {
            this.shop_pincode = shop_pincode;
        }

        public String getShop_city_name() {
            return shop_city_name;
        }

        public void setShop_city_name(String shop_city_name) {
            this.shop_city_name = shop_city_name;
        }

        public List<ShopListBean> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBean> shop_list) {
            this.shop_list = shop_list;
        }

        public static class ShopListBean {
            /**
             * orders_details_id : 65
             * product_id : 6
             * product_name : apple
             * product_quantity : cts
             * price : 10
             * ordered_quantity : 1
             * warehouse_items_collected : yes
             * merchant_order_out : no
             * warehouse_items_picked : yes
             * warehouse_items_dropped : yes
             * order_id : 50
             * invoice_id : 680032
             * created_at : 2019-10-15 15:43:47
             * store_id : 91
             * status : items_reached_warehouse
             * shop_image : storage/img/5d7f5e0aac523.png
             * lat : 5
             * lng : 5
             * shop_name : test
             */

            private int orders_details_id;
            private int product_id;
            private String product_name;
            private String product_quantity;
            private String price;
            private String ordered_quantity;
            private String warehouse_items_collected;
            private String merchant_order_out;
            private String warehouse_items_picked;
            private String warehouse_items_dropped;
            private int order_id;
            private int invoice_id;
            private String created_at;
            private int store_id;
            private String status;
            private String shop_image;
            private String lat;
            private String lng;
            private String shop_name;
            public boolean isClicked = false;


            public boolean isClicked() {
                return isClicked;
            }

            public void setClicked(boolean clicked) {
                isClicked = clicked;
            }




            public int getOrders_details_id() {
                return orders_details_id;
            }

            public void setOrders_details_id(int orders_details_id) {
                this.orders_details_id = orders_details_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_quantity() {
                return product_quantity;
            }

            public void setProduct_quantity(String product_quantity) {
                this.product_quantity = product_quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOrdered_quantity() {
                return ordered_quantity;
            }

            public void setOrdered_quantity(String ordered_quantity) {
                this.ordered_quantity = ordered_quantity;
            }

            public String getWarehouse_items_collected() {
                return warehouse_items_collected;
            }

            public void setWarehouse_items_collected(String warehouse_items_collected) {
                this.warehouse_items_collected = warehouse_items_collected;
            }

            public String getMerchant_order_out() {
                return merchant_order_out;
            }

            public void setMerchant_order_out(String merchant_order_out) {
                this.merchant_order_out = merchant_order_out;
            }

            public String getWarehouse_items_picked() {
                return warehouse_items_picked;
            }

            public void setWarehouse_items_picked(String warehouse_items_picked) {
                this.warehouse_items_picked = warehouse_items_picked;
            }

            public String getWarehouse_items_dropped() {
                return warehouse_items_dropped;
            }

            public void setWarehouse_items_dropped(String warehouse_items_dropped) {
                this.warehouse_items_dropped = warehouse_items_dropped;
            }

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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getShop_image() {
                return shop_image;
            }

            public void setShop_image(String shop_image) {
                this.shop_image = shop_image;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }
        }
    }
}
