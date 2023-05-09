package com.bivashy.messenger.discord.message;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.bivashy.messenger.common.ApiProvider;
import com.bivashy.messenger.common.file.MessengerFile;
import com.bivashy.messenger.common.identificator.Identificator;
import com.bivashy.messenger.common.message.DefaultMessage;
import com.bivashy.messenger.discord.message.keyboard.DiscordKeyboard;
import com.bivashy.messenger.discord.provider.DiscordApiProvider;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

public class DiscordMessage extends DefaultMessage {
    private static DiscordApiProvider defaultApiProvider;

    public DiscordMessage(String text) {
        super(text);
    }

    public static DiscordApiProvider getDefaultApiProvider() {
        return defaultApiProvider;
    }

    public static void setDefaultApiProvider(DiscordApiProvider defaultApiProvider) {
        DiscordMessage.defaultApiProvider = defaultApiProvider;
    }

    @Override
    public void send(Identificator identificator) {
        if (defaultApiProvider == null)
            throw new NullPointerException("Default discord api provider was not defined. Define with static DiscordMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider);
    }

    @Override
    public void send(Identificator identificator, ApiProvider apiProvider) {
        JDA jda = apiProvider.as(DiscordApiProvider.class).getJDA();
        MessageChannel channel = null;
        if (identificator.isNumber())
            channel = jda.getChannelById(MessageChannel.class, identificator.asNumber());
        if (identificator.isString())
            channel = jda.getChannelById(MessageChannel.class, identificator.asString());
        if (channel == null)
            return;
        MessageCreateBuilder messageCreateBuilder = new MessageCreateBuilder().setContent(text);
        if (files != null)
            messageCreateBuilder.setFiles(Arrays.stream(files).map(MessengerFile::getFile).map(FileUpload::fromData).collect(Collectors.toList()));
        if (keyboard != null)
            keyboard.as(DiscordKeyboard.class).create().forEach(actionRow -> messageCreateBuilder.addActionRow(actionRow.getComponents()));
        channel.sendMessage(messageCreateBuilder.build()).queue();
    }

    public static class Builder extends DefaultMessageBuilder {
        public Builder(String text) {
            super(new DiscordMessage(text));
        }
    }
}
