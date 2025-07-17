package com.example.dynamicui;

import java.util.ArrayList;
import java.util.List;

public class ToolbarConfig {

    public interface OnMenuClickListener {
        void onMenuItemClick(String id);
    }

    public enum Position {
        TOP, BOTTOM
    }

    public boolean titleVisible;
    public boolean menuItemsVisible;
    public String title;
    public List<ToolbarMenuItem> menuItems;
    public Position toolbarPosition;
    public OnMenuClickListener onMenuClickListener;

    private ToolbarConfig(Builder builder) {
        this.titleVisible = builder.titleVisible;
        this.menuItemsVisible = builder.menuItemsVisible;
        this.title = builder.title;
        this.menuItems = builder.menuItems;
        this.toolbarPosition = builder.toolbarPosition;
        this.onMenuClickListener = builder.onMenuClickListener;
    }

    public static class Builder {
        private boolean titleVisible = true;
        private boolean menuItemsVisible = true;
        private String title = "";
        private List<ToolbarMenuItem> menuItems = new ArrayList<>();
        private Position toolbarPosition = Position.TOP;
        private OnMenuClickListener onMenuClickListener;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitleVisible(boolean visible) {
            this.titleVisible = visible;
            return this;
        }

        public Builder setMenuItemsVisible(boolean visible) {
            this.menuItemsVisible = visible;
            return this;
        }

        public Builder setMenuItem(int iconResId, String id) {
            this.menuItems.add(new ToolbarMenuItem(iconResId, id));
            return this;
        }

        public Builder setToolbarPosition(Position position) {
            this.toolbarPosition = position;
            return this;
        }

        public Builder setOnMenuClickListener(OnMenuClickListener listener) {
            this.onMenuClickListener = listener;
            return this;
        }

        public ToolbarConfig build() {
            return new ToolbarConfig(this);
        }
    }
}