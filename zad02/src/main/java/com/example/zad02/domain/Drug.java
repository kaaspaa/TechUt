package com.example.zad02.domain;

public class Drug{

        private static long counter = 0;
        private long id;
        private String name;
        private double prize;
        private int yoe;
        private boolean isChildrenFriendly;


        public Drug(String name, double prize, int yoe, boolean isChildrenFriendly) {
                super();
                this.name = name;
                this.prize = prize;
                this.yoe = yoe;
                this.isChildrenFriendly = isChildrenFriendly;
        }

        public long getId() {
                return id;
        }
        public void setId(long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) {
                this.name = name;
        }
        public double getPrize() {
                return prize;
        }
        public void setPrize(int prize) {
                this.prize = prize;
        }
        public int getYoe() {
                return yoe;
        }
        public void setYoe(int yoe) { this.yoe = yoe; }
        public boolean getIsChildrenFriendly() { return isChildrenFriendly; }
        public void setIsChildrenFriendly(boolean isChildrenFriendly) { this.isChildrenFriendly = isChildrenFriendly; }

}

