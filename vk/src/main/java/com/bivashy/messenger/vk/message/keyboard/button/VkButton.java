package com.bivashy.messenger.vk.message.keyboard.button;

import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.Button.Color;
import api.longpoll.bots.model.objects.additional.buttons.CallbackButton;
import api.longpoll.bots.model.objects.additional.buttons.OpenLinkButton;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.additional.buttons.VKAppsButton;
import com.bivashy.messenger.common.button.DefaultButton;
import com.bivashy.messenger.vk.message.keyboard.button.VkButtonAction.Type;
import com.google.gson.JsonPrimitive;

public class VkButton extends DefaultButton {

    private static final VkButtonAction DEFAULT_ACTION = new VkButtonAction.Builder().reply();
    private static final VkButtonColor DEFAULT_COLOR = new VkButtonColor(Color.SECONDARY);

    public VkButton(Button wrappingButton) {
        super(getLabel(wrappingButton.getAction()));
        this.action = new VkButtonAction(Type.valueOf(wrappingButton.getAction().getType().toUpperCase()));
        this.color = new VkButtonColor(getColor(wrappingButton));
        switch (wrappingButton.getAction().getType()) {
            case "callback":
                this.actionData = wrappingButton.getAction().getPayload().toString();
                break;
            case "open_link":
                this.actionData = ((OpenLinkButton.Action) wrappingButton.getAction()).getLink();
                break;
            default:
                break;
        }
    }

    private static String getLabel(Button.Action action) {
        if (action instanceof CallbackButton.Action)
            return ((CallbackButton.Action) action).getLabel();
        if (action instanceof OpenLinkButton.Action)
            return ((OpenLinkButton.Action) action).getLabel();
        if (action instanceof TextButton.Action)
            return ((TextButton.Action) action).getLabel();
        if (action instanceof VKAppsButton.Action)
            return ((VKAppsButton.Action) action).getLabel();
        return null;
    }

    private static Button.Color getColor(Button button) {
        if (button instanceof CallbackButton)
            return ((CallbackButton) button).getColor();
        if (button instanceof TextButton)
            return ((TextButton) button).getColor();
        return Color.SECONDARY;
    }

    public VkButton(String label) {
        super(label);
    }

    public Button create() {
        VkButtonAction buttonAction = action.safeAs(VkButtonAction.class, DEFAULT_ACTION);
        VkButtonColor buttonColor = color.safeAs(VkButtonColor.class, DEFAULT_COLOR);
        VkButtonAction.Type type = buttonAction.getType();
        switch (type) {
            case TEXT:
                return new TextButton(buttonColor.getButtonColor(), new TextButton.Action(label));
            case CALLBACK:
                return new CallbackButton(buttonColor.getButtonColor(), new CallbackButton.Action(label, new JsonPrimitive(actionData)));
            case OPEN_LINK:
                return new OpenLinkButton(new OpenLinkButton.Action(actionData, label));
            default:
                throw new IllegalStateException("Cannot find button with type '" + type + "'");
        }
    }

    public static class Builder extends DefaultButtonBuilder {

        public Builder(String label) {
            super(new VkButton(label));
        }

    }

}
