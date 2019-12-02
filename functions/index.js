"use-strict";
const functions = require("firebase-functions");

var admin = require("firebase-admin");
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.database

  .ref("/notification/{notificationId}")
  .onWrite((change, context) => {

    const idOrder = change.after.child("idOrder").val();

    const idDriver = change.after.child("idDriver").val();

    return admin.database().ref().child("Driver").child("Car").child(idDriver).child("token").once('value')
      .then(function (snapshot) {
        var token_id = snapshot.val();


        console.log(token_id);


        var payload = {
          data: {
            idOrder: idOrder,
            idDriver: idDriver
          },
          token: token_id


            .ref("/article/{articleId}")
            .onWrite((change, context) => {

              const author = change.after.child("author").val();
              const title = change.after.child("title").val();
              var iduser = change.after.child("iduser").val();

              console.log(iduser)

              var str1 = "Author is ";
              var str = str1.concat(author);
              console.log(str);
              // var tokenRE = 'fvI0xGm3N84:APA91bHCwyzqO4pgJSgTjyjRzz34AhyJ-x_qYP0KevtbLfz3kVr28LtsInTf5D3AYLpeZ2Db99nGczLCM0X0ZN4xhvhlSyBaiHkGi1SEOQz8r0ay6VxBqq57x9ZHa3NkiJVPLGSuisZN';

              return admin.database().ref().child("User").child(iduser).child("token").once('value')
                .then(function (snapshot) {
                  // var username = (snapshot.val() && snapshot.val().username) || 'Anonymous';
                  // send thu di
                  var tokenRE = snapshot.val();

                  var strtoken = "Token is ";
                  var stre = strtoken.concat(tokenRE);
                  console.log(stre);

                  var topic = "android";
                  var payload = {
                    data: {
                      title: author,
                      author: title
                    },
                    token: tokenRE

                  };
                  return admin
                    .messaging()
                    .send(payload)
                    .then(function (response) {
                      return console.log("Successfully sent message:", response);
                    })
                    .catch(function (error) {
                      return console.log("Error sending message:", error);

                    });
                });

            }),
        }
      })
  })
