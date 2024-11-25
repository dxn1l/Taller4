package com.example.taller4.Database

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseDataBaseRepository {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("usuarios")


    fun addUser(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.document(user.id).set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun getUsers(onSuccess: (List<User>) -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.get()
            .addOnSuccessListener { result ->
                val users = result.toObjects(User::class.java)
                onSuccess(users)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun deleteUser(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.document(user.id).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun userExists(email: String, onSuccess: (Boolean) -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.whereEqualTo("email", email).get()
            .addOnSuccessListener { result ->
                onSuccess(!result.isEmpty)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }





}