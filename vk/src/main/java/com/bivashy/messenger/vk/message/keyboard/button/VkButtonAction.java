package com.bivashy.messenger.vk.message.keyboard.button;

import com.bivashy.messenger.common.button.ButtonAction;

public class VkButtonAction implements ButtonAction {

    private final Type type;

    public VkButtonAction(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getRawType() {
        return type.rawType();
    }

    public enum Type {
        TEXT, CALLBACK, OPEN_LINK;

        public String rawType() {
            return name().toLowerCase();
        }
    }
    public static class Builder implements ButtonActionBuilder {

        // TODO: Use enum instead of raw string
        @Override
        public VkButtonAction reply() {
            return new VkButtonAction(Type.TEXT);
        }

        @Override
        public VkButtonAction callback() {
            return new VkButtonAction(Type.CALLBACK);
        }

        @Override
        public VkButtonAction link() {
            return new VkButtonAction(Type.OPEN_LINK);
        }

    }

}
