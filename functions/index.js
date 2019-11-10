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
    .then(function(snapshot){
      var token_id = snapshot.val();


      console.log(token_id);


      var payload = {
        data: {
          idOrder: idOrder,
          idDriver: idDriver
        },
        token : token_id
  
      };
      return admin
        .messaging()
        .send(payload)
        .then(function(response) {
          return console.log("Successfully sent message:", response);
        })
        .catch(function(error) {
          return console.log("Error sending message:", error);
        });


    
  });
});
