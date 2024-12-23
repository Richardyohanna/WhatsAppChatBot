package com.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service 
public class FirebaseService {

	/*
	 * private final Firestore db;
	 * 
	 * public FirebaseService() { db =
	 * FirestoreOptions.getDefaultInstance().getService(); }
	 */

	/*
	 * public String getReply(String trigger) { try { return
	 * db.collection("whatsapp_replies") .document(trigger) .get() .get()
	 * .getString("response"); } catch (Exception e) { e.printStackTrace(); return
	 * "Sorry, I couldn't find a reply."; } }
	 */

	public String createCRUD(firebaseCrud crud) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("whatsapp_replies").document(crud.getDocumentID()).set(crud);
		return collectionApiFuture.get().getUpdateTime().toString();
	}

	public firebaseCrud getCRUD(String documentID) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("whatsapp_replies").document(documentID);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		
		
		firebaseCrud crud;
		
		if(document.exists()) {
			crud = document.toObject(firebaseCrud.class);
			
			 String response = document.getString("trigger");
	            
			System.out.println( future);
			return crud;
		}
		return null;
	}

	public String updateCRUD(firebaseCrud crud) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("whatsapp_replies").document(crud.getDocumentID()).set(crud);
		return collectionApiFuture.get().getUpdateTime().toString();

	}

	public String deleteCRUD(String documentID) {
		// TODO Auto-generated method stub
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection("whatsapp_replies").document(documentID).delete();
		
		return "Successfully deleted " + documentID;
	}
	
	
}
