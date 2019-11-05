"use-strict";
const functions = require("firebase-functions");

var admin = require("firebase-admin");
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.database
  .ref("/articles/{articleId}")
  .onWrite((change, context) => {
    const author = change.after.child("author").val();
    const title = change.after.child("title").val();

    var str1 = "Author is ";
    var str = str1.concat(author);
    console.log(str);

    var topic = "android";
    var payload = {
      data: {
        title: author,
        author: title
      }
    };
    return admin
      .messaging()
      .sendToTopic(topic,payload)
      .then(function(response) {
        return console.log("Successfully sent message:", response);
      })
      .catch(function(error) {
        return console.log("Error sending message:", error);
      });
    })
