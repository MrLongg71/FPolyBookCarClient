"use-strict";
const functions = require("firebase-functions");

var admin = require("firebase-admin");
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.database
  .ref("/notification/{notificationId}")
  .onWrite((change, context) => {
    
    const idOrder = change.after.child("idOrder").val();
    const idDriver = change.after.child("idDriver").val();
    const event = change.after.child("event").val();
        return admin.database().ref().child("Driver").child("Car").child(idDriver).child("token").once('value')
    .then(function(snapshot){
      var token_id = snapshot.val();
      console.log(token_id);
      var payload = {
        data: {
          idOrder: idOrder,
          idDriver: idDriver,
          event : event,
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



// functions.sendNotification = functions.database
//   .ref("/notificationRV/{notificationRVId}")
//   .onWrite((change, context) => {
    
//   const idClient = change.after.child("idClient").val();
//         return admin.database().ref().child("Client").child(idClient).child("token").once('value')
//     .then(function(snapshot){
//       var token_id = snapshot.val();
//       console.log(token_id);
//       var payload = {
//         data: {
//           text : "Please! Review Order",
//         },
//         token : token_id
  
//       };
//       return admin
//         .messaging()
//         .send(payload)
//         .then(function(response) {
//           return console.log("Successfully sent message REVIEW: ", response);
//         })
//         .catch(function(error) {
//           return console.log("Error sending message:", error);
//         });
//   });
// });