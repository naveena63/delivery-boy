package com.iprismtech.delivery_boy.Pojos;

import java.util.List;

public class DropedsingleitemPOJO {


    /**
     * status : true
     * message : Data Fetch Successfully!
     * response : [{"shop_name":"test","shop_image":"storage/img/5d7f5e0aac523.png","d_contact_name":"home","d_store_name":null,"d_address":"Post Building 8-2-503-c-4","d_lat":null,"d_lng":null,"contact_no":"8529631470","d_locality":"Chekpost","d_landmark":"backend","d_pincode":"500000","d_city":null,"shop_list":[{"orders_details_id":59,"product_id":1,"product_name":"Mom's Magic","product_quantity":"1 pack","price":"10","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":91,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f5e0aac523.png","lat":"5","lng":"5","store_name":"aajooba","shop_name":"test"}]},{"shop_name":"Shop Name","shop_image":"storage/img/5da4771e846d2.png","d_contact_name":"home","d_store_name":null,"d_address":"Post Building 8-2-503-c-4","d_lat":null,"d_lng":null,"contact_no":"8529631470","d_locality":"Chekpost","d_landmark":"backend","d_pincode":"500000","d_city":null,"shop_list":[{"orders_details_id":54,"product_id":4,"product_name":"Mango","product_quantity":"100","price":"10","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":92,"status":"items_reached_warehouse","shop_image":"storage/img/5da4771e846d2.png","lat":"17.4077591","lng":"78.42687029999999","store_name":"Mahesh","shop_name":"Shop Name"},{"orders_details_id":57,"product_id":5,"product_name":"Apple","product_quantity":"100","price":"15","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":92,"status":"items_reached_warehouse","shop_image":"storage/img/5da4771e846d2.png","lat":"17.4077591","lng":"78.42687029999999","store_name":"Mahesh","shop_name":"Shop Name"}]},{"shop_name":"Harish","shop_image":"storage/img/5d7f66e52b8a6.png","d_contact_name":"home","d_store_name":null,"d_address":"Post Building 8-2-503-c-4","d_lat":null,"d_lng":null,"contact_no":"8529631470","d_locality":"Chekpost","d_landmark":"backend","d_pincode":"500000","d_city":null,"shop_list":[{"orders_details_id":53,"product_id":9,"product_name":"hsn","product_quantity":"4646","price":"49979","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":95,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f66e52b8a6.png","lat":null,"lng":null,"store_name":"haish","shop_name":"Harish"},{"orders_details_id":55,"product_id":10,"product_name":"gtbt","product_quantity":"955","price":"592","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":95,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f66e52b8a6.png","lat":null,"lng":null,"store_name":"haish","shop_name":"Harish"},{"orders_details_id":56,"product_id":8,"product_name":"hshs","product_quantity":"646464","price":"4949","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":95,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f66e52b8a6.png","lat":null,"lng":null,"store_name":"haish","shop_name":"Harish"}]},{"shop_name":"test","shop_image":"storage/img/5da45141a2f7b.png","d_contact_name":"home","d_store_name":null,"d_address":"Post Building 8-2-503-c-4","d_lat":null,"d_lng":null,"contact_no":"8529631470","d_locality":"Chekpost","d_landmark":"backend","d_pincode":"500000","d_city":null,"shop_list":[{"orders_details_id":58,"product_id":3,"product_name":"Grapes","product_quantity":"1 Kg","price":"30","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":97,"status":"items_reached_warehouse","shop_image":"storage/img/5da45141a2f7b.png","lat":null,"lng":null,"store_name":"test","shop_name":"test"},{"orders_details_id":60,"product_id":2,"product_name":"Papaya","product_quantity":"15","price":"30","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":97,"status":"items_reached_warehouse","shop_image":"storage/img/5da45141a2f7b.png","lat":null,"lng":null,"store_name":"test","shop_name":"test"}]}]
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
         * shop_name : test
         * shop_image : storage/img/5d7f5e0aac523.png
         * d_contact_name : home
         * d_store_name : null
         * d_address : Post Building 8-2-503-c-4
         * d_lat : null
         * d_lng : null
         * contact_no : 8529631470
         * d_locality : Chekpost
         * d_landmark : backend
         * d_pincode : 500000
         * d_city : null
         * shop_list : [{"orders_details_id":59,"product_id":1,"product_name":"Mom's Magic","product_quantity":"1 pack","price":"10","ordered_quantity":"1","warehouse_items_dropped":"yes","home_start":"yes","home_items_collected":"yes","home_items_picked":"yes","home_items_delivered":"yes","order_id":47,"invoice_id":521927,"created_at":"2019-10-14 18:20:26","store_id":91,"status":"items_reached_warehouse","shop_image":"storage/img/5d7f5e0aac523.png","lat":"5","lng":"5","store_name":"aajooba","shop_name":"test"}]
         */

