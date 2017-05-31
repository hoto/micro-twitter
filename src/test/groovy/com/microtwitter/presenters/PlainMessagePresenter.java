package com.microtwitter.presenters;

import com.microtwitter.messages.Message;

public class PlainMessagePresenter implements MessagePresenter {

    @Override
    public String present(Message message) {
        return message.userName + " - " + message.text;
    }
}
