/**
 * Created by karas on 24.11.2016.
 */
chrome.runtime.onMessage.addListener(function (msg, sender, value) {
    // First, validate the message's structure
    if ((msg.from === 'content') && (msg.subject === 'storeIt')) {
        chrome.storage.sync.set({'oldValue': value});
    }
    if ((msg.from === 'content') && (msg.subject === 'getValue')) {
        var val = chrome.storage.sync.get(value);
        chrome.runtime.sendMessage({
            from: 'background',
            subject: ''
        })
    }
});