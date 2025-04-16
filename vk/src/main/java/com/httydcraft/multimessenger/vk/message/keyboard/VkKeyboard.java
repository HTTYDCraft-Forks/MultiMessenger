package com.httydcraft.multimessenger.vk.message.keyboard;

import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.Button;
import com.httydcraft.multimessenger.core.keyboard.DefaultKeyboard;
import com.httydcraft.multimessenger.vk.message.keyboard.button.VkButton;

/**
 * VK implementation of a keyboard for messages.
 */
//region VkKeyboard Class
public class VkKeyboard extends DefaultKeyboard {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Constructs an empty VkKeyboard.
     */
    public VkKeyboard() {
        logger.atFine().log("VkKeyboard created (empty)");
    }

    /**
     * Constructs a VkKeyboard from a VK API keyboard object.
     * @param wrappingKeyboard VK API keyboard
     */
    public VkKeyboard(com.vk.api.sdk.objects.messages.Keyboard wrappingKeyboard) {
        this.buttons = wrappingKeyboard
                .getButtons().stream().map(listButtons -> listButtons.stream()
                        .map(button -> new VkButton(button).as(Button.class)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.keyboardType = Optional.ofNullable(wrappingKeyboard.getInline()).orElse(false) ? KeyboardType.inline() : KeyboardType.reply();
        logger.atFine().log("VkKeyboard created from VK API keyboard");
    }

    /**
     * Creates a VK API keyboard from this VkKeyboard.
     * @return VK API keyboard
     */
    public com.vk.api.sdk.objects.messages.Keyboard create() {
        com.vk.api.sdk.objects.messages.Keyboard keyboard = new com.vk.api.sdk.objects.messages.Keyboard();
        keyboard.setButtons(buttons.stream().map(listButtons -> listButtons.stream()
                        .map(button -> button.as(VkButton.class).create()).collect(Collectors.toList()))
                .collect(Collectors.toList()));
        keyboard.setInline(keyboardType.isInline());
        logger.atFine().log("VK API keyboard created from VkKeyboard");
        return keyboard;
    }

    /**
     * Builder for VkKeyboard.
     */
    public static class Builder extends DefaultKeyboardBuilder {
        public Builder() {
            super(new VkKeyboard());
        }
    }
}
//endregion
