package com.android.sravnilka.ui.widgets;

/**
 * Created by Dmitry.Kalyuzhnyi on 11/14/2014.
 */
public interface IInputStorage {
    void onItemCreated(ClosebleEditField field);
    void onStartTyping(ClosebleEditField field);
    void onCloseButtonClicked(ClosebleEditField field);
}
