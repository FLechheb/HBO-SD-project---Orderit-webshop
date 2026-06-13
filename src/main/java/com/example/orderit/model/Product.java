
package com.example.orderit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {


        @JsonProperty("naam")
        protected String name;

        @JsonProperty("price")
        protected double price;

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDisplayTitle() {
            return name;
        }

        public String getDisplaySubtitle() {
            return "";
        }

        public String getDisplayDescription() {
            return "";
        }
    }