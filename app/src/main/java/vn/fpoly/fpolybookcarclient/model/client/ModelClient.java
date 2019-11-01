package vn.fpoly.fpolybookcarclient.model.client;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;

public class ModelClient {

    DatabaseReference dataClient;
    public void addClientDatabase(Client client){
        dataClient = FirebaseDatabase.getInstance().getReference().child(Constans.childClient);
        dataClient.child(client.getKey()).setValue(client);
    }
}
