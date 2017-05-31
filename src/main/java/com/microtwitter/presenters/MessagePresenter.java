package com.microtwitter.presenters;

import com.microtwitter.messages.Message;

public interface MessagePresenter {
    String present(Message message);
}
