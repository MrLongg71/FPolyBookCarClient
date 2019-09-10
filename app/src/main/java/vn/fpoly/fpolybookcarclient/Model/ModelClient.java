package vn.fpoly.fpolybookcarclient.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;

public class ModelClient {

    DatabaseReference dataClient;
    public void addClientDatabase(Client client){
        dataClient = FirebaseDatabase.getInstance().getReference().child(Constans.childClient);
        dataClient.child(client.getKey()).setValue(client);
    }
}
