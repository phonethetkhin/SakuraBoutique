package com.example.sakuraboutique.Models;

public class Items
    {
        private String ProductID,Color,Size,Quantity;

        public Items(String productID, String color, String size, String quantity) {
            ProductID = productID;
            Color = color;
            Size = size;
            Quantity = quantity;
        }

        public Items() {
        }

        public String getProductID() {
            return ProductID;
        }

        public String getColor() {
            return Color;
        }

        public String getSize() {
            return Size;
        }

        public String getQuantity() {
            return Quantity;
        }
    }