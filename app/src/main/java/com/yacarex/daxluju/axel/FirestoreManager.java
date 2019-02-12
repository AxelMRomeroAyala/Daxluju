package com.yacarex.daxluju.axel;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreManager {

    private FirebaseFirestore db;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
    }
}