        private String shop_name;
        private String shop_image;
        private String d_contact_name;
        private Object d_store_name;
        private String d_address;
        private Object d_lat;
        private Object d_lng;
        private String contact_no;
        private String d_locality;
        private String d_landmark;
        private String d_pincode;
        private Object d_city;
        private List<ShopListBean> shop_list;

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

        public String getD_contact_name() {
            return d_contact_name;
        }

        public void setD_contact_name(String d_contact_name) {
            this.d_contact_name = d_contact_name;
        }

        public Object getD_store_name() {
            return d_store_name;
        }

        public void setD_store_name(Object d_store_name) {
            this.d_store_name = d_store_name;
        }

        public String getD_address() {
            return d_address;
        }

        public void setD_address(String d_address) {
            this.d_address = d_address;
        }

        public Object getD_lat() {
            return d_lat;
        }

        public void setD_lat(Object d_lat) {
            this.d_lat = d_lat;
        }

        public Object getD_lng() {
            return d_lng;
        }

        public void setD_lng(Object d_lng) {
            this.d_lng = d_lng;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getD_locality() {
            return d_locality;
        }

        public void setD_locality(String d_locality) {
            this.d_locality = d_locality;
        }

        public String getD_landmark() {
            return d_landmark;
        }

        public void setD_landmark(String d_landmark) {
            this.d_landmark = d_landmark;
        }

        public String getD_pincode() {
            return d_pincode;
        }

        public void setD_pincode(String d_pincode) {
            this.d_pincode = d_pincode;
        }

        public Object getD_city() {
            return d_city;
        }

        public void setD_city(Object d_city) {
            this.d_city = d_city;
        }

        public List<ShopListBean> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBean> shop_list) {
            this.shop_list = shop_list;
        }

        public static class ShopListBean {
            /**
             * orders_details_id : 59
             * product_id : 1
             * product_name : Mom's Magic
             * product_quantity : 1 pack
             * price : 10
             * ordered_quantity : 1
             * warehouse_items_dropped : yes
             * home_start : yes
             * home_items_collected : yes
             * home_items_picked : yes
             * home_items_delivered : yes
             * order_id : 47
             * invoice_id : 521927
             * created_at : 2019-10-14 18:20:26
             * store_id : 91
             * status : items_reached_warehouse
             * shop_image : storage/img/5d7f5e0aac523.png
             * lat : 5
             * lng : 5
             * store_name : aajooba
             * shop_name : test
             */

            private int orders_details_id;
            private int product_id;
            private String product_name;
            private String product_quantity;
            private String price;
            private String ordered_quantity;
            private String warehouse_items_dropped;
            private String home_start;
            private String home_items_collected;
            private String home_items_picked;
            private String home_items_delivered;
            private int order_id;
            private int invoice_id;
            private String created_at;
            private int store_id;
            private String status;
            private String shop_image;
            private String lat;
            private String lng;
            private String store_name;
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

            public String getWarehouse_items_dropped() {
                return warehouse_items_dropped;
            }

            public void setWarehouse_items_dropped(String warehouse_items_dropped) {
                this.warehouse_items_dropped = warehouse_items_dropped;
            }

            public String getHome_start() {
                return home_start;
            }

            public void setHome_start(String home_start) {
                this.home_start = home_start;
            }

            public String getHome_items_collected() {
                return home_items_collected;
            }

            public void setHome_items_collected(String home_items_collected) {
                this.home_items_collected = home_items_collected;
            }

            public String getHome_items_picked() {
                return home_items_picked;
            }

            public void setHome_items_picked(String home_items_picked) {
                this.home_items_picked = home_items_picked;
            }

            public String getHome_items_delivered() {
                return home_items_delivered;
            }

            public void setHome_items_delivered(String home_items_delivered) {
                this.home_items_delivered = home_items_delivered;
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
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
